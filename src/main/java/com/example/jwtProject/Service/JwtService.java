package com.example.jwtProject.Service;

import com.example.jwtProject.Entity.RegistrationEntity;
import com.example.jwtProject.Model.JwtModel;
import com.example.jwtProject.Repository.ClientRegi;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JwtService {
   @Autowired
    private ClientRegi clientRegi;
   @Autowired
   private PasswordEncoder passwordEncoder;



    public List<RegistrationEntity> getUser(){

        return clientRegi.findAll();
    }

    public RegistrationEntity createUser(JwtModel jwtModel) {
        Long id = jwtModel.getId();

        if (id != null && id != 0) {
            Optional<RegistrationEntity> registrationOptional = clientRegi.findById(id);

            if (registrationOptional.isPresent()) {
                RegistrationEntity registration = registrationOptional.get();
                registration.setEntityName(jwtModel.getEntityName());
                registration.setIecCode(jwtModel.getIecCode());
                registration.setGstCertificates(jwtModel.getGstCertificates());
                registration.setFinancialCertificates(jwtModel.getFinancialCertificates());
                registration.setUserName(jwtModel.getUserName());
                registration.setMobileNumber(jwtModel.getMobileNumber());
                registration.setEmailId(jwtModel.getEmailId());
                registration.setAccountNumber(jwtModel.getAccountNumber());
                registration.setBeneficiary(jwtModel.getBeneficiary());
                registration.setSwiftCode(jwtModel.getSwiftCode());

                registration.setPassword(passwordEncoder.encode(jwtModel.getPassword()));


                return clientRegi.save(registration);
            } else {
                throw new EntityNotFoundException("User with ID " + id + " not found");
            }
        } else {
            RegistrationEntity registrationEntity = new RegistrationEntity();

            registrationEntity.setEntityName(jwtModel.getEntityName());
            registrationEntity.setIecCode(jwtModel.getIecCode());
            registrationEntity.setGstCertificates(jwtModel.getGstCertificates());
            registrationEntity.setFinancialCertificates(jwtModel.getFinancialCertificates());
            registrationEntity.setUserName(jwtModel.getUserName());
            registrationEntity.setMobileNumber(jwtModel.getMobileNumber());
            registrationEntity.setEmailId(jwtModel.getEmailId());
            registrationEntity.setAccountNumber(jwtModel.getAccountNumber());
            registrationEntity.setBeneficiary(jwtModel.getBeneficiary());
            registrationEntity.setSwiftCode(jwtModel.getSwiftCode());

             registrationEntity.setPassword(passwordEncoder.encode(jwtModel.getPassword()));

            return clientRegi.save(registrationEntity);
        }
    }
}


