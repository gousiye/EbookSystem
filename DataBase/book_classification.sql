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

 Date: 01/06/2023 20:16:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_classification
-- ----------------------------
DROP TABLE IF EXISTS `book_classification`;
CREATE TABLE `book_classification`  (
  `book_id` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电子书的id',
  `classification_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别的id',
  PRIMARY KEY (`book_id`, `classification_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_classification
-- ----------------------------
INSERT INTO `book_classification` VALUES ('7mELw6hS11q9', '2');
INSERT INTO `book_classification` VALUES ('GhKgl7BPQHXn', '1');
INSERT INTO `book_classification` VALUES ('TCZATAFnMUD3', '1');

SET FOREIGN_KEY_CHECKS = 1;
