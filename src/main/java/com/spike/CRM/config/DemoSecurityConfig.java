package com.spike.CRM.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////
////        // add our users for in memory authentication
////
////        User.UserBuilder users = User.withDefaultPasswordEncoder();
////        auth.inMemoryAuthentication()
////                .withUser(users.username("john").password("123").roles("EMPLOYEE"))
////                .withUser(users.username("sev").password("123").roles("MANAGER"))
////                .withUser(users.username("ma").password("123").roles("ADMIN"));
////        System.out.println("Auth config finished");
////
////    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user").
                password(new BCryptPasswordEncoder().encode("123")).roles("USER");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").
                password(new BCryptPasswordEncoder().encode("123")).roles("ADMIN");
        System.out.println("global config finished");
    }

    // access REST API
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/rest/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/customer/showFormForAdd").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
                .loginProcessingUrl("/customer/list").permitAll();

        System.out.println("Http config finished");
    }
}
