package com.eriksuta.parser;


import com.eriksuta.data.Indexer;
import com.eriksuta.data.ParserImpl;
import com.eriksuta.data.SearchUtil;
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
    public void test_01_parse_category_labels_sk(){
        File file = new File(F_CATEGORY_LABELS_SK);

        RDFParser parser = Rio.createParser(RDFFormat.TURTLE);
        List myGraph = new ArrayList();
        parser.setRDFHandler(new StatementCollector(myGraph));

        try{
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);

            parser.parse(isr, "");
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(myGraph.size());
    }

    @Test
    public void test(){
        ParserImpl parser = new ParserImpl();
        parser.parseSlovakDBPedia();

        Indexer indexer = new Indexer();
        indexer.createIndexes();

        SearchUtil searchUtil = new SearchUtil();
        searchUtil.simpleCategorySearch("Bioware");
    }




}
