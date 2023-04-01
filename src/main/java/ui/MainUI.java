package ui;

import burp.BurpExtender;
import com.alibaba.fastjson2.JSON;
import entity.findpeople.FindPeople;
import entity.getpersona.GetPersona;
import model.ApiTreeModel;
import model.ApiTreeTableModel;
import ui.config.ConfigPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUI extends JPanel {
    //常量
    private String TAB_COLOR_SELECTED = "0xffc599";
    private String TAB_COLOR_MAIN_DATA = "0xf2f2f2";
    private String TAB_COLOR_SUB_DATA = "0xffffff";
    //变量
    public static int currentSelectRow;
    public static int currentSelectParentRow;
    //UI组件
    //主面板
    private JTabbedPane mainTabbedPane;
    //history所需组件
    private JSplitPane historySplitPane;
    private JScrollPane hScrollPane;

    private JSplitPane downSplitPane;
    private JTabbedPane requestPanel;

    private JTabbedPane responsePanel;
    public static ApiTreeTable apiTreeTable;
    public static ApiTreeTableModel apiTreeTableModel;
    private JPopupMenu tableMenu;
    //config所需组件
    private JSplitPane configSplitPane;
    private JTabbedPane logTabbedPane;
    private ConfigPanel configPanel;
    private JScrollPane resultScrollPanel;
    private JTextArea resultTextArea;
    private JScrollPane logScrollPanel;
    private JTextArea logTextArea;

    public MainUI() {
        initComponents();
    }

    private void initComponents() {
        this.mainTabbedPane = new JTabbedPane();
        this.resultTextArea = new JTextArea();
        this.resultScrollPanel = new JScrollPane();
        this.logTextArea = new JTextArea();
        this.logScrollPanel = new JScrollPane();

        //重置布局
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 1.0E-4};
        createHistoryPanel();
        createConfigPanel();
        this.mainTabbedPane.addTab("config",configSplitPane);
        this.mainTabbedPane.addTab("history",historySplitPane);
        //渲染mainTabbedPane
        add(this.mainTabbedPane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
    }

    /**
     * 初始化history面板
     */
    private void createHistoryPanel(){
        //初始化控件
        this.historySplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        tableMenu= new JPopupMenu();
        this.downSplitPane = new JSplitPane();
        this.downSplitPane.setResizeWeight(0.5D);
        this.requestPanel = new JTabbedPane();
        this.responsePanel = new JTabbedPane();
        JMenuItem parseGetPersonItem = new JMenuItem();
        JMenuItem parseFindPeopleItem = new JMenuItem();
        parseGetPersonItem.setText("解析GetPerson数据包");
        parseFindPeopleItem.setText("解析FindPeople数据包");

        //设置监听
        parseFindPeopleItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                FindPeopleTable findPeopleTable = new FindPeopleTable();
                findPeopleTable.crateFrame();
                String emailAddress = getSelectEmailAddress(BurpExtender.FindPeople);
                findPeopleTable.showTable(emailAddress);
            }
        });
        parseGetPersonItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GetPersonaTable getPersonaTable = new GetPersonaTable();
                getPersonaTable.createFrame();
                String emailAddress = getSelectEmailAddress(BurpExtender.GetPersona);
                getPersonaTable.showTable(emailAddress);
            }
        });
        tableMenu.add(parseFindPeopleItem);
        tableMenu.add(parseGetPersonItem);
        //新建table
        apiTreeTableModel = new ApiTreeTableModel();
        apiTreeTable = new ApiTreeTable(apiTreeTableModel);
        //监听table鼠标右键事件
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
        //单元格渲染器
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 2; i++)
            this.apiTreeTable.getColumnModel().getColumn(i).setMaxWidth(30);
        //重新渲染table表格
        this.apiTreeTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
                    ApiTreeModel apiTableData = MainUI.apiTreeTable.getTableData().get(row);
                    if (apiTableData.isSubData.booleanValue()) {
                        c.setBackground(Color.decode(TAB_COLOR_SUB_DATA));
                    } else {
                        c.setBackground(Color.decode(TAB_COLOR_MAIN_DATA));
                    }
                }
                return c;
            }

        });

        //组装UI
        this.requestPanel.addTab("Request", BurpExtender.requestTextEditor.getComponent());
        this.responsePanel.addTab("Response", BurpExtender.responseTextEditor.getComponent());
        this.downSplitPane.add(this.requestPanel, "left");
        this.downSplitPane.add(this.responsePanel, "right");

        hScrollPane= new JScrollPane(apiTreeTable);
        historySplitPane.add(this.hScrollPane);
        historySplitPane.add(this.downSplitPane, "right");
    }

    private void createConfigPanel(){
        //初始化控件
        this.configSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        this.resultScrollPanel.setViewportView(this.resultTextArea);
        this.logScrollPanel.setViewportView(this.logTextArea);
        //配置面板
        this.logTabbedPane = new JTabbedPane();

        logTabbedPane.addTab("Result",this.resultScrollPanel);
        logTabbedPane.addTab("Log",this.logScrollPanel);

        //组装UI
        configPanel = new ConfigPanel();
        this.configSplitPane.add(configPanel.getConfigPanel());
        this.configSplitPane.add(logTabbedPane);
    }



    /**
     * 获取当前选中行的email后缀地址，并拼接自定义自定义字符串形成map的key值
     * @return
     */
    private String getSelectEmailAddress(String actionName){
        ApiTreeModel apiTreeModel = apiTreeTable.getTableData().get(currentSelectRow);
        String responseBody = BurpExtender.getResponseBody(apiTreeModel.getRequestResponse());
        FindPeople findPeople = JSON.parseObject(responseBody, FindPeople.class);
        GetPersona getPersona = JSON.parseObject(responseBody, GetPersona.class);
        String emailAddress = "";
        if (findPeople.getBody().getResultSet().size() > 0){
            emailAddress = findPeople.getBody().getResultSet().get(0).getEmailAddress().getEmailAddress().split("@")[1]+"&"+actionName;
        } else{
            emailAddress = getPersona.getBody().getPersona().getEmailAddress().getEmailAddress().split("@")[1]+"&"+actionName;
        }
        return emailAddress;
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    public JTextArea getLogTextArea() {
        return logTextArea;
    }

    public JTextArea getResultTextArea() {
        return resultTextArea;
    }
}
