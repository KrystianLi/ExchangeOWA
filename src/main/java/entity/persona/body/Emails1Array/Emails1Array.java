package entity.persona.body.Emails1Array;

import java.util.ArrayList;
import java.util.List;

public class Emails1Array {
    private Value Value;
    private List<String> Attributions = new ArrayList<String>();

    public entity.persona.body.Emails1Array.Value getValue() {
        return Value;
    }

    public void setValue(entity.persona.body.Emails1Array.Value value) {
        Value = value;
    }

    public List<String> getAttributions() {
        return Attributions;
    }

    public void setAttributions(List<String> attributions) {
        Attributions = attributions;
    }
}
