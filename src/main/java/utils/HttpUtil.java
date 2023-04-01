package utils;

import burp.BurpExtender;
import burp.IHttpRequestResponse;
import burp.IHttpService;
import burp.IRequestInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class HttpUtil {
    public static List<String> headerMap2List(LinkedHashMap<String,String> headerMap){
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

    public static IHttpRequestResponse sendHttp2Header(List<String> header, IHttpRequestResponse messageInfo){
        IRequestInfo iRequestInfo = BurpExtender.getHelpers().analyzeRequest(messageInfo);
        IHttpService iHttpService = messageInfo.getHttpService();
        IHttpRequestResponse iHttpRequestResponse = null;
        try {
            int bodyOffset = iRequestInfo.getBodyOffset();
            String request = new String(messageInfo.getRequest(), "UTF-8");
            byte[] requestBody = request.substring(bodyOffset).getBytes("UTF-8");
            //重新build我们的请求数据
            byte[] newRequest = BurpExtender.getHelpers().buildHttpMessage(header, requestBody);
            iHttpRequestResponse = BurpExtender.getCallbacks().makeHttpRequest(iHttpService, newRequest);
        }catch (Exception e){
            BurpExtender.getStderr().println(e.getStackTrace());
        }
        return iHttpRequestResponse;
    }

    public static LinkedHashMap<String,String> headerList2Map(List<String> headers){
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
}
