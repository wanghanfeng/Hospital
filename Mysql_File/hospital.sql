/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_Navicat
 Source Server Type    : MySQL
 Source Server Version : 50505
 Source Host           : localhost
 Source Database       : hospital

 Target Server Type    : MySQL
 Target Server Version : 50505
 File Encoding         : utf-8

 Date: 05/06/2017 21:46:45 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `conexamne`
-- ----------------------------
DROP TABLE IF EXISTS `conexamne`;
CREATE TABLE `conexamne` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `approve` varchar(255) DEFAULT NULL,
  `suggest` varchar(255) DEFAULT NULL,
  `situation` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `consumables`
-- ----------------------------
DROP TABLE IF EXISTS `consumables`;
CREATE TABLE `consumables` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `thename` varchar(249) DEFAULT NULL,
  `unit` varchar(249) DEFAULT NULL,
  `hos` varchar(249) DEFAULT NULL,
  `reason` varchar(249) DEFAULT NULL,
  `auditor` varchar(249) DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `reducePrice` double DEFAULT NULL,
  `days` int(10) DEFAULT NULL,
  `doctor` varchar(249) DEFAULT NULL,
  `thetime` date DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `consumables`
-- ----------------------------
BEGIN;
INSERT INTO `consumables` VALUES ('1', 'kkl', 'llk', 'iii', 'sdas', 'ssnn', '12.2', '6', '24', 'ls', '1998-02-02'), ('2', '张三', '五排', '哈医大', '出差', '李某', '99.2', '50.2', '2', '张', '2002-01-22'), ('3', 'jjjjk', 'kjk', 'jkn', 'hjhk', 'hjkh', '22', '10', '2', 'sss', '2017-05-01');
COMMIT;

