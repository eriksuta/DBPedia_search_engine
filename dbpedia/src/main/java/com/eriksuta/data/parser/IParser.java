package com.eriksuta.data.parser;

import java.io.File;
import java.util.List;

/**
 *  @author shood
 *
 * */
public interface IParser {

    /**
     *  returns a file containing results from search on entire data set
     *
     *  @param expression - a String value to be found
     *
     *  @return java.io.File
     * */
    public File parseAllToFile(String expression);


    /**
     *  returns a file containing results from search on data set specified by list of file names.
     *  Can be used for filtered searches in labels, abstracts, wiki links etc.
     *
     *  @param expression - a String value to be found,
     *  @param sourceFileList - a List of values containing file names to be searched
     *
     *  @return java.io.File
     * */
    public File parseToFile(String expression, List<String> sourceFileList);
}
