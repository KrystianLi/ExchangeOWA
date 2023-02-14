package ui;

import burp.*;
import utils.MyExecutor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 右键菜单类
 */
public class ContextMenu implements IContextMenuFactory {
    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation iContextMenuInvocation) {
        List<JMenuItem> menuItems = new ArrayList<>();
        JMenuItem menuItem = new JMenuItem("Do OoutLook Email scan");
        menuItem.addActionListener(new ContextMenuActionListener(iContextMenuInvocation));
        menuItems.add(menuItem);
        return menuItems;
    }

    static class ContextMenuActionListener implements ActionListener {
        IContextMenuInvocation iContextMenuInvocation;
        public ContextMenuActionListener (IContextMenuInvocation iContextMenuInvocation) {
            this.iContextMenuInvocation = iContextMenuInvocation;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //异步执行该方法
            CompletableFuture.supplyAsync(() -> {
                //获取当前选择的url
                IHttpRequestResponse[] httpRequestResponses = this.iContextMenuInvocation.getSelectedMessages();
                for (IHttpRequestResponse iHttpRequestResponse : httpRequestResponses){
                    InfoScanner infoScanner = new InfoScanner(iHttpRequestResponse);
                    infoScanner.parseApi();
                }
                return null;
            },MyExecutor.getExecutor());
        }
        }

}

