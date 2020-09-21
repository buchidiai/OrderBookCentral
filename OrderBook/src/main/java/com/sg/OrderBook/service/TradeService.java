/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.OrderBook.service;

import com.sg.OrderBook.entities.Stock;
import com.sg.OrderBook.entities.StockOrder;
import com.sg.OrderBook.entities.Trade;
import com.sg.OrderBook.repositories.TradeRepository;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anmol
 */
@Service
public class TradeService {

    @Autowired
    private TradeRepository trades;
    
     @Autowired
    private StockOrderService orders;
     
      

    public List<Trade> findAllTrades() {

        return trades.findAll();

    }

    public void makeTrade(Trade trade) {
        trades.save(trade);
    }

    public Trade findTradeById(int id) {

        return trades.findById(id).orElse(null);

    }

    public void deleteTradeById(int id) {

        trades.deleteById(id);

    }

    public List<Trade> findTradeByBuyorder(StockOrder order) {
        return trades.findByBuyorder(order);
    }

    public List<Trade> findTradeBySellorder(StockOrder order) {

        return trades.findBySellorder(order);
    }

    public List<Trade> findTradeByStock(Stock stock) {
        return trades.findByStockIdOrderByDatetimeAsc(stock.getId());
    }

    public void saveTrade(Trade trade) {
        trades.save(trade);
    }

    
     public void makeStockTrade(Stock stock) {
        Trade trade = new Trade();

        List<StockOrder> buyOrders = orders.findByStockAndSideOrderByDatetimeAsc(stock, "BUY");
        List<StockOrder> sellOrders = orders.findByStockAndSideOrderByDatetimeAsc(stock, "SELL");

        List<StockOrder> inProgressOrders = orders.findByStockAndStatusOrderByDatetimeAsc(stock, "IN-PROGRESS");

        for (StockOrder order : inProgressOrders) {
            String side = order.getSide();

            if (side.equals("BUY")) {
               makeTradeBUY(order, sellOrders);
                
            }
            else if(side.equals("SELL")){
                
                makeTradeSELL( order,  buyOrders);
                
        }
        }
        
    }
     
     public void makeTradeBUY(StockOrder order, List<StockOrder> sellOrders) {
        Trade trade = new Trade();
        int updatedBuyQuantity = 0;
        int updatedSellQuantity = 0;

        BigDecimal price = order.getPrice();
        int quantity = order.getQuantity();
        Stock stock = order.getStock();

        //going through all the sell orders
        for (StockOrder sellOrder : sellOrders) {

            // information on the sell order 
            BigDecimal sellPrice = sellOrder.getPrice();
            int sellQuantity = sellOrder.getQuantity();

            //matching the price 
            if (sellPrice.compareTo(price) <= 0) {

                //validating the quantity
                if (quantity <= sellQuantity) {

                    //getting the quantity for the trade
                    updatedSellQuantity = sellQuantity - quantity;

                    //updating the quantity for each order
                    sellOrder.setQuantity(updatedSellQuantity);
                    order.setQuantity(0);

                    //updates status
                    order.setStatus("COMPLETED");
                    
                    if (quantity== sellQuantity) {
                        sellOrder.setStatus("COMPLETED");
                    }
                       //saves it to SQL
                    orders.saveOrder(order);
                    orders.saveOrder(sellOrder);
                
                    
                    

                    //creating the trade and populating the object
                    trade.setBuyorder(order);
                    trade.setSellorder(sellOrder);
                    trade.setPrice(sellPrice);
                    trade.setQuantity(quantity);
                    trade.setStock(stock);

                    //sets the date and time for trade
                    Date date = new Date();
                    Timestamp ts = new Timestamp(date.getTime());
                    trade.setDatetime(ts);

                    saveTrade(trade);

                } else if (sellQuantity < quantity) //getting the quantity for the trade   
                {
                    updatedBuyQuantity = quantity - sellQuantity;
                }

                //updating the quantity for each order
                sellOrder.setQuantity(0);
                order.setQuantity(updatedBuyQuantity);

                //updates status
                sellOrder.setStatus("COMPLETED");
                
                //saves it to SQL
                orders.saveOrder(order);
                orders.saveOrder(sellOrder);
                
                //creating the trade and populating the object
                trade.setBuyorder(order);
                trade.setSellorder(sellOrder);
                trade.setPrice(sellPrice);
                trade.setQuantity(sellQuantity);
                trade.setStock(stock);

                //sets the date and time for trade
                Date date = new Date();
                Timestamp ts = new Timestamp(date.getTime());
                trade.setDatetime(ts);
                saveTrade(trade);
            }

        }

       
    }

    public void makeTradeSELL(StockOrder order, List<StockOrder> buyOrders) {
        Trade trade = new Trade();
        int updatedBuyQuantity = 0;
        int updatedSellQuantity = 0;

        BigDecimal price = order.getPrice();
        int quantity = order.getQuantity();
        Stock stock = order.getStock();

        for (StockOrder buyOrder : buyOrders) {

            // information on the buy order 
            BigDecimal buyPrice = buyOrder.getPrice();
            int buyQuantity = buyOrder.getQuantity();
            

            //matching the price 
            if (buyPrice.compareTo(price) >= 0) {

                //validating the quantity
                if (quantity <= buyQuantity) {

                    //getting the quantity for the trade
                    updatedBuyQuantity = buyQuantity - quantity;

                    //updating the quantity for each order
                    buyOrder.setQuantity(updatedBuyQuantity);
                    order.setQuantity(0);

                    //updates status
                    order.setStatus("COMPLETED");
                    if (quantity == buyQuantity) {
                        buyOrder.setStatus("COMPLETED");
                    }
                    
                    //saves to SQL
                    orders.saveOrder(order);
                    orders.saveOrder(buyOrder);
                    
                    //creating the trade and populating the object
                    trade.setSellorder(order);
                    trade.setBuyorder(buyOrder);
                    trade.setPrice(buyPrice);
                    trade.setQuantity(quantity);
                    trade.setStock(stock);
                    //sets the date and time for trade
                    Date date = new Date();
                    Timestamp ts = new Timestamp(date.getTime());
                    trade.setDatetime(ts);
                    saveTrade(trade);

                } else if (buyQuantity < quantity) //getting the quantity for the trade   
                {
                    updatedSellQuantity = quantity - buyQuantity;
                }

                //updating the quantity for each order
                buyOrder.setQuantity(0);
                order.setQuantity(updatedSellQuantity);

                //sets status
                buyOrder.setStatus("COMPLETED");

                //creating the trade and populating the object
                trade.setSellorder(order);
                trade.setBuyorder(buyOrder);
                trade.setPrice(buyPrice);
                trade.setQuantity(buyQuantity);
                trade.setStock(stock);

                Date date = new Date();
                Timestamp ts = new Timestamp(date.getTime());
                trade.setDatetime(ts);
                saveTrade(trade);

            }

        }
        
    }
}
