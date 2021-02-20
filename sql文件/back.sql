/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.21 : Database - wuai
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wuai` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `wuai`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` bigint NOT NULL COMMENT '唯一标识id',
  `title` varchar(100) NOT NULL COMMENT '文章标题',
  `author_id` int NOT NULL COMMENT '编写文章的用户id',
  `category_id` int NOT NULL COMMENT '文章分类',
  `content` text NOT NULL COMMENT '文章内容',
  `article_cover` varchar(300) NOT NULL COMMENT '文章封面',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `version` int NOT NULL DEFAULT '1' COMMENT '版本号',
  `love` int NOT NULL DEFAULT '0' COMMENT '点赞人数',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `article` */

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` bigint NOT NULL COMMENT '唯一标识id',
  `category_name` varchar(30) NOT NULL COMMENT '分类标题',
  `create_user_id` int NOT NULL COMMENT '创建用户的id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `version` int NOT NULL DEFAULT '1' COMMENT '版本号',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一标识id',
  `article_id` int NOT NULL COMMENT '所属的文章',
  `user_id` int NOT NULL COMMENT '评论人',
  `content` varchar(2000) NOT NULL COMMENT '评论的内容',
  `create_time` datetime NOT NULL COMMENT '评论时间',
  `user_id_parent` int NOT NULL COMMENT '回复的人的id',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int NOT NULL DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '用户唯一标识id',
  `username` varchar(60) NOT NULL COMMENT '用户名',
  `password` varchar(60) NOT NULL COMMENT '用户密码',
  `password_salt` varchar(60) NOT NULL COMMENT '密码盐值',
  `score` int NOT NULL DEFAULT '0' COMMENT '用户积分',
  `sex` varchar(2) DEFAULT NULL COMMENT '用户性别',
  `sign` varbinary(200) DEFAULT NULL COMMENT '用户签名',
  `age` int DEFAULT NULL COMMENT '用户年龄',
  `avatar` varchar(1000) NOT NULL COMMENT '用户头像连接地址',
  `open_id` varchar(1000) DEFAULT NULL COMMENT '微信id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int NOT NULL DEFAULT '1' COMMENT '版本号',
  `email` varchar(60) DEFAULT NULL COMMENT '用户邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`password_salt`,`score`,`sex`,`sign`,`age`,`avatar`,`open_id`,`create_time`,`update_time`,`is_delete`,`version`,`email`) values 
(1362296385294082049,'sam','sam','sam',0,NULL,NULL,20,'ddddddddd',NULL,'2021-02-18 15:31:27','2021-02-18 15:35:14',1,1,NULL),
(1362296529641058306,'jack','sam','sam',0,NULL,NULL,30,'ddddddddd',NULL,'2021-02-18 15:31:27','2021-02-18 15:35:14',0,1,NULL),
(1362296576499789825,'joey','sam','sam',0,NULL,NULL,12,'ddddddddd',NULL,'2021-02-18 15:31:27','2021-02-18 15:35:14',0,1,NULL),
(1362303701057884162,'rose','sam','sam',0,NULL,NULL,15,'bzzzzz',NULL,'2021-02-18 15:31:27','2021-02-18 15:43:49',0,4,NULL),
(1362310927940100098,'milk','sam','sam',0,NULL,NULL,25,'aaaaa',NULL,'2021-02-18 16:00:10','2021-02-18 16:00:10',0,1,NULL),
(1362310976849895426,'law','sam','sam',0,NULL,NULL,24,'aaaaa',NULL,'2021-02-18 16:00:22','2021-02-18 16:00:22',0,1,NULL),
(1362311023058501633,'wong','sam','sam',0,NULL,NULL,11,'aaaaa',NULL,'2021-02-18 16:00:33','2021-02-18 16:00:33',0,1,NULL),
(1362417619210162177,'body','bodyzz','bbbbb',0,NULL,NULL,NULL,'ccccccccccc',NULL,'2021-02-18 23:04:08','2021-02-18 23:04:08',0,1,NULL),
(1362418188209430529,'sam','sam','sam',0,NULL,NULL,NULL,'aaaaa',NULL,'2021-02-18 23:06:23','2021-02-18 23:06:23',0,1,NULL);

/*Table structure for table `user_follow` */

DROP TABLE IF EXISTS `user_follow`;

CREATE TABLE `user_follow` (
  `id` bigint NOT NULL COMMENT '唯一标识',
  `user_id` int NOT NULL COMMENT '被关注用户的id',
  `follow_id` int NOT NULL COMMENT '关注用户的id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int NOT NULL DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_follow` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
