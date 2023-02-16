package ui;

import burp.BurpExtender;
import model.ApiTreeModel;
import model.ApiTreeTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 自定义Table
 */
public class ApiTreeTable  extends JTable {
    public static int parentCount = 0;
    private final List<ApiTreeModel> tableData = new ArrayList<>();

    public ApiTreeTable(TableModel tableModel) {
        super(tableModel);
    }

    public List<ApiTreeModel> getTableData() {
        return this.tableData;
    }

    public void changeSelection(int row, int col, boolean toggle, boolean extend) {
        try{
            ApiTreeModel dataEntry = this.tableData.get(convertRowIndexToModel(row));
            if (dataEntry != null){
                if (!dataEntry.isSubData.booleanValue())
                    if (dataEntry.parentListTree.getExpandStatus().booleanValue()) {
                        dataEntry.parentListTree.collapse();
                    } else {
                        dataEntry.parentListTree.expand();
                    }
                if (null != dataEntry.requestResponse){
                    ExtensionTab.requestTextEditor.setMessage(dataEntry.requestResponse.getRequest(), true);
                    ExtensionTab.responseTextEditor.setMessage(dataEntry.requestResponse.getResponse(), false);
                }
                ExtensionTab.currentlyDisplayedItem = dataEntry.requestResponse;
                super.changeSelection(row, col, toggle, extend);
            }else {
                BurpExtender.getStdout().println("null dataEntry");
            }
        }catch (Exception e){
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                BurpExtender.getStderr().println(stackTraceElement);
            }
        }

    }


}
