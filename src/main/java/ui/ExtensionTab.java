package ui;

import burp.*;
import com.alibaba.fastjson2.JSON;
import entity.alluser.AllUser;
import entity.persona.Persona;
import model.ApiTreeModel;
import model.ApiTreeTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtensionTab implements ITab, IMessageEditorController {
    public static final String FindPeople = "FindPeople";
    public static final String GetPersona = "GetPersona";
    private String TAB_COLOR_SELECTED = "0xffc599";
    private String TAB_COLOR_MAIN_DATA = "0xf2f2f2";
    private String TAB_COLOR_SUB_DATA = "0xffffff";

    private JSplitPane mainSplitPane;
    private JScrollPane upScrollPane;

    private JSplitPane downSplitPane;
    private JTabbedPane requestPanel;

    private JTabbedPane responsePanel;
    public static ApiTreeTable apiTreeTable;
    public static ApiTreeTableModel apiTreeTableModel;
    private JPopupMenu tableMenu;
    public static IMessageEditor requestTextEditor;
    private String extensionName;

    public static IMessageEditor responseTextEditor;


    public static IHttpRequestResponse currentlyDisplayedItem;
    //TODO 静态变量存储请求响应结果，便于其他类使用数据，后续改为db文件存储
    public static Map<String, List<AllUser>> AllUser = new HashMap<>();
    public static Map<String, List<Persona>> Persona = new HashMap<>();

    public static int currentSelectRow;
    public static int currentSelectParentRow;

    public ExtensionTab(String extensionName){
        this.extensionName = extensionName;
        IBurpExtenderCallbacks callbacks = BurpExtender.getCallbacks();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ExtensionTab.this.requestTextEditor = callbacks.createMessageEditor(ExtensionTab.this, false);
                ExtensionTab.this.responseTextEditor = callbacks.createMessageEditor(ExtensionTab.this,false);
                mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
                //上方面板
                //新建table右键菜单
                tableMenu= new JPopupMenu();
                JMenuItem allUserItem = new JMenuItem();
                JMenuItem parseAllUserItem = new JMenuItem();
                JMenuItem allUserEmailItem = new JMenuItem();
                allUserItem.setText("获取All User原始数据包");
                parseAllUserItem.setText("获取All User个人信息");
                allUserEmailItem.setText("获取所有用户邮箱");
                allUserEmailItem.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        AllEmailTable allEmailTable = new AllEmailTable();
                        allEmailTable.crateFrame();
                        String emailAddress = getSelectEmailAddress(ExtensionTab.FindPeople);
                        allEmailTable.showTable(emailAddress);
                    }
                });
                parseAllUserItem.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        AllUserTable allUserTable = new AllUserTable();
                        allUserTable.createFrame();
                        String emailAddress = getSelectEmailAddress(ExtensionTab.GetPersona);
                        allUserTable.showTable(emailAddress);
                    }
                });
                tableMenu.add(allUserItem);
                tableMenu.add(allUserEmailItem);
                tableMenu.add(parseAllUserItem);
                //新建table
                apiTreeTableModel = new ApiTreeTableModel();
                apiTreeTable = new ApiTreeTable(apiTreeTableModel);
                //监听鼠标右键事件
                apiTreeTable.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            //通过点击位置找到点击为表格中的行
                            int focusedRowIndex = apiTreeTable.rowAtPoint(e.getPoint());
                            if (focusedRowIndex == -1) {
                                return;
                            }
                            //将表格所选项设为当前右键点击的行
                            currentSelectRow = focusedRowIndex;
                            //判断当前选择的是否是父节点
                            currentSelectParentRow = Integer.parseInt(apiTreeTable.getValueAt(focusedRowIndex,1).toString().split("\\.")[0]);
                            apiTreeTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                            //弹出菜单
                            tableMenu.show(apiTreeTable, e.getX(), e.getY());
                        }
                    }
                });
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//单元格渲染器
                tcr.setHorizontalAlignment(JLabel.CENTER);//居中显示
                for (int i = 0; i < 2; i++)
                    ExtensionTab.this.apiTreeTable.getColumnModel().getColumn(i).setMaxWidth(30);
                    ExtensionTab.this.apiTreeTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                        @Override
                        //设置单元格内容居中
                        public void setHorizontalAlignment(int alignment){
                            super.setHorizontalAlignment(CENTER);
                        }
                        //设置单元格渲染
                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        if (isSelected) {
                            c.setBackground(Color.decode(TAB_COLOR_SELECTED));
                        } else {
                            ApiTreeModel apiTableData = ExtensionTab.apiTreeTable.getTableData().get(row);
                            if (apiTableData.isSubData.booleanValue()) {
                                c.setBackground(Color.decode(TAB_COLOR_SUB_DATA));
                            } else {
                                c.setBackground(Color.decode(TAB_COLOR_MAIN_DATA));
                            }
                        }
                        return c;
                    }

                });
                upScrollPane= new JScrollPane(apiTreeTable);
                mainSplitPane.add(ExtensionTab.this.upScrollPane);

                //下方面板
                ExtensionTab.this.downSplitPane = new JSplitPane();
                ExtensionTab.this.downSplitPane.setResizeWeight(0.5D);
                ExtensionTab.this.requestPanel = new JTabbedPane();
                ExtensionTab.this.requestPanel.addTab("Request", ExtensionTab.this.requestTextEditor.getComponent());
                ExtensionTab.this.responsePanel = new JTabbedPane();
                ExtensionTab.this.responsePanel.addTab("Response", ExtensionTab.this.responseTextEditor.getComponent());
                ExtensionTab.this.downSplitPane.add(ExtensionTab.this.requestPanel, "left");
                ExtensionTab.this.downSplitPane.add(ExtensionTab.this.responsePanel, "right");

                mainSplitPane.add(ExtensionTab.this.downSplitPane, "right");
                callbacks.customizeUiComponent(ExtensionTab.this.mainSplitPane);
                callbacks.addSuiteTab(ExtensionTab.this);
            }
        });
    }


    public Component getComponent(){
        return mainSplitPane;
    }
    @Override
    public String getTabCaption() {
        return this.extensionName;
    }

    @Override
    public Component getUiComponent() {
        return this.mainSplitPane;
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

    /**
     * 获取当前选中行的email后缀地址，并拼接自定义自定义字符串形成map的key值
     * @return
     */
    private String getSelectEmailAddress(String actionName){
        ApiTreeModel apiTreeModel = apiTreeTable.getTableData().get(currentSelectRow);
        String responseBody = BurpExtender.getResponseBody(apiTreeModel.getRequestResponse());
        entity.alluser.AllUser allUser = JSON.parseObject(responseBody, entity.alluser.AllUser.class);
        Persona persona = JSON.parseObject(responseBody, Persona.class);
        String emailAddress = "";
        if (allUser.getBody().getResultSet().size() > 0){
            emailAddress = allUser.getBody().getResultSet().get(0).getEmailAddress().getEmailAddress().split("@")[1]+"&"+actionName;
        } else{
            emailAddress = persona.getBody().getPersona().getEmailAddress().getEmailAddress().split("@")[1]+"&"+actionName;
        }
        return emailAddress;
    }



}
