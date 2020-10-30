package servlets;

import models.Chat;
import models.Message;
import models.User;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ServletChatData extends HttpServlet {
    Helper helper = new Helper();
    Chat chatStorage = new Chat();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if(method.equals("getMsg")) {
            List<Message> messageList = chatStorage.getNewMessage();
            StringBuilder allMessage = new StringBuilder();
            for (Message msg : messageList) {
                allMessage.append(msg.toString());
            }
            resp.getWriter().println(allMessage);
        } else if(method.equals("sendMsg")){
            HttpSession session = req.getSession();
            User user = (User)session.getAttribute("user");
            String text = req.getParameter("message");
            chatStorage.addMessage(text,user.getUsername());
        }
    }

}
