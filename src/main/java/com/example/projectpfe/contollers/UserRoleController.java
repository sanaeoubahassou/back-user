package com.example.projectpfe.contollers;


import com.example.projectpfe.dto.UserRoleDto;
import com.example.projectpfe.pojo.model.UserRole;
import com.example.projectpfe.pojo.req.UserRoleReq;
import com.example.projectpfe.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("role")
@CrossOrigin("*")

public class UserRoleController {


    @Autowired
    UserRoleService userRoleService;

    @PostMapping("/create")
    public ResponseEntity<UserRole> create(@RequestBody @Valid UserRoleReq userRoleReq){
        return new ResponseEntity<>(userRoleService.createUserRole(userRoleReq), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<UserRoleDto> getUserRolebyId(@PathVariable("id") Long id){
        return new ResponseEntity<>(userRoleService.getUserRolebyId(id), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<Set<UserRoleDto>> allUserRolles(){
        return new ResponseEntity<>(userRoleService.allUserRolles(), HttpStatus.OK);
    }



}
