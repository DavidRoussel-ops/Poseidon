package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.sql.Timestamp;


@Controller
public class BidListController {
    // TODO: Inject Bid service
    @Autowired
    private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if (!result.hasErrors()) {
            bidListService.addBidList(bid);
            //model.addAttribute("bidlists", bidListService.getBidLists());
            return "redirect:/bidList/list";
        }
        return "redirect:/bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        BidList bidList = bidListService.getBidListById(id).orElseThrow(() -> new IllegalArgumentException("Invalide bidList id : " + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListService.addBidList(bidList);
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        BidList bidList = bidListService.getBidListById(id).orElseThrow(() -> new IllegalArgumentException("Invalide bidList id : " + id));
        bidListService.deleteBidListById(bidList.getBidListId());
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "redirect:/bidList/list";
    }
}
