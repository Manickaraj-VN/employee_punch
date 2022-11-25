package model;

public class Punchdetails {
    int emp_id;
    String f_name;
    String l_name;
    String email;
    long phone_number;
    String hire_date;
    String desgination;
    int salary;
    int manager_id;
    int department_id;
    int location;

    public Punchdetails(int emp_id, String f_name, String l_name, String email, long phone_number, String hire_date,
            String desgination, int salary, int manager_id, int department_id, int location) {
        this.emp_id = emp_id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.phone_number = phone_number;
        this.hire_date = hire_date;
        this.desgination = desgination;
        this.salary = salary;
        this.manager_id = manager_id;
        this.department_id = department_id;
        this.location = location;
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

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public String getDesgination() {
        return desgination;
    }

    public void setDesgination(String desgination) {
        this.desgination = desgination;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public static void clear() {
    }

    public static void add(Punchdetails punchdetails) {
    }

    
}
