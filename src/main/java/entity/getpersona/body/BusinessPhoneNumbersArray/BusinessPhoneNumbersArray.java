package entity.getpersona.body.BusinessPhoneNumbersArray;

import java.util.ArrayList;
import java.util.List;

public class BusinessPhoneNumbersArray {
    private Value Value;
    private List<String> Attributions = new ArrayList<String>();

    public entity.getpersona.body.BusinessPhoneNumbersArray.Value getValue() {
        return Value;
    }

    public void setValue(entity.getpersona.body.BusinessPhoneNumbersArray.Value value) {
        Value = value;
    }

    public List<String> getAttributions() {
        return Attributions;
    }

    public void setAttributions(List<String> attributions) {
        Attributions = attributions;
    }
}
