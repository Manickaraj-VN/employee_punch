package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home")
public class Home extends HttpServlet {
    {
        System.out.println("home");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pr = resp.getWriter();
        HttpSession session = req.getSession(false);

        if (session != null) {
            String name = (String) session.getAttribute("uname");
            pr.print("Welcome " + name);
            pr.print("<br/><a href=\"logout\">Logout</a>");
            // resp.sendRedirect("wel.jsp");
        } else {
            resp.sendRedirect("index.jsp");
        }
        pr.close();
    }
}
