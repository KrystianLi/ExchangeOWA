package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private List<UserModel> userModelList;
    public String[] columnNames = new String[]{"用户id","邮箱地址",  "手机号",  "办公地址","职务","部门"};

    public UserTableModel(){
        userModelList = new ArrayList<>();
    }

    public List<UserModel> getUserModelList() {
        return userModelList;
    }

    public void setUserModelList(List<UserModel> userModelList) {
        this.userModelList = userModelList;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return userModelList.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UserModel user = userModelList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.id;
            case 1:
                return user.emailAddress;
            case 2:
                return user.number;
            case 3:
                return user.officeLocations;
            case 4:
                return user.title;
            case 5:
                return user.department;
        }
        return null;
    }
}
