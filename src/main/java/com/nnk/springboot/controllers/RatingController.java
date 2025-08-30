package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;

    /**
     * Méthode get de la page home de l'objet Rating
     * @param model
     * @return String
     */
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("ratings", ratingService.getRatings());
        return "rating/list";
    }

    /**
     * Méthode get de la page add de l'objet Rating
     * @param model
     * @return String
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        model.addAttribute("rating", new Rating());
        return "rating/add";
    }

    /**
     * Méthode post de l'objet Rating
     * @param rating
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingService.addRating(rating);
            model.addAttribute("ratings", ratingService.getRatings());
            return "redirect:/rating/list";
        }
        return "redirect:/rating/add";
    }

    /**
     * Méthode get de la page d'update de l'objet Rating
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating id : " + id));
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    /**
     * Méthode post de l'update de l'objet Rating
     * @param id
     * @param rating
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/rating/update";
        }
        rating.setId(id);
        ratingService.addRating(rating);
        return "redirect:/rating/list";
    }

    /**
     * Méthode get de la page de suppression de l'objet Rating
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating id : " + id));
        ratingService.deleteRatingById(rating.getId());
        model.addAttribute("ratings", ratingService.getRatings());
        return "redirect:/rating/list";
    }
}
