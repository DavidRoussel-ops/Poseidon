package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.RatingService;
import com.nnk.springboot.service.SecurityService;
import com.nnk.springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @MockBean
    private SecurityService securityService;

    @MockBean
    private UserService userService;

    @MockBean
    private RatingService ratingService;

    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser
    public void testHome() throws Exception {
        ArrayList<Rating> ratings = new ArrayList<>();
        Rating rating = new Rating();
        rating.setFitchRating("FitchRating");
        rating.setMoodysRating("MoodysRating");
        rating.setSandPRating("SandPRating");
        rating.setOrderNumber(1);
        ratings.add(rating);
        User user = new User();
        user.setFullname("test");
        user.setUsername("test");
        user.setPassword(encoder.encode("test"));
        user.setRole("ADMIN");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ADMIN", user);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(user.getUsername());
        when(securityService.isAuthenticated()).thenReturn(true);
        when(securityService.getCurrentUserDetails()).thenReturn(userDetails);
        when(userService.getUserByUsername(userDetails.getUsername())).thenReturn(user);
        when(ratingService.getRatings()).thenReturn(ratings);
        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ratings"));
    }

    @Test
    @WithMockUser
    public void testAddRatingForm() throws Exception {
        User user = new User();
        user.setFullname("test");
        user.setUsername("test");
        user.setPassword(encoder.encode("test"));
        user.setRole("ADMIN");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ADMIN", user);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(user.getUsername());
        when(securityService.isAuthenticated()).thenReturn(true);
        when(securityService.getCurrentUserDetails()).thenReturn(userDetails);
        when(userService.getUserByUsername(userDetails.getUsername())).thenReturn(user);
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"));
    }
}
