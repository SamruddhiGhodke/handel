package com.Handel_Version_one.Handel.Controller;

import com.Handel_Version_one.Handel.Entity.RegistrationEntity;
import com.Handel_Version_one.Handel.Model.LoginModel;
import com.Handel_Version_one.Handel.Repository.ClientRegi;
import com.Handel_Version_one.Handel.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController

@RequestMapping("/client")
public class ClientRegisteration {
    @Autowired
    ClientService clientService;

    @Autowired
    private ClientRegi clientRegi;
    @PostMapping("/registration")
    public RegistrationEntity addData(@RequestBody RegistrationEntity regi)
    {

        return this.clientService.addClientService(regi);
    }

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody LoginModel loginModel) {
            String emailId = loginModel.getEmailId();
            String password = loginModel.getPassword();

            RegistrationEntity user = clientRegi.findByEmailId(emailId);

            if (user != null && password.equals(user.getPassword())) {
                // Authentication successful
                return ResponseEntity.ok("Login successful!");
            } else {
                // Authentication failed
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }


    @GetMapping("/getRegistrationList")

    public List<RegistrationEntity> getAllData() {
        return clientRegi.findAll();

    }
    }



