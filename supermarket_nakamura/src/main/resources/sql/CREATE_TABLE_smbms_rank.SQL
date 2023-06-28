SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ems_user
-- ----------------------------
DROP TABLE IF EXISTS `smbms_rank`;
CREATE TABLE `smbms_rank`  (
  `id` int(1) NOT NULL AUTO_INCREMENT,
  `rankName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ランク名',
  `cardType` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '会員カードの種類',
  `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '登録社員コード',
  `creationDate` datetime(0) NULL DEFAULT NULL COMMENT '登録日時',
  `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新社員コード',
  `modifyDate` datetime(0) NULL DEFAULT NULL COMMENT '更新日時',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
