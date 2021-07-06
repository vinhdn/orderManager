-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.18-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for shop
CREATE DATABASE IF NOT EXISTS `shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `shop`;

-- Dumping structure for table shop.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `id` varchar(25) NOT NULL,
  `name` varchar(25) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.order
CREATE TABLE IF NOT EXISTS `order` (
  `id` varchar(25) NOT NULL,
  `shop_id` varchar(25) NOT NULL,
  `user_create` varchar(25) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `sales_channel` varchar(25) DEFAULT 'Facebook',
  `customer_id` varchar(25) DEFAULT NULL,
  `total_price` bigint(20) DEFAULT 0,
  `note` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_order_shop` (`shop_id`),
  KEY `FK_order_user` (`user_create`),
  KEY `FK_order_customer` (`customer_id`),
  CONSTRAINT `FK_order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_order_shop` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `FK_order_user` FOREIGN KEY (`user_create`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.order_order_products
CREATE TABLE IF NOT EXISTS `order_order_products` (
  `order_id` varchar(255) NOT NULL,
  `order_products_id` bigint(20) NOT NULL,
  PRIMARY KEY (`order_id`,`order_products_id`),
  KEY `FK1651lbpfj0962xy855areehpr` (`order_products_id`),
  CONSTRAINT `FK1651lbpfj0962xy855areehpr` FOREIGN KEY (`order_products_id`) REFERENCES `order_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.order_product
CREATE TABLE IF NOT EXISTS `order_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(25) NOT NULL,
  `product_id` varchar(25) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 0,
  `price` bigint(20) NOT NULL DEFAULT 0,
  `total` bigint(20) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FK_order_product_order` (`order_id`),
  KEY `FK_order_product_product` (`product_id`),
  CONSTRAINT `FK_order_product_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `FK_order_product_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.order_ship
CREATE TABLE IF NOT EXISTS `order_ship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(25) NOT NULL,
  `ship_partner_id` varchar(25) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '0 - đã chuẩn bị, 1 - đang gửi, 2 - hoàn thành, 3 - hoàn trả',
  `price` bigint(20) DEFAULT 0,
  `note` varchar(50) DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ordership_order` (`order_id`),
  KEY `FK_ordership_ship_partner` (`ship_partner_id`),
  CONSTRAINT `FK_ordership_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`shop_id`),
  CONSTRAINT `FK_ordership_ship_partner` FOREIGN KEY (`ship_partner_id`) REFERENCES `ship_partner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` varchar(25) NOT NULL,
  `name` varchar(25) NOT NULL,
  `origin` varchar(50) DEFAULT NULL,
  `price` bigint(20) DEFAULT 0,
  `sale_price` bigint(20) DEFAULT 0,
  `quantity` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.product_photo
CREATE TABLE IF NOT EXISTS `product_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(25) NOT NULL DEFAULT '',
  `link` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_productphoto_photo` (`product_id`),
  CONSTRAINT `FK_productphoto_photo` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.sales_channel
CREATE TABLE IF NOT EXISTS `sales_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL DEFAULT 'Facebook',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.ship_partner
CREATE TABLE IF NOT EXISTS `ship_partner` (
  `id` varchar(25) NOT NULL,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.shop
CREATE TABLE IF NOT EXISTS `shop` (
  `id` varchar(25) NOT NULL,
  `name` varchar(25) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.todo
CREATE TABLE IF NOT EXISTS `todo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.user
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `fullname` varchar(25) NOT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `is_deleted` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table shop.user_shop
CREATE TABLE IF NOT EXISTS `user_shop` (
  `username` varchar(25) NOT NULL,
  `shop_id` varchar(25) NOT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`username`,`shop_id`),
  KEY `FK_usershop_shop` (`shop_id`),
  CONSTRAINT `FK_usershop_shop` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `FK_usershop_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
