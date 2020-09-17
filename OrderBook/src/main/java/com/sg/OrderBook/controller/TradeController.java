/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author louie
 */
@Controller
public class TradeController {

    @GetMapping("/trades")
    public String TradePage(Model model) {
        return "trades";
    }

    @GetMapping("/tradeDetail")
    public String getTrade(Model model, int id) {
        System.out.println("id  is " + id);
        model.addAttribute("id", id);

        System.out.println("sending " + id);

        return "trades";
    }

}
