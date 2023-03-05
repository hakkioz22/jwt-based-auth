package com.tpe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//WebSecurityConfigurerAdapter deprecated!!!
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //if you try post services, you must disable csrf protection, because csrf protection still works and it does not allow post services
        http.csrf().disable();

        http.authorizeRequests().
        antMatchers("/","index.html","/css/*","/js/*","/images/*","/register").permitAll().
        //and().
        //authorizeRequests().antMatchers("/student/**").hasRole("INSTRUCTOR").and().
        //authorizeRequests().antMatchers("/beans/**").hasAuthority("ROLE_ADMIN").
        //authorizeRequests().antMatchers("/beans/**").hasRole("ADMIN").
        anyRequest().authenticated().and().httpBasic();
        return http.build();
    }

    //@Bean
//    protected InMemoryUserDetailsManager configureAuthentication(){
//
////        UserDetails user1 = User.builder().username("user").password("{noop}password").roles("INSTRUCTOR").build();
////        return new InMemoryUserDetailsManager(new UserDetails[] {user1});
//
//        UserDetails userJohn = User.builder().username("user").password(passwordEncoder().encode("password")).roles("INSTRUCTOR").build();
//        UserDetails userTony = User.builder().username("tony").password(passwordEncoder().encode("passTony")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(new UserDetails[] {userJohn,userTony});
//    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);//
    };


}
