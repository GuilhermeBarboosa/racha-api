-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 12-Jun-2023 às 11:10
-- Versão do servidor: 8.0.29
-- versão do PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `racha`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `jogador`
--

DROP TABLE IF EXISTS `jogador`;
CREATE TABLE IF NOT EXISTS `jogador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user` int NOT NULL,
  `posicao` int NOT NULL,
  `gols` int NOT NULL,
  `assistencias` int NOT NULL,
  `actived` tinyint(1) NOT NULL,
  `created` timestamp NOT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user` (`user`),
  KEY `posicao` (`posicao`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `jogador`
--

INSERT INTO `jogador` (`id`, `user`, `posicao`, `gols`, `assistencias`, `actived`, `created`, `updated`) VALUES
(1, 1, 5, 2, 3, 1, '2023-05-31 11:09:36', '2023-06-02 16:22:26');

-- --------------------------------------------------------

--
-- Estrutura da tabela `jogador_racha`
--

DROP TABLE IF EXISTS `jogador_racha`;
CREATE TABLE IF NOT EXISTS `jogador_racha` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jogador` int NOT NULL,
  `racha` int NOT NULL,
  `actived` tinyint(1) NOT NULL,
  `created` timestamp NOT NULL,
  `updated` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jogador` (`jogador`),
  KEY `jogador_racha_ibfk_2` (`racha`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `jogador_racha`
--

INSERT INTO `jogador_racha` (`id`, `jogador`, `racha`, `actived`, `created`, `updated`) VALUES
(1, 1, 1, 1, '2023-05-31 11:12:29', '2023-05-31 11:12:29');

-- --------------------------------------------------------

--
-- Estrutura da tabela `jogo`
--

DROP TABLE IF EXISTS `jogo`;
CREATE TABLE IF NOT EXISTS `jogo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` timestamp NOT NULL,
  `valor_pago` int NOT NULL,
  `racha` int NOT NULL,
  `actived` tinyint(1) NOT NULL,
  `created` timestamp NOT NULL,
  `updated` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `racha` (`racha`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `jogo`
--

INSERT INTO `jogo` (`id`, `data`, `valor_pago`, `racha`, `actived`, `created`, `updated`) VALUES
(1, '2023-05-25 11:12:50', 100, 1, 1, '2023-05-31 11:12:50', '2023-05-31 11:12:50');

-- --------------------------------------------------------

--
-- Estrutura da tabela `posicao`
--

DROP TABLE IF EXISTS `posicao`;
CREATE TABLE IF NOT EXISTS `posicao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `posicao` varchar(50) NOT NULL,
  `actived` tinyint(1) NOT NULL,
  `created` timestamp NOT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `posicao`
--

INSERT INTO `posicao` (`id`, `posicao`, `actived`, `created`, `updated`) VALUES
(1, 'Sem posição', 1, '2023-06-05 12:50:28', '2023-06-05 12:50:28'),
(5, 'Ala', 1, '2023-05-24 14:55:22', '2023-05-24 16:08:01'),
(6, 'Meia', 1, '2023-05-24 15:57:17', '2023-05-24 16:08:13');

-- --------------------------------------------------------

--
-- Estrutura da tabela `quadra`
--

DROP TABLE IF EXISTS `quadra`;
CREATE TABLE IF NOT EXISTS `quadra` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `actived` tinyint(1) NOT NULL,
  `created` timestamp NOT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `quadra`
--

INSERT INTO `quadra` (`id`, `nome`, `actived`, `created`, `updated`) VALUES
(1, 'k10', 1, '2023-05-25 11:32:13', '2023-05-25 11:32:13');

-- --------------------------------------------------------

--
-- Estrutura da tabela `racha`
--

