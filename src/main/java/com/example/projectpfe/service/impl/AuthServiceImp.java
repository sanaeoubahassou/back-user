package com.example.projectpfe.service.impl;


import com.example.projectpfe.dto.AuthDto;
import com.example.projectpfe.dto.DataToken;
import com.example.projectpfe.exception.ExceptionApi;
import com.example.projectpfe.exception.PayLoadExceptionItem;
import com.example.projectpfe.pojo.model.Auth;
import com.example.projectpfe.pojo.model.User;
import com.example.projectpfe.pojo.req.AuthReq;
import com.example.projectpfe.repository.AuthRepository;
import com.example.projectpfe.repository.UserRepository;
import com.example.projectpfe.security.JwtTokenUtil;
import com.example.projectpfe.security.SecurityConstants;
import com.example.projectpfe.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AuthServiceImp implements AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public AuthDto login(AuthReq req) {
        User user = userRepository.findByIggAndAndDeletedIsFalse(req.getIgg())
                .orElseThrow(
                        () ->{
                            throw new ExceptionApi(PayLoadExceptionItem.Auth_Error);
                        }
                );
        return createToken(user, req);
    }

    private  AuthDto createToken(User user, AuthReq req){
        if(passwordEncoder.matches(req.getPassword(), user.getPassword())){
            AuthDto authDto = new AuthDto();
            DataToken dataToken = new DataToken();

            try {
                System.out.println(user.getIgg());
                dataToken.setToken(jwtTokenUtil.generateToken(user.getIgg()));
                dataToken.setAuthId(user.getIgg());
            } catch (JsonProcessingException e){
                e.printStackTrace();
            }

            authDto.setData(dataToken);
            return authDto;
        }
        throw new ExceptionApi(PayLoadExceptionItem.Auth_Error);
    }


/*
    @Override
    public Auth createAuth(AuthReq authReq) {
        Auth auth = new Auth();
        auth.setIgg(authReq.getIgg());
        auth.setPassword(passwordEncoder.encode(authReq.getPassword()));
        authRepository.save(auth);

        return auth;

    }

 */


}