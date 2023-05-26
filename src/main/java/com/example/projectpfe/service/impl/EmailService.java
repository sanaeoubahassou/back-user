package com.example.projectpfe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithIgg(String toEmail, String igg,String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("youssef20live@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Vos identifiants de connexion");
        message.setText("Bonjour, \n \n Voici vos identifiants de connexion : \n\n" +
                "IGG : " + igg + "\n" +
                "Mot de passe : " + password + "\n\n" +
                "Cordialement, \n L'équipe");
        javaMailSender.send(message);

    }
}
