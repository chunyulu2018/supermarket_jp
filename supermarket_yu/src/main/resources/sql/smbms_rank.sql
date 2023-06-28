/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80003
 Source Host           : localhost:3306
 Source Schema         : smbms

 Target Server Type    : MySQL
 Target Server Version : 80003
 File Encoding         : 65001

 Date: 20/06/2023 17:47:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for smbms_rank
-- ----------------------------
DROP TABLE IF EXISTS `smbms_rank`;
CREATE TABLE `smbms_rank`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `rankCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ランクコード',
  `rankName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ランク名',
  `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `creationDate` datetime(0) NULL DEFAULT NULL COMMENT '作成時間',
  `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '修改者',
  `modifyDate` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of smbms_rank
-- ----------------------------
INSERT INTO `smbms_rank` VALUES (1, 'SMBMS_NOT', '非会員', 1, '2016-04-13 00:00:00', NULL, NULL);
INSERT INTO `smbms_rank` VALUES (2, 'SMBMS_SILVER', 'シルバー', 1, '2016-04-13 00:00:00', NULL, NULL);
INSERT INTO `smbms_rank` VALUES (3, 'SMBMS_GOLD', 'ゴールド', 1, '2016-04-13 00:00:00', NULL, NULL);
INSERT INTO `smbms_rank` VALUES (4, 'SMBMS_PLATINUM', 'プラチナ', 1, '2016-04-13 00:00:00', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
