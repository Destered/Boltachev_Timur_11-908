package repositories;

import models.Post;
import singletones.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDaoImpl implements PostDao {
    private Connection connection;

    public PostDaoImpl(){
        try {
            this.connection = ConnectionProvider.getConnection();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        }
    }

    final static String SQL_SAVE_POST="INSERT into posts(title, text, photo,author_id) values (?,?,?,?)";
    @Override
    public void save(Post model) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_POST, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,model.getTitle());
            preparedStatement.setString(2,model.getText());
            preparedStatement.setString(3,model.getPhoto());
            preparedStatement.setInt(4,model.getAuthor_id());
            int updateRow = preparedStatement.executeUpdate();
            if(updateRow == 0){
                throw new SQLException();
            }
            try (ResultSet set = preparedStatement.getGeneratedKeys();) {
                if (set.next()) {
                    model.setPost_id(set.getInt(1));
                } else {
                    throw new SQLException();
                }
            }
        }
    }

    final static String SQL_UPDATE_POST="UPDATE posts SET title=?,text=?,photo=? where post_id =?";
    @Override
    public void update(Post model) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_POST)){
            preparedStatement.setString(1,model.getTitle());
            preparedStatement.setString(2,model.getText());
            preparedStatement.setString(3,model.getPhoto());
            preparedStatement.setInt(4,model.getPost_id());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    final static String SQL_DELETE_POST="DELETE FROM posts WHERE post_id = ?";
    @Override
    public void delete(Integer post_id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_POST)){
            preparedStatement.setInt(1,post_id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    final static String SQL_FIND_POST="SELECT * FROM posts WHERE post_id=?";
    @Override
    public Optional<Post> find(Integer post_id) {
        Post post = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_POST)){
            preparedStatement.setInt(1,post_id);
            preparedStatement.execute();
            post = postRowMapper.mapRow(preparedStatement.getResultSet());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(post);
    }

    final static String SQL_FIND_ALL_POST="SELECT * from posts";
    @Override
    public List<Post> findAll() {
        List<Post> post = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            statement.execute(SQL_FIND_ALL_POST);
            ResultSet rs = statement.getResultSet();
            while(rs.next()){
                post.add(postRowMapper.mapRow(rs));
            }
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
        return post;
    }

    private RowMapper<Post> postRowMapper = row -> {
        Post post = new Post();
        post.setPost_id(row.getInt("post_id"));
        post.setPhoto(row.getString("photo"));
        post.setText(row.getString("text"));
        post.setTitle(row.getString("title"));
        post.setAuthor_id(row.getInt("author_id"));
        return post;
    };
}
