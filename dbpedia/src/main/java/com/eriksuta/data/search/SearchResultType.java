package com.eriksuta.data.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  @author shood
 * */
public class SearchResultType implements Serializable{

    /**
     *  The term that was searched
     * */
    private String queryTerm;
    private long searchTime;

    private List<String> labels = new ArrayList<String>();

    private List<String> articleCategories = new ArrayList<String>();
    private List<String> skosCategories = new ArrayList<String>();

    private List<String> externalLinks = new ArrayList<String>();
    private List<String> freebaseLinks = new ArrayList<String>();
    private List<String> wikipediaLinks = new ArrayList<String>();
    private List<String> interLanguageLinks = new ArrayList<String>();
    private List<String> pageLinksSk = new ArrayList<String>();
    private List<String> pageLinksEnUrisSk = new ArrayList<String>();

    private List<String> redirects = new ArrayList<String>();
    private List<String> redirectsTransitive = new ArrayList<String>();

    private List<InfoboxPropertyType> infoboxProperties = new ArrayList<InfoboxPropertyType>();

    private List<String> longAbstracts = new ArrayList<String>();
    private List<String> shortAbstracts = new ArrayList<String>();

    private Integer outDegree;
    private Integer pageId;
    private Integer pageLength;

    private Integer revisionId;
    private String revisionUri;

    public SearchResultType(){

    }

    public long getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(long searchTime) {
        this.searchTime = searchTime;
    }

    public String getQueryTerm() {
        return queryTerm;
    }

