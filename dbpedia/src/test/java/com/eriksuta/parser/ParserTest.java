package com.eriksuta.parser;


import com.eriksuta.data.Indexer;
import com.eriksuta.data.ParserImpl;
import com.eriksuta.data.SearchUtil;
import com.eriksuta.data.search.SearchResultType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openrdf.model.Model;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.Rio;
import org.openrdf.rio.helpers.StatementCollector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  @author shood
 * */
public class ParserTest {

    private static final String DIR = "src/main/resources/rdf/";

    private static final String F_CATEGORY_LABELS_SK = DIR + "category_labels_sk.ttl";

    @BeforeClass
    public static void beforeClass(){
        System.out.println("==========|BASIC PARSER TEST SUIT START|==========");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("==========|BASIC PARSER TEST SUIT END|==========");
    }

    @Test
    public void performParseProcess(){
        ParserImpl parser = new ParserImpl();
        parser.parseSlovakDBPedia();
    }

    @Test
    public void performIndexProcess(){
        Indexer indexer = new Indexer();
        indexer.performIndexing();
    }

    @Test
    public void testBratislava(){
        SearchUtil searchUtil = new SearchUtil();
        SearchResultType result = searchUtil.basicSearch("Bratislava");
        System.out.println(result.toString());
    }

    @Test
    public void testBioware(){
        SearchUtil searchUtil = new SearchUtil();
        SearchResultType result = searchUtil.basicSearch("Bioware");
        System.out.println(result.toString());
    }

    @Test
    public void testMassEffect(){
        SearchUtil searchUtil = new SearchUtil();
        SearchResultType result = searchUtil.basicSearch("Mass Effect");
        System.out.println(result.toString());
    }
}
