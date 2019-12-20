[![Build Status](https://travis-ci.org/wangwei-ying/shorturl.svg?branch=master)](https://travis-ci.org/wangwei-ying/shorturl)  

### SHORTRUL SERVER

#### HOW TO START  

1. **DB INIT**  

**service have a sqlite default db**  

   ##### coustom db 
   
```sql
drop table IF exists `short_url`;
CREATE TABLE `short_url` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `SHORT_TOEKN` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'shorUrl',
  `URL` varchar(1024) NOT NULL,
  `HASH_CODE` int(11) DEFAULT NULL,
  `ENABLED` varchar(1) DEFAULT 'F',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `idx_su_sto` (`SHORT_TOEKN`) USING BTREE,
  KEY `idx_su_hc` (`HASH_CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='shortUrl';
 ```
 
   modify db config in **application.yml** 
 
2. **run ApplicationBootstarp.main**    

3. **curl http://127.0.0.1:8080/shortUrl?url=http://github.com** 

   get `{"status":200,"data":"http://127.0.0.1:8080/s/vqvuRw","msg":""}`  
   
4. **visit http://127.0.0.1:8080/s/vqvuRw**  
