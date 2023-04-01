package entity.onethree.urlpostdata.findpeople.body;

import com.alibaba.fastjson2.annotation.JSONField;

public class Body {
    @JSONField(name = "__type",ordinal = 0)
    private String __type;
    @JSONField(name = "IndexedPageItemView",ordinal = 1)
    private IndexedPageItemView indexedPageItemView;
    @JSONField(name = "QueryString",ordinal = 2)
    private Object queryString;
    @JSONField(name = "ParentFolderId",ordinal = 3)
    private ParentFolderId parentFolderId;
    @JSONField(name = "PersonaShape",ordinal = 4)
    private PersonaShape personaShape;
    @JSONField(name = "ShouldResolveOneOffEmailAddress",ordinal = 5)
    private Boolean shouldResolveOneOffEmailAddress;
    @JSONField(name = "SearchPeopleSuggestionIndex",ordinal = 6)
    private Boolean searchPeopleSuggestionIndex;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public IndexedPageItemView getIndexedPageItemView() {
        return indexedPageItemView;
    }

    public void setIndexedPageItemView(IndexedPageItemView indexedPageItemView) {
        this.indexedPageItemView = indexedPageItemView;
    }

    public Object getQueryString() {
        return queryString;
    }

    public void setQueryString(Object queryString) {
        this.queryString = queryString;
    }

    public ParentFolderId getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(ParentFolderId parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public PersonaShape getPersonaShape() {
        return personaShape;
    }

    public void setPersonaShape(PersonaShape personaShape) {
        this.personaShape = personaShape;
    }

    public Boolean getShouldResolveOneOffEmailAddress() {
        return shouldResolveOneOffEmailAddress;
    }

    public void setShouldResolveOneOffEmailAddress(Boolean shouldResolveOneOffEmailAddress) {
        this.shouldResolveOneOffEmailAddress = shouldResolveOneOffEmailAddress;
    }

    public Boolean getSearchPeopleSuggestionIndex() {
        return searchPeopleSuggestionIndex;
    }

    public void setSearchPeopleSuggestionIndex(Boolean searchPeopleSuggestionIndex) {
        this.searchPeopleSuggestionIndex = searchPeopleSuggestionIndex;
    }
}
