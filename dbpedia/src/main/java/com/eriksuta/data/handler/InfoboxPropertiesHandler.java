package com.eriksuta.data.handler;

import com.eriksuta.data.ParserImpl;
import org.openrdf.model.Statement;

/**
 *  @author shood
 * */
public class InfoboxPropertiesHandler extends BasicRdfHandler{

    private String lastSubject;
    private StringBuilder sb;

    @Override
    public void handleStatement(Statement statement){
        statement.getObject();

        String[] subject = statement.getSubject().stringValue().split("/");
        String subjectValue = subject[subject.length-1];

        String[] predicate = statement.getPredicate().stringValue().split("/");
        String predicateValue = predicate[predicate.length-1];

        String objectValue =  statement.getObject().stringValue();
        if(objectValue.contains("\n")){
            objectValue = objectValue.replaceAll("\n", " ");
        }

        if(sb == null){
            sb = new StringBuilder();
        }

        if(lastSubject != null && lastSubject.equals(subjectValue)){
            sb.append("\t");
            sb.append(predicateValue);
            sb.append(":");
            sb.append(objectValue);
        } else {
            sb.append("\n");
            sb.append(subjectValue);
            sb.append("->");
            sb.append("\t");
            sb.append(predicateValue);
            sb.append(":");
            sb.append(objectValue);
            lastSubject = subjectValue;
            numberOfStatementsAfter++;
        }

        ParserImpl.getParserInstance().writeToFile(sb.toString());
        sb = null;

        numOfStatements++;
    }
}
