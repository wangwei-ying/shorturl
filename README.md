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

### HOW SERVER DO 

#### 生成短链接 
     利用数据库自增ID生成唯一字符串.
##### 唯一token生成算法
      1. 进制算法 
         将自增ID转化为52+后一位随机字符串. 6位字符串即可容纳十亿条链接.
         
```java
public String creatToken(Long id) {
    int binary = 52;
    String format = "DEFGHNOLMRSPQIJK0123456ABCefghiTUVW789XYZabcdjklmnop";
    //未满6位,填充随机字符串
    String randomseed = "qrstuvwxyz";
    StringBuilder stringBuilder = new StringBuilder();
    binary(id, binary, format, stringBuilder);
    stringBuilder.append(randomseed.charAt(ThreadLocalRandom.current().nextInt(9)));
    int minLength = 6;
    int length=stringBuilder.length();
    if (length < 6) {
        int add = 6-length;
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < add; i++) {
            sb2.append(randomseed.charAt(ThreadLocalRandom.current().nextInt(9)));
        }
        return sb2.append(stringBuilder).toString();
    }

    return stringBuilder.toString();
}
```





