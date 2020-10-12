package servlets;

import models.User;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null) resp.sendRedirect("/profile");
        else req.getRequestDispatcher("/html/login.html").include(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        UserService us = new UserService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("remember");
        if(username.isEmpty() || password.isEmpty()) {
            RequestDispatcher rd = req.getRequestDispatcher("/html/register.html");
            rd.include(req, resp);
            writer.write("Has empty field!");
        }else{
            User loginUser = new User(username,password);
            if(us.login(loginUser)){
                if(rememberMe != null){
                    Cookie userCookie = new Cookie("SiteAuthUser",username);
                    userCookie.setMaxAge(-1);
                    resp.addCookie(userCookie);
                }
                session.setAttribute("user",us.findUserByUsername(loginUser.getUsername()).get());
                session.setMaxInactiveInterval(20);
                resp.sendRedirect("/profile");
            }else{
                resp.sendRedirect("/login");
            }
        }
    }
}
