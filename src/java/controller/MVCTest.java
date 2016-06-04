package controller;

import DAL.entities.*;
import java.util.List;
import model.*;
import DAL.entities.User;
import DAL.dao.UserDao;
import managedbeans.LoginBean;

public class MVCTest {

    public static void main(String[] args) {
//        Office office = new Office();
//        OfficeModel officeModel = new OfficeModel();
        //officeCode, city, phone, addressLine1, 
        //addressLine2, state, country, postalCode, territory
//        office.setOfficeCode("123123");
//        office.setCity("cairo");
//        office.setPhone("35151351");
//        office.setAddressLine1("address Line 1");
//        office.setAddressLine2(null);
//        office.setState(null);
//        office.setCountry("Somalia");
//        office.setPostalCode("asdasd");
//        office.setTerritory("terr");
//        System.out.println(officeModel.addOffice(office));
//        System.out.println(officeModel.editOffice(office));
//        System.out.println(officeModel.removeOffice(office));
//
//        String header = String.format("%20s %20s %20s %20s ",
//                "Office Code", "City", "Phone", "Country");
//        System.out.println(header);
//
//        List<Office> offices = officeModel.getAll();
//        offices.stream().forEach((_item) -> {
//            System.out.println(String.format("%20s %20s %20s %20s ",
//                    _item.getOfficeCode(), _item.getCity(), _item.getPhone(), _item.getCountry()));
//        });
        //employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle
//        EmployeeModel employeeModel = new EmployeeModel();
//        Employee employee = new Employee();
//        employee.setEmployeeNumber(1703);
//        employee.setLastName("Hassan");
//        employee.setFirstName("Ahmed");
//        employee.setExtension("s5654");
//        employee.setEmail("samargndi@hotmail.com");
//        employee.setOfficeCode("1");
//        employee.setReportsTo(1002);
//        employee.setJobTitle("Java Developer");
//        System.out.println(employeeModel.remove(employee));
//        String header = String.format("%20s %20s %20s %20s ",
//                "employee Numbere", "Last Name", "First Name", "Job Title");
//        System.out.println(header);
////
//        EmployeeModel employeeModel = new EmployeeModel();
//        List<Employee> employees = employeeModel.getAll();
//        employees.stream().forEach((_item) -> {
//            System.out.println(String.format("%20s %20s %20s %20s ",
//                    _item.getEmployeeNumber(), _item.getLastName(), _item.getFirstName(),
//                    _item.getJobTitle()));
//        });
//        System.out.println("What else you want me to do?");

        User user = new User();
        user.setPassword("admin");
        user.setUsername("admin");
        LoginBean loginBean = new LoginBean();
        loginBean.user = user;
        String temp = loginBean.login();
        System.out.println(temp);
        System.out.println(loginBean.getSuccess() + " " + loginBean.getErr_msg());
    }// main method 

}//
