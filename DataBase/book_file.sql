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

 Date: 01/06/2023 20:16:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_file
-- ----------------------------
DROP TABLE IF EXISTS `book_file`;
CREATE TABLE `book_file`  (
  `book_id` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电子书的id',
  `file_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id(书名)组成的文件名',
  `suffix` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '后缀名',
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '下载链接',
  `size` bigint(0) NOT NULL COMMENT '文件大小',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_file
-- ----------------------------
INSERT INTO `book_file` VALUES ('7mELw6hS11q9', '7mELw6hS11q9(寒山纪)', '.docx', 'http://localhost/table/7mELw6hS11q9(寒山纪).docx', 1991);
INSERT INTO `book_file` VALUES ('GhKgl7BPQHXn', 'GhKgl7BPQHXn(数学分析下)', '.pdf', 'http://localhost/table/GhKgl7BPQHXn(数学分析下).pdf', 53014);
INSERT INTO `book_file` VALUES ('TCZATAFnMUD3', 'TCZATAFnMUD3(数学分析上)', '.pdf', 'http://localhost/table/TCZATAFnMUD3(数学分析上).pdf', 57848);

SET FOREIGN_KEY_CHECKS = 1;
