package servlets;

import models.User;
import org.mindrot.jbcrypt.BCrypt;
import services.Helper;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterServlet extends HttpServlet {
    UserService us = new UserService();
    Helper helper = new Helper();
    String checkEmail= "^(.+)@(.+)$";
    String checkPassword= "[$@_/\\\\]";
    Pattern emailCheck = Pattern.compile(checkEmail,Pattern.CASE_INSENSITIVE);
    Pattern passwordCheck = Pattern.compile(checkPassword,Pattern.CASE_INSENSITIVE);
    String emailAlertScript ="<script> alert('Введите валидную почту!')</script>";
    String passwordAlertScript ="<script> alert('В пароле использованы запрещённые символы!($@_/\\\\)')</script>";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null) resp.sendRedirect("/profile");
        else {
                Map<String, Object> root = new HashMap<>();
                root.put("isLogged",false);
                helper.render(req,resp,"register.ftl",root);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        String username = req.getParameter("username");
        String password = BCrypt.hashpw(req.getParameter("password"),BCrypt.gensalt());
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        Matcher emailMatcher = emailCheck.matcher(email);
        Matcher passwordMatcher = passwordCheck.matcher(req.getParameter("password"));
        if(username.isEmpty() || password.isEmpty() || email.isEmpty() || firstName.isEmpty() || secondName.isEmpty()){
            Map<String, Object> root = new HashMap<>();
            root.put("isLogged",false);
            helper.render(req,resp,"register.ftl",root);
        }else if(!emailMatcher.find()){
            Map<String, Object> root = new HashMap<>();
            root.put("isLogged",false);
            helper.render(req,resp,"register.ftl",root);
            writer.write(emailAlertScript);
        }else if(passwordMatcher.find()){
            Map<String, Object> root = new HashMap<>();
            root.put("isLogged",false);
            helper.render(req,resp,"register.ftl",root);
            writer.write(passwordAlertScript);
        }
        else {
            User registerUser = new User(username, password, email,firstName,secondName,null);
            if (us.addUser(registerUser)) {
                HttpSession session = req.getSession();
                session.setAttribute("user",us.findUserByUsername(registerUser.getUsername()).get());
                resp.sendRedirect("/profile");
            } else {
                resp.sendRedirect("/register");
            }
        }

    }
}
