package com.eriksuta.data;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;

/**
 *  @author shood
 * */
public class Indexer {

    public static final String INDEX_DIR = "src/main/webapp/parsed/index";

    public static final String DATA_DIR = "src/main/webapp/parsed/";
    public static final String F_ARTICLE_CATEGORIES_SK = DATA_DIR + "article_categories_sk.txt";
    public static final String F_ARTICLE_CATEGORIES_EN_URIS_SK = DATA_DIR + "article_categories_en_uris_sk.txt";

    private Directory directory;
    private Analyzer analyzer;
    private static IndexWriter indexWriter;

    /**
     *  Constructor - performs necessary initialization
     * */
    public Indexer(){
        initIndexer();
    }

    public void initIndexer(){
        analyzer = new StandardAnalyzer();

        try {
            directory = FSDirectory.open(new File(INDEX_DIR));

            if(indexWriter == null){
                IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);
                indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
                indexWriter = new IndexWriter(directory, indexWriterConfig);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createIndexes(){
        try {
            createCategoryIndexes(new File(F_ARTICLE_CATEGORIES_SK));
            createCategoryIndexes(new File(F_ARTICLE_CATEGORIES_EN_URIS_SK));

            indexWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createCategoryIndexes(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while((line = bufferedReader.readLine()) != null){
            if(line.isEmpty()){
                continue;
            }

            String[] content = line.split(":");

            Document document = new Document();
            document.add(new TextField(IndexLabelNames.ARTICLE_CATEGORY_LABEL, content[0], Field.Store.YES));
            document.add(new StringField(IndexLabelNames.ARTICLE_CATEGORY_CONTENT, content[1], Field.Store.YES));
            indexWriter.addDocument(document);
        }

        bufferedReader.close();
        fileReader.close();
    }

    public Directory getDirectory() {
        return directory;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public IndexWriter getIndexWriter() {
        return indexWriter;
    }
}
