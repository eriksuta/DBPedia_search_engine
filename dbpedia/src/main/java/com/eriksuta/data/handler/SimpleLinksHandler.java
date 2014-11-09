package com.eriksuta.data.handler;

import com.eriksuta.data.ParserImpl;
import org.openrdf.model.Statement;

/**
 *  @author shood
 * */
public class SimpleLinksHandler extends BasicRdfHandler {

    private String lastSubject;
    private StringBuilder sb;

    @Override
    public void handleStatement(Statement statement){
        String[] subject = statement.getSubject().stringValue().split("/");
        String subjectValue = subject[subject.length-1];

        String object =  statement.getObject().stringValue();

        if(sb == null){
            sb = new StringBuilder();
        }

        if(lastSubject != null && lastSubject.equals(subjectValue)){
            sb.append("\t").append(object);
        } else {
            sb.append("\n").append(subjectValue).append(":").append(object);
            lastSubject = subjectValue;
            numberOfStatementsAfter++;
        }

        ParserImpl.getParserInstance().writeToFile(sb.toString());
        sb = null;

        numOfStatements++;
    }

}
