package services;

import models.News;
import repositories.NewsDaoImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class NewsService{
    NewsDaoImpl newsDao = new NewsDaoImpl();

    public boolean addNews(News news){
        try {
            newsDao.save(news);
            return true;
        }
        catch (SQLException e){
            return false;
        }

    }

    public boolean deleteNews(News news){
        newsDao.delete(news.getNews_id());
        return true;
    }

    public boolean updateNews(News news){
        newsDao.update(news);
        return true;
    }

    public Optional<News> findNewsById(Integer id){
        return newsDao.find(id);

    }

    public List<News> getAllNews(){
        return newsDao.findAll();
    }

















}
