package scan;

import burp.BurpExtender;
import burp.IHttpRequestResponse;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import entity.findpeople.FindPeople;
import entity.findpeople.body.ResultSet;
import entity.getpersona.GetPersona;
import entity.onethree.urlpostdata.findpeople.Urlpostdata;
import model.ApiListTree;
import model.ApiTreeModel;
import strategy.ExchangeStrategy;
import ui.ApiTreeTable;
import ui.MainUI;
import utils.HttpUtil;
import utils.MyExecutor;

import javax.swing.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class Exchange2013 extends ExchangeStrategy {
    private final int MaxEntriesReturned = 100;
    private List<FindPeople> findPeopleList;
    private List<GetPersona> getPersonaList;

    private LinkedHashMap<String,String> headerMap;
    private String responseBody;
    private ApiListTree apiListTree;
    private List<ApiTreeModel> subApiTableData;
    private IHttpRequestResponse messageInfo;
    private String scannerId;
    private String baseUrl;
    private int thread;
    private List<ThreadPoolExecutor> threadPoolExecutorList = new ArrayList<>();

    public Exchange2013(){
        findPeopleList = Collections.synchronizedList(new ArrayList());
        getPersonaList = Collections.synchronizedList(new ArrayList());
    }

    public Exchange2013(LinkedHashMap<String,String> headerMap, String responseBody, ApiListTree apiListTree,List<ApiTreeModel> subApiTableData,IHttpRequestResponse messageInfo){
        this.findPeopleList = Collections.synchronizedList(new ArrayList());
        this.getPersonaList = Collections.synchronizedList(new ArrayList());
        this.headerMap = headerMap;
        this.responseBody = responseBody;
        this.apiListTree = apiListTree;
        this.subApiTableData = subApiTableData;
        this.messageInfo = messageInfo;
    }
    @Override
    public void init(LinkedHashMap<String,String> headerMap, String responseBody, ApiListTree apiListTree,List<ApiTreeModel> subApiTableData,IHttpRequestResponse messageInfo){
        this.findPeopleList = Collections.synchronizedList(new ArrayList());
        this.getPersonaList = Collections.synchronizedList(new ArrayList());
        this.headerMap = headerMap;
        this.responseBody = responseBody;
        this.apiListTree = apiListTree;
        this.subApiTableData = subApiTableData;
        this.messageInfo = messageInfo;
    }


    @Override
    public void scan(String scannerId,String baseUrl,String action, int thread){
        this.scannerId = scannerId;
        this.baseUrl = baseUrl;
        this.thread = thread;
        switch (action){
            case "GetPersona":
                getPersona();
                break;
            case "FindPeople":
                findPeople();
                break;
        }
    }
    @Override
    public void findPeople(){
        try {
            BurpExtender.main.getResultTextArea().setText("findPeople Scanning……\n");
            String postdata = URLDecoder.decode(headerMap.get("X-Owa-Urlpostdata"),"UTF-8");
            Urlpostdata urlpostdata = JSON.parseObject(postdata, Urlpostdata.class);
            if (null == urlpostdata.getBody().getParentFolderId() || !urlpostdata.getBody().getParentFolderId().get__type().equals("TargetFolderId:#Exchange") ){
                JOptionPane.showConfirmDialog(null,"错误的API接口，请检测X-Owa-Urlpostdata是否正确","OutLook",JOptionPane.WARNING_MESSAGE);
                return;
            }

            BurpExtender.AllUser.put(this.scannerId,this.findPeopleList);

            //获取总请求数量
            JSONObject jsonObject = new JSONObject(JSONObject.parseObject(responseBody));
            long totalNumberOfPeopleInView = jsonObject.getJSONObject("Body").getIntValue("TotalNumberOfPeopleInView");
            //解析总共有多少子节点，写入ui里面，由于请求需要时间，先写入静态内容避免面板卡死
            int count = (int) (totalNumberOfPeopleInView / 100);
            LinkedHashMap<Integer,List<String>>  headerLists = new LinkedHashMap<>();
            for (int i = 0,offset = 0; i <= count; i++,offset+=MaxEntriesReturned) {
                ApiTreeModel tempApiTableData = new ApiTreeModel(Boolean.valueOf(true),String.valueOf(ApiTreeTable.parentCount-1)+"."+i,this.baseUrl,"","","",null,null);
                subApiTableData.add(tempApiTableData);
                urlpostdata.getBody().getIndexedPageItemView().setOffset(offset);
                urlpostdata.getBody().getIndexedPageItemView().setMaxEntriesReturned(MaxEntriesReturned);
                String s = JSON.toJSONString(urlpostdata, JSONWriter.Feature.WriteNulls);
                String encode = URLEncoder.encode(s, "UTF-8");
                String encodeStr = encode.replaceAll("\\+", "%20");
                headerMap.put("X-Owa-Urlpostdata",encodeStr);
                List<String> headerList = HttpUtil.headerMap2List(headerMap);
                headerLists.put(i,headerList);
            }
            apiListTree.setSubApiData(subApiTableData);
            MainUI.apiTreeTableModel.add(apiListTree);
            //填充子节点的的详细信息
            //此处要用线程去执行，因为不应该在Swing事件分派线程中发出HTTP请求
            ThreadPoolExecutor threadPoolExecutor = MyExecutor.threadPoolExecutor(thread, thread + 2, 60, (int) totalNumberOfPeopleInView);
            threadPoolExecutorList.add(threadPoolExecutor);

            for (Map.Entry<Integer, List<String>> headerList:headerLists.entrySet()) {
                CompletableFuture.runAsync(()->{
                    IHttpRequestResponse iHttpRequestResponse = HttpUtil.sendHttp2Header(headerList.getValue(),messageInfo);
                    String tempResponseBody = BurpExtender.getResponseBody(iHttpRequestResponse);
                    //取出当前请求对应的子节点，填充数据包
                    ApiTreeModel apiTreeModel = subApiTableData.get(headerList.getKey());
                    MainUI.apiTreeTableModel.setApiTreeMode(apiTreeModel,iHttpRequestResponse);
                    FindPeople findPeople = JSON.parseObject(tempResponseBody, FindPeople.class);
                    findPeopleList.add(findPeople);
                    BurpExtender.main.getResultTextArea().append("Number of requests: "+headerList.getKey()+1+"/"+count+1+"\n");
                },threadPoolExecutor);
            }
            //线程池不在接收任务，待所有任务执行完毕关闭线程池
            threadPoolExecutor.shutdown();
        }catch (Exception e){
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                BurpExtender.getStderr().println(stackTraceElement);
            }
        }

    }
    @Override
    public void getPersona(){
        try{
            BurpExtender.main.getResultTextArea().append("getPersona Scanning……\n");
            //判断是否满足前置条件
            List<FindPeople> findPeople = BurpExtender.AllUser.get(this.scannerId.replaceAll(BurpExtender.GetPersona,BurpExtender.FindPeople));
            if (findPeople == null) {
                JOptionPane.showConfirmDialog(null,"请先获取All User数据，执行Action：FindPeople","OutLook",JOptionPane.WARNING_MESSAGE);
                return;
            }

            BurpExtender.Persona.put(this.scannerId,this.getPersonaList);
            //获取头部信息
            String urlPostData = this.headerMap.get("X-Owa-Urlpostdata");
            entity.onesix.urlpostdata.getpersona.Urlpostdata urlpostdata = JSON.parseObject(URLDecoder.decode(urlPostData,"UTF-8"), entity.onesix.urlpostdata.getpersona.Urlpostdata.class);
            //解析总共有多少子节点，写入ui里面，由于请求需要时间，先写入静态内容避免面板卡死
            int total = 0;
            LinkedHashMap<Integer,List<String>>  headerLists = new LinkedHashMap<>();
            for (FindPeople findPeopleItem : findPeople){
                for (ResultSet resultSet : findPeopleItem.getBody().getResultSet()) {
                    ApiTreeModel subApiTableData = new ApiTreeModel(Boolean.valueOf(true),String.valueOf(ApiTreeTable.parentCount-1)+"."+total,this.baseUrl,"","","",null,null);
                    this.subApiTableData.add(subApiTableData);
                    //解析personID，与2016区别在于有个\需要去掉
                    String id = resultSet.getPersonaId().getId().replace("\\","");
                    urlpostdata.getBody().getPersonaId().setId(id);
                    String s = JSON.toJSONString(urlpostdata);
                    String encode = URLEncoder.encode(s, "UTF-8");
                    String encodeStr = encode.replaceAll("\\+", "%20");
                    this.headerMap.put("X-Owa-Urlpostdata",encodeStr);
                    List<String> headerList = HttpUtil.headerMap2List(this.headerMap);
                    headerLists.put(total,headerList);
                    total++;
                }
            }

            this.apiListTree.setSubApiData(subApiTableData);
            MainUI.apiTreeTableModel.add(apiListTree);

            //此处要用线程去执行，因为不应该在Swing事件分派线程中发出HTTP请求
            int count = total;
            ThreadPoolExecutor threadPoolExecutor = MyExecutor.threadPoolExecutor(thread, thread + 2, 60, count);
            threadPoolExecutorList.add(threadPoolExecutor);
            for (Map.Entry<Integer, List<String>> headerList : headerLists.entrySet()) {
                CompletableFuture.runAsync(()->{
                    //参数构造完毕，开始发起请求
                    IHttpRequestResponse iHttpRequestResponse = HttpUtil.sendHttp2Header(headerList.getValue(),this.messageInfo);
                    String responseBody = BurpExtender.getResponseBody(iHttpRequestResponse);
                    //取出当前请求对应的子节点,设置对应属性
                    ApiTreeModel apiTreeModel = this.subApiTableData.get(headerList.getKey());
                    MainUI.apiTreeTableModel.setApiTreeMode(apiTreeModel,iHttpRequestResponse);
                    GetPersona getPersona = JSON.parseObject(responseBody, GetPersona.class);
                    getPersonaList.add(getPersona);
                    BurpExtender.main.getResultTextArea().append("Number of requests: "+headerList.getKey()+"/"+count +"\n");
                },threadPoolExecutor);
            }
            //线程池不在接收任务，待所有任务执行完毕关闭线程池
            threadPoolExecutor.shutdown();
        }catch (Exception e){
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                BurpExtender.main.getLogTextArea().append(stackTraceElement+"\n");
            }
        }
    }

    @Override
    public void stop(){
        for (ThreadPoolExecutor threadPoolExecutor : threadPoolExecutorList) {
            if (threadPoolExecutor != null){
                threadPoolExecutor.shutdownNow();
            }
        }
    }

}
