package com.Handel_Version_one.Handel.Repository;

import com.Handel_Version_one.Handel.Entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRegi extends JpaRepository<RegistrationEntity,Integer> {

    RegistrationEntity findByEmailId(String emailId);
}
