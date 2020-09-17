/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author anmol
 */
@Entity
public class Trade {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Column
    private int quantity;
    
    @Column
    private java.sql.Timestamp dateTime;
    
    @Column
    private BigDecimal price;
    
    
    @ManyToOne
    @JoinColumn(name = "stockId", nullable = false )
    private Stock stock;
    
    @ManyToOne
    @JoinColumn(name = "buyOrderId", nullable = false )
    private Order buyorder;
    
    @ManyToOne
    @JoinColumn(name = "sellOrderId", nullable = false )
    private Order sellorder;

    public Order getBuyorder() {
        return buyorder;
    }

    public void setBuyorder(Order buyorder) {
        this.buyorder = buyorder;
    }

    public Order getSellorder() {
        return sellorder;
    }

    public void setSellorder(Order sellorder) {
        this.sellorder = sellorder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
    
    
}
