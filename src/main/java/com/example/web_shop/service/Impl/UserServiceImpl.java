package com.example.web_shop.service.Impl;

import com.example.web_shop.entity.User;
import com.example.web_shop.repository.UserRepository;
import com.example.web_shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository repository;
    private final UserDetailsService service;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authentication;

    @Autowired
    public UserServiceImpl(UserRepository repository, @Qualifier("userDetailsServiceImpl") UserDetailsService service,
                           BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authentication) {
        this.repository = repository;
        this.service = service;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authentication = authentication;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public void login(String username, String password) {
        UserDetails userDetails = service.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken
                        (userDetails, password, userDetails.getAuthorities());
        authentication.authenticate(token);

        if (token.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(token);
            logger.debug(String.format("User %s logged in successfully"), username);
        }else {
            logger.error(String.format("Error with %s authentication!", username));
        }
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User findById(long id) {
        return repository.findById(id);
    }
}
