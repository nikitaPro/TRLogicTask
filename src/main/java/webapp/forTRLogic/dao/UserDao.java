package webapp.forTRLogic.dao;

import webapp.forTRLogic.bean.NewUser;
import webapp.forTRLogic.bean.Status;
import webapp.forTRLogic.bean.User;

public interface UserDao {

    Status addNewUser(NewUser user, String pass);
    User getUser(long userId);
}
