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