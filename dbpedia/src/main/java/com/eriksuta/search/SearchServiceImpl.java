package com.eriksuta.search;

import com.eriksuta.data.IndexLabelNames;
import com.eriksuta.data.Indexer;
import com.eriksuta.data.search.InfoboxPropertyType;
import com.eriksuta.data.search.SearchResultType;
import com.eriksuta.page.SearchOptions;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 *  A simple implementation of SearchService interface for purposes
 *  of search engine based on indexed dump of Slovak DBPedia
 *
 *  @author shood
 *
 *  TODO - handle multiple word searches - League of Legenfs -> League_of_Legends
 *  TODO - handle substring searches - not working correctly now
 * */
public class SearchServiceImpl implements SearchService, Serializable{

    /**
     *  Right now, SearchService is implemented as a Singleton object,
     *  we don't want several parallel searches to perform at once.
     *  Will rework when current functionality is properly tested
     *  and functional.
     * */
    private static SearchServiceImpl instance = null;

    private SearchServiceImpl(){}

    public static SearchServiceImpl getInstance(){
        if(instance == null){
            instance = new SearchServiceImpl();
        }

        return instance;
    }

    @Override
    public SearchResultType search(String searchTerm) {
        long startTime = System.currentTimeMillis();

        SearchResultType result = new SearchResultType();
        result.setQueryTerm(searchTerm);

        Indexer indexer = new Indexer();
        Directory directory = indexer.getDirectory();

        try {
            //Create queries
            Query articleCategoryQuery = new QueryParser(IndexLabelNames.ARTICLE_CATEGORY_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query externalLinkQuery = new QueryParser(IndexLabelNames.EXTERNAL_LINK_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query freebaseLinkQuery = new QueryParser(IndexLabelNames.FREEBASE_LINK_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query interLanguageLinkQuery = new QueryParser(IndexLabelNames.INTER_LANGUAGE_LINKS_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query pageLinkQuery = new QueryParser(IndexLabelNames.PAGE_LINKS_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query pageLinkUnredirectedQuery = new QueryParser(IndexLabelNames.PAGE_LINKS_UNREDIRECTED_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query wikipediaLinkQuery = new QueryParser(IndexLabelNames.WIKIPEDIA_LINKS_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query infoboxQuery = new QueryParser(IndexLabelNames.INFOBOX_PROPERTIES_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query labelQuery = new QueryParser(IndexLabelNames.LABEL_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query longAbstractQuery = new QueryParser(IndexLabelNames.LONG_ABSTRACT_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query shortAbstractQuery = new QueryParser(IndexLabelNames.SHORT_ABSTRACT_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query outDegreeQuery = new QueryParser(IndexLabelNames.OUT_DEGREE_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query pageIdQuery = new QueryParser(IndexLabelNames.PAGE_ID_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query pageLengthQuery = new QueryParser(IndexLabelNames.PAGE_LENGTH_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query redirectQuery = new QueryParser(IndexLabelNames.REDIRECTS_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query redirectTransitiveQuery = new QueryParser(IndexLabelNames.REDIRECTS_TRANSITIVE_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query revisionIdQuery = new QueryParser(IndexLabelNames.REVISION_ID_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query revisionUriQuery = new QueryParser(IndexLabelNames.REVISION_URI_LABEL ,indexer.getAnalyzer()).parse(searchTerm);
            Query skosCategoryQuery = new QueryParser(IndexLabelNames.SKOS_CATEGORIES_LABEL ,indexer.getAnalyzer()).parse(searchTerm);

            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            ScoreDoc[] infoboxPropertiesHits = doQuery(indexSearcher, infoboxQuery);
            List<InfoboxPropertyType> infoboxProperties = SearchUtil.processInfoboxProperties(infoboxPropertiesHits, indexSearcher, IndexLabelNames.INFOBOX_PROPERTIES_CONTENT);
            result.getInfoboxProperties().addAll(infoboxProperties);

            ScoreDoc[] labelsHits = doQuery(indexSearcher, labelQuery);
            List<String> labels = SearchUtil.processStringListResult(labelsHits, indexSearcher, IndexLabelNames.LABEL_CONTENT);
            result.getLabels().addAll(labels);

            ScoreDoc[] shortAbstractHits = doQuery(indexSearcher, shortAbstractQuery);
            List<String> shortAbstracts = SearchUtil.processStringListResult(shortAbstractHits, indexSearcher, IndexLabelNames.SHORT_ABSTRACT_CONTENT);
            result.getShortAbstracts().addAll(shortAbstracts);

            ScoreDoc[] longAbstractHits = doQuery(indexSearcher, longAbstractQuery);
            List<String> longAbstracts = SearchUtil.processStringListResult(longAbstractHits, indexSearcher, IndexLabelNames.LONG_ABSTRACT_CONTENT);
            result.getLongAbstracts().addAll(longAbstracts);

            ScoreDoc[] redirectsHits = doQuery(indexSearcher, redirectQuery);
            List<String> redirects = SearchUtil.processStringListResult(redirectsHits, indexSearcher, IndexLabelNames.REDIRECTS_CONTENT);
            result.getRedirects().addAll(redirects);

            ScoreDoc[] redirectsTransitiveHits = doQuery(indexSearcher, redirectTransitiveQuery);
            List<String> redirectsTransitive = SearchUtil.processStringListResult(redirectsTransitiveHits, indexSearcher, IndexLabelNames.REDIRECTS_TRANSITIVE_CONTENT);
            result.getRedirectsTransitive().addAll(redirectsTransitive);

            ScoreDoc[] articleCategoryHits = doQuery(indexSearcher, articleCategoryQuery);
            List<String> articleCategories = SearchUtil.processStringListResult(articleCategoryHits, indexSearcher, IndexLabelNames.ARTICLE_CATEGORY_CONTENT);
            result.getArticleCategories().addAll(articleCategories);

            ScoreDoc[] skosCategoriesHits = doQuery(indexSearcher, skosCategoryQuery);
            List<String> skosCategories = SearchUtil.processStringListResult(skosCategoriesHits, indexSearcher, IndexLabelNames.SKOS_CATEGORIES_CONTENT);
            result.getSkosCategories().addAll(skosCategories);

            ScoreDoc[] externalLinksHits = doQuery(indexSearcher, externalLinkQuery);
            List<String> externalLinks = SearchUtil.processStringListResult(externalLinksHits, indexSearcher, IndexLabelNames.EXTERNAL_LINK_CONTENT);
            result.getExternalLinks().addAll(externalLinks);

            ScoreDoc[] freebaseLinksHits = doQuery(indexSearcher, freebaseLinkQuery);
            List<String> freebaseLinks = SearchUtil.processStringListResult(freebaseLinksHits, indexSearcher, IndexLabelNames.FREEBASE_LINK_CONTENT);
            result.getFreebaseLinks().addAll(freebaseLinks);

            ScoreDoc[] interLanguageLinksHits = doQuery(indexSearcher, interLanguageLinkQuery);
            List<String> interLanguageLinks = SearchUtil.processStringListResult(interLanguageLinksHits, indexSearcher, IndexLabelNames.INTER_LANGUAGE_LINKS_CONTENT);
            result.getInterLanguageLinks().addAll(interLanguageLinks);

            ScoreDoc[] pageLinkHits = doQuery(indexSearcher, pageLinkQuery);
            List<String> pageLinks = SearchUtil.processStringListResult(pageLinkHits, indexSearcher, IndexLabelNames.PAGE_LINKS_CONTENT);
            result.getPageLinksSk().addAll(pageLinks);

            ScoreDoc[] pageLinkUnredirectedHits = doQuery(indexSearcher, pageLinkUnredirectedQuery);
            List<String> pageLinksUnredirected = SearchUtil.processStringListResult(pageLinkUnredirectedHits, indexSearcher, IndexLabelNames.PAGE_LINKS_UNREDIRECTED_CONTENT);
            result.getPageLinksEnUrisSk().addAll(pageLinksUnredirected);

            ScoreDoc[] wikipediaLinkHits = doQuery(indexSearcher, wikipediaLinkQuery);
            List<String> wikipediaLinks = SearchUtil.processStringListResult(wikipediaLinkHits, indexSearcher, IndexLabelNames.WIKIPEDIA_LINKS_CONTENT);
            result.getWikipediaLinks().addAll(wikipediaLinks);

            ScoreDoc[] revisionUriHits = doQuery(indexSearcher, revisionUriQuery);
            String revisionUri = SearchUtil.processStringProperty(revisionUriHits, indexSearcher, IndexLabelNames.REVISION_URI_CONTENT);
            result.setRevisionUri(revisionUri);

            ScoreDoc[] outDegreeHits = doQuery(indexSearcher, outDegreeQuery);
            String outDegree = SearchUtil.processIntegerProperty(outDegreeHits, indexSearcher, IndexLabelNames.OUT_DEGREE_CONTENT);
            result.setOutDegree(outDegree);

            ScoreDoc[] pageIdHits = doQuery(indexSearcher, pageIdQuery);
            String pageId = SearchUtil.processIntegerProperty(pageIdHits, indexSearcher, IndexLabelNames.PAGE_ID_CONTENT);
            result.setPageId(pageId);

            ScoreDoc[] pageLengthHits = doQuery(indexSearcher, pageLengthQuery);
            String pageLength = SearchUtil.processIntegerProperty(pageLengthHits, indexSearcher, IndexLabelNames.PAGE_LENGTH_CONTENT);
            result.setPageLength(pageLength);

            ScoreDoc[] revisionIdHits = doQuery(indexSearcher, revisionIdQuery);
            String revisionId = SearchUtil.processIntegerProperty(revisionIdHits, indexSearcher, IndexLabelNames.REVISION_ID_CONTENT);
            result.setRevisionId(revisionId);

            indexReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        result.setSearchTime(endTime - startTime);

        return result;
    }

    @Override
    public SearchResultType search(String searchTerm, SearchOptions searchOptions) {
        return null;
    }

    @Override
    public SearchResultType searchInLinks(String searchTerm) {
        return null;
    }

    @Override
    public SearchResultType searchInAbstracts(String searchTerm) {
        return null;
    }

    @Override
    public SearchResultType searchInCategories(String searchTerm) {
        return null;
    }

    @Override
    public SearchResultType searchInInfoboxProperties(String searchTerm) {
        return null;
    }

    private ScoreDoc[] doQuery(IndexSearcher indexSearcher, Query query) throws IOException {
        //Perform the search
        int hitsPerPage = 10000;
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
        indexSearcher.search(query, collector);

        return collector.topDocs().scoreDocs;
    }
}
