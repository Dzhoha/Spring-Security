package com.zaurtregulov.spring_mvc_hibernate.configuration;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("zaur").password("zaur").roles("IT"))
                .withUser(userBuilder.username("dzhavid").password("dzhavid").roles("IT"))
                .withUser(userBuilder.username("dzhoha").password("dzhoha").roles("UNKNOWN"))
                .withUser(userBuilder.username("mariya").password("mariya").roles("SALES"))
                .withUser(userBuilder.username("denis").password("denis").roles("MANAGER"))
                .withUser(userBuilder.username("oleg").password("oleg").roles("HR"));


    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").hasAnyRole("IT", "HR", "UNKNOWN", "SALES", "MANAGER")
                .and().formLogin().permitAll();
    }
}

