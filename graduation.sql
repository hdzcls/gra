/*
 Navicat Premium Data Transfer

 Source Server         : liushuai
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : graduation

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 10/04/2021 20:40:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `writer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `times` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of files
-- ----------------------------
INSERT INTO `files` VALUES (2, '3.三江学院毕业设计（论文）指导情况记录表-模板.doc', 'Fri Apr 09 14:11:54 CST 2021', NULL, 8);
INSERT INTO `files` VALUES (3, 'IJEST10-02-06-31.pdf', '2021-04-09 02:17:43', NULL, 14);
INSERT INTO `files` VALUES (5, '需求分析-参考.pptx', '2021-04-09 03:07:16', 'hdzc', 4);
INSERT INTO `files` VALUES (6, '12017052197刘帅 软件项目开发管理系统使用手册.doc', '2021-04-09 03:37:48', NULL, 7);
INSERT INTO `files` VALUES (7, '《综合项目2》任务书.doc', '2021-04-09 04:14:08', NULL, 2);

-- ----------------------------
-- Table structure for needs
-- ----------------------------
DROP TABLE IF EXISTS `needs`;
CREATE TABLE `needs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `module` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `function` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `writer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of needs
-- ----------------------------
INSERT INTO `needs` VALUES (7, '5', '5', '5', '5', '5');
INSERT INTO `needs` VALUES (8, '2', '2', '0', '0', '55');
INSERT INTO `needs` VALUES (9, '2', '2', '0', '0', '55');
INSERT INTO `needs` VALUES (10, 'kk2233', 'ii', 'iiuu', '111', '刘帅');
INSERT INTO `needs` VALUES (11, '品牌', '44', 'iiuu', '455', '刘帅');
INSERT INTO `needs` VALUES (12, '4', '54', '45', '5445', '刘帅');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `phone` bigint(20) NULL DEFAULT NULL,
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `workage` int(11) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456',
  `role` int(11) NULL DEFAULT NULL COMMENT '0管理员，1开发，2测试，3项目经理',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES (1, '刘帅', 23, 15380896937, 'hdzc', 1, '123456', 2);
INSERT INTO `person` VALUES (6, 'hdzc22', 22, 15380896937, '666', 333, '123456', 1);
INSERT INTO `person` VALUES (7, 'kk', 20, 15380896937, 'hdzc555', 333, '123456', 1);
INSERT INTO `person` VALUES (13, 'zhangsan', 22, 15380896937, 'hdzc55555', 2, '123456', 1);
INSERT INTO `person` VALUES (14, 'hdzc', 22, 15380896937, '2215555', 2222, '123456', 2);
INSERT INTO `person` VALUES (15, 'zhangsaniiiii', 20, 15380896937, '22', 2222, '123456', 2);
INSERT INTO `person` VALUES (16, 'hdzc', 20, 15380896937, 'hdzc5556699', 333, '123456', 1);

-- ----------------------------
-- Table structure for pn
-- ----------------------------
DROP TABLE IF EXISTS `pn`;
CREATE TABLE `pn`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL COMMENT '人id',
  `nid` int(11) NULL DEFAULT NULL COMMENT '需求id',
  `state` int(11) NULL DEFAULT 0 COMMENT '0未开始1进行中2已完成',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  INDEX `nid`(`nid`) USING BTREE,
  CONSTRAINT `pn_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pn_ibfk_2` FOREIGN KEY (`nid`) REFERENCES `needs` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pn
-- ----------------------------
INSERT INTO `pn` VALUES (2, 7, 11, 0);
INSERT INTO `pn` VALUES (3, 6, 10, 0);
INSERT INTO `pn` VALUES (4, 7, 9, 0);
INSERT INTO `pn` VALUES (5, 13, 8, 0);
INSERT INTO `pn` VALUES (6, 13, 7, 0);

-- ----------------------------
-- Table structure for pt
-- ----------------------------
DROP TABLE IF EXISTS `pt`;
CREATE TABLE `pt`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT 0 COMMENT '0未开始1进行中2已完成',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  INDEX `tid`(`tid`) USING BTREE,
  CONSTRAINT `pt_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pt_ibfk_2` FOREIGN KEY (`tid`) REFERENCES `test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pt
-- ----------------------------
INSERT INTO `pt` VALUES (2, 14, 7, 0);
INSERT INTO `pt` VALUES (3, 14, 8, 0);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `need_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `writer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `need_name`(`need_name`) USING BTREE,
  CONSTRAINT `test_ibfk_1` FOREIGN KEY (`need_name`) REFERENCES `needs` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (6, '6', '2', '6', '6', '6');
INSERT INTO `test` VALUES (7, 'mr', 'kk2233', '66', '5656', '刘帅');
INSERT INTO `test` VALUES (8, 'mr', '2', '66', '2323', '刘帅');

SET FOREIGN_KEY_CHECKS = 1;
