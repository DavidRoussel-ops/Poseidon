package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.BidListService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BidListControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @MockBean
    private BindingResult bindingResult;

    @MockBean
    private SecurityService securityService;

    @MockBean
    private UserService userService;

    @MockBean
    private BidListService bidListService;

    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser
    public void testHome() throws Exception {
        ArrayList<BidList> bidLists = new ArrayList<>();
        BidList bidList = new BidList();
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(20.0);
        bidLists.add(bidList);
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
        when(bidListService.getBidLists()).thenReturn(bidLists);
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidLists"));
    }

    @Test
    @WithMockUser
    public void testAddBidForm() throws Exception {
        ArrayList<BidList> bidLists = new ArrayList<>();
        BidList bidList = new BidList();
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(20.0);
        bidLists.add(bidList);
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
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"));
    }

    @Test
    @WithMockUser
    public void testValidate() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(20.0);
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
        when(bidListService.addBidList(bidList)).thenReturn(bidList);
        mockMvc.perform(post("/bidList/validate"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    @WithMockUser
    public void testValidateNull() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("");
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
        mockMvc.perform(post("/bidList/validate"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/add"));
    }

}
