package model;

import burp.IHttpRequestResponse;

public class ApiTreeModel {
    public String id;
    public String url;
    public String statusCode;
    public String length;
    public IHttpRequestResponse requestResponse;
    public String scanTime;
    public Boolean isSubData;
    public ApiListTree parentListTree;
    public String treeStatus = "";

    public ApiTreeModel(Boolean isSubData, String id, String url, String statusCode, String length, String scanTime,  IHttpRequestResponse requestResponse,ApiListTree parentListTree) {
        this.isSubData = isSubData;
        this.id = id;
        this.url = url;
        this.length = length;
        this.statusCode = statusCode;
        this.scanTime = scanTime;
        this.requestResponse = requestResponse;
        this.parentListTree = parentListTree;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public IHttpRequestResponse getRequestResponse() {
        return requestResponse;
    }

    public void setRequestResponse(IHttpRequestResponse requestResponse) {
        this.requestResponse = requestResponse;
    }

    public String getScanTime() {
        return scanTime;
    }

    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }

    public Boolean getSubData() {
        return isSubData;
    }

    public void setSubData(Boolean subData) {
        isSubData = subData;
    }

    public ApiListTree getParentListTree() {
        return parentListTree;
    }

    public void setParentListTree(ApiListTree parentListTree) {
        this.parentListTree = parentListTree;
    }

    public String getTreeStatus() {
        return treeStatus;
    }

    public void setTreeStatus(String treeStatus) {
        this.treeStatus = treeStatus;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
