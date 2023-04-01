package entity.findpeople;

import entity.findpeople.body.Body;
import entity.findpeople.header.Header;

public class FindPeople {
    private Header Header;
    private Body Body;

    public entity.findpeople.header.Header getHeader() {
        return Header;
    }

    public void setHeader(entity.findpeople.header.Header header) {
        Header = header;
    }

    public entity.findpeople.body.Body getBody() {
        return Body;
    }

    public void setBody(entity.findpeople.body.Body body) {
        Body = body;
    }
}
