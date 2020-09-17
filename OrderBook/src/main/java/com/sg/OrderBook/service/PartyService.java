/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.sg.OrderBook.entities.History;
import com.sg.OrderBook.entities.Party;
import com.sg.OrderBook.repositories.PartyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anmol
 */
@Service
public class PartyService {
    @Autowired
    private PartyRepository parties;
    
    
    public List<Party> findAllParty(){
      
         return parties.findAll();
       
    }
    
    
    public Party findPartyById(int id){
       
        return parties.findById(id).orElse(null);
       
    }
    
    
    public void deletePartyById(int id){
       
         parties.deleteById(id);
    
    }
    
    public List<Party> findByName(String Name){
        return parties.findByName(Name);
      }
    
    public void saveParty(Party party){
        parties.save(party);
    }
    
    
}
