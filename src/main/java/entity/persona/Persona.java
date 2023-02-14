package entity.persona;

import entity.alluser.header.Header;
import entity.persona.body.Body;

public class Persona {
    private Header Header;
    private Body Body;

    public entity.alluser.header.Header getHeader() {
        return Header;
    }

    public void setHeader(entity.alluser.header.Header header) {
        Header = header;
    }

    public entity.persona.body.Body getBody() {
        return Body;
    }

    public void setBody(entity.persona.body.Body body) {
        Body = body;
    }
}
