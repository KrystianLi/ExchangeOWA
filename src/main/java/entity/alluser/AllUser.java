package entity.alluser;

import entity.alluser.body.Body;
import entity.alluser.header.Header;

public class AllUser {
    private Header Header;
    private Body Body;

    public entity.alluser.header.Header getHeader() {
        return Header;
    }

    public void setHeader(entity.alluser.header.Header header) {
        Header = header;
    }

    public entity.alluser.body.Body getBody() {
        return Body;
    }

    public void setBody(entity.alluser.body.Body body) {
        Body = body;
    }
}
