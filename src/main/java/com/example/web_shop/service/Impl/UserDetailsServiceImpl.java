package com.example.web_shop.service.Impl;

import com.example.web_shop.entity.User;
import com.example.web_shop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username){

        User user = repository.findByUsername(username);

        if (user != null){
            Set<GrantedAuthority> authorities = new HashSet<>();

            if (Objects.equals(username, "admin")){
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }else {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }

            logger.debug(String.format("User with name: %s and password: is created", user),
                    user.getUsername(), user.getPassword());

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }else {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }





















}
