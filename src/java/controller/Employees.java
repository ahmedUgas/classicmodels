package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import model.*;
import DAL.entities.*;
import java.util.List;
import model.validators.EmployeeValidator;

public class Employees extends HttpServlet {

    String page;
    String pk = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeModel employeeModel = new EmployeeModel();

        String err_msg;
        if (request.getAttribute("err_msg") == null)
            err_msg = "";
        else
            err_msg = request.getAttribute("err_msg").toString();

        if (request.getParameter("page") == null) {
            if (page == null)
                page = "";
        } else
            page = request.getParameter("page");
        String view = "";

        switch (page) {
            case "add":
                view = "addEmployee.jsp";
                request.setAttribute("offices", new OfficeModel().getAll());
                request.setAttribute("employees", employeeModel.getAll());
                break;

            case "edit":
                if (pk == null)
                    pk = request.getParameter("pk");
                Employee emp = employeeModel.getByID(EmployeeValidator.validateEmpNumAsString(pk));
                if (emp == null) {
                    err_msg = "Error! there is no record in the database with this Number.";
                    page = "";
                } else {
                    request.setAttribute("emp", emp);
                    view = "editEmployee.jsp";
                    request.setAttribute("offices", new OfficeModel().getAll());
                    request.setAttribute("employees", employeeModel.getAll());
                    break;
                }

            default:
                if (page.equals("delete")) {
                    pk = request.getParameter("pk");
                    employeeModel.remove(Integer.parseInt(pk));
                    err_msg = employeeModel.getErr_msg();
                }
                List<Employee> employees = employeeModel.getAll();
                request.setAttribute("employees", employees);
                request.setAttribute("err_msg", err_msg);
                break;
        }// end of switch
        page = null;
        pk = null;
        String url = "/WEB-INF/views/employees/" + view;
        request.getRequestDispatcher(url).forward(request, response);
    }//end of doGet()

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        <!--//employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, -->
        EmployeeModel empModel = new EmployeeModel();
        Employee employee ;
        String frmName = request.getParameter("frmName");
        switch (frmName) {
            case "add":
                employee = new Employee();
                employee.setLastName(request.getParameter("lastName"));
                employee.setFirstName(request.getParameter("firstName"));
                employee.setExtension(request.getParameter("extension"));
                employee.setEmail(request.getParameter("email"));
                employee.setOfficeCode(request.getParameter("officeCode"));
                employee.setReportsTo(EmployeeValidator.validateEmpNumAsString(request.getParameter("reportsTo")));
                employee.setJobTitle(request.getParameter("jobTitle"));
                if (!empModel.add(employee))
                    page = "add";
                else
                    page = "";
                request.setAttribute("err_msg", empModel.getErr_msg());
                break;
            case "edit":
                employee = new Employee();
                employee.setEmployeeNumber(EmployeeValidator.validateEmpNumAsString(request.getParameter("empNo")));
                employee.setLastName(request.getParameter("lastName"));
                employee.setFirstName(request.getParameter("firstName"));
                employee.setExtension(request.getParameter("extension"));
                employee.setEmail(request.getParameter("email"));
                employee.setOfficeCode(request.getParameter("officeCode"));
                employee.setReportsTo(EmployeeValidator.validateEmpNumAsString(request.getParameter("reportsTo")));
                employee.setJobTitle(request.getParameter("jobTitle"));
                if (!empModel.edit(employee))
                    page = "edit";
                else
                    page = "";
                request.setAttribute("err_msg", empModel.getErr_msg());
                break;
        }// end of switch 
//        request.getRequestDispatcher("/Employees").forward(request, response);
        doGet(request, response);
    }// end of doPost
}// end of servlet
