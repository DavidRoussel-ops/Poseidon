package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {

    @Autowired
    private TradeService tradeService;

    /**
     * Méthode get de la page home de l'objet Trade
     * @param model
     * @return String
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("trades", tradeService.getTrades());
        return "trade/list";
    }

    /**
     * Méthode get de la page add de l'objet Trade
     * @param model
     * @return String
     */
    @GetMapping("/trade/add")
    public String addTradeForm(Model model) {
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    /**
     * Méthode post de l'objet Trade
     * @param trade
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.addTrade(trade);
            model.addAttribute("trades", tradeService.getTrades());
            return "redirect:/trade/list";
        }
        return "redirect:/trade/add";
    }

    /**
     * Méthode get de la page d'update de l'objet Trade
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTradeById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade id : " + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    /**
     * Méthode post de la page d'update de l'objet Trade
     * @param id
     * @param trade
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/trade/update";
        }
        trade.setTradeId(id);
        tradeService.addTrade(trade);
        model.addAttribute("trades", tradeService.getTrades());
        return "redirect:/trade/list";
    }

    /**
     * Méthode get de la page de suppression de l'objet Trade
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTradeById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade id : " + id));
        tradeService.deleteTradeById(trade.getTradeId());
        model.addAttribute("trades", tradeService.getTrades());
        return "redirect:/trade/list";
    }
}
