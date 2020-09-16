/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.controller;

import com.sg.OrderBook.service.ServiceLayer;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author anmol
 */
@Controller
public class OrderController {

    @Autowired
    ServiceLayer service;

    @GetMapping("stocks")
    public String displayLocations(Model model) {
        model.addAttribute("stocks", service.findAllStock());
       
        return "stocks";
    }
    
    
}
