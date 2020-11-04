package filter;

import models.User;
import services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {"/register", "/login", "/profile","/main","/news","/post","/post/create","/chat"}, filterName = "AuthFilter")
public class FilterConnect implements Filter {
    UserService us = new UserService();
    private FilterConfig config = null;
    private boolean active = false;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        this.checkCookies(request.getCookies(), session, request, response);

        filterChain.doFilter(request, response);
    }

    private void checkCookies(Cookie[] cookies, HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SiteAuthUser")) {
                    Optional<User> user = us.findUserByUsername(cookie.getValue());
                    if (user.isPresent()) {
                        if (session.getAttribute("user") == null) {
                            session.setAttribute("user",user.get());
                        }
                    }
                    break;
                }
            }
        }
    }

    public void destroy() {
        config = null;
    }
}
