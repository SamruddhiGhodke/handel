package com.Handel_Version_one.Handel.Entity;

import javax.persistence.*;

@Entity
@Table(name="Trader_Registration")
public class TraderRegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Client_id")
    private int id;

    @Column(name="entityName")
    private String entityName;
    @Column(name="iecCode")
    private String iecCode;
    @Column(name="gstCertificates")
    private String gstCertificates;
    @Column(name="financialCertificates")
    private String financialCertificates;
    @Column(name="userName")
    private String userName;
    @Column(name="mobileNumber")
    private Number mobileNumber;
    @Column(name="emailId")
    private String emailId;
    @Column(name="beneficiary")
    private String beneficiary;
    @Column(name="accountNumber")
    private String accountNumber;
    @Column(name="swiftCode")
    private String swiftCode;

    @Column(name="Password")
    private String password;

    public TraderRegistrationEntity() {
    }

    public TraderRegistrationEntity(int id, String entityName, String iecCode, String gstCertificates, String financialCertificates, String userName, Number mobileNumber, String emailId, String beneficiary, String accountNumber, String swiftCode, String password) {
        this.id = id;
        this.entityName = entityName;
        this.iecCode = iecCode;
        this.gstCertificates = gstCertificates;
        this.financialCertificates = financialCertificates;
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.beneficiary = beneficiary;
        this.accountNumber = accountNumber;
        this.swiftCode = swiftCode;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getIecCode() {
        return iecCode;
    }

    public void setIecCode(String iecCode) {
        this.iecCode = iecCode;
    }

    public String getGstCertificates() {
        return gstCertificates;
    }

    public void setGstCertificates(String gstCertificates) {
        this.gstCertificates = gstCertificates;
    }

    public String getFinancialCertificates() {
        return financialCertificates;
    }

    public void setFinancialCertificates(String financialCertificates) {
        this.financialCertificates = financialCertificates;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Number getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Number mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



