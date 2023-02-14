package entity.persona.body.BusinessPhoneNumbersArray;

import java.util.ArrayList;
import java.util.List;

public class BusinessPhoneNumbersArray {
    private Value Value;
    private List<String> Attributions = new ArrayList<String>();

    public entity.persona.body.BusinessPhoneNumbersArray.Value getValue() {
        return Value;
    }

    public void setValue(entity.persona.body.BusinessPhoneNumbersArray.Value value) {
        Value = value;
    }

    public List<String> getAttributions() {
        return Attributions;
    }

    public void setAttributions(List<String> attributions) {
        Attributions = attributions;
    }
}
