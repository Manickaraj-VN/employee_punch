package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Employeeout;
import model.Punch;
import model.Punchdetails;

@WebServlet("/login")
public class Servlet extends HttpServlet {
    {
        System.out.println("logeed");
    }
    ArrayList<Punchdetails> Punchdetails1 = new ArrayList<Punchdetails>();
    ArrayList<Punch> Punch1 = new ArrayList<Punch>();
    ArrayList<Employeeout> empout = new ArrayList<Employeeout>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post Method called");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_punch", "root", "")) {
            Punch1.clear();
            Statement stmt = con.createStatement();
            String strSelect = "SELECT  emp_id,cast(date_time as DATE) AS date,CAST(date_time AS TIME) AS time,device FROM punch_details";
            System.out.println("The SQL statement is: " + strSelect + "\n");
            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.println("The records selected are:");
            System.out.println("resultSet: " + rset.next());

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
        Gson g = new Gson();
        String punch = g.toJson(Punch1);
        // resp.setContentType("application/json");
        resp.getWriter().print(punch);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String reqData = req.getReader().lines().collect(Collectors.joining());
        Punchdetails login = new Gson().fromJson(reqData, Punchdetails.class);

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_punch", "root", "")) {
            String qry = "select f_name,department_id,emp_id from employee_details where email=? and f_name = ?";
            PreparedStatement prestmt = con.prepareStatement(qry);
            prestmt.setString(1, login.getEmail());
            prestmt.setString(2, login.getF_name());
            ResultSet rset = prestmt.executeQuery();
            System.out.println(login.getEmail() + " " + login.getF_name());

            if (rset.next()) {
                int dept_id = rset.getInt("department_id");
                if (dept_id == 30) {
                    empout.clear();
                    empout.add(new Employeeout(rset.getInt("emp_id"), login.getF_name(), "admin.html"));

                    System.out.println(rset.getInt("emp_id"));
                    HttpSession session = req.getSession();
                    session.setAttribute("id", rset.getInt("emp_id"));
                    Gson g = new Gson();
                    String session1 = g.toJson(empout);
                    // resp.setContentType("application/json");
                    resp.getWriter().print(session1);
                } else {
                    empout.clear();
                    empout.add(new Employeeout(rset.getInt("emp_id"), rset.getString("f_name"), "wel.html"));

                    HttpSession session = req.getSession();
                    session.setAttribute("id", rset.getInt("emp_id"));
                    Gson g = new Gson();
                    String session1 = g.toJson(empout);
                    // resp.setContentType("application/json");
                    resp.getWriter().print(session1);
                }
            } else {
                out.print("sorry wrong uname /pass");
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
