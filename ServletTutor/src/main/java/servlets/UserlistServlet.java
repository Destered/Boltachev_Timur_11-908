package servlets;

import services.UserlistService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class UserlistServlet extends HttpServlet {
    UserlistService userlistService = new UserlistService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer write = resp.getWriter();
        userlistService.getUser(write);
    }
}
