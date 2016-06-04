/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import DAL.entities.Employee;
import DAL.entities.Office;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.EmployeeModel;
import model.OfficeModel;

/**
 *
 * @author Ahmed Uhas
 * @E-mail a.ugaas@gmail.com
 * 
 */
@ManagedBean
@SessionScoped

public class EmployeeBean implements Serializable {

    private EmployeeModel employeeModel;
    private Employee employee;
    private String err_msg = "";
    private String success = "";

    @PostConstruct
    public void init() {
        employeeModel = new EmployeeModel();
        employee = new Employee();
    }

    public List<Employee> getEmployees() {
        return employeeModel.getAll();
    }

    public List<Office> getOffices() {
        return new OfficeModel().getAll();
    }//

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Employee> getEmployeesByOffice() {
        return employeeModel.getEmployeesByOffice(employee.getOfficeCode());
    }

    // POST:
    public String add() {
        employeeModel = new EmployeeModel();
        boolean result = employeeModel.add(employee);
        String view = (result ? "Employees" : "#");
        defineMessage(result);
        employee = new Employee();
        return view;

    }// end of addEmployee

    // GET:
    public String edit(Employee emp) {
        this.setEmployee(emp);
        return (employee == null ? "#" : "editEmployee");
    }

    // POST:
    public String edit() {
        employeeModel = new EmployeeModel();
        boolean result = employeeModel.edit(employee);
        String view = (result ? "Employees" : "#");
        defineMessage(result);
        return view;
    }// end of editEmployee

    public void remove(String pk) {
        boolean result = employeeModel.remove(Integer.parseInt(pk));
        defineMessage(result);
    }// end of delete employee

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    private void defineMessage(boolean result) {
        if (result)
            success = employeeModel.getErr_msg();
        else
            err_msg = employeeModel.getErr_msg();
    }
}// end of Managed Bean
