package entity.persona.body.BusinessAddressesArray;

import java.util.List;

public class BusinessAddressesArray {
    private Value Value;
    private List<String> Attributions;

    public entity.persona.body.BusinessAddressesArray.Value getValue() {
        return Value;
    }

    public void setValue(entity.persona.body.BusinessAddressesArray.Value value) {
        Value = value;
    }

    public List<String> getAttributions() {
        return Attributions;
    }

    public void setAttributions(List<String> attributions) {
        Attributions = attributions;
    }
}
