package com.example.projectpfe.service;


import com.example.projectpfe.dto.AuthDto;
import com.example.projectpfe.pojo.model.Auth;
import com.example.projectpfe.pojo.req.AuthReq;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthDto login(AuthReq req);
//    Auth createAuth(AuthReq authReq);


}
