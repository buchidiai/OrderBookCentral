/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.controller;

import com.sg.OrderBook.entities.OrderTransaction;
import com.sg.OrderBook.entities.Stock;
import com.sg.OrderBook.entities.StockOrder;
import com.sg.OrderBook.service.OrderTransactionService;
import com.sg.OrderBook.service.StockOrderService;
import com.sg.OrderBook.service.StockService;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author louie
 */
@Controller
public class OrderController {

    @Autowired
    private StockOrderService orders;

    @Autowired
    private StockService stocks;

    @Autowired
    private OrderTransactionService orderTransactions;

    @PostMapping("/addOrder")
    public String addOrder(StockOrder stockOrder, Model model, RedirectAttributes redirectAttributes, int stockId) {

        if (stockOrder.getSide().equals("1")) {
            stockOrder.setSide("BUY");
        } else if (stockOrder.getSide().equals("2")) {
            stockOrder.setSide("SELL");
        }

        //get stock
        Stock stock = stocks.findStockById(stockId);

        //add stock to order object
        stockOrder.setStock(stock);
        //set status
        stockOrder.setStatus("IN-PROGRESS");

        //add time
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        stockOrder.setDatetime(ts);

        //save order
        orders.saveOrder(stockOrder);
        //add to order transaction

        OrderTransaction orderTransaction = new OrderTransaction();
        orderTransaction.setQuantity(stockOrder.getQuantity());
        orderTransaction.setDatetime(stockOrder.getDatetime());
        orderTransaction.setStockOrder(stockOrder);
        orderTransaction.setTransactiontype("CREATED");

        orderTransactions.saveOrderTransaction(orderTransaction);

        redirectAttributes.addAttribute("stockId", stockId);
        return "redirect:stockDetail";
    }

    @GetMapping("/orderDetail")
    public String displayOrder(Model model, int orderId) {

//        model.addAttribute("order", orders.findOrderById(orderId));
//
//        model.addAttribute("orderTransactions", orders.findOrderById(orderId));
        return "orderDetail";
    }

    @GetMapping("/cancelOrder")
    public String cancelOrder(Model model, int orderId, int stockId, RedirectAttributes redirectAttributes) {

        System.out.println("orderId " + orderId);
        System.out.println("stockId " + stockId);

//        //find order
//        Order order = orders.findOrderById(orderId);
//
//        // cancel order -> should cancel not delete
//        orders.deleteOrderById(stockId);
        // redirect to stock details
        redirectAttributes.addAttribute("stockId", stockId);

        return "redirect:stockDetail";
    }

}
