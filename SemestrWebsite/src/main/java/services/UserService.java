package services;

import models.User;
import repositories.UsersDaoImpl;

import java.sql.SQLException;
import java.util.Optional;


public class UserService{
    UsersDaoImpl usersDao = new UsersDaoImpl();

    public boolean addUser(User user){
        try {
            usersDao.save(user);
            return true;
        }
        catch (SQLException e){
            return false;
        }

    }

    public Optional<User> findUserById(Integer id){
        return usersDao.find(id);

    }

    public Optional<User> findUserByUsername(String username){
        return usersDao.findByUsername(username);

    }



    public boolean login(String username,String password){
        Optional<User> checkUser = usersDao.findByUsername(username);
        if(checkUser.isPresent()){
            User checked = checkUser.get();
            return checked.getPassword().equals(password);
        }
        else return false;
    }

    private boolean hasUser(User user){
        return usersDao.findByUsername(user.getUsername()).isPresent();
    }

}
