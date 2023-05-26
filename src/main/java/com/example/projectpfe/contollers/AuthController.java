package com.example.projectpfe.contollers;

import com.example.projectpfe.dto.AuthDto;
import com.example.projectpfe.pojo.model.Auth;
import com.example.projectpfe.pojo.req.AuthReq;
import com.example.projectpfe.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")

public class AuthController {
    @Autowired
    AuthService authService;
/*
    @PostMapping("/create")
    public ResponseEntity<Auth> createUser(@RequestBody @Valid AuthReq authReq){
        return new ResponseEntity<>(authService.createAuth(authReq), HttpStatus.OK);
    }

 */
    @PostMapping("/login")
    //@CrossOrigin
    public ResponseEntity<AuthDto> login(@RequestBody @Valid AuthReq req) throws MethodArgumentNotValidException {
        return new ResponseEntity<>(authService.login(req),HttpStatus.OK);

    }


}
