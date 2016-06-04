package DAL.dao;

import DAL.dbconnection.DatabaseConnection;
import DAL.entities.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements DaoInterface<Employee> {

    private Connection conn;

    private final String selectQuery = "select * from employees";
    private final String deleteQuery = "delete from employees";

    private String insertQuery = "INSERT INTO employees( employeeNumber , lastName , "
            + "firstName , extension , "
            + "email , officeCode , reportsTo , jobTitle )"
            + "VALUES(";
    private String updateQuery = "UPDATE employees "
            + "SET ";

    public EmployeeDao() {
        try {
            conn = DatabaseConnection.createConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }// end of constructor

//    public List<Employee> select() {
//        List<Employee> employees = new ArrayList<>();
//        ResultSet rs;
//        try {
//            
//            rs = DatabaseConnection.select(selectQuery);
//            while (rs.next()) {
//                Employee emp = new Employee();
//                rsToEmp(rs, emp);
//                employees.add(emp);
//            }// end of while loop
//            rs.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }// end of try 
//        return employees;
//    }// end of select
    /**
     *
     * @return
     */
    @Override
    public List<Employee> select() {
        List<Employee> employees = new ArrayList<>();
        ResultSet rs;
        try (PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                rsToEmp(rs, emp);
                employees.add(emp);
            }// end of while loop
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }// end of try 
        return employees;
    }// end of select

    @Override
    public Employee select(int pk) {

        Employee emp = null;
        ResultSet rs;
        try (PreparedStatement pstmt = conn.prepareStatement(selectQuery + " where employeeNumber = " + pk)) {
            rs = pstmt.executeQuery();
            if (rs.next()) {
                emp = new Employee();
                rsToEmp(rs, emp);
            }// end of if
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }// end of try 
        return emp;
    }// end of select

    public List<Employee> selectByReportsTo(int reportsTo) {

        List<Employee> employees = new ArrayList<>();
        ResultSet rs;
        try (PreparedStatement pstmt = conn.prepareStatement(selectQuery + " where reportsTo = " + reportsTo)) {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                rsToEmp(rs, emp);
                employees.add(emp);
            }// end of while loop
            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }// end of try 
        return employees;
    }// end of selectByReportsTo

    public List<Employee> selectOfficeCode(String officeCode) {

        List<Employee> employees = new ArrayList<>();
        ResultSet rs;
        try (PreparedStatement pstmt = conn.prepareStatement(selectQuery + " where officeCode = " + officeCode)) {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                rsToEmp(rs, emp);
                employees.add(emp);
            }// end of while loop
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }// end of try 
        return employees;
    }// end of selectByReportsTo

    public int insert(Employee entity) {
        //employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle
        insertQuery
                += entity.getEmployeeNumber() + ",'"
                + entity.getLastName() + "','"
                + entity.getFirstName() + "','"
                + entity.getExtension() + "','"
                + entity.getEmail() + "','"
                + entity.getOfficeCode() + "',"
                + entity.getReportsTo() + ",'"
                + entity.getJobTitle() + "');";
        int rows_affeted;
        try (Statement stmt = conn.createStatement()) {
            rows_affeted = stmt.executeUpdate(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }// end of try 
        return rows_affeted;
    }//end of insert

    public int update(Employee entity) {
        //employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle
        updateQuery += "lastName ='" + entity.getLastName() + "', "
                + "firstName = '" + entity.getFirstName() + "', "
                + "extension = '" + entity.getExtension() + "', "
                + "email = '" + entity.getEmail() + "', "
                + "officeCode = '" + entity.getOfficeCode() + "', "
                + "reportsTo = " + entity.getReportsTo() + ", "
                + "jobTitle = '" + entity.getJobTitle() + "' "
                + "where employeeNumber = " + entity.getEmployeeNumber() + ";";

        int rows_affeted;
        try (Statement stmt = conn.createStatement()) {
            rows_affeted = stmt.executeUpdate(updateQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }// end of try 
        return rows_affeted;
    }//end of update

    public int delete(int pk) {
        int rows_affeted;
        try (Statement stmt = conn.createStatement()) {
            rows_affeted = stmt.executeUpdate(deleteQuery + " where employeeNumber = " + pk);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }// end of try 
        return rows_affeted;
    }// end of delete

    public Employee rsToEmp(ResultSet rs, Employee emp) throws SQLException {
        emp.setEmployeeNumber(rs.getInt("employeeNumber"));
        emp.setLastName(rs.getString("lastName"));
        emp.setFirstName(rs.getString("firstName"));
        emp.setExtension(rs.getString("extension"));
        emp.setEmail(rs.getString("email"));
        emp.setOfficeCode(rs.getString("officeCode"));
        emp.setReportsTo(rs.getInt("reportsTo"));
        emp.setJobTitle(rs.getString("jobTitle"));
        return emp;
    }// end of method

}// end of class
