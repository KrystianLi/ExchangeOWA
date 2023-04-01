package entity.onesix.urlpostdata.findpeople.body;

import com.alibaba.fastjson2.annotation.JSONField;
import entity.common.Id;

public class ParentFolderId {
    @JSONField(name = "__type",ordinal = 0)
    private String __type;
    @JSONField(name = "BaseFolderId",ordinal = 1)
    private Id baseFolderId;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public Id getBaseFolderId() {
        return baseFolderId;
    }

    public void setBaseFolderId(Id baseFolderId) {
        this.baseFolderId = baseFolderId;
    }
}
