/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.sg.OrderBook.entities.Stock;
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
public class StockServiceTest {
    
    @Autowired
    private StockService service;
    
    public StockServiceTest() {
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
    public void testFindAll() {
        List<Stock> b;
        b=service.findAllStock();
        Stock first = b.get(0);
        assertEquals(first.getId(),1,"the stock id should be 1");
        
    }
    
    @Test
    public void testFindStockById(){
        Stock x = service.findStockById(1);
        assertEquals(x.getId(),1,"should should be id 1");
    }
    @Test
    public void testSaveStockAndDeletestock(){
        Stock x = new Stock();
        x.setName("anmolcorp");
        x.setSymbol("ANCO");
        service.saveStock(x);
        int num = x.getId();
        Stock y = service.findStockById(num);
        assertEquals(x,y);
        service.deleteStockById(num);
        assertNull(service.findStockById(num));
        
    }
    
}
