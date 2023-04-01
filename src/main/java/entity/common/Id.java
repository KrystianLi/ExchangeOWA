package entity.common;

import com.alibaba.fastjson2.annotation.JSONField;

public class Id {
    @JSONField(name = "__type",ordinal = 0)
    private String __type;
    @JSONField(name = "Id",ordinal = 1)
    private String id;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
