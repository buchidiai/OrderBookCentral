/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.sg.OrderBook.entities.OrderTransaction;
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
public class OrderTransactionServiceTest {
    
    @Autowired
    private OrderTransactionService service;
    
    @Autowired
    private StockService sservice;
    
    @Autowired
    private StockOrderService soservice;
    
    public OrderTransactionServiceTest() {
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
    public void testAllServiceMethods() {
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
        soservice.saveOrder(y);
        
        OrderTransaction t = new OrderTransaction();
        t.setDatetime(ts);
        t.setStockOrder(y);
        t.setTransactiontype("CREATED");
        t.setQuantity(100);
        service.saveOrderTransaction(t);
        
        List<OrderTransaction> ui= service.findByStockOrder(y);
        
        assertNotNull(ui.get(0));
        
    }
}