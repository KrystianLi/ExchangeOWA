package ui;

import burp.BurpExtender;
import entity.common.NamesArray;
import entity.persona.Persona;
import entity.persona.body.BusinessPhoneNumbersArray.BusinessPhoneNumbersArray;
import model.UserModel;
import model.UserTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class AllUserTable {
        private JFrame f;
        private JTable jTable;
        private JScrollPane jScrollPane;

        public static UserTableModel userTableModel;
        public void createFrame(){
                f = new JFrame("outlook");
                f.setSize(1200, 300);
                f.setLocationRelativeTo(null);
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
                        String id = Optional.ofNullable(persona.getBody().getPersona().getPersonaId().getId()).orElse("");
                        String emailAddress1 = Optional.ofNullable(persona.getBody().getPersona().getEmailAddress().getEmailAddress()).orElse("");
                        List<BusinessPhoneNumbersArray> businessPhoneNumbersArray = persona.getBody().getPersona().getBusinessPhoneNumbersArray();
                        String normalizedNumber = "";
                        for (int i = 0; i< businessPhoneNumbersArray.size();i++){
                                normalizedNumber += Optional.ofNullable(businessPhoneNumbersArray.get(i).getValue().getNormalizedNumber()).orElse("");
                                if (i+1 >= businessPhoneNumbersArray.size()){
                                        normalizedNumber += ",";
                                }
                        }
                        String officeLocationsArray = "";
                        List<NamesArray> officeLocationsArray1 = persona.getBody().getPersona().getOfficeLocationsArray();
                        for (int i = 0; i < officeLocationsArray1.size(); i++){
                                officeLocationsArray += Optional.ofNullable(officeLocationsArray1.get(i).getValue()).orElse("");
                                if (i+1 >= officeLocationsArray1.size()){
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
