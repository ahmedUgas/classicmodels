package DAL.dbconnection;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;

public class DatabaseConnection {

    public static Connection conn;
    private static String host, dbName,
            username, password;

    private static void setHostInfo(String host, String dbName,
            String username, String password) {
        DatabaseConnection.host = host;
        DatabaseConnection.dbName = dbName;
        DatabaseConnection.username = username;
        DatabaseConnection.password = password;
    }// test method 

    public static Connection createConnection()
            throws ClassNotFoundException, SQLException {
//        host = "localhost";
//        dbName = "classicmodels";
//        username = "root";
//        password = "root123";
        setHostInfo("localhost", "classicmodels", "root", "root123");
        if (conn == null || conn.isClosed()) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName(host);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setDatabaseName(dbName);
            conn = dataSource.getConnection();
        }// end of if 
        return conn;
    }// end of createConnection

    public static ResultSet select(String query) {
        ResultSet rs;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            rs = pstmt.executeQuery();
            rs.close();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }// end of try
    }
}// end of class

