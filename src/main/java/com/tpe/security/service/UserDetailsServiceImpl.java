package com.tpe.security.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.tpe.domain.User myUser = userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User not found: "+ username));
        //myUser must convert to UserDetails

        return new org.springframework.security.core.userdetails.User(myUser.getUserName(),myUser.getPassword(),buildGrantedAuthority(myUser.getRoles()));//grantedAuthority means User Roles

    }

    private List<SimpleGrantedAuthority> buildGrantedAuthority(Set<Role> roles){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getType().name()));
        }

        return authorities;
    }
}