package model;

import burp.BurpExtender;
import ui.ExtensionTab;

import java.util.List;

public class ApiListTree{
    private String TREE_STATUS_EXPAND = "▼";
    private String TREE_STATUS_COLLAPSE = "▶";
    private ApiTreeTableModel parent;
    private ApiTreeModel mainApiData;
    private List<ApiTreeModel> subApiData;
    private Boolean expandStatus = Boolean.FALSE;

    public ApiListTree(ApiTreeTableModel parent){
        this.parent = parent;
    }

    public void setSubApiData(List<ApiTreeModel> subApiData){
        this.subApiData = subApiData;
    }


    public ApiTreeModel getManApiData() {
        return mainApiData;
    }

    public void setMainApiData(ApiTreeModel mainApiData) {
        this.mainApiData = mainApiData;
    }

    public Boolean getExpandStatus() {
        return this.expandStatus;
    }

    /**
     * 节点展开
     */
    public void expand(){
        if (!this.expandStatus){
            this.mainApiData.setTreeStatus(TREE_STATUS_EXPAND);
            List<ApiTreeModel> apiTableData = ExtensionTab.apiTreeTable.getTableData();
            int selfIndex = apiTableData.indexOf(this.mainApiData);
            for (int i = 0; i < this.subApiData.size(); i++) {
                ApiTreeModel data = this.subApiData.get(i);
                apiTableData.add(selfIndex + 1 + i, data);
            }
            int _id = apiTableData.size();
            this.parent.fireTableRowsInserted(selfIndex, _id);
        }
        this.expandStatus = Boolean.valueOf(true);
    }

    /**
     * 节点折叠
     */
    public void collapse(){
        try {
            if (this.expandStatus){
                this.mainApiData.setTreeStatus(TREE_STATUS_COLLAPSE);
                List<ApiTreeModel> apiTableData = ExtensionTab.apiTreeTable.getTableData();
                int selfIndex = apiTableData.indexOf(this.mainApiData);
                for (int i = 0; i < this.subApiData.size(); i++)
                    apiTableData.remove(selfIndex + 1);
                int _id = apiTableData.size();
                this.parent.fireTableRowsInserted(selfIndex, _id);
            }
            this.expandStatus = Boolean.valueOf(false);
        }catch (Exception e){
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                BurpExtender.getStderr().println(stackTraceElement);
            }
        }
    }
}
