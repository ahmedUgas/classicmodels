package DAL.entities;

import DAL.dao.CustomerDao;
import java.util.List;
import DAL.dao.EmployeeDao;
import DAL.dao.OfficeDao;
import java.util.stream.Collectors;

public class Employee implements EntityInterface {

    private int employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String officeCode;// fk
    private int reportsTo;// recursive fk
    private String jobTitle;

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public int getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(int reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<Employee> getSubordinate() {
        return new EmployeeDao().selectByReportsTo(employeeNumber);
    }// end of get subordinate

    public List<Customer> getCustomers() {
        return new CustomerDao().select().stream().filter(cust -> cust.getSalesRepEmployeeNumber() == employeeNumber).collect(Collectors.toList());
    }// end of get Customers

    public Employee getSupervisor() {
        return new EmployeeDao().select(reportsTo);
    }// end of get subordinate

    public Office getOffice() {
        return new OfficeDao().select(officeCode);
    }// end of getOffice

}// end of Class
