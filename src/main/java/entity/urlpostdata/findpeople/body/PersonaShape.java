package entity.urlpostdata.findpeople.body;

import java.util.ArrayList;
import java.util.List;

public class PersonaShape {
    private String __type;
    private String BaseShape;
    private List<AdditionalProperties> AdditionalProperties = new ArrayList<AdditionalProperties>();

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getBaseShape() {
        return BaseShape;
    }

    public void setBaseShape(String baseShape) {
        BaseShape = baseShape;
    }

    public List<entity.urlpostdata.findpeople.body.AdditionalProperties> getAdditionalProperties() {
        return AdditionalProperties;
    }

    public void setAdditionalProperties(List<entity.urlpostdata.findpeople.body.AdditionalProperties> additionalProperties) {
        AdditionalProperties = additionalProperties;
    }
}
