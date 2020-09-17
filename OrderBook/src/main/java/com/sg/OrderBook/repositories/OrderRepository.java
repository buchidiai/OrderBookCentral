/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.repositories;


import com.sg.OrderBook.entities.History;
import com.sg.OrderBook.entities.History;
import com.sg.OrderBook.entities.Order;
import com.sg.OrderBook.entities.Party;
import com.sg.OrderBook.entities.Party;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anmol
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
    List<Order> findByDatetimeOrderByDatetimeAsc(java.sql.Timestamp datetime);
    List<Order> findBySide(String side);
    List<Order> findByStatus(String status);
    List<Order> findByQuantity(int quantity);
    
    
}
