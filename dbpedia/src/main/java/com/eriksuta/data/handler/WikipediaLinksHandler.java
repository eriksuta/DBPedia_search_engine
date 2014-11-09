package com.eriksuta.data.handler;

import com.eriksuta.data.ParserImpl;
import org.openrdf.model.Statement;

/**
 *  @author shood
 * */
public class WikipediaLinksHandler extends BasicRdfHandler{

    private String lastSubject;
    private StringBuilder sb;

    @Override
    public void handleStatement(Statement statement){
        String[] subject = statement.getSubject().stringValue().split("/");
        String subjectValue = subject[subject.length-1];

        String predicate = statement.getPredicate().stringValue();
        String objectValue;

        if(predicate.endsWith("isPrimaryTopicOf")){
            objectValue = statement.getObject().stringValue();
        } else if (predicate.endsWith("language")){
            objectValue = statement.getObject().stringValue();
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
