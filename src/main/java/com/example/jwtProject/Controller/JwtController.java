package com.example.jwtProject.Controller;

import com.example.jwtProject.Entity.RegistrationEntity;
import com.example.jwtProject.Model.JwtModel;
import com.example.jwtProject.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class JwtController {
    @Autowired
    JwtService jwtService;
    @GetMapping("/user")
    public List<RegistrationEntity> getUser(){
        System.out.println("getting user");
        return this.jwtService.getUser();

    }
    @GetMapping("/currentUser")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
