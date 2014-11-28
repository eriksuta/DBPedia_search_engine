package com.eriksuta.page.component.panel;

import com.eriksuta.data.search.InfoboxPropertyType;
import com.eriksuta.data.search.SearchResultType;
import com.eriksuta.page.SearchOptions;
import com.eriksuta.page.component.behavior.VisibleEnableBehavior;
import com.eriksuta.page.component.model.LoadableModel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author shood
 * */
public class SearchResultClassicPanel extends Panel {

    private enum LinkType{
        FREEBASE,
        WIKI,
        INTER_LANGUAGE,
        PAGE,
        EXTERNAL,
        REDIRECT,
        REDIRECT_TRANSITIVE
    }

    private static final String ID_WRAPPER = "resultWrapper";
    private static final String ID_SEARCH_INFO = "searchInfo";
    private static final String ID_LABEL = "label";
    private static final String ID_PAGE_ID = "pageId";
    private static final String ID_PAGE_LENGTH = "pageLength";
    private static final String ID_OUT_DEGREE = "outDegree";
    private static final String ID_REVISION_ID = "revisionId";
    private static final String ID_REVISION_URI = "revisionUri";
    private static final String ID_ABSTRACT_SHORT = "shortAbstracts";
    private static final String ID_ABSTRACT_LONG = "longAbstracts";
    private static final String ID_CATEGORIES_ARTICLE = "articleCategories";
    private static final String ID_CATEGORIES_SKOS = "skosCategories";
    private static final String ID_LINK_FREEBASE = "freebaseLinks";
    private static final String ID_LINK_WIKI = "wikiLinks";
    private static final String ID_LINK_EXT = "externalLinks";
    private static final String ID_LINK_INTER_LANGUAGE = "interLanguageLinks";
    private static final String ID_LINK_PAGE = "pageLinks";
    private static final String ID_REDIRECTS = "redirects";
    private static final String ID_REDIRECTS_TRANSITIVE = "redirectsTransitive";
    private static final String ID_INFOBOX = "infoboxProperties";
    private static final String ID_CONTAINER_ABSTRACTS = "abstractsContainer";
    private static final String ID_CONTAINER_CATEGORIES = "categoriesContainer";
    private static final String ID_CONTAINER_LINKS = "linksContainer";
    private static final String ID_CONTAINER_INFOBOX = "infoboxContainer";

    private IModel<SearchResultType> model;
    private SearchOptions options;

    public SearchResultClassicPanel(String id, final SearchResultType result, SearchOptions options){
        super(id);

        this.options = options;
        model = new LoadableModel<SearchResultType>(false) {

            @Override
            protected SearchResultType load() {
                return result != null ? result : new SearchResultType();
            }
        };

        initLayout();
    }

