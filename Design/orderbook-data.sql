use orderBook;


-- INSERT INTO party (name,symbol) VALUES ('AAPL','APPLE');
-- INSERT INTO party (name,symbol) VALUES ( 'Amazon','AMAZN');
-- INSERT INTO party (name,symbol) VALUES ( 'Netflix','NFLX');
-- INSERT INTO party (name,symbol) VALUES ('Tesla','TSLA');
-- INSERT INTO party (name,symbol) VALUES ('Walmart','WMT');
-- INSERT INTO party (name,symbol) VALUES (' NVIDIA Corporation','NVDA');
-- INSERT INTO party (name,symbol) VALUES ('JPMorgan Chase & Co.','JPM');
-- INSERT INTO party (name,symbol) VALUES ('Google','GOOG');
-- INSERT INTO party (name,symbol) VALUES ('Alibaba Group Holding Limited','BABA');


-- INSERT INTO stock (price,tickSize,party_id) VALUES ('20', '0.01', '1');
-- INSERT INTO stock (price,tickSize,party_id) VALUES ('900', '0.2', '2');
-- INSERT INTO stock (price,tickSize,party_id) VALUES ('820', '0.34', '3');
-- INSERT INTO stock (price,tickSize,party_id) VALUES ('987', '0.34', '4');
-- INSERT INTO stock (price,tickSize,party_id) VALUES ('2000', '0.3', '5');
-- INSERT INTO stock (price,tickSize,party_id) VALUES ('200', '0.04', '6');
-- INSERT INTO stock (price,tickSize,party_id) VALUES ('2730', '0.23', '7');
-- INSERT INTO stock (price,tickSize,party_id) VALUES ('6700', '0.23', '8');
-- INSERT INTO stock (price,tickSize,party_id) VALUES ('765', '0.22', '9');


INSERT INTO `order` (side,status,stock_id,quantity,dateTime) VALUES ('SELL', 'IN-PROGRESS', '2', '350','2020-08-16 23:02:51.140233');
