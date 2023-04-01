package entity.common;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class NamesArray {
    @JSONField(name = "Value",ordinal = 0)
    private String value;
    @JSONField(name = "Attributions",ordinal = 1)
    private List<String> attributions = new ArrayList<String>();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getAttributions() {
        return attributions;
    }

    public void setAttributions(List<String> attributions) {
        this.attributions = attributions;
    }
}
