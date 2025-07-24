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
        assertThat(allBidLists).isNotNull();
    }

    @Test
    public void testAddBidList() throws Exception {
        BidList bidList = new BidList();
        bidListService.addBidList(bidList);
        int lastId = 0;
        Iterable<BidList> allBid = bidListService.getBidLists();
        for (BidList bidList1 : allBid) {
            lastId = bidList1.getBidListId();
        }
        assertThat(bidList).isNotNull();
        bidListService.deleteBidListById(lastId);
    }

    @Test
    public void testGetBidListById() throws Exception {
        BidList bidList = new BidList();
        bidListService.addBidList(bidList);
        Iterable<BidList> allBidLists = bidListService.getBidLists();
        int lastId = 0;
        for (BidList bidList1 : allBidLists) {
            lastId = bidList1.getBidListId();
        }
        Optional<BidList> bidListOptional = bidListService.getBidListById(lastId);
        BidList bidList2 = bidListOptional.get();
        assertThat(bidList).isNotNull();
        Assertions.assertEquals(lastId, bidList2.getBidListId());
        bidListService.deleteBidListById(lastId);
    }

    @Test
    public void testDeleteBidListById() throws Exception {
        BidList bidList = new BidList();
        bidListService.addBidList(bidList);
        Iterable<BidList> allBidLists = bidListService.getBidLists();
        int lastId = 0;
        int counter = 0;
        for (BidList bidList1 : allBidLists) {
            lastId = bidList1.getBidListId();
            counter ++;
        }
        counter --;
        Optional<BidList> bidListOptional = bidListService.getBidListById(lastId);
        BidList bidList2 = bidListOptional.get();
        bidListService.deleteBidListById(bidList2.getBidListId());
        assertThat(allBidLists).isNotNull();
        Assertions.assertEquals(0, counter);
    }
}
