/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;


import com.sg.OrderBook.entities.History;
import com.sg.OrderBook.entities.Order;
import com.sg.OrderBook.entities.Party;
import com.sg.OrderBook.entities.Stock;
import com.sg.OrderBook.entities.Trade;
import com.sg.OrderBook.repositories.HistoryRepository;
import com.sg.OrderBook.repositories.OrderRepository;
import com.sg.OrderBook.repositories.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author anmol
 */
@Service
public class ServiceLayer {
    @Autowired
    private HistoryRepository histories;
    
    @Autowired
    private OrderRepository orders;
    
    @Autowired
    private PartyRepository parties;
    
    @Autowired
    private StockRepository stocks;
    
    @Autowired
    private TradeRepository trades;
    
    
    //-----------------------ORDERS-----------------------------------------------------
    
    
    
    public List<Order> findAllOrders(){
        
      return orders.findAll();
        
    }
    
    
    public List<Order> findOrderByDatetimeOrderByDatetimeAsc(java.sql.Timestamp datetime){
        
        return orders.findByDatetimeOrderByDatetimeAsc(datetime);
       
    }
    
    public List<Order> findOrderBySide(String side){
        
        return orders.findBySide(side);
   
    }
    public List<Order> findOrderByStatus(String status){
        
        return orders.findByStatus(status);
    
    }
    public List<Order> findOrderByQuantity(int quantity){
       
        return orders.findByQuantity(quantity);
     
    }
    
    public Order findOrderById(int id){
       
        return  orders.findById(id).orElse(null);
        
        
       
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
    
    public List<Party> findByName(String Name){
        return parties.findByName(Name);
      }
    
  //-----------------------Stocks----------------------------------------------------- 
    
   public List<Stock> findAllStock(){
      
         return stocks.findAll();
       
    }
    
    
    public Stock findStockById(int id){
       
        return stocks.findById(id).orElse(null);
       
    }
    
    
    public void deleteStockById(int id){
       
         stocks.deleteById(id);
    
    }
    
    
    
     public <List>Stock findStockByPrice(BigDecimal price){
        return stocks.findByPrice(price);
     }
     
     
    public<List>Stock findStockByParty(Party id){
        return stocks.findByParty(id);
    }
    
    
    public <List>Stock findStockByTickSize(BigDecimal price){
        return stocks.findByTickSize(price);
    }
    
   //-----------------------Trades----------------------------------------------------- 
    
    public List<Trade> findAllTrades(){
      
         return trades.findAll();
       
    }
    
    public void makeTrade(Trade trade){
        trades.save(trade);
    }
    
    
    public Trade findTradeById(int id){
       
        return trades.findById(id).orElse(null);
       
    }
    
    
    public void deleteTradeById(int id){
       
         trades.deleteById(id);
    
    }
    
    public List<Trade> findTradeByBuyorder(Order order){
        return trades.findByBuyorder(order);
    }
    
    public List<Trade> findTradeBySellorder(Order order){
        
        return trades.findBySellorder(order);
    }
    public List<Trade> findTradeByStock(Stock stock){
        
        return trades.findByStock(stock);
    }
    
    
   
    
}
