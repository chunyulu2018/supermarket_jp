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

 Date: 24/06/2022 17:45:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for smbms_0002
-- ----------------------------
DROP TABLE IF EXISTS `smbms_0002`;
CREATE TABLE `smbms_0002`  (
  `userCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '主キー。ユーザコード',
  `userName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ユーザ名',
  `rank` int COMMENT '会員ランク',
  `cardNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'カード番号',
  `cardType` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'カード種類',
  `cardExpirationDate` date NULL DEFAULT NULL COMMENT 'カード有効期限',
  `cardIssueDate` date NULL DEFAULT NULL COMMENT 'カード発行日',
  `cashBalance` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '残高',
  `point` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ポイント',
  PRIMARY KEY (`userCode`) USING BTREE

) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of smbms_0002
-- ----------------------------
INSERT INTO `smbms_0002` VALUES ('BJ_DEMO001', '刘晓明', '1', '1000 0023 9595 841', 'シルバー', '2018-01-15', '2013-01-15', '263852.43', '6400');
INSERT INTO `smbms_0002` VALUES ('SH_DEMO002', '王丽娜', '2', '1000 9012 3756 709', 'ゴールド', '2019-06-30', '2014-06-30', '94876.15', '8953');
INSERT INTO `smbms_0002` VALUES ('SC_DEMO004', '李梦琪', '1', '1000 0937 6543 210', 'シルバー', '2020-12-01', '2015-12-01', '5379.64', '560');
INSERT INTO `smbms_0002` VALUES ('JS_DEMO006', '赵雅芳', '3', '1000 5638 9023 001', 'プラチナ', '2018-09-10', '2013-09-10', '182635.72', '4369');
INSERT INTO `smbms_0002` VALUES ('ZJ_DEMO007', '孙建华', '2', '1000 5402 1033 009', 'ゴールド', '2019-03-25', '2014-03-25', '4067.98', '54018');

SET FOREIGN_KEY_CHECKS = 1;

