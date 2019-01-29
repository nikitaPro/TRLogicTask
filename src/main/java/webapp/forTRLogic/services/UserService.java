package webapp.forTRLogic.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import webapp.forTRLogic.bean.NewUser;
import webapp.forTRLogic.bean.Status;

public interface UserService extends UserDetailsService {

    Status signUp(NewUser newUser, String pass, String passConfirm);
}
