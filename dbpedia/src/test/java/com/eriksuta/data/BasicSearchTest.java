package com.eriksuta.data;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;
import java.io.File;

/**
 *  @author shood
 *
 *  TODO - redirect the output to some log
 *  TODO - add tests for xmlRdf format and JSON
 * */
public class BasicSearchTest {

    private static final String DIR = "src/test/resources/data/";
    private static final String FILE_OUT_TEST_1 = DIR + "BasicSearchTest_result_1.ttl";
    private static final String FILE_OUT_TEST_2 = DIR + "BasicSearchTest_result_2.ttl";
    private static final String FILE_OUT_TEST_3 = DIR + "BasicSearchTest_result_3.ttl";

    private static final String FILE_OUT_TEST_4 = DIR + "BasicSearchTest_result_4.xml";
    private static final String FILE_OUT_TEST_5 = DIR + "BasicSearchTest_result_5.xml";

    private static final String TEST_EXPRESSION_1 = "Mass_Effect";
    private static final String TEST_EXPRESSION_2 = "Sir_Isaac_Newton";
    private static final String TEST_EXPRESSION_3 = "Bioware";

    private static IParser rdfParser;
    private static IParser xmlRdfParser;
    private static IParser xmlParser;

    @BeforeClass
    public static void beforeClass(){
        System.out.println("==========|BASIC SEARCH TEST SUIT START|==========");
        rdfParser = new RdfParser();
        xmlRdfParser = new XmlRdfParser();
        xmlParser = new XMLParser();
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("==========|BASIC SEARCH TEST SUIT END|==========");
    }

    @Test
    public void test_01_TTL(){
        System.out.println("=====|BasicSearchTest 1: ");

        String expression = TEST_EXPRESSION_1;

        File result = rdfParser.parseAllToFile(expression);

        File expected = new File(FILE_OUT_TEST_1);

        try {
            Assert.assertNotNull(result);
            Assert.assertEquals(FileUtils.readLines(result), FileUtils.readLines(expected));
        } catch (Exception e){
            System.out.println("File can't be opened or does not exist: " + e);
        }
    }

    @Test
    public void test_02_TTL(){
        System.out.println("=====|BasicSearchTest 2: ");

        String expression = TEST_EXPRESSION_2;

        File result = rdfParser.parseAllToFile(expression);

        File expected = new File(FILE_OUT_TEST_2);

        try {
            Assert.assertNotNull(result);
            Assert.assertEquals(FileUtils.readLines(result), FileUtils.readLines(expected));
        } catch (Exception e){
            System.out.println("File can't be opened or does not exist: " + e);
        }
    }

    @Test
    public void test_03_TTL(){
        System.out.println("=====|BasicSearchTest 3: ");

        String expression = TEST_EXPRESSION_3;

        File result = rdfParser.parseAllToFile(expression);

        File expected = new File(FILE_OUT_TEST_3);

        try {
            Assert.assertNotNull(result);
            Assert.assertEquals(FileUtils.readLines(result), FileUtils.readLines(expected));
        } catch (Exception e){
            System.out.println("File can't be opened or does not exist: " + e);
        }
    }

    @Test
    public void test_04_XML(){
        System.out.println("=====|BasicSearchTest 4: ");

        String expression = TEST_EXPRESSION_2;

        File result = xmlParser.parseAllToFile(expression);

        File expected = new File(FILE_OUT_TEST_4);

        try {
            Assert.assertNotNull(result);
            Assert.assertEquals(FileUtils.readLines(result), FileUtils.readLines(expected));
        } catch (Exception e){
            System.out.println("File can't be opened or does not exist: " + e);
        }
    }

    @Test
    public void test_05_XML(){
        System.out.println("=====|BasicSearchTest 5: ");

        String expression = TEST_EXPRESSION_1;

        File result = xmlParser.parseAllToFile(expression);

        File expected = new File(FILE_OUT_TEST_5);

        try {
            Assert.assertNotNull(result);
            Assert.assertEquals(FileUtils.readLines(result), FileUtils.readLines(expected));
        } catch (Exception e){
            System.out.println("File can't be opened or does not exist: " + e);
        }
    }
}