DROP TABLE IF EXISTS `racha`;
CREATE TABLE IF NOT EXISTS `racha` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `quadra` int NOT NULL,
  `caixa` int NOT NULL,
  `actived` tinyint(1) NOT NULL,
  `created` timestamp NOT NULL,
  `updated` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `quadra` (`quadra`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `racha`
--

INSERT INTO `racha` (`id`, `nome`, `quadra`, `caixa`, `actived`, `created`, `updated`) VALUES
(1, 'racha dos ruim', 1, 10, 1, '2023-05-25 11:41:31', '2023-05-25 11:41:31');

-- --------------------------------------------------------

--
-- Estrutura da tabela `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `role`
--

INSERT INTO `role` (`id`, `role`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `idade` int NOT NULL,
  `telefone` varchar(30) NOT NULL,
  `cpf` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `actived` tinyint(1) NOT NULL,
  `created` timestamp NOT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`id`, `nome`, `idade`, `telefone`, `cpf`, `email`, `senha`, `actived`, `created`, `updated`, `role`) VALUES
(1, 'Gui', 22, '34984039344', '', 'gui@gmail.com', '$2a$10$zN86Q6M6fixWNco5vp/41ueClkxTQV292zuk3cci.JvvcE4nqZka6', 1, '2023-05-25 12:21:30', '2023-06-06 14:30:13', 1),
(12, 'aaaa', 56, '3312312312', '', 'aaaa@email.com', '$2a$10$CwP81bMXy130272t2/Jvi.caFeFII5vEAH2529.8KaW2XyeaSevZC', 1, '2023-06-05 12:58:14', '2023-06-05 12:58:14', 1),
(13, 'aaaa', 56, '3312312312', '', 'aasdasdaaa@email.com', '$2a$10$C450oOQhH3qVfyUz.oqyuuBge1deiEyMfKzDnzbnm43G84pczdymC', 0, '2023-06-05 12:59:09', '2023-06-05 14:41:27', 1),
(14, 'aaaa', 56, '3312312312', '', 'aaa432423a@email.com', '$2a$10$8yRAO4VhqF5TUvSy3h3ecOiEMe0k2/pnjbfuxIgokJ.om4oGt1Sly', 0, '2023-06-05 12:59:20', '2023-06-05 14:44:12', 1),
(15, 'aaaa', 56, '3312312312', '', 'aaaerf3erefs432423a@email.com', '$2a$10$6mpo/Rz2PDIkjABAX535v.Hd.z6.Jlh.pMYwaNOZNK/LV9Xxx4Ltm', 0, '2023-06-05 12:59:37', '2023-06-05 14:42:05', 1),
(16, 'aaaa', 56, '3312312312', '', 'aaafgdt45gda@email.com', '$2a$10$Z5V2.VoJiSIxbN1Nsf5l9OAGymYXhqWQ9sW/uIu4g2VpcTK99flq.', 1, '2023-06-05 13:00:00', '2023-06-05 13:00:00', 1),
(17, 'aaaa', 56, '3312312312', '', 'aaaadsfdsfsdfds@email.com', '$2a$10$QI7JFfA0CqFihqiL3Kz6Nuno8P9yuKR9D2g/ctBBJJnB8L4wy2FPu', 0, '2023-06-05 13:10:31', '2023-06-05 14:41:30', 1),
(18, 'aaaa', 56, '3312312312', '', 'aaa54354353a@email.com', '$2a$10$SUnYT5Ye6vQST7G6DM1eGuDLzXTzcruyBR02/NB.vpiE/hNMb9adm', 1, '2023-06-05 13:10:45', '2023-06-05 13:17:34', 1),
(23, 'a', 2, 'a', '', 'bb@email.com', '123', 0, '2023-06-21 14:07:32', '2023-06-06 13:59:28', 2),
(24, 'a', 2, 'a', '', 'bbb@email.com', '123', 0, '2023-06-21 14:07:32', '2023-06-05 14:42:13', 2),
(25, 'a', 2, 'a', '', 'b21312@email.com', '123', 1, '2023-06-21 14:07:32', '2023-06-13 14:07:32', 2),
(26, 'a', 2, 'a', '', 'b321321@email.com', '123', 1, '2023-06-21 14:07:32', '2023-06-05 14:44:18', 2),
(27, 'a', 2, 'a', '', 'bdfsd@email.com', '123', 0, '2023-06-21 14:07:32', '2023-06-05 14:42:48', 2),
(28, 'a', 2, 'a', '', 'bfdghfd@email.com', '123', 0, '2023-06-21 14:07:32', '2023-06-06 13:56:43', 2),
(32, 'teste', 20, '236712362', '', 'teste@gmail.com', '$2a$10$0UjeDbrznlV1QI7k/RtfUOzNWB0X4.INkTh/LQkZZnxTP.HiNvibi', 1, '2023-06-06 12:13:23', '2023-06-06 12:13:23', 1),
(33, 'hfds', 30, '9834923', '', 'jda@mfak.com', '$2a$10$OGqcNt3rlx5ETB5nGR.z4eXo4u6X7x5avjqYSBsiwtowfMWRj0C0m', 1, '2023-06-06 16:23:04', '2023-06-06 16:23:04', 1);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `jogador`
--
ALTER TABLE `jogador`
  ADD CONSTRAINT `jogador_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `jogador_ibfk_2` FOREIGN KEY (`posicao`) REFERENCES `posicao` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `jogador_racha`
--
ALTER TABLE `jogador_racha`
  ADD CONSTRAINT `jogador_racha_ibfk_1` FOREIGN KEY (`jogador`) REFERENCES `jogador` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `jogador_racha_ibfk_2` FOREIGN KEY (`racha`) REFERENCES `racha` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `jogo`
--
ALTER TABLE `jogo`
  ADD CONSTRAINT `jogo_ibfk_1` FOREIGN KEY (`racha`) REFERENCES `racha` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `racha`
--
ALTER TABLE `racha`
  ADD CONSTRAINT `racha_ibfk_1` FOREIGN KEY (`quadra`) REFERENCES `quadra` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Limitadores para a tabela `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
