package DAL.dao;

import DAL.dbconnection.DatabaseConnection;
import DAL.entities.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements DaoInterface<User> {

    private Connection conn;

    private final String selectQuery = "select * from users";
    private final String deleteQuery = "delete from users";

    private String insertQuery = "INSERT INTO users( username , password, employeeNumber ) values(";
    private String updateQuery = "UPDATE users SET ";

    public UserDao() {
        try {
            conn = DatabaseConnection.createConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }// end of constructor

    
    public User login(String username, String password) {
        User user = null;
        ResultSet rs;
        try (PreparedStatement pstmt = conn.prepareStatement(selectQuery + " where username = '" + username + "' and password = '" + password + "'")) {
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUser_id(rs.getInt("user_id"));
                user.setEmployeeNumber(rs.getInt("employeeNumber"));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }// end of try 
        return user;
    }

    @Override
    public List<User> select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User select(int pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
