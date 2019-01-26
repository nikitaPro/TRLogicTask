package webapp.forTRLogic.services;

import webapp.forTRLogic.bean.NewUser;
import webapp.forTRLogic.bean.Status;

public interface UserService {

    Status signIn(String email, String pass);
    Status signUp(NewUser newUser, String pass);
}
