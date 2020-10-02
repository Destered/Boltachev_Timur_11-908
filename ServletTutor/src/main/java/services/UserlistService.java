package services;

import models.User;

import java.io.Writer;

public class UserlistService {

    public void getUser(Writer write){

        for(User user : LoginService.userList){
            try {
                write.write(user.getUsername() + "\n");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
