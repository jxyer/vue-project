/*
 Navicat Premium Data Transfer

 Source Server         : jxy
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : jxy_blog

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 04/02/2021 18:39:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `userId` int NOT NULL,
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '概述',
  `viewCount` int NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章内容',
  `categoryId` int NULL DEFAULT NULL COMMENT '所属类别',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `_copy_5`(`userId`) USING BTREE,
  INDEX `_copy_4`(`categoryId`) USING BTREE,
  CONSTRAINT `_copy_4` FOREIGN KEY (`categoryId`) REFERENCES `article_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `_copy_5` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (25, '123', '2021-02-04 16:38:06', '2021-02-04 16:38:06', 5, '这是给测试', 37, '请输入内容', 2);
INSERT INTO `article` VALUES (26, '13', '2021-02-04 17:09:22', '2021-02-04 17:09:22', 5, '21', 4, '<p>dasdsad</p>', 1);

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article_category
-- ----------------------------
INSERT INTO `article_category` VALUES (1, '编程语言', '这是java区', '../api_141.94552529183px_1285311_easyicon.net.png');
INSERT INTO `article_category` VALUES (2, '日常', '哈哈这是日常专区', '../santa_claus_128px_1310564_easyicon.net.png');

-- ----------------------------
-- Table structure for article_type
-- ----------------------------
DROP TABLE IF EXISTS `article_type`;
CREATE TABLE `article_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `typename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `articleId` int NULL DEFAULT NULL,
  `image` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `文章类型_文章`(`articleId`) USING BTREE,
  CONSTRAINT `文章类型_文章` FOREIGN KEY (`articleId`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article_type
-- ----------------------------
INSERT INTO `article_type` VALUES (1, 'vue', NULL, '../Vue_128px_1120004_easyicon.net.png');
INSERT INTO `article_type` VALUES (2, 'spring', NULL, '../Spring_Leaf_72px_1118130_easyicon.net.png');
INSERT INTO `article_type` VALUES (3, 'spring-boot', NULL, '../Spring_Leaf_72px_1118130_easyicon.net.png');
INSERT INTO `article_type` VALUES (4, 'css', NULL, '../css_91.090909090909px_1210202_easyicon.net.png');
INSERT INTO `article_type` VALUES (5, 'element-ui', NULL, '../infographic_elements_128.21843003413px_1257397_easyicon.net.png');
INSERT INTO `article_type` VALUES (6, 'java', NULL, '../java_112.84634760705px_1173942_easyicon.net.png');
INSERT INTO `article_type` VALUES (32, 'javascript', NULL, '../prog_js_128px_1097207_easyicon.net.png');
INSERT INTO `article_type` VALUES (34, 'spring', 26, NULL);

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NULL DEFAULT NULL,
  `expireTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  CONSTRAINT `token_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (21, 14, '2021-01-27 19:55:39', '2021-01-27 07:55:39', 'f345db437f134aa79f5f3c4094fef57e');
INSERT INTO `token` VALUES (30, 16, '2021-02-04 04:13:18', '2021-02-03 16:13:18', 'c9e35ffea42d4ff1bd41451e43e6c7a9');
INSERT INTO `token` VALUES (35, 5, '2021-02-05 03:36:19', '2021-02-04 15:36:19', '2b92289508534e4a816197291d8fde57');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createTime` timestamp NOT NULL,
  `deleted` tinyint NOT NULL,
  `lastTime` timestamp NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (5, '1', '1', '2021-01-19 00:00:00', 0, NULL, NULL);
INSERT INTO `user` VALUES (6, '12', '123', '2021-01-19 00:00:00', 0, NULL, NULL);
INSERT INTO `user` VALUES (11, '11', '11', '2021-01-19 00:00:00', 0, NULL, NULL);
INSERT INTO `user` VALUES (14, '111', '22', '2021-01-19 00:00:00', 0, NULL, NULL);
INSERT INTO `user` VALUES (15, '32', '222', '2021-02-03 00:00:00', 0, NULL, NULL);
INSERT INTO `user` VALUES (16, '1111', '1', '2021-02-03 00:00:00', 0, NULL, NULL);

-- ----------------------------
-- Table structure for user_comment
-- ----------------------------
DROP TABLE IF EXISTS `user_comment`;
CREATE TABLE `user_comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `articleId` int NULL DEFAULT NULL COMMENT '文章id',
  `userId` int NULL DEFAULT NULL COMMENT '用户id',
  `toUserId` int NULL DEFAULT NULL,
  `parentId` int NULL DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_用户评论_文章_1`(`articleId`) USING BTREE,
  INDEX `fk_user_comment_user_comment_1`(`userId`) USING BTREE,
  INDEX `_copy_1`(`toUserId`) USING BTREE,
  INDEX `父子表`(`parentId`) USING BTREE,
  CONSTRAINT `fk_user_comment_user_comment_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_用户评论_文章_1` FOREIGN KEY (`articleId`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `父子表` FOREIGN KEY (`parentId`) REFERENCES `user_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_comment
-- ----------------------------
INSERT INTO `user_comment` VALUES (0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_comment` VALUES (1, '11', '2021-02-04 17:02:47', 25, 5, 0, 0);

-- ----------------------------
-- Table structure for user_type
-- ----------------------------
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  CONSTRAINT `user_type_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_type
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
