package webapp.forTRLogic.dao.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import webapp.forTRLogic.bean.NewUser;
import webapp.forTRLogic.bean.Status;
import webapp.forTRLogic.bean.User;
import webapp.forTRLogic.constants.Query;
import webapp.forTRLogic.dao.UserDao;

@Repository
@Qualifier("userDao")
public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
    
    private static final String PROBLEM_MESSAGE = "Sorry, server problem, try repeat later.";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Status addNewUser(NewUser user, String pass) {
        int rowsAffected;
        try {
            rowsAffected = jdbcTemplate.update(Query.INSERT_NEW_USER,
                    new Object[] { user.getName(),
                            user.getLastName(),
                            user.getPhone(),
                            user.getEmail(),
                            pass });
        } catch (Exception e) {
            LOG.error("User wasn't signed up.", e);
            return new Status(PROBLEM_MESSAGE, false);
        }
        
        if (rowsAffected == 0) {
            LOG.error("User wasn't signed up. Database returned \"0 rows was added\".");
            return new Status(PROBLEM_MESSAGE, false);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("User was wrote down into database.");
            }
            return new Status("Success. Sign in please.", true);
        }
    }

    @Override
    public User getUserById(long userId) {
        Map<String, Object> row = jdbcTemplate.queryForMap(Query.SELECT_USER_BY_ID,
                new Object[]{userId});
        User user = new User(userId, 
                row.get("name").toString(), 
                row.get("lastname").toString(),  
                row.get("phone").toString(),
                row.get("email").toString());
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        Map<String, Object> row;
        try {
            row = jdbcTemplate.queryForMap(Query.SELECT_USER_BY_EMAIL,
                    new Object[] { email });
        } catch (EmptyResultDataAccessException e){
            return null;
        }
        User user = new User((Long)row.get("id"), 
                row.get("name").toString(), 
                row.get("lastname").toString(), 
                row.get("phone").toString(), 
                email);
        return user;
    }

    @Override
    public String getUserPasswordById(long id) {
        String pass = jdbcTemplate.queryForObject(Query.SELECT_USER_PASSWORD_BY_ID,
                new Object[] { id }, String.class);
        return pass;
    }

}
