package entity.alluser.body;

import entity.common.Id;

import java.util.ArrayList;
import java.util.List;

public class ResultSet {
    private String __type;
    private Id PersonaId;
    private String PersonaTypeString;
    private String CreationTimeString;
    private String DisplayName;
    private String DisplayNameFirstLast;
    private String DisplayNameLastFirst;
    private String FileAs;
    private EmailAddress EmailAddress;
    private List<EmailAddress> EmailAddresses = new ArrayList<EmailAddress>();
    private String RelevanceScore;
    private List<AttributionsArray> AttributionsArray = new ArrayList<AttributionsArray>();
    private String ADObjectId;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public Id getPersonaId() {
        return PersonaId;
    }

    public void setPersonaId(Id personaId) {
        PersonaId = personaId;
    }

    public String getPersonaTypeString() {
        return PersonaTypeString;
    }

    public void setPersonaTypeString(String personaTypeString) {
        PersonaTypeString = personaTypeString;
    }

    public String getCreationTimeString() {
        return CreationTimeString;
    }

    public void setCreationTimeString(String creationTimeString) {
        CreationTimeString = creationTimeString;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getDisplayNameFirstLast() {
        return DisplayNameFirstLast;
    }

    public void setDisplayNameFirstLast(String displayNameFirstLast) {
        DisplayNameFirstLast = displayNameFirstLast;
    }

    public String getDisplayNameLastFirst() {
        return DisplayNameLastFirst;
    }

    public void setDisplayNameLastFirst(String displayNameLastFirst) {
        DisplayNameLastFirst = displayNameLastFirst;
    }

    public String getFileAs() {
        return FileAs;
    }

    public void setFileAs(String fileAs) {
        FileAs = fileAs;
    }

    public entity.alluser.body.EmailAddress getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(entity.alluser.body.EmailAddress emailAddress) {
        EmailAddress = emailAddress;
    }

    public List<entity.alluser.body.EmailAddress> getEmailAddresses() {
        return EmailAddresses;
    }

    public void setEmailAddresses(List<entity.alluser.body.EmailAddress> emailAddresses) {
        EmailAddresses = emailAddresses;
    }

    public String getRelevanceScore() {
        return RelevanceScore;
    }

    public void setRelevanceScore(String relevanceScore) {
        RelevanceScore = relevanceScore;
    }

    public List<entity.alluser.body.AttributionsArray> getAttributionsArray() {
        return AttributionsArray;
    }

    public void setAttributionsArray(List<entity.alluser.body.AttributionsArray> attributionsArray) {
        AttributionsArray = attributionsArray;
    }

    public String getADObjectId() {
        return ADObjectId;
    }

    public void setADObjectId(String ADObjectId) {
        this.ADObjectId = ADObjectId;
    }
}
