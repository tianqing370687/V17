/*
Navicat MySQL Data Transfer

Source Server         : dev
Source Server Version : 50619
Source Host           : 10.1.1.66:3306
Source Database       : v17

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2018-01-12 09:57:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for v17_admin
-- ----------------------------
DROP TABLE IF EXISTS `v17_admin`;
CREATE TABLE `v17_admin` (
  `admin_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(30) NOT NULL COMMENT '管理员用户名',
  `admin_password` varchar(30) NOT NULL COMMENT '管理员密码',
  `password_salt` char(6) NOT NULL COMMENT '盐值',
  `login_time` datetime DEFAULT NULL COMMENT '登陆时间',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
