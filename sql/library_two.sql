/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : library_two

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 05/02/2020 14:22:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_bookinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_bookinfo`;
CREATE TABLE `tb_bookinfo`  (
  `ISBN` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图书编号',
  `typeId` int(11) NULL DEFAULT NULL COMMENT '类别编号',
  `bookName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书名称',
  `writer` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `translator` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '译者',
  `publisher` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '出版日期',
  `price` double(6, 2) NULL DEFAULT NULL COMMENT '书籍价格',
  PRIMARY KEY (`ISBN`) USING BTREE,
  INDEX `FK_tb_bookInfo_typeId_2_tb_bookType_id`(`typeId`) USING BTREE,
  CONSTRAINT `FK_tb_bookInfo_typeId_2_tb_bookType_id` FOREIGN KEY (`typeId`) REFERENCES `tb_booktype` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_booktype
-- ----------------------------
DROP TABLE IF EXISTS `tb_booktype`;
CREATE TABLE `tb_booktype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书类别编号',
  `typeName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书类别名称',
  `days` int(11) NULL DEFAULT 0 COMMENT '可借天数',
  `Fk` double(3, 2) NULL DEFAULT NULL COMMENT '迟还一天的罚款',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_borrow
-- ----------------------------
DROP TABLE IF EXISTS `tb_borrow`;
CREATE TABLE `tb_borrow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '借阅编号',
  `bookiSBN` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍编号',
  `operatorId` int(11) NULL DEFAULT NULL COMMENT '操作员编号',
  `readerISBN` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '读者编号',
  `isback` int(11) NULL DEFAULT NULL COMMENT '是否归还',
  `borrowDate` datetime(0) NULL DEFAULT NULL COMMENT '借书日期',
  `backDate` datetime(0) NULL DEFAULT NULL COMMENT '应还日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tb_borrow_bookISBN_2_tb_bookInfo_ISBN`(`bookiSBN`) USING BTREE,
  INDEX `FK_tb_borrow_operatorId_2_tb_operator_id`(`operatorId`) USING BTREE,
  INDEX `FK_tb_borrow_readerISBN_2_tb_teafer_ISBN`(`readerISBN`) USING BTREE,
  CONSTRAINT `FK_tb_borrow_bookISBN_2_tb_bookInfo_ISBN` FOREIGN KEY (`bookiSBN`) REFERENCES `tb_bookinfo` (`ISBN`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_tb_borrow_operatorId_2_tb_operator_id` FOREIGN KEY (`operatorId`) REFERENCES `tb_operator` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_tb_borrow_readerISBN_2_tb_teafer_ISBN` FOREIGN KEY (`readerISBN`) REFERENCES `tb_teafer` (`ISBN`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_operator
-- ----------------------------
DROP TABLE IF EXISTS `tb_operator`;
CREATE TABLE `tb_operator`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作员编号',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `identityCard` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `workdate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作时间',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `admin` int(11) NULL DEFAULT NULL COMMENT '是否为管理员',
  `Password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `ISBN` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍编号',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '订购日期',
  `number` int(11) NULL DEFAULT NULL COMMENT '订购数量',
  `operator` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `cheakAndAccept` int(11) NULL DEFAULT NULL COMMENT '是否验收',
  `zk` double(4, 2) NULL DEFAULT NULL COMMENT '书籍折扣',
  PRIMARY KEY (`ISBN`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_teafer
-- ----------------------------
DROP TABLE IF EXISTS `tb_teafer`;
CREATE TABLE `tb_teafer`  (
  `ISBN` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '读者编号',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '读者姓名',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '读者性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '读者年龄',
  `identityCard` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '会员有效日期',
  `maxNum` int(11) NULL DEFAULT NULL COMMENT '最大借书量',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `keepMoney` double(6, 2) NULL DEFAULT NULL COMMENT '押金',
  `zj` int(11) NULL DEFAULT NULL COMMENT '证件类型',
  `zy` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `bztime` datetime(0) NULL DEFAULT NULL COMMENT '办证日期',
  PRIMARY KEY (`ISBN`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
