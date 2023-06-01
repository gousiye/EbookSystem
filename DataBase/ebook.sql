/*
 Navicat Premium Data Transfer

 Source Server         : MySql1
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : sprdb

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 01/06/2023 20:16:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ebook
-- ----------------------------
DROP TABLE IF EXISTS `ebook`;
CREATE TABLE `ebook`  (
  `id` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '编号',
  `book_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书名',
  `author_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '第一作者名',
  `suffix` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件格式',
  `book_language` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '语言',
  `download` int(0) NULL DEFAULT NULL COMMENT '下载量',
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '简介\r\n',
  `publisher` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '出版商',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ebook
-- ----------------------------
INSERT INTO `ebook` VALUES ('7mELw6hS11q9', '寒山纪', '看长亭晚', 'docx', '中文', 0, '百合小说', '网络小说', '2023-06-01 19:13:20', '2023-06-01 19:13:20');
INSERT INTO `ebook` VALUES ('GhKgl7BPQHXn', '数学分析下', '陈纪修', 'pdf', '中文', 0, '数学分析下', '复旦大学出版社', '2023-06-01 19:22:12', '2023-06-01 19:22:12');
INSERT INTO `ebook` VALUES ('TCZATAFnMUD3', '数学分析上', '陈纪修', 'pdf', '中文', 2, '数学分析', '复旦大学出版社', '2023-06-01 19:11:51', '2023-06-01 19:16:26');

SET FOREIGN_KEY_CHECKS = 1;
