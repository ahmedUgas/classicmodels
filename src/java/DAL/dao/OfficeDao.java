package DAL.dao;

import DAL.dbconnection.DatabaseConnection;
import DAL.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfficeDao {

    private Connection conn;

    private final String selectQuery = "select * from offices";
    private final String deleteQuery = "delete from offices";
    private String insertQuery = "INSERT INTO offices(officeCode, city, phone, addressLine1,"
            + " addressLine2, state, country, postalCode, territory)"
            + "VALUES('";
    private String updateQuery = "UPDATE offices SET ";
    public OfficeDao() {
        try {
            conn = DatabaseConnection.createConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }// end of constructor 

    public List<Office> select() {// this method selects all the offices table content
        List<Office> offices = new ArrayList<>();
        ResultSet rs;
        try (PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Office office = new Office();
                rsToOffice(rs, office);
                offices.add(office);
            }// end of while loop
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }// end of try 
        return offices;
    }// end of select 

    public Office select(String pk) {// this method selects a row from table content by given pk
        Office office = null;
        ResultSet rs;
        try (PreparedStatement pstmt = conn.prepareStatement(selectQuery + " where officeCode = " + pk)) {
            rs = pstmt.executeQuery();
            //selectQuery + " where officeCode = " + pk
            if (rs.next()) {
                office = new Office();
                rsToOffice(rs, office);
            }// end of if
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }// end of try 
        return office;
    }// end of select 

    public int insert(Office office) {
        //officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory
        insertQuery += office.getOfficeCode() + "','"
                + office.getCity() + "','"
                + office.getPhone() + "','"
                + office.getAddressLine1() + "','"
                + office.getAddressLine2() + "','"
                + office.getState() + "','"
                + office.getCountry() + "','"
                + office.getPostalCode() + "','"
                + office.getTerritory() + "');";
        int rows_affeted;
        try (Statement stmt = conn.createStatement()) {
            rows_affeted = stmt.executeUpdate(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }// end of try 
        return rows_affeted;
    }// end of insert method 

    public int update(Office office) {

        updateQuery
                += "city = '" + office.getCity() + "', "
                + "phone = '" + office.getPhone() + "', "
                + "addressLine1 = '" + office.getAddressLine1() + "', "
                + "addressLine2 = '" + office.getAddressLine2() + "', "
                + "state = '" + office.getState() + "', "
                + "country = '" + office.getCountry() + "', "
                + "postalCode = '" + office.getPostalCode() + "', "
                + "territory = '" + office.getTerritory() + "' "
                + "WHERE officeCode = '" + office.getOfficeCode() + "';";
        int rows_affeted;
        try (Statement stmt = conn.createStatement()) {
            rows_affeted = stmt.executeUpdate(updateQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }// end of try 
        return rows_affeted;
    }// end of update method 

    public int delete(String officeCode) {
        int rows_affeted;
        try (Statement stmt = conn.createStatement()) {
            rows_affeted = stmt.executeUpdate(deleteQuery + " where officeCode = " + officeCode);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }// end of try 
        return rows_affeted;
    }// end of delete

    private Office rsToOffice(ResultSet rs, Office office) throws SQLException {
        office.setOfficeCode(rs.getString("officeCode"));
        office.setCity(rs.getString("city"));
        office.setPhone(rs.getString("phone"));
        office.setAddressLine1(rs.getString("addressLine1"));
        office.setAddressLine2(rs.getString("addressLine2"));
        office.setState(rs.getString("state"));
        office.setCountry(rs.getString("country"));
        office.setPostalCode(rs.getString("postalCode"));
        office.setTerritory(rs.getString("territory"));
        return office;
    }// end of rsToOffice
}// end of class
