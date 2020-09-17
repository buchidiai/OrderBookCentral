/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.entities;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author anmol
 */

@Entity
public class Stock {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Column
    private BigDecimal ticksize;
    
    @Column
    private BigDecimal price;
    
    @ManyToOne
    @JoinColumn(name = "partyId", nullable = false )
    private Party party;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTicksize() {
        return ticksize;
    }

    public void setTicksize(BigDecimal ticksize) {
        this.ticksize = ticksize;
    }

   

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", ticksize=" + ticksize + ", price=" + price + ", party=" + party + '}';
    }
    
    
    
}
