package entity.urlpostdata.findpeople.header;

import entity.common.Id;

public class TimeZoneContext {
    private String __type;
    private Id TimeZoneDefinition;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public Id getTimeZoneDefinition() {
        return TimeZoneDefinition;
    }

    public void setTimeZoneDefinition(Id timeZoneDefinition) {
        TimeZoneDefinition = timeZoneDefinition;
    }
}
