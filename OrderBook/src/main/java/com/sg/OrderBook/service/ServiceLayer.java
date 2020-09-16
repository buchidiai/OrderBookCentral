/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.sg.OrderBook.repositories.HistoryRepository;
import com.sg.OrderBook.repositories.OrderRepository;
import com.sg.OrderBook.repositories.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author anmol
 */
public class ServiceLayer {
    @Autowired
    HistoryRepository histories;
    
    @Autowired
    OrderRepository orders;
    
    @Autowired
    PartyRepository parties;
    
    @Autowired
    StockRepository stocks;
    
    @Autowired
    TradeRepository trades;
    
    public List<Order> findByDatetimeOrderByDatetimeAsc(java.sql.Timestamp datetime){
        List<Order> orderlist;
        
        orderlist = orders.findByDatetimeOrderByDatetimeAsc(datetime);
        return orderlist;
    }
    
    
    
}
