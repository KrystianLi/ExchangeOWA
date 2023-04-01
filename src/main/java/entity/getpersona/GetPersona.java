package entity.getpersona;

import entity.findpeople.header.Header;
import entity.getpersona.body.Body;

public class GetPersona {
    private Header Header;
    private Body Body;

    public entity.findpeople.header.Header getHeader() {
        return Header;
    }

    public void setHeader(entity.findpeople.header.Header header) {
        Header = header;
    }

    public entity.getpersona.body.Body getBody() {
        return Body;
    }

    public void setBody(entity.getpersona.body.Body body) {
        Body = body;
    }
}
