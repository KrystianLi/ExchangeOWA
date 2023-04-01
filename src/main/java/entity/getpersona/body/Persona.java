package entity.getpersona.body;

import entity.common.NamesArray;
import entity.common.Id;
import entity.getpersona.body.BusinessAddressesArray.BusinessAddressesArray;
import entity.getpersona.body.BusinessPhoneNumbersArray.BusinessPhoneNumbersArray;
import entity.getpersona.body.Emails1Array.Emails1Array;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String __type;
    private Id PersonaId;
    private String PersonaTypeString;
    private String CreationTimeString;
    private String LastModifiedTimeString;
    private String DisplayName;
    private String DisplayNameFirstLast;
    private String DisplayNameLastFirst;
    private String FileAs;
    private String FileAsId;
    private String GivenName;
    private String Surname;
    private String Title;
    private String Department;
    private EmailAddress EmailAddress;
    private List<EmailAddress> EmailAddresses = new ArrayList<EmailAddress>();
    private String ImAddress;
    private String WorkCity;
    private String RelevanceScore;
    private List<entity.getpersona.body.AttributionsArray> AttributionsArray = new ArrayList<entity.getpersona.body.AttributionsArray>();
    private List<NamesArray> DisplayNamesArray = new ArrayList<NamesArray>();
    private List<NamesArray> GivenNamesArray = new ArrayList<NamesArray>();
    private List<NamesArray> SurnamesArray = new ArrayList<NamesArray>();
    private List<BusinessPhoneNumbersArray> BusinessPhoneNumbersArray = new ArrayList<BusinessPhoneNumbersArray>();
    private List<entity.getpersona.body.Emails1Array.Emails1Array> Emails1Array = new ArrayList<Emails1Array>();
    private List<NamesArray> OfficeLocationsArray = new ArrayList<NamesArray>();
    private List<NamesArray> ImAddressesArray = new ArrayList<NamesArray>();
    private List<BusinessAddressesArray> BusinessAddressesArray = new ArrayList<BusinessAddressesArray>();
    private List<NamesArray> TitlesArray = new ArrayList<NamesArray>();
    private List<NamesArray> DepartmentsArray = new ArrayList<NamesArray>();
    private String HasActiveDeals;
    private List<NamesArray> AttributedHasActiveDeals = new ArrayList<NamesArray>();
    private List<NamesArray> AttributedIsBusinessContact = new ArrayList<NamesArray>();
    private String LastContactedDate;
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

    public String getLastModifiedTimeString() {
        return LastModifiedTimeString;
    }

    public void setLastModifiedTimeString(String lastModifiedTimeString) {
        LastModifiedTimeString = lastModifiedTimeString;
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

    public String getFileAsId() {
        return FileAsId;
    }

    public void setFileAsId(String fileAsId) {
        FileAsId = fileAsId;
    }

    public String getGivenName() {
        return GivenName;
    }

    public void setGivenName(String givenName) {
        GivenName = givenName;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public entity.getpersona.body.EmailAddress getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(entity.getpersona.body.EmailAddress emailAddress) {
        EmailAddress = emailAddress;
    }

    public List<entity.getpersona.body.EmailAddress> getEmailAddresses() {
        return EmailAddresses;
    }

    public void setEmailAddresses(List<entity.getpersona.body.EmailAddress> emailAddresses) {
        EmailAddresses = emailAddresses;
    }

    public String getImAddress() {
        return ImAddress;
    }

    public void setImAddress(String imAddress) {
        ImAddress = imAddress;
    }

    public String getWorkCity() {
        return WorkCity;
    }

    public void setWorkCity(String workCity) {
        WorkCity = workCity;
    }

    public String getRelevanceScore() {
        return RelevanceScore;
    }

    public void setRelevanceScore(String relevanceScore) {
        RelevanceScore = relevanceScore;
    }

    public List<entity.getpersona.body.AttributionsArray> getAttributionsArray() {
        return AttributionsArray;
    }

    public void setAttributionsArray(List<entity.getpersona.body.AttributionsArray> attributionsArray) {
        AttributionsArray = attributionsArray;
    }

    public List<NamesArray> getDisplayNamesArray() {
        return DisplayNamesArray;
    }

    public void setDisplayNamesArray(List<NamesArray> displayNamesArray) {
        DisplayNamesArray = displayNamesArray;
    }

    public List<NamesArray> getGivenNamesArray() {
        return GivenNamesArray;
    }

    public void setGivenNamesArray(List<NamesArray> givenNamesArray) {
        GivenNamesArray = givenNamesArray;
    }

    public List<NamesArray> getSurnamesArray() {
        return SurnamesArray;
    }

    public void setSurnamesArray(List<NamesArray> surnamesArray) {
        SurnamesArray = surnamesArray;
    }

    public List<entity.getpersona.body.BusinessPhoneNumbersArray.BusinessPhoneNumbersArray> getBusinessPhoneNumbersArray() {
        return BusinessPhoneNumbersArray;
    }

    public void setBusinessPhoneNumbersArray(List<entity.getpersona.body.BusinessPhoneNumbersArray.BusinessPhoneNumbersArray> businessPhoneNumbersArray) {
        BusinessPhoneNumbersArray = businessPhoneNumbersArray;
    }

    public List<entity.getpersona.body.Emails1Array.Emails1Array> getEmails1Array() {
        return Emails1Array;
    }

    public void setEmails1Array(List<entity.getpersona.body.Emails1Array.Emails1Array> emails1Array) {
        Emails1Array = emails1Array;
    }

    public List<NamesArray> getOfficeLocationsArray() {
        return OfficeLocationsArray;
    }

    public void setOfficeLocationsArray(List<NamesArray> officeLocationsArray) {
        OfficeLocationsArray = officeLocationsArray;
    }

    public List<NamesArray> getImAddressesArray() {
        return ImAddressesArray;
    }

    public void setImAddressesArray(List<NamesArray> imAddressesArray) {
        ImAddressesArray = imAddressesArray;
    }

    public List<entity.getpersona.body.BusinessAddressesArray.BusinessAddressesArray> getBusinessAddressesArray() {
        return BusinessAddressesArray;
    }

    public void setBusinessAddressesArray(List<entity.getpersona.body.BusinessAddressesArray.BusinessAddressesArray> businessAddressesArray) {
        BusinessAddressesArray = businessAddressesArray;
    }

    public List<NamesArray> getTitlesArray() {
        return TitlesArray;
    }

    public void setTitlesArray(List<NamesArray> titlesArray) {
        TitlesArray = titlesArray;
    }

    public List<NamesArray> getDepartmentsArray() {
        return DepartmentsArray;
    }

    public void setDepartmentsArray(List<NamesArray> departmentsArray) {
        DepartmentsArray = departmentsArray;
    }

    public String getHasActiveDeals() {
        return HasActiveDeals;
    }

    public void setHasActiveDeals(String hasActiveDeals) {
        HasActiveDeals = hasActiveDeals;
    }

    public List<NamesArray> getAttributedHasActiveDeals() {
        return AttributedHasActiveDeals;
    }

    public void setAttributedHasActiveDeals(List<NamesArray> attributedHasActiveDeals) {
        AttributedHasActiveDeals = attributedHasActiveDeals;
    }

    public List<NamesArray> getAttributedIsBusinessContact() {
        return AttributedIsBusinessContact;
    }

    public void setAttributedIsBusinessContact(List<NamesArray> attributedIsBusinessContact) {
        AttributedIsBusinessContact = attributedIsBusinessContact;
    }

    public String getLastContactedDate() {
        return LastContactedDate;
    }

    public void setLastContactedDate(String lastContactedDate) {
        LastContactedDate = lastContactedDate;
    }

    public String getADObjectId() {
        return ADObjectId;
    }

    public void setADObjectId(String ADObjectId) {
        this.ADObjectId = ADObjectId;
    }
}
