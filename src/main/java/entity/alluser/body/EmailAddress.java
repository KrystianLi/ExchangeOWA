package entity.alluser.body;

public class EmailAddress {
    private String Name;
    private String EmailAddress;
    private String RoutingType;
    private String MailboxType;

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
}
