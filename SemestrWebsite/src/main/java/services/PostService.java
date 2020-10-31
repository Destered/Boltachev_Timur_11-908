package services;

import models.News;
import models.Post;
import repositories.NewsDaoImpl;
import repositories.PostDaoImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostService {
    PostDaoImpl postDao = new PostDaoImpl();

    public boolean addPost(Post post){
        try {
            postDao.save(post);
            return true;
        }
        catch (SQLException e){
            return false;
        }

    }

    public boolean deletePost(Post post){
        postDao.delete(post.getPost_id());
        return true;
    }

    public boolean updatePost(Post post){
        postDao.update(post);
        return true;
    }

    public Optional<Post> findPostById(Integer id){
        return postDao.find(id);
    }

    public List<Post> getAllPost(){
        return postDao.findAll();
    }}
