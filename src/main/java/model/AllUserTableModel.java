package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AllUserTableModel extends AbstractTableModel {
    private List<AllUserModel> allUserModelList;
    public String[][] columnNames = new String[][]{{"id","emailAddress","displayName"},{"用户id","邮箱地址",  "用户名"}};

    public AllUserTableModel(){
        allUserModelList = new ArrayList<>();
    }
    public AllUserTableModel(List<AllUserModel> allUserModelList) {
        this.allUserModelList = allUserModelList;
    }

    @Override
    public int getRowCount() {
        return this.allUserModelList.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames[1].length;
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[1][column];
    }

    public String[][] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[][] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AllUserModel allUserModel = this.allUserModelList.get(rowIndex);
        switch (columnIndex){
            case 0:
                return allUserModel.getId();
            case 1:
                return allUserModel.getEmailAddress();
            case 2:
                return allUserModel.getDisplayName();
        }
        return null;
    }

    public List<AllUserModel> getAllUserModelList() {
        return allUserModelList;
    }

    public void setAllUserModelList(List<AllUserModel> allUserModelList) {
        this.allUserModelList = allUserModelList;
    }
}
