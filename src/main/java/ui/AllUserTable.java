package ui;

import burp.BurpExtender;
import entity.persona.Persona;
import model.UserModel;
import model.UserTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AllUserTable {
        private JFrame f;
        private JTable jTable;
        private JScrollPane jScrollPane;

        public static UserTableModel userTableModel;
        public void createFrame(){
                f = new JFrame("outlook");
                f.setSize(1200, 300);
                f.setLocation(200, 200);
                f.setLayout(new BorderLayout());

                userTableModel = new UserTableModel();
                jTable = new JTable(userTableModel);
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
                        String id = persona.getBody().getPersona().getPersonaId().getId();
                        String emailAddress1 = persona.getBody().getPersona().getEmailAddress().getEmailAddress();
                        String normalizedNumber = persona.getBody().getPersona().getBusinessPhoneNumbersArray().get(0).getValue().getNormalizedNumber();
                        String officeLocationsArray = persona.getBody().getPersona().getOfficeLocationsArray().get(0).getValue();
                        String title = persona.getBody().getPersona().getTitle();
                        String department = persona.getBody().getPersona().getDepartment();
                        UserModel userModel = new UserModel(id, emailAddress1, normalizedNumber, officeLocationsArray, title, department);
                        userModelList.add(userModel);
                        userTableModel.fireTableDataChanged();
                }
        }
}
