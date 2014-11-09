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
public class PropertyIndexAlgorithm implements IndexAlgorithm{

    private String labelName;
    private String contentName;

    public PropertyIndexAlgorithm(String labelName, String contentName){
        this.labelName = labelName;
        this.contentName = contentName;
    }

    @Override
    public void createSimpleIndex(String line, IndexWriter indexWriter) throws IOException {
        String[] content = line.split("->");
        Document document = new Document();
        document.add(new TextField(labelName, content[0], Field.Store.YES));
        document.add(new StringField(contentName, content[1], Field.Store.YES));
        indexWriter.addDocument(document);
    }
}
