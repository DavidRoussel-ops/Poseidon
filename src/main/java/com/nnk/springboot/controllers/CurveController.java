package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveController {

    @Autowired
    private CurvePointService curvePointService;

    /**
     * Méthode get de la page home de l'objet CurvePoint
     * @param model
     * @return String
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        model.addAttribute("curvePoints", curvePointService.getCurvePoints());
        return "curvePoint/list";
    }

    /**
     * Méthode get de la page add de l'objet CurvePoint
     * @param model
     * @return String
     */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model) {
        model.addAttribute("curvePoint", new CurvePoint());
        return "curvePoint/add";
    }

    /**
     * Méthode post de l'objet CurvePoint
     * @param curvePoint
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            curvePointService.addCurvePoint(curvePoint);
            model.addAttribute("curvePoints", curvePointService.getCurvePoints());
            return "redirect:/curvePoint/list";
        }
        return "redirect:/curvePoint/add";
    }

    /**
     * Méthode get de la page d'udpate de l'objet CurvePoint
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.getCurvePointById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint id : " + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    /**
     * Méthode post de l'update de l'objet CurvePoint
     * @param id
     * @param curvePoint
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/curvePoint/update";
        }
        curvePoint.setId(id);
        curvePointService.addCurvePoint(curvePoint);
        model.addAttribute("curvePoints", curvePointService.getCurvePoints());
        return "redirect:/curvePoint/list";
    }

    /**
     * Méthode get de la page de suppression de l'objet CurvePoint
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.getCurvePointById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint id : " + id));
        curvePointService.deleteCurvePointById(curvePoint.getId());
        model.addAttribute("curvePoints", curvePointService.getCurvePoints());
        return "redirect:/curvePoint/list";
    }
}
