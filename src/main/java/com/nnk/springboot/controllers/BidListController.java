package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
public class BidListController {

    @Autowired
    private BidListService bidListService;

    /**
     * Méthode get de la page home de l'objet BidList
     * @param model
     * @return String
     */
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "bidList/list";
    }

    /**
     * Méthode get de la page add de l'objet BidList
     * @param model
     * @return String
     */
    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    /**
     * Méthode post de l'objet BidList
     * @param bid
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            bidListService.addBidList(bid);
            model.addAttribute("bidlists", bidListService.getBidLists());
            return "redirect:/bidList/list";
        }
        return "redirect:/bidList/add";
    }

    /**
     * Méthode get de la page d'update de l'objet BidList
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.getBidListById(id).orElseThrow(() -> new IllegalArgumentException("Invalide bidList id : " + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    /**
     * Méthode post de l'update de l'objet BidList
     * @param id
     * @param bidList
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/bidList/update";
        }
        bidList.setBidListId(id);
        bidListService.addBidList(bidList);
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "redirect:/bidList/list";
    }

    /**
     * Méthode get de la page de suppression de l'objet BidList
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.getBidListById(id).orElseThrow(() -> new IllegalArgumentException("Invalide bidList id : " + id));
        bidListService.deleteBidListById(bidList.getBidListId());
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "redirect:/bidList/list";
    }
}
