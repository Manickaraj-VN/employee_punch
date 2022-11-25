package model;

public class Punch {
    int emp_id;
    String date;
    String device;
    String time;

    public Punch(int emp_id, String date, String device, String time) {
        this.emp_id = emp_id;
        this.date = date;
        this.device = device;
        this.time = time;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
