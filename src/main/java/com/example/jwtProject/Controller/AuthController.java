package com.example.jwtProject.Controller;



import com.example.jwtProject.Entity.RegistrationEntity;
import com.example.jwtProject.Model.JwtModel;
import com.example.jwtProject.Model.JwtRequest;
import com.example.jwtProject.Model.JwtResponse;
import com.example.jwtProject.Repository.ClientRegi;
import com.example.jwtProject.Service.JwtService;
import com.example.jwtProject.security.JwtHelper;
import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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



    // API for user login
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        String token = this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userName(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Authentication logic
    private String doAuthenticate(String email, String password) {
        System.out.println("Attempting authentication for email: " + email);

        RegistrationEntity registrationEntity = clientRegi.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String entityPassword = registrationEntity.getPassword();

        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Password cannot be empty or whitespace.");
        }

        if (password.equals("123456")) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            String token = helper.generateToken(userDetails);
            System.out.println("Authentication successful");
            return token;
        } else {
            throw new RuntimeException("Password mismatch");
        }
    }

    // API for creating a new user
    @PostMapping("/createUser")
    public RegistrationEntity createUser(@RequestBody JwtModel jwtModel){
        return jwtService.createUser(jwtModel);
    }

    // API for uploading files and user data
    @PostMapping("/uploadFileAndUser")
    public ResponseEntity<RegistrationEntity> uploadFileAndUser(
            @RequestParam("gst") MultipartFile gst,
            @RequestParam("financial") MultipartFile financial,
            @ModelAttribute JwtModel jwtModel) {
        try {
            RegistrationEntity registrationEntity = jwtService.uploadFileAndUser(gst, financial, jwtModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(registrationEntity);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // API for downloading files
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        try {
            byte[] fileBytes = jwtService.downloadFile(fileName);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + fileName)
                    .body(fileBytes);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // API for handling forgotten passwords
    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestParam String emailId){
        return new ResponseEntity<>(jwtService.forgotPassword(emailId), HttpStatus.OK);
    }

    // API for setting a new password
    @PostMapping("/setPassword")
    public ResponseEntity<String> setPassword(@RequestParam String emailId, @RequestParam String newPassword) throws MessagingException {
        return new ResponseEntity<>(jwtService.setPassword(emailId,newPassword), HttpStatus.OK);
    }
}
