package ui;

import burp.BurpExtender;
import entity.common.NamesArray;
import entity.persona.Persona;
import entity.persona.body.BusinessPhoneNumbersArray.BusinessPhoneNumbersArray;
import model.AllUserModel;
import model.UserModel;
import model.UserTableModel;
import utils.ExportExcel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public class AllUserTable {
        private JFrame f;
        private JTable jTable;
        private JScrollPane jScrollPane;
        private JPopupMenu jPopupMenu;

        public static UserTableModel userTableModel;
        public void createFrame(){
                f = new JFrame("All User个人信息");
                f.setSize(1200, 300);
                f.setLocationRelativeTo(null);
                f.setLayout(new BorderLayout());

                //创建表格
                userTableModel = new UserTableModel();
                jTable = new JTable(userTableModel);

                //创建表格右键菜单
                jPopupMenu = new JPopupMenu();

                JMenuItem exportMenItem = new JMenuItem();
                exportMenItem.setText("导出数据到表格");
                exportMenItem.addActionListener(new ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                //导出数据
                                LinkedHashMap<String, String> titleMap = new LinkedHashMap<String, String>();
                                String[][] columnNames = userTableModel.getColumnNames();
                                for (int i = 0; i < columnNames[1].length; i++) {
                                        titleMap.put(columnNames[0][i],columnNames[1][i]);
                                }
                                String sheetName = "信息导出";
                                List<UserModel> userModelList = userTableModel.getUserModelList();
                                ExportExcel.excelExport(userModelList, titleMap, sheetName);
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
                f.add(jScrollPane);
                f.add(jScrollPane, BorderLayout.CENTER);
                f.dispose();
                f.setVisible(true);
        }

        public void showTable(String emailAddress){
                List<Persona> personas = ExtensionTab.Persona.get(emailAddress);
                List<UserModel> userModelList = userTableModel.getUserModelList();
                for (Persona persona : personas) {
                        String id = Optional.ofNullable(persona.getBody().getPersona().getPersonaId().getId()).orElse("");
                        String emailAddress1 = Optional.ofNullable(persona.getBody().getPersona().getEmailAddress().getEmailAddress()).orElse("");
                        List<BusinessPhoneNumbersArray> businessPhoneNumbersArray = persona.getBody().getPersona().getBusinessPhoneNumbersArray();
                        String normalizedNumber = "";
                        for (int i = 0; i< businessPhoneNumbersArray.size();i++){
                                normalizedNumber += Optional.ofNullable(businessPhoneNumbersArray.get(i).getValue().getNormalizedNumber()).orElse("");
                                if ((i+1) < businessPhoneNumbersArray.size()){
                                        normalizedNumber += ",";
                                }
                        }
                        String officeLocationsArray = "";
                        List<NamesArray> officeLocationsArray1 = persona.getBody().getPersona().getOfficeLocationsArray();
                        for (int i = 0; i < officeLocationsArray1.size(); i++){
                                officeLocationsArray += Optional.ofNullable(officeLocationsArray1.get(i).getValue()).orElse("");
                                if ((i+1) < officeLocationsArray1.size()){
                                        officeLocationsArray += ",";
                                }
                        }
                        String title = Optional.ofNullable(persona.getBody().getPersona().getTitle()).orElse(" ");
                        String department = Optional.ofNullable(persona.getBody().getPersona().getDepartment()).orElse("");
                        UserModel userModel = new UserModel(id, emailAddress1, normalizedNumber, officeLocationsArray, title, department);
                        userModelList.add(userModel);
                        userTableModel.fireTableDataChanged();
                }
        }
}
