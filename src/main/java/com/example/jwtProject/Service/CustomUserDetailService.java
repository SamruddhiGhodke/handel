package com.example.jwtProject.Service;

import com.example.jwtProject.Entity.RegistrationEntity;
import com.example.jwtProject.Repository.ClientRegi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    public ClientRegi clientRegi;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       RegistrationEntity registrationEntity= clientRegi.findByEmailId(username).orElseThrow(()->new RuntimeException("user not found"));
        return registrationEntity;
    }
}
