package model;

public class FindPeopleModel {
    private String id;
    private String emailAddress;
    private String displayName;
    private String emailDomain;


    public FindPeopleModel(String id, String emailAddress, String displayName, String emailDomain) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.displayName = displayName;
        this.emailDomain = emailDomain;
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

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }
}
