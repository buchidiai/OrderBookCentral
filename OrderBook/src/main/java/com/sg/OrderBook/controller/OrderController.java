/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.controller;

import com.sg.OrderBook.entities.Order;
import com.sg.OrderBook.service.OrderService;
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
    private OrderService orders;

    @PostMapping("/addOrder")
    public String addOrder(Order order, Model model, RedirectAttributes redirectAttributes, int stockId) {

        if (order.getSide().equals("1")) {
            order.setSide("BUY");
        } else if (order.getSide().equals("2")) {
            order.setSide("SELL");
        }

        System.out.println("order to strdf " + order.toString());

        //get stock
        //add stock to order object
        //save order
        //add to order transaction
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
