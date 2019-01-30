package webapp.forTRLogic.dao.impl;

import java.util.ArrayList;
import java.util.List;
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
        long id;
        int rowsAffected;
        try {
            id = jdbcTemplate.queryForObject(Query.INSERT_NEW_USER,
                    new Object[] {user.getName(),
                            user.getLastName(),
                            user.getEmail(),
                            pass},
                    Long.class);
            rowsAffected = jdbcTemplate.update(Query.INSERT_USER_PHONE, 
                    new Object[] {id, user.getPhones().get(0)});
        } catch (Exception e) {
            LOG.error("User wasn't signed up.", e);
            return new Status(PROBLEM_MESSAGE, false);
        }
        
        if (rowsAffected == 0) {
            LOG.error("User's phone wasn't wrote down. Database returned \"0 rows was added\".");
            return new Status(PROBLEM_MESSAGE, false);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("User's phone was wrote down into database.");
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
                row.get("email").toString());
        user.setPhones(getPhonesById(userId));
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
        long userId = (Long)row.get("id");
        User user = new User(userId, 
                row.get("name").toString(), 
                row.get("lastname").toString(), 
                email);

        user.setPhones(getPhonesById(userId));
        return user;
    }
    
    @Override
    public List<String> getPhonesById(long id) {
        List<String> returnigPhones = new ArrayList<String>();
        List<Map<String, Object>> phones = jdbcTemplate.queryForList(Query.SELECT_USERS_PHONES_BY_ID, new Object[]{id});
        for (Map<String, Object> row_2 : phones) {
            returnigPhones.add(row_2.get("phone").toString());
        }
        return returnigPhones;
    }

    @Override
    public String getUserPasswordById(long id) {
        String pass = jdbcTemplate.queryForObject(Query.SELECT_USER_PASSWORD_BY_ID,
                new Object[] { id }, String.class);
        return pass;
    }

}
