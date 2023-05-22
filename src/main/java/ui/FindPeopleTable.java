package ui;

import burp.BurpExtender;
import entity.common.Id;
import model.FindPeopleModel;
import model.FindPeopleTableModel;
import utils.ExportExcel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public class FindPeopleTable {
    private JFrame jFrame;
    private JTable jTable;
    private JScrollPane jScrollPane;
    private FindPeopleTableModel findPeopleTableModel;
    private JPopupMenu jPopupMenu;

    public void crateFrame(){
        jFrame = new JFrame("FindPeople数据解析");
        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);

        findPeopleTableModel = new FindPeopleTableModel();
        jTable = new JTable(findPeopleTableModel);

        //创建右键菜单
        jPopupMenu = new JPopupMenu();

        JMenuItem exportMenItem = new JMenuItem();
        exportMenItem.setText("导出数据到表格");
        exportMenItem.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //导出数据
                LinkedHashMap<String, String> titleMap = new LinkedHashMap<String, String>();
                String[][] columnNames = findPeopleTableModel.getColumnNames();
                for (int i = 0; i < columnNames[1].length; i++) {
                    titleMap.put(columnNames[0][i],columnNames[1][i]);
                }
                String sheetName = "信息导出";
                List<FindPeopleModel> findPeopleModelList = findPeopleTableModel.getUserEmailModelList();
                ExportExcel.excelExport(findPeopleModelList, titleMap, sheetName);
                JOptionPane.showMessageDialog(null, "导出完成,生成文件在当前目录", "导出完成",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jPopupMenu.add(exportMenItem);

        //表格右键监听事件
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    jPopupMenu.show(jTable, e.getX(), e.getY());
                }
            }
        });


        jScrollPane = new JScrollPane(jTable);

        jFrame.add(jScrollPane);
        jFrame.add(jScrollPane, BorderLayout.CENTER);
        jFrame.dispose();
        jFrame.setVisible(true);
    }

    public void showTable(String emailAddress){
        List<FindPeopleModel> findPeopleModelList = findPeopleTableModel.getUserEmailModelList();
        BurpExtender.AllUser.get(emailAddress).forEach(allUser1 -> {
            allUser1.getBody().getResultSet().forEach(resultSet1 -> {
                try{
                    String id = "";
                    String emailAddress1 = "";
                    String displayName = "";
                    if (Optional.ofNullable(resultSet1.getPersonaId()).isPresent()){
                        id = resultSet1.getPersonaId().getId();
                    }
                    if (Optional.ofNullable(resultSet1.getEmailAddress()).isPresent()){
                        emailAddress1 = resultSet1.getEmailAddress().getEmailAddress();
                    }
                    if (Optional.ofNullable(resultSet1.getDisplayName()).isPresent()){
                        displayName = resultSet1.getDisplayName();
                    }
                    FindPeopleModel findPeopleModel = new FindPeopleModel(id, emailAddress1, displayName,emailAddress);
                    findPeopleModelList.add(findPeopleModel);
                    this.findPeopleTableModel.fireTableDataChanged();
                }catch (Exception e){
                    System.out.println("数据解析异常");
                }
            });
        });

    }
}
