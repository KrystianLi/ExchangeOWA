package entity.onesix.urlpostdata.findpeople.header;

import com.alibaba.fastjson2.annotation.JSONField;

public class Header {
    @JSONField(name = "__type",ordinal = 0)
    private String __type;
    @JSONField(name = "RequestServerVersion",ordinal = 1)
    private String requestServerVersion;
    @JSONField(name = "TimeZoneContext",ordinal = 2)
    private TimeZoneContext timeZoneContext;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getRequestServerVersion() {
        return requestServerVersion;
    }

    public void setRequestServerVersion(String requestServerVersion) {
        this.requestServerVersion = requestServerVersion;
    }

    public TimeZoneContext getTimeZoneContext() {
        return timeZoneContext;
    }

    public void setTimeZoneContext(TimeZoneContext timeZoneContext) {
        this.timeZoneContext = timeZoneContext;
    }
}
