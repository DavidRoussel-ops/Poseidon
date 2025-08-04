package com.nnk.springboot.controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.CurvePointService;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CurveControllerTest {

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
    private CurvePointService curvePointService;

    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser
    public void testHome() throws Exception {
        ArrayList<CurvePoint> curvePoints = new ArrayList<>();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);
        curvePoints.add(curvePoint);
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
        when(curvePointService.getCurvePoints()).thenReturn(curvePoints);
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoints"));
    }

    @Test
    @WithMockUser
    public void testAddCurvePointForm() throws Exception {
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
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"));
    }

    @Test
    @WithMockUser
    public void testValidate() throws Exception {
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
        mockMvc.perform(post("/curvePoint/validate")
                        .param("term", String.valueOf(10.0))
                        .param("value", String.valueOf(20.0)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    @WithMockUser
    public void testValidateNull() throws Exception {
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
        mockMvc.perform(post("/curvePoint/validate"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/add"));
    }

    @Test
    @WithMockUser
    public void testShowUpdateForm() throws Exception {
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
        when(curvePointService.getCurvePointById(anyInt())).thenReturn(Optional.ofNullable(mock(CurvePoint.class)));
        mockMvc.perform(get("/curvePoint/update/" + 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"));
    }

    @Test
    @WithMockUser
    public void testUpdateCurvePoint() throws Exception {
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
        when(curvePointService.getCurvePointById(anyInt())).thenReturn(Optional.ofNullable(mock(CurvePoint.class)));
        mockMvc.perform(post("/curvePoint/update/" + 1)
                        .param("term", String.valueOf(10.0))
                        .param("value", String.valueOf(20.0)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    @WithMockUser
    public void testDeleteCurvePoint() throws Exception {
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
        when(curvePointService.getCurvePointById(anyInt())).thenReturn(Optional.ofNullable(mock(CurvePoint.class)));
        doNothing().when(curvePointService).deleteCurvePointById(anyInt());
        mockMvc.perform(get("/curvePoint/delete/" + 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }


}
