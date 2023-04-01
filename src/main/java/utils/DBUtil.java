package utils;

import model.FindPeopleModel;

import java.sql.*;
import java.util.List;

public class DBUtil {
    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:test.db";
    private static final String CREATE_USER_EMAIL = "create table if not exists UserEmail (" +
                                                " id           TEXT, " +
                                                " emailAddress            TEXT, " +
                                                " displayName        TEXT, " +
                                                " emailDomain        TEXT, " +
                                                " responseBody    TEXT)";
    private static final String CREATE_FIND_PEOPLE = "create table if not exists FindPeopleDate (" +
                                                        " EmailDomain           TEXT, " +
                                                        " UserID           TEXT, " +
                                                        " EmailAddress           TEXT, " +
                                                        " DisplayName           TEXT, " +
                                                        " ResponseBody    TEXT)";
    private static Connection connection;

    public DBUtil() {
        try {
            Class.forName(CLASS_NAME);
            connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void FindPeopleDateInsert(String EmailDomain,String UserID, String EmailAddress,String DisplayName,String ResponseBody){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_FIND_PEOPLE);
            String  sql = "insert into FindPeopleDate(EmailDomain, UserID, EmailAddress, DisplayName, ResponseBody) values(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,EmailDomain);
            preparedStatement.setString(2,UserID);
            preparedStatement.setString(3,EmailAddress);
            preparedStatement.setString(4,DisplayName);
            preparedStatement.setString(5,ResponseBody);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnect(){
        try{
            if( connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void userEmailInsert(List<FindPeopleModel> findPeopleModelList){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_USER_EMAIL);
            String  sql = "insert into UserEmail(id, emailAddress,displayName,emailDomain) values(?, ?, ?, ?)";
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(sql);
            for(FindPeopleModel findPeopleModel : findPeopleModelList){
                preparedStatement.setString(1, findPeopleModel.getId());
                preparedStatement.setString(2, findPeopleModel.getEmailAddress());
                preparedStatement.setString(3, findPeopleModel.getDisplayName());
                preparedStatement.setString(4, findPeopleModel.getEmailDomain());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if( connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
