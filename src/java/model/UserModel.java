package model;

import DAL.dao.UserDao;
import DAL.entities.User;

/**
 *
 * @author Ahmed Uhas
 * @E-mail a.ugaas@gmail.com
 *
 */
public class UserModel {

    private String err_msg;
    private UserDao userDao;
    private User user;

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public boolean login(String username, String password) {
        userDao = new UserDao();
        user = userDao.login(username, password);

        if (user != null) {
            err_msg = "Welcone Mr." + user.getEmployee().getLastName();
            return true;
        } else {
            err_msg = "Login Failed, Please check your username or password";
            return false;
        }
    }

}
