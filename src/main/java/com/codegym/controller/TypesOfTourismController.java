package com.codegym.controller;


import com.codegym.model.TypesOfTourism;
import com.codegym.service.typesOfTourism.ITypesOfTourismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/types")
public class TypesOfTourismController {
    @Autowired
    private ITypesOfTourismService iTypesOfTourismService;

    @GetMapping("/create")
    public String showFormCreate(){
        return "/typesOfTourism/create";

    }
    @GetMapping("/create")
    public String saveNew(TypesOfTourism typesOfTourism){
        iTypesOfTourismService.save(typesOfTourism);
        return "redirect:/types";
    }

    @PostMapping("")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("typesOfTourism/list");
        modelAndView.addObject("typesOfTourism", iTypesOfTourismService.findAll());
        return modelAndView;
    }
}
