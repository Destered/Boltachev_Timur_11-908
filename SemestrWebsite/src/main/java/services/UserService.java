package services;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    static List<User> userList = new ArrayList<>();

    public boolean addUser(User user){
        if(!hasUser(user)){
            userList.add(user);
            return true;
        }else{
            return false;
        }
    }

    public User findUser(String username){
        for (User user: userList){
            if(user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public boolean login(User user){
        boolean alreadyHas = false;
        for(User userCheck: userList){
            if(userCheck.getUsername().equals(user.getUsername()) && userCheck.getPassword().equals(user.getPassword())){
                alreadyHas = true;
            }
        }
        return alreadyHas;
    }

    private boolean hasUser(User user){
        boolean alreadyHas = false;
        for(User userCheck: userList){
            if(userCheck.getUsername().equals(user.getUsername()) || userCheck.getEmail().equals(user.getEmail())){
                alreadyHas = true;
            }
        }
        return alreadyHas;
    }

}
