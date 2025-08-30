package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {

    @Autowired
    private RuleNameService ruleNameService;

    /**
     * Méthode get de la page home de l'ojet RuleName
     * @param model
     * @return String
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute("ruleNames", ruleNameService.getRuleNames());
        return "ruleName/list";
    }

    /**
     * Méthode get de la page add de l'objet RuleName
     * @param model
     * @return String
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
        model.addAttribute("ruleName", new RuleName());
        return "ruleName/add";
    }

    /**
     * Méthode post de l'objet  RuleName
     * @param ruleName
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameService.addRuleName(ruleName);
            model.addAttribute("ruleNames", ruleNameService.getRuleNames());
            return "redirect:/ruleName/list";
        }
        return "redirect:/ruleName/add";
    }

    /**
     * Méthode get de la page d'update de l'objet RuleName
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleNameById(id).orElseThrow(() -> new IllegalArgumentException("Invalid RuleName id : " + id));
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    /**
     * Méthode post de la page d'update de l'objet RuleName
     * @param id
     * @param ruleName
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/ruleName/update";
        }
        ruleName.setId(id);
        ruleNameService.addRuleName(ruleName);
        model.addAttribute("ruleNames", ruleNameService.getRuleNames());
        return "redirect:/ruleName/list";
    }

    /**
     * Méthode get de la page de suppression de l'objet RuleName
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleNameById(id).orElseThrow(() -> new IllegalArgumentException("Invalid RuleName id : " + id));
        ruleNameService.deleteRuleNameById(ruleName.getId());
        model.addAttribute("ruleNames", ruleNameService.getRuleNames());
        return "redirect:/ruleName/list";
    }
}
