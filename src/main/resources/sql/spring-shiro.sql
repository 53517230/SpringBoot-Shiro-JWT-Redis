/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 80023
Source Host           : localhost:3306
Source Database       : spring-shiro

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2021-04-28 15:47:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `parentId` bigint DEFAULT NULL COMMENT '父级Id',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限串',
  `level` tinyint(1) DEFAULT NULL COMMENT '是否为叶子节点 1 是,0 否',
  `status` int DEFAULT '1' COMMENT '1 正常, 0 禁用',
  `url` varchar(10240) DEFAULT NULL COMMENT '页面跳转路径',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', '首页', '0', 'sys:index', '0', '1', '/index/', 'menu');
INSERT INTO `t_permission` VALUES ('2', '用户管理', '1', 'sys:user:manag', '1', '1', '/index/user/', 'menu');
INSERT INTO `t_permission` VALUES ('3', '新增用户', '2', 'sys:user:add', '0', '1', '/user/insert/', 'button');
INSERT INTO `t_permission` VALUES ('4', '删除用户', '2', 'sys:user:del', '0', '1', '/user/delete/', 'button');
INSERT INTO `t_permission` VALUES ('5', '查询用户', '2', 'sys:user:query', '0', '1', '/user/query/', 'button');
INSERT INTO `t_permission` VALUES ('6', '角色管理', '1', 'sys:role:manag', '1', '1', '/index/role/', 'menu');
INSERT INTO `t_permission` VALUES ('7', '新增角色', '6', 'sys:role:add', '0', '1', '/role/insert/', 'button');
INSERT INTO `t_permission` VALUES ('8', '删除角色', '6', 'sys:role:del', '0', '1', '/role/delete/', 'button');
INSERT INTO `t_permission` VALUES ('9', '查询角色', '6', 'sys:role:query', '0', '1', '/role/query/', 'button');
INSERT INTO `t_permission` VALUES ('10', '权限管理', '1', 'sys:menu:manag', '1', '1', '/index/permission/', 'menu');
INSERT INTO `t_permission` VALUES ('11', '新增权限', '10', 'sys:menu:add', '0', '1', '/permission/insert/', 'button');
INSERT INTO `t_permission` VALUES ('12', '删除权限', '10', 'sys:menu:del', '0', '1', '/permission/delete/', 'button');
INSERT INTO `t_permission` VALUES ('13', '权限查询', '10', 'sys:menu:query', '0', '1', '/permission/query/', 'button');
INSERT INTO `t_permission` VALUES ('14', '用户信息更改', '2', 'sys:user:update', '0', '1', '/user/update/', 'button');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'Admin', '管理员');
INSERT INTO `t_role` VALUES ('2', 'User', '用户');
INSERT INTO `t_role` VALUES ('3', 'Teacher', '教师');
INSERT INTO `t_role` VALUES ('4', 'Art', '美工');
INSERT INTO `t_role` VALUES ('5', 'Student', '学生');
INSERT INTO `t_role` VALUES ('6', 'Developer', '开发工程师');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `roleId` bigint DEFAULT NULL,
  `permissionId` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1', '1');
INSERT INTO `t_role_permission` VALUES ('2', '1', '2');
INSERT INTO `t_role_permission` VALUES ('3', '1', '3');
INSERT INTO `t_role_permission` VALUES ('4', '1', '4');
INSERT INTO `t_role_permission` VALUES ('5', '1', '5');
INSERT INTO `t_role_permission` VALUES ('6', '1', '6');
INSERT INTO `t_role_permission` VALUES ('7', '1', '7');
INSERT INTO `t_role_permission` VALUES ('8', '1', '8');
INSERT INTO `t_role_permission` VALUES ('9', '1', '9');
INSERT INTO `t_role_permission` VALUES ('10', '1', '10');
INSERT INTO `t_role_permission` VALUES ('11', '1', '11');
INSERT INTO `t_role_permission` VALUES ('12', '1', '12');
INSERT INTO `t_role_permission` VALUES ('13', '1', '13');
INSERT INTO `t_role_permission` VALUES ('14', '1', '14');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(10240) DEFAULT NULL,
  `status` int DEFAULT '1' COMMENT '1 正常,0 禁用',
  `Img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'SuSan', 'a76eca0832b2a8f208a5a7f9e5e0f8e2', '74fe28e21c3ed6e3b2f66bfbbb8ed68d', '1', 'E:\\JavaProject\\uploadFile\\06a32a76d307438fb9e8f63d22c03d4a.jpg');
INSERT INTO `t_user` VALUES ('2', 'Evla', '4aaced065ffe69c09edd11de4d599ed1', 'b7671d2bde4c3883874f5174419f9c05', '1', 'E:\\JavaProject\\uploadFile\\88664ae0db5c4b329d5375f2c36d8d03.jpg');
INSERT INTO `t_user` VALUES ('3', '惊雷', 'ff213a2061bf6878cb2801f6da34c9dc', '82383a9f0b1537a67cf38e071bba9d15', '1', 'E:\\JavaProject\\uploadFile\\c40ef8a1499e44fbb7ecae5a58842086.jpg');
INSERT INTO `t_user` VALUES ('4', '惊蛰', '227188fe61ca98ff4f8da93bdd4caf60', 'd0e56917999f3775422374a97144fe1a', '1', 'E:\\JavaProject\\uploadFile\\9672268563934de585b9bc0f3d943a65.jpg');
INSERT INTO `t_user` VALUES ('5', '惊魂', '20db5eb95d5d31af169d0e2baa3a462d', '29859436226ea131dfc07b8d0c4ab330', '1', 'E:\\JavaProject\\uploadFile\\c2ca3acc00184fe882af3ffc91026f26.jpg');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `userId` bigint DEFAULT NULL,
  `roleId` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '2');
INSERT INTO `t_user_role` VALUES ('2', '2', '1');
INSERT INTO `t_user_role` VALUES ('3', '2', '2');
INSERT INTO `t_user_role` VALUES ('4', '5', '2');
