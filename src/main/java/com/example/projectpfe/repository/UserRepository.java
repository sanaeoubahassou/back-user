package com.example.projectpfe.repository;


import com.example.projectpfe.pojo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
import java.util.Set;

@Repository
@CrossOrigin("*")

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIggAndAndDeletedIsFalse(String igg);
    Set<User> findAllByDeletedIsFalseOrderByLastName();
    @Query("SELECT u from User u where u.id= ?1 and u.deleted =false ")
    User getUserById(Long id);
    @Query("SELECT u from User u where u.igg= ?1 and u.deleted =false ")
    User getUserByIgg(String igg);

    Optional<User> findByEmailAndDeletedIsFalse(String igg);

    @Query("SELECT u FROM User u where u.deleted=false order by u.firstName asc ")
    Page<User> allUers(Pageable pageable);

    Page<User> findAllByDeletedIsFalse(Pageable pageable);





}
