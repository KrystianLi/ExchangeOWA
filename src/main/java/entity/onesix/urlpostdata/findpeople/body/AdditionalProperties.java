package entity.onesix.urlpostdata.findpeople.body;

import com.alibaba.fastjson2.annotation.JSONField;

public class AdditionalProperties {
    @JSONField(name = "__type",ordinal = 0)
    private String __type;
    @JSONField(name = "FieldURI",ordinal = 1)
    private String fieldURI;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getFieldURI() {
        return fieldURI;
    }

    public void setFieldURI(String fieldURI) {
        this.fieldURI = fieldURI;
    }
}
