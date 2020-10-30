package servlets;

import models.User;
import repositories.UsersDaoImpl;
import services.Helper;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServletEditUser extends HttpServlet {
    Helper helper = new Helper();
    UserService us = new UserService();
    UsersDaoImpl usersDao = new UsersDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null) {
            Map<String, Object> root = new HashMap<>();
            root.put("isLogged",true);
            helper.render(req,resp,"aboutuser.ftl",root);
        }
        else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        String firstName = req.getParameter("firstname");
        String secondName = req.getParameter("secondname");
        String about = req.getParameter("about");
        if(firstName != null){
            user.setFirstName(firstName);
        }
        if(secondName != null){
            user.setSecondName(secondName);
        }
        if(about != null){
            user.setAbout(about);
        }
        usersDao.update(user);
        session.setAttribute("user",user);
        resp.sendRedirect("/profile");
    }
}
