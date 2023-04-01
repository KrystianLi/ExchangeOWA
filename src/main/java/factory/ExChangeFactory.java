package factory;


import enums.PurposeEnum;
import strategy.ExchangeStrategy;

public class ExChangeFactory {
    //使用策略工厂获取具体策略实现
    public static ExchangeStrategy getFactApplyStrategy(String expName) {
        try {
            return (ExchangeStrategy) Class.forName(PurposeEnum.getEnumObjByExpName(expName).getClassName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
