package com.eriksuta.data;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;

/**
 *  @author shood
 * */
public class SearchUtil {

    public void simpleCategorySearch(String searchQuery){
        Indexer indexer = new Indexer();
        Directory directory = indexer.getDirectory();

        try {
            //Create the query
            Query query = new QueryParser("title", indexer.getAnalyzer()).parse(searchQuery);

            //Perform the search
            int hitsPerPage = 10;
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);

            indexSearcher.search(query, collector);
            ScoreDoc[] hits = collector.topDocs().scoreDocs;

            //Display the results
            System.out.println("Found: " + hits.length + " hits.");

            for(int i = 0; i < hits.length; i++){
                int docId = hits[i].doc;
                Document d = indexSearcher.doc(docId);

                System.out.println(i + ". " + d.get("title") + ": " + d.get("categories"));
            }

            indexReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
