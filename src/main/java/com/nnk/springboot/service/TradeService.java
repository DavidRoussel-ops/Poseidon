package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeService {

    private static final Logger logger = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    private TradeRepository tradeRepository;

    /**
     * Méthode renvoyant une liste de tout les commerces
     * @return Iterable<Trade>
     */
    public Iterable<Trade> getTrades() {
        return tradeRepository.findAll();
    }

    /**
     * Méthode renvoyant un commerce par son id
     * @param id
     * @return Optional<Trade>
     */
    public Optional<Trade> getTradeById(Integer id) {
        return tradeRepository.findById(id);
    }

    /**
     * Méthode d'ajout d'un commerce
     * @param trade
     * @return Trade
     */
    @Transactional
    public Trade addTrade(Trade trade) {
        logger.info("Ajout d'un commerce");
        return tradeRepository.save(trade);
    }

    /**
     * Méthode de suppression d'un commerce par son id
     * @param id
     */
    public void deleteTradeById(Integer id) {
        tradeRepository.deleteById(id);
    }
}
