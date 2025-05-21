package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Méthode renvoyant tout les utilisateurs
     * @return Iterable<User>
     */
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Méthode renvoyant un utilisateur par son id
     * @param id
     * @return Optional<User>
     */
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * Méthode d'ajout d'un utilisateur
     * @param user
     * @return User
     */
    @Transactional
    public User addUser(User user) {
        logger.info("Ajout d'un utilisateur");
        return userRepository.save(user);
    }

    /**
     * Méthode de suppression d'un utilisateur par son id
     * @param id
     */
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
