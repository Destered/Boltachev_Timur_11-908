package repositories;

import models.News;
import singletones.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsDaoImpl implements NewsDao {
    private Connection connection;

    public NewsDaoImpl(){
           try {
        this.connection = ConnectionProvider.getConnection();
    } catch (ClassNotFoundException e) {
        System.out.println("ClassNotFoundException");
    }
}

    final static String SQL_SAVE_NEWS="INSERT into news(title, text, photo) values (?,?,?)";
    @Override
    public void save(News model) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_NEWS, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,model.getTitle());
            preparedStatement.setString(2,model.getText());
            preparedStatement.setString(3,model.getPhoto());
            int updateRow = preparedStatement.executeUpdate();
            if(updateRow == 0){
                throw new SQLException();
            }
            try (ResultSet set = preparedStatement.getGeneratedKeys();) {
                if (set.next()) {
                    model.setNews_id(set.getInt(1));
                } else {
                    throw new SQLException();
                }
            }
        }
    }

    final static String SQL_UPDATE_NEWS="UPDATE news SET title=?,text=?,photo=? where news_id =?";
    @Override
    public void update(News model) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_NEWS)){
            preparedStatement.setString(1,model.getTitle());
            preparedStatement.setString(2,model.getText());
            preparedStatement.setString(3,model.getPhoto());
            preparedStatement.setInt(4,model.getNews_id());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    final static String SQL_DELETE_NEWS="DELETE FROM news WHERE news_id = ?";
    @Override
    public void delete(Integer news_id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_NEWS)){
            preparedStatement.setInt(1,news_id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    final static String SQL_FIND_NEWS="SELECT * FROM news WHERE news_id=?";
    @Override
    public Optional<News> find(Integer news_id) {
        News news = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_NEWS)){
            preparedStatement.setInt(1,news_id);
            preparedStatement.execute();
            news = newsRowMapper.mapRow(preparedStatement.getResultSet());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(news);
    }

    final static String SQL_FIND_ALL_NEWS="SELECT * from news";
    @Override
    public List<News> findAll() {
        List<News> news = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            statement.execute(SQL_FIND_ALL_NEWS);
            ResultSet rs = statement.getResultSet();
            while(rs.next()){
                news.add(newsRowMapper.mapRow(rs));
            }
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
        return news;
    }

    private RowMapper<News> newsRowMapper = row -> {
        News news = new News();
        news.setNews_id(row.getInt("news_id"));
        news.setPhoto(row.getString("photo"));
        news.setText(row.getString("text"));
        news.setTitle(row.getString("title"));
        return news;
    };
}
