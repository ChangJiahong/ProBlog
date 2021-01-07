/*
 Navicat Premium Data Transfer

 Source Server         : pythongMySql
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           :
 Source Schema         : note_blog

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 06/01/2021 17:17:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
                            `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章标题',
                            `alias` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章别名',
                            `author` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
                            `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容',
                            `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述信息',
                            `hits` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '单击次数',
                            `modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '最近修改时间',
                            `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                            `status` enum('publish','draft') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'publish' COMMENT '文章状态',
                            `front_cover_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '封面大图',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE INDEX `alias`(`alias`) USING BTREE,
                            INDEX `author`(`author`) USING BTREE,
                            CONSTRAINT `article_ibfk_1` FOREIGN KEY (`author`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_type
-- ----------------------------
DROP TABLE IF EXISTS `article_type`;
CREATE TABLE `article_type`  (
                                 `aid` int(11) NOT NULL COMMENT '文章id',
                                 `tid` int(11) NOT NULL COMMENT '标签id',
                                 `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                 PRIMARY KEY (`aid`, `tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for file_rev
-- ----------------------------
DROP TABLE IF EXISTS `file_rev`;
CREATE TABLE `file_rev`  (
                             `file_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                             `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                             `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                             `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                             `author` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                             `protective` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态、private\\public',
                             `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                             PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes`  (
                          `article_id` int(11) NOT NULL,
                          `likes` int(255) NULL DEFAULT NULL,
                          PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
                         `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                         `rname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
                         `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                         PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                         `name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名字',
                         `type` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型',
                         `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                         `username` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
                         `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱账号',
                         `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码（密文存储）最大长度36',
                         `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像地址',
                         `sex` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '性别（1男/0女）',
                         `age` int(4) NOT NULL COMMENT '年龄',
                         `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                         PRIMARY KEY (`uid`) USING BTREE,
                         UNIQUE INDEX `username`(`username`) USING BTREE,
                         UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_likes_article
-- ----------------------------
DROP TABLE IF EXISTS `user_likes_article`;
CREATE TABLE `user_likes_article`  (
                                       `username` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                       `article_id` int(11) NOT NULL,
                                       PRIMARY KEY (`username`, `article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
                              `r_id` int(11) NOT NULL COMMENT '角色id',
                              `u_id` int(11) NOT NULL COMMENT '用户id',
                              `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                              PRIMARY KEY (`r_id`, `u_id`) USING BTREE,
                              INDEX `u_id`(`u_id`) USING BTREE,
                              CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`r_id`) REFERENCES `role` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
