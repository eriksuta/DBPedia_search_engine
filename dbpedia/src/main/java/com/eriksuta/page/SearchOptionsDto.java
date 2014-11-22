package com.eriksuta.page;

import java.io.Serializable;

/**
 *  @author shood
 * */
public class SearchOptionsDto implements Serializable{

    public static final String F_MATCH_CASE = "matchCase";
    public static final String F_WHOLE_WORD = "wholeWord";
    public static final String F_REGEX = "asRegex";
    public static final String F_LINKS = "links";
    public static final String F_ABSTRACTS = "abstracts";
    public static final String F_CATEGORIES = "categories";
    public static final String F_INFO_PROPERTIES = "infoboxProperties";
    public static final String F_EVERYTHING = "everything";

    private boolean matchCase;
    private boolean wholeWord;
    private boolean asRegex;

    private boolean links;
    private boolean abstracts;
    private boolean categories;
    private boolean infoboxProperties;
    private boolean everything = true;

    public SearchOptionsDto(){}

    public boolean isMatchCase() {
        return matchCase;
    }

    public void setMatchCase(boolean matchCase) {
        this.matchCase = matchCase;
    }

    public boolean isWholeWord() {
        return wholeWord;
    }

    public void setWholeWord(boolean wholeWord) {
        this.wholeWord = wholeWord;
    }

    public boolean isAsRegex() {
        return asRegex;
    }

    public void setAsRegex(boolean asRegex) {
        this.asRegex = asRegex;
    }

    public boolean isLinks() {
        return links;
    }

    public void setLinks(boolean links) {
        this.links = links;
    }

    public boolean isAbstracts() {
        return abstracts;
    }

    public void setAbstracts(boolean abstracts) {
        this.abstracts = abstracts;
    }

    public boolean isCategories() {
        return categories;
    }

    public void setCategories(boolean categories) {
        this.categories = categories;
    }

    public boolean isInfoboxProperties() {
        return infoboxProperties;
    }

    public void setInfoboxProperties(boolean infoboxProperties) {
        this.infoboxProperties = infoboxProperties;
    }

    public boolean isEverything() {
        return everything;
    }

    public void setEverything(boolean everything) {
        this.everything = everything;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchOptionsDto)) return false;

        SearchOptionsDto that = (SearchOptionsDto) o;

        if (abstracts != that.abstracts) return false;
        if (asRegex != that.asRegex) return false;
        if (categories != that.categories) return false;
        if (everything != that.everything) return false;
        if (infoboxProperties != that.infoboxProperties) return false;
        if (links != that.links) return false;
        if (matchCase != that.matchCase) return false;
        if (wholeWord != that.wholeWord) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (matchCase ? 1 : 0);
        result = 31 * result + (wholeWord ? 1 : 0);
        result = 31 * result + (asRegex ? 1 : 0);
        result = 31 * result + (links ? 1 : 0);
        result = 31 * result + (abstracts ? 1 : 0);
        result = 31 * result + (categories ? 1 : 0);
        result = 31 * result + (infoboxProperties ? 1 : 0);
        result = 31 * result + (everything ? 1 : 0);
        return result;
    }
}
