/*
 Navicat Premium Dump SQL

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80017 (8.0.17)
 Source Host           : localhost:3306
 Source Schema         : chaos_dev

 Target Server Type    : MySQL
 Target Server Version : 80017 (8.0.17)
 File Encoding         : 65001

 Date: 14/12/2025 19:21:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_datasource
-- ----------------------------
DROP TABLE IF EXISTS `gen_datasource`;
CREATE TABLE `gen_datasource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '别名',
  `conf_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置类型',
  `db_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库类型',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'jdbcurl',
  `host` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主机',
  `port` int(11) NULL DEFAULT NULL COMMENT '端口',
  `db_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库名称',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `instance` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实例',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据源表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_datasource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sort_order` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` int(11) NULL DEFAULT 0,
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1998989225195642884 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '总部', 1, '2022-07-29 10:11:49', '2025-12-11 08:35:42', 1, 1, 0, 1);
INSERT INTO `sys_dept` VALUES (2, 1, '研发部', 5, '2022-07-29 10:12:16', NULL, 1, NULL, 0, 1);
INSERT INTO `sys_dept` VALUES (3, 1, '运营部', 1, '2023-04-03 20:27:38', '2025-12-11 08:28:09', 1, 1, 0, 1);
INSERT INTO `sys_dept` VALUES (4, 3, '运营1部', 1, '2023-04-03 20:27:51', '2025-12-11 08:28:10', 1, 1, 0, 1);
INSERT INTO `sys_dept` VALUES (1998989225195642882, 2, 'java研发部', 1, NULL, '2025-12-11 13:33:28', NULL, NULL, 0, 1);
INSERT INTO `sys_dept` VALUES (1998989225195642883, 2, 'python研发部', 3, NULL, '2025-12-11 13:33:29', NULL, NULL, 0, 1);

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation`  (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`, `descendant`) USING BTREE,
  INDEX `idx1`(`ancestor` ASC) USING BTREE,
  INDEX `idx2`(`descendant` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
INSERT INTO `sys_dept_relation` VALUES (1, 1);
INSERT INTO `sys_dept_relation` VALUES (1, 2);
INSERT INTO `sys_dept_relation` VALUES (1, 3);
INSERT INTO `sys_dept_relation` VALUES (1, 4);
INSERT INTO `sys_dept_relation` VALUES (2, 2);
INSERT INTO `sys_dept_relation` VALUES (3, 3);
INSERT INTO `sys_dept_relation` VALUES (3, 4);
INSERT INTO `sys_dept_relation` VALUES (4, 4);
INSERT INTO `sys_dept_relation` VALUES (6, 6);
INSERT INTO `sys_dept_relation` VALUES (7, 7);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `system_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否系统类型 1：系统类型 0：业务类型',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` int(11) NULL DEFAULT 0,
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`dict_id`) USING BTREE,
  INDEX `sys_dict_del_flag`(`del_flag` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'dict_type', '字典类型', '1', '字典类型不能删除', '2022-04-12 23:57:42', '2025-12-11 15:08:23', '1', '1', 0, 1);
INSERT INTO `sys_dict` VALUES (2, 'gen_style_type', '代码生成风格', '1', '0-Avue 1-element', '2022-04-28 16:49:35', '2025-12-11 15:08:32', '1', '1', 0, 1);
INSERT INTO `sys_dict` VALUES (3, 'bool_type', '是否判断 是 否', '1', '1-是  0-否', '2022-06-07 10:02:08', '2025-12-11 15:08:37', '1', '1', 0, 1);
INSERT INTO `sys_dict` VALUES (4, 'status_type', '状态  正常 冻结', '1', '1-正常  0-冻结', '2022-06-07 10:07:10', '2025-12-11 15:08:41', '1', '1', 0, 1);
INSERT INTO `sys_dict` VALUES (5, 'realty_area_type', '房产地区类型', '0', NULL, '2023-02-21 16:29:34', '2025-12-11 15:08:44', '1', '1', 0, 1);
INSERT INTO `sys_dict` VALUES (6, 'survey_platform_type', '渠道类型', '0', '222', '2023-03-12 21:02:40', '2025-12-14 19:05:25', '1', 'admin', 1, 1);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `dict_id` bigint(20) NOT NULL COMMENT '字典ID',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典类型',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标识',
  `value` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '值',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `sort_order` int(11) NULL DEFAULT 0 COMMENT '排序（升序）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` int(11) NOT NULL DEFAULT 0,
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `sys_dict_value`(`value` ASC) USING BTREE,
  INDEX `sys_dict_label`(`label` ASC) USING BTREE,
  INDEX `sys_dict_del_flag`(`del_flag` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1999071207485325314 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典项表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1, 1, 'dict_type', '系统类', '1', '系统类字典', NULL, 0, '2022-04-12 23:58:09', '2025-12-11 14:39:34', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (2, 1, 'dict_type', '业务类', '0', '业务类字典', '', 1, '2022-04-12 23:58:44', '2025-12-11 14:39:35', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (3, 2, 'gen_style_type', 'avue', '0', 'avue风格', NULL, 1, '2022-04-28 16:51:08', '2025-12-11 14:39:35', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (4, 2, 'gen_style_type', 'element', '1', 'element-ui', NULL, 2, '2022-04-28 16:51:39', '2025-12-11 14:39:36', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (5, 3, 'bool_type', '是', '1', '是', NULL, 0, '2022-06-07 10:02:39', '2025-12-11 14:39:39', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (6, 3, 'bool_type', '否', '0', '否', NULL, 1, '2022-06-07 10:03:05', '2025-12-11 14:39:39', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (7, 4, 'status_type', '正常', '1', '正常', NULL, 0, '2022-06-07 10:08:07', '2025-12-11 14:39:43', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (8, 4, 'status_type', '冻结', '0', '冻结', NULL, 1, '2022-06-07 10:08:29', '2025-12-11 14:39:43', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (9, 5, 'realty_area_type', '兰山区', '1', '兰山区', NULL, 1, '2023-02-21 16:29:57', '2025-12-11 14:39:44', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (10, 5, 'realty_area_type', '河东区', '2', '河东区', NULL, 2, '2023-02-21 16:30:09', '2025-12-11 14:39:45', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (11, 5, 'realty_area_type', '罗庄区', '3', '罗庄区', NULL, 3, '2023-02-21 16:30:23', '2025-12-11 14:39:45', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (12, 5, 'realty_area_type', '经济开发区', '4', '经济开发区', NULL, 4, '2023-02-21 16:30:39', '2025-12-11 14:39:46', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (13, 5, 'realty_area_type', '高新区', '5', '高新区', NULL, 5, '2023-02-21 16:30:49', '2025-12-11 14:39:47', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (14, 5, 'realty_area_type', '南坊新区', '6', '南坊新区', NULL, 6, '2023-02-21 16:31:03', '2025-12-11 14:39:47', '1', '1', 0, 1);
INSERT INTO `sys_dict_item` VALUES (15, 6, 'survey_platform_type', 'ZCA', '1', 'ZCA', '', 1, '2023-03-12 21:03:35', NULL, '1', NULL, 1, 1);
INSERT INTO `sys_dict_item` VALUES (1999071207485325313, 6, 'survey_platform_type', 'zca', '1', 'zca', '111222', 1, '2025-12-14 19:05:25', '2025-12-14 19:05:25', 'admin', 'admin', 1, 1);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `group_id` bigint(20) NULL DEFAULT NULL COMMENT '文件组',
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `bucket_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件存储桶名称',
  `dir` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件夹名称',
  `original` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原始文件名',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ' ' COMMENT '创建人',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ' ' COMMENT '修改人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1805550282048704513, NULL, 'c6c1e77882244cd5969ee60366f6f286.jpg', 'maojiang-shop-1300160460', NULL, '1.jpg', NULL, 17277, 'admin', 'admin', '2024-06-25 18:35:07', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1805550341398106113, NULL, 'fdfd1d427f1045e491279f9a9beecc9d.jpg', 'maojiang-shop-1300160460', NULL, '2.jpg', NULL, 507344, 'admin', 'admin', '2024-06-25 18:35:22', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1805550844551008258, NULL, '730a6dc6a54645f68e2dcf5ce5caf5c9.jpg', 'maojiang-shop-1300160460', NULL, '1.jpg', NULL, 17277, 'admin', 'admin', '2024-06-25 18:37:22', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1805550845461172226, NULL, '6aea9d1a99d147f7b6efaca33d9d6281.jpg', 'maojiang-shop-1300160460', NULL, '2.jpg', NULL, 507344, 'admin', 'admin', '2024-06-25 18:37:22', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1805754703605067778, NULL, '1ba013d7954a44c9b6840a50b53fc13e.jpg', 'maojiang-shop-1300160460', NULL, '1.jpg', NULL, 17277, 'admin', 'admin', '2024-06-26 08:07:25', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1805754736555520002, NULL, 'e4dfeaee2e33471a9a69e5bac979abcf.jpg', 'maojiang-shop-1300160460', NULL, '2.jpg', NULL, 507344, 'admin', 'admin', '2024-06-26 08:07:33', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806236412985405442, NULL, 'c2acd03bf41846c98d62f34372f60c4a.jpg', 'maojiang-shop-1300160460', NULL, '5.jpg', '10', 25084, 'admin', 'admin', '2024-06-27 16:01:34', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806236605617205249, NULL, '4d94e4b363a9467fa60ed7604dbc6695.jpg', 'maojiang-shop-1300160460', NULL, '1.jpg', '10', 17277, 'admin', 'admin', '2024-06-27 16:02:20', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806483089537343490, NULL, '2e43d89f9a04482191db997931689390.jpg', 'maojiang-shop-1300160460', NULL, 'banner3.jpg', NULL, 20138, 'admin', 'admin', '2024-06-28 08:21:46', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806483089537343491, NULL, '14f4fdcb7f124a09a0406905628d1db7.jpg', 'maojiang-shop-1300160460', NULL, 'banner1.jpg', NULL, 11784, 'admin', 'admin', '2024-06-28 08:21:46', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806483089537343492, NULL, 'eaa8f4df89fa460f8ae665c4e63c788a.jpg', 'maojiang-shop-1300160460', NULL, 'banner4.jpg', NULL, 5952, 'admin', 'admin', '2024-06-28 08:21:46', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806483089537343493, NULL, 'b000ce235c7e49abafce33609ff95eda.jpg', 'maojiang-shop-1300160460', NULL, 'banner2.jpg', NULL, 17462, 'admin', 'admin', '2024-06-28 08:21:46', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806483174119677954, NULL, '5f05a8349f5e4817b4574aa1b69319d9.jpg', 'maojiang-shop-1300160460', NULL, 'banner2.jpg', NULL, 17462, 'admin', 'admin', '2024-06-28 08:22:06', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806483195850366978, NULL, 'a87f92f51b644b5392cb250b79420a6a.jpg', 'maojiang-shop-1300160460', NULL, 'banner3.jpg', NULL, 20138, 'admin', 'admin', '2024-06-28 08:22:11', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806483228062621697, NULL, '991d5f44b6f043f4a5211b20f968d1a3.jpg', 'maojiang-shop-1300160460', NULL, 'banner4.jpg', NULL, 5952, 'admin', 'admin', '2024-06-28 08:22:19', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806487483205001217, NULL, 'a93b3bbf19f4427b963a1a2036015e18.jpg', 'maojiang-shop-1300160460', NULL, 'banner1.jpg', NULL, 68298, 'admin', 'admin', '2024-06-28 08:39:14', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806487498245775362, NULL, '735ac5c2b0ac426681d5ded17c81370f.jpg', 'maojiang-shop-1300160460', NULL, 'banner2.jpg', NULL, 85413, 'admin', 'admin', '2024-06-28 08:39:17', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806487510170181634, NULL, '1da81dba724f46d39f3920bfad665351.jpg', 'maojiang-shop-1300160460', NULL, 'banner3.jpg', NULL, 95210, 'admin', 'admin', '2024-06-28 08:39:20', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806487522878922754, NULL, '12796abfd2114a2f9659b3b09c01fff3.jpg', 'maojiang-shop-1300160460', NULL, 'banner4.jpg', NULL, 39177, 'admin', 'admin', '2024-06-28 08:39:23', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806487553753194497, NULL, 'fbee0f6178cf4bcd83e6a828cab8f63e.png', 'maojiang-shop-1300160460', NULL, 'banner5.png', NULL, 140893, 'admin', 'admin', '2024-06-28 08:39:30', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806492434417295361, NULL, 'a0a810638ad04d62b61cde336c696792.jpg', 'maojiang-shop-1300160460', NULL, 'new-logo1.jpg', NULL, 41078, 'admin', 'admin', '2024-06-28 08:58:54', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806493073956380673, NULL, '8699dec8b0da4745b4f77ae490c84f63.jpg', 'maojiang-shop-1300160460', NULL, 'new-logo3.jpg', NULL, 40779, 'admin', 'admin', '2024-06-28 09:01:27', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806495592971476994, NULL, 'a1786df290a4457a97cb91ffa21377d4.jpg', 'maojiang-shop-1300160460', NULL, 'kefu.jpg', NULL, 33217, 'admin', 'admin', '2024-06-28 09:11:27', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806496341088509954, NULL, '95216fa09e51456fbf45c92fc89bbab6.jpg', 'maojiang-shop-1300160460', NULL, 'kefu.jpg', NULL, 41164, 'admin', 'admin', '2024-06-28 09:14:25', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806497982772649985, NULL, 'cee4ed4f8b8449cfbcac7e1979b984e1.jpg', 'maojiang-shop-1300160460', NULL, 'banner4.jpg', NULL, 39177, 'admin', 'admin', '2024-06-28 09:20:57', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806497982831370242, NULL, 'db6388b5ec57462899cf12194fedc05f.jpg', 'maojiang-shop-1300160460', NULL, 'banner3.jpg', NULL, 95210, 'admin', 'admin', '2024-06-28 09:20:57', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806497982860730369, NULL, '54a451fb3da64a75922b0cfc9c0d34c2.jpg', 'maojiang-shop-1300160460', NULL, 'banner2.jpg', NULL, 85413, 'admin', 'admin', '2024-06-28 09:20:57', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806497983032696834, NULL, 'a12d49c3d0c94e92959cce0798c12bfd.png', 'maojiang-shop-1300160460', NULL, 'banner5.png', NULL, 140893, 'admin', 'admin', '2024-06-28 09:20:57', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806497983330492418, NULL, '6d37109f37f14b4e9e1af47a635e2fef.jpg', 'maojiang-shop-1300160460', NULL, 'banner1.jpg', NULL, 68298, 'admin', 'admin', '2024-06-28 09:20:57', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806497998169939970, NULL, 'bfd65fe24bfa4bc4a1267226c4be4cdc.jpg', 'maojiang-shop-1300160460', NULL, 'banner2.jpg', NULL, 85413, 'admin', 'admin', '2024-06-28 09:21:01', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806498015429500929, NULL, 'c7f94c8967034ef19613216a80678670.jpg', 'maojiang-shop-1300160460', NULL, 'banner3.jpg', NULL, 95210, 'admin', 'admin', '2024-06-28 09:21:05', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806498033125269506, NULL, '3bbe90bf49814df4ad28bb6b2b90bfcb.jpg', 'maojiang-shop-1300160460', NULL, 'banner4.jpg', NULL, 39177, 'admin', 'admin', '2024-06-28 09:21:09', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806498048338010114, NULL, 'cef0c161b94f4df898c4ea0eb2807730.png', 'maojiang-shop-1300160460', NULL, 'banner5.png', NULL, 140893, 'admin', 'admin', '2024-06-28 09:21:12', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806500457202294785, NULL, '8d97c03c974d45fb8ef9da7618cbbea1.png', 'maojiang-shop-1300160460', NULL, 'nav04.png', NULL, 20123, 'admin', 'admin', '2024-06-28 09:30:47', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806523799175098369, NULL, '43db159b7bc74d0dac7f0544b93a1b02.jpeg', 'maojiang-shop-1300160460', NULL, 'wNVzmkYMv7Yd61dc20706f1e2815ca1e55a5c40b55d8.jpeg', NULL, 5877, 'obRk75ilgZyPn2SEqTp4mXjr-LRM', 'obRk75ilgZyPn2SEqTp4mXjr-LRM', '2024-06-28 11:03:32', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806539658899668993, NULL, '855e1cb21a32484ca37e4685212addfc.png', 'maojiang-shop-1300160460', NULL, 'tabbar1_1.png', NULL, 1574, 'admin', 'admin', '2024-06-28 12:06:33', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806539667904839681, NULL, 'c98a1a42dba64531af9c379df68f0885.png', 'maojiang-shop-1300160460', NULL, 'tabbar1.png', NULL, 1623, 'admin', 'admin', '2024-06-28 12:06:35', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1806694073430360065, NULL, 'f4e9db692e8c45158c3a3ff543254e63.jpg', 'maojiang-shop-1300160460', NULL, 'new-logo1.jpg', NULL, 41078, 'admin', 'admin', '2024-06-28 22:20:09', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1807050830451245058, NULL, '55f1ba9e32bb4265b620dd8e7d3db13b.jpg', 'maojiang-shop-1300160460', NULL, 'new-logo3.jpg', NULL, 40779, 'admin', 'admin', '2024-06-29 21:57:46', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1807760529865756673, NULL, '49918566e998470d83c0bec9777c7930.jpg', 'maojiang-shop-1300160460', NULL, 'kefu.jpg', NULL, 41164, 'admin', 'admin', '2024-07-01 20:57:52', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1808139206403244033, NULL, 'db7280b58ba5414b89ec271a4dd0ae96.png', 'maojiang-shop-1300160460', NULL, 'tabbar1.png', NULL, 1623, 'admin', 'admin', '2024-07-02 22:02:35', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1808139228154904577, NULL, '052d620b94c1486281b4d2197d330f1c.png', 'maojiang-shop-1300160460', NULL, 'tabbar1_1.png', NULL, 1574, 'admin', 'admin', '2024-07-02 22:02:40', NULL, '0', 1);
INSERT INTO `sys_file` VALUES (1810958536140595202, NULL, '0cdcfcd2f0d841e7885657ec5dc02bc4.jpeg', 'maojiang-shop-1300160460', NULL, 'hNyu3Fjnr4F1a06b59a28b87659e99ee3d612815a4d4.jpeg', NULL, 5878, 'oYyrs5c1_YSx517favnjWq3vpQNo', 'oYyrs5c1_YSx517favnjWq3vpQNo', '2024-07-10 16:45:36', NULL, '0', 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限',
  `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端路由标识路径',
  `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端组件路径',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单类型, 0:目录 1：菜单 2:按钮',
  `sort_order` int(11) NULL DEFAULT 1 COMMENT '排序值',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否可见，0隐藏，1显示',
  `keep_alive` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '路由缓冲',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` int(11) NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, -1, '系统管理', '', '/admin', '', '#ali-icon-xitongguanli', '0', 2, '1', '0', '2018-01-20 13:17:19', '2023-03-12 11:15:14', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (2, 1, '用户管理', '', '/admin/system/user/index', '', '#ali-icon-yonghuguanli', '0', 3, '1', '0', '2018-09-28 08:29:53', '2025-12-12 11:29:00', 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (3, 2, '用户查看', 'sys_user_view', '', NULL, 'icon-shuju', '1', 1, '1', '0', '2019-08-16 10:08:56', '2022-04-23 11:27:11', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (4, 2, '用户新增', 'sys_user_add', '', NULL, 'icon-crm-kehuguanli', '1', 2, '1', '0', '2018-01-20 13:17:19', '2022-04-23 11:27:15', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (5, 2, '用户修改', 'sys_user_edit', NULL, NULL, '', '1', 3, '1', '0', '2018-05-15 21:35:18', '2022-04-23 11:27:22', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (6, 2, '用户删除', 'sys_user_del', NULL, NULL, '', '1', 4, '1', '0', '2018-05-15 21:35:18', '2022-04-23 11:27:26', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (7, 1, '角色管理', '', '/admin/system/role/index', '', '#ali-icon-quanxian', '0', 4, '1', '0', '2018-05-15 21:35:18', '2025-12-12 11:29:07', 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (8, 7, '角色查看', 'sys_role_view', NULL, NULL, NULL, '1', 1, '1', '0', '2022-04-09 19:02:55', '2022-04-23 11:18:53', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (9, 7, '角色新增', 'sys_role_add', NULL, NULL, '', '1', 2, '1', '0', '2018-05-15 21:35:18', '2022-04-23 11:28:48', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (10, 7, '角色修改', 'sys_role_edit', NULL, NULL, '', '1', 3, '1', '0', '2018-05-15 21:35:18', '2022-04-23 11:28:53', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (11, 7, '角色删除', 'sys_role_del', NULL, NULL, '', '1', 4, '1', '0', '2018-05-15 21:35:18', '2022-04-23 11:28:57', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (12, 7, '分配权限', 'sys_role_perm', NULL, NULL, NULL, '1', 5, '1', '0', '2022-04-17 11:18:03', '2025-12-12 11:26:00', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (13, 1, '字典管理', '', '/admin/dict/index', '', '#ali-icon-zidianmokuai', '0', 9, '1', '0', '2022-04-12 21:02:34', '2025-12-12 11:29:30', 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (14, 13, '字典查看', 'sys_dict_view', '', NULL, NULL, '1', 1, '1', '0', '2022-04-12 21:03:04', '2022-04-23 11:29:10', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (15, 13, '字典新增', 'sys_dict_add', NULL, NULL, NULL, '1', 2, '1', '0', '2022-04-12 21:03:27', '2022-04-23 11:29:20', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (16, 13, '字典修改', 'sys_dict_edit', NULL, NULL, NULL, '1', 3, '1', '0', '2022-04-12 21:03:47', '2022-04-23 11:29:25', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (17, 13, '字典删除', 'sys_dict_del', NULL, NULL, NULL, '1', 4, '1', '0', '2022-04-12 21:04:04', '2022-04-23 11:29:33', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (18, 1, '菜单管理', NULL, '/admin/system/menu/index', '', '#ali-icon-caidan', '0', 5, '1', '0', '2022-04-14 19:58:25', '2025-12-12 11:29:13', 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, 18, '菜单查看', 'sys_menu_view', NULL, NULL, NULL, '1', 1, '1', '0', '2022-04-14 19:58:56', '2022-04-23 11:29:55', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (20, 18, '菜单新增', 'sys_menu_add', NULL, NULL, NULL, '1', 2, '1', '0', '2022-04-14 19:59:13', '2022-04-23 11:30:02', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (21, 18, '菜单修改', 'sys_menu_edit', NULL, NULL, NULL, '1', 3, '1', '0', '2022-04-14 19:59:31', '2022-04-23 11:30:07', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (22, 18, '菜单删除', 'sys_menu_del', NULL, NULL, NULL, '1', 4, '1', '0', '2022-04-14 19:59:46', '2022-04-23 11:30:13', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (23, 1, '部门管理', NULL, '/admin/system/dept/index', '', '#ali-icon-bumenguanli', '0', 1, '1', '0', '2022-04-16 18:17:14', '2025-12-10 20:09:40', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (24, 23, '部门查看', 'sys_dept_view', NULL, NULL, NULL, '1', 1, '1', '0', '2022-04-16 18:18:08', '2022-04-23 11:30:31', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (25, 23, '部门新增', 'sys_dept_add', NULL, NULL, NULL, '1', 2, '1', '0', '2022-04-16 18:18:24', '2022-04-23 11:30:36', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (26, 23, '部门修改', 'sys_dept_edit', NULL, NULL, NULL, '1', 3, '1', '0', '2022-04-16 18:18:48', '2022-04-23 11:30:41', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (27, 23, '部门删除', 'sys_dept_del', NULL, NULL, NULL, '1', 4, '1', '0', '2022-04-16 18:19:01', '2022-04-23 11:30:46', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (28, -1, '开发平台', NULL, '/gen', NULL, 'icon-shejiyukaifa-', '0', 2, '1', '0', '2022-04-26 20:52:24', '2022-06-22 16:08:18', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (29, 28, '数据源管理', NULL, '/gen/datasource/index', NULL, 'icon-mysql', '0', 1, '1', '0', '2022-04-26 20:52:24', '2025-12-10 20:09:50', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (30, 28, '代码生成', NULL, '/gen/generator/index', NULL, 'icon-weibiaoti46', '0', 2, '1', '0', '2022-04-26 20:52:24', '2025-12-10 20:09:50', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (31, 28, '表单设计', NULL, '/gen/design/index', NULL, 'icon-biaodanbiaoqian', '0', 3, '1', '0', '2022-04-28 15:55:07', '2025-12-10 20:09:51', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (42, 1, '参数管理', '', '/admin/param/index', NULL, '#ali-icon-xitongcanshupeizhiicon-', '0', 6, '1', '0', '2018-01-20 13:17:19', '2025-12-10 20:09:51', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (43, 42, '参数查看', 'sys_param_view', NULL, NULL, NULL, '1', 1, '1', '0', '2018-01-20 13:17:19', '2022-06-07 08:47:37', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (44, 42, '参数新增', 'sys_param_add', NULL, NULL, NULL, '1', 2, '1', '0', '2018-01-20 13:17:19', '2022-06-07 08:48:20', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (45, 42, '参数修改', 'sys_param_edit', NULL, NULL, NULL, '1', 3, '1', '0', '2018-01-20 13:17:19', '2022-06-07 08:48:28', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (46, 42, '参数删除', 'sys_param_del', NULL, NULL, NULL, '1', 4, '1', '0', '2018-01-20 13:17:19', '2022-06-07 08:48:38', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (47, 1, '文件管理', '', '/admin/file/index', NULL, '#ali-icon-wenjianguanli1', '0', 7, '1', '0', '2018-01-20 13:17:19', '2025-12-10 20:09:51', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (48, 47, '文件查看', 'sys_file_view', NULL, NULL, NULL, '1', 1, '1', '0', '2018-01-20 13:17:19', '2018-07-29 13:38:19', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (49, 47, '文件新增', 'sys_file_add', NULL, NULL, NULL, '1', 2, '1', '0', '2018-01-20 13:17:19', '2018-07-29 13:38:19', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (50, 47, '文件修改', 'sys_file_edit', NULL, NULL, NULL, '1', 3, '1', '0', '2018-01-20 13:17:19', '2018-07-29 13:38:19', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (51, 47, '文件删除', 'sys_file_del', NULL, NULL, NULL, '1', 4, '1', '0', '2018-01-20 13:17:19', '2018-07-29 13:38:19', 1, 1, 0);
INSERT INTO `sys_menu` VALUES (79, 1, '岗位管理', NULL, '/admin/system/post/index', NULL, '', '0', 2, '1', '0', '2025-12-12 11:28:43', '2025-12-12 22:54:32', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (80, 79, '岗位查看', 'sys_post_view', NULL, NULL, NULL, '1', 1, '1', '0', '2025-12-12 12:21:54', '2025-12-12 12:21:54', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (81, 79, '岗位新增', 'sys_post_add', NULL, NULL, NULL, '1', 2, '1', '0', '2025-12-12 12:22:25', '2025-12-12 12:22:25', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (82, 79, '岗位修改', 'sys_post_edit', NULL, NULL, NULL, '1', 3, '1', '0', '2025-12-12 12:22:50', '2025-12-12 12:23:05', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (83, 79, '岗位删除', 'sys_post_del', NULL, NULL, NULL, '1', 4, '1', '0', '2025-12-12 12:23:21', '2025-12-12 12:23:21', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '键',
  `value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '值',
  `is_system` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '系统内置',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_param
-- ----------------------------

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(11) NOT NULL COMMENT '岗位排序',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '岗位描述',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'TEAM_LEADER', '部门负责人', 0, 'LEADER', '0', '2022-03-26 13:48:17', '', '2025-12-12 12:31:31', 'admin', 1);
INSERT INTO `sys_post` VALUES (1999294796641755155, '001', '销售岗', 1, '销售岗', '0', '2024-07-12 22:26:27', 'admin', NULL, '', 1811754375729545218);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ds_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '2',
  `ds_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` int(11) NULL DEFAULT 0,
  `tenant_id` bigint(20) NULL DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`role_id`) USING BTREE,
  INDEX `role_idx1_role_code`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1999294796641755139 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'role_admin', '管理员', '2', '3', '2017-10-29 15:45:51', '2025-12-10 20:21:09', '1', '1', 0, 1);
INSERT INTO `sys_role` VALUES (4, '测试角色', 'role_test', NULL, '2', NULL, '2023-04-03 20:33:00', '2025-12-10 20:21:05', '1', '1', 0, 1);
INSERT INTO `sys_role` VALUES (1999294796641755138, '普通角色', 'role_normal', '111222', '3', '', '2025-12-12 09:46:39', '2025-12-12 09:59:33', 'admin', 'admin', 0, NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (1, 26);
INSERT INTO `sys_role_menu` VALUES (1, 27);
INSERT INTO `sys_role_menu` VALUES (1, 42);
INSERT INTO `sys_role_menu` VALUES (1, 43);
INSERT INTO `sys_role_menu` VALUES (1, 44);
INSERT INTO `sys_role_menu` VALUES (1, 45);
INSERT INTO `sys_role_menu` VALUES (1, 46);
INSERT INTO `sys_role_menu` VALUES (1, 47);
INSERT INTO `sys_role_menu` VALUES (1, 48);
INSERT INTO `sys_role_menu` VALUES (1, 49);
INSERT INTO `sys_role_menu` VALUES (1, 50);
INSERT INTO `sys_role_menu` VALUES (1, 51);
INSERT INTO `sys_role_menu` VALUES (1, 79);
INSERT INTO `sys_role_menu` VALUES (1, 80);
INSERT INTO `sys_role_menu` VALUES (1, 81);
INSERT INTO `sys_role_menu` VALUES (1, 82);
INSERT INTO `sys_role_menu` VALUES (1, 83);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 1);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 2);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 3);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 4);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 5);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 6);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 7);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 8);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 9);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 10);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 11);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 12);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 13);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 14);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 15);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 16);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 17);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 18);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 19);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 20);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 21);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 22);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 23);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 24);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 25);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 26);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 27);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 42);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 43);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 44);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 45);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 46);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 47);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 48);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 49);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 50);
INSERT INTO `sys_role_menu` VALUES (1999294796641755138, 51);

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant`  (
  `id` bigint(20) NOT NULL COMMENT '租户ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户编码',
  `tenant_domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户域名',
  `website_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网站名称',
  `mini_qr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '移动端二维码',
  `background` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录页背景图',
  `footer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '页脚信息',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'logo',
  `start_time` datetime NULL DEFAULT NULL COMMENT '租户开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '租户结束时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '租户状态，0正常，1停用',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记，0未删除，1已删除',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT ' ' COMMENT '创建人',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT ' ' COMMENT '修改人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `menu_id` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '租户菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
INSERT INTO `sys_tenant` VALUES (1, '北京分公司', '1', 'dcd.zhouzhenlin.com', '达成度生成', NULL, NULL, NULL, NULL, '2019-05-15 00:00:00', '2029-05-15 00:00:00', '0', '0', '', 'admin', '2019-05-15 15:44:57', '2024-07-18 13:20:09', '1642752536722997250');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '真实姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `is_super` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '是否是超级管理员 1：是 0:否',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `lock_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '锁定标记，0未锁定，9已锁定',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` int(11) NULL DEFAULT 0 COMMENT '删除标志',
  `tenant_id` bigint(20) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_idx1_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1999485243725115394 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$kXIMjjUm5ZaKZx46w72WWODe5QS/JZ0.kxGBF7BSBwcNCORuLUPOG', '13345092256', '956459902@163.com', '周', 'https://gitee.com/uploads/61/632261_smallweigit.jpg', 'zhou', 1, 1, '0', '2022-04-09 17:33:05', '2025-12-12 20:55:41', '1', 'admin', 0, 1);
INSERT INTO `sys_user` VALUES (3, 'test', NULL, NULL, NULL, '', NULL, NULL, 0, 4, '0', '2023-04-03 20:32:17', '2025-12-12 12:42:14', '1', 'admin', 0, 1);
INSERT INTO `sys_user` VALUES (1999485243725115393, 'normal', '$2a$10$WJMsin36.gasiObqzsHV6.sK0zcMA/sqTNRKYyZ/iydADNNe3Wu3i', '13345092258', '956459902@163.com', '周振林', NULL, 'joe', 0, 1998989225195642882, '0', '2025-12-12 22:23:25', '2025-12-14 18:53:44', 'normal', 'admin', 0, 0);

-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `dept_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------
INSERT INTO `sys_user_dept` VALUES (1, 1);
INSERT INTO `sys_user_dept` VALUES (1999485243725115393, 1998989225195642882);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (1999485243725115393, 1);
INSERT INTO `sys_user_post` VALUES (1999485243725115393, 1999294796641755155);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1999485243725115393, 1);
INSERT INTO `sys_user_role` VALUES (1999485243725115393, 1999294796641755138);

SET FOREIGN_KEY_CHECKS = 1;
