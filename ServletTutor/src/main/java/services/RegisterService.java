package services;

import models.User;

public class RegisterService {

    public boolean register (User registerUser){
        boolean alreadyHas = alreadyHas(registerUser);
        if(alreadyHas){
            return false;
        } else{
            LoginService.userList.add(registerUser);
            return true;
        }
    }

    private boolean alreadyHas(User registerUser){
        boolean alreadyHas = false;
        for(User user: LoginService.userList){
            if(registerUser.getUsername().toLowerCase().equals(user.getUsername().toLowerCase())) alreadyHas = true;
        }
        if(alreadyHas){
            return true;
        } else{
            return false;
        }
    }
}
