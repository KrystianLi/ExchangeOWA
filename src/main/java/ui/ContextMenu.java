package ui;

import burp.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 右键菜单类
 */
public class ContextMenu implements IContextMenuFactory {
    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation iContextMenuInvocation) {
        List<JMenuItem> menuItems = new ArrayList<>();
        JMenuItem sendToConfigItem = new JMenuItem("Send to Config Panel");
        sendToConfigItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取当前选择的url
                IHttpRequestResponse[] httpRequestResponses = iContextMenuInvocation.getSelectedMessages();
                for (IHttpRequestResponse iHttpRequestResponse : httpRequestResponses){
                    try{
                        String requestStr = new String(iHttpRequestResponse.getRequest(), "UTF-8");
                        BurpExtender.main.getConfigPanel().getRequestTextArea().setText(requestStr);
                        BurpExtender.main.getConfigPanel().setiHttpRequestResponse(iHttpRequestResponse);
                    }catch (Exception exception){
                        for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                            BurpExtender.getStderr().println(stackTraceElement.toString());
                        }

                    }

                }
                BurpExtender.getStdout().println("send to config panel");
            }
        });
        menuItems.add(sendToConfigItem);
        return menuItems;
    }
}

