package model;

public class AllUserModel {
    private String id;
    private String emailAddress;
    private String displayName;

    public AllUserModel(String id, String emailAddress, String displayName) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
