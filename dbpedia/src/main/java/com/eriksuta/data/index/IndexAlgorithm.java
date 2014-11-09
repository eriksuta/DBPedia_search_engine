package com.eriksuta.data.index;

import org.apache.lucene.index.IndexWriter;

import java.io.IOException;

/**
 *  @author shood
 * */
public interface IndexAlgorithm {

    /**
     *  createIndex method is a single IndexAlgorithm interface method that takes care of adding
     *  a relevant index from one line of parsed data.
     * */
    public void createSimpleIndex(String line, IndexWriter indexWriter) throws IOException;
}
