package com.example.projectpfe.repository;

import com.example.projectpfe.pojo.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin("*")

public interface AuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findByIgg(String igg);
    @Query("SELECT a from Auth a where a.igg= ?1")
    Auth  getAuthByIgg(String igg);


}
