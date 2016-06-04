package DAL.entities;
import DAL.dao.*;

public class User implements EntityInterface{
    private int user_id;
    private String username;
    private String password;
    private int employeeNumber;
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Employee getEmployee() {
        return new EmployeeDao().select(employeeNumber);
    }
   
}
