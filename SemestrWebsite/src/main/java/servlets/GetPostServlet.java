package servlets;

import models.Post;
import models.User;
import services.PostService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/getpost")
public class GetPostServlet extends HttpServlet {
    PostService ps = new PostService();
    UserService us = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        List<Post> postList = ps.getAllPost();
        StringBuilder allPost = new StringBuilder();
        for (Post post : postList) {
            int user_id = post.getAuthor_id();
            Optional<User> userCanditate = us.findUserById(user_id);
            if(userCanditate.isPresent()) {
                User user = userCanditate.get();
                allPost.append(post.toString()).append("#%#").append(user.getId().toString()).append("#%#").append(user.getUsername()).append("#&#");
            }
        }
        resp.getWriter().println(allPost);
    }
}