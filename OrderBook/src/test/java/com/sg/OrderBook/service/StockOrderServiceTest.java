/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.sg.OrderBook.entities.Stock;
import com.sg.OrderBook.entities.StockOrder;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author anmol
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockOrderServiceTest {
    
    @Autowired
    private StockOrderService service;
    @Autowired
    private StockService sservice;
    
    public StockOrderServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testfindOrdersByStockId() {
        Stock x = new Stock();
        x = sservice.findStockById(1);
        List<StockOrder> list;
        list = service.findOrdersByStockId(x);
        StockOrder order = list.get(0);
        Stock y = order.getStock();
        assertEquals(x,y);
    }
    
    @Test
    public void testfindOrdersbyStockPriceAsc(){
        Stock x = new Stock();
        x = sservice.findStockById(1);
        List<StockOrder> list;
        list = service.findOrdersByStockIdAndSideAndpriceAsc(x, "SELL", "COMPLETED");
        StockOrder order = list.get(0);
        Stock y = order.getStock();
        assertEquals(x,y);   
    }
    
    @Test
    public void testfindOrderByDatetimeOrderByDatetimeAsc(){
        
        
        StockOrder y = new StockOrder();
        BigDecimal b = new BigDecimal("12.12");
        y.setQuantity(12);
        y.setSide("BUY");
        y.setPrice(b);
        y.setStatus("IN-PROGRESS");
        y.setStock(sservice.findStockById(1));
        String text = "2020-10-02 18:48:05.123";
        Timestamp ts = Timestamp.valueOf(text);
        y.setDatetime(ts);
        service.saveOrder(y);
        Timestamp tss = y.getDatetime();
        System.out.println(tss);
        assertNotNull(tss);
    }
    
    
}
