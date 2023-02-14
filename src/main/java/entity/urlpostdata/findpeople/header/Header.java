package entity.urlpostdata.findpeople.header;

public class Header {
    private String __type;
    private String RequestServerVersion;
    private TimeZoneContext TimeZoneContext;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getRequestServerVersion() {
        return RequestServerVersion;
    }

    public void setRequestServerVersion(String requestServerVersion) {
        RequestServerVersion = requestServerVersion;
    }

    public entity.urlpostdata.findpeople.header.TimeZoneContext getTimeZoneContext() {
        return TimeZoneContext;
    }

    public void setTimeZoneContext(entity.urlpostdata.findpeople.header.TimeZoneContext timeZoneContext) {
        TimeZoneContext = timeZoneContext;
    }
}
