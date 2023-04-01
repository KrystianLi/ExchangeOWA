package entity.onesix.urlpostdata.findpeople.body;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class PersonaShape {
    @JSONField(name = "__type",ordinal = 0)
    private String __type;
    @JSONField(name = "BaseShape",ordinal = 1)
    private String baseShape;
    @JSONField(name = "AdditionalProperties",ordinal = 2)
    private List<AdditionalProperties> additionalProperties = new ArrayList<AdditionalProperties>();

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getBaseShape() {
        return baseShape;
    }

    public void setBaseShape(String baseShape) {
        this.baseShape = baseShape;
    }

    public List<AdditionalProperties> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(List<AdditionalProperties> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
