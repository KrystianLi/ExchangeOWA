package entity.onesix.urlpostdata.getpersona;

import com.alibaba.fastjson2.annotation.JSONField;
import entity.common.Id;

public class Body {
    @JSONField(name = "__type",ordinal = 0)
    private String __type;
    @JSONField(name = "PersonaId",ordinal = 1)
    private Id personaId;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public Id getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Id personaId) {
        this.personaId = personaId;
    }
}
