package ui;

import burp.BurpExtender;
import entity.common.NamesArray;
import entity.getpersona.GetPersona;
import entity.getpersona.body.BusinessPhoneNumbersArray.BusinessPhoneNumbersArray;
import model.GetPersonaModel;
import model.GetPersonaTableModel;
import utils.ExportExcel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public class GetPersonaTable {
        private JFrame f;
        private JTable jTable;
        private JScrollPane jScrollPane;
        private JPopupMenu jPopupMenu;

        public static GetPersonaTableModel getPersonaTableModel;
        public void createFrame(){
                f = new JFrame("GetPersona数据解析");
                f.setSize(1200, 300);
                f.setLocationRelativeTo(null);
                f.setLayout(new BorderLayout());

                //创建表格
                getPersonaTableModel = new GetPersonaTableModel();
                jTable = new JTable(getPersonaTableModel);

                //创建表格右键菜单
                jPopupMenu = new JPopupMenu();

                JMenuItem exportMenItem = new JMenuItem();
                exportMenItem.setText("导出数据到表格");
                exportMenItem.addActionListener(new ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                //导出数据
                                LinkedHashMap<String, String> titleMap = new LinkedHashMap<String, String>();
                                String[][] columnNames = getPersonaTableModel.getColumnNames();
                                for (int i = 0; i < columnNames[1].length; i++) {
                                        titleMap.put(columnNames[0][i],columnNames[1][i]);
                                }
                                String sheetName = "信息导出";
                                List<GetPersonaModel> getPersonaModelList = getPersonaTableModel.getUserModelList();
                                ExportExcel.excelExport(getPersonaModelList, titleMap, sheetName);
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
                List<GetPersona> getPersonas = BurpExtender.Persona.get(emailAddress);
                List<GetPersonaModel> getPersonaModelList = getPersonaTableModel.getUserModelList();
                for (GetPersona getPersona : getPersonas) {
                        String id = Optional.ofNullable(getPersona.getBody().getPersona().getPersonaId().getId()).orElse("");
                        String displayName = Optional.ofNullable(getPersona.getBody().getPersona().getDisplayName()).orElse("");
                        String emailAddress1 = Optional.ofNullable(getPersona.getBody().getPersona().getEmailAddress().getEmailAddress()).orElse("");
                        List<BusinessPhoneNumbersArray> businessPhoneNumbersArray = getPersona.getBody().getPersona().getBusinessPhoneNumbersArray();
                        String normalizedNumber = "";
                        for (int i = 0; i< businessPhoneNumbersArray.size();i++){
                                normalizedNumber += Optional.ofNullable(businessPhoneNumbersArray.get(i).getValue().getNormalizedNumber()).orElse("");
                                if ((i+1) < businessPhoneNumbersArray.size()){
                                        normalizedNumber += ",";
                                }
                        }
                        String officeLocationsArray = "";
                        List<NamesArray> officeLocationsArray1 = getPersona.getBody().getPersona().getOfficeLocationsArray();
                        for (int i = 0; i < officeLocationsArray1.size(); i++){
                                officeLocationsArray += Optional.ofNullable(officeLocationsArray1.get(i).getValue()).orElse("");
                                if ((i+1) < officeLocationsArray1.size()){
                                        officeLocationsArray += ",";
                                }
                        }
                        String title = Optional.ofNullable(getPersona.getBody().getPersona().getTitle()).orElse(" ");
                        String department = Optional.ofNullable(getPersona.getBody().getPersona().getDepartment()).orElse("");
                        GetPersonaModel getPersonaModel = new GetPersonaModel(id,displayName, emailAddress1, normalizedNumber, officeLocationsArray, title, department);
                        getPersonaModelList.add(getPersonaModel);
                        getPersonaTableModel.fireTableDataChanged();
                }
        }
}
