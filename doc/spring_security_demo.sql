/*
 Navicat Premium Data Transfer

 Source Server         : MySQL-02
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 192.168.43.2:3306
 Source Schema         : spring_security_demo

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 26/05/2023 18:41:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '权限(菜单)父id',
  `permissions_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名称(菜单名称)',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由地址',
  `permissions_key` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识符',
  `is_delete` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，0否、1是',
  `sort` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `idx_is_delete`(`is_delete` ASC) USING BTREE,
  INDEX `idx_sort`(`sort` ASC) USING BTREE,
  INDEX `idx_update_time`(`update_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permissions
-- ----------------------------
INSERT INTO `sys_permissions` VALUES (1, 0, '用户管理', NULL, NULL, 0, 0, '2023-05-25 14:33:34', '2023-05-25 14:33:34');
INSERT INTO `sys_permissions` VALUES (2, 0, '角色管理', NULL, NULL, 0, 0, '2023-05-25 14:33:42', '2023-05-25 14:33:42');
INSERT INTO `sys_permissions` VALUES (3, 0, '权限管理', NULL, NULL, 0, 0, '2023-05-25 14:33:51', '2023-05-25 14:33:51');
INSERT INTO `sys_permissions` VALUES (4, 0, '数据管理', NULL, NULL, 0, 0, '2023-05-25 14:33:58', '2023-05-25 14:33:58');
INSERT INTO `sys_permissions` VALUES (5, 1, '注册系统用户', '/register', 'SYS_USER:ADD', 0, 0, '2023-05-25 11:05:22', '2023-05-25 14:37:50');
INSERT INTO `sys_permissions` VALUES (6, 1, '删除系统用户', '/user/*', 'SYS_USER:DELETE', 0, 0, '2023-05-25 12:06:21', '2023-05-26 06:21:08');
INSERT INTO `sys_permissions` VALUES (7, 1, '修改系统用户角色', '/user/give-role', 'SYS_USER:UPDATE', 0, 0, '2023-05-25 13:35:32', '2023-05-25 14:37:53');
INSERT INTO `sys_permissions` VALUES (8, 1, '系统用户列表', '/user/list', 'SYS_USER:GET', 0, 0, '2023-05-25 13:33:56', '2023-05-25 14:37:54');
INSERT INTO `sys_permissions` VALUES (9, 2, '新增角色', '/role', 'SYS_ROLE:ADD', 0, 0, '2023-05-25 12:09:01', '2023-05-25 14:41:34');
INSERT INTO `sys_permissions` VALUES (10, 2, '删除角色', '/role/*', 'SYS_ROLE:DELETE', 0, 0, '2023-05-25 12:09:56', '2023-05-25 14:41:35');
INSERT INTO `sys_permissions` VALUES (11, 2, '修改角色', '/role/*', 'SYS_ROLE:UPDATE', 0, 0, '2023-05-25 14:30:15', '2023-05-25 14:41:36');
INSERT INTO `sys_permissions` VALUES (12, 2, '修改角色权限', '/role/give-perm', 'SYS_ROLE:UPDATE', 0, 0, '2023-05-25 12:09:28', '2023-05-25 14:41:37');
INSERT INTO `sys_permissions` VALUES (13, 2, '角色列表', '/role/list', 'SYS_ROLE:GET', 0, 0, '2023-05-25 12:10:38', '2023-05-25 14:41:39');
INSERT INTO `sys_permissions` VALUES (14, 3, '新增权限', '/perm', 'SYS_PERM:ADD', 0, 0, '2023-05-25 14:26:31', '2023-05-25 14:43:06');
INSERT INTO `sys_permissions` VALUES (15, 3, '删除权限', '/perm/*', 'SYS_PERM:DELETE', 0, 0, '2023-05-25 14:27:30', '2023-05-25 14:43:08');
INSERT INTO `sys_permissions` VALUES (16, 3, '修改权限', '/perm/*', 'SYS_PERM:UPDATE', 0, 0, '2023-05-25 14:27:01', '2023-05-25 14:43:09');
INSERT INTO `sys_permissions` VALUES (17, 3, '权限信息', '/perm', 'SYS_PERM:GET', 0, 0, '2023-05-25 14:28:59', '2023-05-25 14:43:10');
INSERT INTO `sys_permissions` VALUES (18, 3, '权限列表', '/perm/list', 'SYS_PERM:GET', 0, 0, '2023-05-25 14:28:09', '2023-05-25 14:43:11');
INSERT INTO `sys_permissions` VALUES (19, 4, '新增数据', '/data', 'DATA:ADD', 0, 0, '2023-05-24 20:19:29', '2023-05-25 14:44:07');
INSERT INTO `sys_permissions` VALUES (20, 4, '删除数据', '/data/*', 'DATA:DELETE', 0, 0, '2023-05-24 20:21:16', '2023-05-25 14:44:08');
INSERT INTO `sys_permissions` VALUES (21, 4, '修改数据', '/data/*', 'DATA:UPDATE', 0, 0, '2023-05-24 20:20:40', '2023-05-25 18:52:00');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色标识符',
  `is_delete` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，0否、1是',
  `sort` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_is_delete`(`is_delete` ASC) USING BTREE,
  INDEX `idx_sort`(`sort` ASC) USING BTREE,
  INDEX `idx_update_time`(`update_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'root', 0, 0, '2023-05-24 08:30:01', '2023-05-24 08:30:01');
INSERT INTO `sys_role` VALUES (2, '管理员', 'admin', 0, 0, '2023-05-24 08:30:10', '2023-05-24 08:30:10');
INSERT INTO `sys_role` VALUES (3, '系统用户', 'user', 0, 0, '2023-05-24 08:30:31', '2023-05-24 08:30:31');

-- ----------------------------
-- Table structure for sys_role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permissions`;
CREATE TABLE `sys_role_permissions`  (
  `role_id` int UNSIGNED NOT NULL COMMENT '角色id',
  `permissions_id` int UNSIGNED NOT NULL COMMENT '权限(菜单)id',
  PRIMARY KEY (`role_id`, `permissions_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限(菜单)关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permissions
-- ----------------------------
INSERT INTO `sys_role_permissions` VALUES (1, 5);
INSERT INTO `sys_role_permissions` VALUES (1, 6);
INSERT INTO `sys_role_permissions` VALUES (1, 7);
INSERT INTO `sys_role_permissions` VALUES (1, 8);
INSERT INTO `sys_role_permissions` VALUES (1, 9);
INSERT INTO `sys_role_permissions` VALUES (1, 10);
INSERT INTO `sys_role_permissions` VALUES (1, 11);
INSERT INTO `sys_role_permissions` VALUES (1, 12);
INSERT INTO `sys_role_permissions` VALUES (1, 13);
INSERT INTO `sys_role_permissions` VALUES (1, 14);
INSERT INTO `sys_role_permissions` VALUES (1, 15);
INSERT INTO `sys_role_permissions` VALUES (1, 16);
INSERT INTO `sys_role_permissions` VALUES (1, 17);
INSERT INTO `sys_role_permissions` VALUES (1, 18);
INSERT INTO `sys_role_permissions` VALUES (1, 19);
INSERT INTO `sys_role_permissions` VALUES (1, 20);
INSERT INTO `sys_role_permissions` VALUES (1, 21);
INSERT INTO `sys_role_permissions` VALUES (2, 19);
INSERT INTO `sys_role_permissions` VALUES (2, 21);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `is_delete` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，0否、1是',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username` ASC) USING BTREE,
  INDEX `idx_is_delete`(`is_delete` ASC) USING BTREE,
  INDEX `idx_update_time`(`update_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'root', '$2a$10$wo7XfOcQXB3F/XXSNbXY/u5.dxlwZdnqIEA6ivXzTUyMUQSjEO9IG', '系统超级管理员', 0, '2023-05-24 08:31:16', '2023-05-24 08:33:35');
INSERT INTO `sys_user` VALUES (2, 'admin', '$2a$10$FwvJYon89K4H1dMxclzZWuSERgiN4cSQnSpY5R.FLexqEWfb2Di/i', '管理员', 0, '2023-05-25 10:47:49', '2023-05-25 11:02:24');
INSERT INTO `sys_user` VALUES (3, 'user', '$2a$10$8heh6bPGK6KLgiAf/6bsLutCG0M6lx.nHf6ntfZqVIWVeCGzbOYaS', '用户:aded7d7041fa46af9be63099b90c600e', 0, '2023-05-25 11:01:55', '2023-05-25 11:01:55');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int UNSIGNED NOT NULL COMMENT '用户id',
  `role_id` int UNSIGNED NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3);

-- ----------------------------
-- Table structure for tb_data
-- ----------------------------
DROP TABLE IF EXISTS `tb_data`;
CREATE TABLE `tb_data`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据信息',
  `is_delete` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，0否、1是',
  `sort` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_is_delete`(`is_delete` ASC) USING BTREE,
  INDEX `idx_sort`(`sort` ASC) USING BTREE,
  INDEX `idx_update_time`(`update_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '测试数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_data
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
