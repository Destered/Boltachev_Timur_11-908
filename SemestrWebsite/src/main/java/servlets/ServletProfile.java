package servlets;

import models.User;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ServletProfile extends HttpServlet {
    Helper helper = new Helper();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            Map<String, Object> root = new HashMap<>();
            root.put("username",user.getUsername());
            root.put("email",user.getEmail());
            helper.render(req,resp,"profile.ftl",root);
        }else resp.sendRedirect("/login");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        session.invalidate();
        Cookie userCookie = new Cookie("SiteAuthUser",null);
        userCookie.setMaxAge(0);
        resp.addCookie(userCookie);
        resp.sendRedirect("/login");
    }


}
