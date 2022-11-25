package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import model.Punchdetails;

@WebServlet("/pass")
public class Servlet2 extends HttpServlet {

    ArrayList<Punchdetails> pd = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("pass servlet");
        pd.clear();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_punch", "root", "")) {
            Statement stmt = con.createStatement();
            String str = "SELECT emp_id,f_name,l_name,email,phone_number,hire_date,desgination,salary,manager_id,department_id,location from employee_details";
            ResultSet rs = stmt.executeQuery(str);
            System.out.println("check " + rs.next());

            while (rs.next()) {
                int emp_id = rs.getInt("emp_id");
                String f_name = rs.getString("f_name");
                String l_name = rs.getString("l_name");
                String email = rs.getString("email");
                Long phone_number = rs.getLong("phone_number");
                String hire_date = rs.getString("hire_date");
                String desgination = rs.getString("desgination");
                int salary = rs.getInt("salary");
                int manager_id = rs.getInt("manager_id");
                int department_id = rs.getInt("department_id");
                pd.add(new Punchdetails(emp_id, f_name, l_name, email, phone_number, hire_date, desgination, salary, manager_id, department_id, department_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        String punch = g.toJson(pd);
        // resp.setContentType("application/json");
        resp.getWriter().print(punch);
    }

    

}
