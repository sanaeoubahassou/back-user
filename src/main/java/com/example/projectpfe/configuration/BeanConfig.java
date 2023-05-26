package com.example.projectpfe.configuration;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {



    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper o = new ObjectMapper();
        o.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        o.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        o.registerModule(javaTimeModule);
        return o;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }




}
