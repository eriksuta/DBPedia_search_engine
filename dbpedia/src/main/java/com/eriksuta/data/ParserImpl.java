package com.eriksuta.data;

import com.eriksuta.data.handler.BasicRdfHandler;
import com.eriksuta.data.handler.CategoryLabelResultHandler;
import com.eriksuta.data.handler.ExternalLinksHandler;
import com.eriksuta.data.handler.InfoboxPropertiesHandler;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.Rio;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  @author shood
 * */
public class ParserImpl implements IParser{

    private static ParserImpl parser;

    public static final String OUT_DIR = "src/main/webapp/parsed/";
    public static final String TTL_DIR = "src/main/resources/rdf/";

    public static final String TTL_ARTICLE_CATEGORIES_EN_URIS_SK = TTL_DIR + "article_categories_en_uris_sk.ttl";
    public static final String TTL_ARTICLE_CATEGORIES_SK = TTL_DIR + "article_categories_sk.ttl";
    public static final String TTL_EXTERNAL_LINKS_EN_URIS_SK = TTL_DIR + "external_links_en_uris_sk.ttl";
    public static final String TTL_EXTERNAL_LINKS_SK = TTL_DIR + "external_links_sk.ttl";
    public static final String TTL_FREEBASE_LINKS = TTL_DIR + "freebase_links_sk.ttl";
    public static final String TTL_INFOBOX_PROPERTIES_EN_URIS_SK = TTL_DIR + "infobox_properties_en_uris_sk.ttl";
    public static final String TTL_INFOBOX_PROPERTIES_SK = TTL_DIR + "infobox_properties_sk.ttl";
    public static final String TTL_INTER_LANGUAGE_LINKS = TTL_DIR + "interlanguage_links_sk.ttl";

    public static final String ARTICLE_CATEGORIES_EN_URIS_SK = OUT_DIR + "article_categories_en_uris_sk.txt";
    public static final String ARTICLE_CATEGORIES_SK = OUT_DIR + "article_categories_sk.txt";
    public static final String EXTERNAL_LINKS_EN_URIS_SK = OUT_DIR + "external_links_en_uris_sk.txt";
    public static final String EXTERNAL_LINKS_SK = OUT_DIR + "external_links_sk.txt";
    public static final String FREEBASE_LINKS_SK = OUT_DIR + "freebase_links_sk.txt";
    public static final String INFOBOX_PROPERTIES_EN_URIS_SK = OUT_DIR + "infobox_properties_en_uris_sk.txt";
    public static final String INFOBOX_PROPERTIES_SK = OUT_DIR + "infobox_properties_sk.txt";
    public static final String INTER_LANGUAGE_LINKS = OUT_DIR + "interlanguage_links_sk.txt";

    private static BufferedWriter bufferedWriter;
    private RDFParser rdfParser = Rio.createParser(RDFFormat.TURTLE);

    public void parseSlovakDBPedia(){
        parseArticleCategories(new File(TTL_ARTICLE_CATEGORIES_SK), new File(ARTICLE_CATEGORIES_SK));
        sortStatementsInFile(new File(ARTICLE_CATEGORIES_SK));

        parseArticleCategories(new File(TTL_ARTICLE_CATEGORIES_EN_URIS_SK), new File(ARTICLE_CATEGORIES_EN_URIS_SK));
        sortStatementsInFile(new File(ARTICLE_CATEGORIES_EN_URIS_SK));

        parseInfoboxProperties(new File(TTL_INFOBOX_PROPERTIES_EN_URIS_SK), new File(INFOBOX_PROPERTIES_EN_URIS_SK));
        sortStatementsInFile(new File(INFOBOX_PROPERTIES_EN_URIS_SK));

        parseInfoboxProperties(new File(TTL_INFOBOX_PROPERTIES_SK), new File(INFOBOX_PROPERTIES_SK));
        sortStatementsInFile(new File(INFOBOX_PROPERTIES_SK));

        parseExternalLinks(new File(TTL_EXTERNAL_LINKS_SK), new File(EXTERNAL_LINKS_SK));
        sortStatementsInFile(new File(EXTERNAL_LINKS_SK));

        parseExternalLinks(new File(TTL_EXTERNAL_LINKS_EN_URIS_SK), new File(EXTERNAL_LINKS_EN_URIS_SK));
        sortStatementsInFile(new File(EXTERNAL_LINKS_EN_URIS_SK));

        parseExternalLinks(new File(TTL_FREEBASE_LINKS), new File(FREEBASE_LINKS_SK));
        sortStatementsInFile(new File(FREEBASE_LINKS_SK));

        parseExternalLinks(new File(TTL_INTER_LANGUAGE_LINKS), new File(INTER_LANGUAGE_LINKS));
        sortStatementsInFile(new File(INTER_LANGUAGE_LINKS));
    }

    public static ParserImpl getParserInstance(){
        if(parser == null){
            parser = new ParserImpl();
        }

        return parser;
    }

    public void parseRdfFile(File input, File output, BasicRdfHandler handler){
        if(input == null || output == null || handler == null){
            return;
        }

        System.out.println("Parsing file: " + input);
        System.out.println("Output file: " + output);

        rdfParser.setRDFHandler(handler);

        try{
            //Define output .txt file
            bufferedWriter = new BufferedWriter(new FileWriter(output));

            //Define input .ttl file
            FileInputStream fileInputStream = new FileInputStream(input);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            rdfParser.parse(inputStreamReader, "");

            System.out.println("Statements: " + handler.getNumberOfStatements());
            System.out.println("Unique categories: " + handler.getNumberOfStatementsAfter());
            bufferedWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void parseExternalLinks(File input, File output){
        ExternalLinksHandler externalLinksHandler = new ExternalLinksHandler();
        parseRdfFile(input, output, externalLinksHandler);
    }

    public void parseArticleCategories(File input, File output){
        CategoryLabelResultHandler categoryHandler = new CategoryLabelResultHandler();
        parseRdfFile(input, output, categoryHandler);
    }

    public void parseInfoboxProperties(File input, File output){
        InfoboxPropertiesHandler infoboxPropertiesHandler = new InfoboxPropertiesHandler();
        parseRdfFile(input, output, infoboxPropertiesHandler);
    }

    private void sortStatementsInFile(File fileToSort){
        if(fileToSort == null){
            return;
        }

        try {
            FileReader fileReader = new FileReader(fileToSort);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<String> lines = new ArrayList<String>();
            String inputLine;

            while((inputLine = bufferedReader.readLine()) != null){
                lines.add(inputLine);
            }

            bufferedReader.close();
            fileReader.close();

            Collections.sort(lines);

            FileWriter fileWriter = new FileWriter(fileToSort);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for(String line: lines){
                printWriter.println(line);
            }

            printWriter.flush();
            printWriter.close();
            fileWriter.close();

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
