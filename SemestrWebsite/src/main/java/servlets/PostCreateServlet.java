package servlets;

import models.Post;
import models.User;
import repositories.PostDaoImpl;
import repositories.UsersDao;
import repositories.UsersDaoImpl;
import services.FileSaverImpl;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@MultipartConfig
public class PostCreateServlet extends HttpServlet {

    private FileSaverImpl fileSaver;
    private UsersDao usersDao;
    private Helper helper;
    private PostDaoImpl postDao = new PostDaoImpl();

    @Override
    public void init() throws ServletException {
        fileSaver = new FileSaverImpl(getServletContext().getRealPath(""));
        usersDao = new UsersDaoImpl();
        helper = new Helper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null){
            Map<String, Object> root = new HashMap<>();
            root.put("isLogged",true);
            helper.render(req,resp,"post_create.ftl",root);
        }else{
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        User userSession = (User)session.getAttribute("user");
        Part photoPart = req.getPart("photo");
        int author = userSession.getId();
        Post post = new Post();
        Optional<String> resultOfFileSaving = fileSaver.saveFile(photoPart, userSession.getId()+"\\post");
        if (resultOfFileSaving.isPresent()) {
                String postPhoto = resultOfFileSaving.get();
                String title = req.getParameter("title");
                String text = req.getParameter("text");
                post.setPhoto(postPhoto);
                post.setAuthor_id(author);
                post.setText(text);
                post.setTitle(title);
                try {
                    postDao.save(post);
                } catch (SQLException e){
                    e.printStackTrace();
                }

                session.setAttribute("user",userSession);
                resp.sendRedirect("/post");
        } else {
            throw new IllegalArgumentException("Передан пустой файл");
        }
    }
}
