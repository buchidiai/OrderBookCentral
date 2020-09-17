/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.sg.OrderBook.entities.Order;
import com.sg.OrderBook.entities.Stock;
import com.sg.OrderBook.entities.Trade;
import com.sg.OrderBook.repositories.TradeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anmol
 */
@Service
public class TradeService {
    
    @Autowired
    private TradeRepository trades;
    
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
    public void saveTrade(Trade trade){
        trades.save(trade);
    }
    
}
