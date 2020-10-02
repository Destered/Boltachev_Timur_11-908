package services;

import models.User;
import java.util.ArrayList;
import java.util.List;

public class LoginService {

    static List<User> userList = loadUser();

    public boolean login(String username, String password){
        User userCandidate = new User(username, password);
        for(User user: userList){
            if(user.getUsername().toLowerCase().equals(userCandidate.getUsername().toLowerCase())){
                if(user.getPassword().equals(userCandidate.getPassword())){
                    return true;
                }
                else break;
            }
        }
        return false;
    }

    private static List<User> loadUser(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("Dester","123zqwex","Male"));
        return userList;
    }
}
