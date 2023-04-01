package strategy;

import burp.IHttpRequestResponse;
import model.ApiListTree;
import model.ApiTreeModel;

import java.util.LinkedHashMap;
import java.util.List;

public abstract class ExchangeStrategy {

    /**
     *  初始化操作
     * @param headerMap http头
     * @param responseBody  响应包，用作解析数据大小
     * @param apiListTree   主节点树
     * @param subApiTableData   子节点数
     * @param messageInfo   burp http上下文
     */
    public void init(LinkedHashMap<String,String> headerMap, String responseBody, ApiListTree apiListTree, List<ApiTreeModel> subApiTableData, IHttpRequestResponse messageInfo){
        throw new UnsupportedOperationException();
    }

    /**
     * 开始扫描
     * @param scannerId 扫描任务id
     * @param baseUrl   目标url
     * @param action    进行扫描类型
     * @param thread    线程
     */
    public void scan(String scannerId,String baseUrl,String action, int thread){
        throw new UnsupportedOperationException();
    }

    /**
     * 停止扫描
     */
    public void stop(){
        throw new UnsupportedOperationException();
    }

    /**
     * Exchange Action: FindPeople
     */
    public void findPeople(){
        throw new UnsupportedOperationException();
    }

    /**
     * Exchange Action: GetPerson
     */
    public void getPersona(){
        throw new UnsupportedOperationException();
    }
}
