package servlets;

import models.News;
import services.NewsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetNewsServlet extends HttpServlet {
    NewsService ns =new NewsService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        List<News> newsList = ns.getAllNews();
        StringBuilder allNews = new StringBuilder();
        for(News news:newsList){
            allNews.append(news.toString()).append(";");
        }
        resp.getWriter().println(allNews);
    }
}
