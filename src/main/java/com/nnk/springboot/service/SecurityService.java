package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Méthode permettant de mettre à jour les utilisateurs authentifier
     * @param user
     */
    public void updateSpringSecurityContext(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                authentication.getCredentials(),
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }

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
