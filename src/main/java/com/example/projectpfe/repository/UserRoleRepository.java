package com.example.projectpfe.repository;

import org.springframework.data.jpa.repository.Query;
import com.example.projectpfe.pojo.model.UserRole;
import com.example.projectpfe.pojo.emuns.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.Optional;
import java.util.Set;

@Repository
@CrossOrigin("*")

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {


    Optional<UserRole> findByIdAndDeletedIsFalse(Long id);
    Set<UserRole> findByDeletedIsFalse();

     @Query("SELECT r from UserRole r where r.id= ?1 and r.deleted =false ")
     UserRole getUserRoleById(Long id);
    @Query("SELECT r from UserRole r where r.role= ?1 and r.deleted =false ")
    UserRole getUserRoleByRole(RoleEnum roleEnum);








}
