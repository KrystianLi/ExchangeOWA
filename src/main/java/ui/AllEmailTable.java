package ui;

import model.AllUserModel;
import model.AllUserTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AllEmailTable {
    private JFrame jFrame;
    private JTable jTable;
    private JScrollPane jScrollPane;
    private AllUserTableModel allUserTableModel;

    public void crateFrame(){
        jFrame = new JFrame();
        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);

        allUserTableModel= new AllUserTableModel();
        jTable = new JTable(allUserTableModel);
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
