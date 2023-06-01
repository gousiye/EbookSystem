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

 Date: 01/06/2023 20:16:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `class_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES (1, '数学');
INSERT INTO `classification` VALUES (2, '百合');

SET FOREIGN_KEY_CHECKS = 1;
