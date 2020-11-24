package repositories;

import models.User;

import java.util.Optional;

public interface UsersDao extends CrudDao<User,Integer> {

    Optional<User> findByUsername(String username);
    boolean updateUserAbout(String about,int user_id);

}
