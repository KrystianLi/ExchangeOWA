package model;

import burp.BurpExtender;
import burp.IHttpRequestResponse;
import ui.MainUI;
import utils.HttpUtil;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class ApiTreeTableModel extends AbstractTableModel {
    public String[] columnNames = new String[]{" ","#",  "URL",  "Status Code","Length","Scan Time"};

    @Override
    public int getRowCount() {
        return MainUI.apiTreeTable.getTableData().size();
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
        MainUI.apiTreeTable.getTableData().add(apiListTree.getManApiData());
        int id = MainUI.apiTreeTable.getTableData().size();
        fireTableRowsInserted(id,id);
    }

    public void setApiTreeMode(ApiTreeModel apiTreeModel, IHttpRequestResponse iHttpRequestResponse){
        //当前时间
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String nowDate = ft.format(date);
        //数据包长度
        List<String> headers = BurpExtender.getHelpers().analyzeResponse(iHttpRequestResponse.getResponse()).getHeaders();
        LinkedHashMap<String, String> headerMap = HttpUtil.headerList2Map(headers);
        //设置apiTreeModel属性
        apiTreeModel.setScanTime(nowDate);
        apiTreeModel.setLength(headerMap.get("Content-Length"));
        apiTreeModel.setStatusCode(String.valueOf(BurpExtender.getHelpers().analyzeResponse(iHttpRequestResponse.getResponse()).getStatusCode()));
        apiTreeModel.setRequestResponse(iHttpRequestResponse);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ApiTreeModel data = MainUI.apiTreeTable.getTableData().get(rowIndex);
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
