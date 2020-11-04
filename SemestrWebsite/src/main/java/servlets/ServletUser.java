package servlets;

import models.User;
import services.Helper;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/user")
public class ServletUser extends HttpServlet {
    Helper helper = new Helper();
    UserService us = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String userId = req.getParameter("user_id");
        Optional<User> reqUser = us.findUserById(Integer.parseInt(userId));
        if(reqUser.isPresent()){
            User user = reqUser.get();
            Map<String, Object> root = new HashMap<>();
            root.put("pageOverlord",false);
            root.put("email",user.getEmail());
            root.put("firstname",user.getFirstName());
            root.put("secondname",user.getSecondName());
            root.put("username",user.getUsername());
            root.put("imagepath",user.getImagePath());
            root.put("about",user.getAbout());
            if(session.getAttribute("user") != null){
                root.put("isLogged",true);
                helper.render(req,resp,"profile.ftl",root);
            }else{
                root.put("isLogged",false);
                helper.render(req,resp,"profile.ftl",root);
            }
        }
    }
}
