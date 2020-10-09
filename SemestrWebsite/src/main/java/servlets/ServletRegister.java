package servlets;

import models.User;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class ServletRegister extends HttpServlet {
    UserService us = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null) resp.sendRedirect("/profile");
        else req.getRequestDispatcher("/html/register.html").include(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
            RequestDispatcher rd = req.getRequestDispatcher("/html/register.html");
            rd.include(req, resp);
            writer.write("Has empty field!");
        }else {
            User registerUser = new User(username, password, email);
            if (us.addUser(registerUser)) {
                HttpSession session = req.getSession();
                session.setAttribute("user",registerUser);
                session.setMaxInactiveInterval(-1);
                resp.sendRedirect("/profile");
            } else {
                resp.sendRedirect("/register");
            }
        }

    }
}
