/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : mp

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-03-01 23:28:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '直属上级id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1094592041087729668 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '刘明强', '31', null, '1087982257332887553', '2020-01-17 00:39:47');
INSERT INTO `user` VALUES ('1087982257332887553', '大boss', '40', 'boss@baomidou.com', null, '2019-01-11 14:20:20');
INSERT INTO `user` VALUES ('1088248166370832385', '王天风', '26', 'wtf2@baomidou.com', '0', '2019-02-05 11:12:22');
INSERT INTO `user` VALUES ('1088250446457389058', '李艺伟', '29', 'lyw@baomidou.com', '1088248166370832385', '2019-02-14 08:31:16');
INSERT INTO `user` VALUES ('1094590409767661570', '张雨琪', '31', 'zjq@baomidou.com', '1088248166370832385', '2019-01-14 09:15:15');
INSERT INTO `user` VALUES ('1094592041087729666', '刘红雨', '32', 'lhm@baomidou.com', '1088248166370832385', '2019-01-14 09:48:16');
INSERT INTO `user` VALUES ('1094592041087729667', '张草update', '24', 'zc@baomidou.com', '0', '2020-01-30 23:23:33');

-- ----------------------------
-- Table structure for usergj
-- ----------------------------
DROP TABLE IF EXISTS `usergj`;
CREATE TABLE `usergj` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '直属上级id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT '1' COMMENT '版本',
  `deleted` int(1) DEFAULT '0' COMMENT '逻辑删除标识(0.未删除,1.已删除)',
  PRIMARY KEY (`id`),
  KEY `manager_fk` (`manager_id`),
  CONSTRAINT `manager_fk` FOREIGN KEY (`manager_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usergj
-- ----------------------------
INSERT INTO `usergj` VALUES ('1087982257332887553', '大boss', '40', 'boss@baomidou.com', null, '2019-01-11 14:20:20', null, '1', '0');
INSERT INTO `usergj` VALUES ('1088248166370832385', '王天风', '25', 'wtf@baomidou.com', '1087982257332887553', '2019-02-05 11:12:22', null, '1', '0');
INSERT INTO `usergj` VALUES ('1088250446457389058', '李艺伟', '28', 'lyw@baomidou.com', '1088248166370832385', '2019-02-14 08:31:16', null, '1', '0');
INSERT INTO `usergj` VALUES ('1094590409767661570', '张雨琪', '31', 'zjq@baomidou.com', '1088248166370832385', '2019-01-14 09:15:15', null, '1', '0');
INSERT INTO `usergj` VALUES ('1094592041087729666', '刘红雨', '99', 'lhm@baomidou.com', '1088248166370832385', '2019-01-14 09:48:16', '2020-03-01 14:04:17', '7', '0');
