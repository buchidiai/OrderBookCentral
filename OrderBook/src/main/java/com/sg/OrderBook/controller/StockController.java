/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.controller;

import com.sg.OrderBook.entities.Stock;
import com.sg.OrderBook.entities.StockOrder;
import com.sg.OrderBook.entities.Trade;
import com.sg.OrderBook.service.StockOrderService;
import com.sg.OrderBook.service.StockService;
import com.sg.OrderBook.service.TradeService;
import java.util.List;
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
    private StockOrderService orders;

    @Autowired
    private TradeService trades;

    @PostMapping("/addStock")
    public String addStock(Stock stock, Model model) {

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

        //make trades
        trades.makeStockTrade(stock);
        //get orders of stock
        List<StockOrder> buyOrder = orders.findOrdersByStockIdAndSideAndpriceDesc(stock, "BUY", "IN-PROGRESS");

        List<StockOrder> sellOrder = orders.findOrdersByStockIdAndSideAndpriceAsc(stock, "SELL", "IN-PROGRESS");

        List<Trade> stockTrades = trades.findTradeByStock(stock);

        //return errors
        model.addAttribute("errors", orders.getStockOrderViolations());

        System.out.println("orders.getStockOrderViolations() " + orders.getStockOrderViolations().size());

        model.addAttribute("buyOrders", buyOrder);
        model.addAttribute("sellOrders", sellOrder);
        model.addAttribute("trades", stockTrades);
        model.addAttribute("stock", stock);

        return "stockDetail";
    }

}
