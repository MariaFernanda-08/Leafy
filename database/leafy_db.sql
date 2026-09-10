-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: leafy_db
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `pontos_coleta`
--

DROP TABLE IF EXISTS `pontos_coleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pontos_coleta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `latitude` decimal(10,8) NOT NULL,
  `longitude` decimal(11,8) NOT NULL,
  `descricao` text DEFAULT NULL,
  `criado_em` timestamp NOT NULL DEFAULT current_timestamp(),
  `materiais_aceitos` text DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pontos_coleta`
--

LOCK TABLES `pontos_coleta` WRITE;
/*!40000 ALTER TABLE `pontos_coleta` DISABLE KEYS */;
INSERT INTO `pontos_coleta` VALUES (4,'Ecoponto Sabará','Ecoponto','Rua Adelino Rodrigues Gatto x Rua Afonso Vincoletto',-22.11641260,-51.42410280,'Localizado na zona oeste. Recebe pequenos volumes de entulho de construção, móveis, eletrônicos, eletrodomésticos, madeira e materiais recicláveis.','2026-09-10 17:37:59','Plástico, papel, metal, móveis, eletrônicos, eletrodomésticos, madeira e entulho','Aberto'),(5,'Ecoponto Cambuci','Ecoponto','Avenida Dom Pedro I, nº 38',-22.14862630,-51.38210310,'Ecoponto temporariamente fechado. Recebe recicláveis, móveis usados, pequenos volumes de entulho, pequenas quantidades de poda e óleo de cozinha usado.','2026-09-10 17:37:59','Papel, plástico, metal, vidro, móveis, entulho, poda e óleo de cozinha','Temporariamente fechado');
/*!40000 ALTER TABLE `pontos_coleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `residuos`
--

DROP TABLE IF EXISTS `residuos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `residuos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `codigo_barras` varchar(50) DEFAULT NULL,
  `tipo` varchar(50) NOT NULL,
  `instrucoes_descarte` text NOT NULL,
  `reciclavel` tinyint(1) NOT NULL,
  `tempo_decomposicao` varchar(100) DEFAULT NULL,
  `impacto_ambiental` text DEFAULT NULL,
  `criado_em` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_barras` (`codigo_barras`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `residuos`
--

LOCK TABLES `residuos` WRITE;
/*!40000 ALTER TABLE `residuos` DISABLE KEYS */;
INSERT INTO `residuos` VALUES (1,'Garrafa PET','789000000001','Plástico','Esvazie, lave e encaminhe para a coleta seletiva.',1,'Cerca de 400 anos','Pode permanecer por muito tempo no ambiente quando descartada incorretamente.','2026-09-03 11:33:31'),(2,'Lata de alumínio','789000000002','Metal','Esvazie e encaminhe para a coleta seletiva.',1,'Cerca de 200 anos','O descarte incorreto contribui para a poluição do ambiente.','2026-09-03 11:33:31'),(3,'Papelão','789000000003','Papel','Mantenha seco e encaminhe para a coleta seletiva.',1,'Cerca de 3 meses','Pode ser reciclado e reaproveitado na produção de novos materiais.','2026-09-03 11:33:31'),(4,'Guardanapo usado',NULL,'Orgânico','Descarte conforme as orientações locais para resíduos não recicláveis.',0,'Cerca de 1 a 3 meses','Quando descartado incorretamente, pode contribuir para a poluição.','2026-09-03 11:33:31'),(5,'Garrafa de vidro','789000000004','Vidro','Lave a embalagem e encaminhe para a coleta seletiva.',1,'Mais de 4000 anos','O vidro descartado incorretamente permanece no ambiente por um período extremamente longo.','2026-09-03 11:33:31'),(6,'Caixa longa vida','789000000005','Papel','Lave, se possível, e encaminhe para pontos de coleta ou coleta seletiva.',1,'Cerca de 5 anos','É composta por diferentes materiais, exigindo processos específicos de reciclagem.','2026-09-03 11:33:31'),(7,'Trident X Senses Melância Mint','7622210570376','Plástico','Descarte na coleta seletiva para plásticos, preferencialmente com a embalagaem limpa.',1,'Mais de 400 anos','Pode contribuir para a poluição do solo e de ambientes aquáticos.','2026-09-08 16:40:25');
/*!40000 ALTER TABLE `residuos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `xp` int(11) DEFAULT 0,
  `nivel` varchar(50) DEFAULT 'Formiga Operária',
  `criado_em` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-10 16:24:53
