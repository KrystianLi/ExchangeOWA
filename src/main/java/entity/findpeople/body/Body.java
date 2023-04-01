package entity.findpeople.body;

import java.util.ArrayList;
import java.util.List;

public class Body {
    private String __type;
    private List<ResultSet> ResultSet = new ArrayList<ResultSet>();
    private int TotalNumberOfPeopleInView;
    private int FirstMatchingRowIndex;
    private int FirstLoadedRowIndex;
    private int TotalNumberOfFavoritesInView;
    private String ResponseCode;
    private String ResponseClass;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public List<entity.findpeople.body.ResultSet> getResultSet() {
        return ResultSet;
    }

    public void setResultSet(List<entity.findpeople.body.ResultSet> resultSet) {
        ResultSet = resultSet;
    }

    public int getTotalNumberOfPeopleInView() {
        return TotalNumberOfPeopleInView;
    }

    public void setTotalNumberOfPeopleInView(int totalNumberOfPeopleInView) {
        TotalNumberOfPeopleInView = totalNumberOfPeopleInView;
    }

    public int getFirstMatchingRowIndex() {
        return FirstMatchingRowIndex;
    }

    public void setFirstMatchingRowIndex(int firstMatchingRowIndex) {
        FirstMatchingRowIndex = firstMatchingRowIndex;
    }

    public int getFirstLoadedRowIndex() {
        return FirstLoadedRowIndex;
    }

    public void setFirstLoadedRowIndex(int firstLoadedRowIndex) {
        FirstLoadedRowIndex = firstLoadedRowIndex;
    }

    public int getTotalNumberOfFavoritesInView() {
        return TotalNumberOfFavoritesInView;
    }

    public void setTotalNumberOfFavoritesInView(int totalNumberOfFavoritesInView) {
        TotalNumberOfFavoritesInView = totalNumberOfFavoritesInView;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getResponseClass() {
        return ResponseClass;
    }

    public void setResponseClass(String responseClass) {
        ResponseClass = responseClass;
    }
}
