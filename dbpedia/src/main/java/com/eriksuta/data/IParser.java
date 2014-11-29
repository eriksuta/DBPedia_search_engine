package com.eriksuta.data;

/**
 *  @author shood
 *
 * */
public interface IParser {

    /**
     *  This method is responsible for parsing entire dump of Slovak DB-Pedia
     * */
    public void parseSlovakDBPedia();

    /**
     *  This method is responsible for parsing entire dump of Czech DB-Pedia
     * */
    public void parseCzechDBPedia();

}