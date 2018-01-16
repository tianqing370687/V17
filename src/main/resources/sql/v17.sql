/*
Navicat MySQL Data Transfer

Source Server         : dev
Source Server Version : 50619
Source Host           : 10.1.1.66:3306
Source Database       : v17

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2018-01-16 18:25:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for v17_admin
-- ----------------------------
DROP TABLE IF EXISTS `v17_admin`;
CREATE TABLE `v17_admin` (
  `admin_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(30) NOT NULL COMMENT '管理员用户名',
  `admin_password` varchar(50) NOT NULL COMMENT '管理员密码',
  `password_salt` char(50) NOT NULL COMMENT '盐值',
  `login_time` datetime DEFAULT NULL COMMENT '登陆时间',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for v17_personal_info
-- ----------------------------
DROP TABLE IF EXISTS `v17_personal_info`;
CREATE TABLE `v17_personal_info` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT,
  `info_name` varchar(20) NOT NULL COMMENT '姓名',
  `info_birthday` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '生日',
  `info_age` int(3) NOT NULL COMMENT '年龄',
  `info_birthplace` varchar(30) NOT NULL COMMENT '出生地',
  `info_residence` varchar(200) DEFAULT NULL COMMENT '现住地',
  `info_performing_experience` varchar(200) DEFAULT NULL COMMENT '演艺经历',
  `info_family_composition` varchar(200) DEFAULT NULL COMMENT '家庭构成',
  `info_occupation` varchar(200) NOT NULL COMMENT '职业',
  `info_schools_majors` varchar(200) NOT NULL COMMENT '学校与专业',
  `info_grade` varchar(200) NOT NULL COMMENT '年级',
  `info_place_of_study` varchar(200) NOT NULL COMMENT '就读地/工作地',
  `info_phone_num` varchar(200) NOT NULL COMMENT '手机',
  `info_qq` int(12) NOT NULL COMMENT 'qq',
  `info_email` varchar(200) NOT NULL COMMENT '邮箱',
  `info_weibo` varchar(200) DEFAULT NULL COMMENT '微博',
  `info_home_phone` varchar(200) DEFAULT NULL COMMENT '家庭电话',
  `info_specialty` varchar(200) DEFAULT NULL COMMENT '特长',
  `info_interest` varchar(200) DEFAULT NULL COMMENT '兴趣',
  `info_awards` varchar(200) DEFAULT NULL COMMENT '获奖情况',
  `info_dream` varchar(200) DEFAULT NULL COMMENT '梦想',
  `info_idol` varchar(200) DEFAULT NULL COMMENT '喜欢的艺人、偶像或配音演员',
  `info_comic` varchar(200) DEFAULT NULL COMMENT '喜欢的动漫、游戏作品',
  `info_film_works` varchar(200) DEFAULT NULL COMMENT '喜欢的影视作品',
  `info_website` varchar(200) DEFAULT NULL COMMENT '常去的网站',
  `info_app` varchar(200) DEFAULT NULL COMMENT '常用的app',
  `info_want_to_say` varchar(500) DEFAULT NULL COMMENT '相对你未来可能开始的生涯说点什么',
  `info_video1_url` varchar(200) DEFAULT NULL COMMENT '配音DEMON/声线展示视频地址',
  `info_video2_url` varchar(200) DEFAULT NULL COMMENT '其他才艺展示视频地址',
  `info_mugshot_img_url` varchar(200) DEFAULT NULL COMMENT '大头照地址',
  `info_halflength_img_url` varchar(200) DEFAULT NULL COMMENT '半身照地址',
  `info_fullbody_img_url` varchar(200) DEFAULT NULL COMMENT '全身照地址',
  `info_serial_num` char(11) DEFAULT NULL COMMENT '序列号',
  `info_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for v17_verification_code
-- ----------------------------
DROP TABLE IF EXISTS `v17_verification_code`;
CREATE TABLE `v17_verification_code` (
  `phone_num` varchar(30) NOT NULL COMMENT '主键，报名人手机号',
  `verification_code` char(6) NOT NULL DEFAULT '' COMMENT '验证码',
  PRIMARY KEY (`phone_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
