package webapp.forTRLogic.dao;

import java.math.BigInteger;

import webapp.forTRLogic.bean.NewUser;
import webapp.forTRLogic.bean.User;

public interface UserDao {

    String addNewUser(NewUser user, String pass);
    User getUser(BigInteger userId);
}
