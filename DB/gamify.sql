-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: gamify
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `clase_status`
--

DROP TABLE IF EXISTS `clase_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clase_status` (
  `id_clase` int(11) NOT NULL,
  `id_status` int(11) NOT NULL,
  PRIMARY KEY (`id_clase`,`id_status`),
  KEY `id_status` (`id_status`),
  CONSTRAINT `clase_status_ibfk_1` FOREIGN KEY (`id_clase`) REFERENCES `clases` (`id`),
  CONSTRAINT `clase_status_ibfk_2` FOREIGN KEY (`id_status`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clase_status`
--

LOCK TABLES `clase_status` WRITE;
/*!40000 ALTER TABLE `clase_status` DISABLE KEYS */;
INSERT INTO `clase_status` VALUES (2,11),(3,14),(3,15),(4,16),(4,17),(1,18),(1,19),(1,20),(2,51),(11,54),(11,55);
/*!40000 ALTER TABLE `clase_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clases`
--

DROP TABLE IF EXISTS `clases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clases` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clases`
--

LOCK TABLES `clases` WRITE;
/*!40000 ALTER TABLE `clases` DISABLE KEYS */;
INSERT INTO `clases` VALUES (1,'tarea'),(2,'historiasUsuario'),(3,'recompensa'),(4,'recompensaAdmin'),(5,'Proyecto'),(11,'Proyectazomod'),(12,'Proyecto de prueba');
/*!40000 ALTER TABLE `clases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` tinyint(1) DEFAULT NULL,
  `id_proyecto` int(11) DEFAULT NULL,
  `id_recompensa` int(11) DEFAULT NULL,
  `calificacion` double DEFAULT '-1',
  `id_grupo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_proyecto_idx` (`id_proyecto`),
  KEY `id_recompensa_idx` (`id_recompensa`),
  KEY `FKgwbu2x3j69r8ugskmv73ika26` (`id_grupo`),
  CONSTRAINT `FKgwbu2x3j69r8ugskmv73ika26` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`),
  CONSTRAINT `id_proyecto` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id`),
  CONSTRAINT `id_recompensa_fk` FOREIGN KEY (`id_recompensa`) REFERENCES `recompensa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (51,'Equipo con usuarios2','2023-02-06 22:46:42',1,5,6,-1,NULL),(53,'Equipo con usuarios2','2023-02-06 22:50:02',1,NULL,NULL,-1,NULL),(54,'Equipo con usuarios2','2023-02-06 22:53:02',1,NULL,NULL,-1,NULL),(55,'Equipo con usuarios2','2023-02-06 22:56:48',1,NULL,NULL,-1,NULL),(56,'Equipo con usuarios2','2023-02-06 22:58:50',1,NULL,NULL,-1,NULL),(57,'Equipo con usuarios2','2023-02-06 23:17:08',1,NULL,NULL,-1,NULL),(58,'Equipo con usuarios2','2023-02-08 07:05:00',1,NULL,NULL,-1,NULL),(59,'Equipo con usuarios2','2023-02-08 07:06:23',1,NULL,NULL,-1,NULL),(60,'Dinamita','2023-02-15 05:25:32',1,7,NULL,-1,NULL),(61,'Equipo con usuarios2','2023-02-15 22:48:33',1,NULL,NULL,-1,NULL),(62,'Equipo con usuarios2','2023-02-15 22:52:11',1,NULL,NULL,-1,NULL),(63,'Equipo con usuarios2','2023-02-15 22:53:11',1,NULL,NULL,-1,NULL),(64,'Equipo con usuarios2','2023-02-15 22:53:47',1,NULL,NULL,-1,NULL),(65,'Arturo Jimenez Cruz','2023-03-13 23:30:41',1,NULL,NULL,-1,NULL),(66,'Arturo Jimenez Cruz','2023-03-13 23:30:51',1,NULL,NULL,-1,NULL),(67,'crear','2023-04-21 00:31:36',1,8,NULL,-1,NULL),(68,'Equipo 2','2023-04-21 01:47:28',0,NULL,NULL,NULL,NULL),(69,'asfacas','2023-04-21 01:49:29',0,NULL,NULL,NULL,NULL),(70,'equipo2','2023-04-21 01:50:43',0,8,NULL,NULL,NULL),(71,'crear','2023-04-21 02:13:35',0,8,NULL,NULL,NULL),(72,'crearacacs','2023-04-21 02:18:27',0,8,NULL,NULL,NULL),(73,'Equipo de prueba','2023-04-21 02:19:12',1,8,NULL,-1,7),(74,'asdasdasd','2023-04-21 03:17:48',1,NULL,NULL,NULL,NULL),(75,'asdasdasd','2023-04-21 03:20:00',1,NULL,NULL,NULL,NULL),(76,'asdasdasd','2023-04-21 03:20:00',1,NULL,NULL,NULL,NULL),(77,'asdasdasd','2023-04-21 03:22:21',1,NULL,NULL,NULL,NULL),(78,'asdasdasd','2023-04-21 03:28:05',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_alumno`
--

DROP TABLE IF EXISTS `equipo_alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo_alumno` (
  `id_usuario` int(11) NOT NULL,
  `id_equipo` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_equipo`),
  KEY `FKg4j9hted0swpknpkieb0hugn0_idx` (`id_usuario`),
  KEY `id_equipo_idx` (`id_equipo`),
  CONSTRAINT `FK4w83r7fhxt9chuj4mi2a5na8o` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`),
  CONSTRAINT `FK69sf8lrrlqs2mfiab6tt2e30d` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_alumno`
--

LOCK TABLES `equipo_alumno` WRITE;
/*!40000 ALTER TABLE `equipo_alumno` DISABLE KEYS */;
INSERT INTO `equipo_alumno` VALUES (57,51),(57,60),(58,73),(59,51),(63,61),(67,60),(69,73),(71,51),(71,60);
/*!40000 ALTER TABLE `equipo_alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etiquetados`
--

DROP TABLE IF EXISTS `etiquetados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etiquetados` (
  `id_usuario` int(11) NOT NULL,
  `id_tarea` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_tarea`),
  KEY `FKhqo8lll95ux45wq8k6qxck5m8` (`id_usuario`),
  KEY `FKtpr2cb2e6xugpeex45iipl5pc` (`id_tarea`),
  CONSTRAINT `FKhqo8lll95ux45wq8k6qxck5m8` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKtpr2cb2e6xugpeex45iipl5pc` FOREIGN KEY (`id_tarea`) REFERENCES `tarea` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etiquetados`
--

LOCK TABLES `etiquetados` WRITE;
/*!40000 ALTER TABLE `etiquetados` DISABLE KEYS */;
INSERT INTO `etiquetados` VALUES (56,3),(56,8),(56,9),(56,10),(57,6),(57,7),(58,12),(58,13),(58,15),(59,11),(59,14),(67,1),(67,7),(67,8),(67,9);
/*!40000 ALTER TABLE `etiquetados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flujo_acumulado`
--

DROP TABLE IF EXISTS `flujo_acumulado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flujo_acumulado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_equipo` int(11) DEFAULT NULL,
  `fecha_guardado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `num_tareas` int(11) DEFAULT NULL,
  `id_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_status` (`id_status`),
  KEY `flujo_acumulado_ibfk_1_idx` (`id_equipo`),
  CONSTRAINT `flujo_acumulado_ibfk_2` FOREIGN KEY (`id_status`) REFERENCES `status` (`id`),
  CONSTRAINT `id_equipo_ibfk` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flujo_acumulado`
--

LOCK TABLES `flujo_acumulado` WRITE;
/*!40000 ALTER TABLE `flujo_acumulado` DISABLE KEYS */;
INSERT INTO `flujo_acumulado` VALUES (26,73,'2023-04-01 06:00:00',5,18),(27,73,'2023-04-02 06:00:00',4,18),(28,73,'2023-04-03 06:00:00',5,18),(29,73,'2023-04-04 06:00:00',5,18),(30,73,'2023-04-05 06:00:00',3,18),(31,73,'2023-04-06 06:00:00',1,18),(32,73,'2023-04-01 06:00:00',0,19),(33,73,'2023-04-02 06:00:00',1,19),(34,73,'2023-04-03 06:00:00',2,19),(35,73,'2023-04-04 06:00:00',1,19),(36,73,'2023-04-05 06:00:00',0,19),(37,73,'2023-04-06 06:00:00',1,19),(38,73,'2023-04-01 06:00:00',0,20),(39,73,'2023-04-02 06:00:00',1,20),(40,73,'2023-04-03 06:00:00',2,20),(41,73,'2023-04-04 06:00:00',3,20),(42,73,'2023-04-05 06:00:00',5,20),(43,73,'2023-04-06 06:00:00',6,20);
/*!40000 ALTER TABLE `flujo_acumulado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_acceso` varchar(20) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_profesor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_profesor_idx` (`id_profesor`),
  CONSTRAINT `id_profesor` FOREIGN KEY (`id_profesor`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,'Com8','Compiladores 4CM14',NULL,NULL),(2,'Com239A92','Compiladores',NULL,NULL),(3,'cre239A96','crearascas',NULL,NULL),(4,'las239A98','lascnas',NULL,NULL),(5,'lkf239C1C','lkfansfian23','2023-04-04 00:24:48',63),(7,'Ing163D84E','Ing. Software 4CM5','2023-04-20 23:54:07',71),(8,'ComDE65DEE','Compiladores 4CV8','2023-04-21 04:34:37',71);
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_alumno`
--

DROP TABLE IF EXISTS `grupo_alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_alumno` (
  `id_grupo` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_grupo`,`id_usuario`),
  KEY `FK3mjuu9w99ppkuwg16hvcjwrlx` (`id_usuario`),
  CONSTRAINT `FK3mjuu9w99ppkuwg16hvcjwrlx` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKopxlfrigkk8q21jxuyirqx6fe` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_alumno`
--

LOCK TABLES `grupo_alumno` WRITE;
/*!40000 ALTER TABLE `grupo_alumno` DISABLE KEYS */;
INSERT INTO `grupo_alumno` VALUES (5,56),(7,56),(7,57),(7,58),(7,69);
/*!40000 ALTER TABLE `grupo_alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (0);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(50) NOT NULL,
  `next_val` int(11) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historias_usuario`
--

DROP TABLE IF EXISTS `historias_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historias_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` text,
  `puntos_historia` double DEFAULT NULL,
  `id_status` int(11) DEFAULT NULL,
  `id_equipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_status` (`id_status`),
  KEY `id_equipo_idx` (`id_equipo`),
  CONSTRAINT `historias_usuario_ibfk_2` FOREIGN KEY (`id_status`) REFERENCES `status` (`id`),
  CONSTRAINT `id_equipo_fbk` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historias_usuario`
--

LOCK TABLES `historias_usuario` WRITE;
/*!40000 ALTER TABLE `historias_usuario` DISABLE KEYS */;
INSERT INTO `historias_usuario` VALUES (3,'hola','sdfsdsd',52,12,51),(4,'Historia larga','Historia larga y dificil',10,11,51),(5,'Historia larga2','sdvdsvdvsdfv df',12,11,51),(6,'Historia larga2','sdvdsvdvsdfv df',12,11,51),(7,'heelllo','23dsvdv fdvvv',123,12,51),(8,'Historia de prueba1','Historia de prueba para la presentación del CIC',10,12,73);
/*!40000 ALTER TABLE `historias_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notificacion`
--

DROP TABLE IF EXISTS `notificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notificacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `id_recompensa` int(11) DEFAULT NULL,
  `id_tarea` int(11) DEFAULT NULL,
  `visto` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_recompensa` (`id_recompensa`),
  KEY `id_tarea` (`id_tarea`),
  CONSTRAINT `notificacion_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `notificacion_ibfk_2` FOREIGN KEY (`id_recompensa`) REFERENCES `recompensa` (`id`),
  CONSTRAINT `notificacion_ibfk_3` FOREIGN KEY (`id_tarea`) REFERENCES `tarea` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacion`
--

LOCK TABLES `notificacion` WRITE;
/*!40000 ALTER TABLE `notificacion` DISABLE KEYS */;
INSERT INTO `notificacion` VALUES (1,67,NULL,7,0),(2,56,NULL,9,0),(3,67,NULL,9,0),(4,56,NULL,8,0),(5,67,NULL,8,0),(6,56,NULL,8,0),(7,67,NULL,8,0),(8,67,NULL,3,0),(9,63,NULL,NULL,0),(10,56,NULL,3,0),(11,58,NULL,3,0),(12,59,NULL,3,0),(13,56,NULL,10,0),(14,59,NULL,11,0),(15,58,NULL,12,0),(16,58,NULL,13,0),(17,59,NULL,14,0),(18,71,NULL,14,0),(19,71,6,NULL,0),(20,58,NULL,15,0);
/*!40000 ALTER TABLE `notificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto`
--

DROP TABLE IF EXISTS `proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyecto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_entrega` timestamp NULL DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `descripcion` text,
  `nombre` varchar(50) DEFAULT NULL,
  `porcentaje_penalizacion` int(11) DEFAULT NULL,
  `files` text,
  `is_active` tinyint(1) DEFAULT NULL,
  `id_status` int(11) DEFAULT NULL,
  `id_profesor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `proyecto_ibfk_2_idx` (`id_status`),
  KEY `FKsi4dstotm0xmys7c4bcjbw8nf` (`id_profesor`),
  CONSTRAINT `FKsi4dstotm0xmys7c4bcjbw8nf` FOREIGN KEY (`id_profesor`) REFERENCES `usuario` (`id`),
  CONSTRAINT `proyecto_ibfk_2` FOREIGN KEY (`id_status`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyecto`
--

LOCK TABLES `proyecto` WRITE;
/*!40000 ALTER TABLE `proyecto` DISABLE KEYS */;
INSERT INTO `proyecto` VALUES (5,'2023-03-28 13:45:00','2023-02-07 06:56:15','Proyecto 1','Proyectazomod',20,'ConstanciaServicioSoc_2019630325_BRIONES_MOLINA_ARTURO.pdf',1,NULL,NULL),(6,'2023-02-24 06:00:00','2023-02-21 05:51:11','descripcion','Arjona',100,'985860801775.pdf',1,51,NULL),(7,'2023-02-23 06:00:00','2023-02-21 05:53:12','afsafs','Aloja',100,'',1,12,NULL),(8,'2023-05-26 07:00:00','2023-02-21 05:55:00','Esta es una prueba','Proyecto de prueba',55,'gestionarTarea.png',1,51,71);
/*!40000 ALTER TABLE `proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quemado`
--

DROP TABLE IF EXISTS `quemado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quemado` (
  `fecha_guardado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `puntos_quemado` double DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_equipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_equipo_idx` (`id_equipo`),
  CONSTRAINT `id_equipo` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quemado`
--

LOCK TABLES `quemado` WRITE;
/*!40000 ALTER TABLE `quemado` DISABLE KEYS */;
INSERT INTO `quemado` VALUES ('2023-05-01 05:55:00',2,1,73),('2023-05-02 05:55:00',5,2,73),('2023-05-03 05:55:00',8,3,73),(NULL,175,4,51),(NULL,10,5,73);
/*!40000 ALTER TABLE `quemado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recompensa`
--

DROP TABLE IF EXISTS `recompensa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recompensa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `puntos` double DEFAULT NULL,
  `descripcion` text,
  `id_profesor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `puntos_UNIQUE` (`puntos`),
  KEY `id_profesor_idx` (`id_profesor`),
  CONSTRAINT `id_profesor_fk` FOREIGN KEY (`id_profesor`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recompensa`
--

LOCK TABLES `recompensa` WRITE;
/*!40000 ALTER TABLE `recompensa` DISABLE KEYS */;
INSERT INTO `recompensa` VALUES (6,'+1 Punto extra calificación final',NULL,'1 punto extra sobre la calificación final',NULL),(7,'+1 Punto en el parcial',NULL,'+1 Punto en el parcial',71),(8,'Exentar examen 3er parcial',NULL,'Exentar examen 3er parcial',71);
/*!40000 ALTER TABLE `recompensa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (2,'Profesor','me'),(3,'Alumno','mew');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud` (
  `id` int(11) NOT NULL,
  `decripcion` longtext,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `files` longtext,
  `is_correction` bit(1) DEFAULT NULL,
  `id_status` int(11) DEFAULT NULL,
  `id_tarea` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbdgwn76scc68x3newk23h86du` (`id_status`),
  KEY `FKq5itopv05kafasjtrwug9oylu` (`id_tarea`),
  KEY `FKnm4y7w49isl37c27aq2fmos5t` (`id_usuario`),
  CONSTRAINT `FKbdgwn76scc68x3newk23h86du` FOREIGN KEY (`id_status`) REFERENCES `status` (`id`),
  CONSTRAINT `FKnm4y7w49isl37c27aq2fmos5t` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKq5itopv05kafasjtrwug9oylu` FOREIGN KEY (`id_tarea`) REFERENCES `tarea` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud`
--

LOCK TABLES `solicitud` WRITE;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `id_equipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_equipo_idx` (`id_equipo`),
  CONSTRAINT `fk_id_equipo` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (11,'Iniciado',NULL),(12,'Finalizado',NULL),(14,'Por cobrar',NULL),(15,'Solicitar cobro',NULL),(16,'Aprobado',NULL),(17,'Cobrado',NULL),(18,'Por hacer',NULL),(19,'Haciendo',NULL),(20,'Hecho',NULL),(22,'Pruebas',NULL),(23,'pruebas2',NULL),(24,'pruebas3',NULL),(25,'Pruebas5',NULL),(26,'pruebas6',NULL),(27,'pruebas7',NULL),(28,'pruebas8',NULL),(29,'pruebas9',NULL),(30,'pruebas10',NULL),(31,'pruebas11',NULL),(32,'pruebas12',NULL),(33,'pruebas13',NULL),(34,'pruebas14',NULL),(35,'pruebas15',NULL),(36,'pruebas16',NULL),(37,'pruebas17',NULL),(38,'pruebas18',NULL),(39,'pruebas19',NULL),(40,'pruebas20',NULL),(41,'pruebas21',NULL),(42,'pruebas22',NULL),(43,'pruebas23',NULL),(44,'pruebas24',NULL),(45,'pruebas25',NULL),(46,'pruebas28',NULL),(47,'Pruebas29',NULL),(48,'Pruebas30',NULL),(49,'pruebas31',NULL),(50,'pruebas32',NULL),(51,'Desarrollando',51),(52,'kk',51),(53,NULL,NULL),(54,'acascasc',51),(55,'qweqwd32r3few',51);
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarea`
--

DROP TABLE IF EXISTS `tarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarea` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_autor` int(11) DEFAULT NULL,
  `id_historia` int(11) DEFAULT NULL,
  `fecha_tentativa` timestamp NULL DEFAULT NULL,
  `descripcion` text,
  `id_status` int(11) DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `files` text,
  `nombre` varchar(45) DEFAULT NULL,
  `tarea` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  `id_equipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_autor` (`id_autor`),
  KEY `id_historia` (`id_historia`),
  KEY `id_status` (`id_status`),
  KEY `tarea_ibfk_5_idx` (`id_equipo`),
  CONSTRAINT `tarea_ibfk_2` FOREIGN KEY (`id_autor`) REFERENCES `usuario` (`id`),
  CONSTRAINT `tarea_ibfk_3` FOREIGN KEY (`id_historia`) REFERENCES `historias_usuario` (`id`),
  CONSTRAINT `tarea_ibfk_4` FOREIGN KEY (`id_status`) REFERENCES `status` (`id`),
  CONSTRAINT `tarea_ibfk_5` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarea`
--

LOCK TABLES `tarea` WRITE;
/*!40000 ALTER TABLE `tarea` DISABLE KEYS */;
INSERT INTO `tarea` VALUES (1,56,NULL,'2023-03-31 06:00:00','tarea 1sacafasas',22,'2023-02-24 02:46:38','relacional.jpg,back.pdf,985860801775.pdf','Tarea 1 Mod',NULL,0,NULL),(2,56,NULL,'2023-02-06 22:46:42','tarea 2',20,'2023-03-07 23:08:12',NULL,'Tarea 2',NULL,1,51),(3,57,NULL,'2023-02-06 22:46:42','tarea 3asascas',52,'2023-03-07 23:08:12',NULL,'Tarea 3',NULL,NULL,NULL),(4,56,NULL,'2023-02-06 22:46:42','slifjaoichasoicnaslicn',20,'2023-02-06 22:46:42',NULL,'tarea4',NULL,NULL,NULL),(5,NULL,NULL,NULL,'sklansdlkncvsldknc',NULL,'2023-03-15 02:05:25','undefined,clabe.txt','Tare5',NULL,NULL,NULL),(6,67,NULL,'2023-03-31 06:00:00','bkjbcakjbsca',NULL,'2023-03-15 06:42:57',NULL,'Un rayo de sol wo',NULL,NULL,NULL),(7,56,NULL,'2023-04-08 06:00:00','Un rayo de sol',22,'2023-03-15 06:46:36',NULL,'tarea7',NULL,NULL,NULL),(8,57,NULL,'2023-04-07 07:00:00','Tarea bien dificil',52,'2023-03-21 23:09:11',NULL,'tarea dificil 54684',NULL,NULL,NULL),(9,57,NULL,'2023-03-31 06:00:00','Tarea bien dificil',52,'2023-03-21 23:09:37',NULL,'tarea dificil',NULL,NULL,NULL),(10,57,NULL,'2023-04-06 07:00:00','Tarea nueva larga',19,'2023-04-05 17:47:23','relacional.png','Tarea nueva',NULL,NULL,51),(11,57,NULL,'2023-04-27 07:00:00','asdasdasdas',19,'2023-04-05 17:48:58',NULL,'Crear tarea',NULL,NULL,51),(12,57,NULL,'2023-04-19 07:00:00','asdasdasdsd',19,'2023-04-05 17:49:33','undefined,front.pdf','Crear tarea',NULL,NULL,51),(13,57,NULL,'2023-04-13 07:00:00','asdasdas',52,'2023-04-06 00:17:38',NULL,'Crear tarea',NULL,NULL,51),(14,56,NULL,'2023-04-27 07:00:00','assdxzcv xcbdgbg ',22,'2023-04-06 00:20:11',NULL,'ultima',NULL,NULL,51),(15,69,8,'2023-04-29 07:00:00','Tarea de prueba 1',18,'2023-04-21 06:44:57',NULL,'Tarea de prueba 1',NULL,NULL,73),(16,58,NULL,'2023-04-29 07:00:00','Tarea de prueba 2 creada por otro alumno',18,'2023-04-21 06:44:57',NULL,'Tarea de prueba 2 creada por otro alumno',NULL,NULL,73);
/*!40000 ALTER TABLE `tarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `nombre_usuario` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `puntaje` double DEFAULT NULL,
  `id_rol` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `is_available` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `id_rol_idx` (`id_rol`),
  CONSTRAINT `id_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (56,'Arthu','asdasd@dcsd.sfc','SFweiufbw','ASDasd',750,3,1,1),(57,'David el cacahuete','asdaasdsd@dcsd.sf','SFweiasdufbw','ASDasd',64.564,2,1,1),(58,'Arturo Briones','arturo@arturo.com','SFweiasdufbw','$10$rqv2ki9RgluVoYBRfPDvouqW11QPTsCEvts2jZlVp8MsS5BeLniJO',64.564,2,1,1),(59,'asArthu','','SFweiasdufbw','ASDasd',64.564,2,1,1),(62,'asArthu','','SFweiasdufbw','ASDasd',64.564,2,0,1),(63,'Eren Jeager','','SFweiasdufbw','ASDasd',100,2,1,1),(64,'asArthu','','SFweiasdufbw','ASDasd',64.564,2,1,1),(65,'asArthu','','SFweiasdufbw','ASDasd',64.564,2,1,1),(66,'asArthu','','SFweiasdufbw','ASDasd',64.564,2,1,1),(67,'Arturo Jimenez Cruz','','SFweiasdufbw','ASDasd',64.564,2,1,1),(68,'Homero Sinso Jhonson','Homero@sinso.com','DonBarredora','mdscopsmeop3',0,3,0,0),(69,'Alumno Prueba Prueba','alumno@alumno.com','alumno123','$2a$10$bv69XBwStoPvTDVj98bKjexrQO0zvw.mmdEXPRWMJSv7AFscAT1/C',0,3,0,0),(70,'Julay Julay Julay','julay@jyla.com','Julay123','$2a$10$9f0c0DjIlh42BUD36bYt4egTSwcU/7a4Pn9DX8IO2gHQ.0fDjJhBi',0,3,0,0),(71,'Profesor Prueba Pruebas','profesor@profesor.com','Carloa','$2a$10$2zl8SY/lXDJn99Wny9QWKOjLaEZ8fjxZ.b3WK.pCn1uL/4cZJYLHG',0,2,1,0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_grupos`
--

DROP TABLE IF EXISTS `usuario_grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_grupos` (
  `usuario_id` int(11) NOT NULL,
  `grupos_id` int(11) NOT NULL,
  KEY `FK92ff1v8fkwig9tqv9bk4nvi0t` (`grupos_id`),
  KEY `FK158r9y55ufwykh675ddt8kb43` (`usuario_id`),
  CONSTRAINT `FK158r9y55ufwykh675ddt8kb43` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK92ff1v8fkwig9tqv9bk4nvi0t` FOREIGN KEY (`grupos_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_grupos`
--

LOCK TABLES `usuario_grupos` WRITE;
/*!40000 ALTER TABLE `usuario_grupos` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_recompensa`
--

DROP TABLE IF EXISTS `usuario_recompensa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_recompensa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `id_recompensa` int(11) DEFAULT NULL,
  `id_status` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhe92llfn14fs94yfyrpmohxva` (`id_recompensa`),
  KEY `FK2ygtneglv2mnhuga7ew68e28c` (`id_status`),
  KEY `FKbbdbqmva9v1gjokuob5qtv76k` (`id_usuario`),
  CONSTRAINT `FK2ygtneglv2mnhuga7ew68e28c` FOREIGN KEY (`id_status`) REFERENCES `status` (`id`),
  CONSTRAINT `FKbbdbqmva9v1gjokuob5qtv76k` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKhe92llfn14fs94yfyrpmohxva` FOREIGN KEY (`id_recompensa`) REFERENCES `recompensa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_recompensa`
--

LOCK TABLES `usuario_recompensa` WRITE;
/*!40000 ALTER TABLE `usuario_recompensa` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_recompensa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'gamify'
--
/*!50003 DROP PROCEDURE IF EXISTS `actualizar_puntajes_recompensas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizar_puntajes_recompensas`(IN idproyecto INT)
BEGIN
	DECLARE puntaje_obtenido DOUBLE default 0;
    DECLARE diferencia_diaz INT default 0;
    SELECT TIMESTAMPDIFF(DAY, fecha_entrega , CURDATE()) INTO diferencia_diaz FROM proyecto WHERE id = idproyecto;
    IF diferencia_diaz <=  0 THEN
		SELECT puntos_recompensa INTO  puntaje_obtenido FROM proyecto WHERE id = idproyecto ;
    ELSE
		SELECT ( puntos_recompensa* POW((1-((porcentaje_penalizacion/100)/1)), diferencia_diaz ) ) INTO  puntaje_obtenido FROM proyecto WHERE id = idproyecto ;
    END IF;
    UPDATE usuario SET puntaje = puntaje + puntaje_obtenido WHERE id IN (SELECT ee.id_usuario FROM equipo_empleado ee INNER JOIN  equipo e ON e.id = ee.id_equipo INNER JOIN proyecto p ON p.id_equipo = e.id WHERE  p.id = idproyecto);
    INSERT INTO notificacion (id_usuario,id_recompensa) SELECT u.id,r.id FROM usuario u CROSS JOIN recompensa r LEFT JOIN usuario_recompensa ur ON ur.id_usuario = u.id AND ur.id_recompensa = r.id CROSS JOIN status s WHERE ur.id IS NULL AND s.nombre ="Por cobrar"  AND u.puntaje >= r.puntos AND u.id IN (SELECT ee.id_usuario FROM equipo_empleado ee INNER JOIN  equipo e ON e.id = ee.id_equipo INNER JOIN proyecto p ON p.id_equipo = e.id WHERE  p.id = idproyecto);
	INSERT INTO usuario_recompensa (id_usuario,id_recompensa,id_status) SELECT u.id,r.id,s.id FROM usuario u CROSS JOIN recompensa r LEFT JOIN usuario_recompensa ur ON ur.id_usuario = u.id AND ur.id_recompensa = r.id CROSS JOIN status s WHERE ur.id IS NULL AND s.nombre ="Por cobrar"  AND u.puntaje >= r.puntos AND u.id IN (SELECT ee.id_usuario FROM equipo_empleado ee INNER JOIN  equipo e ON e.id = ee.id_equipo INNER JOIN proyecto p ON p.id_equipo = e.id WHERE  p.id = idproyecto);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `etiquetados_notificaciones` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `etiquetados_notificaciones`(IN idtarea INT,IN idusuario INT)
BEGIN
DECLARE existente INTEGER(11) DEFAULT 0;
SELECT count(*) INTO existente FROM gamify.notificacion WHERE id_tarea = idtarea AND id_usuario = idusuario;
IF existente <=  0 THEN
	INSERT INTO gamify.notificacion (id_tarea,id_usuario) VALUES (idtarea,idusuario);
END if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_recompensas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_recompensas`(IN  idUser INT)
BEGIN
	INSERT INTO usuario_recompensa  VALUES(idUser,(SELECT id FROM recompensa WHERE puntos <= (SELECT puntaje from usuario WHERE id = idUser)),1);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-24 18:40:25

ALTER TABLE historias_usuario ADD COLUMN terminado TINYINT(1) NULL DEFAULT 0 AFTER id_equipo;

CREATE TABLE juego_servidor (id INTEGER AUTO_INCREMENT PRIMARY KEY, level_ram INTEGER, level_hdd INTEGER, level_red INTEGER, level_server INTEGER, id_equipo INTEGER UNIQUE, monedas DOUBLE, FOREIGN KEY (id_equipo) REFERENCES equipo(id));

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_monedas`(in idhistoria integer)
BEGIN
	DECLARE cterminado BOOLEAN DEFAULT false;
    DECLARE idequipo integer;
    DECLARE addedMonedas DOUBLE;
    DECLARE idstatus integer;
	SET cterminado := (SELECT terminado FROM historias_usuario WHERE id = idhistoria);
    SET idstatus := (SELECT id_status FROM historias_usuario WHERE id = idhistoria);
    SET idequipo := (SELECT id_equipo FROM historias_usuario WHERE id = idhistoria);
    SET addedMonedas := (SELECT (((1000-(SELECT monedas+200*level_server+50*level_hdd+50*level_ram+50*level_red FROM juego_servidor WHERE id_equipo = idequipo))))/(SELECT count(*) FROM historias_usuario where id_equipo = idequipo AND terminado = false));
    IF NOT cterminado AND idstatus = 12 THEN
		UPDATE historias_usuario SET terminado = true WHERE id = idhistoria;
        UPDATE juego_servidor SET monedas = monedas + addedMonedas  WHERE id_equipo = idequipo;
    END IF;

END
;;
DELIMITER ;

DELIMITER ;;
CREATE EVENT data_quemados ON SCHEDULE EVERY 24 HOUR STARTS CURRENT_TIMESTAMP DO
 INSERT INTO quemado (id_equipo,puntos_quemado)  SELECT id_equipo,SUM(puntos_historia) FROM historias_usuario WHERE id_status=(SELECT id FROM status WHERE id_equipo IS NULL AND nombre ="Finalizado") GROUP BY id_equipo;
DELIMITER ;

DELIMITER ;;
CREATE EVENT data_flujo ON SCHEDULE EVERY 24 HOUR STARTS CURRENT_TIMESTAMP DO
 INSERT INTO flujo_acumulado (num_tareas,id_status,id_equipo)  SELECT count(*),s.id,e.id FROM tarea t INNER JOIN status s ON s.id = t.id_status INNER JOIN equipo e ON e.id = t.id_equipo GROUP BY t.id_equipo,t.id_status;
DELIMITER ;
