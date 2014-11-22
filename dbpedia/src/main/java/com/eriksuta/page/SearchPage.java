package com.eriksuta.page;

import com.eriksuta.page.component.panel.SearchOptionsPanel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.validation.validator.StringValidator;
import com.eriksuta.page.component.behavior.VisibleEnableBehavior;

/**
 *  @author shood
 * */
public class SearchPage extends WebPage {

    private static final String ID_FEEDBACK = "feedback";
    private static final String ID_MAIN_FORM = "mainForm";
    private static final String ID_SEARCH_TEXT = "searchText";
    private static final String ID_SEARCH_BUTTON = "searchButton";
    private static final String ID_SEARCH_OPTIONS_LINK = "searchOptionsLink";
    private static final String ID_SEARCH_OPTIONS_TEXT = "searchOptionsText";
    private static final String ID_SEARCH_OPTIONS_PANEL = "searchOptions";
    private static final String ID_SEARCH_RESULT = "searchResult";

    private String searchText;
    private boolean searchOptionsVisible = false;

	public SearchPage(final PageParameters parameters) {
		super(parameters);

        initLayout();
    }

    private void initLayout(){
        Form form = new Form(ID_MAIN_FORM);
        form.setOutputMarkupId(true);
        add(form);

        FeedbackPanel feedback = new FeedbackPanel(ID_FEEDBACK);
        feedback.setOutputMarkupId(true);
        form.add(feedback);

        TextField searchField = new TextField<String>(ID_SEARCH_TEXT, new PropertyModel<String>(this, "searchText"));
        searchField.add(StringValidator.minimumLength(3));
        form.add(searchField);

        AjaxButton searchButton = new AjaxButton(ID_SEARCH_BUTTON) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                searchPerformed(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(getFeedbackPanel());
            }
        };
        form.add(searchButton);

        AjaxLink searchOptionsLink = new AjaxLink(ID_SEARCH_OPTIONS_LINK) {

            @Override
            public void onClick(AjaxRequestTarget target) {
                showSearchOptionsPerformed(target);
            }
        };
        searchOptionsLink.add(new Label(ID_SEARCH_OPTIONS_TEXT, new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                if(searchOptionsVisible){
                    return "Hide Search Options";
                } else {
                    return "Show Search Options";
                }
            }
        }));
        searchOptionsLink.setOutputMarkupId(true);
        form.add(searchOptionsLink);

        WebMarkupContainer searchOptionsPanel = new SearchOptionsPanel(ID_SEARCH_OPTIONS_PANEL);
        searchOptionsPanel.setOutputMarkupId(true);
        searchOptionsPanel.setOutputMarkupPlaceholderTag(true);
        searchOptionsPanel.add(new VisibleEnableBehavior() {

            @Override
            public boolean isVisible() {
                return searchOptionsVisible;
            }
        });
        form.add(searchOptionsPanel);

        WebMarkupContainer searchResultPanel = new WebMarkupContainer(ID_SEARCH_RESULT);
        searchResultPanel.setOutputMarkupId(true);
        searchResultPanel.setOutputMarkupPlaceholderTag(true);
        add(searchResultPanel);
    }

    private FeedbackPanel getFeedbackPanel(){
        return (FeedbackPanel) get(ID_MAIN_FORM + ":" + ID_FEEDBACK);
    }

    private SearchOptionsPanel getSearchOptionsContainer(){
        return (SearchOptionsPanel) get(ID_MAIN_FORM + ":" + ID_SEARCH_OPTIONS_PANEL);
    }

    private Component getSearchOptionsLink(){
        return get(ID_MAIN_FORM + ":" + ID_SEARCH_OPTIONS_LINK);
    }

    private void showSearchOptionsPerformed(AjaxRequestTarget target){
        searchOptionsVisible = !searchOptionsVisible;

        target.add(getSearchOptionsContainer(), getSearchOptionsLink());
    }

    private void searchPerformed(AjaxRequestTarget target){
        //TODO
    }
}
