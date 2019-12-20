---------------------------- sqlite
DROP TABLE IF EXISTS `short_url`;
CREATE TABLE `short_url` (
                             `ID` INTEGER NOT NULL,
                             `SHORT_TOKEN` TEXT(20)  DEFAULT NULL ,
                             `URL` TEXT(1024) NOT NULL,
                             `HASH_CODE` INTEGER(11) DEFAULT NULL,
                             `ENABLED` TEXT(1) DEFAULT 'F',
                             PRIMARY KEY(ID ASC)
) ;
-- mysql
drop table IF exists `short_url`;
CREATE TABLE `short_url` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `SHORT_TOEKN` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短链接',
  `URL` varchar(1024) NOT NULL,
  `HASH_CODE` int(11) DEFAULT NULL,
  `ENABLED` varchar(1) DEFAULT 'F',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `idx_su_sto` (`SHORT_TOEKN`) USING BTREE,
  KEY `idx_su_hc` (`HASH_CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接';
