/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.sg.OrderBook.entities.History;
import com.sg.OrderBook.repositories.HistoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zeenatbaig
 */
@Service
public class HistoryService {
    
    @Autowired
    private HistoryRepository histories;
    
    
     public List<History> findAllHistory(){
      
         return histories.findAll();
       
    }
     
     public void saveHistory(History history){
      
          histories.save(history);
       
    }
    
    
     public History findHistoryById(int id){
       
        return histories.findById(id).orElse(null);
       
    }
    
    
    public History deleteHistoryById(int id){
       History history = histories.findById(id).orElse(null);
         histories.deleteById(id);
        return history;
  
    }
}
