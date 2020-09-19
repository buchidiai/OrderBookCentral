/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.sg.OrderBook.entities.Order;
import com.sg.OrderBook.entities.Stock;
import com.sg.OrderBook.repositories.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zeenatbaig
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orders;

    public void saveOrder(Order order) {

        orders.save(order);

    }

    public List<Order> findAllOrders() {

        return orders.findAll();

    }

    public List<Order> findOrderStock(Stock stock) {

        return orders.findByStockOrderByPrice(stock);

    }

    public List<Order> findOrderByDatetimeOrderByDatetimeAsc(java.sql.Timestamp datetime) {

        return orders.findByDatetimeOrderByDatetimeAsc(datetime);

    }

    public List<Order> findOrderBySide(String side) {

        return orders.findBySide(side);

    }

    public List<Order> findOrderByStatus(String status) {

        return orders.findByStatus(status);

    }

    public List<Order> findOrderByQuantity(int quantity) {

        return orders.findByQuantity(quantity);

    }

    public Order findOrderById(int id) {

        return orders.findById(id).orElse(null);

    }

    public void deleteOrderById(int id) {

        orders.deleteById(id);

    }

}
