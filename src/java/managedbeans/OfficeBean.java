package managedbeans;

import DAL.entities.Office;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.OfficeModel;

@ManagedBean
@SessionScoped
public class OfficeBean {

    private Office office;
    private String err_msg;
    private final OfficeModel officeModel;

    public OfficeBean() {
        officeModel = new OfficeModel();
    }


    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public List<Office> getOffices() {
        return officeModel.getAll();
    }
}
