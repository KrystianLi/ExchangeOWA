package burp;

import ui.ContextMenu;
import ui.ExtensionTab;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class BurpExtender implements IBurpExtender, ITab{
    private static PrintWriter stdout;
    private static PrintWriter stderr;
    private static IExtensionHelpers helpers;
    private static IBurpExtenderCallbacks callbacks;
    private String extensionName = "OutLook";

    private static ExtensionTab extensionTab;
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks iBurpExtenderCallbacks) {
        //初始化变量
        this.callbacks = iBurpExtenderCallbacks;
        this.helpers = this.callbacks.getHelpers();
        //设置插件名字
        iBurpExtenderCallbacks.setExtensionName("OutLook information collection");
        // 实例化输入输出
        this.stdout = new PrintWriter(iBurpExtenderCallbacks.getStdout(), true);
        this.stderr = new PrintWriter(iBurpExtenderCallbacks.getStderr(), true);
        //注册右键菜单
        callbacks.registerContextMenuFactory(new ContextMenu());
        // UI
        extensionTab = new ExtensionTab(extensionName);
    }


    @Override
    public String getTabCaption() {
        return extensionName;
    }

    @Override
    public Component getUiComponent() {
        return extensionTab.getComponent();
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
}
