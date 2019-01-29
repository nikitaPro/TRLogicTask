package webapp.forTRLogic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import webapp.forTRLogic.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                // specify query rules
                // which will determine access to resources and other data
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/**").access("hasRole('ROLE_USER')")
                .and();

        http.formLogin()
                // specify the page with the login form
                .loginPage("/home")
                // specify the "action" from the login form
                .loginProcessingUrl("/signin")
                // specify URL when login failed
                .failureUrl("/home?error=\"Wrong email or password\"")
                .defaultSuccessUrl("/profile", true)
                // Specify login and password parameters from the login form
                .usernameParameter("email")
                .passwordParameter("pass")
                // give access to the login form to all
                .permitAll();


        http.logout()
                // allow everyone to log out
                .permitAll()
                // specify the URL of the logout
                .logoutUrl("/logout")
                // specify the URL when successful logout
                .logoutSuccessUrl("/home")
                // make the current session invalid
                .invalidateHttpSession(true);

        http.sessionManagement().maximumSessions(1);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Controller
    static class FaviconController {
        @RequestMapping("favicon.ico")
        String favicon() {
            return "forward:/forTRLogic/images/favicon.ico";
        }
    }
}
