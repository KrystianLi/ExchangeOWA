package ui;

import model.AllUserModel;
import model.AllUserTableModel;
import utils.ExportExcel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.List;

public class AllEmailTable {
    private JFrame jFrame;
    private JTable jTable;
    private JScrollPane jScrollPane;
    private AllUserTableModel allUserTableModel;
    private JPopupMenu jPopupMenu;

    public void crateFrame(){
        jFrame = new JFrame("所有用户邮箱");
        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);

        allUserTableModel= new AllUserTableModel();
        jTable = new JTable(allUserTableModel);

        //创建右键菜单
        jPopupMenu = new JPopupMenu();

        JMenuItem exportMenItem = new JMenuItem();
        exportMenItem.setText("导出数据到表格");
        exportMenItem.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //导出数据
                LinkedHashMap<String, String> titleMap = new LinkedHashMap<String, String>();
                String[][] columnNames = allUserTableModel.getColumnNames();
                for (int i = 0; i < columnNames[1].length; i++) {
                    titleMap.put(columnNames[0][i],columnNames[1][i]);
                }
                String sheetName = "信息导出";
                List<AllUserModel> allUserModelList = allUserTableModel.getAllUserModelList();
                ExportExcel.excelExport(allUserModelList, titleMap, sheetName);
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
        List<AllUserModel> allUserModelList = allUserTableModel.getAllUserModelList();
        ExtensionTab.AllUser.get(emailAddress).forEach(allUser1 -> {
            allUser1.getBody().getResultSet().forEach(resultSet1 -> {
                String id = resultSet1.getPersonaId().getId();
                String emailAddress1 = resultSet1.getEmailAddress().getEmailAddress();
                String displayName = resultSet1.getDisplayName();
                AllUserModel allUserModel = new AllUserModel(id, emailAddress1, displayName);
                allUserModelList.add(allUserModel);
                this.allUserTableModel.fireTableDataChanged();
            });
        });

    }
}
