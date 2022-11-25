package model;

public class Employeeout {

    int emp_id;
    String f_name;
    String r_link;

    public Employeeout(int emp_id, String f_name, String r_link) {
        this.emp_id = emp_id;
        this.f_name = f_name;
        this.r_link = r_link;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getR_link() {
        return r_link;
    }

    public void setR_link(String r_link) {
        this.r_link = r_link;
    }

}