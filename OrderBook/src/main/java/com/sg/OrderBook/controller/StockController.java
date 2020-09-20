/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.controller;

import com.sg.OrderBook.entities.Party;
import com.sg.OrderBook.entities.Stock;
import com.sg.OrderBook.service.OrderService;
import com.sg.OrderBook.service.PartyService;
import com.sg.OrderBook.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author anmol
 */
@Controller
public class StockController {

    @Autowired
    private StockService stocks;

    @Autowired
    private PartyService partys;

    @Autowired
    private OrderService orders;

    @PostMapping("/addStock")
    public String addStock(Stock stock, Party party, Model model) {

        partys.saveParty(party);

        stock.setParty(party);

        stocks.saveStock(stock);

        return "redirect:stocks";
    }

    @GetMapping("/stocks")
    public String displayStocks(Model model) {

        model.addAttribute("stocks", stocks.findAllStock());
        return "stocks";
    }

    @GetMapping("/stockDetail")
    public String displayStockDetails(Model model, int stockId) {

        Stock stock = stocks.findStockById(stockId);

        model.addAttribute("stock", stock);

        return "stockDetail";
    }

}
