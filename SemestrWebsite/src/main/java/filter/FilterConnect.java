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

@WebFilter(urlPatterns = {"/register", "/login", "/profile"}, filterName = "AuthFilter")
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

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(-1);

        this.checkCookies(request.getCookies(), session, request, response);

        filterChain.doFilter(request, response);
    }

    private void checkCookies(Cookie[] cookies, HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SiteAuthUser")) {
                    User user = us.findUser(cookie.getValue());
                    if (user != null) {
                        if (session.getAttribute("user") == null) {
                            session.setAttribute("user",user);
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
