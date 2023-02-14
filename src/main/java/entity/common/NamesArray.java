package entity.common;

import java.util.ArrayList;
import java.util.List;

public class NamesArray {
    private String Value;
    private List<String> Attributions = new ArrayList<String>();

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public List<String> getAttributions() {
        return Attributions;
    }

    public void setAttributions(List<String> attributions) {
        Attributions = attributions;
    }
}
