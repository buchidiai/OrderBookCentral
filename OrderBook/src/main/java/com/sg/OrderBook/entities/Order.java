/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.entities;

import java.sql.Timestamp;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder.In;

/**
 *
 * @author anmol
 */
 
	enum Status {
    INPROGRESS, COMPLETED, CANCELLED;
}
enum Side {
    BUY,SELL;
}

@Entity
public class Order {
    
  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    
       
    
    @Enumerated(EnumType.ORDINAL)
    private String status;
    
    @Enumerated(EnumType.ORDINAL)
    private String side;
    
    @Column
    private int quantity;
    
    @Column
    private java.sql.Timestamp dateTime;
    
    
    @ManyToOne
    @JoinColumn(name = "stockId", nullable = false )
    private Stock stock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

}
