package com.example.jwtProject.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Admin")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Admin_Id")
    private Long adminId;

    @Column(name="Admin_Email_Id")
    private String adminEmailId;

    @Column(name="Password")
    private String password;

    public AdminEntity() {
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminEmailId() {
        return adminEmailId;
    }

    public void setAdminEmailId(String adminEmailId) {
        this.adminEmailId = adminEmailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
