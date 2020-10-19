package servlets;

import models.User;
import services.Helper;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ServletLogin extends HttpServlet {
    Helper helper = new Helper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null) resp.sendRedirect("/profile");
        else {
                Map<String, Object> root = new HashMap<>();
                root.put("isLogged",false);
                helper.render(req,resp,"login.ftl",root);
        }
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
            if(us.login(username,password)){
                if(rememberMe != null){
                    Cookie userCookie = new Cookie("SiteAuthUser",username);
                    userCookie.setMaxAge(-1);
                    resp.addCookie(userCookie);
                }
                session.setAttribute("user",us.findUserByUsername(username).get());
                resp.sendRedirect("/profile");
            }else{
                resp.sendRedirect("/login");
            }
        }
    }
}
