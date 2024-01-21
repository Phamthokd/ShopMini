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
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `unit_price` double NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  KEY `FKinivj2k1370kw224lavkm3rqm` (`product_id`),
  CONSTRAINT `FKinivj2k1370kw224lavkm3rqm` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (106,1,90000,48,2,'Hoàn thành'),(107,1,35000,48,6,'Hoàn thành'),(108,1,59000,48,5,'Hoàn thành'),(109,1,85000,49,3,'Hoàn thành'),(110,1,300000,49,1,'Hoàn thành'),(111,1,90000,49,2,'Hoàn thành'),(112,1,130000,49,4,'Hoàn thành'),(113,1,59000,49,5,'Hoàn thành'),(114,1,150000,49,11,'Hoàn thành'),(115,4,300000,50,1,'Chờ xác nhận'),(116,1,59000,51,5,'Chờ xác nhận'),(118,1,59000,52,5,'Hoàn thành'),(119,1,195000,53,8,'Hoàn thành'),(120,1,300000,53,1,'Hoàn thành'),(121,1,59000,53,5,'Hoàn thành'),(122,1,85000,54,3,'Hoàn thành'),(123,1,59000,54,5,'Hoàn thành'),(124,1,85000,55,3,'Hoàn thành'),(125,1,130000,55,4,'Hoàn thành'),(126,1,300000,56,1,'Hoàn thành'),(127,1,150000,56,11,'Hoàn thành'),(128,1,130000,57,4,'Hoàn thành'),(129,1,90000,58,2,'Hoàn thành'),(130,1,85000,58,3,'Hoàn thành'),(131,1,150000,58,11,'Hoàn thành'),(132,1,300000,59,1,'Hoàn thành'),(133,1,59000,59,5,'Hoàn thành'),(134,1,300000,60,1,'Hoàn thành'),(135,1,59000,60,5,'Hoàn thành'),(136,1,150000,61,11,'Hoàn thành'),(137,1,90000,61,2,'Hoàn thành'),(138,1,130000,61,4,'Hoàn thành'),(139,1,300000,62,1,'Hoàn thành'),(140,1,130000,62,4,'Hoàn thành'),(141,1,300000,63,1,'Hoàn thành'),(142,1,130000,63,4,'Hoàn thành'),(143,1,150000,63,11,'Hoàn thành'),(144,1,300000,64,1,'Hoàn thành'),(145,1,90000,64,2,'Hoàn thành'),(146,1,85000,64,3,'Hoàn thành'),(147,1,90000,65,2,'Hoàn thành'),(148,1,300000,66,1,'Hoàn thành'),(149,1,300000,67,1,'Hoàn thành'),(150,1,85000,69,3,'Hoàn thành'),(151,1,300000,70,1,'Hoàn thành'),(152,1,300000,71,1,'Hoàn thành'),(153,1,130000,72,4,'Chờ xác nhận'),(154,1,24000,73,10,'Có đơn hàng'),(155,1,35000,74,6,'Có đơn hàng'),(156,1,85000,75,3,'Hoàn thành'),(157,1,24000,75,10,'Chuẩn bị hàng'),(158,1,35000,76,6,'Đã hủy'),(159,1,90000,77,2,'Chờ xác nhận');
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-22  0:38:44
