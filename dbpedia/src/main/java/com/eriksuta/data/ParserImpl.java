package com.eriksuta.data;

import com.eriksuta.data.handler.CategoryLabelResultHandler;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.Rio;

import java.io.*;

/**
 *  @author shood
 * */
public class ParserImpl implements IParser{

    private static ParserImpl parser;

    public static final String OUT_DIR = "src/main/webapp/parsed/";
    public static final String TTL_DIR = "src/main/resources/rdf/";

    public static final String TTL_ARTICLE_CATEGORIES_EN_URIS_SK = TTL_DIR + "article_categories_en_uris_sk.ttl";
    public static final String TTL_ARTICLE_CATEGORIES_SK = TTL_DIR + "article_categories_sk.ttl";

    public static final String ARTICLE_CATEGORIES_EN_URIS_SK = OUT_DIR + "article_categories_en_uris_sk.txt";
    public static final String ARTICLE_CATEGORIES_SK = OUT_DIR + "article_categories_sk.txt";

    private static BufferedWriter bufferedWriter;
    private RDFParser rdfParser = Rio.createParser(RDFFormat.TURTLE);

    /**
     *
     * */
    public void parseSlovakDBPedia(){
        parseArticleCategories(new File(TTL_ARTICLE_CATEGORIES_SK), new File(ARTICLE_CATEGORIES_SK));
        parseArticleCategories(new File(TTL_ARTICLE_CATEGORIES_EN_URIS_SK), new File(ARTICLE_CATEGORIES_EN_URIS_SK));
    }

    public static ParserImpl getParserInstance(){
        if(parser == null){
            parser = new ParserImpl();
        }

        return parser;
    }

    public void parseArticleCategories(File input, File output){
        if(input == null || output == null){
            return;
        }

        System.out.println("Parsing file: " + input);
        System.out.println("Output file: " + output);

        CategoryLabelResultHandler categoryHandler = new CategoryLabelResultHandler();
        rdfParser.setRDFHandler(categoryHandler);

        try{
            //Define output .txt file
            bufferedWriter = new BufferedWriter(new FileWriter(output));

            //Define input .ttl file
            FileInputStream fileInputStream = new FileInputStream(input);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            rdfParser.parse(inputStreamReader, "");

            System.out.println("Statements: " + categoryHandler.getNumberOfStatements());
            System.out.println("Unique categories: " + categoryHandler.getNumberOfCategories());
            bufferedWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeToFile(String text){
        if(bufferedWriter == null){
            return;
        }

        try {
            bufferedWriter.write(text);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
