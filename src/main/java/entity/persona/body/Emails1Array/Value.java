package entity.persona.body.Emails1Array;

public class Value {
    private String Name;
    private String EmailAddress;
    private String RoutingType;
    private String MailboxType;
    private int RelevanceScore;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getRoutingType() {
        return RoutingType;
    }

    public void setRoutingType(String routingType) {
        RoutingType = routingType;
    }

    public String getMailboxType() {
        return MailboxType;
    }

    public void setMailboxType(String mailboxType) {
        MailboxType = mailboxType;
    }

    public int getRelevanceScore() {
        return RelevanceScore;
    }

    public void setRelevanceScore(int relevanceScore) {
        RelevanceScore = relevanceScore;
    }
}
