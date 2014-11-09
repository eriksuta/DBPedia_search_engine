package com.eriksuta.data.handler;

import com.eriksuta.data.ParserImpl;
import org.openrdf.model.Statement;

/**
 *  @author shood
 * */
public class SimpleSubjectHandler extends BasicRdfHandler{

    private String lastSubject;
    private StringBuilder sb;

    @Override
    public void handleStatement(Statement statement){
        String[] subject = statement.getSubject().stringValue().split("/");
        String subjectValue = subject[subject.length-1];

        String objectValue =  statement.getObject().stringValue();

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
