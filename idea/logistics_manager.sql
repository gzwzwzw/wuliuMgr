/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : logistics_manager

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 14/11/2024 20:56:12
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `roles` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1f8f045d-eeaa-4838-b327-17840f4ba55b', '2025-5-14 20:41:11', '222@qq.com', '123456', 'ROLE_EMPLOYEE');
INSERT INTO `admin` VALUES ('3f33b767-d6cd-4736-9a51-6c1ff27e0663', '2025-5-20 20:00:08', '123@qq.com', '123456', 'ROLE_SUPER_ADMIN');
INSERT INTO `admin` VALUES ('9aw4fs5a-e12d-41ee-b548-8cb429ecad13', '2025-5-14 20:25:00', '789@qq.com', '123456', 'ROLE_COMMODITY');
INSERT INTO `admin` VALUES ('9b23fa9e-6448-4226-b9f2-32ac6ac8e4b8', '2025-5-14 20:41:36', '444@qq.com', '123456', 'ROLE_WAREHOUSE');
INSERT INTO `admin` VALUES ('d27364c7-e12d-41ee-b548-8cb429ecad13', '2025-5-14 20:23:38', '456@qq.com', '123456', 'ROLE_ADMIN');
INSERT INTO `admin` VALUES ('ec3aa694-e531-49f5-885b-21de6acfcef0', '2025-5-14 20:40:23', '333@qq.com', '123456', 'ROLE_SALE');

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `count` int(0) NOT NULL,
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` double NOT NULL,
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1dade9ff-ba00-4f78-bd27-55771dc65061', 0, '2025-6-18 11:30:17', '商品简介', '抽纸', 9.99, NULL);
INSERT INTO `commodity` VALUES ('52fdac99-f224-469e-8af8-ed49c166bb23', 20, '2025-6-22 14:14:29', '搭载麒麟980芯片', 'HUWAI MATE 30 Pro', 4000, NULL);
INSERT INTO `commodity` VALUES ('5fcb392b-39aa-4381-b5ad-b1ccd8d5b74e', 20081, '2025-6-02 20:36:04', 'Mac笔记本', 'Mac', 7400, NULL);
INSERT INTO `commodity` VALUES ('91eb39e3-d664-4aa8-8826-750d0a45bc67', 0, '2025-6-14 17:45:04', '键盘', '青柚', 34, NULL);
INSERT INTO `commodity` VALUES ('b3a8f5a2-2dac-4194-b806-687b7f08e82f', 50, '2025-6-11 13:35:52', '商品简介', '鼠标', 9.99, NULL);
INSERT INTO `commodity` VALUES ('eda4215d-82d8-4a08-a3f3-981b19274006', 70, '2025-6-24 23:56:52', '商品简介', 'Apple', 40000, NULL);

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for distribution
-- ----------------------------
DROP TABLE IF EXISTS `distribution`;
CREATE TABLE `distribution`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `care` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `did` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `driver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `urgent` bit(1) NOT NULL,
  `vid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of distribution
-- ----------------------------
INSERT INTO `distribution` VALUES ('3036e2cf-28db-4d4c-b52d-5f590540b74b', 'sad', '冰柜冷藏, ', 'ea3bba9b-cda6-438d-b196-7c81e97b5040', '王五', '京A0000', 'sad', 2, '2025-06-09 13:09:23.000000', b'1', 'ce33de59-9584-4161-a17e-9046399d14c6');
INSERT INTO `distribution` VALUES ('77a6b6b9-682c-4aba-926b-6d7645895037', '2343', '注意易碎, ', 'ea3bba9b-cda6-438d-b196-7c81e97b5040', '王五', '京A0000', '234', 0, '2025-06-08 12:58:27.000000', b'1', 'ce33de59-9584-4161-a17e-9046399d14c6');
INSERT INTO `distribution` VALUES ('7c6c52fd-abbe-4505-a7c0-ea12f68ed6cb', 'asd345', '冰柜冷藏, ', 'ea3bba9b-cda6-438d-b196-7c81e97b5040', '王五', '京A0000', 'sd435345', 0, '2025-06-07 13:01:39.000000', b'1', 'ce33de59-9584-4161-a17e-9046399d14c6');
INSERT INTO `distribution` VALUES ('ae587e56-1d74-4edf-9707-ef7235eebd91', '324234', '冰柜冷藏, ', 'ea3bba9b-cda6-438d-b196-7c81e97b5040', '王五', '京A0000', '235352', 0, '2025-06-06 12:59:04.000000', b'1', 'ce33de59-9584-4161-a17e-9046399d14c6');
INSERT INTO `distribution` VALUES ('ff9bcb29-5e7b-4462-b789-a92576711ef7', 'asd', '冰柜冷藏, ', 'ea3bba9b-cda6-438d-b196-7c81e97b5040', '王五', '京A0000', 'sd', 0, '2025-06-07 13:01:39.000000', b'1', 'ce33de59-9584-4161-a17e-9046399d14c6');

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `driving` bit(1) NOT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('a4a88363-a438-4847-a4bf-b25e6fa5ae6e', 'xxx', '2025-6-14 20:48:34', b'0', '男性', '110256199702256666', 'xxx', '李四', '13333333333', '12', NULL);
INSERT INTO `driver` VALUES ('ea3bba9b-cda6-438d-b196-7c81e97b5040', '北京市', '2025-6-14 20:48:52', b'0', '男性', '110447198702026547', '34', '王五', '14755555555', '12', NULL);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('18d600a1-8184-43bc-b46d-11c66ff2561d', 'xxx', '2025-5-11 11:07:38', 'A号仓库', '男性', '434242341', '张师傅', '1373242363', NULL);
INSERT INTO `employee` VALUES ('8ba9bf53-7e1d-43ff-ab71-b9d6af299c26', '广州', '2025-5-18 11:38:16', 'A号仓库', '男性', '441253546456234', '王师傅', '13729532464', NULL);

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `count` int(0) NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `wid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES ('0ff7a831-0824-428d-b140-ab41de52d5a4', 'eda4215d-82d8-4a08-a3f3-981b19274006', 70, NULL, 'Apple', '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory` VALUES ('3c6f0c42-25e8-4ccb-b30a-2622d9740c87', '52fdac99-f224-469e-8af8-ed49c166bb23', 20, NULL, 'HUWAI MATE 30 Pro', '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory` VALUES ('ca7f8e97-1edf-4fc8-b9a1-783a7d053131', '5fcb392b-39aa-4381-b5ad-b1ccd8d5b74e', 20081, NULL, 'Mac', '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory` VALUES ('d9afc86f-a782-4d00-9300-78484f72c117', '2283b0a2-5e0b-4c1e-b651-d2e3b51b87ee', 80, NULL, '手机支架', '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory` VALUES ('e8e63bfa-be44-4cb8-abe3-0b6baf3159fa', 'b3a8f5a2-2dac-4194-b806-687b7f08e82f', 50, NULL, '鼠标', '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');

-- ----------------------------
-- Table structure for inventory_record
-- ----------------------------
DROP TABLE IF EXISTS `inventory_record`;
CREATE TABLE `inventory_record`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `count` int(0) NULL DEFAULT NULL,
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `wid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inventory_record
-- ----------------------------
INSERT INTO `inventory_record` VALUES ('0327e497-6349-4a33-816e-38f680bd48f4', 'b3a8f5a2-2dac-4194-b806-687b7f08e82f', 100, '2025-7-11 13:36:27', 'xxx', '鼠标', 1, '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory_record` VALUES ('2c141734-d0d0-4f4e-bf66-ae60667f0709', 'eda4215d-82d8-4a08-a3f3-981b19274006', 20, '2025-6-25 00:01:09', 'ss', 'Apple', 1, '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory_record` VALUES ('4197fbfb-78a5-4cae-b825-955099da1943', '52fdac99-f224-469e-8af8-ed49c166bb23', 20, '2025-6-22 14:16:57', '', 'HUWAI MATE 30 Pro', 1, '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory_record` VALUES ('51f8cee5-a238-41dc-b031-40b05cd83abf', '2283b0a2-5e0b-4c1e-b651-d2e3b51b87ee', 100, '2025-6-11 11:06:12', 'xxxxx', '手机支架', 1, '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory_record` VALUES ('6ff4d4dc-326c-4fd3-a782-06b799f9931a', '5fcb392b-39aa-4381-b5ad-b1ccd8d5b74e', 21312, '2025-7-02 20:36:52', '213', 'Mac', 1, '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory_record` VALUES ('b1bdf63a-3c77-45b6-8841-8d149986f709', '5fcb392b-39aa-4381-b5ad-b1ccd8d5b74e', 1231, '2025-7-02 20:37:18', '123', 'Mac', -1, '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory_record` VALUES ('b49e8ba9-30c8-4794-ab1f-c807c5dbc8c2', '2283b0a2-5e0b-4c1e-b651-d2e3b51b87ee', 20, '2025-7-11 11:08:25', 'xxxx', '手机支架', -1, '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory_record` VALUES ('e99dd646-bbd0-42d9-a315-72d081af8544', 'b3a8f5a2-2dac-4194-b806-687b7f08e82f', 50, '2025-7-11 13:39:15', '50', '鼠标', -1, '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');
INSERT INTO `inventory_record` VALUES ('fa51e39c-f210-4bcf-928f-e1036c6bdffb', 'eda4215d-82d8-4a08-a3f3-981b19274006', 50, '2025-7-11 09:27:00', '入库苹果手机', 'Apple', 1, '9f2a2784-e182-4fdf-85e8-c3bde6d539d2');

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES ('05f9f121-fe6e-40b5-b800-3b641f2c9a75', 'Chrome', '2025-7-14 20:40:42.716000', '123@qq.com', '127.0.0.1', 1);
INSERT INTO `login_log` VALUES ('0ae8bb9d-bf7a-45d7-8b28-fa1ab6601ad7', 'Chrome', '2025-7-14 20:16:36.490000', '456@qq.com', '127.0.0.1', 1);
INSERT INTO `login_log` VALUES ('28a23e80-1a8d-4a3a-9abb-c1b00936944c', 'Chrome', '2025-7-14 20:14:55.024000', '456@qq.com', '127.0.0.1', 0);
INSERT INTO `login_log` VALUES ('47212999-2d15-4d20-b109-4a42bf5300e5', 'Chrome', '2025-7-14 20:10:17.111000', '456@qq.com', '127.0.0.1', 0);
INSERT INTO `login_log` VALUES ('4f990cb8-9a49-47f7-a6ea-462a0ef65758', 'Chrome', '2025-7-14 20:14:47.780000', '456@qq.com', '127.0.0.1', 0);
INSERT INTO `login_log` VALUES ('542dc2ed-1dad-4a6a-88e6-a289d13e5c8d', 'Chrome', '2025-7-14 20:13:05.549000', '456@qq.com', '127.0.0.1', 0);
INSERT INTO `login_log` VALUES ('55e63db3-fa62-4e47-90fa-acb6f6313012', 'Chrome', '2025-7-14 20:25:07.488000', '456@qq.com', '127.0.0.1', 1);
INSERT INTO `login_log` VALUES ('80430216-3883-4aac-832f-1cd9c0ffdfba', 'Chrome', '2025-7-14 20:14:32.452000', '456@qq.com', '127.0.0.1', 0);
INSERT INTO `login_log` VALUES ('8ee4573c-517e-4a00-a6dc-257b11c7a4d5', 'Chrome', '2025-7-14 20:15:24.057000', '456@qq.com', '127.0.0.1', 0);
INSERT INTO `login_log` VALUES ('9c4b210c-2b0b-46fa-9a6f-17309f30beb8', 'Chrome', '2025-7-14 20:31:36.180000', '123@qq.com', '127.0.0.1', 1);
INSERT INTO `login_log` VALUES ('a1ecf216-020c-4720-9189-d78a7381977d', 'Chrome', '2025-7-14 20:22:24.521000', 'admin@qq.com', '127.0.0.1', 1);
INSERT INTO `login_log` VALUES ('cc6f6041-cc0a-4801-9bdf-fa1f78656350', 'Chrome', '2025-7-14 20:21:36.427000', '1402014577@qq.com', '127.0.0.1', 1);
INSERT INTO `login_log` VALUES ('d4662e54-aca5-4c84-bbfc-9010f3cef421', 'Chrome', '2025-7-14 20:15:42.057000', '123@qq.com', '127.0.0.1', 1);
INSERT INTO `login_log` VALUES ('da92a77c-2c65-4661-b7c7-b8c97aa51ff2', 'Chrome', '2025-7-14 20:22:02.683000', 'lop@qq.com', '127.0.0.1', 1);
INSERT INTO `login_log` VALUES ('df7865fe-112c-4a8e-8e80-5c912cd0b624', 'Chrome', '2025-7-14 20:10:20.239000', '456@qq.com', '127.0.0.1', 0);
INSERT INTO `login_log` VALUES ('e7988b9d-e89b-4e06-b0be-1abbdc2d521c', 'Chrome', '2025-7-14 20:33:24.868000', '123@qq.com', '127.0.0.1', 1);

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `commodity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `count` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pay` bit(1) NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` double NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sale
-- ----------------------------
INSERT INTO `sale` VALUES ('0da2a4b7-208d-4c1d-bdd2-8966590f46c5', 'HUWAI MATE 30 Pro', '2', '500', '2025-7-13 14:23:44', 'ccc', '2342', b'1', '232423', 2000000);
INSERT INTO `sale` VALUES ('1307750a-78eb-4b07-8fb7-5b75bb3c6dc9', '手机支架', '中兴', '50', '2025-7-12 11:07:12', 'xxx', '234234', b'1', '213123', 499.5);
INSERT INTO `sale` VALUES ('38214342-a785-4929-9208-94762c16a7d0', 'Apple', '小米', '20', '2025-7-11 10:15:35', 'xxx', '428354352', b'1', '13789253421', 800000);
INSERT INTO `sale` VALUES ('b97a82bf-6aa8-47a7-a3a6-03e0df570038', '鼠标', '亚马逊', '50', '2025-7-10 13:37:50', '2341234', '342342', b'1', '214234', 499.5);
INSERT INTO `sale` VALUES ('bbcb2f8d-9ab4-4b6b-b0ff-1edae383c7a5', 'HUWAI MATE 30 Pro', '华为', '50', '2025-7-9 10:11:07', '234', '2134', b'1', '12332542342', 200000);
INSERT INTO `sale` VALUES ('e19debea-048d-4f38-9adb-d75346a3ccb9', '抽纸', 'ttt', '50', '2025-7-8 20:54:05', '无', '111444578974986', b'1', '15877777777', 499.5);

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `busincess_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'LTD',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `module` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES ('01d43c76-dc06-4583-a079-9298ae6b6e55', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.WarehouseController.findAll', '仓库管理', '2025-7-14 20:51:40.772000');
INSERT INTO `system_log` VALUES ('094ed1c5-e69c-455f-8cf2-0fdf56b8fc26', '123@qq.com', '新增', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.save', '驾驶员管理', '2025-7-14 20:48:34.284000');
INSERT INTO `system_log` VALUES ('0a2689c8-e9d8-4d30-80b0-51897dbbea91', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:31:40.157000');
INSERT INTO `system_log` VALUES ('0a2873ed-eca1-4bf6-8a09-2a1e8aabff49', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:33:25.959000');
INSERT INTO `system_log` VALUES ('0c143029-4d18-49b7-a511-120bead4bb29', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:52:36.822000');
INSERT INTO `system_log` VALUES ('0cf33952-9902-4664-9993-a8379c5c2a54', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.findAll', '驾驶员管理', '2025-7-14 20:48:03.563000');
INSERT INTO `system_log` VALUES ('1135bfb2-9c58-4ce4-be89-f03c461ae2b6', '456@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.findAll', '驾驶员管理', '2025-7-14 20:25:32.197000');
INSERT INTO `system_log` VALUES ('11db9edb-fc38-474f-97bf-e563bc938f93', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.findAll', '驾驶员管理', '2025-7-14 20:43:28.528000');
INSERT INTO `system_log` VALUES ('15fabc28-0524-4942-b650-a967bbd82935', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.EmployeeController.findAll', '员工管理', '2025-7-14 20:09:48.905000');
INSERT INTO `system_log` VALUES ('20513bff-1abd-4b64-b6bc-c2a8799e64ee', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.DistributionController.findAll', '配送管理', '2025-7-14 20:52:07.547000');
INSERT INTO `system_log` VALUES ('23f43683-120a-4a21-82ae-088cf92a9a2d', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.findAll', '驾驶员管理', '2025-7-14 20:52:13.787000');
INSERT INTO `system_log` VALUES ('24f59bf7-081b-40ef-8fa6-4ee2362a81ff', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.findAll', '驾驶员管理', '2025-7-14 20:54:20.187000');
INSERT INTO `system_log` VALUES ('2d3930ec-bbae-4aee-8c18-c97d20b5c4ca', '456@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.DistributionController.findAll', '配送管理', '2025-7-14 20:25:26.210000');
INSERT INTO `system_log` VALUES ('32b5c120-ab7b-49d4-a11a-e6abc80b4444', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:52:44.261000');
INSERT INTO `system_log` VALUES ('38c06f28-e335-42b6-80be-9ac736241721', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:49:15.646000');
INSERT INTO `system_log` VALUES ('3e026e3b-63d8-4906-9257-91e026f08600', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.findAll', '驾驶员管理', '2025-7-14 20:31:51.878000');
INSERT INTO `system_log` VALUES ('43684d35-89e4-43ae-9126-b30626ca2376', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:54:24.206000');
INSERT INTO `system_log` VALUES ('436c6122-03cd-4e0b-9c30-980bce166400', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.WarehouseController.findAll', '仓库管理', '2025-7-14 20:49:58.206000');
INSERT INTO `system_log` VALUES ('4849d5cb-f3b6-454d-8b0e-7ea5062305bf', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.VehicleController.findAll', '车辆管理', '2025-7-14 20:43:29.721000');
INSERT INTO `system_log` VALUES ('4cb14d28-cfc1-4fcb-914e-f3f9ce4e772a', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:15:43.124000');
INSERT INTO `system_log` VALUES ('526ac964-4ef3-41fe-965b-e375caf3c4e6', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.WarehouseController.findAll', '仓库管理', '2025-7-14 20:09:48.905000');
INSERT INTO `system_log` VALUES ('5397fe25-98a0-4a35-af77-850f0f0c527c', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.WarehouseController.findAll', '仓库管理', '2025-7-14 20:49:22.001000');
INSERT INTO `system_log` VALUES ('5657a8fe-9468-4af1-9f24-df9ee39efdfb', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:40:44.152000');
INSERT INTO `system_log` VALUES ('5a3634e9-18c9-4acb-b0fe-67de8a85e7fb', '456@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:16:37.605000');
INSERT INTO `system_log` VALUES ('5b9cb4c3-3e38-4adb-a9bb-2a905155f332', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.WarehouseController.findAll', '仓库管理', '2025-7-14 20:49:50.241000');
INSERT INTO `system_log` VALUES ('5d0ae0f0-fd62-48f3-823e-1a90d431464e', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:52:21.077000');
INSERT INTO `system_log` VALUES ('5ded8002-1296-4d50-b7d5-13a1904158bb', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.findAll', '驾驶员管理', '2025-7-14 20:54:27.546000');
INSERT INTO `system_log` VALUES ('5e265455-8af2-4141-96a6-28a048f80347', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.VehicleController.findAll', '车辆管理', '2025-7-14 20:54:21.039000');
INSERT INTO `system_log` VALUES ('60485821-9313-493a-a1b3-25a39491c29b', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:51:55.650000');
INSERT INTO `system_log` VALUES ('69a4543c-7607-4439-9dbf-3817d843cc3b', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.VehicleController.findAll', '车辆管理', '2025-7-14 20:54:26.710000');
INSERT INTO `system_log` VALUES ('6ee00152-8320-4c1c-befd-71516bf3a6de', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:31:37.292000');
INSERT INTO `system_log` VALUES ('73685d02-643b-4075-bb4f-ae7c9bb6573b', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:51:48.734000');
INSERT INTO `system_log` VALUES ('73758df5-2503-4d28-b3f1-0bd690134638', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.WarehouseController.findAll', '仓库管理', '2025-7-14 20:49:18.627000');
INSERT INTO `system_log` VALUES ('7771f897-072d-46ea-bddf-482ba60d1ab4', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:09:46.302000');
INSERT INTO `system_log` VALUES ('7a028442-bd79-4824-8efc-e04f6b982966', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.VehicleController.findAll', '车辆管理', '2025-7-14 20:47:57.965000');
INSERT INTO `system_log` VALUES ('7c493ac4-5c4b-4c78-b23c-145852b73fea', '456@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.DistributionController.findAll', '配送管理', '2025-7-14 20:25:28.966000');
INSERT INTO `system_log` VALUES ('7e883aba-182d-4783-a988-9aa50809d07c', 'admin@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:22:25.606000');
INSERT INTO `system_log` VALUES ('881e2477-8fa3-42f4-aeef-276b4da77dab', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:49:59.363000');
INSERT INTO `system_log` VALUES ('918f3f81-49d6-4861-a7b7-f5a103337373', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.VehicleController.findAll', '车辆管理', '2025-7-14 20:31:50.618000');
INSERT INTO `system_log` VALUES ('9c95542a-6fbe-49ee-a5f9-8766b9f1d6a5', '456@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.VehicleController.findAll', '车辆管理', '2025-7-14 20:25:30.564000');
INSERT INTO `system_log` VALUES ('9df7b7e8-10f5-4167-b49a-f776b3be4331', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:49:55.163000');
INSERT INTO `system_log` VALUES ('a0a7f4d1-3f08-4aea-9a70-ffbfc8339142', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.DistributionController.findAll', '配送管理', '2025-7-14 20:31:47.320000');
INSERT INTO `system_log` VALUES ('a1185b48-9709-46a8-974d-0e11c8c741b1', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:52:27.218000');
INSERT INTO `system_log` VALUES ('a4350eb4-1421-4cbd-af92-6ca13a62dd28', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.WarehouseController.findAll', '仓库管理', '2025-7-14 20:09:42.127000');
INSERT INTO `system_log` VALUES ('b1814f53-aff6-425c-9c84-42c059b74026', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.VehicleController.findAll', '车辆管理', '2025-7-14 20:43:27.332000');
INSERT INTO `system_log` VALUES ('b7fcde69-7b2d-43f8-a1b9-a6088749047b', '123@qq.com', '新增', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.save', '驾驶员管理', '2025-7-14 20:48:14.936000');
INSERT INTO `system_log` VALUES ('bf32fd5b-67aa-43e6-aa92-3e83113c8e82', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:49:24.365000');
INSERT INTO `system_log` VALUES ('cd74a4d6-d5c6-4782-a88c-652cd65bb9f0', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.EmployeeController.findAll', '员工管理', '2025-7-14 20:49:33.075000');
INSERT INTO `system_log` VALUES ('ce24c346-82a2-4c23-99e8-b069f62fa50a', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.EmployeeController.findAll', '员工管理', '2025-7-14 20:49:18.627000');
INSERT INTO `system_log` VALUES ('d0908593-ac51-4c4c-b246-e54abebfec7e', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.VehicleController.findAll', '车辆管理', '2025-7-14 20:52:12.257000');
INSERT INTO `system_log` VALUES ('d342654b-478d-4e56-b87a-8af7d87b0320', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:49:36.203000');
INSERT INTO `system_log` VALUES ('d3fea53b-4a1c-45c6-a608-dd7a02a44f6e', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.WarehouseController.findAll', '仓库管理', '2025-7-14 20:49:33.075000');
INSERT INTO `system_log` VALUES ('d69fc575-026b-4be9-87db-3aa882f88826', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:54:09.126000');
INSERT INTO `system_log` VALUES ('d75563c1-11f8-4715-8298-f1752769bb42', '123@qq.com', '新增', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.save', '驾驶员管理', '2025-7-14 20:48:52.054000');
INSERT INTO `system_log` VALUES ('dad922fc-294a-4047-acdc-16b98c10606d', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.EmployeeController.findAll', '员工管理', '2025-7-14 20:49:22.001000');
INSERT INTO `system_log` VALUES ('dea8c62a-cc38-44dd-a352-67a7076c2592', '123@qq.com', '新增', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.save', '驾驶员管理', '2025-7-14 20:48:45.440000');
INSERT INTO `system_log` VALUES ('dff7736d-86f2-46dd-8895-fc2d041e0b11', '1402014577@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:21:37.635000');
INSERT INTO `system_log` VALUES ('e3c18bb0-4742-4a65-b328-41ea5ce665c8', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.WarehouseController.findAll', '仓库管理', '2025-7-14 20:49:33.539000');
INSERT INTO `system_log` VALUES ('e54fc274-7a64-44f5-a6f2-ee7d9d94135f', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.EmployeeController.findAll', '员工管理', '2025-7-14 20:49:50.241000');
INSERT INTO `system_log` VALUES ('e5a50bad-d14a-47c0-ae61-d799b2c837aa', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.WarehouseController.findAll', '仓库管理', '2025-7-14 20:49:53.571000');
INSERT INTO `system_log` VALUES ('ea653e3b-c821-477d-bb0c-ef651ff5bac1', '123@qq.com', '新增', '127.0.0.1', 'com.rabbiter.lm.controller.VehicleController.save', '车辆管理', '2025-7-14 20:47:57.940000');
INSERT INTO `system_log` VALUES ('ed05caea-f7f0-437f-9f57-94f46c16fb45', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.CommodityController.findAll', '商品管理', '2025-7-14 20:51:44.238000');
INSERT INTO `system_log` VALUES ('f6dccdb0-8bef-4056-b8be-677818a751e6', '123@qq.com', '新增', '127.0.0.1', 'com.rabbiter.lm.controller.DriverController.save', '驾驶员管理', '2025-7-14 20:48:21.843000');
INSERT INTO `system_log` VALUES ('f777eef5-4f44-412b-930f-030961b987b6', '123@qq.com', '查询', '127.0.0.1', 'com.rabbiter.lm.controller.WarehouseController.findAll', '仓库管理', '2025-7-14 20:49:19.845000');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `driving` bit(1) NOT NULL,
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES ('786fb288-4445-4784-b923-de013fa3892f', '2025-5-11 13:38:19', b'0', '京A0001', '货车');
INSERT INTO `vehicle` VALUES ('ba4b9ef1-c964-4841-9cc3-d106c2efc8dc', '2025-6-14 20:47:57', b'0', '京A0000', '小型汽车');
INSERT INTO `vehicle` VALUES ('ce33de59-9584-4161-a17e-9046399d14c6', '2025-4-21 12:26:18', b'0', '京A0000', '货车');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `principle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES ('9f2a2784-e182-4fdf-85e8-c3bde6d539d2', '2025-5-22 14:16:46', 'A号仓库', '杰克');

SET FOREIGN_KEY_CHECKS = 1;
