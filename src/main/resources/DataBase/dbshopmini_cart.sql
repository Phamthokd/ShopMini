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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3d704slv66tw6x5hmbm6p2x3u` (`product_id`),
  KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`),
  CONSTRAINT `FK3d704slv66tw6x5hmbm6p2x3u` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (173,1,15000,7,4,'Thanh toán'),(174,1,59000,5,4,'Thanh toán'),(175,1,195000,8,4,'Thanh toán'),(176,1,300000,1,4,'Thanh toán'),(177,1,59000,5,4,'Thanh toán'),(178,1,85000,3,4,'Thanh toán'),(179,1,59000,5,4,'Thanh toán'),(180,1,85000,3,4,'Thanh toán'),(181,1,130000,4,4,'Thanh toán'),(182,1,300000,1,4,'Thanh toán'),(183,1,150000,11,4,'Thanh toán'),(184,1,130000,4,4,'Thanh toán'),(185,1,90000,2,4,'Thanh toán'),(186,1,85000,3,4,'Thanh toán'),(187,1,150000,11,4,'Thanh toán'),(188,1,300000,1,4,'Thanh toán'),(189,1,59000,5,4,'Thanh toán'),(190,1,300000,1,4,'Thanh toán'),(191,1,59000,5,4,'Thanh toán'),(192,1,150000,11,4,'Thanh toán'),(193,1,90000,2,4,'Thanh toán'),(194,1,130000,4,4,'Thanh toán'),(195,1,300000,1,4,'Thanh toán'),(196,1,130000,4,4,'Thanh toán'),(197,1,300000,1,4,'Thanh toán'),(198,1,130000,4,4,'Thanh toán'),(199,1,150000,11,4,'Thanh toán'),(200,1,300000,1,4,'Thanh toán'),(201,1,90000,2,4,'Thanh toán'),(202,1,85000,3,4,'Thanh toán'),(203,1,90000,2,4,'Thanh toán'),(204,1,300000,1,4,'Thanh toán'),(205,1,300000,1,4,'Thanh toán'),(206,1,85000,3,4,'Thanh toán'),(207,1,300000,1,4,'Thanh toán'),(208,1,300000,1,4,'Thanh toán'),(209,1,130000,4,4,'Thanh toán'),(210,1,24000,10,4,'Thanh toán'),(211,1,35000,6,4,'Thanh toán'),(212,1,85000,3,5,'Thanh toán'),(213,1,24000,10,5,'Thanh toán'),(214,1,59000,5,4,'Giỏ hàng'),(215,1,35000,6,5,'Thanh toán'),(216,1,90000,2,5,'Thanh toán');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
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
