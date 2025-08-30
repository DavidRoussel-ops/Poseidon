package com.nnk.springboot.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    /**
     * Méthode permettant de vérifier l'authentification dans un context sécurisé
     * @return true si authentifier false si non
     */
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    /**
     * Méthode récupérant les détails utilisateurs
     * @return UserDetails
     */
    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object object = authentication.getPrincipal();
            if (object instanceof UserDetails) {
                return (UserDetails) object;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
