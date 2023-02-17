package burp;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import entity.alluser.AllUser;
import entity.alluser.body.ResultSet;
import entity.persona.Persona;
import entity.urlpostdata.findpeople.Urlpostdata;
import model.ApiTreeModel;
import model.ApiListTree;
import ui.ApiTreeTable;
import ui.ExtensionTab;

import javax.swing.*;
import java.net.URLDecoder;
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
    //常量定义
    private final int MaxEntriesReturned = 100;
    private ApiListTree apiListTree;
    private List<ApiTreeModel> subApiTableData;
    private LinkedHashMap<String,String> headerMap;
    private List<AllUser> allUserList;
    private List<Persona> personaList;
    private String scannerId;
    private String parentScannerId;
    private String scannerAction;
    private String baseUrl;

    public InfoScanner(IHttpRequestResponse messageInfo) {
        this.messageInfo = messageInfo;
        this.iRequestInfo = BurpExtender.getHelpers().analyzeRequest(messageInfo);
        this.iResponseInfo = BurpExtender.getHelpers().analyzeResponse(messageInfo.getResponse());
        this.iHttpService = messageInfo.getHttpService();
        this.apiListTree = new ApiListTree(ExtensionTab.apiTreeTableModel);
        this.allUserList = new ArrayList<>();
        this.personaList = new ArrayList<>();
        this.subApiTableData = new ArrayList<>();
        //解析header
        this.headerMap = headerList2Map(this.iRequestInfo.getHeaders());
        //scannerId是有域名+actionName组成
        this.responseBody = BurpExtender.getResponseBody(messageInfo);
        this.scannerAction = this.headerMap.get("Action").trim();
        //解析邮件域名，用它做key，防止一个域名重复请求
        String emailAddress = parseEmailDomain();
        if (emailAddress.isEmpty()){
            JOptionPane.showConfirmDialog(null,"错误的API接口，请检测X-Owa-Urlpostdata是否正确","OutLook",JOptionPane.WARNING_MESSAGE);
        }
        this.scannerId = emailAddress + "&" + this.scannerAction;
        this.parentScannerId = emailAddress + "&" + ExtensionTab.FindPeople;
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
        LinkedHashMap<String, String> stringStringLinkedHashMap = headerList2Map(headers);
        //当前时间
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String nowDate = ft.format(date);
        //写入主节点到UI面板
        ApiTreeModel mainApiTableData = new ApiTreeModel(Boolean.valueOf(false), String.valueOf(ApiTreeTable.parentCount++),this.baseUrl,statusCode,stringStringLinkedHashMap.get("Content-Length"),nowDate,messageInfo,apiListTree);
        this.apiListTree.setMainApiData(mainApiTableData);
    }
    public void scanFindPeople(){
        try {
            BurpExtender.getStdout().println("All User Scanning");
            //TODO 判断前置条件是否满足,后续补充不满足自动构造正确请求
            String postdata = URLDecoder.decode(headerMap.get("X-Owa-Urlpostdata"),"UTF-8");
            Urlpostdata urlpostdata = JSON.parseObject(postdata, Urlpostdata.class);
            if (null == urlpostdata.getBody().getParentFolderId() || !urlpostdata.getBody().getParentFolderId().get__type().equals("TargetFolderId:#Exchange") ){
                JOptionPane.showConfirmDialog(null,"错误的API接口，请检测X-Owa-Urlpostdata是否正确","OutLook",JOptionPane.WARNING_MESSAGE);
                return;
            }
            //防止扫描数据重复
            for (Map.Entry<String, List<AllUser>> entry: ExtensionTab.AllUser.entrySet()){
                if (entry.getKey().equals(this.scannerId))
                    JOptionPane.showConfirmDialog(null,"重复扫描，请重新加载插件","OutLook",JOptionPane.WARNING_MESSAGE);
                return;
            }
            ExtensionTab.AllUser.put(this.scannerId,this.allUserList);
            parseFindPeople();
        }catch (Exception e){
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                BurpExtender.getStderr().println(stackTraceElement);
            }
        }
    }
    public void scanPersona(){
        try {
            BurpExtender.getStdout().println("Person Scanning");
            //判断是否满足前置条件
            List<AllUser> allUsers = ExtensionTab.AllUser.get(this.parentScannerId);
            if (allUsers == null) {
                JOptionPane.showConfirmDialog(null,"请先获取All User数据","OutLook",JOptionPane.WARNING_MESSAGE);
                return;
            }
            //防止扫描数据重复
            for (Map.Entry<String, List<Persona>> entry: ExtensionTab.Persona.entrySet()){
                if (entry.getKey().equals(this.scannerId))
                    JOptionPane.showConfirmDialog(null,"重复扫描，请重新加载插件","OutLook",JOptionPane.WARNING_MESSAGE);
                return;
            }
            ExtensionTab.Persona.put(this.scannerId,this.personaList);
            parsePersona();
        }catch (Exception e){
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                BurpExtender.getStderr().println(stackTraceElement);
            }
        }
    }
    /**
     * 解析FindPeople api接口，获取所有用户信息
     */
    public void parseFindPeople(){
        try {
            String postdata = URLDecoder.decode(headerMap.get("X-Owa-Urlpostdata"),"UTF-8");
            Urlpostdata urlpostdata = JSON.parseObject(postdata, Urlpostdata.class);
            //获取总请求数量
            JSONObject jsonObject = new JSONObject(JSONObject.parseObject(this.responseBody));
            long totalNumberOfPeopleInView = jsonObject.getJSONObject("Body").getIntValue("TotalNumberOfPeopleInView");
            //解析总共有多少子节点，写入ui里面，由于请求需要时间，先写入静态内容避免面板卡死
            int count = (int) (totalNumberOfPeopleInView / 100);
            for (int i = 0; i <= count; i++) {
                ApiTreeModel subApiTableData = new ApiTreeModel(Boolean.valueOf(true),String.valueOf(ApiTreeTable.parentCount-1)+"."+i,this.baseUrl,"","","",null,null);
                this.subApiTableData.add(subApiTableData);
            }
            this.apiListTree.setSubApiData(subApiTableData);
            ExtensionTab.apiTreeTableModel.add(apiListTree);
            //填充子节点的的详细信息
            for (int offset = 0,index = 0;totalNumberOfPeopleInView>offset;offset+=100,index++){
                urlpostdata.getBody().getIndexedPageItemView().setOffset(offset);
                urlpostdata.getBody().getIndexedPageItemView().setMaxEntriesReturned(MaxEntriesReturned);
                String s = JSON.toJSONString(urlpostdata);
                this.headerMap.put("X-Owa-Urlpostdata",s);
                List<String> headerList = headerMap2List(this.headerMap);
                //获取请求的结果
                IHttpRequestResponse iHttpRequestResponse = sendHttp2Header(headerList);
                String responseBody = BurpExtender.getResponseBody(iHttpRequestResponse);
                //取出当前请求对应的子节点
                ApiTreeModel apiTreeModel = this.subApiTableData.get(index);
                setApiTreeMode(apiTreeModel,iHttpRequestResponse);
                AllUser allUser = JSON.parseObject(responseBody, AllUser.class);
                this.allUserList.add(allUser);
                BurpExtender.getStdout().println("Number of requests: "+index+1+"/"+count+1);
            }
        } catch (Exception e) {
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                BurpExtender.getStderr().println(stackTraceElement);
            }
        }
    }

    /**
     * 解析GetPersona请求
     */
    public void parsePersona(){
        try {
            //获取头部信息
            String urlPostData = this.headerMap.get("X-Owa-Urlpostdata");
            entity.urlpostdata.getpersona.Urlpostdata urlpostdata = JSON.parseObject(URLDecoder.decode(urlPostData,"UTF-8"), entity.urlpostdata.getpersona.Urlpostdata.class);
            List<AllUser> allUsers = ExtensionTab.AllUser.get(this.parentScannerId);
            //解析总共有多少子节点，写入ui里面，由于请求需要时间，先写入静态内容避免面板卡死
            int totalNumberOfPersonaInView = 0;
            int total = 0;
            for (AllUser allUser : allUsers){
                int size = allUser.getBody().getResultSet().size();
                for (int i = 0; i < size; i++) {
                    total++;
                    ApiTreeModel subApiTableData = new ApiTreeModel(Boolean.valueOf(true),String.valueOf(ApiTreeTable.parentCount-1)+"."+total,this.baseUrl,"","","",null,null);
                    this.subApiTableData.add(subApiTableData);
                }
            }
            totalNumberOfPersonaInView = total;
            this.apiListTree.setSubApiData(subApiTableData);
            ExtensionTab.apiTreeTableModel.add(apiListTree);
            //获取所有的FindPeople数据包里面的id
            //开始解析所有的子节点
            total = 0;
            for (AllUser allUser : allUsers) {
                for (ResultSet resultSet : allUser.getBody().getResultSet()) {
                    String id = resultSet.getPersonaId().getId();
                    urlpostdata.getBody().getPersonaId().setId(id);
                    String s = JSON.toJSONString(urlpostdata);
                    this.headerMap.put("X-Owa-Urlpostdata",s);
                    List<String> headerList = headerMap2List(this.headerMap);
                    //参数构造完毕，开始发起请求
                    IHttpRequestResponse iHttpRequestResponse = sendHttp2Header(headerList);
                    String responseBody = BurpExtender.getResponseBody(iHttpRequestResponse);
                    //取出当前请求对应的子节点,设置对应属性
                    ApiTreeModel apiTreeModel = this.subApiTableData.get(total);
                    setApiTreeMode(apiTreeModel,iHttpRequestResponse);
                    Persona persona = JSON.parseObject(responseBody, Persona.class);
                    personaList.add(persona);
                    total++;
                    BurpExtender.getStdout().println("Number of requests: "+total+"/"+totalNumberOfPersonaInView);
                }
            }
        }catch (Exception e){
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                BurpExtender.getStderr().println(stackTraceElement);
            }
        }


    }
    /**
     * 填充ApiTreeMode的属性
     * @param apiTreeModel
     * @param iHttpRequestResponse
     */
    public void setApiTreeMode(ApiTreeModel apiTreeModel,IHttpRequestResponse iHttpRequestResponse){
        //当前时间
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String nowDate = ft.format(date);
        //数据包长度
        List<String> headers = BurpExtender.getHelpers().analyzeResponse(iHttpRequestResponse.getResponse()).getHeaders();
        LinkedHashMap<String, String> headerMap = headerList2Map(headers);
        //设置apiTreeModel属性
        apiTreeModel.setScanTime(nowDate);
        apiTreeModel.setLength(headerMap.get("Content-Length"));
        apiTreeModel.setStatusCode(String.valueOf(BurpExtender.getHelpers().analyzeResponse(iHttpRequestResponse.getResponse()).getStatusCode()));
        apiTreeModel.setRequestResponse(iHttpRequestResponse);
    }

    public LinkedHashMap<String,String> headerList2Map(List<String> headers){
        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        headers.forEach(s -> {
            String[] split = s.split(":", 2);
            if (split[0].equals(s)){
                header.put("error",split[0]);
            }else {
                header.put(split[0],split[1]);
            }
        });
        return header;
    }
    public List<String> headerMap2List(LinkedHashMap<String,String> headerMap){
        List<String> headerList = new ArrayList<>();
        headerMap.forEach((key, value) -> {
            if (key.equals("error")){
                headerList.add(value);
            }else {
                headerList.add(key+":"+value);
            }
        });
        return headerList;
    }
    public IHttpRequestResponse sendHttp2Header(List<String> header){
        IHttpRequestResponse iHttpRequestResponse = null;
        try {
            int bodyOffset = iRequestInfo.getBodyOffset();
            String request = new String(messageInfo.getRequest(), "UTF-8");
            byte[] requestBody = request.substring(bodyOffset).getBytes("UTF-8");
            //重新build我们的请求数据
            byte[] newRequest = BurpExtender.getHelpers().buildHttpMessage(header, requestBody);
            iHttpRequestResponse = BurpExtender.getCallbacks().makeHttpRequest(this.iHttpService, newRequest);
        }catch (Exception e){
            BurpExtender.getStderr().println(e.getStackTrace());
        }
        return iHttpRequestResponse;
    }

    public String parseEmailDomain(){
        try {
            AllUser allUser = JSON.parseObject(this.responseBody, AllUser.class);
            Persona persona = JSON.parseObject(this.responseBody, Persona.class);
            String emailAddress = "";
            if (allUser.getBody().getResultSet().size() > 0){
                emailAddress = allUser.getBody().getResultSet().get(0).getEmailAddress().getEmailAddress().split("@")[1];
            } else{
                emailAddress = persona.getBody().getPersona().getEmailAddress().getEmailAddress().split("@")[1];
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
