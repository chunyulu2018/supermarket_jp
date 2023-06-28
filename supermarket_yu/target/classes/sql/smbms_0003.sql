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
-- Table structure for smbms_0003
-- ----------------------------
DROP TABLE IF EXISTS `smbms_0003`;
CREATE TABLE `smbms_0003`  (
  `productId` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '商品コード',
  `userCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ユーザコード',
  `userName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ユーザ名',
  `rank` int COMMENT '会員ランク',
  `orderdate` datetime(0) NULL DEFAULT NULL COMMENT '消費時間',
  `place` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '場所',
  `product` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '買った商品',
  `unitPrice` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '単価',
  `quantity` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '件数',
  `amount` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '消費金額',
  `paymentMethod` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '支払い方法',
  `orderID` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '注文番号',
  `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '登録者',
  `creationDate` datetime(0) NULL DEFAULT NULL COMMENT '登録日時',
  `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `modifyDate` datetime(0) NULL DEFAULT NULL COMMENT '更新日時',
  PRIMARY KEY (`productId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of smbms_address
-- ----------------------------
INSERT INTO `smbms_0003` VALUES ('ZYX-876543210', 'BJ_DEMO001', '刘晓明', '1', '2014-02-15 10:23:45', '北京分店0001', '风衣H5', '5799.99', '2', '11599.98', '現金', '200012345678901', 1, '2014-02-15 11:00:00', NULL, NULL);
INSERT INTO `smbms_0003` VALUES ('QWE-192837465', 'SH_DEMO002', '王丽娜', '2', '2015-06-30 18:54:32', '上海分店0002', '猫粮Ⅰ型100g', '18.00', '3', '54.00', 'カード', '200098765432109', 1, '2015-06-30 19:00:00', NULL, NULL);
INSERT INTO `smbms_0003` VALUES ('RST-564738291', 'BJ_DEMO001', '刘晓明', '1', '2016-12-01 20:59:59', '北京分店0001', '筒装乐事薯片120g', '12.40', '5', '62.00', '現金', '200056789012345', 1, '2016-12-02 21:00:00', NULL, NULL);
INSERT INTO `smbms_0003` VALUES ('MNO-748596132', 'SC_DEMO004', '李梦琪', '1', '2017-09-10 08:00:00', '四川分店0003', '防晒服H8', '388.99', '1', '388.99', 'カード', '200043210987654', 1, '2017-09-10 09:00:00', NULL, NULL);
INSERT INTO `smbms_0003` VALUES ('UVW-309876542', 'SH_DEMO002', '王丽娜', '2', '2018-03-25 15:37:48', '上海分店0002', '狗粮Ⅱ型250g', '47.00', '1', '47.00', '現金', '200067890123456', 1, '2018-03-25 16:00:00', NULL, NULL);
INSERT INTO `smbms_0003` VALUES ('GHI-625481937', 'JS_DEMO006', '赵雅芳', '3', '2019-07-14 12:12:12', '江苏分店0004', '清风抽纸200g', '15.66', '10', '156.60', 'カード', '200054321098765', 1, '2019-07-14 13:00:00', NULL, NULL);
INSERT INTO `smbms_0003` VALUES ('KLM-417293658', 'JS_DEMO006', '赵雅芳', '3', '2014-05-05 05:05:05', '江苏分店0004', '吸尘器H9', '3499.00', '1', '3499.00', 'カード', '200078901234567', 1, '2014-05-05 06:00:00', NULL, NULL);
INSERT INTO `smbms_0003` VALUES ('DEF-983625471', 'ZJ_DEMO007', '孙建华', '2', '2015-10-10 10:10:10', '浙江分店0005', '飞傲解码耳放btr7', '1200.00', '1', '1200.00', '現金', '200065432109876', 1, '2015-10-10 11:00:00', NULL, NULL);
INSERT INTO `smbms_0003` VALUES ('BCD-762549183', 'JS_DEMO006', '赵雅芳', '3', '2016-07-07 07:07:07', '江苏分店0004', '索尼A9S3', '24499.00', '1', '24499.00', 'カード', '200089012345678', 1, '2016-07-07 08:00:00', NULL, NULL);
INSERT INTO `smbms_0003` VALUES ('JKL-294817365', 'ZJ_DEMO007', '孙建华', '2', '2017-11-11 11:11:11', '浙江分店0005', '飞傲播放器m15s', '6400.00', '1', '6400.00', 'カード', '200076543210987', 1, '2017-11-11 12:00:00', NULL, NULL);
INSERT INTO `smbms_0003` VALUES ('NOP-518374629', 'BJ_DEMO001', '刘晓明', '1', '2018-08-08 08:08:08', '北京分店0001', '可口可乐2L', '6.90', '5', '34.5', '現金', '200090123456789', 1, '2018-08-08 09:00:00', NULL, NULL);
INSERT INTO `smbms_0003` VALUES ('XYZ-637492815', 'SX_DEMO008', '周慧敏', '0', '2019-04-04 09:04:04', '陕西分店0006', '机械键盘H10', '369.88', '2', '739.76', '現金', '200087654321098', 1, '2019-04-04 10:00:00', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
