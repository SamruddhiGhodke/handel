package com.example.jwtProject.Util;

import com.example.jwtProject.Entity.RegistrationEntity;
import com.example.jwtProject.Repository.ClientRegi;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ClientRegi clientRegi;

    public void sendOtpEmail(String emailId, String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(emailId);
        mimeMessageHelper.setSubject("Verify OTP");
        mimeMessageHelper.setText("""
        <div>
          <a href="http://localhost:8097/verify-account?email=%s&otp=%s" target="_blank">click link to verify</a>
        </div>
        """.formatted(emailId, otp), true);

        javaMailSender.send(mimeMessage);
    }

    public void sendPasswordEmail(String emailId) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        RegistrationEntity registrationEntity = clientRegi.findByEmailId(emailId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String entityName = registrationEntity.getEntityName();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(emailId);
        mimeMessageHelper.setSubject("set password");

        String emailBody = String.format("Hi %s,<br><br>", entityName);
        emailBody += "Please click the link below to set your password:<br>";
        emailBody += String.format("<a href=\"http://localhost:8097/setPassword?emailid=%s\" target=\"http://localhost:4200/resetpassword\">Click here to set your password</a>", emailId);
        mimeMessageHelper.setText(emailBody, true);
        javaMailSender.send(mimeMessage);
    }
}
