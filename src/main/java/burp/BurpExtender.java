package burp;

import ui.ContextMenu;
import ui.MainUI;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BurpExtender implements IBurpExtender, ITab, IMessageEditorController{
    private static PrintWriter stdout;
    private static PrintWriter stderr;
    public static IHttpRequestResponse currentlyDisplayedItem;
    private static IExtensionHelpers helpers;
    private static IBurpExtenderCallbacks callbacks;
    public static IMessageEditor requestTextEditor;
    public static IMessageEditor responseTextEditor;
    public static MainUI main;
    //常量定义
    private String extensionName = "OWA";
    public static final String FindPeople = "FindPeople";
    public static final String GetPersona = "GetPersona";
    //TODO 静态变量存储请求响应结果，便于其他类使用数据，后续改为db文件存储
    public static Map<String, List<entity.findpeople.FindPeople>> AllUser = new HashMap<>();
    public static Map<String, List<entity.getpersona.GetPersona>> Persona = new HashMap<>();
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks iBurpExtenderCallbacks) {
        //初始化变量
        this.callbacks = iBurpExtenderCallbacks;
        this.helpers = this.callbacks.getHelpers();
        this.requestTextEditor = iBurpExtenderCallbacks.createMessageEditor(BurpExtender.this, false);
        this.responseTextEditor = iBurpExtenderCallbacks.createMessageEditor(BurpExtender.this,false);
        //设置插件名字
        iBurpExtenderCallbacks.setExtensionName("Exchange OWA");
        // 实例化输入输出
        this.stdout = new PrintWriter(iBurpExtenderCallbacks.getStdout(), true);
        this.stderr = new PrintWriter(iBurpExtenderCallbacks.getStderr(), true);
        //注册右键菜单
        callbacks.registerContextMenuFactory(new ContextMenu());
        //初始化UI
        SwingUtilities.invokeLater(this::initialize);

    }

    private void initialize() {
        main = new MainUI();
        callbacks.customizeUiComponent(main);
        callbacks.addSuiteTab(BurpExtender.this);
    }


    @Override
    public String getTabCaption() {
        return extensionName;
    }

    @Override
    public Component getUiComponent() {
        return main;
    }

    public static PrintWriter getStdout() {
        return stdout;
    }

    public static PrintWriter getStderr() {
        return stderr;
    }

    public static IExtensionHelpers getHelpers() {
        return helpers;
    }

    public static IBurpExtenderCallbacks getCallbacks() {
        return callbacks;
    }

    public static String getResponseBody(IHttpRequestResponse messageInfo){
        String responseBody = "";
        try {
            IResponseInfo iResponseInfo = getHelpers().analyzeResponse(messageInfo.getResponse());
            int bodyOffset = iResponseInfo.getBodyOffset();
            String response = new String(messageInfo.getResponse(),"UTF-8");
            responseBody = response.substring(bodyOffset);
        } catch (Exception e) {
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                stderr.println(stackTraceElement);
            }
        }
        return responseBody;
    }

    @Override
    public IHttpService getHttpService() {
        return this.currentlyDisplayedItem.getHttpService();
    }

    @Override
    public byte[] getRequest() {
        return this.currentlyDisplayedItem.getRequest();
    }

    @Override
    public byte[] getResponse() {
        return this.currentlyDisplayedItem.getResponse();
    }
}
