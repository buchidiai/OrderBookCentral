/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.sg.OrderBook.entities.History;
import com.sg.OrderBook.entities.Party;
import com.sg.OrderBook.repositories.HistoryRepository;
import com.sg.OrderBook.repositories.OrderRepository;
import com.sg.OrderBook.repositories.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

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
    
    
    //-----------------------ORDERS-----------------------------------------------------
    
    public List<Order> findAllOrders(){
        
        return  orders.findAll();
        
    }
    
    
    public List<Order> findByDatetimeOrderByDatetimeAsc(java.sql.Timestamp datetime){
        List<Order> orderlist;
        orderlist = orders.findByDatetimeOrderByDatetimeAsc(datetime);
        return orderlist;
    }
    
    public List<Order> findBySide(String side){
        List<Order> orderlist;
        orderlist = orders.findBySide(side);
        return orderlist;
    }
    public List<Order> findByStatus(String status){
        List<Order> orderlist;
        orderlist = orders.findByStatus(status);
        return orderlist;
    }
    public List<Order> findByQuantity(int quantity){
        List<Order> orderlist;
        orderlist = orders.findByQuantity(quantity);
        return orderlist;
        
       
    }
    
    public Order findOrderById(int id){
       
        Order order = orders.findById(id).orElse(null);
        return order;
        
       
    }
    
    public void deleteOrderById(int id){
      
         orders.deleteById(id);
        
    }
    
    
    //-----------------------HISTORIES-----------------------------------------------------
   
     
    public List<History> findAllHistory(){
      
         return histories.findAll();
       
    }
    
    
     public History findHistoryById(int id){
       
        return histories.findById(id).orElse(null);
       
    }
    
    
    public History deleteHistoryById(int id){
       History history = histories.findById(id).orElse(null);
         histories.deleteById(id);
        return history;
  
    }
    
     //-----------------------Parties-----------------------------------------------------
    
     public List<Party> findAllParty(){
      
         return parties.findAll();
       
    }
    
    
     public Party findPartyById(int id){
       
        return parties.findById(id).orElse(null);
       
    }
    
    
    public void deletePartyById(int id){
       
         histories.deleteById(id);
    
    }
    
    
    
    
}
