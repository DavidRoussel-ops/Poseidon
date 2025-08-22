package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
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

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

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
    private UserRepository userRepository;

    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(username = "User", roles = "ADMIN")
    public void testHome() throws Exception {
        when(userService.getUsers()).thenReturn(List.of(mock(User.class)));
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    @Test
    @WithMockUser(username = "User", roles = "ADMIN")
    public void testAddUser() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    @WithMockUser(username = "User", roles = "ADMIN")
    public void testValidate() throws Exception {
        mockMvc.perform(post("/user/validate")
                        .param("username", "Test")
                        .param("password", "Test123!")
                        .param("fullname", "Test")
                        .param("role", "USER"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    @WithMockUser(username = "User", roles = "ADMIN")
    public void testNotValidate() throws Exception {
        mockMvc.perform(post("/user/validate"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/add"));
    }

    @Test
    @WithMockUser(username = "User", roles = "ADMIN")
    public void testShowUpdateForm() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setPassword("Test123!");
        user.setFullname("Test");
        user.setRole("USER");
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        mockMvc.perform(get("/user/update/" + 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser(username = "User", roles = "ADMIN")
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setPassword("Test1234!");
        user.setFullname("Test");
        user.setRole("USER");
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        mockMvc.perform(post("/user/update/" + 1)
                        .param("username", "Test")
                        .param("password", "Test123!")
                        .param("fullname", "Test")
                        .param("role", "USER"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    @WithMockUser(username = "User", roles = "ADMIN")
    public void testUpdateUserNotValidate() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setPassword("Test123!");
        user.setFullname("Test");
        user.setRole("USER");
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        mockMvc.perform(post("/user/update/" + 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/update"));
    }

    @Test
    @WithMockUser(username = "User", roles = "ADMIN")
    public void testDeleteUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setPassword("Test123!");
        user.setFullname("Test");
        user.setRole("USER");
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        doNothing().when(userService).deleteUserById(anyInt());
        mockMvc.perform(get("/user/delete/" + 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

}
