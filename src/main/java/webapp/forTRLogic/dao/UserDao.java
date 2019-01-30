package webapp.forTRLogic.dao;

import java.util.List;

import webapp.forTRLogic.bean.NewUser;
import webapp.forTRLogic.bean.Status;
import webapp.forTRLogic.bean.User;

/**Class for working with user data from database*/
public interface UserDao {
    
    /**Insert new user into database*/
    Status addNewUser(NewUser user, String pass);
    
    User getUserById(long userId);
    User getUserByEmail(String Email);
    
    /**Method return password by user id*/
    String getUserPasswordById(long id);
    /**Return list of phone number by user id*/
    List<String> getPhonesById(long id);
}
