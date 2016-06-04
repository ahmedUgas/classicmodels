package managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.UserModel;
import DAL.entities.User;
import javax.annotation.PostConstruct;

/**
 *
 * @author Ahmed Uhas
 * @E-mail a.ugaas@gmail.com
 *
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    private UserModel userModel;
    private String err_msg;
    private String success;
    public User user;// for test 

//    @PostConstruct
//    public void init() {
//        userModel = new UserModel();
//        user = new User();
//    }
    
    public LoginBean() {
        userModel = new UserModel();
        user = new User();
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String login() {
        String view = "/";
        boolean result = userModel.login(user.getUsername(), user.getPassword());
        if (!result) {
            err_msg = userModel.getErr_msg();
            view = "#";
        } else
            success = userModel.getErr_msg();
        return view;
    }
}
