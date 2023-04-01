package scan;

import burp.*;
import com.alibaba.fastjson2.JSON;
import entity.findpeople.FindPeople;
import entity.getpersona.GetPersona;
import factory.ExChangeFactory;
import model.ApiTreeModel;
import model.ApiListTree;
import strategy.ExchangeStrategy;
import ui.ApiTreeTable;
import ui.MainUI;
import utils.HttpUtil;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * outlook信息收集类
 */
public class InfoScanner{
    //burp http协议相关变量
    private IHttpRequestResponse messageInfo;
    private IRequestInfo iRequestInfo;
    private IResponseInfo iResponseInfo;
    private IHttpService iHttpService;
    private String responseBody;
    private ApiListTree apiListTree;
    private List<ApiTreeModel> subApiTableData;
    private LinkedHashMap<String,String> headerMap;
    private String emailAddress;
    private String scannerId;
    private String scannerAction;
    private String baseUrl;
    private ExchangeStrategy exchangeStrategy;

    public InfoScanner(IHttpRequestResponse messageInfo) {
        this.messageInfo = messageInfo;
        this.iRequestInfo = BurpExtender.getHelpers().analyzeRequest(messageInfo);
        this.iResponseInfo = BurpExtender.getHelpers().analyzeResponse(messageInfo.getResponse());
        this.iHttpService = messageInfo.getHttpService();
        this.apiListTree = new ApiListTree(MainUI.apiTreeTableModel);
        this.subApiTableData = new ArrayList<>();
        //解析header
        this.headerMap = HttpUtil.headerList2Map(this.iRequestInfo.getHeaders());
        //scannerId是有域名+actionName组成
        this.responseBody = BurpExtender.getResponseBody(messageInfo);
        this.scannerAction = this.headerMap.get("Action").trim();
        //解析邮件域名，用它做key，防止一个域名重复请求
        emailAddress = parseEmailDomain();
        if (emailAddress.isEmpty()){
            JOptionPane.showConfirmDialog(null,"错误的API接口，请检测X-Owa-Urlpostdata是否正确","OutLook",JOptionPane.WARNING_MESSAGE);
        }
        this.scannerId = emailAddress + "&" + this.scannerAction;
        init();
    }

    /**
     * ApiTreeTable列表展示基本信息构建
     */
    public void init(){
        //解析请求url
        this.baseUrl = String.valueOf(this.iRequestInfo.getUrl());
        //解析响应状态码
        String statusCode = String.valueOf(this.iResponseInfo.getStatusCode());
        //解析响应数据包长度
        List<String> headers = this.iResponseInfo.getHeaders();
        LinkedHashMap<String, String> stringStringLinkedHashMap = HttpUtil.headerList2Map(headers);
        //当前时间
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String nowDate = ft.format(date);
        //写入主节点到UI面板
        ApiTreeModel mainApiTableData = new ApiTreeModel(Boolean.valueOf(false), String.valueOf(ApiTreeTable.parentCount++),this.baseUrl,statusCode,stringStringLinkedHashMap.get("Content-Length"),nowDate,messageInfo,apiListTree);
        this.apiListTree.setMainApiData(mainApiTableData);
    }

    /**
     * 自动扫描
     * @param version   exchange的版本
     * @param action    进行扫描的动作
     * @param thread    使用多少线程
     */
    public void scan(String version,String action, int thread){
        exchangeStrategy = ExChangeFactory.getFactApplyStrategy(version);
        exchangeStrategy.init(headerMap,responseBody,apiListTree,subApiTableData,messageInfo);
        exchangeStrategy.scan(this.scannerId,this.baseUrl,action,thread);
    }

    public void stop(){
        exchangeStrategy.stop();
    }


    public String parseEmailDomain(){
        try {
            FindPeople findPeople = JSON.parseObject(this.responseBody, FindPeople.class);
            GetPersona getPersona = JSON.parseObject(this.responseBody, GetPersona.class);
            String emailAddress = "";
            if (findPeople.getBody().getResultSet().size() > 0){
                emailAddress = findPeople.getBody().getResultSet().get(0).getEmailAddress().getEmailAddress().split("@")[1];
            } else{
                emailAddress = getPersona.getBody().getPersona().getEmailAddress().getEmailAddress().split("@")[1];
            }
            return emailAddress;
        }catch (Exception e){
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                BurpExtender.getStderr().println(stackTraceElement);
            }
            return "";
        }
    }
}
