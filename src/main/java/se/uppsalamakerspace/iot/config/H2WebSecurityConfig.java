package se.uppsalamakerspace.iot.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by fredl2 on 2016-11-01.
 */
@EnableWebSecurity
@Order(3)
public class H2WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/h2-console**")
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .authenticationProvider(null);
    }
}
