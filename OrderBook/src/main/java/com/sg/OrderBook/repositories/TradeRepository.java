/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.repositories;

import com.sg.OrderBook.entities.Order;
import com.sg.OrderBook.entities.Stock;
import com.sg.OrderBook.entities.Party;
import com.sg.OrderBook.entities.Trade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anmol
 */
public interface TradeRepository extends JpaRepository<Stock, Integer>{
    List<Trade> findByBuyorder(Order order);
    List<Trade> findBySellorder(Order order);
    List<Trade> findByStock(Stock stock);
}
