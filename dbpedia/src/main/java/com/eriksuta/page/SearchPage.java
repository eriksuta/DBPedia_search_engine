package com.eriksuta.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.validation.validator.StringValidator;
import com.eriksuta.page.component.VisibleEnableBehavior;

/**
 *  @author shood
 * */
public class SearchPage extends WebPage {

    private static final String ID_FEEDBACK = "feedback";
    private static final String ID_SEARCH_TEXT = "searchText";
    private static final String ID_SEARCH_BUTTON = "searchButton";
    private static final String ID_SEARCH_OPTIONS_LINK = "searchOptionsLink";
    private static final String ID_SEARCH_OPTIONS_PANEL = "searchOptions";
    private static final String ID_SEARCH_RESULT = "searchResult";

    private String searchText;
    private boolean searchOptionsVisible = false;

	public SearchPage(final PageParameters parameters) {
		super(parameters);

        initLayout();
    }

    private void initLayout(){
        FeedbackPanel feedback = new FeedbackPanel(ID_FEEDBACK);
        feedback.setOutputMarkupId(true);
        add(feedback);

        TextField searchField = new TextField<String>(ID_SEARCH_TEXT, new PropertyModel<String>(this, "searchText"));
        searchField.add(StringValidator.minimumLength(3));
        add(searchField);

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
        add(searchButton);

        AjaxLink searchOptionsLink = new AjaxLink(ID_SEARCH_OPTIONS_LINK) {

            @Override
            public void onClick(AjaxRequestTarget target) {
                showSearchOptionsPerformed(target);
            }
        };

        searchOptionsLink.add(new VisibleEnableBehavior(){

            @Override
            public boolean isVisible() {
                return !searchOptionsVisible;
            }
        });
        add(searchOptionsLink);

        WebMarkupContainer searchOptionsPanel = new WebMarkupContainer(ID_SEARCH_OPTIONS_PANEL);
        searchOptionsPanel.setOutputMarkupId(true);
        searchOptionsPanel.setOutputMarkupPlaceholderTag(true);
        add(searchOptionsPanel);

        WebMarkupContainer searchResultPanel = new WebMarkupContainer(ID_SEARCH_RESULT);
        searchResultPanel.setOutputMarkupId(true);
        searchResultPanel.setOutputMarkupPlaceholderTag(true);
        add(searchResultPanel);
    }

    private FeedbackPanel getFeedbackPanel(){
        return (FeedbackPanel) get(ID_FEEDBACK);
    }

    private void showSearchOptionsPerformed(AjaxRequestTarget target){
        //TODO
    }

    private void searchPerformed(AjaxRequestTarget target){
        //TODO
    }
}
