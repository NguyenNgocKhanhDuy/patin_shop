/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100428 (10.4.28-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : patin

 Target Server Type    : MySQL
 Target Server Version : 100428 (10.4.28-MariaDB)
 File Encoding         : 65001

 Date: 19/07/2024 16:23:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `payment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `isDeleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `b_u`(`user_id` ASC) USING BTREE,
  CONSTRAINT `b_u` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES (34, 'DH28D2024-07-16T14:25:02.109209900', '2024-07-16 14:25:02', 2, 'Trả tiền khi nhận', '', 28, 0);
INSERT INTO `bill` VALUES (35, 'DH28D2024-07-16T14:26:26.557484300', '2024-07-16 14:26:26', 0, 'Trả tiền khi nhận', '', 28, 0);

-- ----------------------------
-- Table structure for bill_detail
-- ----------------------------
DROP TABLE IF EXISTS `bill_detail`;
CREATE TABLE `bill_detail`  (
  `bill_id` int NOT NULL,
  `product_id` int NOT NULL,
  `size_id` int NOT NULL,
  `color_id` int NOT NULL,
  `quantity` int NULL DEFAULT NULL,
  `price` int NULL DEFAULT NULL,
  `isDeleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`bill_id`, `product_id`, `size_id`, `color_id`) USING BTREE,
  INDEX `b_c`(`color_id` ASC) USING BTREE,
  INDEX `b_p`(`product_id` ASC) USING BTREE,
  INDEX `b_s`(`size_id` ASC) USING BTREE,
  CONSTRAINT `b_b` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `b_c` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `b_p` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `b_s` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill_detail
-- ----------------------------
INSERT INTO `bill_detail` VALUES (34, 26, 2, 2, 1, 1260000, 0);
INSERT INTO `bill_detail` VALUES (35, 25, 2, 4, 1, 2000000, 0);
INSERT INTO `bill_detail` VALUES (35, 26, 2, 4, 1, 1260000, 0);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `isDeleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Giày Patin Trẻ em', 0);
INSERT INTO `category` VALUES (2, 'Giày Patin Người lớn', 0);
INSERT INTO `category` VALUES (3, 'Giày Patin hãng Micro', 0);
INSERT INTO `category` VALUES (4, 'Giày Patin hãng Flying Eagle', 0);
INSERT INTO `category` VALUES (5, 'Giày Patin hãng SOFT', 0);
INSERT INTO `category` VALUES (6, 'Giày Patin tốc độ cao', 0);

-- ----------------------------
-- Table structure for category_detail
-- ----------------------------
DROP TABLE IF EXISTS `category_detail`;
CREATE TABLE `category_detail`  (
  `id_category` int NOT NULL,
  `id_product` int NOT NULL,
  PRIMARY KEY (`id_category`, `id_product`) USING BTREE,
  INDEX `c_p`(`id_product` ASC) USING BTREE,
  CONSTRAINT `c-c` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `c_p` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category_detail
-- ----------------------------
INSERT INTO `category_detail` VALUES (1, 1);
INSERT INTO `category_detail` VALUES (1, 2);
INSERT INTO `category_detail` VALUES (1, 3);
INSERT INTO `category_detail` VALUES (1, 4);
INSERT INTO `category_detail` VALUES (1, 5);
INSERT INTO `category_detail` VALUES (1, 6);
INSERT INTO `category_detail` VALUES (1, 7);
INSERT INTO `category_detail` VALUES (1, 8);
INSERT INTO `category_detail` VALUES (1, 9);
INSERT INTO `category_detail` VALUES (1, 10);
INSERT INTO `category_detail` VALUES (1, 11);
INSERT INTO `category_detail` VALUES (1, 12);
INSERT INTO `category_detail` VALUES (1, 13);
INSERT INTO `category_detail` VALUES (1, 14);
INSERT INTO `category_detail` VALUES (1, 15);
INSERT INTO `category_detail` VALUES (1, 16);
INSERT INTO `category_detail` VALUES (1, 17);
INSERT INTO `category_detail` VALUES (1, 18);
INSERT INTO `category_detail` VALUES (1, 19);
INSERT INTO `category_detail` VALUES (1, 20);
INSERT INTO `category_detail` VALUES (1, 21);
INSERT INTO `category_detail` VALUES (1, 22);
INSERT INTO `category_detail` VALUES (1, 23);
INSERT INTO `category_detail` VALUES (1, 24);
INSERT INTO `category_detail` VALUES (1, 25);
INSERT INTO `category_detail` VALUES (1, 26);
INSERT INTO `category_detail` VALUES (1, 27);
INSERT INTO `category_detail` VALUES (1, 28);
INSERT INTO `category_detail` VALUES (1, 29);
INSERT INTO `category_detail` VALUES (1, 30);
INSERT INTO `category_detail` VALUES (1, 31);
INSERT INTO `category_detail` VALUES (1, 32);
INSERT INTO `category_detail` VALUES (1, 33);
INSERT INTO `category_detail` VALUES (1, 34);
INSERT INTO `category_detail` VALUES (1, 35);
INSERT INTO `category_detail` VALUES (1, 36);
INSERT INTO `category_detail` VALUES (1, 37);
INSERT INTO `category_detail` VALUES (1, 38);
INSERT INTO `category_detail` VALUES (1, 39);
INSERT INTO `category_detail` VALUES (1, 40);
INSERT INTO `category_detail` VALUES (1, 41);
INSERT INTO `category_detail` VALUES (2, 42);
INSERT INTO `category_detail` VALUES (2, 43);
INSERT INTO `category_detail` VALUES (2, 44);
INSERT INTO `category_detail` VALUES (2, 45);
INSERT INTO `category_detail` VALUES (2, 46);
INSERT INTO `category_detail` VALUES (2, 47);
INSERT INTO `category_detail` VALUES (2, 48);
INSERT INTO `category_detail` VALUES (2, 49);
INSERT INTO `category_detail` VALUES (2, 50);
INSERT INTO `category_detail` VALUES (2, 51);
INSERT INTO `category_detail` VALUES (2, 52);
INSERT INTO `category_detail` VALUES (2, 53);
INSERT INTO `category_detail` VALUES (2, 54);
INSERT INTO `category_detail` VALUES (2, 55);
INSERT INTO `category_detail` VALUES (2, 56);
INSERT INTO `category_detail` VALUES (2, 57);
INSERT INTO `category_detail` VALUES (2, 58);
INSERT INTO `category_detail` VALUES (2, 59);
INSERT INTO `category_detail` VALUES (2, 60);
INSERT INTO `category_detail` VALUES (2, 61);
INSERT INTO `category_detail` VALUES (2, 62);
INSERT INTO `category_detail` VALUES (2, 63);
INSERT INTO `category_detail` VALUES (2, 64);
INSERT INTO `category_detail` VALUES (2, 65);
INSERT INTO `category_detail` VALUES (2, 66);
INSERT INTO `category_detail` VALUES (2, 67);
INSERT INTO `category_detail` VALUES (2, 68);
INSERT INTO `category_detail` VALUES (2, 69);
INSERT INTO `category_detail` VALUES (2, 70);
INSERT INTO `category_detail` VALUES (2, 71);
INSERT INTO `category_detail` VALUES (2, 72);
INSERT INTO `category_detail` VALUES (2, 73);
INSERT INTO `category_detail` VALUES (2, 74);
INSERT INTO `category_detail` VALUES (2, 75);
INSERT INTO `category_detail` VALUES (2, 76);
INSERT INTO `category_detail` VALUES (2, 77);
INSERT INTO `category_detail` VALUES (2, 78);
INSERT INTO `category_detail` VALUES (3, 1);
INSERT INTO `category_detail` VALUES (3, 2);
INSERT INTO `category_detail` VALUES (3, 3);
INSERT INTO `category_detail` VALUES (3, 4);
INSERT INTO `category_detail` VALUES (3, 5);
INSERT INTO `category_detail` VALUES (3, 6);
INSERT INTO `category_detail` VALUES (3, 8);
INSERT INTO `category_detail` VALUES (3, 9);
INSERT INTO `category_detail` VALUES (3, 10);
INSERT INTO `category_detail` VALUES (3, 11);
INSERT INTO `category_detail` VALUES (3, 12);
INSERT INTO `category_detail` VALUES (3, 15);
INSERT INTO `category_detail` VALUES (3, 18);
INSERT INTO `category_detail` VALUES (3, 19);
INSERT INTO `category_detail` VALUES (3, 42);
INSERT INTO `category_detail` VALUES (3, 43);
INSERT INTO `category_detail` VALUES (3, 44);
INSERT INTO `category_detail` VALUES (3, 45);
INSERT INTO `category_detail` VALUES (3, 46);
INSERT INTO `category_detail` VALUES (3, 47);
INSERT INTO `category_detail` VALUES (3, 48);
INSERT INTO `category_detail` VALUES (3, 50);
INSERT INTO `category_detail` VALUES (3, 51);
INSERT INTO `category_detail` VALUES (3, 52);
INSERT INTO `category_detail` VALUES (3, 53);
INSERT INTO `category_detail` VALUES (3, 56);
INSERT INTO `category_detail` VALUES (3, 60);
INSERT INTO `category_detail` VALUES (4, 7);
INSERT INTO `category_detail` VALUES (4, 13);
INSERT INTO `category_detail` VALUES (4, 16);
INSERT INTO `category_detail` VALUES (4, 20);
INSERT INTO `category_detail` VALUES (4, 21);
INSERT INTO `category_detail` VALUES (4, 22);
INSERT INTO `category_detail` VALUES (4, 23);
INSERT INTO `category_detail` VALUES (4, 24);
INSERT INTO `category_detail` VALUES (4, 25);
INSERT INTO `category_detail` VALUES (4, 26);
INSERT INTO `category_detail` VALUES (4, 28);
INSERT INTO `category_detail` VALUES (4, 29);
INSERT INTO `category_detail` VALUES (4, 30);
INSERT INTO `category_detail` VALUES (4, 32);
INSERT INTO `category_detail` VALUES (4, 41);
INSERT INTO `category_detail` VALUES (4, 49);
INSERT INTO `category_detail` VALUES (4, 54);
INSERT INTO `category_detail` VALUES (4, 55);
INSERT INTO `category_detail` VALUES (4, 57);
INSERT INTO `category_detail` VALUES (4, 58);
INSERT INTO `category_detail` VALUES (4, 59);
INSERT INTO `category_detail` VALUES (4, 61);
INSERT INTO `category_detail` VALUES (4, 62);
INSERT INTO `category_detail` VALUES (4, 63);
INSERT INTO `category_detail` VALUES (4, 64);
INSERT INTO `category_detail` VALUES (4, 65);
INSERT INTO `category_detail` VALUES (4, 66);
INSERT INTO `category_detail` VALUES (4, 67);
INSERT INTO `category_detail` VALUES (4, 68);
INSERT INTO `category_detail` VALUES (4, 69);
INSERT INTO `category_detail` VALUES (4, 70);
INSERT INTO `category_detail` VALUES (4, 73);
INSERT INTO `category_detail` VALUES (4, 74);
INSERT INTO `category_detail` VALUES (4, 75);
INSERT INTO `category_detail` VALUES (4, 76);
INSERT INTO `category_detail` VALUES (4, 77);
INSERT INTO `category_detail` VALUES (4, 78);
INSERT INTO `category_detail` VALUES (5, 33);
INSERT INTO `category_detail` VALUES (5, 35);
INSERT INTO `category_detail` VALUES (5, 40);
INSERT INTO `category_detail` VALUES (6, 5);
INSERT INTO `category_detail` VALUES (6, 6);
INSERT INTO `category_detail` VALUES (6, 13);
INSERT INTO `category_detail` VALUES (6, 47);
INSERT INTO `category_detail` VALUES (6, 49);
INSERT INTO `category_detail` VALUES (6, 55);

-- ----------------------------
-- Table structure for color
-- ----------------------------
DROP TABLE IF EXISTS `color`;
CREATE TABLE `color`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `isDeleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of color
-- ----------------------------
INSERT INTO `color` VALUES (1, 'Trắng', 0);
INSERT INTO `color` VALUES (2, 'Đen', 0);
INSERT INTO `color` VALUES (3, 'Hồng', 0);
INSERT INTO `color` VALUES (4, 'Xanh dương', 0);
INSERT INTO `color` VALUES (5, 'Xanh lá', 0);
INSERT INTO `color` VALUES (6, 'Vàng', 0);
INSERT INTO `color` VALUES (7, 'Nâu', 0);
INSERT INTO `color` VALUES (8, 'Đỏ', 0);

-- ----------------------------
-- Table structure for image_product
-- ----------------------------
DROP TABLE IF EXISTS `image_product`;
CREATE TABLE `image_product`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_product` int NOT NULL,
  `isDeleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `id_product`) USING BTREE,
  INDEX `i_p`(`id_product` ASC) USING BTREE,
  CONSTRAINT `i_p` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of image_product
-- ----------------------------
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2023/05/cau-tao-anh-2-380x380.jpg', 1, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/01/13664b915b-380x380.jpg', 2, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2021/12/giay-patin-micro-delta-junior-mau-xanh-anh-2-380x380.jpg', 3, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/10/Giày-Patin-Micro-DELTA-X-Hồng-380x380.jpg', 4, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-toc-do-micro-dynamic-speed-junior-3-banh-mau-xanh-anh-2-380x380.jpg', 5, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-toc-do-micro-dynamic-speed-junior-4-banh-mau-hong.jpg', 6, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/12/drift-junior-blue-380x380.jpg', 7, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-micro-infinite-ce-mau-xanh-duong-380x380.jpg', 8, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-micro-infinite-se-thuong-hieu-thuy-si-380x380.jpg', 9, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2021/10/giay-patin-micro-infiniti-mau-vang-380x380.jpg', 10, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2020/05/giay-micro-duality-mau-luc-380x380.jpg', 11, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/mau-sac-noi-bat-thu-hut-xpatin-380x380.jpg', 12, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Giầy-Patin-Flying-Eagle-S6S-Xanh-380x380.jpg', 13, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2021/06/giay-patin-thex-elite-mau-hong-380x380.jpg', 14, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Giày-Patin-Micro-Discovery-Hồng-380x380.jpg', 15, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-flying-eagle-s7-mau-hong-380x380.jpg', 16, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2021/06/giay-patin-thex-atom-mau-den.jpg', 17, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2020/05/giay-micro-shaper-xanh-duong-550x537.jpg', 18, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2020/05/giay-micro-mj-mau-vang.jpg', 19, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2021/09/giay-truot-patin-flying-eagle-s8-mau-den-550x550.jpg', 20, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2023/03/giay-patin-flying-eagle-l8-mau-xanh-duong-380x380.jpg', 21, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/02/giay-patin-flying-eagle-s9-mau-xanh-380x380.jpg', 22, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Giày-Patin-Flying-Eagle-NT-Combo-Xanh-380x380.jpg', 23, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2020/01/giay-patin-flying-eagle-s6t-s6-tripple.jpg', 24, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2021/07/giay-truot-patin-flying-eagle-s6s-mau-hong-380x380.jpg', 25, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2021/05/giay-patin-flying-eagle-s5s-new-mau-xanh-1-380x380.jpg', 26, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/09/giay-patin-lakoko-mau-do-550x550.jpg', 27, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Giầy-Patin-Flying-Eagle-S6S-Xanh-380x380.jpg', 28, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2020/10/giay-patin-flying-eagle-k8-den-2-380x380.jpg', 29, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Giầy-Patin-Flying-Eagle-S5S-Đen-Đỏ-380x380.jpg', 30, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2021/04/giay-patin-sk-2-xanh-380x380.jpg', 31, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-flying-eagle-k7-hong-380x380.jpg', 32, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%C3%A0y-Patin-SOFT-C3-%C4%90en.jpg', 33, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2020/09/giay-patin-iskate-fb22-hong-anh-2-380x380.jpg', 34, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%C3%A0y-Patin-SOFT-C2-Xanh.jpg', 35, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-tre-em-iskate-ichi-mau-hong-380x380.jpg', 36, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2021/04/giay-patin-kf-1-hong-380x380.jpg', 37, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2020/10/giay-patin-iskate-arrow-do-380x380.jpg', 38, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/12/giay-truot-patin-papaison-chat-luong-tot-380x380.jpg', 39, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Giày-Patin-SOFT-A1-Hồng-380x380.jpg', 40, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/11/giay-patin-flying-eagle-s5s-xpatin4-380x380.jpg', 41, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/08/giay-patin-micro-d3lta.jpg', 42, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2023/05/giay-patin-micro-delta-force-ii-silver.jpg', 43, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/08/giay-patin-cao-cap-micro-delta-force-jet-anh-2-380x380.jpg', 44, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2023/09/giay-patin-micro-delta-hml-pro-anh-3-380x380.jpg', 45, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/10/Gi%C3%A0y-Patin-Micro-DELTA-F-anh6-2.jpg', 46, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-dynamic-speed-thuong-hieu-thuy-si.jpg', 47, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2021/12/giay-patin-micro-hml.jpg', 48, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-toc-do-speed-flying-eagle-leader-anh-4-380x380.jpg', 49, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/10/Micro-DELTA-Pro-anh-7-380x380.jpg', 50, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2023/03/giay-patin-micro-delta-plus-co-carbon.jpg', 51, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/01/giay-patin-micro-delta-liberty-mau-bac.jpg', 52, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-micro-beat.jpg', 53, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/10/FLYING-EAGLE-DRIFT-2.0-ANH-2-380x380.jpg', 54, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-toc-do-speed-flying-eagle-phantom-anh-2.jpg', 55, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/11/giay-patin-micro-sr-red.jpg', 56, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%C3%A0y-Patin-Flying-Eagle-F110H.jpg', 57, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2020/12/giay-enkidu-min.jpg', 58, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%C3%A0y-Patin-Flying-Eagle-F7.jpg', 59, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/giay-patin-micro-mt-plus-mau-do-380x380.jpg', 60, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%C3%A0y-Patin-Flying-Eagle-F6S-%C4%90%E1%BB%8F.jpg', 61, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/giay-patin-flying-eagle-f5s-eclipse-380x380.jpg', 62, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/giay-patin-flying-eagle-f5s-eclipse-xpatin1-1-380x380.jpg', 63, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%C3%A0y-Patin-Flying-Eagle-F4-%C4%90en.jpg', 64, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%C3%A0y-Patin-Flying-Eagle-F3S-%C4%90en.jpg', 65, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2020/08/giay-patin-flying-eagle-f3-dragonfly-den-380x380.jpg', 66, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/giay-patin-flying-eagle-f2s-sphinx-mau-hong-xpatin-380x380.jpg', 67, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%E1%BA%A7y-Patin-Flying-Eagle-B5S-%C4%90en.jpg', 68, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2021/07/giay-patin-flying-eagle-f1s-mantra-moi.jpg\'', 69, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%E1%BA%A7y-Patin-Flying-Eagle-FBS.jpg', 70, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2022/09/giay-patin-calary-c9-mau-trang-min-380x380.jpg', 71, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2020/09/giay-patin-iskate-ranger-gia-re-380x380.jpg', 72, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/09/giay-truot-patin-flying-eagle-f1s-mau-hong-xpatin1-380x380.jpg', 73, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-flying-eagle-f6.jpg', 74, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-flying-eagle-f5.jpg', 75, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-flying-eagle-f3.jpg', 76, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-flying-eagle-f2.jpg', 77, 0);
INSERT INTO `image_product` VALUES (1, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-flying-eagle-f1-anh-2-380x380.jpg', 78, 0);
INSERT INTO `image_product` VALUES (1, 'data/product/id110/mercury-sky-night-background-asset-game-2d-futuristic-generative-ai.jpg', 110, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2023/05/cau-tao-anh-1-380x380.jpg', 1, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/01/525e5cf1ed-380x380.jpg', 2, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2021/12/delta-junior-la-mau-giay-truot-rat-nhay-380x380.jpg', 3, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/10/Giày-Patin-Micro-DELTA-X-380x380.jpg', 4, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-toc-do-micro-dynamic-speed-junior-3-banh-380x380.jpg', 5, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-toc-do-micro-dynamic-speed-junior-mau-hong-380x380.jpg', 6, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/12/drift-junior-gorl-380x380.jpg', 7, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-micro-infinite-ce-mau-hong-380x380.jpg', 8, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/07/khoa-ban-tu-dong-tinh-te-de-dang-thao-tac-380x380.jpg', 9, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2021/10/giay-patin-micro-infiniti-mau-trang-380x380.jpg', 10, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2020/05/giay-micro-duality-mau-xanh.jpg', 11, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/Giày-Patin-Micro-LE-Hồng-380x380.jpg', 12, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2021/06/giay-patin-thex-elite-mau-xanh-380x380.jpg', 14, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/Giày-Patin-Micro-Discovery-Xanh-380x380.jpg', 15, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-flying-eagle-s7-mau-den-380x380.jpg', 16, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2021/06/giay-patin-thex-atom-mau-cam-550x550.jpg', 17, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2020/05/giay-micro-shaper-do-380x380.jpg', 18, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2020/05/giay-micro-mj-mau-hong.jpg', 19, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2021/09/thiet-ke-sang-trong-hien-dai-380x380.jpg', 20, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2023/03/giay-patin-flying-eagle-l8-mau-hong-380x380.jpg', 21, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/02/giay-patin-flying-eagle-s9-mau-do-380x380.jpg', 22, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/Giày-Patin-Flying-Eagle-NT-Combo-Hồng-380x380.jpg', 23, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2021/07/giay-truot-patin-flying-eagle-s6s-mau-den-380x380.jpg', 25, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2021/05/giay-patin-flying-eagle-s5s-new-mau-hong-1-380x380.jpg', 26, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/09/giay-patin-lakoko-mau-hong-380x380.jpg', 27, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/Giầy-Patin-Flying-Eagle-S6S-Hồng-380x380.jpg', 28, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2020/10/giay-patin-flying-eagle-k8-xanh-2.jpg', 29, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/Giầy-Patin-Flying-Eagle-S5S-Hồng-380x380.jpg', 30, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2021/04/giay-patin-papaya-sk2-new-mau-xanh-380x380.jpg', 31, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-flying-eagle-k7-xanh-duong-380x380.jpg', 32, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%C3%A0y-Patin-SOFT-C3-H%E1%BB%93ng-380x380.jpg', 33, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2020/09/giay-patin-iskate-fb22-hong-380x380.jpg', 34, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%C3%A0y-Patin-SOFT-C2-H%E1%BB%93ng.jpg', 35, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/12/giay-iskate-ichi-xanh-380x380.jpg', 36, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2021/04/giay-patin-kf-1-xanh-380x380.jpg', 37, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2020/10/giay-patin-iskate-arrow-do-2-380x380.jpg', 38, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-papaison-do-anh-2-380x380.jpg', 39, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/Giày-Patin-SOFT-A1-Đen-380x380.jpg', 40, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/11/giay-patin-flying-eagle-s5s-xpatin5-380x380.jpg', 41, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2023/05/giay-patin-micro-delta-force-ii-black-380x380.jpg', 43, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/08/giay-patin-cao-cap-micro-delta-force-jet-anh-3-380x380.jpg', 44, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2023/09/giay-patin-micro-delta-hml-pro-anh-4-380x380.jpg', 45, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-dynamic-speed-mau-trang-380x380.jpg', 47, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2021/12/giay-patin-micro-hml-cao-cap-thuong-hieu-thuy-si.jpg', 48, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-toc-do-speed-flying-eagle-leader-380x380.jpg', 49, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/10/Micro-DELTA-Pro-anh2.jpg', 50, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/01/giay-patin-micro-delta-liberty-anh-2.jpg', 52, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-micro-beat-speed-380x380.jpg', 53, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/10/FLYING-EAGLE-DRIFT-2.0-ANH-4-380x380.jpg', 54, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-toc-do-speed-flying-eagle-phantom-anh-4-380x380.jpg', 55, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/11/giay-patin-micro-sr-blue.jpg', 56, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2020/01/giay-patin-flying-eagle-f7-optimum-2020-xpatin1.jpg', 59, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/giay-patin-flying-eagle-f6s-falcon-green-xpatin2.jpg', 61, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2020/08/giay-patin-flying-eagle-f3-dragonfly-hong-380x380.jpg', 66, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/giay-patin-flying-eagle-f2s-sphinx-mau-hong-xpatin1-380x380.jpg', 67, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/Giầy-Patin-Flying-Eagle-B5S-Hồng-380x380.jpg', 68, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2022/09/giay-patin-calary-c9-mau-xanh-anh-2-min-380x380.jpg', 71, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2020/09/giay-patin-iskate-ranger-cho-nguoi-lon-gia-re-380x380.jpg', 72, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/09/giay-truot-patin-flying-eagle-f1s-mau-hong-xpatin2-380x380.jpg', 73, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-flying-eagle-f6-anh-2.jpg', 74, 0);
INSERT INTO `image_product` VALUES (2, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-flying-eagle-f2-anh-2.jpg', 77, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2023/05/thiet-ke-hop-dung-giay-patin-micro-delta-x-2023-380x380.jpg', 1, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2022/01/c057c9f26e-min-380x380.jpg', 2, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2021/12/co-giay-composite-380x380.jpg', 3, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-toc-do-micro-dynamic-speed-junior-mau-hong-380x380.jpg', 5, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-dynamic-speed-thuong-hieu-thuy-si-380x380.jpg', 6, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-flying-eagle-f6-380x380.jpg', 7, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-micro-infinite-se-mau-hong-380x380.jpg', 9, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/09/Giày-Patin-Micro-Discovery-Đen-380x380.jpg', 10, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/10/Micro-DELTA-Pro-1-380x380.jpg', 11, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2020/05/giay-micro-mj-mau-xanh.jpg', 19, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2021/09/giay-truot-patin-flying-eagle-s8-mau-hong-anh-2-380x380.jpg', 20, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2023/03/giay-patin-flying-eagle-l8-mau-den-550x550.jpg', 21, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2022/02/giay-patin-flying-eagle-s9-mau-hong-550x550.jpg', 22, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2021/07/giay-truot-patin-flying-eagle-s6s-mau-xanh-550x550.jpg', 25, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2021/05/giay-patin-flying-eagle-s5s-new-mau-den-1-380x380.jpg', 26, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/09/Giầy-Patin-Flying-Eagle-S6S-Đen-380x380.jpg', 28, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2020/10/giay-patin-flying-eagle-k8-hong-4-380x380.jpg', 29, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%E1%BA%A7y-Patin-Flying-Eagle-S5S-Xanh-550x550.jpg', 30, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2021/04/giay-patin-papaya-sk2-new-mau-hong-380x380.jpg', 31, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-flying-eagle-k7-den-550x550.jpg', 32, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2020/09/giay-patin-iskate-fb22-xanh-380x380.png', 34, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2020/10/giay-patin-iskate-arrow-hong-2-380x380.jpg', 38, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/11/giay-patin-flying-eagle-s5s-xpatin6-380x380.jpg', 41, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2023/05/b68eaf1962-min-380x380.jpg', 43, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2023/09/giay-patin-micro-delta-hml-pro-anh-2-380x380.jpg', 45, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-dynamic-speed-mau-trang-anh-3-380x380.jpg', 47, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-toc-do-speed-flying-eagle-leader-anh-5-380x380.jpg', 49, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2022/01/3faeca51ee.jpg', 52, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/11/giay-patin-micro-sr-white.jpg', 56, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2020/01/giay-patin-flying-eagle-f7-optimum-2020-xpatin.jpg', 59, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/09/giay-patin-micro-mt-plus-mau-cherry-380x380.jpg', 60, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2020/08/giay-patin-flying-eagle-f3-dragonfly-hong-2-380x380.jpg', 66, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/09/giay-patin-flying-eagle-f2s-sphinx-mau-hong-xpatin2-380x380.jpg', 67, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/09/giay-patin-flying-eagle-b5s-mau-hong-xpatin-380x380.jpg', 68, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2022/09/giay-patin-calary-c9-mau-trang-anh-2-min-380x380.jpg', 71, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2020/09/giay-patin-iskate-ranger-mau-den-380x380.jpg', 72, 0);
INSERT INTO `image_product` VALUES (3, 'https://xpatin.com/wp-content/uploads/2019/09/Gi%E1%BA%A7y-Patin-Flying-Eagle-F1S-%C4%90en.jpg', 73, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2023/05/giay-patin-micro-delta-x-2023-mau-hong-380x380.jpg', 1, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2022/01/giay-patin-micro-delta-liberty-mau-bac-380x380.jpg', 2, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2021/12/giay-patin-micro-delta-junior-mau-hong-380x380.jpg', 3, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-toc-do-micro-dynamic-speed-junior-3-banh-mau-hong.jpg', 5, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2023/05/giay-patin-micro-delta-x-2023-mau-xanh-380x380.jpg', 6, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-micro-infinite-se-mau-xanh-380x380.jpg', 9, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2021/09/giay-truot-patin-flying-eagle-s8-mau-hong-380x380.jpg', 20, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2021/05/giay-patin-flying-eagle-s5s-new-mau-xanh-1-380x380.jpg', 26, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2019/09/Giầy-Patin-Flying-Eagle-S6S-Xanh-380x380.jpg', 28, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2020/10/giay-patin-flying-eagle-k8-hong-380x380.jpg', 29, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2021/04/giay-patin-sk-2-xanh-anh-3-380x380.jpg', 31, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2020/10/giay-patin-iskate-arrow-den-380x380.jpg', 38, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2019/11/giay-patin-flying-eagle-s5s-mau-xanh-380x380.jpg', 41, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2023/09/giay-patin-micro-delta-hml-pro-anh-3-380x380.jpg', 45, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-toc-do-speed-flying-eagle-leader-anh-3-380x380.jpg', 49, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2022/01/13664b915b.jpg', 52, 0);
INSERT INTO `image_product` VALUES (4, 'https://xpatin.com/wp-content/uploads/2020/09/giay-patin-iskate-ranger-mau-xanh-anh-2-380x380.jpg', 72, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2023/05/giay-patin-micro-delta-x-2023-mau-xanh-anh-2-380x380.jpg', 1, 1);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2022/01/giay-patin-micro-delta-liberty-thuong-hieu-thuy-si-380x380.jpg', 2, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2021/12/duoc-tao-thanh-boi-cong-nghe-tot-nhat-380x380.jpg', 3, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2022/07/giay-patin-toc-do-micro-dynamic-speed-junior-3-banh-mau-xanh-anh-2-380x380.jpg', 5, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2021/05/giay-patin-flying-eagle-s5s-new-mau-hong-1-380x380.jpg', 26, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2019/09/Giầy-Patin-Flying-Eagle-S6S-Hồng-380x380.jpg', 28, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2020/10/giay-patin-flying-eagle-k8-xanh-380x380.jpg', 29, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2021/04/giay-patin-sk-2-hong-anh-2-380x380.jpg', 31, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2020/10/giay-patin-iskate-arrow-hong-380x380.jpg', 38, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2019/12/giay-patin-toc-do-speed-flying-eagle-leader-anh-4-380x380.jpg', 49, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2022/01/giay-patin-micro-delta-liberty-thuong-hieu-thuy-si.jpg', 52, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2019/09/giay-patin-micro-mt-plus-mau-xanh-duong-380x380.jpg', 60, 0);
INSERT INTO `image_product` VALUES (5, 'https://xpatin.com/wp-content/uploads/2019/09/giay-patin-flying-eagle-f2s-sphinx-mau-hong-xpatin3-380x380.jpg', 67, 0);

-- ----------------------------
-- Table structure for image_rating
-- ----------------------------
DROP TABLE IF EXISTS `image_rating`;
CREATE TABLE `image_rating`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rating_id` int NOT NULL,
  PRIMARY KEY (`id`, `rating_id`) USING BTREE,
  INDEX `r_r`(`rating_id` ASC) USING BTREE,
  CONSTRAINT `r_r` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of image_rating
-- ----------------------------

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `preValue` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `value` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `createAt` datetime NULL DEFAULT NULL,
  `updateAt` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 384 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (374, '0:0:0:0:0:0:0:1', 'info', 'login', NULL, 'email=21130035@st.hcmuaf.edu.vn, fullName=null, phone=null\nStatus: Thành công', '2024-07-16 12:56:38', NULL);
INSERT INTO `log` VALUES (375, '0:0:0:0:0:0:0:1', 'info', 'add product', NULL, 'name=Sản phẩm 1, salePercent=0.2', '2024-07-16 13:07:37', NULL);
INSERT INTO `log` VALUES (376, '0:0:0:0:0:0:0:1', 'info', 'add productDetail', NULL, 'productName=Sản phẩm 1,Size=Size{id=1, name=\'\', isDeleted=0}, color=Color{id=2, name=\'\', isDeleted=0}, quantity=1, price=200000', '2024-07-16 13:07:37', NULL);
INSERT INTO `log` VALUES (377, '0:0:0:0:0:0:0:1', 'info', 'login', NULL, 'email=21130035@st.hcmuaf.edu.vn, fullName=null, phone=null\nStatus: Thành công', '2024-07-16 14:24:48', NULL);
INSERT INTO `log` VALUES (378, '0:0:0:0:0:0:0:1', 'info', 'user mua hang', NULL, 'name=DH28D2024-07-16T14:25:02.109209900, date=2024-07-16T14:25:02.109209900, status=0\nuserName=Nguyễn Ngọc Khánh Duy, userEmail=21130035@st.hcmuaf.edu.vn, userPhone=0839151003', '2024-07-16 14:25:02', NULL);
INSERT INTO `log` VALUES (379, '0:0:0:0:0:0:0:1', 'info', 'user mua hang', NULL, 'name=DH28D2024-07-16T14:26:26.557484300, date=2024-07-16T14:26:26.557484300, status=0\nuserName=Nguyễn Ngọc Khánh Duy, userEmail=21130035@st.hcmuaf.edu.vn, userPhone=0839151003', '2024-07-16 14:26:26', NULL);
INSERT INTO `log` VALUES (380, '0:0:0:0:0:0:0:1', 'alert', '21130035@st.hcmuaf.edu.vn: change status bill', 'name=DH28D2024-07-16T14:25:02.109209900, date=2024-07-16T14:25:02, status=0\nuserName=Nguyễn Ngọc Khánh Duy, userEmail=21130035@st.hcmuaf.edu.vn, userPhone=0839151003', 'name=DH28D2024-07-16T14:25:02.109209900, date=2024-07-16T14:25:02, status=2\nuserName=Nguyễn Ngọc Khánh Duy, userEmail=21130035@st.hcmuaf.edu.vn, userPhone=0839151003', NULL, '2024-07-16 14:27:49');
INSERT INTO `log` VALUES (381, '0:0:0:0:0:0:0:1', 'info', 'login google', NULL, 'email=lomninkon@gmail.com, fullName=Lom Nin, phone=null\nStatus: Thành công', '2024-07-16 14:28:57', NULL);
INSERT INTO `log` VALUES (382, '0:0:0:0:0:0:0:1', 'alert', 'login', NULL, 'email=21130035@st.hcmuaf.edu.vn, fullName=null, phone=null\nStatus: Thất bại', '2024-07-16 14:29:27', NULL);
INSERT INTO `log` VALUES (383, '0:0:0:0:0:0:0:1', 'info', 'login', NULL, 'email=21130035@st.hcmuaf.edu.vn, fullName=null, phone=null\nStatus: Thành công', '2024-07-16 14:29:41', NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `hot` int NULL DEFAULT NULL,
  `sale_percent` double NULL DEFAULT NULL,
  `information` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `isDeleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'Giày Patin Micro Delta X 2023', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Hồng, Xanh dương%Kích cỡ:27, 30, 32%Đối tượng:Trẻ em%Khả năng điều chỉnh:Thay đổi được 3 kích cỡ khác nhau, rộng hơn khi chân trẻ to ra!%Khả năng trượt:Bánh xe 72mm, Bánh xe 76mm, Vòng bi Slalom PRO9%Độ bám:Bánh xe', 0);
INSERT INTO `product` VALUES (2, 'Giày Patin Micro DELTA LIBERTY', 0, 0.2, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc: Trắng%Kích cỡ:27, 32%Đối tượng:Người lớn, Trẻ em%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh, Size kép (2 kích cỡ)%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 64mm-68mm, Bánh ', 0);
INSERT INTO `product` VALUES (3, 'Giày Patin Micro Delta Junior', 0, 0.25, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Hồng, Xanh dương%Kích cỡ:29, 30, 33, 35%Đối tượng:Trẻ em%Khả năng điều chỉnh:Thay đổi được 3 kích cỡ khác nhau, rộng hơn khi chân trẻ to ra!%Khả năng trượt:ABEC7 PRO, Bánh xe 72mm, Bánh xe 76mm%Độ bám:Bánh xe cao su', 0);
INSERT INTO `product` VALUES (4, 'Giày Patin Micro DELTA X', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Hồng, Xanh dương%Kích cỡ:27, 29, 30%Đối tượng:Trẻ em%Khả năng điều chỉnh:Thay đổi được 3 kích cỡ khác nhau, rộng hơn khi chân trẻ to ra!%Khả năng trượt:Bánh xe 72mm, Bánh xe 76mm, Vòng bi ABEC9%Độ bám:Bánh xe cao su', 0);
INSERT INTO `product` VALUES (5, 'Giày Patin tốc độ Micro Dynamic Speed Junior (3 bánh', 0, 0.3, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Hồng, Xanh dương%Kích cỡ:27%Đối tượng:Trẻ em%Khả năng điều chỉnh:Thay đổi được 3 kích cỡ khác nhau, rộng hơn khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 100mm, Twinca', 0);
INSERT INTO `product` VALUES (6, 'Giày Patin tốc độ Micro Dynamic Speed Junior (4 bánh', 0, 0.5, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Hồng, Xanh dương%Kích cỡ:27, 30, 33%Đối tượng:Trẻ em%Khả năng điều chỉnh:Thay đổi được 3 kích cỡ khác nhau, rộng hơn khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 100mm', 0);
INSERT INTO `product` VALUES (7, 'Flying Eagle Drift Junior', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc: Đỏ Vàng, Xanh dương%Kích cỡ:27, 29, 30%Đối tượng:Trẻ em%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Khung càng - Frames:Nhôm nguyên khối CNC%Khả năng trượt:Bánh xe 68mm-70mm,', 0);
INSERT INTO `product` VALUES (8, 'Giày Patin Micro INFINITE CE', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Hồng, Xanh dương%Kích cỡ:27, 29%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Trẻ em%Khả năng trượt:Bánh x', 0);
INSERT INTO `product` VALUES (9, 'Giày Patin Micro INFINITE SE', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Hồng, Xanh dương%Kích cỡ:27, 30, 33%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Trẻ em%Khả năng trượt:Bá', 0);
INSERT INTO `product` VALUES (10, 'Giày Patin Micro INFINITE', 0, 0.25, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đen, Trắng, Vàng%Kích cỡ:27, 29, 30%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Trẻ em%Khả năng trượt:Bá', 0);
INSERT INTO `product` VALUES (11, 'Giày Patin Micro DUALITY', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đen, Xanh dương, Xanh lá%Kích cỡ:27, 29, 30%Đối tượng:Trẻ em%Khóa giày:Khóa cổ giày: khóa đóng nhựa dẻo, Khóa thân giày: khóa dán%Khung càng - Frames:Nhôm nguyên khối CNC%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tố', 0);
INSERT INTO `product` VALUES (12, 'Giày Patin Micro LE', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Hồng, Xanh dương%Kích cỡ:27, 29, 30%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Trẻ em%Khả năng trượt:Bá', 0);
INSERT INTO `product` VALUES (13, 'Giày Patin Flying Eagle S6S Speed', 0, 0.3, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Kích cỡ:30%Khả năng điều chỉnh:Điều chỉnh được 5 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Trẻ em%Khả năng trượt:Bánh xe 100mm, Vòng bi FLY', 0);
INSERT INTO `product` VALUES (14, 'Giày Patin TheX ELITE', 0, 0, 'Thương hiệu:THEX%Màu sắc:Hồng, Xanh dương%Kích cỡ:30%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Trẻ em%Khả năng trượt:Bánh xe 64mm-68mm, Bá', 0);
INSERT INTO `product` VALUES (15, 'Giày Patin Micro Discovery', 0, 0.5, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Hồng, Xanh dương%Kích cỡ:27, 29%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Trẻ em%Khả năng trượt:Bánh x', 0);
INSERT INTO `product` VALUES (16, 'Giày patin Flying Eagle S7', 0, 0, 'Màu sắc:Đen, Hồng%Kích cỡ:27, 29', 0);
INSERT INTO `product` VALUES (17, 'Giày Patin TheX ATOM', 0, 0.25, 'Thương hiệu:THEX%Màu sắc:Đỏ, Đen%Kích cỡ:30, 33%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Trẻ em%Khả năng trượt:Bánh xe 64mm-68mm, Bánh xe', 0);
INSERT INTO `product` VALUES (18, 'Giày Patin Micro SHAPER', 0, 0.4, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đỏ, Đen%Kích cỡ:27, 30, 33%Đối tượng:Trẻ em%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Phanh (thắng):Có ở chân giày phải%Thân giày:Vải sợi tổng hợp cao cấp%Khung c', 0);
INSERT INTO `product` VALUES (19, 'Giày Patin Micro MJ', 0, 0.25, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Hồng, Vàng, Xanh dương%Kích cỡ:29, 30%Đối tượng:Trẻ em%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Vòng bi ABEC5%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi châ', 0);
INSERT INTO `product` VALUES (20, 'Giày patin Flying Eagle S8', 0, 0.4, 'Màu sắc:Đen, Hồng, Xanh dương%Kích cỡ:27, 29, 30', 0);
INSERT INTO `product` VALUES (21, 'Giày patin Flying Eagle L8 Firefly (full 8 bánh đèn)', 0, 0, 'Màu sắc:Đen, Hồng, Xanh dương%Kích cỡ:27, 29', 0);
INSERT INTO `product` VALUES (22, 'Giày Patin Flying Eagle S9 Dynamo', 0, 0.25, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đỏ, Hồng, Xanh dương%Kích cỡ:27, 29, 30%Đối tượng:Trẻ em%Thân giày:Khuôn vỏ nhựa chịu lực tốt, khả năng chống va đập cao%Khả năng điều chỉnh:Điều chỉnh được 5 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Kh', 0);
INSERT INTO `product` VALUES (23, 'Giày Patin Flying Eagle NT Combo', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Hồng, Xanh dương%Kích cỡ:27, 30, 33%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Trẻ em%Khả năng trượt:Bánh ', 0);
INSERT INTO `product` VALUES (24, 'Giày Patin Flying Eagle S6T', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen, Hồng, Xanh dương%Kích cỡ:S (28-32), M (33-37), L (38-42)%Đối tượng:Trẻ em%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng điều chỉnh:Điều chỉnh được 5 cỡ% Có thể nới rộng giày Trượt khi chân trẻ t', 0);
INSERT INTO `product` VALUES (25, 'Giày Patin Flying Eagle S6S+', 1, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen, Hồng, Xanh dương%Kích cỡ:S (28-32), M (33-37), L (38-42)%Đối tượng:Trẻ em%Thân giày:Khuôn vỏ nhựa chịu lực tốt, khả năng chống va đập cao%Khả năng điều chỉnh:Điều chỉnh được 5 cỡ% Có thể nới rộng giày Trượt k', 0);
INSERT INTO `product` VALUES (26, 'Giày Patin Flying Eagle S5S+ New', 1, 0.4, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen, Hồng, Xanh dương%Kích cỡ:S (28-32), M (33-37), L (38-42)%Đối tượng:Trẻ em%Thân giày:Khuôn vỏ nhựa chịu lực tốt, khả năng chống va đập cao%Khả năng điều chỉnh:Điều chỉnh được 5 cỡ% Có thể nới rộng giày Trượt k', 0);
INSERT INTO `product` VALUES (27, 'Giày Patin Lakoko', 0, 0.2, 'Màu sắc:Đỏ, Hồng%Kích cỡ:S (29-33), M (34-38)%Đối tượng:Trẻ em%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Khả năng trượt:Vòng bi ABEC7%Khóa giày:Khóa', 0);
INSERT INTO `product` VALUES (28, 'Giày Patin Flying Eagle S6S ', 0, 0.2, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen, Hồng, Xanh dương%Kích cỡ:S (28-32), M (33-37), L (38-42)%Khả năng điều chỉnh:Điều chỉnh được 5 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:T', 0);
INSERT INTO `product` VALUES (29, 'Giày Patin Flying Eagle BKB K8', 1, 0.3, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen, Hồng, Xanh dương%Kích cỡ:S (28-31), M (32-35), L (36-39)%Đối tượng:Trẻ em%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Thân giày:Khuôn vỏ nhựa chịu lực tốt, khả nă', 0);
INSERT INTO `product` VALUES (30, 'Giày Patin Flying Eagle S5S', 0, 0.25, 'Thương hiệu:FLYING EAGLE%Màu sắc:Hồng, Đen đỏ, Xanh dương%Kích cỡ:S (28-32), M (33-37), L (38-42)%Khả năng điều chỉnh:Điều chỉnh được 5 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượn', 0);
INSERT INTO `product` VALUES (31, 'Giày Patin PAPAYA SK2 (2 màu', 0, 0, 'Kích cỡ:M (34-37), S (30-33), XS (26-29)%Màu sắc:Hồng, Xanh dương%Khóa giày:Khóa cổ giày: khóa đóng nhựa dẻo, Khóa thân giày: khóa cài nhựa dẻo%Đối tượng:Trẻ em%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to', 0);
INSERT INTO `product` VALUES (32, 'Giày Patin Flying Eagle K7', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen, Hồng, Xanh dương%Kích cỡ:S (28-31), M (32-35), L (36-39)%Đối tượng:Trẻ em%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng điều chỉnh:Điều chỉnh được 5 cỡ% Có thể nới rộng giày Trượt khi chân trẻ t', 0);
INSERT INTO `product` VALUES (33, 'Giày Patin SOFT C3', 0, 0, 'Thương hiệu:SOFT%Màu sắc:Đen, Hồng%Kích cỡ:S (28-31), M (32-35), L (36-39)%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Đối tượng:Trẻ em%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt', 0);
INSERT INTO `product` VALUES (34, 'Giày Patin iSkate FB22', 0, 0.25, 'Màu sắc:Hồng, Xanh dương%Kích cỡ:L (38-41), M (34-37)%Đối tượng:Trẻ em%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Khả năng trượt:Bánh xe 70mm-72mm, Bánh xe 72mm-76mm, Vòng bi ABEC7%Phanh (thắng):Chất', 0);
INSERT INTO `product` VALUES (35, 'Giày Patin SOFT C2', 0, 0, 'Thương hiệu:SOFT%Màu sắc:Hồng, Xanh dương%Kích cỡ:S (28-31), M (32-35), L (36-39)%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Trẻ em%Khả năn', 0);
INSERT INTO `product` VALUES (36, 'Giày Patin iSkate ICHI', 0, 0, 'Màu sắc:Đen, Hồng, Xanh dương%Kích cỡ:S (31-34), M (35-38)%Đối tượng:Trẻ em%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Khả năng trượt:Bánh xe 64mm-68mm, Bánh xe 68mm-70mm%Khóa giày:Khóa cổ giày: khóa', 0);
INSERT INTO `product` VALUES (37, 'Giày Patin CHAMTPION KF-1 ', 0, 0.4, 'Màu sắc:Hồng, Xanh dương%Kích cỡ:M (34-37), S (30-33)%Đối tượng:Trẻ em%Khả năng điều chỉnh:Thay đổi được 3 kích cỡ khác nhau, rộng hơn khi chân trẻ to ra!%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khóa giày:Khóa cổ giày: khóa đóng nhựa dẻo, Kh', 0);
INSERT INTO `product` VALUES (38, 'Giày Patin iSkate ARROW ', 1, 0, 'Thương hiệu:KHÁC%Màu sắc:Đỏ, Đen, Hồng%Kích cỡ:M (35-38), L (39-42)%Đối tượng:Trẻ em%Thân giày:Khuôn vỏ nhựa chịu lực tốt, khả năng chống va đập cao%Khóa giày:Khóa cổ giày: khóa đóng nhựa dẻo, Khóa thân giày: khóa dán%Khả năng điều chỉnh:Điều chỉnh đượ', 0);
INSERT INTO `product` VALUES (39, 'Giày Patin Papaison', 0, 0, 'Màu sắc:Đỏ, Đen%Kích cỡ:S (31-34), M (35-38), L (39-42)%Đối tượng:Trẻ em%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Khả năng trượt:Vòng bi ABEC7%Khóa', 0);
INSERT INTO `product` VALUES (40, 'Giày Patin SOFT A1', 0, 0, 'Thương hiệu:SOFT%Màu sắc:Đen, Hồng, Xanh dương%Kích cỡ:S (31-34), M (35-38), L (39-42)%Khả năng điều chỉnh:Điều chỉnh được 4 cỡ% Có thể nới rộng giày Trượt khi chân trẻ to ra!%Đối tượng:Trẻ em%Khả năng trượt:Bánh xe 64mm-68mm, Bánh xe 68mm-70mm,', 0);
INSERT INTO `product` VALUES (41, 'Giày Patin Flying Eagle S5S+', 0, 0.25, NULL, 0);
INSERT INTO `product` VALUES (42, 'Giày Patin Micro D3LTA', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đen%Kích cỡ:38, 39, 40%Đối tượng:Người lớn%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Khả năng trượt:Bánh xe 72mm, Bánh xe 76mm, Bánh xe 80mm, Vòng bi Slalom PRO9%Độ bám:Bánh xe cao su độ đàn hồi và bám đ', 0);
INSERT INTO `product` VALUES (43, 'Giày Patin Micro Delta Force II', 0, 0.3, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đen, Trắng%Kích cỡ:37, 38%Đối tượng:Người lớn%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Khả năng trượt:Bánh xe 72mm, Bánh xe 76mm, Bánh xe 80mm, Vòng bi Slalom PRO9%Độ bám:Bánh xe cao su độ đàn hồi và bá', 0);
INSERT INTO `product` VALUES (44, 'Giày Patin Micro Delta Force JET', 0, 0.25, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Trắng%Kích cỡ:39 40%Đối tượng:Người lớn%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Khả năng trượt:Bánh xe 72mm, Bánh xe 76mm, Bánh xe 80mm, Vòng bi ABEC9%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%', 0);
INSERT INTO `product` VALUES (45, 'Giày Patin Micro Delta HML Pro', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Trắng%Kích cỡ:35, 36%Đối tượng:Người lớn%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Khả năng trượt:Bánh xe 76mm, Bánh xe 80mm, Vòng bi Slalom PRO9%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khung c', 0);
INSERT INTO `product` VALUES (46, 'Giày Patin Micro DELTA F', 0, 0.4, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đen%Kích cỡ:40, 41, 42, 43%Đối tượng:Người lớn%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Khả năng trượt:Bánh xe 72mm, Bánh xe 76mm, Bánh xe 80mm, Vòng bi ABEC9%Độ bám:Bánh xe cao su độ đàn hồi và bám đườ', 0);
INSERT INTO `product` VALUES (47, 'Giày Patin tốc độ Micro DYNAMIC SPEED', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đen, Trắng%Kích cỡ:41, 42%Đối tượng:Người lớn, Trẻ em%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh, Size kép (2 kích cỡ)%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 100mm, Bánh ', 0);
INSERT INTO `product` VALUES (48, 'Giày Patin Micro Delta HMH', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đen%Kích cỡ:40, 41, 42, 43%Đối tượng:Người lớn%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 76mm, Bánh xe 80mm, Độ cứng bánh xe 85A, ', 0);
INSERT INTO `product` VALUES (49, 'Giày Patin Tốc Độ – Speed Flying Eagle LEADER', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Kích cỡ:38, 39, 40, 41%Đối tượng:Người lớn, Trẻ em%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Khả năng trượt:Bánh xe 100mm, Bánh xe 110mm, Vòng bi FLYING EAGLE PRO%Phanh (thắng):Không%Độ bám:Bánh xe cao ', 0);
INSERT INTO `product` VALUES (50, 'Giày Patin Micro DELTA Pro', 0, 0.4, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đen%Kích cỡ:39, 40, 41, 42%Đối tượng:Người lớn%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 76mm, Bánh xe 80mm, Độ cứng bánh xe 85A, ', 0);
INSERT INTO `product` VALUES (51, 'Giày Patin Micro Delta Plus', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đen%Phiên bản:Cổ Carbon, Cổ nhựa%Kích cỡ:43%Đối tượng:Người lớn%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 76mm, Bánh xe 80mm, Độ c', 0);
INSERT INTO `product` VALUES (52, 'Giày Patin Micro DELTA LIBERTY', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Hồng%Kích cỡ:40%Đối tượng:Người lớn, Trẻ em%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh, Size kép (2 kích cỡ)%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 64mm-68mm, Bánh xe 72m', 0);
INSERT INTO `product` VALUES (53, 'Giày Patin Micro BEAT', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Kích cỡ:40%Đối tượng:Người lớn%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 100mm, Bánh xe 110mm, Vòng bi ABEC9%Khung càng - Frames:Nhôm nguyên khối CNC%Khóa ', 0);
INSERT INTO `product` VALUES (54, 'FLYING EAGLE DRIFT 2.0', 0, 0.4, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Kích cỡ:38, 39, 40, 41, 42%Đối tượng:Người lớn%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Khả năng trượt:Bánh xe 72mm, Bánh xe 76mm, Bánh xe 80mm, Vòng bi FLYING EAGLE PRO%Độ bám:Bánh xe cao su độ đàn hồ', 0);
INSERT INTO `product` VALUES (55, 'Giày Patin Tốc Độ – Speed Flying Eagle PHANTOM', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Kích cỡ:38, 39, 40 , 41%Đối tượng:Người lớn, Trẻ em%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Khả năng trượt:Bánh xe 100mm, Bánh xe 110mm, Vòng bi FLYING EAGLE PRO%Độ bám:Bánh xe cao su độ đàn hồi và bá', 0);
INSERT INTO `product` VALUES (56, 'Giày Patin Micro Super', 0, 0.5, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đen, Trắng, Vàng, Đỏ%Kích cỡ:36, 37, 38, 40%Đối tượng:Người lớn, Trẻ em%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Khả năng trượt:Bánh xe 72mm, Bánh xe 76', 0);
INSERT INTO `product` VALUES (57, 'Giày Patin Flying Eagle F110H', 0, 0.2, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Kích cỡ: 40%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Người lớn%Khả năng trượt:Bánh xe 110mm, Vòng bi ABEC7%Khung càng - Frames:Nhôm nguyên kh', 0);
INSERT INTO `product` VALUES (58, 'Giày Patin Aggressive Flying Eagle ENKIDU', 0, 0.4, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Kích cỡ:38, 40, 41, 42%Đối tượng:Người lớn%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Thân giày:Khuôn vỏ nhựa chịu lực tốt, khả năng chống va đập cao%Phanh (thắng):Không%Khóa giày:Khóa cổ giày: khóa đóng nhựa dẻo cao cấp', 0);
INSERT INTO `product` VALUES (59, 'Giày Patin Flying Eagle F7', 0, 0.2, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Kích cỡ:43%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi ABEC9%Đối tượng:Người lớn%Khóa giày:Kh', 0);
INSERT INTO `product` VALUES (60, 'Giày Patin Micro MT Plus', 0, 0, 'Thương hiệu:MICRO – Thụy Sĩ%Màu sắc:Đỏ, Đe, Vàng, Nâu%Kích cỡ:39, 40 41, 42, 43%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Đối tượng:Người lớn%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 70mm-72mm, Bánh xe 72mm-', 0);
INSERT INTO `product` VALUES (61, 'Giày Patin Flying Eagle F6S', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đỏ, Xanh lá%Kích cỡ:39, 40, 41%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Người lớn%Khả năng trượt:Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi AB', 0);
INSERT INTO `product` VALUES (62, 'Giày Patin Flying Eagle F5S+ Eclipse', 1, 0, 'Màu sắc:Đen%Thương hiệu:FLYING EAGLE%Kích cỡ:39, 40, 41 42%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Đối tượng:Người lớn%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 76mm, Bánh xe 80mm%Khóa giày:Khóa cổ giày: kh', 0);
INSERT INTO `product` VALUES (63, 'Giày Patin Flying Eagle F5S Eclipse', 0, 0, 'Màu sắc:Đen%Kích cỡ: 41%Thương hiệu:FLYING EAGLE%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Người lớn%Khả năng trượt:Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi ABEC7%Khóa giày:K', 0);
INSERT INTO `product` VALUES (64, 'Giày Patin Flying Eagle F4', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen, Đỏ%Kích cỡ:39, 40, 41, 42%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Người lớn%Khả năng trượt:Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi AB', 0);
INSERT INTO `product` VALUES (65, 'Giày Patin Flying Eagle F3S', 0, 0.2, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đỏ, Đen%Kích cỡ:39, 40, 43, 44%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Người lớn%Khả năng trượt:Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi AB', 0);
INSERT INTO `product` VALUES (66, 'Giày Patin Flying Eagle F3 Dragonfly ', 1, 0.2, 'Thương hiệu:FLYING EAGLE%Kích cỡ:39, 40%Màu sắc:Hồng, Đen%Đối tượng:Người lớn, Trẻ em%Thân giày:Khuôn vỏ nhựa chịu lực tốt, khả năng chống va đập cao%Phanh (thắng):Không%Khung càng - Frames:Nhôm nguyên khối CNC%Khóa giày:Khóa cổ giày: khóa đóng nhựa dẻo c', 0);
INSERT INTO `product` VALUES (67, 'Giày Patin Flying Eagle F2S', 0, 0.3, 'Thương hiệu:FLYING EAGLE%Màu sắc:Hồng%Kích cỡ:39 40%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Đối tượng:Người lớn%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi ABEC7%Khóa giày:Khóa cổ giày: k', 0);
INSERT INTO `product` VALUES (68, 'Giày Patin Flying Eagle BKB B5S', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Kích cỡ:38, 39,40, 41, 42%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Người lớn%Khả năng trượt:Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi ABEC7%Khóa giày:Khóa', 0);
INSERT INTO `product` VALUES (69, 'Giày Patin Flying Eagle F1S+', 0, 0.5, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Kích cỡ: 40, 41, 42%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Đối tượng:Người lớn%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi ABEC7%Khóa giày:Khóa cổ gi', 0);
INSERT INTO `product` VALUES (70, 'Giày Patin Flying Eagle FBS Fast Blade', 0, 0.1, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Kích cỡ:35, 40, 41%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Người lớn%Khả năng trượt:Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi ABEC7%Khóa giày:Khóa cổ già', 0);
INSERT INTO `product` VALUES (71, 'Giày Patin Calary C9', 0, 0, 'Thương hiệu:CALARY%Màu sắc:Trắng, Vàng%Kích cỡ:39,40%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Người lớn%Khả năng trượt:Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi ABEC7%Khóa giày:Khóa cổ giày: ', 0);
INSERT INTO `product` VALUES (72, 'Giày Patin iSkate RANGER', 0, 0, 'Kích cỡ:40%Màu sắc:Vàng%Đối tượng:Nam, Người lớn, Nữ, Trẻ em%Phanh (thắng):Không%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Khóa giày:Khóa cổ giày: khóa đóng nhựa dẻo, Khóa thân giày: khóa cài nhựa dẻo%Độ bám:Bánh xe cao su độ đàn hồi và bá', 0);
INSERT INTO `product` VALUES (73, 'Giày Patin Flying Eagle F1S', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen, Hồng%Kích cỡ:41,42, 44%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Đối tượng:Người lớn%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi ABEC7%Khóa giày:Khóa c', 0);
INSERT INTO `product` VALUES (74, 'Giày patin Flying Eagle F6 Falcon', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đỏ, Xanh lá%Đối tượng:Người lớn, Trẻ em%Khả năng điều chỉnh:Size đơn (1 kích cỡ) không điều chỉnh%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng trượt:Bánh xe 70mm-72mm, Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vò', 0);
INSERT INTO `product` VALUES (75, 'Giày patin Flying Eagle F5 Street Slider ', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Đối tượng:Người lớn, Trẻ em%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Khả năng trượt:Bánh xe 70mm-72mm, Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi ABEC7%Khung càng - Frames:Nhôm nguyên khối CNC%Thân giày:Khuôn vỏ nhự', 0);
INSERT INTO `product` VALUES (76, 'Giày patin Flying Eagle F3', 0, 0, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen%Đối tượng:Người lớn, Trẻ em%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Khung càng - Frames:Nhôm nguyên khối CNC%Thân giày:Khuôn vỏ nhựa chịu lực tốt, khả năng chống va đậ', 0);
INSERT INTO `product` VALUES (77, 'Giày patin Flying Eagle F2 ', 0, 0.2, 'Thương hiệu:FLYING EAGLE%Màu sắc:Đen, Vàng%Độ bám:Bánh xe cao su độ đàn hồi và bám đường tốt%Đối tượng:Người lớn, Trẻ em%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Khả năng trượt:Bánh xe 70mm-72mm, Bánh xe 72mm-76mm, Bánh xe 76mm-80mm, Vòng bi ABEC7%Khung c', 0);
INSERT INTO `product` VALUES (78, 'Giày Patin Flying Eagle F1 Mantra ', 0, 0.2, 'Thương hiệu:FLYING EAGLE%Màu sắc:Vàng, Xanh dương%Đối tượng:Người lớn, Trẻ em%Khả năng điều chỉnh:Size kép (2 kích cỡ)%Khóa giày:Khóa cổ giày: khóa đóng nhựa dẻo, Khóa thân giày: khóa cài nhựa dẻo%Khả năng trượt:Bánh xe 70mm-72mm, Bánh xe 72mm-76mm, Bánh ', 0);
INSERT INTO `product` VALUES (110, 'Sản phẩm 1', 0, 0.2, '', 0);

-- ----------------------------
-- Table structure for product_detail
-- ----------------------------
DROP TABLE IF EXISTS `product_detail`;
CREATE TABLE `product_detail`  (
  `id_product` int NOT NULL,
  `id_size` int NOT NULL,
  `id_color` int NOT NULL,
  `quantity` int NULL DEFAULT NULL,
  `price` int NULL DEFAULT NULL,
  `isDeleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_product`, `id_size`, `id_color`) USING BTREE,
  INDEX `p_s`(`id_size` ASC) USING BTREE,
  INDEX `p_c`(`id_color` ASC) USING BTREE,
  CONSTRAINT `p_c` FOREIGN KEY (`id_color`) REFERENCES `color` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `p_p` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `p_s` FOREIGN KEY (`id_size`) REFERENCES `size` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_detail
-- ----------------------------
INSERT INTO `product_detail` VALUES (1, 1, 3, 3, 800000, 0);
INSERT INTO `product_detail` VALUES (1, 3, 3, 10, 850000, 0);
INSERT INTO `product_detail` VALUES (1, 5, 4, 10, 850000, 0);
INSERT INTO `product_detail` VALUES (1, 14, 6, 20, 500000, 0);
INSERT INTO `product_detail` VALUES (2, 1, 1, 30, 1200000, 0);
INSERT INTO `product_detail` VALUES (2, 5, 1, 20, 1270000, 0);
INSERT INTO `product_detail` VALUES (3, 2, 3, 10, 2300000, 0);
INSERT INTO `product_detail` VALUES (3, 3, 3, 15, 2300000, 0);
INSERT INTO `product_detail` VALUES (3, 6, 3, 6, 2350000, 0);
INSERT INTO `product_detail` VALUES (3, 6, 4, 4, 2350000, 0);
INSERT INTO `product_detail` VALUES (3, 8, 4, 15, 2380000, 0);
INSERT INTO `product_detail` VALUES (4, 1, 4, 26, 700000, 0);
INSERT INTO `product_detail` VALUES (4, 2, 3, 10, 700000, 0);
INSERT INTO `product_detail` VALUES (4, 3, 4, 8, 700000, 0);
INSERT INTO `product_detail` VALUES (5, 1, 3, 13, 895000, 0);
INSERT INTO `product_detail` VALUES (5, 1, 4, 10, 895000, 0);
INSERT INTO `product_detail` VALUES (6, 1, 3, 4, 895000, 0);
INSERT INTO `product_detail` VALUES (6, 3, 3, 4, 895000, 0);
INSERT INTO `product_detail` VALUES (6, 6, 3, 4, 920000, 0);
INSERT INTO `product_detail` VALUES (6, 6, 4, 4, 920000, 0);
INSERT INTO `product_detail` VALUES (7, 1, 4, 8, 8900000, 0);
INSERT INTO `product_detail` VALUES (7, 2, 6, 5, 8900000, 0);
INSERT INTO `product_detail` VALUES (7, 3, 8, 5, 8900000, 0);
INSERT INTO `product_detail` VALUES (8, 1, 3, 5, 3790000, 0);
INSERT INTO `product_detail` VALUES (8, 1, 4, 4, 3790000, 0);
INSERT INTO `product_detail` VALUES (8, 2, 3, 10, 3790000, 0);
INSERT INTO `product_detail` VALUES (8, 2, 4, 10, 3790000, 0);
INSERT INTO `product_detail` VALUES (9, 1, 3, 40, 3650000, 0);
INSERT INTO `product_detail` VALUES (9, 3, 4, 1, 3650000, 0);
INSERT INTO `product_detail` VALUES (9, 6, 4, 9, 3700000, 0);
INSERT INTO `product_detail` VALUES (10, 1, 2, 20, 3650000, 0);
INSERT INTO `product_detail` VALUES (10, 2, 1, 20, 3650000, 0);
INSERT INTO `product_detail` VALUES (10, 3, 6, 10, 3650000, 0);
INSERT INTO `product_detail` VALUES (11, 1, 2, 20, 3500000, 0);
INSERT INTO `product_detail` VALUES (11, 2, 4, 10, 3520000, 0);
INSERT INTO `product_detail` VALUES (11, 3, 5, 20, 3520000, 0);
INSERT INTO `product_detail` VALUES (12, 1, 3, 20, 3195000, 0);
INSERT INTO `product_detail` VALUES (12, 1, 4, 10, 3195000, 0);
INSERT INTO `product_detail` VALUES (12, 2, 4, 10, 3195000, 0);
INSERT INTO `product_detail` VALUES (12, 3, 4, 10, 3200000, 0);
INSERT INTO `product_detail` VALUES (13, 3, 2, 3, 2980000, 0);
INSERT INTO `product_detail` VALUES (14, 3, 3, 25, 2950000, 0);
INSERT INTO `product_detail` VALUES (14, 3, 4, 25, 2950000, 0);
INSERT INTO `product_detail` VALUES (15, 1, 3, 15, 2930000, 0);
INSERT INTO `product_detail` VALUES (15, 2, 3, 15, 2930000, 0);
INSERT INTO `product_detail` VALUES (15, 2, 4, 20, 2930000, 0);
INSERT INTO `product_detail` VALUES (16, 1, 2, 3, 2750000, 0);
INSERT INTO `product_detail` VALUES (16, 2, 2, 1, 2780000, 0);
INSERT INTO `product_detail` VALUES (16, 2, 3, 1, 2780000, 0);
INSERT INTO `product_detail` VALUES (17, 3, 8, 1, 3000000, 0);
INSERT INTO `product_detail` VALUES (17, 6, 2, 3, 3050000, 0);
INSERT INTO `product_detail` VALUES (18, 1, 2, 6, 2750000, 0);
INSERT INTO `product_detail` VALUES (18, 3, 2, 3, 2780000, 0);
INSERT INTO `product_detail` VALUES (18, 6, 8, 3, 2800000, 0);
INSERT INTO `product_detail` VALUES (19, 2, 3, 23, 2650000, 0);
INSERT INTO `product_detail` VALUES (19, 2, 6, 10, 2650000, 0);
INSERT INTO `product_detail` VALUES (19, 3, 4, 20, 2650000, 0);
INSERT INTO `product_detail` VALUES (20, 1, 2, 7, 2540000, 0);
INSERT INTO `product_detail` VALUES (20, 1, 3, 3, 2540000, 0);
INSERT INTO `product_detail` VALUES (20, 1, 4, 1, 2540000, 0);
INSERT INTO `product_detail` VALUES (20, 2, 2, 4, 2600000, 0);
INSERT INTO `product_detail` VALUES (20, 3, 2, 5, 2600000, 0);
INSERT INTO `product_detail` VALUES (21, 1, 3, 25, 2500000, 0);
INSERT INTO `product_detail` VALUES (21, 1, 4, 15, 2500000, 0);
INSERT INTO `product_detail` VALUES (21, 2, 2, 15, 2500000, 0);
INSERT INTO `product_detail` VALUES (22, 1, 3, 1, 3000000, 0);
INSERT INTO `product_detail` VALUES (22, 1, 8, 7, 3000000, 0);
INSERT INTO `product_detail` VALUES (22, 2, 4, 2, 3000000, 0);
INSERT INTO `product_detail` VALUES (22, 3, 4, 2, 3050000, 0);
INSERT INTO `product_detail` VALUES (23, 1, 4, 12, 2200000, 0);
INSERT INTO `product_detail` VALUES (23, 3, 4, 10, 2200000, 0);
INSERT INTO `product_detail` VALUES (23, 6, 3, 10, 2210000, 0);
INSERT INTO `product_detail` VALUES (24, 3, 3, 9, 2300000, 0);
INSERT INTO `product_detail` VALUES (25, 2, 4, 2, 2000000, 0);
INSERT INTO `product_detail` VALUES (25, 3, 2, 6, 2000000, 0);
INSERT INTO `product_detail` VALUES (25, 3, 3, 2, 2000000, 0);
INSERT INTO `product_detail` VALUES (26, 2, 2, 0, 2100000, 0);
INSERT INTO `product_detail` VALUES (26, 2, 4, 4, 2100000, 0);
INSERT INTO `product_detail` VALUES (26, 6, 3, 3, 2200000, 0);
INSERT INTO `product_detail` VALUES (27, 7, 3, 5, 1950000, 0);
INSERT INTO `product_detail` VALUES (27, 7, 8, 5, 1950000, 0);
INSERT INTO `product_detail` VALUES (28, 3, 2, 3, 1600000, 0);
INSERT INTO `product_detail` VALUES (28, 3, 3, 15, 1600000, 0);
INSERT INTO `product_detail` VALUES (28, 3, 4, 15, 1600000, 0);
INSERT INTO `product_detail` VALUES (29, 4, 2, 2, 1500000, 0);
INSERT INTO `product_detail` VALUES (29, 4, 3, 10, 1500000, 0);
INSERT INTO `product_detail` VALUES (29, 5, 4, 1, 1550000, 0);
INSERT INTO `product_detail` VALUES (30, 2, 2, 13, 1580000, 0);
INSERT INTO `product_detail` VALUES (30, 3, 3, 2, 1580000, 0);
INSERT INTO `product_detail` VALUES (30, 6, 4, 8, 1620000, 0);
INSERT INTO `product_detail` VALUES (31, 2, 4, 10, 1990000, 0);
INSERT INTO `product_detail` VALUES (31, 7, 3, 10, 2000000, 0);
INSERT INTO `product_detail` VALUES (31, 7, 4, 3, 2000000, 0);
INSERT INTO `product_detail` VALUES (32, 4, 2, 5, 1490000, 0);
INSERT INTO `product_detail` VALUES (32, 5, 3, 13, 1500000, 0);
INSERT INTO `product_detail` VALUES (32, 5, 4, 5, 1500000, 0);
INSERT INTO `product_detail` VALUES (33, 4, 3, 20, 1790000, 0);
INSERT INTO `product_detail` VALUES (33, 5, 2, 3, 1800000, 0);
INSERT INTO `product_detail` VALUES (34, 2, 4, 12, 1380000, 0);
INSERT INTO `product_detail` VALUES (34, 3, 3, 1, 1380000, 0);
INSERT INTO `product_detail` VALUES (34, 3, 4, 10, 1380000, 0);
INSERT INTO `product_detail` VALUES (35, 4, 3, 11, 1500000, 0);
INSERT INTO `product_detail` VALUES (35, 4, 4, 11, 1500000, 0);
INSERT INTO `product_detail` VALUES (35, 5, 4, 1, 1500000, 0);
INSERT INTO `product_detail` VALUES (36, 4, 3, 5, 1165000, 0);
INSERT INTO `product_detail` VALUES (36, 4, 4, 18, 1165000, 0);
INSERT INTO `product_detail` VALUES (37, 3, 4, 6, 2200000, 0);
INSERT INTO `product_detail` VALUES (37, 6, 3, 14, 2250000, 0);
INSERT INTO `product_detail` VALUES (37, 6, 4, 3, 2250000, 0);
INSERT INTO `product_detail` VALUES (38, 8, 2, 2, 1200000, 0);
INSERT INTO `product_detail` VALUES (38, 8, 3, 13, 1200000, 0);
INSERT INTO `product_detail` VALUES (38, 8, 8, 8, 1200000, 0);
INSERT INTO `product_detail` VALUES (39, 7, 2, 5, 1340000, 0);
INSERT INTO `product_detail` VALUES (39, 7, 8, 5, 1340000, 0);
INSERT INTO `product_detail` VALUES (40, 7, 3, 30, 1065000, 0);
INSERT INTO `product_detail` VALUES (40, 8, 2, 20, 1100000, 0);
INSERT INTO `product_detail` VALUES (41, 2, 2, 10, 1650000, 0);
INSERT INTO `product_detail` VALUES (41, 3, 2, 20, 1650000, 0);
INSERT INTO `product_detail` VALUES (41, 6, 3, 15, 1700000, 0);
INSERT INTO `product_detail` VALUES (41, 6, 4, 5, 1700000, 0);
INSERT INTO `product_detail` VALUES (42, 11, 2, 10, 10750000, 0);
INSERT INTO `product_detail` VALUES (42, 12, 2, 30, 10800000, 0);
INSERT INTO `product_detail` VALUES (42, 13, 2, 10, 11000000, 0);
INSERT INTO `product_detail` VALUES (43, 10, 1, 25, 9000000, 0);
INSERT INTO `product_detail` VALUES (43, 10, 2, 10, 9000000, 0);
INSERT INTO `product_detail` VALUES (43, 11, 2, 15, 9050000, 0);
INSERT INTO `product_detail` VALUES (44, 12, 1, 5, 9300000, 0);
INSERT INTO `product_detail` VALUES (44, 13, 1, 10, 9350000, 0);
INSERT INTO `product_detail` VALUES (44, 17, 1, 35, 9470000, 0);
INSERT INTO `product_detail` VALUES (45, 8, 1, 25, 8000000, 0);
INSERT INTO `product_detail` VALUES (45, 9, 1, 25, 8030000, 0);
INSERT INTO `product_detail` VALUES (46, 13, 2, 10, 8500000, 0);
INSERT INTO `product_detail` VALUES (46, 14, 2, 20, 8503000, 0);
INSERT INTO `product_detail` VALUES (46, 15, 2, 10, 8508000, 0);
INSERT INTO `product_detail` VALUES (46, 16, 2, 10, 8600000, 0);
INSERT INTO `product_detail` VALUES (47, 14, 1, 10, 5200000, 0);
INSERT INTO `product_detail` VALUES (47, 14, 2, 10, 5200000, 0);
INSERT INTO `product_detail` VALUES (47, 15, 1, 15, 5250000, 0);
INSERT INTO `product_detail` VALUES (47, 15, 2, 15, 5250000, 0);
INSERT INTO `product_detail` VALUES (48, 13, 2, 14, 4500000, 0);
INSERT INTO `product_detail` VALUES (48, 14, 2, 20, 4520000, 0);
INSERT INTO `product_detail` VALUES (48, 15, 2, 5, 4570000, 0);
INSERT INTO `product_detail` VALUES (48, 16, 2, 5, 4600000, 0);
INSERT INTO `product_detail` VALUES (49, 11, 2, 11, 4500000, 0);
INSERT INTO `product_detail` VALUES (49, 12, 2, 1, 4500000, 0);
INSERT INTO `product_detail` VALUES (49, 13, 2, 5, 4550000, 0);
INSERT INTO `product_detail` VALUES (49, 14, 2, 5, 4580000, 0);
INSERT INTO `product_detail` VALUES (50, 12, 2, 3, 6000000, 0);
INSERT INTO `product_detail` VALUES (50, 13, 2, 9, 6030000, 0);
INSERT INTO `product_detail` VALUES (50, 14, 2, 9, 6080000, 0);
INSERT INTO `product_detail` VALUES (50, 15, 2, 2, 6100000, 0);
INSERT INTO `product_detail` VALUES (50, 16, 2, 10, 6150000, 0);
INSERT INTO `product_detail` VALUES (51, 16, 2, 11, 8000000, 0);
INSERT INTO `product_detail` VALUES (52, 13, 1, 1, 2900000, 0);
INSERT INTO `product_detail` VALUES (52, 17, 3, 1, 3000000, 0);
INSERT INTO `product_detail` VALUES (53, 13, 1, 3, 1100000, 0);
INSERT INTO `product_detail` VALUES (54, 11, 2, 1, 990000, 0);
INSERT INTO `product_detail` VALUES (54, 12, 2, 3, 990000, 0);
INSERT INTO `product_detail` VALUES (54, 13, 2, 2, 1000000, 0);
INSERT INTO `product_detail` VALUES (54, 14, 2, 1, 1000000, 0);
INSERT INTO `product_detail` VALUES (54, 15, 2, 1, 1150000, 0);
INSERT INTO `product_detail` VALUES (55, 11, 2, 3, 980000, 0);
INSERT INTO `product_detail` VALUES (55, 12, 2, 3, 1000000, 0);
INSERT INTO `product_detail` VALUES (55, 13, 2, 1, 1050000, 0);
INSERT INTO `product_detail` VALUES (55, 14, 2, 2, 1080000, 0);
INSERT INTO `product_detail` VALUES (56, 9, 2, 2, 965000, 0);
INSERT INTO `product_detail` VALUES (56, 10, 1, 5, 970000, 0);
INSERT INTO `product_detail` VALUES (56, 11, 4, 2, 980000, 0);
INSERT INTO `product_detail` VALUES (56, 13, 4, 2, 1000000, 0);
INSERT INTO `product_detail` VALUES (56, 13, 8, 1, 1000000, 0);
INSERT INTO `product_detail` VALUES (57, 13, 2, 1, 690000, 0);
INSERT INTO `product_detail` VALUES (58, 11, 2, 4, 1100000, 0);
INSERT INTO `product_detail` VALUES (58, 13, 2, 4, 1150000, 0);
INSERT INTO `product_detail` VALUES (58, 14, 2, 2, 1180000, 0);
INSERT INTO `product_detail` VALUES (58, 17, 2, 12, 1200000, 0);
INSERT INTO `product_detail` VALUES (59, 16, 2, 1, 2300000, 0);
INSERT INTO `product_detail` VALUES (60, 12, 2, 4, 4000000, 0);
INSERT INTO `product_detail` VALUES (60, 13, 8, 2, 4030000, 0);
INSERT INTO `product_detail` VALUES (60, 14, 4, 3, 4050000, 0);
INSERT INTO `product_detail` VALUES (60, 15, 7, 3, 4080000, 0);
INSERT INTO `product_detail` VALUES (60, 16, 2, 1, 4100000, 0);
INSERT INTO `product_detail` VALUES (60, 16, 4, 1, 4100000, 0);
INSERT INTO `product_detail` VALUES (61, 12, 8, 2, 2300000, 0);
INSERT INTO `product_detail` VALUES (61, 13, 5, 2, 2350000, 0);
INSERT INTO `product_detail` VALUES (61, 14, 8, 2, 2380000, 0);
INSERT INTO `product_detail` VALUES (62, 12, 2, 1, 5600000, 0);
INSERT INTO `product_detail` VALUES (62, 13, 2, 2, 5650000, 0);
INSERT INTO `product_detail` VALUES (62, 14, 2, 4, 5680000, 0);
INSERT INTO `product_detail` VALUES (62, 15, 2, 1, 5700000, 0);
INSERT INTO `product_detail` VALUES (63, 14, 2, 1, 2890000, 0);
INSERT INTO `product_detail` VALUES (64, 12, 2, 4, 3000000, 0);
INSERT INTO `product_detail` VALUES (64, 13, 2, 15, 3050000, 0);
INSERT INTO `product_detail` VALUES (64, 14, 2, 12, 3080000, 0);
INSERT INTO `product_detail` VALUES (64, 15, 2, 3, 3100000, 0);
INSERT INTO `product_detail` VALUES (65, 12, 2, 2, 2300000, 0);
INSERT INTO `product_detail` VALUES (65, 12, 8, 4, 2300000, 0);
INSERT INTO `product_detail` VALUES (65, 13, 2, 2, 2350000, 0);
INSERT INTO `product_detail` VALUES (65, 16, 2, 1, 2308000, 0);
INSERT INTO `product_detail` VALUES (65, 16, 8, 1, 2308000, 0);
INSERT INTO `product_detail` VALUES (65, 17, 2, 1, 2400000, 0);
INSERT INTO `product_detail` VALUES (66, 12, 2, 5, 1800000, 0);
INSERT INTO `product_detail` VALUES (66, 12, 3, 12, 1800000, 0);
INSERT INTO `product_detail` VALUES (66, 13, 3, 5, 1850000, 0);
INSERT INTO `product_detail` VALUES (67, 13, 3, 40, 1850000, 0);
INSERT INTO `product_detail` VALUES (67, 14, 3, 10, 1880000, 0);
INSERT INTO `product_detail` VALUES (68, 11, 2, 23, 300000, 0);
INSERT INTO `product_detail` VALUES (68, 12, 2, 13, 320000, 0);
INSERT INTO `product_detail` VALUES (68, 13, 2, 4, 350000, 0);
INSERT INTO `product_detail` VALUES (68, 14, 2, 5, 380000, 0);
INSERT INTO `product_detail` VALUES (68, 15, 3, 5, 400000, 0);
INSERT INTO `product_detail` VALUES (69, 13, 2, 13, 500000, 0);
INSERT INTO `product_detail` VALUES (69, 14, 2, 7, 550000, 0);
INSERT INTO `product_detail` VALUES (69, 15, 2, 2, 600000, 0);
INSERT INTO `product_detail` VALUES (70, 8, 2, 10, 2200000, 0);
INSERT INTO `product_detail` VALUES (70, 13, 2, 10, 2300000, 0);
INSERT INTO `product_detail` VALUES (70, 14, 2, 10, 2350000, 0);
INSERT INTO `product_detail` VALUES (71, 12, 1, 6, 1500000, 0);
INSERT INTO `product_detail` VALUES (71, 12, 4, 2, 1500000, 0);
INSERT INTO `product_detail` VALUES (71, 13, 1, 3, 1580000, 0);
INSERT INTO `product_detail` VALUES (72, 13, 2, 3, 1500000, 0);
INSERT INTO `product_detail` VALUES (72, 13, 4, 3, 1500000, 0);
INSERT INTO `product_detail` VALUES (73, 14, 2, 1, 2600000, 0);
INSERT INTO `product_detail` VALUES (73, 15, 2, 2, 2650000, 0);
INSERT INTO `product_detail` VALUES (73, 17, 2, 2, 2700000, 0);
INSERT INTO `product_detail` VALUES (73, 17, 3, 1, 2700000, 0);
INSERT INTO `product_detail` VALUES (74, 8, 5, 3, 5000000, 0);
INSERT INTO `product_detail` VALUES (74, 9, 8, 2, 5050000, 0);
INSERT INTO `product_detail` VALUES (74, 10, 5, 4, 5100000, 0);
INSERT INTO `product_detail` VALUES (74, 10, 8, 1, 5100000, 0);
INSERT INTO `product_detail` VALUES (75, 10, 2, 1, 3000000, 0);
INSERT INTO `product_detail` VALUES (75, 11, 2, 1, 3030000, 0);
INSERT INTO `product_detail` VALUES (76, 13, 2, 31, 1800000, 0);
INSERT INTO `product_detail` VALUES (77, 16, 2, 13, 2400000, 0);
INSERT INTO `product_detail` VALUES (77, 17, 4, 10, 2480000, 0);
INSERT INTO `product_detail` VALUES (78, 8, 4, 3, 4200000, 0);
INSERT INTO `product_detail` VALUES (78, 12, 3, 2, 4350000, 0);
INSERT INTO `product_detail` VALUES (78, 12, 4, 7, 4350000, 0);
INSERT INTO `product_detail` VALUES (78, 14, 4, 3, 4480000, 0);
INSERT INTO `product_detail` VALUES (110, 1, 2, 1, 200000, 0);

-- ----------------------------
-- Table structure for rating
-- ----------------------------
DROP TABLE IF EXISTS `rating`;
CREATE TABLE `rating`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` int NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rate_p`(`product_id` ASC) USING BTREE,
  INDEX `rate_u`(`user_id` ASC) USING BTREE,
  CONSTRAINT `rate_p` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rate_u` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rating
-- ----------------------------
INSERT INTO `rating` VALUES (39, 5, 'giao hang dung hen, chat luong nhu hinh se mua ung ho', '2024-07-16 13:01:44', 28, 25);

-- ----------------------------
-- Table structure for rating_detail_like
-- ----------------------------
DROP TABLE IF EXISTS `rating_detail_like`;
CREATE TABLE `rating_detail_like`  (
  `rating_id` int NOT NULL,
  `like` int NULL DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`rating_id`, `user_id`) USING BTREE,
  INDEX `rd_u`(`user_id` ASC) USING BTREE,
  CONSTRAINT `r_rd` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rd_u` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rating_detail_like
-- ----------------------------

-- ----------------------------
-- Table structure for size
-- ----------------------------
DROP TABLE IF EXISTS `size`;
CREATE TABLE `size`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `isDeleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of size
-- ----------------------------
INSERT INTO `size` VALUES (1, '27', 0);
INSERT INTO `size` VALUES (2, '29', 0);
INSERT INTO `size` VALUES (3, '30', 0);
INSERT INTO `size` VALUES (4, '31', 0);
INSERT INTO `size` VALUES (5, '32', 0);
INSERT INTO `size` VALUES (6, '33', 0);
INSERT INTO `size` VALUES (7, '34', 0);
INSERT INTO `size` VALUES (8, '35', 0);
INSERT INTO `size` VALUES (9, '36', 0);
INSERT INTO `size` VALUES (10, '37', 0);
INSERT INTO `size` VALUES (11, '38', 0);
INSERT INTO `size` VALUES (12, '39', 0);
INSERT INTO `size` VALUES (13, '40', 0);
INSERT INTO `size` VALUES (14, '41', 0);
INSERT INTO `size` VALUES (15, '42', 0);
INSERT INTO `size` VALUES (16, '43', 0);
INSERT INTO `size` VALUES (17, '44', 0);

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `productID` int NOT NULL,
  `sizeID` int NOT NULL,
  `colorID` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  `price` int NULL DEFAULT NULL,
  `salePercent` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `productID`, `colorID`, `sizeID`) USING BTREE,
  INDEX `f_store_p`(`productID` ASC) USING BTREE,
  INDEX `f_store_c`(`colorID` ASC) USING BTREE,
  INDEX `f_store_s`(`sizeID` ASC) USING BTREE,
  CONSTRAINT `f_store_c` FOREIGN KEY (`colorID`) REFERENCES `color` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_store_p` FOREIGN KEY (`productID`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_store_s` FOREIGN KEY (`sizeID`) REFERENCES `size` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 537 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (299, 1, 1, 3, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 800000, 0.2);
INSERT INTO `store` VALUES (300, 1, 3, 3, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 850000, 0.2);
INSERT INTO `store` VALUES (301, 1, 5, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 850000, 0.2);
INSERT INTO `store` VALUES (302, 2, 1, 1, 'Nguyễn Ngọc Khánh Duy', 30, '2024-07-15 20:57:40', 1200000, 0.2);
INSERT INTO `store` VALUES (303, 2, 5, 1, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 1270000, 0.2);
INSERT INTO `store` VALUES (304, 3, 2, 3, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 2300000, 0.2);
INSERT INTO `store` VALUES (305, 3, 3, 3, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 2300000, 0.2);
INSERT INTO `store` VALUES (306, 3, 6, 3, 'Nguyễn Ngọc Khánh Duy', 6, '2024-07-15 20:57:40', 2350000, 0.2);
INSERT INTO `store` VALUES (307, 3, 6, 4, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 2350000, 0.2);
INSERT INTO `store` VALUES (308, 3, 8, 4, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 2380000, 0.2);
INSERT INTO `store` VALUES (309, 4, 1, 4, 'Nguyễn Ngọc Khánh Duy', 30, '2024-07-15 20:57:40', 700000, 0.2);
INSERT INTO `store` VALUES (310, 4, 2, 3, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 700000, 0.2);
INSERT INTO `store` VALUES (311, 4, 3, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 700000, 0.2);
INSERT INTO `store` VALUES (312, 5, 1, 3, 'Nguyễn Ngọc Khánh Duy', 13, '2024-07-15 20:57:40', 895000, 0.2);
INSERT INTO `store` VALUES (313, 5, 1, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 895000, 0.2);
INSERT INTO `store` VALUES (314, 6, 1, 3, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 895000, 0.2);
INSERT INTO `store` VALUES (315, 6, 3, 3, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 895000, 0.2);
INSERT INTO `store` VALUES (316, 6, 6, 3, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 920000, 0.2);
INSERT INTO `store` VALUES (317, 6, 6, 4, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 920000, 0.2);
INSERT INTO `store` VALUES (318, 7, 1, 4, 'Nguyễn Ngọc Khánh Duy', 8, '2024-07-15 20:57:40', 8900000, 0.2);
INSERT INTO `store` VALUES (319, 7, 2, 6, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 8900000, 0.2);
INSERT INTO `store` VALUES (320, 7, 3, 8, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 8900000, 0.2);
INSERT INTO `store` VALUES (321, 8, 1, 3, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 3790000, 0.2);
INSERT INTO `store` VALUES (322, 8, 1, 4, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 3790000, 0.2);
INSERT INTO `store` VALUES (323, 8, 2, 3, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 3790000, 0.2);
INSERT INTO `store` VALUES (324, 8, 2, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 3790000, 0.2);
INSERT INTO `store` VALUES (325, 9, 1, 3, 'Nguyễn Ngọc Khánh Duy', 40, '2024-07-15 20:57:40', 3650000, 0.2);
INSERT INTO `store` VALUES (326, 9, 3, 4, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 3650000, 0.2);
INSERT INTO `store` VALUES (327, 9, 6, 4, 'Nguyễn Ngọc Khánh Duy', 9, '2024-07-15 20:57:40', 3700000, 0.2);
INSERT INTO `store` VALUES (328, 10, 1, 2, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 3650000, 0.2);
INSERT INTO `store` VALUES (329, 10, 2, 1, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 3650000, 0.2);
INSERT INTO `store` VALUES (330, 10, 3, 6, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 3650000, 0.2);
INSERT INTO `store` VALUES (331, 11, 1, 2, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 3500000, 0.2);
INSERT INTO `store` VALUES (332, 11, 2, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 3520000, 0.2);
INSERT INTO `store` VALUES (333, 11, 3, 5, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 3520000, 0.2);
INSERT INTO `store` VALUES (334, 12, 1, 3, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 3195000, 0.2);
INSERT INTO `store` VALUES (335, 12, 1, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 3195000, 0.2);
INSERT INTO `store` VALUES (336, 12, 2, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 3195000, 0.2);
INSERT INTO `store` VALUES (337, 12, 3, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 3200000, 0.2);
INSERT INTO `store` VALUES (338, 13, 3, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 2980000, 0.2);
INSERT INTO `store` VALUES (339, 14, 3, 3, 'Nguyễn Ngọc Khánh Duy', 25, '2024-07-15 20:57:40', 2950000, 0.2);
INSERT INTO `store` VALUES (340, 14, 3, 4, 'Nguyễn Ngọc Khánh Duy', 25, '2024-07-15 20:57:40', 2950000, 0.2);
INSERT INTO `store` VALUES (341, 15, 1, 3, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 2930000, 0.2);
INSERT INTO `store` VALUES (342, 15, 2, 3, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 2930000, 0.2);
INSERT INTO `store` VALUES (343, 15, 2, 4, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 2930000, 0.2);
INSERT INTO `store` VALUES (344, 16, 1, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 2750000, 0.2);
INSERT INTO `store` VALUES (345, 16, 2, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 2780000, 0.2);
INSERT INTO `store` VALUES (346, 16, 2, 3, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 2780000, 0.2);
INSERT INTO `store` VALUES (347, 17, 3, 8, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 3000000, 0.2);
INSERT INTO `store` VALUES (348, 17, 6, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 3050000, 0.2);
INSERT INTO `store` VALUES (349, 18, 1, 2, 'Nguyễn Ngọc Khánh Duy', 6, '2024-07-15 20:57:40', 2750000, 0.2);
INSERT INTO `store` VALUES (350, 18, 3, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 2780000, 0.2);
INSERT INTO `store` VALUES (351, 18, 6, 8, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 2800000, 0.2);
INSERT INTO `store` VALUES (352, 19, 2, 3, 'Nguyễn Ngọc Khánh Duy', 23, '2024-07-15 20:57:40', 2650000, 0.2);
INSERT INTO `store` VALUES (353, 19, 2, 6, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 2650000, 0.2);
INSERT INTO `store` VALUES (354, 19, 3, 4, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 2650000, 0.2);
INSERT INTO `store` VALUES (355, 20, 1, 2, 'Nguyễn Ngọc Khánh Duy', 7, '2024-07-15 20:57:40', 2540000, 0.2);
INSERT INTO `store` VALUES (356, 20, 1, 3, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 2540000, 0.2);
INSERT INTO `store` VALUES (357, 20, 1, 4, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 2540000, 0.2);
INSERT INTO `store` VALUES (358, 20, 2, 2, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 2600000, 0.2);
INSERT INTO `store` VALUES (359, 20, 3, 2, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 2600000, 0.2);
INSERT INTO `store` VALUES (360, 21, 1, 3, 'Nguyễn Ngọc Khánh Duy', 25, '2024-07-15 20:57:40', 2500000, 0.2);
INSERT INTO `store` VALUES (361, 21, 1, 4, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 2500000, 0.2);
INSERT INTO `store` VALUES (362, 21, 2, 2, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 2500000, 0.2);
INSERT INTO `store` VALUES (363, 22, 1, 8, 'Nguyễn Ngọc Khánh Duy', 7, '2024-07-15 20:57:40', 3000000, 0.2);
INSERT INTO `store` VALUES (364, 22, 1, 3, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 3000000, 0.2);
INSERT INTO `store` VALUES (365, 22, 2, 4, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 3000000, 0.2);
INSERT INTO `store` VALUES (366, 22, 3, 4, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 3050000, 0.2);
INSERT INTO `store` VALUES (367, 23, 1, 4, 'Nguyễn Ngọc Khánh Duy', 12, '2024-07-15 20:57:40', 2200000, 0.2);
INSERT INTO `store` VALUES (368, 23, 3, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 2200000, 0.2);
INSERT INTO `store` VALUES (369, 23, 6, 3, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 2210000, 0.2);
INSERT INTO `store` VALUES (370, 26, 2, 4, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 2100000, 0.2);
INSERT INTO `store` VALUES (371, 24, 3, 3, 'Nguyễn Ngọc Khánh Duy', 9, '2024-07-15 20:57:40', 2300000, 0.2);
INSERT INTO `store` VALUES (372, 26, 6, 3, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 2200000, 0.2);
INSERT INTO `store` VALUES (373, 25, 2, 4, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 2000000, 0.2);
INSERT INTO `store` VALUES (374, 25, 3, 2, 'Nguyễn Ngọc Khánh Duy', 6, '2024-07-15 20:57:40', 2000000, 0.2);
INSERT INTO `store` VALUES (375, 25, 3, 3, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 2000000, 0.2);
INSERT INTO `store` VALUES (376, 26, 2, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 2100000, 0.2);
INSERT INTO `store` VALUES (377, 27, 7, 8, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 1950000, 0.2);
INSERT INTO `store` VALUES (378, 27, 7, 3, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 1950000, 0.2);
INSERT INTO `store` VALUES (379, 28, 3, 4, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 1600000, 0.2);
INSERT INTO `store` VALUES (380, 28, 3, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 1600000, 0.2);
INSERT INTO `store` VALUES (381, 28, 3, 3, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 1600000, 0.2);
INSERT INTO `store` VALUES (382, 29, 4, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 1500000, 0.2);
INSERT INTO `store` VALUES (383, 29, 4, 3, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 1500000, 0.2);
INSERT INTO `store` VALUES (384, 29, 5, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 1550000, 0.2);
INSERT INTO `store` VALUES (385, 30, 2, 2, 'Nguyễn Ngọc Khánh Duy', 13, '2024-07-15 20:57:40', 1580000, 0.2);
INSERT INTO `store` VALUES (386, 30, 3, 3, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 1580000, 0.2);
INSERT INTO `store` VALUES (387, 30, 6, 4, 'Nguyễn Ngọc Khánh Duy', 8, '2024-07-15 20:57:40', 1620000, 0.2);
INSERT INTO `store` VALUES (388, 31, 2, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 1990000, 0.2);
INSERT INTO `store` VALUES (389, 31, 7, 3, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 2000000, 0.2);
INSERT INTO `store` VALUES (390, 31, 7, 4, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 2000000, 0.2);
INSERT INTO `store` VALUES (391, 32, 4, 2, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 1490000, 0.2);
INSERT INTO `store` VALUES (392, 32, 5, 3, 'Nguyễn Ngọc Khánh Duy', 13, '2024-07-15 20:57:40', 1500000, 0.2);
INSERT INTO `store` VALUES (393, 32, 5, 4, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 1500000, 0.2);
INSERT INTO `store` VALUES (394, 33, 4, 3, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 1790000, 0.2);
INSERT INTO `store` VALUES (395, 33, 5, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 1800000, 0.2);
INSERT INTO `store` VALUES (396, 34, 2, 4, 'Nguyễn Ngọc Khánh Duy', 12, '2024-07-15 20:57:40', 1380000, 0.2);
INSERT INTO `store` VALUES (397, 34, 3, 3, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 1380000, 0.2);
INSERT INTO `store` VALUES (398, 34, 3, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 1380000, 0.2);
INSERT INTO `store` VALUES (399, 35, 4, 3, 'Nguyễn Ngọc Khánh Duy', 11, '2024-07-15 20:57:40', 1500000, 0.2);
INSERT INTO `store` VALUES (400, 35, 4, 4, 'Nguyễn Ngọc Khánh Duy', 11, '2024-07-15 20:57:40', 1500000, 0.2);
INSERT INTO `store` VALUES (401, 35, 5, 4, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 1500000, 0.2);
INSERT INTO `store` VALUES (402, 36, 4, 3, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 1165000, 0.2);
INSERT INTO `store` VALUES (403, 36, 4, 4, 'Nguyễn Ngọc Khánh Duy', 18, '2024-07-15 20:57:40', 1165000, 0.2);
INSERT INTO `store` VALUES (404, 38, 8, 8, 'Nguyễn Ngọc Khánh Duy', 8, '2024-07-15 20:57:40', 1200000, 0.2);
INSERT INTO `store` VALUES (405, 37, 3, 4, 'Nguyễn Ngọc Khánh Duy', 6, '2024-07-15 20:57:40', 2200000, 0.2);
INSERT INTO `store` VALUES (406, 37, 6, 4, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 2250000, 0.2);
INSERT INTO `store` VALUES (407, 37, 6, 3, 'Nguyễn Ngọc Khánh Duy', 14, '2024-07-15 20:57:40', 2250000, 0.2);
INSERT INTO `store` VALUES (408, 38, 8, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 1200000, 0.2);
INSERT INTO `store` VALUES (409, 38, 8, 3, 'Nguyễn Ngọc Khánh Duy', 13, '2024-07-15 20:57:40', 1200000, 0.2);
INSERT INTO `store` VALUES (410, 39, 7, 8, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 1340000, 0.2);
INSERT INTO `store` VALUES (411, 39, 7, 2, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 1340000, 0.2);
INSERT INTO `store` VALUES (412, 40, 7, 3, 'Nguyễn Ngọc Khánh Duy', 30, '2024-07-15 20:57:40', 1065000, 0.2);
INSERT INTO `store` VALUES (413, 40, 8, 2, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 1100000, 0.2);
INSERT INTO `store` VALUES (414, 41, 2, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 1650000, 0.2);
INSERT INTO `store` VALUES (415, 41, 3, 2, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 1650000, 0.2);
INSERT INTO `store` VALUES (416, 41, 6, 3, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 1700000, 0.2);
INSERT INTO `store` VALUES (417, 41, 6, 4, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 1700000, 0.2);
INSERT INTO `store` VALUES (418, 42, 11, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 10750000, 0.2);
INSERT INTO `store` VALUES (419, 42, 12, 2, 'Nguyễn Ngọc Khánh Duy', 30, '2024-07-15 20:57:40', 10800000, 0.2);
INSERT INTO `store` VALUES (420, 42, 13, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 11000000, 0.2);
INSERT INTO `store` VALUES (421, 43, 10, 1, 'Nguyễn Ngọc Khánh Duy', 25, '2024-07-15 20:57:40', 9000000, 0.2);
INSERT INTO `store` VALUES (422, 43, 10, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 9000000, 0.2);
INSERT INTO `store` VALUES (423, 43, 11, 2, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 9050000, 0.2);
INSERT INTO `store` VALUES (424, 44, 12, 1, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 9300000, 0.2);
INSERT INTO `store` VALUES (425, 44, 13, 1, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 9350000, 0.2);
INSERT INTO `store` VALUES (426, 44, 17, 1, 'Nguyễn Ngọc Khánh Duy', 35, '2024-07-15 20:57:40', 9470000, 0.2);
INSERT INTO `store` VALUES (427, 45, 8, 1, 'Nguyễn Ngọc Khánh Duy', 25, '2024-07-15 20:57:40', 8000000, 0.2);
INSERT INTO `store` VALUES (428, 45, 9, 1, 'Nguyễn Ngọc Khánh Duy', 25, '2024-07-15 20:57:40', 8030000, 0.2);
INSERT INTO `store` VALUES (429, 46, 13, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 8500000, 0.2);
INSERT INTO `store` VALUES (430, 46, 14, 2, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 8503000, 0.2);
INSERT INTO `store` VALUES (431, 46, 15, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 8508000, 0.2);
INSERT INTO `store` VALUES (432, 46, 16, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 8600000, 0.2);
INSERT INTO `store` VALUES (433, 47, 14, 1, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 5200000, 0.2);
INSERT INTO `store` VALUES (434, 47, 14, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 5200000, 0.2);
INSERT INTO `store` VALUES (435, 47, 15, 1, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 5250000, 0.2);
INSERT INTO `store` VALUES (436, 47, 15, 2, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 5250000, 0.2);
INSERT INTO `store` VALUES (437, 48, 13, 2, 'Nguyễn Ngọc Khánh Duy', 14, '2024-07-15 20:57:40', 4500000, 0.2);
INSERT INTO `store` VALUES (438, 48, 14, 2, 'Nguyễn Ngọc Khánh Duy', 20, '2024-07-15 20:57:40', 4520000, 0.2);
INSERT INTO `store` VALUES (439, 48, 15, 2, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 4570000, 0.2);
INSERT INTO `store` VALUES (440, 48, 16, 2, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 4600000, 0.2);
INSERT INTO `store` VALUES (441, 49, 11, 2, 'Nguyễn Ngọc Khánh Duy', 11, '2024-07-15 20:57:40', 4500000, 0.2);
INSERT INTO `store` VALUES (442, 49, 12, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 4500000, 0.2);
INSERT INTO `store` VALUES (443, 49, 13, 2, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 4550000, 0.2);
INSERT INTO `store` VALUES (444, 49, 14, 2, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 4580000, 0.2);
INSERT INTO `store` VALUES (445, 50, 12, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 6000000, 0.2);
INSERT INTO `store` VALUES (446, 50, 13, 2, 'Nguyễn Ngọc Khánh Duy', 9, '2024-07-15 20:57:40', 6030000, 0.2);
INSERT INTO `store` VALUES (447, 50, 14, 2, 'Nguyễn Ngọc Khánh Duy', 9, '2024-07-15 20:57:40', 6080000, 0.2);
INSERT INTO `store` VALUES (448, 50, 15, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 6100000, 0.2);
INSERT INTO `store` VALUES (449, 50, 16, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 6150000, 0.2);
INSERT INTO `store` VALUES (450, 51, 16, 2, 'Nguyễn Ngọc Khánh Duy', 11, '2024-07-15 20:57:40', 8000000, 0.2);
INSERT INTO `store` VALUES (451, 52, 13, 1, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 2900000, 0.2);
INSERT INTO `store` VALUES (452, 52, 17, 3, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 3000000, 0.2);
INSERT INTO `store` VALUES (453, 53, 13, 1, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 1100000, 0.2);
INSERT INTO `store` VALUES (454, 54, 11, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 990000, 0.2);
INSERT INTO `store` VALUES (455, 54, 12, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 990000, 0.2);
INSERT INTO `store` VALUES (456, 54, 13, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 1000000, 0.2);
INSERT INTO `store` VALUES (457, 54, 14, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 1000000, 0.2);
INSERT INTO `store` VALUES (458, 54, 15, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 1150000, 0.2);
INSERT INTO `store` VALUES (459, 55, 11, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 980000, 0.2);
INSERT INTO `store` VALUES (460, 55, 12, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 1000000, 0.2);
INSERT INTO `store` VALUES (461, 55, 13, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 1050000, 0.2);
INSERT INTO `store` VALUES (462, 55, 14, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 1080000, 0.2);
INSERT INTO `store` VALUES (463, 56, 9, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 965000, 0.2);
INSERT INTO `store` VALUES (464, 56, 10, 1, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 970000, 0.2);
INSERT INTO `store` VALUES (465, 56, 11, 4, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 980000, 0.2);
INSERT INTO `store` VALUES (466, 56, 13, 8, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 1000000, 0.2);
INSERT INTO `store` VALUES (467, 56, 13, 4, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 1000000, 0.2);
INSERT INTO `store` VALUES (468, 57, 13, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 690000, 0.2);
INSERT INTO `store` VALUES (469, 58, 11, 2, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 1100000, 0.2);
INSERT INTO `store` VALUES (470, 58, 13, 2, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 1150000, 0.2);
INSERT INTO `store` VALUES (471, 58, 14, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 1180000, 0.2);
INSERT INTO `store` VALUES (472, 58, 17, 2, 'Nguyễn Ngọc Khánh Duy', 12, '2024-07-15 20:57:40', 1200000, 0.2);
INSERT INTO `store` VALUES (473, 59, 16, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 2300000, 0.2);
INSERT INTO `store` VALUES (474, 60, 12, 2, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 4000000, 0.2);
INSERT INTO `store` VALUES (475, 60, 13, 8, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 4030000, 0.2);
INSERT INTO `store` VALUES (476, 60, 14, 4, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 4050000, 0.2);
INSERT INTO `store` VALUES (477, 60, 15, 7, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 4080000, 0.2);
INSERT INTO `store` VALUES (478, 60, 16, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 4100000, 0.2);
INSERT INTO `store` VALUES (479, 60, 16, 4, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 4100000, 0.2);
INSERT INTO `store` VALUES (480, 61, 12, 8, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 2300000, 0.2);
INSERT INTO `store` VALUES (481, 61, 13, 5, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 2350000, 0.2);
INSERT INTO `store` VALUES (482, 61, 14, 8, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 2380000, 0.2);
INSERT INTO `store` VALUES (483, 62, 12, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 5600000, 0.2);
INSERT INTO `store` VALUES (484, 62, 13, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 5650000, 0.2);
INSERT INTO `store` VALUES (485, 62, 14, 2, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 5680000, 0.2);
INSERT INTO `store` VALUES (486, 62, 15, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 5700000, 0.2);
INSERT INTO `store` VALUES (487, 63, 14, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 2890000, 0.2);
INSERT INTO `store` VALUES (488, 64, 12, 2, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 3000000, 0.2);
INSERT INTO `store` VALUES (489, 64, 13, 2, 'Nguyễn Ngọc Khánh Duy', 15, '2024-07-15 20:57:40', 3050000, 0.2);
INSERT INTO `store` VALUES (490, 64, 14, 2, 'Nguyễn Ngọc Khánh Duy', 12, '2024-07-15 20:57:40', 3080000, 0.2);
INSERT INTO `store` VALUES (491, 64, 15, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 3100000, 0.2);
INSERT INTO `store` VALUES (492, 65, 12, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 2300000, 0.2);
INSERT INTO `store` VALUES (493, 65, 12, 8, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 2300000, 0.2);
INSERT INTO `store` VALUES (494, 65, 13, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 2350000, 0.2);
INSERT INTO `store` VALUES (495, 65, 16, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 2308000, 0.2);
INSERT INTO `store` VALUES (496, 65, 16, 8, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 2308000, 0.2);
INSERT INTO `store` VALUES (497, 65, 17, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 2400000, 0.2);
INSERT INTO `store` VALUES (498, 66, 12, 2, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 1800000, 0.2);
INSERT INTO `store` VALUES (499, 66, 12, 3, 'Nguyễn Ngọc Khánh Duy', 12, '2024-07-15 20:57:40', 1800000, 0.2);
INSERT INTO `store` VALUES (500, 66, 13, 3, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 1850000, 0.2);
INSERT INTO `store` VALUES (501, 67, 13, 3, 'Nguyễn Ngọc Khánh Duy', 40, '2024-07-15 20:57:40', 1850000, 0.2);
INSERT INTO `store` VALUES (502, 67, 14, 3, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 1880000, 0.2);
INSERT INTO `store` VALUES (503, 68, 11, 2, 'Nguyễn Ngọc Khánh Duy', 23, '2024-07-15 20:57:40', 300000, 0.2);
INSERT INTO `store` VALUES (504, 68, 12, 2, 'Nguyễn Ngọc Khánh Duy', 13, '2024-07-15 20:57:40', 320000, 0.2);
INSERT INTO `store` VALUES (505, 68, 13, 2, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 350000, 0.2);
INSERT INTO `store` VALUES (506, 68, 14, 2, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 380000, 0.2);
INSERT INTO `store` VALUES (507, 68, 15, 3, 'Nguyễn Ngọc Khánh Duy', 5, '2024-07-15 20:57:40', 400000, 0.2);
INSERT INTO `store` VALUES (508, 69, 13, 2, 'Nguyễn Ngọc Khánh Duy', 13, '2024-07-15 20:57:40', 500000, 0.2);
INSERT INTO `store` VALUES (509, 69, 14, 2, 'Nguyễn Ngọc Khánh Duy', 7, '2024-07-15 20:57:40', 550000, 0.2);
INSERT INTO `store` VALUES (510, 69, 15, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 600000, 0.2);
INSERT INTO `store` VALUES (511, 70, 8, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 2200000, 0.2);
INSERT INTO `store` VALUES (512, 70, 13, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 2300000, 0.2);
INSERT INTO `store` VALUES (513, 70, 14, 2, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 2350000, 0.2);
INSERT INTO `store` VALUES (514, 71, 12, 1, 'Nguyễn Ngọc Khánh Duy', 6, '2024-07-15 20:57:40', 1500000, 0.2);
INSERT INTO `store` VALUES (515, 71, 12, 4, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 1500000, 0.2);
INSERT INTO `store` VALUES (516, 71, 13, 1, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 1580000, 0.2);
INSERT INTO `store` VALUES (517, 72, 13, 2, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 1500000, 0.2);
INSERT INTO `store` VALUES (518, 72, 13, 4, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 1500000, 0.2);
INSERT INTO `store` VALUES (519, 73, 14, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 2600000, 0.2);
INSERT INTO `store` VALUES (520, 73, 15, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 2650000, 0.2);
INSERT INTO `store` VALUES (521, 73, 17, 2, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 2700000, 0.2);
INSERT INTO `store` VALUES (522, 73, 17, 3, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 2700000, 0.2);
INSERT INTO `store` VALUES (523, 74, 8, 5, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 5000000, 0.2);
INSERT INTO `store` VALUES (524, 74, 9, 8, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 5050000, 0.2);
INSERT INTO `store` VALUES (525, 74, 10, 5, 'Nguyễn Ngọc Khánh Duy', 4, '2024-07-15 20:57:40', 5100000, 0.2);
INSERT INTO `store` VALUES (526, 74, 10, 8, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 5100000, 0.2);
INSERT INTO `store` VALUES (527, 75, 10, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 3000000, 0.2);
INSERT INTO `store` VALUES (528, 75, 11, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-15 20:57:40', 3030000, 0.2);
INSERT INTO `store` VALUES (529, 76, 13, 2, 'Nguyễn Ngọc Khánh Duy', 31, '2024-07-15 20:57:40', 1800000, 0.2);
INSERT INTO `store` VALUES (530, 77, 16, 2, 'Nguyễn Ngọc Khánh Duy', 13, '2024-07-15 20:57:40', 2400000, 0.2);
INSERT INTO `store` VALUES (531, 77, 17, 4, 'Nguyễn Ngọc Khánh Duy', 10, '2024-07-15 20:57:40', 2480000, 0.2);
INSERT INTO `store` VALUES (532, 78, 8, 4, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 4200000, 0.2);
INSERT INTO `store` VALUES (533, 78, 12, 3, 'Nguyễn Ngọc Khánh Duy', 2, '2024-07-15 20:57:40', 4350000, 0.2);
INSERT INTO `store` VALUES (534, 78, 12, 4, 'Nguyễn Ngọc Khánh Duy', 7, '2024-07-15 20:57:40', 4350000, 0.2);
INSERT INTO `store` VALUES (535, 78, 14, 4, 'Nguyễn Ngọc Khánh Duy', 3, '2024-07-15 20:57:40', 4480000, 0.2);
INSERT INTO `store` VALUES (536, 110, 1, 2, 'Nguyễn Ngọc Khánh Duy', 1, '2024-07-16 13:07:37', 200000, 20);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `verify` int NULL DEFAULT NULL,
  `fullname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` int NULL DEFAULT NULL,
  `dob` date NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `keyPass` int NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  `isDeleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (28, '21130035@st.hcmuaf.edu.vn', '2ac9a6746aca543af8dff39894cfe8173afba21eb01c6fae33d52947222855ef', 1, 'Nguyễn Ngọc Khánh Duy', '0839151003', '863/10', 0, '2003-10-15', NULL, NULL, 2, 0);
INSERT INTO `user` VALUES (51, 'lomninkon@gmail.com', NULL, 1, 'Lom Nin', NULL, NULL, NULL, NULL, 'https://lh3.googleusercontent.com/a/ACg8ocIQ0aMDCuW5F9PMSGdBc5EDtABjp_V6Yv6MAvO9wC7AXV_nLA=s96-c', NULL, 0, 0);

-- ----------------------------
-- Table structure for wish_list
-- ----------------------------
DROP TABLE IF EXISTS `wish_list`;
CREATE TABLE `wish_list`  (
  `id_user` int NOT NULL,
  `id_product` int NOT NULL,
  PRIMARY KEY (`id_user`, `id_product`) USING BTREE,
  INDEX `w_p`(`id_product` ASC) USING BTREE,
  CONSTRAINT `w_p` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `w_u` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wish_list
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
