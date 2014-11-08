package com.eriksuta.data.handler;

import com.eriksuta.data.ParserImpl;
import org.openrdf.model.Statement;
import org.openrdf.rio.helpers.RDFHandlerBase;

/**
 *  @author shood
 * */
public class CategoryLabelResultHandler extends RDFHandlerBase {
        
    private int numOfStatements = 0;
    private int numberOfCategories = 0;
    private String lastSubject;
    private StringBuilder sb;

    @Override
    public void handleStatement(Statement statement){
        statement.getObject();

        String[] subject = statement.getSubject().stringValue().split("/");
        String subjectValue = subject[subject.length-1];

        String[] object =  statement.getObject().stringValue().split(":");
        String objectValue = object[object.length-1];

        if(sb == null){
            sb = new StringBuilder();
        }

        if(lastSubject != null && lastSubject.equals(subjectValue)){
            sb.append("\t").append(objectValue);
        } else {
            sb.append("\n").append(subjectValue).append("\t").append(objectValue);
            lastSubject = subjectValue;
            numberOfCategories++;
        }

        ParserImpl.getParserInstance().writeToFile(sb.toString());
        sb = null;

        numOfStatements++;
    }

    public int getNumberOfStatements(){
        return numOfStatements;
    }

    public int getNumberOfCategories(){
        return numberOfCategories;
    }
}