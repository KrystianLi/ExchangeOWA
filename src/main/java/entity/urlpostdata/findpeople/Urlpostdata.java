package entity.urlpostdata.findpeople;

import entity.urlpostdata.findpeople.header.Header;

public class Urlpostdata {
    private String __type;
    private Header Header;
    private entity.urlpostdata.findpeople.body.Body Body;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public entity.urlpostdata.findpeople.header.Header getHeader() {
        return Header;
    }

    public void setHeader(entity.urlpostdata.findpeople.header.Header header) {
        Header = header;
    }

    public entity.urlpostdata.findpeople.body.Body getBody() {
        return Body;
    }

    public void setBody(entity.urlpostdata.findpeople.body.Body body) {
        Body = body;
    }
}
