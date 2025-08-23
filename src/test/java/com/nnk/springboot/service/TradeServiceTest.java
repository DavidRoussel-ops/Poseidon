package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TradeServiceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Autowired
    private TradeService tradeService;

    @Test
    public void testGetTrades() throws Exception {
        Iterable<Trade> allTrades = tradeService.getTrades();
        assertThat(allTrades).isNotNull();
    }

    @Test
    public void testAddTrade() throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Trade trade = new Trade("Account", "Type", 20.0);
        trade.setSellQuantity(20.0);
        trade.setBuyPrice(20.0);
        trade.setSellPrice(20.0);
        trade.setBenchmark("Benchmark");
        trade.setTradeDate(timestamp);
        trade.setSecurity("Security");
        trade.setStatus("Status");
        trade.setTrader("Trader");
        trade.setBook("Book");
        trade.setCreationName("CreationNane");
        trade.setCreationDate(timestamp);
        trade.setRevisionName("RevisionName");
        trade.setRevisionDate(timestamp);
        trade.setDealName("DealName");
        trade.setDealType("DealType");
        trade.setSourceListId("SourceList");
        trade.setSide("Side");
        tradeService.addTrade(trade);
        int lastId = 0;
        Iterable<Trade> allTrades = tradeService.getTrades();
        for (Trade trade1 : allTrades) {
            lastId = trade1.getTradeId();
        }
        assertThat(trade).isNotNull();
        assertEquals("Account", trade.getAccount());
        assertNotNull(trade.getSellQuantity());
        assertEquals(trade.getSellQuantity(), 20d, 20d);
        assertNotNull(trade.getBuyPrice());
        assertEquals(trade.getBuyPrice(), 20d, 20d);
        assertNotNull(trade.getSellPrice());
        assertEquals(trade.getSellPrice(), 20d, 20d);
        assertNotNull(trade.getBenchmark());
        assertEquals(trade.getBenchmark(), "Benchmark", "Benchmark");
        assertNotNull(trade.getTradeDate());
        assertNotNull(trade.getSecurity());
        assertEquals(trade.getSecurity(), "Security", "Security");
        assertNotNull(trade.getStatus());
        assertEquals(trade.getStatus(), "Status", "Status");
        assertNotNull(trade.getTrader());
        assertEquals(trade.getTrader(), "Trader", "Trader");
        assertNotNull(trade.getBook());
        assertEquals(trade.getBook(), "Book", "Book");
        assertNotNull(trade.getCreationName());
        assertEquals(trade.getCreationName(), "CreationNane", "CreationNane");
        assertNotNull(trade.getCreationDate());
        assertNotNull(trade.getRevisionName());
        assertEquals(trade.getRevisionName(), "RevisionName", "RevisionName");
        assertNotNull(trade.getRevisionDate());
        assertNotNull(trade.getDealName());
        assertEquals(trade.getDealName(), "DealName", "DealName");
        assertNotNull(trade.getDealType());
        assertEquals(trade.getDealType(), "DealType", "DealType");
        assertNotNull(trade.getSourceListId());
        assertEquals(trade.getSourceListId(), "SourceList", "SourceList");
        assertNotNull(trade.getSide());
        assertEquals(trade.getSide(), "Side", "Side");
        tradeService.deleteTradeById(lastId);
    }

    @Test
    public void testGetTradeById() throws Exception {
        Trade trade = new Trade("Account", "Type", 20.0);
        tradeService.addTrade(trade);
        Iterable<Trade> allTrades = tradeService.getTrades();
        int lastId = 0;
        for (Trade trade1 : allTrades) {
            lastId = trade1.getTradeId();
        }
        Optional<Trade> tradeOptional = tradeService.getTradeById(lastId);
        Trade trade2 = tradeOptional.get();
        assertThat(trade).isNotNull();
        assertEquals(lastId, trade2.getTradeId());
        tradeService.deleteTradeById(lastId);
    }

    @Test
    public void testDeleteTradeById() throws Exception {
        Trade trade = new Trade("Account", "Type", 20.0);
        tradeService.addTrade(trade);
        Iterable<Trade> allTrades = tradeService.getTrades();
        int lastId = 0;
        int counter = 0;
        for (Trade trade1 : allTrades) {
            lastId = trade1.getTradeId();
            counter ++;
        }
        counter --;
        Optional<Trade> tradeOptional = tradeService.getTradeById(lastId);
        Trade trade2 = tradeOptional.get();
        tradeService.deleteTradeById(trade2.getTradeId());
        assertThat(allTrades).isNotNull();
        assertEquals(0, counter);
    }
}
