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
        JMenuItem findPeopleItem = new JMenuItem("FindPeople scan");
        JMenuItem getPersonaItem = new JMenuItem("All GetPersona scan");
        findPeopleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompletableFuture.supplyAsync(() -> {
                    //获取当前选择的url
                    IHttpRequestResponse[] httpRequestResponses = iContextMenuInvocation.getSelectedMessages();
                    for (IHttpRequestResponse iHttpRequestResponse : httpRequestResponses){
                        InfoScanner infoScanner = new InfoScanner(iHttpRequestResponse);
                        infoScanner.scanFindPeople();
                    }
                    BurpExtender.getStdout().println("Scan completion");
                    return null;
                },MyExecutor.getExecutor());
            }
        });
        getPersonaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //异步执行该方法
                CompletableFuture.supplyAsync(() -> {
                    //获取当前选择的url
                    IHttpRequestResponse[] httpRequestResponses = iContextMenuInvocation.getSelectedMessages();
                    for (IHttpRequestResponse iHttpRequestResponse : httpRequestResponses){
                        InfoScanner infoScanner = new InfoScanner(iHttpRequestResponse);
                        infoScanner.scanPersona();
                    }
                    BurpExtender.getStdout().println("Scan completion");
                    return null;
                },MyExecutor.getExecutor());
            }
        });
        menuItems.add(findPeopleItem);
        menuItems.add(getPersonaItem);
        return menuItems;
    }
}

