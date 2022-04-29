-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: stud_info
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `discipline_group`
--

DROP TABLE IF EXISTS `discipline_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discipline_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_group` int NOT NULL,
  `id_discipline` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ROW_UNIQUE` (`id_group`,`id_discipline`),
  KEY `FK_FROM_GROUPE_idx` (`id_group`),
  KEY `FK_FROM_DISCIPLINE_idx` (`id_discipline`),
  CONSTRAINT `FK_FROM_DISCIPLINE` FOREIGN KEY (`id_discipline`) REFERENCES `disciplines` (`id_discipline`),
  CONSTRAINT `FK_FROM_GROUPE` FOREIGN KEY (`id_group`) REFERENCES `stud_groups` (`id_group`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discipline_group`
--

LOCK TABLES `discipline_group` WRITE;
/*!40000 ALTER TABLE `discipline_group` DISABLE KEYS */;
INSERT INTO `discipline_group` VALUES (6,1,6),(9,1,7),(14,1,8),(1,2,5),(10,2,7),(15,2,8),(2,3,5),(11,3,7),(16,3,8),(3,4,5),(12,4,7),(17,4,8),(4,5,5),(13,5,7),(18,5,8),(7,6,6),(19,6,9),(21,6,10),(8,7,6),(5,8,5),(20,8,9),(22,8,10);
/*!40000 ALTER TABLE `discipline_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplines`
--

DROP TABLE IF EXISTS `disciplines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disciplines` (
  `id_discipline` int NOT NULL AUTO_INCREMENT,
  `cod_discipline` varchar(20) NOT NULL,
  `hour_lect` int NOT NULL,
  `hour_labs` int NOT NULL,
  `test_type` int NOT NULL,
  PRIMARY KEY (`id_discipline`),
  UNIQUE KEY `id_discipline_UNIQUE` (`id_discipline`),
  UNIQUE KEY `cod_discipline_UNIQUE` (`cod_discipline`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplines`
--

LOCK TABLES `disciplines` WRITE;
/*!40000 ALTER TABLE `disciplines` DISABLE KEYS */;
INSERT INTO `disciplines` VALUES (5,'АППЗ-КН',32,32,0),(6,'АППЗ-ПИ',32,32,0),(7,'ADV_JAVA2',16,32,1),(8,'Spring',32,16,1),(9,'Python frame',16,32,1),(10,'ADV_Python2',32,16,1);
/*!40000 ALTER TABLE `disciplines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stud_dop_info`
--

DROP TABLE IF EXISTS `stud_dop_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stud_dop_info` (
  `idstud_dop_info` int NOT NULL AUTO_INCREMENT,
  `channel` varchar(45) DEFAULT NULL,
  `activity` varchar(150) DEFAULT NULL,
  `id_stud` int NOT NULL,
  PRIMARY KEY (`idstud_dop_info`),
  UNIQUE KEY `id_stud_UNIQUE` (`id_stud`),
  CONSTRAINT `FK_DOP_STUD` FOREIGN KEY (`id_stud`) REFERENCES `students` (`id_student`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stud_dop_info`
--

LOCK TABLES `stud_dop_info` WRITE;
/*!40000 ALTER TABLE `stud_dop_info` DISABLE KEYS */;
INSERT INTO `stud_dop_info` VALUES (1,'Personal meeting','Волонтер',1),(2,'phone call','Работает, devops',2),(3,'telegram','EPAM внешние курсы',8),(4,'viber','футбол',7),(5,'email','во Франции по обмену',14);
/*!40000 ALTER TABLE `stud_dop_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stud_groups`
--

DROP TABLE IF EXISTS `stud_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stud_groups` (
  `id_group` int NOT NULL AUTO_INCREMENT,
  `cod_group` varchar(10) NOT NULL,
  `cod_spec` int NOT NULL,
  PRIMARY KEY (`id_group`),
  UNIQUE KEY `cod_group_UNIQUE` (`cod_group`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stud_groups`
--

LOCK TABLES `stud_groups` WRITE;
/*!40000 ALTER TABLE `stud_groups` DISABLE KEYS */;
INSERT INTO `stud_groups` VALUES (1,'КН-219а',121),(2,'КН-419а',122),(3,'КН-420с',122),(4,'КН-719',126),(5,'КН-720с',126),(6,'КН-219б',121),(7,'КН-219в',121),(8,'КН-419б',122);
/*!40000 ALTER TABLE `stud_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id_student` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `age` varchar(35) NOT NULL,
  `gender` varchar(3) NOT NULL,
  `id_group` int DEFAULT NULL,
  PRIMARY KEY (`id_student`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `FK_FROM_GRUOP_idx` (`id_group`),
  CONSTRAINT `FK_FROM_GRUOP` FOREIGN KEY (`id_group`) REFERENCES `stud_groups` (`id_group`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Кузуб','20','1',4),(2,'Кольцов','20','1',4),(3,'Гресс','20','0',5),(4,'Евдоченко','20','0',1),(5,'Любаненко','20','0',2),(6,'Матвиенко','20','1',3),(7,'Татишвили','20','1',3),(8,'Сапожников','20','1',2),(9,'Тарасенко','20','1',1),(10,'Буряк','20','1',2),(11,'Кравченко','20','1',6),(12,'Кондрашин','20','1',6),(13,'Ковальская','20','0',7),(14,'Кулешова','20','0',8);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-30  1:35:36
