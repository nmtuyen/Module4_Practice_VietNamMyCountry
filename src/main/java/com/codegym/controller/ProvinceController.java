package com.codegym.controller;


import com.codegym.model.Province;
import com.codegym.service.province.IProvinceService;
import groovy.transform.PackageScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    private IProvinceService iProvinceService;

    @GetMapping("/create")
    public String showFormCreate(){
        return "/province/create";
    }


    @PostMapping("/create")
    public String save(Province province){
        iProvinceService.save(province);
        return "redirect:/provinces";
    }
    @GetMapping("")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces", iProvinceService.findAll());
        return modelAndView;
    }
}
