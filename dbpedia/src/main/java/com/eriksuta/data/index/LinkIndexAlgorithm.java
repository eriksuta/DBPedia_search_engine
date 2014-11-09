package com.eriksuta.data.index;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;

/**
 *  @author shood
 * */
public class LinkIndexAlgorithm implements IndexAlgorithm{

    private String labelName;
    private String contentName;

    public LinkIndexAlgorithm(String labelName, String contentName){
        this.labelName = labelName;
        this.contentName = contentName;
    }

    @Override
    public void createSimpleIndex(String line, IndexWriter indexWriter) throws IOException {
        String[] content = line.split("->");
        Document document = new Document();
        document.add(new TextField(labelName, content[0], Field.Store.YES));

        /*
        *   This is a dirty fix for issue from https://issues.apache.org/jira/browse/LUCENE-5472
        *   If a term is longer than 2^15, IllegalArgumentException is thrown and the process
        *   of indexing is stopped. We don't want that, so we just won't index fields longer
        *   than 2^15 bytes.
        * */
        if(content[1].getBytes().length < 32766){
            document.add(new StringField(contentName, content[1], Field.Store.YES));
        }

        try {
            indexWriter.addDocument(document);
        } catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }

    }
}
