package entity.onesix.urlpostdata.getpersona;

import com.alibaba.fastjson2.annotation.JSONField;
import entity.onesix.urlpostdata.findpeople.header.Header;

public class Urlpostdata {
    @JSONField(name = "__type",ordinal = 0)
    private String __type;
    @JSONField(name = "Header",ordinal = 1)
    private entity.onesix.urlpostdata.findpeople.header.Header header;
    @JSONField(name = "Body",ordinal = 2)
    private Body body;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
