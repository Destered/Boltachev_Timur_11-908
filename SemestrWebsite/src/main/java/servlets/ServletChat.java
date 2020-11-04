package servlets;

import models.Chat;
import models.User;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServletChat extends HttpServlet {
    Helper helper = new Helper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null) {
            Map<String, Object> root = new HashMap<>();
            root.put("isLogged",true);
            helper.render(req,resp,"chat.ftl",root);
        }
        else {
            resp.sendRedirect("/login");
        }
    }
}
