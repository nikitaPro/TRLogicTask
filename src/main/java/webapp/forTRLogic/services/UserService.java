package webapp.forTRLogic.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import webapp.forTRLogic.bean.NewUser;
import webapp.forTRLogic.bean.Status;
import webapp.forTRLogic.bean.User;

public interface UserService extends UserDetailsService {

    /** This method do all user data validation and register user in the system. */
    Status signUp(NewUser newUser, String pass, String passConfirm);
    /** This method return user data using his id */
    User getUserData(long id);
}
