/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.entities;

import javax.persistence.*;

/**
 *
 * @author anmol
 */
@Entity
public class History {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @OneToOne
    @JoinColumn(name = "tradeId", nullable = false )
    private Trade trade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }
    
    
    
}
