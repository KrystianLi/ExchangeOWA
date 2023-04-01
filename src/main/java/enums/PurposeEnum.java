package enums;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum PurposeEnum {
    //ExchangeOWA Version
    Exchange2013_FindPeople("Exchange2013", "FindPeople","scan.Exchange2013"),
    Exchange2013_GetPersona("Exchange2013", "GetPersona","scan.Exchange2013"),
    Exchange2018_FindPeople("Exchange2018", "FindPeople","scan.Exchange2018"),
    Exchange2018_GetPersona("Exchange2018", "GetPersona","scan.Exchange2018"),
    ;

    private String version;
    private String action;
    private String className;

    PurposeEnum(String version,String action, String className) {
        this.version = version;
        this.action = action;
        this.className = className;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    /**
     * 返回Exchange Version版本
     * @return
     */
    public static Set<String> getExchangeVersion(){
        Set<String> lists = new HashSet<String>();
        for (PurposeEnum purposeEnum : values()) {
            lists.add(purposeEnum.getVersion());
        }
        return lists;
    }

    /**
     * 根据Exchange Version返回所有的Action
     * @param exchangeVersion Exchange版本
     * @return
     */
    public static List<String> getExchangeAction(String exchangeVersion){
        List<String> lists = new ArrayList<>();
        for (PurposeEnum purposeEnum : values()){
            if (purposeEnum.getVersion().equals(exchangeVersion) && !purposeEnum.getVersion().isEmpty()) {
                lists.add(purposeEnum.getAction());
            }
        }
        return lists;
    }

    /**
     * 根据不同的exchange version返回一个对象
     * @param exchangeVersion
     * @return
     */
    public static PurposeEnum getEnumObjByExpName(String exchangeVersion) {
        for (PurposeEnum purposeEnum : values()) {
            if (purposeEnum.getVersion().equals(exchangeVersion)) {
                return purposeEnum;
            }
        }
        return null;
    }
}
