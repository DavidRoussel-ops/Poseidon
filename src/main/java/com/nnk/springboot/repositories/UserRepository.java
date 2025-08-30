package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * Méthode permettant de retrouver un utilisateur dans la BDD via son username
     * @param username
     * @return Utilisateur enregistrer en BDD
     */
    User findByUsername(String username);
}
