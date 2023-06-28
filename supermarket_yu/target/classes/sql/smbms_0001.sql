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
-- Table structure for smbms_0001
-- ----------------------------
DROP TABLE IF EXISTS `smbms_0001`;
CREATE TABLE `smbms_0001`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主キー',
  `userCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ユーザコード',
  `userName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ユーザ名',
  `rank` int COMMENT '会員ランク',
  `age` int COMMENT '年齢',
  `gender` int COMMENT '性別（1:女、 2:男）',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '携帯電話',
  `mailAddress` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'メールアドレス',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '住所',
  `userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'IDカード番号',
  `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '登録者',
  `creationDate` datetime(0) NULL DEFAULT NULL COMMENT '登録日時',
  `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `modifyDate` datetime(0) NULL DEFAULT NULL COMMENT '更新日時',
  
  PRIMARY KEY (`id`) USING BTREE
  
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;
ALTER TABLE smbms_0001 ADD UNIQUE INDEX userCode_UNIQUE (`userCode`);
-- ----------------------------
-- Records of smbms_0001
-- ----------------------------
INSERT INTO `smbms_0001` VALUES (0, 'BJ_DEMO001', '刘晓明', '1', '24', '1', '13887654321', 'LiuXM-1234@126.com', '北京市海淀区中关村南大街1号院5号楼301室', '110101196003071234', 1, '2013-03-21 16:52:07', NULL, NULL);
INSERT INTO `smbms_0001` VALUES (1, 'SH_DEMO002', '王丽娜', '2', '28', '2', '15934645678', 'WangLN-1234@qq.com', '上海市浦东新区张江高科技园区祖冲之路888号18幢602室', '310110197104082345', 1, '2013-03-22 16:54:07', NULL, NULL);
INSERT INTO `smbms_0001` VALUES (2, 'GD_DEMO003', '陈伟强', '0', '28', '1', '17623456789', 'ChenWQ-1234@163.com', '广东省深圳市南山区科技园南区科苑路9号腾讯大厦A座12层', '440111198205093456', 1, '2017-04-22 16:52:20', NULL, NULL);
INSERT INTO `smbms_0001` VALUES (3, 'SC_DEMO004', '李梦琪', '1', '28', '2', '13578906835', 'LiMQ-1234@gmail.com', '四川省成都市高新区天府大道北段1700号环球中心W3栋1808室', '510112199306104567', 1, '2021-05-22 23:52:40', NULL, NULL);
INSERT INTO `smbms_0001` VALUES (4, 'HB_DEMO005', '马云飞', '0', '28', '1', '18765432190', 'MaYF-1234@outlook.com', '湖北省武汉市东湖新技术开发区光谷大道77号光谷软件园F4栋506室', '610113196407115678', 1, '2013-12-01 16:52:59', NULL, NULL);
INSERT INTO `smbms_0001` VALUES (5, 'JS_DEMO006', '赵雅芳', '3', '28', '2', '13987654320', 'ZhaoYF-1234@126.com', '江苏省南京市鼓楼区汉口路22号金陵饭店国际大厦15层', '710114197508126789', 1, '2014-03-22 13:52:07', NULL, NULL);
INSERT INTO `smbms_0001` VALUES (6, 'ZJ_DEMO007', '孙建华', '2', '28', '1', '15823055679', 'SunJH-1234@qq.com', '浙江省杭州市西湖区文一西路969号阿里巴巴西溪园区3号楼9层', '810115198609137890', 1, '2016-03-22 09:52:07', NULL, NULL);
INSERT INTO `smbms_0001` VALUES (7, 'SX_DEMO008', '周慧敏', '0', '28', '2', '17723456788', 'ZhouHM-1234@163.com', '陕西省西安市雁塔区电子城西路18号西安软件园A座10层', '910116199710148901', 1, '2013-03-22 16:52:07', NULL, NULL);
INSERT INTO `smbms_0001` VALUES (8, 'SX_DEMO009', 'Alice', '2', '28', '2', '17723456778', 'ZhouHM-1235@163.com', '陕西省西安市雁塔区电子城西路17号西安软件园A座10层', '910116199710148901', 1, '2013-03-22 16:52:07', NULL, NULL);
INSERT INTO `smbms_0001` VALUES (9, 'ZJ_DEMO010', 'Bob', '1', '28', '1', '15823055690', 'SunJH-1235@qq.com', '浙江省杭州市西湖区文一西路959号阿里巴巴西溪园区3号楼9层', '810115198609137890', 1, '2016-03-22 09:52:07', NULL, NULL);
INSERT INTO `smbms_0001` VALUES (10, 'SX_DEMO011', 'Alic', '2', '28', '2', '17723456678', 'ZhouHM-1236@163.com', '陕西省西安市雁塔区电子城西路17号西安软件园A座10层', '910116199710148901', 1, '2013-03-21 16:52:07', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
