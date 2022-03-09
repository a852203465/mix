/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : faceverify2.0

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 15/08/2019 10:22:07
*/

SET NAMES utf8mb4;
set autocommit = 0;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xdc_t_dictionary
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_dictionary`;
CREATE TABLE IF NOT EXISTS `xdc_t_dictionary`  (
  `id` bigint(20) NOT NULL,
  `dictionary_chinese` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dictionary_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `meaning` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `numerical` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_dictionary
-- ----------------------------
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (1, '删除状态', 'DeleteStatus', '未删除', 0);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (2, '删除状态', 'DeleteStatus', '已删除', 1);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (3, '账号角色状态', 'AccountRoleStatus', '已过期', 0);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (4, '账号角色状态', 'AccountRoleStatus', '启用', 1);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (5, '账号角色状态', 'AccountRoleStatus', '禁用', -1);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (6, '性别', 'Sex', '男', 0);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (7, '性别', 'Sex', '女', 1);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (8, '账号分配', 'AccountAssignment', '未分配', 0);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (9, '账号分配', 'AccountAssignment', '已分配', 1);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (10, '有效期', 'Validity', '永久', -1);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (11, '用户/角色级别', 'UserRoleLevel', '系统管理员', -1);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (12, '用户/角色级别', 'UserRoleLevel', '组织用户', 1);


























commit;
set autocommit = 1;
SET FOREIGN_KEY_CHECKS = 1;
