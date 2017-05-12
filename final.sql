# To run it:
# mysql -u root -p < final.sql
# enter pwd: goodyear123!@#

DROP DATABASE IF EXISTS final;
CREATE DATABASE final;
USE final;
CREATE TABLE stockHistory(
	id INT AUTO_INCREMENT PRIMARY KEY,
	dates VARCHAR(255),
	opens VARCHAR(255),
	highs VARCHAR(255),
	lows VARCHAR(255),
	closes VARCHAR(255),
	volumes VARCHAR(255),
	adjClose VARCHAR(255)
);

