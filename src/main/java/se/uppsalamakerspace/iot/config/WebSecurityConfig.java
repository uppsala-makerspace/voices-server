package se.uppsalamakerspace.iot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import se.uppsalamakerspace.iot.auth.DbUserDetailsService;
import se.uppsalamakerspace.iot.auth.StationHeaderAuthenticationFilter;
import se.uppsalamakerspace.iot.auth.StationUserDetailsService;
import se.uppsalamakerspace.iot.repository.StationRepository;
import se.uppsalamakerspace.iot.repository.UserRepository;

/**
 * Created by fredl2 on 2016-11-01.
 */
@Order(2)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepo;

    @Autowired
    public WebSecurityConfig(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    private StationRepository stationRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/admin/login")
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/webjars/**","/css/**","/admin/login","/admin/login/post","/admin/firstUser").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(stationFilter(), AnonymousAuthenticationFilter.class)
                .authenticationProvider(preauthAuthProvider())
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/h2-console**","/h2-console/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(preauthAuthProvider())
                .userDetailsService(dbUserDetailsService()).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Bean
    public UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsServiceWrapper() {
        UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper =
                new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>();
        wrapper.setUserDetailsService(new StationUserDetailsService(stationRepository));
        return wrapper;
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preauthAuthProvider() {
        PreAuthenticatedAuthenticationProvider preauthAuthProvider =
                new PreAuthenticatedAuthenticationProvider();
        preauthAuthProvider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper());
        return preauthAuthProvider;
    }

    @Bean
    public StationHeaderAuthenticationFilter stationFilter() throws Exception {
        StationHeaderAuthenticationFilter filter = new StationHeaderAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public UserDetailsService dbUserDetailsService() {
        return new DbUserDetailsService(userRepo);
    }

}
