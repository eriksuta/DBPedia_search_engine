package com.eriksuta.data.handler;

import com.eriksuta.data.ParserImpl;
import org.openrdf.model.Statement;

/**
 *  @author shood
 * */
public class SkosCategoriesHandler extends BasicRdfHandler{

    private String lastSubject;
    private StringBuilder sb;

    @Override
    public void handleStatement(Statement statement){
        String[] subject = statement.getSubject().stringValue().split("/");
        String subjectValue = subject[subject.length-1];

        if(subjectValue.startsWith("Kategória") || subjectValue.startsWith("Category")){
            String[] subjects = subjectValue.split(":");
            subjectValue = subjects[subjects.length-1];
        }

        String predicate = statement.getPredicate().stringValue();
        String objectValue;

        if(predicate.endsWith("prefLabel")){
            objectValue = statement.getObject().stringValue();
        } else if (predicate.endsWith("broader")){
            String objects[] = statement.getObject().stringValue().split(":");
            objectValue = objects[objects.length-1];
        } else {
            numOfStatements++;
            return;
        }

        if(sb == null){
            sb = new StringBuilder();
        }

        if(lastSubject != null && lastSubject.equals(subjectValue)){
            sb.append("\t").append(objectValue);
        } else {
            sb.append("\n").append(subjectValue).append(":").append(objectValue);
            lastSubject = subjectValue;
            numberOfStatementsAfter++;
        }

        ParserImpl.getParserInstance().writeToFile(sb.toString());
        sb = null;

        numOfStatements++;
    }
}
