package pl.krystian.imageuploader.config;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.krystian.imageuploader.Model.Users;
import pl.krystian.imageuploader.repo.UsersRepo;
import pl.krystian.imageuploader.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsServiceImpl userDetailsService;
    private UsersRepo usersRepo;


    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UsersRepo usersRepo) {
        this.userDetailsService = userDetailsService;
        this.usersRepo = usersRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser(new User("krystian",passwordEncoder().encode("krystian123"), Collections.singleton(new SimpleGrantedAuthority("user"))));
    auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/gallery").hasAnyRole("USER")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable(); //zabezpieczenie zeby nie łaczyć się zzewnetrzego hosta ;) httpBassic ->postman

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get(){
        Users usersUser = new Users("user",passwordEncoder().encode("user123"),"ROLE_USER");
        Users usersAdmin =  new Users("admin",passwordEncoder().encode("admin123"),"ROLE_ADMIN");
        usersRepo.save(usersUser);
        usersRepo.save(usersAdmin);
    }



}
