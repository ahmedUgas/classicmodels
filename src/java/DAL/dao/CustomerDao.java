package DAL.dao;

import DAL.dbconnection.DatabaseConnection;
import DAL.entities.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements DaoInterface<Customer> {

    private List<Customer> customers;

    private Connection conn;

    private final String selectQuery = "select * from customers";
    private final String deleteQuery = "delete from customers";

    private String insertQuery = "INSERT INTO customers"
            + "(customerNumber,customerName,contactLastName,"
            + "contactFirstName,phone,addressLine1,addressLine2,"
            + "city,state,postalCode,country,salesRepEmployeeNumber,"
            + "creditLimit)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String updateQuery = "UPDATE classicmodels.customers"
            + "SET"
            + "customerNumber =?,"
            + "customerName = ?,"
            + "contactLastName = ?,"
            + "contactFirstName = ?,"
            + "phone = ?,"
            + "addressLine1 = ?,"
            + "addressLine2 = ?,"
            + "city = ?,"
            + "state = ?,"
            + "postalCode = ?,"
            + "country = ?,"
            + "salesRepEmployeeNumber = ?,"
            + "creditLimit = ?"
            + "WHERE customerNumber = ?;";

    public CustomerDao() {
        try {
            conn = DatabaseConnection.createConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Customer> select() {
        customers = new ArrayList<>();
        ResultSet rs;
        try (PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Customer cust = new Customer();
                cust.setCustomerNumber(rs.getInt("customerNumber"));
                cust.setCustomerName(rs.getString("customerName"));
                cust.setContactLastName(rs.getString("contactLastName"));
                cust.setContactFirstName(rs.getString("contactFirstName"));
                cust.setPhone(rs.getString("phone"));
                cust.setAddressLine1(rs.getString("addressLine1"));
                cust.setAddressLine2(rs.getString("addressLine2"));
                cust.setCity(rs.getString("city"));
                cust.setState(rs.getString("state"));
                cust.setCountry(rs.getString("country"));
                cust.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                cust.setCreditLimit(rs.getDouble("creditLimit"));
                customers.add(cust);
            }// end of while loop
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }// end of try 

        return customers;
    }

    @Override
    public Customer select(int pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Customer entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Customer entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
