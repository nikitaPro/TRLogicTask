package webapp.forTRLogic.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value="/home")
    public ModelAndView homePage(HttpServletResponse response) throws IOException{
        return new ModelAndView("home");
    }

    @RequestMapping(value="/signin")
    public ModelAndView signIn(HttpServletResponse response) throws IOException{
        return new ModelAndView("home");
    }
	
}
