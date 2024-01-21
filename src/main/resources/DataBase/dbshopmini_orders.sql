-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: dbshopmini
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commission` double NOT NULL,
  `customer_address` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `total_amount` double NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (48,4600,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',184000,4,'2023-10-14 01:32:58.719000'),(49,20350,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Thokd1','','0397327836',814000,4,'2023-11-14 01:58:35.636000'),(50,30000,'Vinh phuc1','Pham thuy huong','Giao hàng ngay','98765432101',1200000,5,'2023-12-14 23:21:33.790000'),(51,1475,'Vinh phuc','Pham xuân Thọ','123456789','03973278363',59000,15,'2023-12-16 16:06:08.167000'),(52,1850,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',74000,4,'2023-12-18 19:12:29.255000'),(53,13850,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',554000,4,'2023-12-28 16:29:19.064000'),(54,3600,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',144000,4,'2023-12-28 16:53:27.557000'),(55,5375,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',215000,4,'2023-12-28 17:08:28.451000'),(56,11250,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',450000,4,'2023-12-28 17:11:06.902000'),(57,3250,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',130000,4,'2023-12-28 17:16:39.289000'),(58,8125,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',325000,4,'2023-12-28 17:23:14.464000'),(59,8975,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',359000,4,'2023-12-28 17:29:32.472000'),(60,8975,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',359000,4,'2023-12-28 17:34:27.872000'),(61,9250,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',370000,4,'2023-12-28 17:36:07.853000'),(62,10750,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',430000,4,'2023-12-28 21:42:07.280000'),(63,14500,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',580000,4,'2023-12-28 21:58:49.270000'),(64,11875,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',475000,4,'2023-12-28 22:02:44.198000'),(65,2250,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',90000,4,'2023-12-28 22:03:38.135000'),(66,7500,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',300000,4,'2023-12-28 22:09:15.056000'),(67,7500,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',300000,4,'2023-12-28 22:10:07.298000'),(69,2125,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',85000,4,'2023-12-28 22:10:36.248000'),(70,7500,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',300000,4,'2023-12-28 22:10:59.997000'),(71,7500,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',300000,4,'2023-12-28 22:31:09.625000'),(72,3250,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',130000,4,'2024-01-01 16:44:23.936000'),(73,600,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',24000,4,'2024-01-01 16:44:50.280000'),(74,875,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','Pham Tho','','0397327836',35000,4,'2024-01-02 20:50:14.305000'),(75,2725,'Hà nội','Cô châu','Cô châu đạt hàng','0123456789',109000,5,'2024-01-02 20:58:42.231000'),(76,0,'Vinh phuc','Pham thuy huong','','9876543210',0,5,'2024-01-19 17:54:54.270000'),(77,2250,'Vinh phuc1','Pham thuy huong','Tôi chỉ nhận vào lúc 10 giờ sáng','03973278363',90000,5,'2024-01-19 18:05:05.442000');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-22  0:38:45
