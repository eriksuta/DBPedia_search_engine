package com.eriksuta.page.component.panel;

import com.eriksuta.data.search.InfoboxPropertyType;
import com.eriksuta.data.search.SearchResultType;
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

    private IModel<SearchResultType> model;

    public SearchResultClassicPanel(String id, final SearchResultType result){
        super(id);

        model = new LoadableModel<SearchResultType>(false) {

            @Override
            protected SearchResultType load() {
                return result != null ? result : new SearchResultType();
            }
        };

        initLayout();
    }

    public void updateModel(SearchResultType result){
        //TODO
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

        Label revisionUri = new Label(ID_REVISION_URI, new PropertyModel<String>(model, "revisionUri"));
        wrapper.add(revisionUri);

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
        wrapper.add(shortAbstracts);

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
        wrapper.add(longAbstracts);

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
        wrapper.add(articleCategories);

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
        wrapper.add(skosCategories);

        Label freebaseLinks = new Label(ID_LINK_FREEBASE, prepareLinksReadOnlyModel(LinkType.FREEBASE));
        wrapper.add(freebaseLinks);

        Label wikiLinks = new Label(ID_LINK_WIKI, prepareLinksReadOnlyModel(LinkType.WIKI));
        wrapper.add(wikiLinks);

        Label externalLinks = new Label(ID_LINK_EXT, prepareLinksReadOnlyModel(LinkType.EXTERNAL));
        wrapper.add(externalLinks);

        Label interLanguageLinks = new Label(ID_LINK_INTER_LANGUAGE, prepareLinksReadOnlyModel(LinkType.INTER_LANGUAGE));
        wrapper.add(interLanguageLinks);

        Label pageLinks = new Label(ID_LINK_PAGE, prepareLinksReadOnlyModel(LinkType.PAGE));
        wrapper.add(pageLinks);

        Label redirects = new Label(ID_REDIRECTS, prepareLinksReadOnlyModel(LinkType.REDIRECT));
        wrapper.add(redirects);

        Label redirectsTransitive = new Label(ID_REDIRECTS_TRANSITIVE, prepareLinksReadOnlyModel(LinkType.REDIRECT_TRANSITIVE));
        wrapper.add(redirectsTransitive);

        Label infoboxProperties = new Label(ID_INFOBOX, new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                StringBuilder sb = new StringBuilder();

                for(InfoboxPropertyType propertyType: model.getObject().getInfoboxProperties()){
                    sb.append(propertyType.getName());
                    sb.append("=");
                    sb.append(propertyType.getValue());
                    sb.append(", ");
                }

                return sb.toString();
            }
        });
        wrapper.add(infoboxProperties);
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
                    sb.append(s);
                    sb.append(", ");
                }

                return sb.toString();
            }
        };
    }

    public WebMarkupContainer getWrapper(){
        return (WebMarkupContainer) get(ID_WRAPPER);
    }
}
