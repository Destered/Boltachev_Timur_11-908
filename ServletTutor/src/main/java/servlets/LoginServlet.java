package servlets;

import services.Helper;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    LoginService loginServices = new LoginService();
    Helper helper = new Helper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").replaceAll("\"","");
        String password = req.getParameter("password").replaceAll("\"","");
        if(loginServices.login(username,password)){
            Map<String, Object> root = new HashMap<>();
            root.put("username",username);
            helper.render(req,resp,"profile.ftl",root);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
