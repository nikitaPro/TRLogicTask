package webapp.forTRLogic.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import webapp.forTRLogic.bean.NewUser;
import webapp.forTRLogic.bean.Status;
import webapp.forTRLogic.bean.TRLogicUserDetails;
import webapp.forTRLogic.bean.User;
import webapp.forTRLogic.services.UserService;

@Controller
public class HomeController {
    private static final Logger LOG = Logger.getLogger(HomeController.class); 
    
    @Autowired
    private UserService userService;
    
    /**This is the entry point for an authorized user.*/
    @Secured("ROLE_USER")
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public ModelAndView profile(@AuthenticationPrincipal TRLogicUserDetails userDetails) throws IOException {
        MDC.put("userName", userDetails.getUsername());
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("signed in.");
            }
            
            User userInfo = userService.getUserData(userDetails.getUserId());
            
            ModelAndView page = new ModelAndView("profile");
            page.addObject("userInfo", userInfo);
            return page;
            
        } finally {
            MDC.remove("userName");
        }
    }
    
    /**
     * This method registered user in system
     * @return status in json format.
     * @see webapp.forTRLogic.bean.Status
     * */
    @RequestMapping(value="/signup", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> signup (
            @RequestParam(value = "name") String name,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "pass") String pass,
            @RequestParam(value = "pass_confirm") String passConfirm) throws JsonProcessingException {
        MDC.put("userName", email);
        try {
            NewUser newUser = new NewUser(name, lastName, email);
            newUser.addPhone(phone);
            
            Status status = userService.signUp(newUser, pass, passConfirm);
            
            Map<String, Object> resp = new HashMap<>();
            resp.put("status", status);
            
            ObjectMapper mapper = new ObjectMapper();
            String jsonShips = mapper.writeValueAsString(resp);
            
            return ResponseEntity.ok(jsonShips);
        } finally {
            MDC.remove("userName");
        }
    }
    
    /**
     * Entry point for everyone
     * @return home page view
     * */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView("home");
        if (error != null) {
            model.addObject("error", error);
            if (LOG.isDebugEnabled()) {
                LOG.debug(error);
            }
        }

        if (logout != null) {
            model.addObject("logout", logout);
            if (LOG.isDebugEnabled()) {
                LOG.debug("logged out.");
            }
        }

        return model;
    }
	
}
