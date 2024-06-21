package com.example.multitenant.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import com.example.multitenant.model.User;
import com.example.multitenant.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    public void save(User user){
        userRepository.save(user);
    }

    public List<User> getAll() throws SQLException {
        return userRepository.findAll();

    }

    public User get(Long id){
        return userRepository.findById(id).get();
    }

    public User getByTxUser(String txUser){
        return userRepository.findByTxUser(txUser);
    }

    public void delete(String txUser){
        userRepository.deleteByTxUser(txUser);
    }
}
