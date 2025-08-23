package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        BidList bidList = new BidList();
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(20.0);
        bidList.setAskQuantity(20.0);
        bidList.setBid(20.0);
        bidList.setAsk(20.0);
        bidList.setBenchmark("Benchmarck");
        bidList.setBidListData(timestamp);
        bidList.setCommentary("Commentary");
        bidList.setSecurity("Security");
        bidList.setStatus("Status");
        bidList.setTrader("Trader");
        bidList.setBook("Book");
        bidList.setCreationName("CreationNane");
        bidList.setCreationDate(timestamp);
        bidList.setRevisionName("RevisionName");
        bidList.setRevisionDate(timestamp);
        bidList.setDealName("DealName");
        bidList.setDealType("DealType");
        bidList.setSourceListId("SourceList");
        bidList.setSide("Side");
        bidListService.addBidList(bidList);
        int lastId = 0;
        Iterable<BidList> allBid = bidListService.getBidLists();
        for (BidList bidList1 : allBid) {
            lastId = bidList1.getBidListId();
        }
        assertThat(bidList).isNotNull();
        Assertions.assertNotNull(bidList.getAskQuantity());
        Assertions.assertEquals(bidList.getAskQuantity(), 20d, 20d);
        Assertions.assertNotNull(bidList.getBid());
        Assertions.assertEquals(bidList.getBid(), 20d, 20d);
        Assertions.assertNotNull(bidList.getAsk());
        Assertions.assertEquals(bidList.getAsk(), 20d, 20d);
        Assertions.assertNotNull(bidList.getBenchmark());
        Assertions.assertEquals("Benchmarck", bidList.getBenchmark());
        Assertions.assertNotNull(bidList.getBidListData());
        Assertions.assertNotNull(bidList.getCommentary());
        Assertions.assertEquals("Commentary", bidList.getCommentary());
        Assertions.assertNotNull(bidList.getSecurity());
        Assertions.assertEquals(bidList.getSecurity(), "Security", "Security");
        Assertions.assertNotNull(bidList.getStatus());
        Assertions.assertEquals(bidList.getStatus(), "Status", "Status");
        Assertions.assertNotNull(bidList.getTrader());
        Assertions.assertEquals(bidList.getTrader(), "Trader", "Trader");
        Assertions.assertNotNull(bidList.getBook());
        Assertions.assertEquals(bidList.getBook(), "Book", "Book");
        Assertions.assertNotNull(bidList.getCreationName());
        Assertions.assertEquals(bidList.getCreationName(), "CreationNane", "CreationNane");
        Assertions.assertNotNull(bidList.getCreationDate());
        Assertions.assertNotNull(bidList.getRevisionName());
        Assertions.assertEquals(bidList.getRevisionName(), "RevisionName", "RevisionName");
        Assertions.assertNotNull(bidList.getRevisionDate());
        Assertions.assertNotNull(bidList.getDealName());
        Assertions.assertEquals(bidList.getDealName(), "DealName", "DealName");
        Assertions.assertNotNull(bidList.getDealType());
        Assertions.assertEquals(bidList.getDealType(), "DealType", "DealType");
        Assertions.assertNotNull(bidList.getSourceListId());
        Assertions.assertEquals(bidList.getSourceListId(), "SourceList", "SourceList");
        Assertions.assertNotNull(bidList.getSide());
        Assertions.assertEquals(bidList.getSide(), "Side", "Side");
        bidListService.deleteBidListById(lastId);
    }

    @Test
    public void testGetBidListById() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(20.0);
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
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(20.0);
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
