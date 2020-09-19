/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.controller;

import com.sg.OrderBook.entities.Stock;
import com.sg.OrderBook.service.OrderService;
import com.sg.OrderBook.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author anmol
 */
@Controller
public class StockController {

    @Autowired
    private StockService stocks;

    @Autowired
    private OrderService orders;

    @GetMapping("/stocks")
    public String displayStocks(Model model) {

        model.addAttribute("stocks", stocks.findAllStock());
        return "stocks";
    }

    @GetMapping("/stockDetail")
    public String displayStockDetails(Model model, int stockId) {

        Stock stock = stocks.findStockById(stockId);

        model.addAttribute("stock", stock);

        model.addAttribute("orders", orders.findOrderStock(stock));

        System.out.println(" orders.findOrderStock(stock) " + orders.findOrderStock(stock));

        return "stockDetail";
    }

}
