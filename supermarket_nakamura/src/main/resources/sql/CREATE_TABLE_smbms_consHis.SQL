CREATE DATABASE IF NOT EXISTS ems;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for smbms_consHis
-- ----------------------------
DROP TABLE IF EXISTS `smbms_consHis`;
CREATE TABLE `smbms_consHis`  (
  `historyId` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主キー',
  `empCode` bigint(1) NULL DEFAULT NULL COMMENT '社員コード',
  `productId` bigint(1) NULL DEFAULT NULL COMMENT '商品ID',
  `orderDate` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '消費時間',
  `amount` bigint(1) NULL DEFAULT NULL COMMENT '消費金額',
  `paymentMethod` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '支払い方法',
  `orderID` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '注文番号',
  `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '登録社員コード',
  `creationDate` datetime(0) NULL DEFAULT NULL COMMENT '登録日時',
  `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新社員コード',
  `modifyDate` datetime(0) NULL DEFAULT NULL COMMENT '更新日時',
  PRIMARY KEY (`historyId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
