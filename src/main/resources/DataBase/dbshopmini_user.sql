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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Hà nội','hinh-nen-dien-thoai-dep.jpg','shop1@gmail.com',NULL,'$2a$10$7lvUI1mdYFbLS9a.naYaJefXCP7GkJ2QAcfJ6mw.kniDmUA4zjGFC','0321789654','shop1','Hoạt động','SHOP','Shop1'),(2,'Hà nội','2030c25716321678a2891cc9989202f6.jfif','quanly@gmail.com',NULL,'$2a$10$7lvUI1mdYFbLS9a.naYaJefXCP7GkJ2QAcfJ6mw.kniDmUA4zjGFC','0397327837','shop2','Hoạt động','SHOP','Shop2'),(3,NULL,NULL,NULL,NULL,'$2a$10$7lvUI1mdYFbLS9a.naYaJefXCP7GkJ2QAcfJ6mw.kniDmUA4zjGFC',NULL,'admin','Hoạt động','ADMIN',NULL),(4,'Kim De, An Tuong, Vinh Tuong, Vinh Phuc','b4b98829fa383a666329.jpg','phamthokd19@gmail.com',NULL,'$2a$10$dpaRDfZK7iVPrUyK3cAUgOhMe48q9mxxEn7JYXdKhCbsfCQKPUAHC','0397327836','customer1','Hoạt động','CUSTOMER','Pham Tho'),(5,NULL,NULL,NULL,NULL,'$2a$10$7lvUI1mdYFbLS9a.naYaJefXCP7GkJ2QAcfJ6mw.kniDmUA4zjGFC',NULL,'customer2','Hoạt động','CUSTOMER',NULL),(15,'Hà nội',NULL,'phamthokd@gmail.com',NULL,'$2a$10$mZlQkFH082lj/3HPhSPj3.5RW8y.QDLX4ehmcCbilSfmkhR9EGLuO','123456789','DoanhChinh',NULL,'CUSTOMER','Nguyen doanh chinh');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
