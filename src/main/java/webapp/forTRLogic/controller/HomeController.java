package webapp.forTRLogic.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import webapp.forTRLogic.services.UserService;


@Controller
public class HomeController {
    
    @Autowired
    private UserService userService;

    @RequestMapping(value="/home")
    public ModelAndView homePage(HttpServletResponse response) throws IOException{
        return new ModelAndView("home");
    }
    
    @RequestMapping(value="/signin", method = RequestMethod.GET)
    public ModelAndView signIn(HttpServletResponse response) throws IOException{
        return new ModelAndView("home");
    }
    
    @RequestMapping(value="/signup", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> signup (
            @RequestParam(value = "name") String name,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "pass") String pass,
            @RequestParam(value = "pass_confirm") String passConfirm) throws JsonProcessingException {
        NewUser newUser = new NewUser(name, lastName, phone, email);
        Status status = userService.signUp(newUser, pass, passConfirm);
        
        Map<String, Object> resp = new HashMap<>();
        resp.put("status", status.isSuccess());
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonShips = mapper.writeValueAsString(resp);
        
        return ResponseEntity.ok(jsonShips);
    }
	
}
