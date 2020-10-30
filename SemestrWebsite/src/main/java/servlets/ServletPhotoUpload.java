package servlets;

import models.User;
import repositories.UsersDao;
import repositories.UsersDaoImpl;
import services.FileSaver;
import services.FileSaverImpl;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

        @WebServlet("/saveImage")
        @MultipartConfig
        public class ServletPhotoUpload extends HttpServlet {
            private FileSaverImpl fileSaver;
            private UsersDao usersDao;
            private Helper helper;

            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.sendRedirect("/profile");
            }

            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                HttpSession session = req.getSession();
                User userSession = (User)session.getAttribute("user");
                Part photoPart = req.getPart("photo");
                Optional<String> resultOfFileSaving = fileSaver.saveFile(photoPart, userSession.getId()+"");
                if (resultOfFileSaving.isPresent()) {
                    Optional<User> userCandidate = usersDao.find(userSession.getId());
                    if (userCandidate.isPresent()) {
                        User user = userCandidate.get();
                        if(user.getImagePath() != null){
                            File file = new File(getServletContext().getRealPath("") + user.getImagePath());
                            file.delete();
                        }
                        user.setImagePath(resultOfFileSaving.get());
                        usersDao.update(user);
                        session.setAttribute("user",user);
                        resp.sendRedirect("/profile");
                    } else {
                        throw new IllegalArgumentException("Пользователь не существует");
                    }
                } else {
                    throw new IllegalArgumentException("Передан пустой файл");
                }
            }

            @Override
            public void init() throws ServletException {
                fileSaver = new FileSaverImpl(getServletContext().getRealPath(""));
                usersDao = new UsersDaoImpl();
                helper = new Helper();
            }
        }
