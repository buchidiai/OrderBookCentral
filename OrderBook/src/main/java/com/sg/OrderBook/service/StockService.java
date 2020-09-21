/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.sg.OrderBook.entities.Stock;
import com.sg.OrderBook.repositories.StockRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anmol
 */
@Service
public class StockService {

    @Autowired
    private StockRepository stocks;

    public List<Stock> findAllStock() {
        return stocks.findAll();
    }

    public Stock findStockById(int id) {
        return stocks.findById(id).orElse(null);
    }

    public void deleteStockById(int id) {
        stocks.deleteById(id);
    }

    public void saveStock(Stock stock) {
        stocks.save(stock);
    }

}
