package model.validators;

import DAL.entities.Employee;
import utils.UtilityClass;

public class EmployeeValidator {

    private static String err_msg = "";

    public static String getErr_msg() {
        return err_msg;
    }// end of get error message

    public static int validateEmpNumAsString(String pk) {
        if (pk == null || UtilityClass.stringIsEmpy(pk) || !UtilityClass.isNumeric(pk))
            return 0;
        else
            return Integer.parseInt(pk);
    }// end of validateEdit()

    public static boolean validateEdit(Employee employee) {
        err_msg = (employee.getEmployeeNumber() == 0) ? "the employee Number should be greater than 0 and not be empty !"  : "";

        if ((employee.getEmployeeNumber() == 0)) {
            err_msg = "the employee Number should be greater than 0 and not be empty !";
            return false;
        }
        return validate(employee);
    }// end of validateEdit()

    public static boolean validate(Employee employee) {
        //lastName, firstName, extension, email, officeCode, reportsTo, jobTitle

        if (UtilityClass.stringIsEmpy(employee.getFirstName())) {
            err_msg = "the first Name should not be empty !";
            return false;
        }
        if (UtilityClass.stringIsEmpy(employee.getLastName())) {
            err_msg = "the last name should not be empty !";
            return false;
        }
        if (UtilityClass.stringIsEmpy(employee.getExtension())) {
            err_msg = "the extension should not be empty !";
            return false;
        }
        if (UtilityClass.stringIsEmpy(employee.getEmail())) {
            err_msg = "the email should not be empty !";
            return false;
        }
        if (UtilityClass.stringIsEmpy(employee.getOfficeCode())) {
            err_msg = "the Office Code code should not be empty !";
            return false;
        }
        if (UtilityClass.stringIsEmpy(employee.getJobTitle())) {
            err_msg = "the Job Title code should not be empty !";
            return false;
        }
        return true;
    }// end of validate     
}// end of class

