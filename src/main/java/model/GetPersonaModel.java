package model;

public class GetPersonaModel {
    public String id;
    public String displayName;
    public String emailAddress;
    public String number;
    public String officeLocations;
    public String title;
    public String department;

    public GetPersonaModel(String id, String displayName, String emailAddress, String number, String officeLocations, String title , String department) {
        this.id = id;
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.number = number;
        this.officeLocations = officeLocations;
        this.title = title;
        this.department = department;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOfficeLocations() {
        return officeLocations;
    }

    public void setOfficeLocations(String officeLocations) {
        this.officeLocations = officeLocations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
