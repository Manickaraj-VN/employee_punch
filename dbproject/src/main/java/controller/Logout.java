package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Punch;
import model.Punchdetails;

@WebServlet("/get")
public class Logout extends HttpServlet {
    ArrayList<Punch> Punch1 = new ArrayList<Punch>();
    ArrayList<Punchdetails> Punchdetails1 = new ArrayList<Punchdetails>();
    

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Punchdetails1.clear();
        String reqData = req.getReader().lines().collect(Collectors.joining());
        Punchdetails login = new Gson().fromJson(reqData, Punchdetails.class);

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_punch", "root", "")) {
            String qry = "select * from employee_details where emp_id=?";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, login.getEmp_id());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int emp_id = rs.getInt("emp_id");
                String f_name = rs.getString("f_name");
                String l_name = rs.getString("l_name");
                String email = rs.getString("email");
                long phone_number = rs.getLong("phone_number");
                String hire_date = rs.getString("hire_date");
                String desgination = rs.getString("desgination");
                int salary = rs.getInt("salary");
                int manager_id = rs.getInt("manager_id");
                int department_id = rs.getInt("department_id");
                int location = rs.getInt("location");

                Punchdetails1.add(new Punchdetails(emp_id, f_name, l_name, email, phone_number, hire_date, desgination,
                        salary, manager_id, department_id, location));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        String punch = g.toJson(Punchdetails1);
        // resp.setContentType("application/json");
        resp.getWriter().print(punch);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Punch1.clear();
        HttpSession session = req.getSession(false);
        System.out.println("sessionid "+session.getAttribute("id"));
        System.out.println("login passs");
        // String reqdata = req.getReader().lines().collect(Collectors.joining());
        // Punch userid = new Gson().fromJson(reqdata, Punch.class);
        // System.out.println(userid.getEmp_id());
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_punch", "root", "")) {

            String strSelect = "SELECT emp_id, cast(date_time as DATE) AS date, CAST(date_time AS TIME) AS time, device FROM punch_details where emp_id = ?";
            PreparedStatement stmt = con.prepareStatement(strSelect);
            stmt.setInt(1, (Integer)session.getAttribute("id"));

            ResultSet rset = stmt.executeQuery();

            System.out.println("The records selected are:");
            // System.out.println("resultSet: " + rset.next());
            while (rset.next()) { // Repeatedly process each row
                int emp_id = rset.getInt("Emp_id");
                String date = rset.getString("date");
                String time = rset.getString("time");
                String device = rset.getString("device");
                System.out.println(emp_id + "," + date + ", " + time + "," + device);
                Punch1.add(new Punch(emp_id, date, device, time));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String g = new Gson().toJson(Punch1);
        // resp.setContentType("application/json");
        resp.getWriter().print(g);
    }
}
