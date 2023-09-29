package com.example.jwtProject.Repository;

import com.example.jwtProject.Entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<AdminEntity, Long> {
}
