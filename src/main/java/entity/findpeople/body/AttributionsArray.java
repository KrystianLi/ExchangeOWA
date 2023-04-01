package entity.findpeople.body;

import entity.common.Id;

public class AttributionsArray {
    private String Id;
    private Id SourceId;
    private String DisplayName;
    private Boolean IsWritable;
    private Boolean IsQuickContact;
    private Boolean IsHidden;
    private Object FolderId;
    private Object FolderName;
    private Boolean IsGuest;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public entity.common.Id getSourceId() {
        return SourceId;
    }

    public void setSourceId(entity.common.Id sourceId) {
        SourceId = sourceId;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public Boolean getWritable() {
        return IsWritable;
    }

    public void setWritable(Boolean writable) {
        IsWritable = writable;
    }

    public Boolean getQuickContact() {
        return IsQuickContact;
    }

    public void setQuickContact(Boolean quickContact) {
        IsQuickContact = quickContact;
    }

    public Boolean getHidden() {
        return IsHidden;
    }

    public void setHidden(Boolean hidden) {
        IsHidden = hidden;
    }

    public Object getFolderId() {
        return FolderId;
    }

    public void setFolderId(Object folderId) {
        FolderId = folderId;
    }

    public Object getFolderName() {
        return FolderName;
    }

    public void setFolderName(Object folderName) {
        FolderName = folderName;
    }

    public Boolean getGuest() {
        return IsGuest;
    }

    public void setGuest(Boolean guest) {
        IsGuest = guest;
    }
}
