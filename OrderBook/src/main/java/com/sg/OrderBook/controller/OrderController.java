/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.controller;

import com.sg.OrderBook.entities.Order;
import com.sg.OrderBook.service.OrderService;
import javax.validation.Valid;
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
    public String displayOrder(@Valid Order order, Model model, RedirectAttributes redirectAttributes) {

        System.out.println("order to strdf " + order.toString());

//        redirectAttributes.addAttribute("stockId", order.getStock().getId());
        return "redirect:stockDetail";
    }

    @GetMapping("/orderDetail")
    public String displayOrder(Model model, int orderId) {

        model.addAttribute("order", orders.findOrderById(orderId));

        model.addAttribute("orderTransactions", orders.findOrderById(orderId));
        return "orderDetails";
    }

}
