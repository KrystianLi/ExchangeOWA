package entity.onesix.urlpostdata.findpeople.header;

import com.alibaba.fastjson2.annotation.JSONField;
import entity.common.Id;

public class TimeZoneContext {
    @JSONField(name = "__type",ordinal = 0)
    private String __type;
    @JSONField(name = "TimeZoneDefinition",ordinal = 1)
    private Id timeZoneDefinition;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public Id getTimeZoneDefinition() {
        return timeZoneDefinition;
    }

    public void setTimeZoneDefinition(Id timeZoneDefinition) {
        this.timeZoneDefinition = timeZoneDefinition;
    }
}
