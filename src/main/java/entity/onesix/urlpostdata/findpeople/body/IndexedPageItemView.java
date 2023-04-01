package entity.onesix.urlpostdata.findpeople.body;

import com.alibaba.fastjson2.annotation.JSONField;

public class IndexedPageItemView {
    @JSONField(name = "__type",ordinal = 0)
    private String __type;
    @JSONField(name = "BasePoint",ordinal = 1)
    private String basePoint;
    @JSONField(name = "Offset",ordinal = 2)
    private int offset;
    @JSONField(name = "MaxEntriesReturned",ordinal = 3)
    private int maxEntriesReturned;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getBasePoint() {
        return basePoint;
    }

    public void setBasePoint(String basePoint) {
        this.basePoint = basePoint;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getMaxEntriesReturned() {
        return maxEntriesReturned;
    }

    public void setMaxEntriesReturned(int maxEntriesReturned) {
        this.maxEntriesReturned = maxEntriesReturned;
    }
}