    private void initLayout(){
        WebMarkupContainer wrapper = new WebMarkupContainer(ID_WRAPPER);
        wrapper.setOutputMarkupId(true);
        wrapper.setOutputMarkupPlaceholderTag(true);
        add(wrapper);

        Label searchInfo = new Label(ID_SEARCH_INFO, new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                StringBuilder sb = new StringBuilder();
                sb.append("Results for search term: '");
                sb.append(model.getObject().getQueryTerm());
                sb.append("'. Results found in ");
                sb.append(model.getObject().getSearchTime());
                sb.append(" ms");
                return sb.toString();
            }
        });
        wrapper.add(searchInfo);

        Label label = new Label(ID_LABEL, new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                StringBuilder sb = new StringBuilder();

                for(String s: model.getObject().getLabels()){
                    sb.append(s);
                    sb.append(", ");
                }

                return sb.toString();
            }
        });
        wrapper.add(label);

        Label pageId = new Label(ID_PAGE_ID, new PropertyModel<String>(model, "pageId"));
        wrapper.add(pageId);

        Label pageLength = new Label(ID_PAGE_LENGTH, new PropertyModel<String>(model, "pageLength"));
        wrapper.add(pageLength);

        Label outDegree = new Label(ID_OUT_DEGREE, new PropertyModel<String>(model, "outDegree"));
        wrapper.add(outDegree);

        Label revisionId = new Label(ID_REVISION_ID, new PropertyModel<String>(model, "revisionId"));
        wrapper.add(revisionId);

        Label revisionUri = new Label(ID_REVISION_URI, new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                StringBuilder sb = new StringBuilder();

                String uri = model.getObject().getRevisionUri();

                sb.append("<a href=\"");
                sb.append(uri);
                sb.append("\">");
                sb.append(uri);
                sb.append("</a>");

                return sb.toString();
            }
        });
        revisionUri.setEscapeModelStrings(false);
        wrapper.add(revisionUri);

        WebMarkupContainer abstractsContainer = new WebMarkupContainer(ID_CONTAINER_ABSTRACTS);
        abstractsContainer.setOutputMarkupId(true);
        abstractsContainer.setOutputMarkupPlaceholderTag(true);
        abstractsContainer.add(new VisibleEnableBehavior(){

            @Override
            public boolean isVisible() {
                return options != null && options.isAbstracts();
            }
        });
        wrapper.add(abstractsContainer);

        Label shortAbstracts = new Label(ID_ABSTRACT_SHORT, new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                StringBuilder sb = new StringBuilder();

                for(String s: model.getObject().getShortAbstracts()){
                    sb.append(s);
                    sb.append("\n");
                }

                return sb.toString();
            }
        });
        abstractsContainer.add(shortAbstracts);

        Label longAbstracts = new Label(ID_ABSTRACT_LONG, new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                StringBuilder sb = new StringBuilder();

                for(String s: model.getObject().getLongAbstracts()){
                    sb.append(s);
                    sb.append("\n");
                }

                return sb.toString();
            }
        });
        abstractsContainer.add(longAbstracts);

        WebMarkupContainer categoriesContainer = new WebMarkupContainer(ID_CONTAINER_CATEGORIES);
        categoriesContainer.setOutputMarkupId(true);
        categoriesContainer.setOutputMarkupPlaceholderTag(true);
        categoriesContainer.add(new VisibleEnableBehavior(){

            @Override
            public boolean isVisible() {
                return options != null && options.isCategories();
            }
        });
        wrapper.add(categoriesContainer);

        Label articleCategories = new Label(ID_CATEGORIES_ARTICLE, new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                StringBuilder sb = new StringBuilder();

                for(String s: model.getObject().getArticleCategories()){
                    sb.append(s);
                    sb.append(", ");
                }

                return sb.toString();
            }
        });
        categoriesContainer.add(articleCategories);

        Label skosCategories = new Label(ID_CATEGORIES_SKOS, new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                StringBuilder sb = new StringBuilder();

                for(String s: model.getObject().getSkosCategories()){
                    sb.append(s);
                    sb.append(", ");
                }

                return sb.toString();
            }
        });
        categoriesContainer.add(skosCategories);

        WebMarkupContainer linksContainer = new WebMarkupContainer(ID_CONTAINER_LINKS);
        linksContainer.setOutputMarkupId(true);
        linksContainer.setOutputMarkupPlaceholderTag(true);
        linksContainer.add(new VisibleEnableBehavior(){

            @Override
            public boolean isVisible() {
                return options != null && options.isLinks();
            }
        });
        wrapper.add(linksContainer);

        Label freebaseLinks = new Label(ID_LINK_FREEBASE, prepareLinksReadOnlyModel(LinkType.FREEBASE));
        freebaseLinks.setEscapeModelStrings(false);
        linksContainer.add(freebaseLinks);

        Label wikiLinks = new Label(ID_LINK_WIKI, prepareLinksReadOnlyModel(LinkType.WIKI));
        wikiLinks.setEscapeModelStrings(false);
        linksContainer.add(wikiLinks);

        Label externalLinks = new Label(ID_LINK_EXT, prepareLinksReadOnlyModel(LinkType.EXTERNAL));
        externalLinks.setEscapeModelStrings(false);
        linksContainer.add(externalLinks);

        Label interLanguageLinks = new Label(ID_LINK_INTER_LANGUAGE, prepareLinksReadOnlyModel(LinkType.INTER_LANGUAGE));
        interLanguageLinks.setEscapeModelStrings(false);
        linksContainer.add(interLanguageLinks);

        Label pageLinks = new Label(ID_LINK_PAGE, prepareLinksReadOnlyModel(LinkType.PAGE));
        pageLinks.setEscapeModelStrings(false);
        linksContainer.add(pageLinks);

        Label redirects = new Label(ID_REDIRECTS, prepareLinksReadOnlyModel(LinkType.REDIRECT));
        redirects.setEscapeModelStrings(false);
        linksContainer.add(redirects);

        Label redirectsTransitive = new Label(ID_REDIRECTS_TRANSITIVE, prepareLinksReadOnlyModel(LinkType.REDIRECT_TRANSITIVE));
        redirectsTransitive.setEscapeModelStrings(false);
        linksContainer.add(redirectsTransitive);

        WebMarkupContainer infoboxContainer = new WebMarkupContainer(ID_CONTAINER_INFOBOX);
        infoboxContainer.setOutputMarkupId(true);
        infoboxContainer.setOutputMarkupPlaceholderTag(true);
        infoboxContainer.add(new VisibleEnableBehavior(){

            @Override
            public boolean isVisible() {
                return options != null && options.isInfoboxProperties();
            }
        });
        wrapper.add(infoboxContainer);

        Label infoboxProperties = new Label(ID_INFOBOX, new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                StringBuilder sb = new StringBuilder();

                for(InfoboxPropertyType propertyType: model.getObject().getInfoboxProperties()){
                    sb.append(propertyType.getName());
                    sb.append("=");
                    sb.append(propertyType.getValue());
                    sb.append("<br>");
                }

                return sb.toString();
            }
        });
        infoboxProperties.setEscapeModelStrings(false);
        infoboxContainer.add(infoboxProperties);
    }

    private AbstractReadOnlyModel<String> prepareLinksReadOnlyModel(final LinkType type){
        return new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                StringBuilder sb = new StringBuilder();

                List<String> links = new ArrayList<String>();

                if(LinkType.FREEBASE.equals(type)){
                    links = model.getObject().getFreebaseLinks();
                } else if(LinkType.WIKI.equals(type)){
                    links = model.getObject().getWikipediaLinks();
                } else if(LinkType.EXTERNAL.equals(type)){
                    links = model.getObject().getExternalLinks();
                } else if (LinkType.PAGE.equals(type)){
                    links = model.getObject().getPageLinksSk();
                } else if (LinkType.INTER_LANGUAGE.equals(type)){
                    links = model.getObject().getInterLanguageLinks();
                } else if(LinkType.REDIRECT.equals(type)){
                    links = model.getObject().getRedirects();
                } else if(LinkType.REDIRECT_TRANSITIVE.equals(type)){
                    links = model.getObject().getRedirectsTransitive();
                }

                for(String s: links){
                    if(s.contains("\t")){
                        String[] separatedLinks = s.split("\t");

                        for(String sLink: separatedLinks){
                            sb.append("<a href=\"");
                            sb.append(sLink);
                            sb.append("\">");
                            sb.append(sLink);
                            sb.append("</a><br>");
                        }
                    } else {
                        sb.append("<a href=\"");
                        sb.append(s);
                        sb.append("\">");
                        sb.append(s);
                        sb.append("</a><br>");
                    }
                }

                return sb.toString();
            }
        };
    }
}
