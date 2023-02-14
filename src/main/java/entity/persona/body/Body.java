package entity.persona.body;

public class Body {
    private String __type;
    private Persona Persona;

    private String ResponseCode;
    private String ResponseClass;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public entity.persona.body.Persona getPersona() {
        return Persona;
    }

    public void setPersona(entity.persona.body.Persona persona) {
        Persona = persona;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getResponseClass() {
        return ResponseClass;
    }

    public void setResponseClass(String responseClass) {
        ResponseClass = responseClass;
    }
}
