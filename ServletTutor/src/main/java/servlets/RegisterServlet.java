package servlets;

import models.User;
import services.Helper;
import services.RegisterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    RegisterService registerServices = new RegisterService();
    Helper helper = new Helper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/html/register.html");
        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        User registerUser = new User(username,password,gender);
        boolean result = registerServices.register(registerUser);
        Map<String, Object> root = new HashMap<>();
        if(result){
            root.put("username",username);
            helper.render(req,resp,"profile.ftl",root);
        }
        else{
            RequestDispatcher rd = req.getRequestDispatcher("/html/register.html");
            rd.include(req, resp);
            writer.write("Already has user!");
        }

    }
}
