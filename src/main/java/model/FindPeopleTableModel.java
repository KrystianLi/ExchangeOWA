package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FindPeopleTableModel extends AbstractTableModel {
    private List<FindPeopleModel> findPeopleModelList;
    public String[][] columnNames = new String[][]{{"id","emailAddress","displayName"},{"用户id","邮箱地址",  "用户名"}};

    public FindPeopleTableModel(){
        findPeopleModelList = new ArrayList<>();
    }
    public FindPeopleTableModel(List<FindPeopleModel> findPeopleModelList) {
        this.findPeopleModelList = findPeopleModelList;
    }

    @Override
    public int getRowCount() {
        return this.findPeopleModelList.size();
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
        FindPeopleModel findPeopleModel = this.findPeopleModelList.get(rowIndex);
        switch (columnIndex){
            case 0:
                return findPeopleModel.getId();
            case 1:
                return findPeopleModel.getEmailAddress();
            case 2:
                return findPeopleModel.getDisplayName();
        }
        return null;
    }

    public List<FindPeopleModel> getUserEmailModelList() {
        return findPeopleModelList;
    }

    public void setUserEmailModelList(List<FindPeopleModel> findPeopleModelList) {
        this.findPeopleModelList = findPeopleModelList;
    }
}
