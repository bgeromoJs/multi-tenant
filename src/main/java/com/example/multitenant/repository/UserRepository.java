package com.example.multitenant.repository;

import com.example.multitenant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findById(Long idUser);

    User findByTxUser(String txUser);

    void deleteByTxUser(String txUser);
}
