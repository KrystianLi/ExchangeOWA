package entity.urlpostdata.getpersona;

import entity.common.Id;

public class Body {
    private String __type;
    private Id PersonaId;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public Id getPersonaId() {
        return PersonaId;
    }

    public void setPersonaId(Id personaId) {
        PersonaId = personaId;
    }
}
