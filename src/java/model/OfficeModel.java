package model;

import DAL.dao.OfficeDao;
import DAL.entities.Office;
import java.util.List;

public class OfficeModel {

    private String err_msg;
    private OfficeDao officeDao;

    public OfficeModel() {
        this.err_msg = "";
    }

    public List<Office> getAll() {
        return new OfficeDao().select();
    }// end of getAll

    public Office getByID(Office office) {
        return new OfficeDao().select(office.getOfficeCode());
    }// end of getAll

    public boolean add(Office office) {
        if (!validate(office))
            return false;
        if (officeDao.select(office.getOfficeCode()) != null) {
            err_msg = "a record with the same office code exists";
            return false;
        }
        List<Office> offices = getAll();
        String lastOfficeCode = (offices.isEmpty() ? "0" : (offices.get(offices.size() - 1).getOfficeCode()));
        office.setOfficeCode(Integer.toString((Integer.parseInt(lastOfficeCode) + 1)));
        int rows_affectede = officeDao.insert(office);
        err_msg = (rows_affectede > 0 ? "(" + rows_affectede + ") record(s) affected, Operation success" : "error! Operation Failed!");
        return (rows_affectede > 0);
    }// end of addOffice

    public boolean edit(Office office) {
        if (!validate(office))
            return false;
        if (stringIsEmpy(office.getOfficeCode())) {
            err_msg = "the office code should not be empty !";
            return false;
        }
        if (officeDao.select(office.getOfficeCode()) == null) {
            err_msg = " there is no record with this office code";
            return false;
        }

        int rows_affectede = officeDao.update(office);
        err_msg = (rows_affectede > 0 ? "(" + rows_affectede + ") record(s) affected, Operation success" : "error! Operation Failed!");
        return (rows_affectede > 0);
    }// end of editOffice

    public String remove(Office office) {
        officeDao = new OfficeDao();
        int rows_affectede = officeDao.delete(office.getOfficeCode());
        return (rows_affectede > 0 ? "(" + rows_affectede + ") record(s) affected, Operation success" : "error! Operation Failed!");
    }// end of delete office

    private boolean validate(Office office) {

        if (stringIsEmpy(office.getCity())) {
            err_msg += "the city should not be empty !";
            return false;
        }
        if (stringIsEmpy(office.getPhone())) {
            err_msg += "the phone should not be empty !";
            return false;
        }
        if (stringIsEmpy(office.getAddressLine1())) {
            err_msg += "the Address should not be empty !";
            return false;
        }
        if (stringIsEmpy(office.getCountry())) {
            err_msg += "the country should not be empty !";
            return false;
        }
        if (stringIsEmpy(office.getPostalCode())) {
            err_msg += "the postal code should not be empty !";
            return false;
        }
        if (stringIsEmpy(office.getTerritory())) {
            err_msg += "the Territory should not be empty !";
            return false;
        }
        return true;
    }// end of validate

    private boolean stringIsEmpy(String str) {
        return str == null || str.trim().equals("");
    }

}// end of office Model
