package webapp.forTRLogic.services.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webapp.forTRLogic.bean.NewUser;
import webapp.forTRLogic.bean.Status;
import webapp.forTRLogic.bean.TRLogicUserDetails;
import webapp.forTRLogic.bean.User;
import webapp.forTRLogic.dao.UserDao;
import webapp.forTRLogic.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class); 
    
    private static final String USER_ALREADY_EXIST = "Sorry, but this email address already registered.";
    private static final String INVALID_PASS = "Password is invalid. \nPlease, enter password in 8-20 length range.";
    private static final String CONFIRM_PASS_FAIL = "Passwords are not matching.";
    private static final String INVALID_EMAIL = "Invalid email format. Please, check your email, it must look like example@example.com";
    private static final String INVALID_PHONE = "Invalid phone number format. Please, check your phone number, it must look like +00(000)000-00-00";

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /* This method invoke by spring security when user try signed in */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        /* Retrieve user from database */
        LOG.debug("User " + email + " try to sign in");
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            UsernameNotFoundException ex = new UsernameNotFoundException("User with email [" + email + "] not found.");
            LOG.error("User not registered", ex);
            throw ex;
        }
        
        /* Granting role to this user */
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        return new TRLogicUserDetails(user.getId(), 
                email, 
                userDao.getUserPasswordById(user.getId()), 
                true, true, true, true, 
                authorities);
    }

    @Override
    public Status signUp(NewUser newUser, String pass, String passConfirm) {
        /* Checking whether the same user are in the system */
        if (userDao.getUserByEmail(newUser.getEmail()) != null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("User with email [" + newUser.getEmail() + "] already exist.");
            }
            return new Status(USER_ALREADY_EXIST, false);
        }
        
        if (!isEmailValid(newUser.getEmail())) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Invalid email format " + newUser.getEmail());
            }
            return new Status(INVALID_EMAIL, false);
        }
        
        if (!isPasswordValid(pass)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Invalid pass length");
            }
            return new Status(INVALID_PASS, false);
        }
        
        if (!isPasswordMatching(pass, passConfirm)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Invalid password matching");
            }
            return new Status(CONFIRM_PASS_FAIL, false);
        }
        
        // Get first element because only one phone number take part in sign up procedure
        String phone = newUser.getPhones().get(0);
        if (!isPhoneValid(phone)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Invalid phone format " + phone);
            }
            return new Status(INVALID_PHONE, false);
        }
        
        Status resultMessage;
        resultMessage = userDao.addNewUser(newUser, passwordEncoder.encode(pass));

        return resultMessage;
    }
    
    private boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        int passwordLength = password.trim().length();
        return passwordLength >= 8 && passwordLength <= 20;
    }

    private boolean isPasswordMatching(String password, String passwordConfirm) {
        if (password == null || passwordConfirm == null) {
            return false;
        }
        return password.equals(passwordConfirm.trim());

    }

    private boolean isEmailValid(String email) {
        return email != null && email.matches("(^.+@.+\\..+$)");
    }
    
    private boolean isPhoneValid(String phone) {
        return phone != null && phone.matches("(^\\+\\d\\d\\(\\d{3}\\)\\d{3}\\-\\d\\d\\-\\d\\d$)");
    }

    @Override
    public User getUserData(long id) {
        User user = userDao.getUserById(id);
        return user;
    }
}
