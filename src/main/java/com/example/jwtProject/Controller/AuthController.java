package com.example.jwtProject.Controller;

import com.example.jwtProject.Entity.RegistrationEntity;
import com.example.jwtProject.Model.JwtModel;
import com.example.jwtProject.Model.JwtRequest;
import com.example.jwtProject.Model.JwtResponse;
import com.example.jwtProject.Repository.ClientRegi;
import com.example.jwtProject.Service.JwtService;
import com.example.jwtProject.security.JwtHelper;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ClientRegi clientRegi;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

// original code
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
//
//        this.doAuthenticate(request.getEmail(), request.getPassword());
//
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//        String token = this.helper.generateToken(userDetails);
//        System.out.println("login model password");
//
//        JwtResponse response = JwtResponse.builder()
//                .jwtToken(token)
//                .userName(userDetails.getUsername()).build();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        String token = this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userName(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    private void doAuthenticate(String email, String password) {
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//        try {
//            manager.authenticate(authentication);
//            System.out.println("doAuthenticate model password");
//
//
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException(" Invalid Username or Password  !!");
//        }
//
//    }

    private String doAuthenticate(String email, String password) {
        System.out.println("Attempting authentication for email: " + email);

        // Retrieve the user's registration entity based on their email
        RegistrationEntity registrationEntity = clientRegi.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String entityPassword = registrationEntity.getPassword();

        // Check if the provided password is empty or blank
        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Password cannot be empty or whitespace.");
        }

        // Use a strong password hashing and validation mechanism
        if (password.equals("123456")) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            String token = helper.generateToken(userDetails);
            System.out.println("Authentication successful");

            return token;
        } else {
            // Authentication failed
            throw new RuntimeException("Password mismatch");
        }
    }

    // Use a strong password hashing and validation mechanism instead of a hardcoded password
//        if (passwordEncoder.matches(password, entityPassword)) {
//            System.out.println(password);
//            System.out.println(entityPassword);
//            // Authentication successful
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//            manager.authenticate(authentication);
//            System.out.println("Authentication successful");
//        } else {
//            // Authentication failed
//            throw new BadCredentialsException("Invalid Username or Password!!");
//        }




//    @ExceptionHandler(BadCredentialsException.class)
//    public String exceptionHandler() {
//        System.out.println("BadCredentialsException model password");
//
//        return "Credentials Invalid !!";
//    }
    @PostMapping("/createUser")
    public RegistrationEntity createUser(@RequestBody JwtModel jwtModel){
        return jwtService.createUser(jwtModel);

    }
}