    public void setQueryTerm(String queryTerm) {
        this.queryTerm = queryTerm;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(List<String> articleCategories) {
        this.articleCategories = articleCategories;
    }

    public List<String> getSkosCategories() {
        return skosCategories;
    }

    public void setSkosCategories(List<String> skosCategories) {
        this.skosCategories = skosCategories;
    }

    public List<String> getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(List<String> externalLinks) {
        this.externalLinks = externalLinks;
    }

    public List<String> getFreebaseLinks() {
        return freebaseLinks;
    }

    public void setFreebaseLinks(List<String> freebaseLinks) {
        this.freebaseLinks = freebaseLinks;
    }

    public List<String> getWikipediaLinks() {
        return wikipediaLinks;
    }

    public void setWikipediaLinks(List<String> wikipediaLinks) {
        this.wikipediaLinks = wikipediaLinks;
    }

    public List<String> getInterLanguageLinks() {
        return interLanguageLinks;
    }

    public void setInterLanguageLinks(List<String> interLanguageLinks) {
        this.interLanguageLinks = interLanguageLinks;
    }

    public List<String> getPageLinksSk() {
        return pageLinksSk;
    }

    public void setPageLinksSk(List<String> pageLinksSk) {
        this.pageLinksSk = pageLinksSk;
    }

    public List<String> getPageLinksEnUrisSk() {
        return pageLinksEnUrisSk;
    }

    public void setPageLinksEnUrisSk(List<String> pageLinksEnUrisSk) {
        this.pageLinksEnUrisSk = pageLinksEnUrisSk;
    }

    public List<String> getRedirects() {
        return redirects;
    }

    public void setRedirects(List<String> redirects) {
        this.redirects = redirects;
    }

    public List<String> getRedirectsTransitive() {
        return redirectsTransitive;
    }

    public void setRedirectsTransitive(List<String> redirects_transitive) {
        this.redirectsTransitive = redirects_transitive;
    }

    public List<InfoboxPropertyType> getInfoboxProperties() {
        return infoboxProperties;
    }

    public void setInfoboxProperties(List<InfoboxPropertyType> infoboxProperties) {
        this.infoboxProperties = infoboxProperties;
    }

    public List<String> getLongAbstracts() {
        return longAbstracts;
    }

    public void setLongAbstracts(List<String> longAbstracts) {
        this.longAbstracts = longAbstracts;
    }

    public List<String> getShortAbstracts() {
        return shortAbstracts;
    }

    public void setShortAbstracts(List<String> shortAbstracts) {
        this.shortAbstracts = shortAbstracts;
    }

    public Integer getOutDegree() {
        return outDegree;
    }

    public void setOutDegree(Integer outDegree) {
        this.outDegree = outDegree;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getPageLength() {
        return pageLength;
    }

    public void setPageLength(Integer pageLength) {
        this.pageLength = pageLength;
    }

    public Integer getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(Integer revisionId) {
        this.revisionId = revisionId;
    }

    public String getRevisionUri() {
        return revisionUri;
    }

    public void setRevisionUri(String revisionUri) {
        this.revisionUri = revisionUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchResultType)) return false;

        SearchResultType that = (SearchResultType) o;

        if (searchTime != that.searchTime) return false;
        if (articleCategories != null ? !articleCategories.equals(that.articleCategories) : that.articleCategories != null)
            return false;
        if (externalLinks != null ? !externalLinks.equals(that.externalLinks) : that.externalLinks != null)
            return false;
        if (freebaseLinks != null ? !freebaseLinks.equals(that.freebaseLinks) : that.freebaseLinks != null)
            return false;
        if (infoboxProperties != null ? !infoboxProperties.equals(that.infoboxProperties) : that.infoboxProperties != null)
            return false;
        if (interLanguageLinks != null ? !interLanguageLinks.equals(that.interLanguageLinks) : that.interLanguageLinks != null)
            return false;
        if (labels != null ? !labels.equals(that.labels) : that.labels != null) return false;
        if (longAbstracts != null ? !longAbstracts.equals(that.longAbstracts) : that.longAbstracts != null)
            return false;
        if (outDegree != null ? !outDegree.equals(that.outDegree) : that.outDegree != null) return false;
        if (pageId != null ? !pageId.equals(that.pageId) : that.pageId != null) return false;
        if (pageLength != null ? !pageLength.equals(that.pageLength) : that.pageLength != null) return false;
        if (pageLinksEnUrisSk != null ? !pageLinksEnUrisSk.equals(that.pageLinksEnUrisSk) : that.pageLinksEnUrisSk != null)
            return false;
        if (pageLinksSk != null ? !pageLinksSk.equals(that.pageLinksSk) : that.pageLinksSk != null) return false;
        if (queryTerm != null ? !queryTerm.equals(that.queryTerm) : that.queryTerm != null) return false;
        if (redirects != null ? !redirects.equals(that.redirects) : that.redirects != null) return false;
        if (redirectsTransitive != null ? !redirectsTransitive.equals(that.redirectsTransitive) : that.redirectsTransitive != null)
            return false;
        if (revisionId != null ? !revisionId.equals(that.revisionId) : that.revisionId != null) return false;
        if (revisionUri != null ? !revisionUri.equals(that.revisionUri) : that.revisionUri != null) return false;
        if (shortAbstracts != null ? !shortAbstracts.equals(that.shortAbstracts) : that.shortAbstracts != null)
            return false;
        if (skosCategories != null ? !skosCategories.equals(that.skosCategories) : that.skosCategories != null)
            return false;
        if (wikipediaLinks != null ? !wikipediaLinks.equals(that.wikipediaLinks) : that.wikipediaLinks != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = queryTerm != null ? queryTerm.hashCode() : 0;
        result = 31 * result + (int) (searchTime ^ (searchTime >>> 32));
        result = 31 * result + (labels != null ? labels.hashCode() : 0);
        result = 31 * result + (articleCategories != null ? articleCategories.hashCode() : 0);
        result = 31 * result + (skosCategories != null ? skosCategories.hashCode() : 0);
        result = 31 * result + (externalLinks != null ? externalLinks.hashCode() : 0);
        result = 31 * result + (freebaseLinks != null ? freebaseLinks.hashCode() : 0);
        result = 31 * result + (wikipediaLinks != null ? wikipediaLinks.hashCode() : 0);
        result = 31 * result + (interLanguageLinks != null ? interLanguageLinks.hashCode() : 0);
        result = 31 * result + (pageLinksSk != null ? pageLinksSk.hashCode() : 0);
        result = 31 * result + (pageLinksEnUrisSk != null ? pageLinksEnUrisSk.hashCode() : 0);
        result = 31 * result + (redirects != null ? redirects.hashCode() : 0);
        result = 31 * result + (redirectsTransitive != null ? redirectsTransitive.hashCode() : 0);
        result = 31 * result + (infoboxProperties != null ? infoboxProperties.hashCode() : 0);
        result = 31 * result + (longAbstracts != null ? longAbstracts.hashCode() : 0);
        result = 31 * result + (shortAbstracts != null ? shortAbstracts.hashCode() : 0);
        result = 31 * result + (outDegree != null ? outDegree.hashCode() : 0);
        result = 31 * result + (pageId != null ? pageId.hashCode() : 0);
        result = 31 * result + (pageLength != null ? pageLength.hashCode() : 0);
        result = 31 * result + (revisionId != null ? revisionId.hashCode() : 0);
        result = 31 * result + (revisionUri != null ? revisionUri.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Results retrieved in: ");
        sb.append(searchTime);
        sb.append(" milliseconds\n");

        sb.append("Results for search term: ");
        sb.append("'");
        sb.append(queryTerm);
        sb.append("'");
        sb.append(" are:\n");



        sb.append("Labels: ");
        for(String label: labels){
            sb.append(label);
            sb.append(", ");
        }
        sb.append("\n");

        sb.append("Page ID: ");
        sb.append(pageId);
        sb.append("\n");

        sb.append("Page Length: ");
        sb.append(pageLength);
        sb.append("\n");

        sb.append("Revision ID: ");
        sb.append(revisionId);
        sb.append("\n");

        sb.append("Revision URI: ");
        sb.append(revisionUri);
        sb.append("\n");

        sb.append("Out Degree: ");
        sb.append(outDegree);
        sb.append("\n");

        sb.append("Short Abstracts: \n");
        for(String shortAbstract: shortAbstracts){
            sb.append(shortAbstract);
            sb.append("\n");
        }
        sb.append("\n");

        sb.append("Long Abstracts: \n");
        for(String longAbstract: longAbstracts){
            sb.append(longAbstract);
            sb.append("\n");
        }
        sb.append("\n");

        sb.append("Infobox properties: \n");
        for(InfoboxPropertyType property: infoboxProperties){
            sb.append(property.getName());
            sb.append("=");
            sb.append(property.getValue());
            sb.append(", ");
        }
        sb.append("\n");

        sb.append("Article Categories: \n");
        for(String category: articleCategories){
            sb.append(category);
            sb.append(", ");
        }
        sb.append("\n");

        sb.append("Skos Categories: \n");
        for(String category: skosCategories){
            sb.append(category);
            sb.append(", ");
        }
        sb.append("\n");

        sb.append("External Links: \n");
        for(String link: externalLinks){
            sb.append(link);
            sb.append(", ");
        }
        sb.append("\n");

        sb.append("Freebase Links: \n");
        for(String link: freebaseLinks){
            sb.append(link);
            sb.append(", ");
        }
        sb.append("\n");

        sb.append("Wikipedia Links: \n");
        for(String link: wikipediaLinks){
            sb.append(link);
            sb.append(", ");
        }
        sb.append("\n");

        sb.append("Inter-Language Links: \n");
        for(String link: interLanguageLinks){
            sb.append(link);
            sb.append(", ");
        }
        sb.append("\n");

        sb.append("Page Links (SK): \n");
        for(String link: pageLinksSk){
            sb.append(link);
            sb.append(", ");
        }
        sb.append("\n");

        sb.append("Page Links (EN): \n");
        for(String link: pageLinksEnUrisSk){
            sb.append(link);
            sb.append(", ");
        }
        sb.append("\n");

        sb.append("Redirects: \n");
        for(String link: redirects){
            sb.append(link);
            sb.append(", ");
        }
        sb.append("\n");

        sb.append("Redirects (Transitive): \n");
        for(String link: redirectsTransitive){
            sb.append(link);
            sb.append(", ");
        }
        sb.append("\n");

        return sb.toString();
    }
}