package webapp.forTRLogic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webapp.forTRLogic.bean.NewUser;
import webapp.forTRLogic.bean.Status;
import webapp.forTRLogic.dao.UserDao;
import webapp.forTRLogic.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final String INVALID_PASS = "Password is invalid! \nPlease, enter password in 8-20 length range.";
    private static final String CONFIRM_PASS_FAIL = "Passwords are not matching!";
    private static final String INVALID_EMAIL = "Invalid email format! Must look like example@example.com";
    private static final String INVALID_PHONE = "Invalid phone format! Must look like +00(000)000-00-00";

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Status signUp(NewUser newUser, String pass, String passConfirm) {
        if (!isEmailValid(newUser.getEmail())) {
            return new Status(INVALID_EMAIL, false);
        }
        if (!isPasswordValid(pass)) {
            return new Status(INVALID_PASS, false);
        }
        if (!isPasswordMatching(pass, passConfirm)) {
            return new Status(CONFIRM_PASS_FAIL, false);
        }
        if (!isPhoneValid(newUser.getPhone())) {
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
}
