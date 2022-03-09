/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : permission_service

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 04/03/2022 13:49:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
# DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE  IF NOT EXISTS`sys_permission`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父权限ID',
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限字符串',
  `resource_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能的级别(\'menu\',\'button\')',
  `route` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `hide` tinyint(2) NULL DEFAULT 0 COMMENT '是否隐藏  1:是 0：否',
  `method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `system_mark` tinyint(2) NULL DEFAULT NULL COMMENT '系统标识(1->供应链内部端,2->供应链外部端)',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
# DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE  IF NOT EXISTS`sys_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `mark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `status` tinyint(2) NULL DEFAULT 1 COMMENT '状态(0->禁用,1->启用)',
  `system_mark` tinyint(2) NULL DEFAULT NULL COMMENT '系统标识(1->供应链内部端,2->供应链外部端)',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
# DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE  IF NOT EXISTS`sys_role_permission`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色编号',
  `permission_id` bigint(20) NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关联信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role_type
-- ----------------------------
# DROP TABLE IF EXISTS `sys_role_type`;
CREATE TABLE  IF NOT EXISTS`sys_role_type`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色类型名',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色前缀',
  `system_mark` tinyint(2) NULL DEFAULT NULL COMMENT '系统标识(1->供应链内部端,2->供应链外部端)',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色类型信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role_type_permission
-- ----------------------------
# DROP TABLE IF EXISTS `sys_role_type_permission`;
CREATE TABLE  IF NOT EXISTS`sys_role_type_permission`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `role_type_id` bigint(20) NOT NULL COMMENT '角色类型ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色类型-权限关联信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
# DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE  IF NOT EXISTS`sys_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `mail` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(2) NULL DEFAULT NULL COMMENT '性别  男：0，女：1',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `status` tinyint(2) NULL DEFAULT 1 COMMENT '账号状态, (0->已过期，1->启用，-1->禁用 )',
  `validity` bigint(20) NULL DEFAULT NULL COMMENT '有效期(-1:永久)',
  `source_type` tinyint(2) NULL DEFAULT 1 COMMENT '数据来源类型(1->系统添加, 2->ldap同步,默认是1)',
  `allocated` tinyint(2) NULL DEFAULT 0 COMMENT '是否分配, (1->已分配，0->未分配)',
  `system_mark` tinyint(2) NULL DEFAULT NULL COMMENT '系统标识(1->供应链内部端,2->供应链外部端)',
  `created_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
# DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE  IF NOT EXISTS`sys_user_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联信息' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
