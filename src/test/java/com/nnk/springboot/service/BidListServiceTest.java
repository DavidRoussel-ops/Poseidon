package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BidListServiceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Autowired
    private BidListService bidListService;

    @Test
    public void testGetBidLists() throws Exception {
        Iterable<BidList> allBidLists = bidListService.getBidLists();
        int counter = 0;
        for (BidList bidList : allBidLists) {
            counter ++;
        }
        assertThat(allBidLists).isNotNull();
        Assertions.assertEquals(0, counter);
    }

    @Test
    public void testAddBidList() throws Exception {
        BidList bidList = new BidList();
        bidListService.addBidList(bidList);
        assertThat(bidList).isNotNull();
    }

    @Test
    public void testGetBidListById() throws Exception {
        Iterable<BidList> allBidLists = bidListService.getBidLists();
        int lastId = 0;
        for (BidList bidList : allBidLists) {
            lastId = bidList.getBidListId();
        }
        Optional<BidList> bidListOptional = bidListService.getBidListById(lastId);
        BidList bidList = bidListOptional.get();
        assertThat(bidList).isNotNull();
        Assertions.assertEquals(1, bidList.getBidListId());
    }

    @Test
    public void testDeleteBidListById() throws Exception {
        Iterable<BidList> allBidLists = bidListService.getBidLists();
        int lastId = 0;
        int counter = 0;
        for (BidList bidList : allBidLists) {
            lastId = bidList.getBidListId();
            counter ++;
        }
        counter --;
        Optional<BidList> bidListOptional = bidListService.getBidListById(lastId);
        BidList bidList = bidListOptional.get();
        bidListService.deleteBidListById(bidList.getBidListId());
        assertThat(allBidLists).isNotNull();
        Assertions.assertEquals(0, counter);
    }
}
