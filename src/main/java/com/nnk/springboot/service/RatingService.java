package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    private static final Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * Méthode renvoyant toute les notes
     * @return Iterable<Rating>
     */
    public Iterable<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    /**
     * Méthode renvoyant une note par son id
     * @param id
     * @return Optional<Rating>
     */
    public Optional<Rating> getRatingById(Integer id) {
        return ratingRepository.findById(id);
    }

    /**
     * Méthode d'ajout d'une note
     * @param rating
     * @return Rating
     */
    @Transactional
    public Rating addRating(Rating rating) {
        logger.info("Ajout d'une note");
        return ratingRepository.save(rating);
    }

    /**
     * Méthode de suppression d'une note par son id
     * @param id
     */
    public void deleteRatingById(Integer id) {
        ratingRepository.deleteById(id);
    }
}
