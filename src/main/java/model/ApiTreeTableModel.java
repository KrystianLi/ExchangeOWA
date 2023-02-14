package model;

import ui.ExtensionTab;

import javax.swing.table.AbstractTableModel;

public class ApiTreeTableModel extends AbstractTableModel {
    public String[] columnNames = new String[]{" ","#",  "URL",  "Status Code","Length","Scan Time"};

    @Override
    public int getRowCount() {
        return ExtensionTab.apiTreeTable.getTableData().size();
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void add(ApiListTree apiListTree){
//        synchronized (ExtensionTab.apiTreeTable){
//
//        }
        ExtensionTab.apiTreeTable.getTableData().add(apiListTree.getManApiData());
        int id = ExtensionTab.apiTreeTable.getTableData().size();
        fireTableRowsInserted(id,id);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ApiTreeModel data = ExtensionTab.apiTreeTable.getTableData().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return data.treeStatus;
            case 1:
                return data.id;
            case 2:
                return data.url;
            case 3:
                return data.statusCode;
            case 4:
                return data.length;
            case 5:
                return data.scanTime;
        }
        return null;
    }




}