-- ----------------------------
--  Table structure for `doctor`
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `identify` int(11) NOT NULL,
  `name` varchar(249) NOT NULL,
  `password` varchar(249) NOT NULL,
  PRIMARY KEY (`identify`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `doctor`
-- ----------------------------
BEGIN;
INSERT INTO `doctor` VALUES ('1', 'doc', 'doc'), ('2', '张三', 'zs'), ('3', '王医生', '123321');
COMMIT;

-- ----------------------------
--  Table structure for `drugs`
-- ----------------------------
DROP TABLE IF EXISTS `drugs`;
CREATE TABLE `drugs` (
  `drugcode` varchar(249) NOT NULL,
  `thetime` date DEFAULT NULL,
  `drug` varchar(249) DEFAULT NULL,
  `sort` varchar(249) DEFAULT NULL,
  `standard` varchar(249) DEFAULT NULL,
  `che` varchar(249) DEFAULT NULL,
  `packages` varchar(249) DEFAULT NULL,
  `place` varchar(249) DEFAULT NULL,
  `buysale` double DEFAULT NULL,
  `thesale` double DEFAULT NULL,
  `lose` date DEFAULT NULL,
  `company` varchar(249) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `user` varchar(249) DEFAULT NULL,
  PRIMARY KEY (`drugcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `drugs`
-- ----------------------------
BEGIN;
INSERT INTO `drugs` VALUES ('cu', '2003-02-03', '醋', '食用', 'g', '忘了', 'L', '忘了', '20', '10', '4000-02-03', '好像是忘了', '140', '二'), ('yan', '2002-02-02', 'yan', '食用', 'g', '氯化钠', '包', '大海', '99', '199', '3000-02-02', '大海', '200', 'u');
COMMIT;

-- ----------------------------
--  Table structure for `finance`
-- ----------------------------
DROP TABLE IF EXISTS `finance`;
CREATE TABLE `finance` (
  `identify` int(11) NOT NULL,
  `name` varchar(249) NOT NULL,
  `password` varchar(249) NOT NULL,
  PRIMARY KEY (`identify`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `finance`
-- ----------------------------
BEGIN;
INSERT INTO `finance` VALUES ('1', 'doc', 'docc'), ('2', 'wei', 'wei');
COMMIT;

-- ----------------------------
--  Table structure for `health`
-- ----------------------------
DROP TABLE IF EXISTS `health`;
CREATE TABLE `health` (
  `identify` int(11) NOT NULL,
  `name` varchar(249) NOT NULL,
  `password` varchar(249) NOT NULL,
  PRIMARY KEY (`identify`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `health`
-- ----------------------------
BEGIN;
INSERT INTO `health` VALUES ('1', 'doc', 'doc'), ('2001', 'wei', 'wei');
COMMIT;

-- ----------------------------
--  Table structure for `healthscreen`
-- ----------------------------
DROP TABLE IF EXISTS `healthscreen`;
CREATE TABLE `healthscreen` (
  `hs_id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(249) DEFAULT NULL,
  `epidemic` varchar(249) DEFAULT NULL,
  `die` int(11) DEFAULT NULL,
  `type` varchar(249) DEFAULT NULL,
  `name` varchar(249) DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`hs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `healthscreen`
-- ----------------------------
BEGIN;
INSERT INTO `healthscreen` VALUES ('1', 'oo', 'i', '0', 'wu', 'i', '1998-08-08'), ('2', 'ss', 'sase', '0', '无', '李', '1998-08-08'), ('3', 'sj', 'j', '1', 'j', 'j', '1992-02-02');
COMMIT;

-- ----------------------------
--  Table structure for `importantperson`
-- ----------------------------
DROP TABLE IF EXISTS `importantperson`;
CREATE TABLE `importantperson` (
  `i_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(249) DEFAULT NULL,
  `sex` varchar(15) DEFAULT NULL,
  `company` varchar(249) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `temperature` double DEFAULT NULL,
  `situation` varchar(249) DEFAULT NULL,
  `drug` varchar(249) DEFAULT NULL,
  `drugAmount` varchar(249) DEFAULT NULL,
  `isolationTime` varchar(249) DEFAULT NULL,
  `doctor` varchar(249) DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `importantperson`
-- ----------------------------
BEGIN;
INSERT INTO `importantperson` VALUES ('1', 'ss', '男', 'asd', '11', '22', 'asd', 'asd', 'asd', 'sad', 'sad', '1998-09-09');
COMMIT;

-- ----------------------------
--  Table structure for `nurse`
-- ----------------------------
DROP TABLE IF EXISTS `nurse`;
CREATE TABLE `nurse` (
  `identify` int(11) NOT NULL,
  `name` varchar(249) NOT NULL,
  `password` varchar(249) NOT NULL,
  PRIMARY KEY (`identify`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `nurse`
-- ----------------------------
BEGIN;
INSERT INTO `nurse` VALUES ('1', 'doc', 'nur'), ('2', 'wei', 'wei');
COMMIT;

-- ----------------------------
--  Table structure for `nursingrecords`
-- ----------------------------
DROP TABLE IF EXISTS `nursingrecords`;
CREATE TABLE `nursingrecords` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient` varchar(249) DEFAULT NULL,
  `drug` varchar(249) DEFAULT NULL,
  `thecode` varchar(249) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `ins` varchar(20) DEFAULT NULL,
  `thetime` date DEFAULT NULL,
  `nur` varchar(249) DEFAULT NULL,
  `note` varchar(249) DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `nursingrecords`
-- ----------------------------
BEGIN;
INSERT INTO `nursingrecords` VALUES ('2', 'jkjj', '撒大声地', 'jk', '男', '22', 'das', '1998-02-03', '按时', 'fas'), ('3', '张三', '阿司匹林', 'ssad', '男', '22', '五排', '2009-01-29', '李', '无'), ('4', 'sss', 'dasdsa', 'ss', '男', '22', 'das', '2017-05-01', 'das', 'dsa');
COMMIT;

-- ----------------------------
--  Table structure for `patientinformation`
-- ----------------------------
DROP TABLE IF EXISTS `patientinformation`;
CREATE TABLE `patientinformation` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(249) DEFAULT NULL,
  `sex` varchar(15) DEFAULT NULL,
  `institutions` varchar(249) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `flag` varchar(15) DEFAULT NULL,
  `situation` varchar(12345) DEFAULT NULL,
  `doctor` varchar(249) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `detail_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `patientinformation`
-- ----------------------------
BEGIN;
INSERT INTO `patientinformation` VALUES ('1', '张三', '男', '五排', '22', '初诊', '轻微咳嗽', '张医生', '2017-04-13', null), ('3', 'w', '男', 'w', '11', '初诊', 'www', 'ww', '1998-01-01', null), ('8', 'kk', '男', 'www', '11', '初诊', 'oooo', 'lll', '1998-02-03', null), ('9', 'lll', '男', 'llsad2', '233', '初诊', 'sa', 'ws', '2017-05-01', null), ('10', '王尼玛', '男', '优酷紫萍倒', '38', '初诊', '白痴症状', '2', '2017-05-05', null), ('11', 'whd', '男', 'wead', '12', '初诊', '', '0', '2017-05-06', null), ('12', '11', '男', '11', '11', '初诊', '11', '0', '2017-05-06', null), ('13', 'whdd', '男', 'qdas', '12', '初诊', '3ddasd', '0', '2017-05-06', null), ('14', 'dd', '男', '12', '123', '初诊', 'dasdas', '0', '2017-05-06', null), ('15', 'qwe', '男', '2e', '12', '初诊', 'da', '0', '2017-05-06', 'http://123.206.13.45/dashboard/img/whf1.jpg'), ('16', 'www', '男', 'asd', '23', '初诊', 'ddasd', '0', '2017-05-06', 'http://123.206.13.45/dashboard/img/whf1.jpg'), ('17', 'sssa', '男', '123', '21', '初诊', 'dasd', '0', '2017-05-06', 'http://123.206.13.45/dashboard/img/whf_shuai.jpg');
COMMIT;

-- ----------------------------
--  Table structure for `prescription`
-- ----------------------------
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription` (
  `pre_id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor` varchar(249) DEFAULT NULL,
  `name` varchar(249) DEFAULT NULL,
  `institutions` varchar(249) DEFAULT NULL,
  `drug` varchar(249) DEFAULT NULL,
  `code` varchar(249) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `dosage` varchar(249) DEFAULT NULL,
  `hz` varchar(249) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `note` varchar(249) DEFAULT NULL,
  `chronicDisease` varchar(24) DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`pre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `prescription`
-- ----------------------------
BEGIN;
INSERT INTO `prescription` VALUES ('3', '张医生', '张三', '五排', '阿司匹林', 'ss2', '12', '一日三次', '一日三次', '33', null, '是', '1200-01-02'), ('4', '123', '张三', '12321', 'yan', '1', '12312321', '131', '123', '213', '123', '是', '2017-05-04');
COMMIT;

-- ----------------------------
--  Table structure for `storagekeeper`
-- ----------------------------
DROP TABLE IF EXISTS `storagekeeper`;
CREATE TABLE `storagekeeper` (
  `identify` int(11) NOT NULL,
  `name` varchar(249) NOT NULL,
  `password` varchar(249) NOT NULL,
  PRIMARY KEY (`identify`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `storagekeeper`
-- ----------------------------
BEGIN;
INSERT INTO `storagekeeper` VALUES ('1', 'doc', 'doc'), ('2', 'wei', 'wei');
COMMIT;

-- ----------------------------
--  Table structure for `stuff`
-- ----------------------------
DROP TABLE IF EXISTS `stuff`;
CREATE TABLE `stuff` (
  `stuffcode` varchar(249) NOT NULL,
  `thetime` date DEFAULT NULL,
  `stuff` varchar(249) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `standard` varchar(249) DEFAULT NULL,
  `company` varchar(249) DEFAULT NULL,
  `picode` varchar(249) DEFAULT NULL,
  `place` varchar(249) DEFAULT NULL,
  `buysale` double DEFAULT NULL,
  `thesale` double DEFAULT NULL,
  `lose` date DEFAULT NULL,
  `somecode` varchar(249) DEFAULT NULL,
  `user` varchar(249) DEFAULT NULL,
  PRIMARY KEY (`stuffcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stuff`
-- ----------------------------
BEGIN;
INSERT INTO `stuff` VALUES ('mutou', '1998-01-01', '木头', '100', 'kg', '根', null, '大兴安岭', '3000', '3500', '9998-01-01', '1', '我'), ('shitou', '2009-09-09', '石头', '50', '块', 'kg', null, '大地', '20', '24', '9999-09-09', '2', '它');
COMMIT;

-- ----------------------------
--  Table structure for `systemcon`
-- ----------------------------
DROP TABLE IF EXISTS `systemcon`;
CREATE TABLE `systemcon` (
  `identify` int(11) NOT NULL,
  `name` varchar(249) NOT NULL,
  `password` varchar(249) NOT NULL,
  PRIMARY KEY (`identify`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `systemcon`
-- ----------------------------
BEGIN;
INSERT INTO `systemcon` VALUES ('1', 'manager', 'manager'), ('2001', 'wei', 'wei');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
