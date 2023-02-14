package entity.urlpostdata.findpeople.body;

public class Body {
    private IndexedPageItemView IndexedPageItemView;
    private Object QueryString;
    private ParentFolderId ParentFolderId;
    private PersonaShape PersonaShape;
    private Boolean ShouldResolveOneOffEmailAddress;
    private Boolean SearchPeopleSuggestionIndex;

    public entity.urlpostdata.findpeople.body.IndexedPageItemView getIndexedPageItemView() {
        return IndexedPageItemView;
    }

    public void setIndexedPageItemView(entity.urlpostdata.findpeople.body.IndexedPageItemView indexedPageItemView) {
        IndexedPageItemView = indexedPageItemView;
    }

    public Object getQueryString() {
        return QueryString;
    }

    public void setQueryString(Object queryString) {
        QueryString = queryString;
    }

    public entity.urlpostdata.findpeople.body.ParentFolderId getParentFolderId() {
        return ParentFolderId;
    }

    public void setParentFolderId(entity.urlpostdata.findpeople.body.ParentFolderId parentFolderId) {
        ParentFolderId = parentFolderId;
    }

    public entity.urlpostdata.findpeople.body.PersonaShape getPersonaShape() {
        return PersonaShape;
    }

    public void setPersonaShape(entity.urlpostdata.findpeople.body.PersonaShape personaShape) {
        PersonaShape = personaShape;
    }

    public Boolean getShouldResolveOneOffEmailAddress() {
        return ShouldResolveOneOffEmailAddress;
    }

    public void setShouldResolveOneOffEmailAddress(Boolean shouldResolveOneOffEmailAddress) {
        ShouldResolveOneOffEmailAddress = shouldResolveOneOffEmailAddress;
    }

    public Boolean getSearchPeopleSuggestionIndex() {
        return SearchPeopleSuggestionIndex;
    }

    public void setSearchPeopleSuggestionIndex(Boolean searchPeopleSuggestionIndex) {
        SearchPeopleSuggestionIndex = searchPeopleSuggestionIndex;
    }
}
