package controller;

import java.io.IOException;
// import java.io.PrintWriter;

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
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Employeeout;
import model.Punch;
import model.Punchdetails;

@WebServlet("/main")
public class Mainservlet extends HttpServlet {

    Connection con = null;
    static Statement stmt = null;
    static ResultSet rset = null;

    public static void dbConnection() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_punch", "root", "")) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Punchdetails> Punchdetails1 = new ArrayList<Punchdetails>();
    ArrayList<Punch> Punch1 = new ArrayList<Punch>();
    ArrayList<Employeeout> empout = new ArrayList<Employeeout>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // resp.setContentType("text/html");
        // PrintWriter out = resp.getWriter();
        // req.getParameter("fun");

        String action = req.getServletPath();
        // String action1 = req.getQueryString();

        System.out.println(action);

        try{
            switch(action)
            {
                case "/check":
                punchView(req, req);
                break;
                case "/run":
                viewEmployee(resp, resp);
                break;
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    public void punchView(HttpServletRequest req, HttpServletRequest resp) throws IOException {
        dbConnection();
        try {
            Punch1.clear();
            stmt = con.createStatement();
            String strSelect = "SELECT  emp_id,cast(date_time as DATE) AS date,CAST(date_time AS TIME) AS time,device FROM punch_details";
            System.out.println("The SQL statement is: " + strSelect + "\n");
            rset = stmt.executeQuery(strSelect);
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
        ((ServletResponse) resp).getWriter().print(punch);
    }

    public void viewEmployee(HttpServletResponse req, HttpServletResponse resp) throws IOException {
        dbConnection();
        ArrayList<Punchdetails> pd = new ArrayList<>();
        pd.clear();
        try {

            stmt = con.createStatement();
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
                pd.add(new Punchdetails(emp_id, f_name, l_name, email, phone_number, hire_date, desgination, salary,
                        manager_id, department_id, department_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        String punch = g.toJson(pd);
        // resp.setContentType("application/json");
        resp.getWriter().print(punch);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // resp.setContentType("text/html");
        // PrintWriter out = resp.getWriter();
        System.out.println(req.getParameter("fun"));
        System.out.println(req.getQueryString());
        System.out.println(req.getServletPath());
        if (req.getParameter("fun") == "login") {
            System.out.println("lo");
            login(req,resp);
        }

    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("before db");
        dbConnection();
        System.err.println("login method");
        try {
            String reqData = req.getReader().lines().collect(Collectors.joining());
            Punchdetails login = new Gson().fromJson(reqData, Punchdetails.class);

            String qry = "select department_id,emp_id from employee_details where email=? and f_name = ?";
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
                    session.setAttribute("uname", rset.getInt("emp_id"));
                    Gson g = new Gson();
                    String session1 = g.toJson(empout);
                    // resp.setContentType("application/json");
                    ((ServletResponse) resp).getWriter().print(session1);
                } else {
                    empout.clear();
                    empout.add(new Employeeout(rset.getInt("emp_id"), login.getF_name(), "wel.html"));

                    HttpSession session = req.getSession();
                    session.setAttribute("uname", rset.getInt("emp_id"));
                    Gson g = new Gson();
                    String session1 = g.toJson(empout);
                    // resp.setContentType("application/json");
                    ((ServletResponse) resp).getWriter().print(session1);
                }
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, (ServletResponse) resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
