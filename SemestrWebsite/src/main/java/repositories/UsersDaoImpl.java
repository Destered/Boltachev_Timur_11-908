package repositories;

import models.User;
import singletones.ConnectionProvider;

import java.sql.*;
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

    private final String SQL_SAVE_USER_TO_TABLE = "INSERT into users(username, email, password) values (?,?,?)";
    @Override
    public void save(User model)throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER_TO_TABLE, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,model.getUsername());
            preparedStatement.setString(2,model.getEmail());
            preparedStatement.setString(3,model.getPassword());
            int updateRow = preparedStatement.executeUpdate();
            if(updateRow == 0){
                throw new SQLException();
            }
            try (ResultSet set = preparedStatement.getGeneratedKeys();) {
                //Если id  существет,обновляем его у подели.
                if (set.next()) {
                    model.setId(set.getInt(1));
                } else {
                    throw new SQLException();
                }
            }
        }

    }

    private final String SQL_UPDATE_USER = "UPDATE users";
    @Override
    public void update(User model) {
    }

    private final String SQL_DELETE_USER = "DELETE FROM users WHERE id_user = ?";
    @Override
    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)){
            preparedStatement.setInt(1,id);
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

    @Override
    public List<User> findAll() {
        return null;
    }


    private RowMapper<User> userRowMapper = row -> {
        Integer id = row.getInt("id_user");
        String username = row.getString("username");
        String email = row.getString("email");
        String password = row.getString("password");
        return new User(username,password,email,id);
    };

}
