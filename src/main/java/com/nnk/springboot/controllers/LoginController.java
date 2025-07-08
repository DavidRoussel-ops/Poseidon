package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.SecurityService;
import com.nnk.springboot.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("app")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("login")
    public ModelAndView login(HttpSession httpSession) {
        ModelAndView mavLogin = new ModelAndView();
        mavLogin.setViewName("login");
        ModelAndView mavBidlist = new ModelAndView();
        mavBidlist.setViewName("bidList/list");
        if (!securityService.isAuthenticated()) {
            return mavLogin;
        }
        UserDetails userDetails = securityService.getCurrentUserDetails();
        if (userDetails == null) {
            return mavLogin;
        }
        User user = userService.getUserByUsername(userDetails.getUsername());
        httpSession.setAttribute("user", user);
        logger.info("Utilisateur connecter : {}", user);
        return mavBidlist;
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
