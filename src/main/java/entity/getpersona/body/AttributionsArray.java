package entity.getpersona.body;

import entity.common.Id;

public class AttributionsArray {
    private String Id;
    private Id SourceId;
    private String DisplayName;
    private Object IsWritable;
    private Object IsQuickContact;
    private Object IsHidden;
    private Object FolderId;
    private Object FolderName;
    private Object IsGuest;

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

    public Object getIsWritable() {
        return IsWritable;
    }

    public void setIsWritable(Object isWritable) {
        IsWritable = isWritable;
    }

    public Object getIsQuickContact() {
        return IsQuickContact;
    }

    public void setIsQuickContact(Object isQuickContact) {
        IsQuickContact = isQuickContact;
    }

    public Object getIsHidden() {
        return IsHidden;
    }

    public void setIsHidden(Object isHidden) {
        IsHidden = isHidden;
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

    public Object getIsGuest() {
        return IsGuest;
    }

    public void setIsGuest(Object isGuest) {
        IsGuest = isGuest;
    }
}
