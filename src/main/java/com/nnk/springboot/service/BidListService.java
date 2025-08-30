package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidListService {

    private static final Logger logger = LoggerFactory.getLogger(BidListService.class);

    @Autowired
    private BidListRepository bidListRepository;

    /**
     * Méthode renvoyant toute les listes d'enchères
     * @return Iterable<BidList>
     */
    public Iterable<BidList> getBidLists() {
        return bidListRepository.findAll();
    }

    /**
     * Méthode renvoyant une liste d'enchère par son id
     * @param id
     * @return Optional<BidList>
     */
    public Optional<BidList> getBidListById(Integer id) {
        return bidListRepository.findById(id);
    }

    /**
     * Méthode d'ajout d'une liste d'enchère
     * @param bidList
     * @return BidList
     */
    @Transactional
    public BidList addBidList(BidList bidList) {
        logger.info("Ajout d'une liste d'enchère");
        return bidListRepository.save(bidList);
    }

    /**
     * Méthode de suppression d'une liste d'enchère par son id
     * @param id
     */
    public void deleteBidListById(Integer id) {
        bidListRepository.deleteById(id);
    }
}
