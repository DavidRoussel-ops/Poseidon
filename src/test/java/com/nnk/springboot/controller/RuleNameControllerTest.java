package com.nnk.springboot.controller;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.RuleNameService;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RuleNameControllerTest {

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
    private RuleNameService ruleNameService;

    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser
    public void testHome() throws Exception {
        ArrayList<RuleName> ruleNames = new ArrayList<>();
        RuleName ruleName = new RuleName();
        ruleNames.add(ruleName);
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
        when(ruleNameService.getRuleNames()).thenReturn(ruleNames);
        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleNames"));
    }

    @Test
    @WithMockUser
    public void testAddRuleName() throws Exception {
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
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleName"));
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
        mockMvc.perform(post("/ruleName/validate")
                        .param("name", "name")
                        .param("description", "description")
                        .param("json", "json")
                        .param("template", "template")
                        .param("sqlStr", "sqlStr")
                        .param("sqlPart", "sqlPart"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
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
        mockMvc.perform(post("/ruleName/validate"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/add"));
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
        when(ruleNameService.getRuleNameById(anyInt())).thenReturn(Optional.ofNullable(mock(RuleName.class)));
        mockMvc.perform(get("/ruleName/update/" + 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleName"));
    }

    @Test
    @WithMockUser
    public void testUpdateRuleName() throws Exception {
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
        when(ruleNameService.getRuleNameById(anyInt())).thenReturn(Optional.ofNullable(mock(RuleName.class)));
        mockMvc.perform(post("/ruleName/update/" + 1)
                        .param("name", "name")
                        .param("description", "description")
                        .param("json", "json")
                        .param("template", "template")
                        .param("sqlStr", "sqlStr")
                        .param("sqlPart", "sqlPart"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    @WithMockUser
    public void testDeleteRuleName() throws Exception {
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
        when(ruleNameService.getRuleNameById(anyInt())).thenReturn(Optional.ofNullable(mock(RuleName.class)));
        mockMvc.perform(get("/ruleName/delete/" + 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }
}
