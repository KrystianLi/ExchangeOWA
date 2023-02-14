package entity.urlpostdata.getpersona;
import entity.urlpostdata.findpeople.header.Header;
public class Urlpostdata {
    private String __type;
    private Header Header;
    private Body Body;

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

    public entity.urlpostdata.getpersona.Body getBody() {
        return Body;
    }

    public void setBody(entity.urlpostdata.getpersona.Body body) {
        Body = body;
    }
}
