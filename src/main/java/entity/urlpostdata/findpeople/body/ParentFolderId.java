package entity.urlpostdata.findpeople.body;

import entity.common.Id;

public class ParentFolderId {
    private String __type;
    private Id BaseFolderId;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public Id getBaseFolderId() {
        return BaseFolderId;
    }

    public void setBaseFolderId(Id baseFolderId) {
        BaseFolderId = baseFolderId;
    }
}
