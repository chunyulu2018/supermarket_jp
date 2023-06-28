CREATE DATABASE IF NOT EXISTS ems;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for smbms_goods
-- ----------------------------
DROP TABLE IF EXISTS `smbms_goods`;
CREATE TABLE `smbms_goods`  (
  `ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主キー',
  `Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品名',
  `place` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '場所',
  `unitPrice` bigint(10) NULL DEFAULT NULL COMMENT '単価',
  `quantity` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '件数',
  `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '登録社員コード',
  `creationDate` datetime(0) NULL DEFAULT NULL COMMENT '登録日時',
  `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新社員コード',
  `modifyDate` datetime(0) NULL DEFAULT NULL COMMENT '更新日時',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
