package model;

import DAL.dao.EmployeeDao;
import DAL.dao.OfficeDao;
import DAL.entities.Employee;
import java.util.List;
import model.validators.EmployeeValidator;

public class EmployeeModel {

    private final EmployeeDao employeeDao;
    private String err_msg;

    public EmployeeModel() {
        employeeDao = new EmployeeDao();
    }

    public List<Employee> getAll() {
        return new EmployeeDao().select();
    }// end of getAll

    public Employee getByID(int pk) {
        return employeeDao.select(pk);
    }// end of getByID

    public boolean add(Employee employee) {
        if (!EmployeeValidator.validate(employee)) {
            err_msg = EmployeeValidator.getErr_msg();
            return false;
        }

        if (new OfficeDao().select(employee.getOfficeCode()) == null) {
            err_msg = "The office code that you entered do not exist in the database";
            return false;
        }
        if (employeeDao.select(employee.getReportsTo()) == null) {
            err_msg = "The supervisor Number that you entered do not exist in the database";
            return false;
        }

        int lastEmployeeNumber;
        List<Employee> employees = getAll();
        lastEmployeeNumber = (employees.isEmpty() ? 0 : (employees.get(employees.size() - 1).getEmployeeNumber()));
        employee.setEmployeeNumber(++lastEmployeeNumber);
        int rows_affectede = employeeDao.insert(employee);
        if (rows_affectede == -1) {
            err_msg = "Error! something went wrong";
            return false;
        } else if (rows_affectede == 0) {
            err_msg = "Error, 0 records affected, please try again later";
            return false;
        }
        err_msg = "(" + rows_affectede + ")s affected, Operation success";
        return true;
    }// end of addEmployee

    public boolean edit(Employee employee) {
        if (!EmployeeValidator.validateEdit(employee)) {
            err_msg += EmployeeValidator.getErr_msg();
            return false;
        }
        if (employeeDao.select(employee.getEmployeeNumber()) == null) {
            err_msg = " there is no record with this employee Number";
            return false;
        }
        if (new OfficeDao().select(employee.getOfficeCode()) == null) {
            err_msg = "The office code that you entered do not exist in the database";
            return false;
        }
        if (employeeDao.select(employee.getReportsTo()) == null) {
            err_msg = "The supervisor Number that you entered do not exist in the database";
            return false;
        }
        int rows_affectede = employeeDao.update(employee);
        if (rows_affectede == -1) {
            err_msg = "Error! something went wrong";
            return false;
        } else if (rows_affectede == 0) {
            err_msg = "Error, 0 records affected, please try again later";
            return false;
        }
        err_msg = "(" + rows_affectede + ")s affected, Operation success";
        return true;
    }// end of editEmployee

    public boolean remove(int pk) {
        Employee employee = this.getByID(pk);
        if (employee == null) {
            err_msg = "Please Enter a valid Employee Number";
            return false;
        } else if (employee.getSubordinate().size() > 0 || employee.getCustomers().size() > 0) {
            err_msg = "This record has dependencies, remove dependencies first.";
            return false;
        }
        int rows_affectede = employeeDao.delete(employee.getEmployeeNumber());
        if (rows_affectede == -1) {
            err_msg = "Error! something went wrong";
            return false;
        }
        err_msg = "(" + rows_affectede + ") record(s) affected, Operation success";
        return true;
    }// end of delete employee

    public boolean hasDependencies(int pk) {
        return (employeeDao.select(pk).getSubordinate() != null);
    }// end of test method

    public List<Employee> getEmployeesByOffice(String officeCode) {
        return employeeDao.selectOfficeCode(officeCode);
//        return getAll().stream().filter(emp -> emp.getOfficeCode() == null
//                ? null
//                : emp.getOfficeCode().equals(officeCode))
//                .collect(Collectors.toList());
    }

    public String getErr_msg() {
        return err_msg;
    }

}// end of EmployeeModel
