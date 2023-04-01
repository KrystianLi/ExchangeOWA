package ui.config;

import burp.IHttpRequestResponse;
import enums.PurposeEnum;
import scan.InfoScanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel {
    private JButton startBtn;
    private JButton pauseBtn;
    private JButton stopBtn;
    private JLabel versionLabel;
    private JComboBox<String> versionJComboBox;
    private JLabel actionLabel;

    private JComboBox<String> actionJComboBox;
    private JLabel threadLabel;
    private JTextField threadField;
    private JTextArea requestTextArea;
    private JPanel configPanel;

    private IHttpRequestResponse iHttpRequestResponse;
    private InfoScanner infoScanner;

    public ConfigPanel() {
        initComponents();
    }

    public void initComponents(){
        this.configPanel = new JPanel(new BorderLayout());
        this.requestTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(requestTextArea);
        configPanel.setBorder(new EmptyBorder(20,20,20,20));

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(0));
        btnPanel.setBorder(new EmptyBorder(0,0,10,0));
        //组装按钮
        startBtn = new JButton("开始");
        pauseBtn = new JButton("暂停");
        stopBtn = new JButton("停止");
        versionLabel = new JLabel("Version: ");
        versionJComboBox = new JComboBox(PurposeEnum.getExchangeVersion().toArray());
        actionLabel = new JLabel("Action: ");
        actionJComboBox = new JComboBox<>();
        threadLabel = new JLabel("线程: ");
        threadField = new JTextField(5);
        threadField.setFocusable(true);
        threadField.setText("5");

        //二级联动action
        versionJComboBox.addActionListener( e -> {
            actionJComboBox.removeAllItems();
            JComboBox source = (JComboBox) e.getSource();
            String exchangeVersion = (String) source.getSelectedItem();
            for (String action : PurposeEnum.getExchangeAction(exchangeVersion)) {
                actionJComboBox.addItem(action);
            }
        });
        versionJComboBox.setSelectedIndex(0);
        //按钮监听事件
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAction();
            }
        });
        pauseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAction();
            }
        });
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseAction();
            }
        });

        //组装UI
        btnPanel.add(startBtn);
        btnPanel.add(pauseBtn);
        btnPanel.add(stopBtn);
        btnPanel.add(versionLabel);
        btnPanel.add(versionJComboBox);
        btnPanel.add(actionLabel);
        btnPanel.add(actionJComboBox);
        btnPanel.add(threadLabel);
        btnPanel.add(threadField);

        this.configPanel.add(btnPanel,BorderLayout.NORTH);
        this.configPanel.add(jScrollPane,BorderLayout.CENTER);
    }

    public void startAction(){
        JOptionPane.showMessageDialog(null,"开始");
        String owaVersion = (String) this.versionJComboBox.getSelectedItem();
        String owaAction = (String) this.actionJComboBox.getSelectedItem();
        int scanThread = Integer.parseInt(this.threadField.getText());
        this.infoScanner = new InfoScanner(iHttpRequestResponse);
        this.infoScanner.scan(owaVersion,owaAction,scanThread);
    }

    public void stopAction(){
        this.infoScanner.stop();
        JOptionPane.showMessageDialog(null,"暂停");
    }

    public void pauseAction(){
        this.infoScanner.stop();
        JOptionPane.showMessageDialog(null,"停止");
    }

    public JPanel getConfigPanel() {
        return configPanel;
    }

    public void setConfigPanel(JPanel exchange2016Panel) {
        configPanel = exchange2016Panel;
    }

    public JTextArea getRequestTextArea() {
        return requestTextArea;
    }

    public void setRequestTextArea(JTextArea requestTextArea) {
        this.requestTextArea = requestTextArea;
    }

    public IHttpRequestResponse getiHttpRequestResponse() {
        return iHttpRequestResponse;
    }

    public void setiHttpRequestResponse(IHttpRequestResponse iHttpRequestResponse) {
        this.iHttpRequestResponse = iHttpRequestResponse;
    }
}
