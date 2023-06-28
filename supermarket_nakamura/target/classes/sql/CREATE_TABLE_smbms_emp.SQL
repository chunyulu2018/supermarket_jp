CREATE DATABASE IF NOT EXISTS ems;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ems_user
-- ----------------------------
DROP TABLE IF EXISTS `smbms_emp`;
CREATE TABLE `smbms_emp`  (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT 'id(主キー)',
  `empCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '社員コード(主キー)',
  `empName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '社員名',
  `rank` int(1) NULL DEFAULT NULL COMMENT '社員ランク',
  `age` int(1) NULL DEFAULT NULL COMMENT '年齢',
  `gender` int(1) NULL DEFAULT NULL COMMENT '性別（1:女、 2:男）',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '携帯電話',
  `mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'メールアドレス',
  `IDCard` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'IDカード',
  `picture` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '写真のファイルパス',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '住所',
  `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '登録社員コード',
  `creationDate` datetime(0) NULL DEFAULT NULL COMMENT '登録日時',
  `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新社員コード',
  `modifyDate` datetime(0) NULL DEFAULT NULL COMMENT '更新日時',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

