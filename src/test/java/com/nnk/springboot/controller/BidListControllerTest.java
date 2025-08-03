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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
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
        when(bindingResult.hasErrors()).thenReturn(false);
        mockMvc.perform(post("/bidList/validate")
                        .param("account", "Account")
                        .param("type", "Type")
                        .param("bidQuantity", String.valueOf(20.0)))
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

    @Test
    @WithMockUser
    public void testShowUpdateForm() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(20.0);
        bidListService.addBidList(bidList);
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
        when(bidListService.getBidListById(anyInt())).thenReturn(Optional.ofNullable(mock(BidList.class)));
        mockMvc.perform(get("/bidList/update/" + 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"));
    }

    @Test
    @WithMockUser
    public void testUpdateBid() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(20.0);
        bidListService.addBidList(bidList);
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
        when(bidListService.getBidListById(anyInt())).thenReturn(Optional.ofNullable(mock(BidList.class)));
        mockMvc.perform(post("/bidList/update/" + 1)
                        .param("account", "Account")
                        .param("type", "Type")
                        .param("bidQuantity", String.valueOf(20.0)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    @WithMockUser
    public void testDeleteBid() throws Exception {
        BidList bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(20.0);
        bidListService.addBidList(bidList);
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
        when(bidListService.getBidListById(1)).thenReturn(Optional.of(bidList));
        doNothing().when(bidListService).deleteBidListById(anyInt());
        mockMvc.perform(get("/bidList/delete/" + 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

}
