package entity.urlpostdata.findpeople.body;

public class IndexedPageItemView {
    private String __type;
    private String BasePoint;
    private int Offset;
    private int MaxEntriesReturned;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getBasePoint() {
        return BasePoint;
    }

    public void setBasePoint(String basePoint) {
        BasePoint = basePoint;
    }

    public int getOffset() {
        return Offset;
    }

    public void setOffset(int offset) {
        Offset = offset;
    }

    public int getMaxEntriesReturned() {
        return MaxEntriesReturned;
    }

    public void setMaxEntriesReturned(int maxEntriesReturned) {
        MaxEntriesReturned = maxEntriesReturned;
    }
}
