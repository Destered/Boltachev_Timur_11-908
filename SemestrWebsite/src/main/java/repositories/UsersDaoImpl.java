package repositories;

import models.User;
import singletones.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDaoImpl implements UsersDao {
    private Connection connection;


   public UsersDaoImpl(){
       try {
           this.connection = ConnectionProvider.getConnection();
       } catch (ClassNotFoundException e) {
           System.out.println("ClassNotFoundException");
       }
   }

    private final String SQL_SAVE_USER_TO_TABLE = "INSERT into users(username, email, password,firstName,secondName,about) values (?,?,?,?,?,?)";
    @Override
    public void save(User model)throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER_TO_TABLE, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,model.getUsername());
            preparedStatement.setString(2,model.getEmail());
            preparedStatement.setString(3,model.getPassword());
            preparedStatement.setString(4,model.getFirstName());
            preparedStatement.setString(5,model.getSecondName());
            preparedStatement.setString(6,null);
            int updateRow = preparedStatement.executeUpdate();
            if(updateRow == 0){
                throw new SQLException();
            }
            try (ResultSet set = preparedStatement.getGeneratedKeys();) {
                //Если id  существет,обновляем его у модели.
                if (set.next()) {
                    model.setId(set.getInt(1));
                } else {
                    throw new SQLException();
                }
            }
        }

    }

    private final String SQL_UPDATE_USER = "UPDATE users SET firstname=?,secondname=?,about=?,imagepath=? where id_user =?";
    @Override
    public void update(User model) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)){
            preparedStatement.setString(1,model.getFirstName());
            preparedStatement.setString(2,model.getSecondName());
            preparedStatement.setString(3,model.getAbout());
            preparedStatement.setString(4,model.getImagePath());
            preparedStatement.setInt(5,model.getId());
            preparedStatement.execute();
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    private final String SQL_DELETE_USER = "DELETE FROM users WHERE id_user = ?";
    @Override
    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)){
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    private final String SQL_FIND_ONE_BY_ID = "SELECT * FROM users where id_user = ?";
    @Override
    public Optional<User> find(Integer id) {
        User user = null;
        if (id < 0) return Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ONE_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = userRowMapper.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return Optional.ofNullable(user);
    }


    private final String SQL_FIND_ONE_BY_USERNAME = "SELECT * FROM users where username = ?";
    @Override
    public Optional<User> findByUsername(String username) {
        User user = null;
        if (username.isEmpty()) return Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ONE_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = userRowMapper.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return Optional.ofNullable(user);
    }

    private final String SQL_UPDATE_USER_ABOUT = "UPDATE users SET about=? WHERE user_id=?";
    @Override
    public boolean updateUserAbout(String about,int user_id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_ABOUT)){
            preparedStatement.setString(1,about);
            preparedStatement.setInt(2,user_id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
        return true;
    }

    private final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            statement.execute(SQL_FIND_ALL_USERS);
            ResultSet rs = statement.getResultSet();
            while(rs.next()){
                users.add(userRowMapper.mapRow(rs));
            }
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
        return users;
    }


    private RowMapper<User> userRowMapper = row -> {
        Integer id = row.getInt("id_user");
        User user = new User();
        user.setUsername(row.getString("username"));
        user.setEmail(row.getString("email"));
        user.setPassword(row.getString("password"));
        user.setFirstName(row.getString("firstName"));
        user.setSecondName(row.getString("secondName"));
        user.setAbout(row.getString("about"));
        user.setImagePath(row.getString("imagepath"));
        user.setId(row.getInt("id_user"));
        return user;
    };

}
