/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.sg.OrderBook.entities.StockOrder;
import com.sg.OrderBook.entities.Trade;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
public class TradeServiceTest {

    @Autowired
    public TradeService service;

    @Autowired
    public StockService sservice;

    @Autowired
    public StockOrderService soservice;

    @Autowired
    public OrderTransactionService otservice;

    public TradeServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        StockOrder buyOrder = new StockOrder();
        BigDecimal b = new BigDecimal("12.12");
        buyOrder.setQuantity(12);
        buyOrder.setSide("BUY");
        buyOrder.setPrice(b);
        buyOrder.setStatus("IN-PROGRESS");
        buyOrder.setStock(sservice.findStockById(1));
        String text = "2020-10-02 18:48:05.123";
        Timestamp ts = Timestamp.valueOf(text);
        buyOrder.setDatetime(ts);
        soservice.saveOrder(buyOrder);

        StockOrder sellOrder = new StockOrder();
        sellOrder.setQuantity(12);
        sellOrder.setSide("SELL");
        sellOrder.setPrice(b);
        sellOrder.setStatus("IN-PROGRESS");
        sellOrder.setStock(sservice.findStockById(1));
        sellOrder.setDatetime(ts);
        soservice.saveOrder(sellOrder);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testfindTradeById() {
        StockOrder buyOrder = new StockOrder();
        BigDecimal b = new BigDecimal("12.12");
        buyOrder.setQuantity(12);
        buyOrder.setSide("BUY");
        buyOrder.setPrice(b);
        buyOrder.setStatus("IN-PROGRESS");
        buyOrder.setStock(sservice.findStockById(1));
        String text = "2020-10-02 18:48:05.123";
        Timestamp ts = Timestamp.valueOf(text);
        buyOrder.setDatetime(ts);
        soservice.saveOrder(buyOrder);

        StockOrder sellOrder = new StockOrder();
        sellOrder.setQuantity(12);
        sellOrder.setSide("SELL");
        sellOrder.setPrice(b);
        sellOrder.setStatus("IN-PROGRESS");
        sellOrder.setStock(sservice.findStockById(1));
        sellOrder.setDatetime(ts);
        soservice.saveOrder(sellOrder);

        Trade z = new Trade();
        z.setBuyorder(buyOrder);
        z.setSellorder(sellOrder);
        z.setDatetime(ts);
        z.setPrice(b);
        z.setQuantity(12);
        z.setStock(sellOrder.getStock());

        Trade a = new Trade();

        service.makeTrade(z);
        service.saveTrade(z);
        int tradeid = z.getId();

        a = service.findTradeById(tradeid);
        assertNotNull(a);

        List<Trade> trades;
        trades = service.findTradeByBuyorder(buyOrder);
        Trade trade = trades.get(0);
        assertNotNull(trade);

        List<Trade> tradess = null;
        tradess = service.findTradeByStock(buyOrder.getStock());

        Trade trad = tradess.get(0); //nullpointerexception
        assertNotNull(trad);

    }

    @Test
    public void testMakeTradeBuy() {
        StockOrder buyOrder = new StockOrder();
        BigDecimal b = new BigDecimal("12.12");

        buyOrder.setQuantity(12);
        buyOrder.setSide("BUY");
        buyOrder.setPrice(b);
        buyOrder.setStatus("IN-PROGRESS");
        buyOrder.setStock(sservice.findStockById(1));

        String text = "2020-10-02 18:48:05.123";
        Timestamp ts = Timestamp.valueOf(text);

        buyOrder.setDatetime(ts);
        soservice.saveOrder(buyOrder);

        StockOrder sellOrder = new StockOrder();
        sellOrder.setQuantity(12);
        sellOrder.setSide("SELL");
        sellOrder.setPrice(b);
        sellOrder.setStatus("IN-PROGRESS");
        sellOrder.setStock(sservice.findStockById(1));
        sellOrder.setDatetime(ts);
        soservice.saveOrder(sellOrder);

        Trade z = new Trade();
        z.setBuyorder(buyOrder);
        z.setSellorder(sellOrder);
        z.setDatetime(ts);
        z.setPrice(b);
        z.setQuantity(12);
        z.setStock(sellOrder.getStock());

        service.saveTrade(z);

        Trade t = service.findTradeById(z.getId());

        assertNotNull(t);
    }

}
