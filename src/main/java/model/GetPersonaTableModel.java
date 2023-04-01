package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GetPersonaTableModel extends AbstractTableModel {

    private List<GetPersonaModel> getPersonaModelList;
    public String[][] columnNames = new String[][]{{"id","displayName","emailAddress","number","officeLocations","title","department"},{"用户id","用户名","邮箱地址",  "手机号",  "办公地址","职务","部门"}};

    public GetPersonaTableModel(){
        getPersonaModelList = new ArrayList<>();
    }

    public List<GetPersonaModel> getUserModelList() {
        return getPersonaModelList;
    }

    public void setUserModelList(List<GetPersonaModel> getPersonaModelList) {
        this.getPersonaModelList = getPersonaModelList;
    }

    public String[][] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[][] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return getPersonaModelList.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[1][column];
    }

    @Override
    public int getColumnCount() {
        return columnNames[1].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GetPersonaModel user = getPersonaModelList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.id;
            case 1:
                return user.displayName;
            case 2:
                return user.emailAddress;
            case 3:
                return user.number;
            case 4:
                return user.officeLocations;
            case 5:
                return user.title;
            case 6:
                return user.department;
        }
        return null;
    }
}
