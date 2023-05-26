package com.example.projectpfe.service;


import com.example.projectpfe.dto.UserRoleDto;
import com.example.projectpfe.pojo.model.UserRole;
import com.example.projectpfe.pojo.req.UserRoleReq;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Set;

@Service
public interface UserRoleService {


    UserRole createUserRole (UserRoleReq userRoleReq);
    UserRoleDto getUserRolebyId(Long id);
    Set<UserRoleDto> allUserRolles();
    UserRoleDto createUserRole(UserRoleReq userRoleReq, Principal principal);



}
