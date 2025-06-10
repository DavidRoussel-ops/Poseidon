package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        int counter = 0;
        for (Trade trade : allTrades) {
            counter ++;
        }
        assertThat(allTrades).isNotNull();
        Assertions.assertEquals(0, counter);
    }

    @Test
    public void testAddTrade() throws Exception {
        Trade trade = new Trade();
        tradeService.addTrade(trade);
        assertThat(trade).isNotNull();
    }

    @Test
    public void testGetTradeById() throws Exception {
        Iterable<Trade> allTrades = tradeService.getTrades();
        int lastId = 0;
        for (Trade trade : allTrades) {
            lastId = trade.getTradeId();
        }
        Optional<Trade> tradeOptional = tradeService.getTradeById(lastId);
        Trade trade = tradeOptional.get();
        assertThat(trade).isNotNull();
        Assertions.assertEquals(1, trade.getTradeId());
    }

    @Test
    public void testDeleteTradeById() throws Exception {
        Iterable<Trade> allTrades = tradeService.getTrades();
        int lastId = 0;
        int counter = 0;
        for (Trade trade : allTrades) {
            lastId = trade.getTradeId();
            counter ++;
        }
        counter --;
        Optional<Trade> tradeOptional = tradeService.getTradeById(lastId);
        Trade trade = tradeOptional.get();
        tradeService.deleteTradeById(trade.getTradeId());
        assertThat(allTrades).isNotNull();
        Assertions.assertEquals(0, counter);
    }
}
