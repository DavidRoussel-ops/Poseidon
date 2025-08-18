package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
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
public class RatingServiceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    public void setUpMockMvc() { mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Autowired
    private RatingService ratingService;

    @Test
    public void testGetRatings() throws Exception {
        Iterable<Rating> allRatings = ratingService.getRatings();
        assertThat(allRatings).isNotNull();
    }

    @Test
    public void testAddRating() throws Exception {
        Rating rating = new Rating();
        rating.setMoodysRating("Moodys");
        rating.setSandPRating("Sand");
        rating.setFitchRating("Fitch");
        rating.setOrderNumber(20);
        Rating ratingToAdd = ratingService.addRating(rating);
        assertThat(ratingToAdd).isNotNull();
        ratingService.deleteRatingById(rating.getId());
    }

    @Test
    public void testGetRatingById() throws Exception {
        ratingService.addRating(new Rating("Moodys", "Sand", "Fitch", 20));
        Iterable<Rating> allRatings = ratingService.getRatings();
        int lastId = 0;
        for (Rating rating : allRatings) {
            lastId = rating.getId();
        }
        Optional<Rating> ratingOptional = ratingService.getRatingById(lastId);
        Rating rating = ratingOptional.get();
        assertThat(rating).isNotNull();
        Assertions.assertEquals(lastId, rating.getId());
        ratingService.deleteRatingById(lastId);
    }

    @Test
    public void testDeleteRatingById() throws Exception {
        ratingService.addRating(new Rating("Moodys", "Sand", "Fitch", 20));
        Iterable<Rating> allRatings = ratingService.getRatings();
        int lastId = 0;
        int counter = 0;
        for (Rating rating : allRatings) {
            lastId = rating.getId();
            counter ++;
        }
        counter --;
        Optional<Rating> ratingOptional = ratingService.getRatingById(lastId);
        Rating rating = ratingOptional.get();
        ratingService.deleteRatingById(rating.getId());
        assertThat(allRatings).isNotNull();
        Assertions.assertEquals(0, counter);
    }
}
