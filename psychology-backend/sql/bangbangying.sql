/*
 Navicat Premium Data Transfer

 Source Server         : bangbangying
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : bangbangying

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 18/07/2024 10:25:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `admin_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `admin_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES (1, 'admin1', '202cb962ac59075b964b07152d234b70', '123@qq.com', '2024-07-08 14:39:56');
INSERT INTO `admins` VALUES (2, 'admin2', '202cb962ac59075b964b07152d234b70', '123@qq.com', '2024-07-09 09:08:33');
INSERT INTO `admins` VALUES (3, 'admin3', '202cb962ac59075b964b07152d234b70', '123@qq.com', '2024-07-09 09:08:38');
INSERT INTO `admins` VALUES (4, 'admin4', '202cb962ac59075b964b07152d234b70', '123@qq.com', '2024-07-09 09:08:40');
INSERT INTO `admins` VALUES (5, 'admin5', '202cb962ac59075b964b07152d234b70', '123@qq.com', '2024-07-09 09:08:43');
INSERT INTO `admins` VALUES (6, 'admin6', '202cb962ac59075b964b07152d234b70', '123@qq.com', '2024-07-11 10:51:26');
INSERT INTO `admins` VALUES (7, 'admin7', '202cb962ac59075b964b07152d234b70', 'test@admin.com', '2024-07-12 08:21:40');

-- ----------------------------
-- Table structure for appointments
-- ----------------------------
DROP TABLE IF EXISTS `appointments`;
CREATE TABLE `appointments`  (
  `apt_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `expert_id` int NOT NULL,
  `apt_time` bigint NOT NULL,
  PRIMARY KEY (`apt_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `expert_id`(`expert_id` ASC) USING BTREE,
  CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`expert_id`) REFERENCES `experts` (`ex_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of appointments
-- ----------------------------
INSERT INTO `appointments` VALUES (1, 1, 2, 1720834816000);
INSERT INTO `appointments` VALUES (2, 2, 3, 1720834851000);
INSERT INTO `appointments` VALUES (3, 4, 2, 1720771200000);
INSERT INTO `appointments` VALUES (4, 4, 2, 1720767600000);
INSERT INTO `appointments` VALUES (5, 6, 2, 1720767600001);
INSERT INTO `appointments` VALUES (6, 6, 3, 1720767600001);
INSERT INTO `appointments` VALUES (7, 6, 4, 1720767600001);
INSERT INTO `appointments` VALUES (8, 5, 2, 1721207715004);

-- ----------------------------
-- Table structure for articles
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles`  (
  `art_id` int NOT NULL AUTO_INCREMENT,
  `art_author` int NOT NULL,
  `art_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `art_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `art_time` bigint NULL DEFAULT NULL,
  `art_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`art_id`) USING BTREE,
  INDEX `art_author`(`art_author` ASC) USING BTREE,
  CONSTRAINT `articles_ibfk_1` FOREIGN KEY (`art_author`) REFERENCES `experts` (`ex_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of articles
-- ----------------------------
INSERT INTO `articles` VALUES (1, 1, '抑郁症患者心情突然变好，不一定是一件好事！', '\\n# 一级标题\\n\\n## 二级标题\\n\\n### 三级标题\\n\\n### 1.2 无序列表\\n\\n无序列表的使用，在符号`-`后加空格使用。如下：\\n- 无序列表 1\\n- 无序列表 2\\n  - 无序列表 2.1\\n  - 无序列表 2.2\\n\\n**由于微信原因，最多支持到二级列表。**\\n\\n### 1.3 有序列表\\n\\n1. 有序列表 1\\n2. 有序列表 2\\n\\n### 1.4 粗体和斜体\\n\\n**这个是粗体**\\n\\n_这个是斜体_\\n\\n**_这个是粗体加斜体_**\\n\\n### 1.5 链接\\n\\n对于该论述，欢迎读者查阅之前发过的文章，[你是《未来世界的幸存者》么？](https://mp.weixin.qq.com/s/s5IhxV2ooX3JN_X416nidA)\\n\\n### 1.6 引用\\n\\n> ### 一级引用示例\\n> \\n> 读一本好书，就是在和高尚的人谈话。 **——歌德**\\n\\n### 1.7 分割线\\n\\n可以在一行中用三个以上的减号来建立一个分隔线，同时需要在分隔线的上面空一行。如下：\\n\\n---\\n\\n### 1.8 删除线\\n\\n删除线的使用，在需要删除的文字前后各使用两个`~`，如下：\\n\\n~~这是要被删除的内容。~~\\n\\n### 1.9 表格\\n\\n| 姓名       | 年龄 |         工作 |\\n| :--------- | :--: | -----------: |\\n| 作者     |  18  |     web |\\n| zerojs   |  20  |  前端 |\\n| 太菜了 |  22  | 躺平 |\\n\\n## 2. 特殊语法\\n\\n### 2.1 脚注\\n\\n脚注与链接的区别如下所示：\\n\\n```markdown\\n链接：[文字](链接)\\n脚注：[文字](脚注解释 \\\"脚注名字\\\")\\n```\\n\\n### 2.2 代码块\\n\\n```js\\nconsole.log(\\\"1\\\");\\n\\nsetTimeout(function () {\\n  console.log(\\\"2\\\");\\n  process.nextTick(function () {\\n    console.log(\\\"3\\\");\\n  });\\n  new Promise(function (resolve) {\\n    console.log(\\\"4\\\");\\n    resolve();\\n  }).then(function () {\\n    console.log(\\\"5\\\");\\n  });\\n});\\n```\\n\\ndiff 不能同时和其他语言的高亮同时显示，且需要调整代码主题为微信代码主题以外的代码主题才能看到 diff 效果，使用效果如下:\\n\\n```diff\\n+ 新增项\\n- 删除项\\n```\\n\\n**其他主题不带行号，可自定义是否换行，代码大小与当前编辑器一致**\\n\\n## 3 其他语法\\n\\n### 3.1 HTML\\n\\n支持原生 HTML 语法，请写内联样式，如下：\\n\\n<span style=\\\"display:block;text-align:right;color:orangered;\\\">橙色居右</span>\\n<span style=\\\"display:block;text-align:center;color:orangered;\\\">橙色居中</span>\\n\\n### 3.2 UML\\n\\n不支持，推荐使用开源工具`https://draw.io/`制作后再导入图片\\n', 1721015186, 'http://8.217.178.86:8081/articles/4.jpg');
INSERT INTO `articles` VALUES (2, 2, '睡不着 ≠ 失眠！关于失眠的 6 个真相', 'This is a regular paragraph.  <table>     <tr>         <td>Foo</td>     </tr> </table>  This is another regular paragraph.', 1721015196, 'http://8.217.178.86:8081/articles/2.jpg');
INSERT INTO `articles` VALUES (3, 3, '焦虑的本质是对失控的恐惧 | 如何克服你的焦虑？', '<div class=\"notecard note\">   <p><strong>备注：</strong> 这是编写备注的方式。</p>   <p>它可以包含代码块。</p>   <pre class=\"brush: js\">const s = \"我在代码块中\";</pre>   <p>就像这样。</p> </div>', 1721015196, 'http://8.217.178.86:8081/articles/3.jpeg');
INSERT INTO `articles` VALUES (4, 4, '莫名地情绪高涨，这正常吗？', '受到表扬、鼓励后，心中激情澎湃，干劲十足，这种反应很正常。  遭人误会、辱骂后，内心忿忿不平，火冒三丈，这样的情绪也很好理解。  然而，有的朋友会有这样的疑惑：  为什么我会没来由地情绪高涨？没有任何人或事刺激我，但我却突然很亢奋，这是怎么回事？', 1721015196, 'http://8.217.178.86:8081/articles/1.jpg');
INSERT INTO `articles` VALUES (5, 5, '总是莫名的烦躁怎么办', '# 烦躁：一种常见的情绪  烦躁是一种常见的情绪，当有不顺心的事发生时，我们就有可能变得烦躁不安，这是很正常的。那么，什么样的烦躁是不正常的呢？  ## 焦虑症的表现 若总是觉得莫名烦躁，且持续时间很长，那就有可能是焦虑症的表现。  ## 普通烦躁的自我调理方法  ### 1. 增加自信 - 自信是解除烦躁的必要前提。 - 增强自信心，减少自卑感。  ### 2. 想象放松 - 想象种种可能的危险情景，让最弱的情景首先出现并重复多次。  ### 3. 反省发泄 - 自我反省，诉说潜意识中引起痛苦的事情。 - 必要时发泄，以消除症状。  ### 4. 转移注意 - 转移注意力，如阅读或从事体力劳动。  ## 焦虑症莫名烦躁的治疗方法  ### 精神分析疗法 - 帮助患者领悟内在心理冲突的根源。  ### 药物治疗 - 抗焦虑药物是最常用的治疗焦虑症的方法。 - 使用时需谨慎。', 1721015196, '这里填图片链接');

-- ----------------------------
-- Table structure for articlescomments
-- ----------------------------
DROP TABLE IF EXISTS `articlescomments`;
CREATE TABLE `articlescomments`  (
  `comments_id` int NOT NULL AUTO_INCREMENT,
  `art_id` int NOT NULL,
  `user_id` int NOT NULL,
  `comments_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `comments_time` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`comments_id`) USING BTREE,
  INDEX `art_id`(`art_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `articlescomments_ibfk_1` FOREIGN KEY (`art_id`) REFERENCES `articles` (`art_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `articlescomments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of articlescomments
-- ----------------------------
INSERT INTO `articlescomments` VALUES (1, 2, 3, '豪堪，艾堪', 1721013689457);
INSERT INTO `articlescomments` VALUES (2, 1, 2, '帖子堪了敏感肌用了也很好用', 1721013689457);
INSERT INTO `articlescomments` VALUES (3, 2, 1, '111', 1721013689457);
INSERT INTO `articlescomments` VALUES (4, 5, 3, '爱了爱了', 1721013689457);

-- ----------------------------
-- Table structure for consultationlogs
-- ----------------------------
DROP TABLE IF EXISTS `consultationlogs`;
CREATE TABLE `consultationlogs`  (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `apt_id` int NOT NULL,
  `log_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `log_time` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `apt_id`(`apt_id` ASC) USING BTREE,
  CONSTRAINT `consultationlogs_ibfk_1` FOREIGN KEY (`apt_id`) REFERENCES `appointments` (`apt_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of consultationlogs
-- ----------------------------
INSERT INTO `consultationlogs` VALUES (1, 8, 'hahahahahaha', 1721029108001);

-- ----------------------------
-- Table structure for experts
-- ----------------------------
DROP TABLE IF EXISTS `experts`;
CREATE TABLE `experts`  (
  `ex_id` int NOT NULL AUTO_INCREMENT,
  `ex_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ex_bio` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `ex_dire` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ex_qualification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ex_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ex_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`ex_id`) USING BTREE,
  INDEX `ex_id`(`ex_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of experts
-- ----------------------------
INSERT INTO `experts` VALUES (1, '华央恒', '华央恒，男，武汉大学医生，专治失眠方向', '失眠', '特聘专家', '202cb962ac59075b964b07152d234b70', '123@qq.com', '2024-07-08 17:00:55');
INSERT INTO `experts` VALUES (2, '肖一帆', '肖一帆，男，武汉大学医生，专治焦虑方向', '焦虑', '专家', '202cb962ac59075b964b07152d234b70', '123@qq.com', '2024-07-09 09:05:11');
INSERT INTO `experts` VALUES (3, '陈毅飞', '陈毅飞，男，武汉大学医生，专治烦躁方向', '烦躁', '专家', '202cb962ac59075b964b07152d234b70', '123@qq.com', '2024-07-09 09:05:19');
INSERT INTO `experts` VALUES (4, '刘骥飞', '刘骥飞，男，华中科技大学医生，专治抑郁方向', '抑郁', '专家', '202cb962ac59075b964b07152d234b70', '123@qq.com', '2024-07-09 09:05:23');
INSERT INTO `experts` VALUES (5, '何正邦', '何正邦，男，武汉大学医生，专治妇科方向', '妇科', '院长', '202cb962ac59075b964b07152d234b70', '123@qq.com', '2024-07-09 09:25:25');

-- ----------------------------
-- Table structure for psychologicaltests
-- ----------------------------
DROP TABLE IF EXISTS `psychologicaltests`;
CREATE TABLE `psychologicaltests`  (
  `test_id` int NOT NULL AUTO_INCREMENT,
  `belonging_id` int NOT NULL,
  `test_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `test_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `test_score` int NULL DEFAULT 0,
  `test_cho_a` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N',
  `test_cho_b` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N',
  `test_cho_c` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N',
  `test_cho_d` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N',
  `test_proportion_a` int NULL DEFAULT 0,
  `test_proportion_b` int NULL DEFAULT 0,
  `test_proportion_c` int NULL DEFAULT 0,
  `test_proportion_d` int NULL DEFAULT 0,
  PRIMARY KEY (`test_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of psychologicaltests
-- ----------------------------
INSERT INTO `psychologicaltests` VALUES (1, 0, 'string', 'string', 0, 'string', 'string', 'string', 'string', 0, 0, 0, 0);
INSERT INTO `psychologicaltests` VALUES (2, 1, 'test2,保存测试', '题干信息', 10, '选项A', '选项B', '选项C', '选项D', 0, 0, 0, 0);
INSERT INTO `psychologicaltests` VALUES (3, 1, 'test3,保存测试', '题干信息', 10, '选项A', '选项B', '选项C', '选项D', 1, 0, 0, 0);
INSERT INTO `psychologicaltests` VALUES (4, 1, 'test3,保存测试', '题干信息', 0, '选项A', '选项B', 'N', 'N', 0, 0, 0, 0);
INSERT INTO `psychologicaltests` VALUES (5, 1, 'test4,保存测试', NULL, 0, '选项A', '选项B', 'N', 'N', 0, 0, 0, 0);
INSERT INTO `psychologicaltests` VALUES (6, 1, 'test4,保存测试', '题干信息', 10, '选项A', '选项B', '选项C', '选项D', 0, 0, 0, 0);

-- ----------------------------
-- Table structure for stressrelieftools
-- ----------------------------
DROP TABLE IF EXISTS `stressrelieftools`;
CREATE TABLE `stressrelieftools`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `tool_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tool_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `tool_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stressrelieftools
-- ----------------------------
INSERT INTO `stressrelieftools` VALUES (1, '音乐播放', '根据您当前的情绪播放适合您的音乐', '123.com');
INSERT INTO `stressrelieftools` VALUES (2, '解压游戏', '适合压力较大的用户进行小游戏放松', 'https://www.4399.com/');
INSERT INTO `stressrelieftools` VALUES (3, '催眠引导', '跟随我们的专业心理师，来一场深层催眠', 'https://www.yuanshen.com/');

-- ----------------------------
-- Table structure for t_favorite
-- ----------------------------
DROP TABLE IF EXISTS `t_favorite`;
CREATE TABLE `t_favorite`  (
  `favorite_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `music_id` bigint NOT NULL,
  `created` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`favorite_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_favorite
-- ----------------------------
INSERT INTO `t_favorite` VALUES (1, 6, 61, '2024-07-13 08:54:22');

-- ----------------------------
-- Table structure for t_history
-- ----------------------------
DROP TABLE IF EXISTS `t_history`;
CREATE TABLE `t_history`  (
  `history_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `music_id` bigint NOT NULL,
  `created` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_history
-- ----------------------------
INSERT INTO `t_history` VALUES (1, 1, 83, '2024-07-10 08:26:40');
INSERT INTO `t_history` VALUES (2, 1, 84, '2024-07-10 09:01:30');
INSERT INTO `t_history` VALUES (3, 1, 33, '2024-07-11 08:27:08');
INSERT INTO `t_history` VALUES (4, 1, 81, '2024-07-16 07:01:09');
INSERT INTO `t_history` VALUES (5, 1, 40, '2024-07-12 01:24:08');
INSERT INTO `t_history` VALUES (6, 6, 61, '2024-07-13 08:54:27');
INSERT INTO `t_history` VALUES (7, 1, 1813103255241101313, '2024-07-16 06:47:56');
INSERT INTO `t_history` VALUES (8, 1, 1813103293736423425, '2024-07-16 06:48:06');
INSERT INTO `t_history` VALUES (9, 1, 1813107137606324225, '2024-07-16 07:03:22');
INSERT INTO `t_history` VALUES (10, 1, 1813391326381608962, '2024-07-17 01:52:38');
INSERT INTO `t_history` VALUES (11, 1, 1813404210473996289, '2024-07-17 02:43:50');

-- ----------------------------
-- Table structure for t_music
-- ----------------------------
DROP TABLE IF EXISTS `t_music`;
CREATE TABLE `t_music`  (
  `details_id` bigint NOT NULL AUTO_INCREMENT COMMENT '音乐ID，主键，自动递增',
  `recommend_id` bigint NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌曲的URL',
  `album_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专辑封面URL',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌曲作者',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌曲标题',
  `hot_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌曲热评',
  `comment_author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '热评网友名',
  PRIMARY KEY (`details_id`) USING BTREE,
  INDEX `idx_music_title`(`title` ASC) USING BTREE,
  INDEX `idx_music_author`(`author` ASC) USING BTREE,
  INDEX `music_details_ibfk_1`(`recommend_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_music
-- ----------------------------
INSERT INTO `t_music` VALUES (1, 1, 'http://8.217.178.86:8081/music/1.mp3', 'http://8.217.178.86:8081/photo/1.jpg', 'LiQWYD', 'Back To You', 'Turn up the volume, turn down the darkness.', 'MelodicDreamer');
INSERT INTO `t_music` VALUES (2, 2, 'http://8.217.178.86:8081/music/2.mp3', 'http://8.217.178.86:8081/photo/2.jpg', 'BoxCat Games', 'Battle (Special)', 'It\'s not just music; it\'s an embrace.', 'HarmonyWhisper');
INSERT INTO `t_music` VALUES (3, 3, 'http://8.217.178.86:8081/music/3.mp3', 'http://8.217.178.86:8081/photo/3.jpg', 'Luke Hall', 'Breakout', 'This song is a bridge to my aspirations.', 'AmbitionSeeker');
INSERT INTO `t_music` VALUES (4, 4, 'http://8.217.178.86:8081/music/4.mp3', 'http://8.217.178.86:8081/photo/4.jpg', 'Peyruis', 'Buzzin', 'Lost in the beat and found in the melody.', 'RhythmicSoul');
INSERT INTO `t_music` VALUES (5, 5, 'http://8.217.178.86:8081/music/5.mp3', 'http://8.217.178.86:8081/photo/5.jpg', 'Peyruis', 'Coco Mademoiselle', 'Music heals me more than time ever could.', 'SonicHealer');
INSERT INTO `t_music` VALUES (6, 6, 'http://8.217.178.86:8081/music/6.mp3', 'http://8.217.178.86:8081/photo/6.jpg', 'KV', 'Crux', 'The lyrics feel like my unwritten diary.', 'EchoedEmotions');
INSERT INTO `t_music` VALUES (7, 7, 'http://8.217.178.86:8081/music/7.mp3', 'http://8.217.178.86:8081/photo/7.jpg', 'Hotham', 'Dance Tonight', 'Floating on a cloud made of beats.', 'SkyHighTunes');
INSERT INTO `t_music` VALUES (8, 8, 'http://8.217.178.86:8081/music/8.mp3', 'http://8.217.178.86:8081/photo/8.jpg', 'Dylan Emmet', 'Flipside', 'Every chord strikes a chord in my heart.', 'HeartStrings');
INSERT INTO `t_music` VALUES (9, 9, 'http://8.217.178.86:8081/music/9.mp3', 'http://8.217.178.86:8081/photo/9.jpg', 'Alex-Productions', 'Floating', 'This melody is the soundtrack of my dreams.', 'DreamyNotes');
INSERT INTO `t_music` VALUES (10, 10, 'http://8.217.178.86:8081/music/10.mp3', 'http://8.217.178.86:8081/photo/10.jpg', 'KV', 'Fuzzy', 'Feeling the music with every fiber of my being.', 'VibrantVibes');
INSERT INTO `t_music` VALUES (11, 11, 'http://8.217.178.86:8081/music/11.mp3', 'http://8.217.178.86:8081/photo/11.jpg', 'ASHUTOSH', 'Game Over', 'Where words fail, music speaks.', 'MelodicMessenger');
INSERT INTO `t_music` VALUES (12, 12, 'http://8.217.178.86:8081/music/12.mp3', 'http://8.217.178.86:8081/photo/12.jpg', 'Peyruis', 'Groovin', 'Turning the volume up and my fears down.', 'SoundBrave');
INSERT INTO `t_music` VALUES (13, 13, 'http://8.217.178.86:8081/music/13.mp3', 'http://8.217.178.86:8081/photo/13.jpg', 'Jeff II', 'Heartfül of Kerøsene (instrumental)', 'This song paints my mood in vibrant colors.', 'MoodPainter');
INSERT INTO `t_music` VALUES (14, 14, 'http://8.217.178.86:8081/music/14.mp3', 'http://8.217.178.86:8081/photo/14.jpg', 'ASHUTOSH', 'Jaipur', 'Music is the language of my soul.', 'SoulfulSinger');
INSERT INTO `t_music` VALUES (15, 15, 'http://8.217.178.86:8081/music/15.mp3', 'http://8.217.178.86:8081/photo/15.jpg', 'Jeff II', 'Jetfire', 'In this moment, the song understands me better than anyone.', 'HarmonicCompanion');
INSERT INTO `t_music` VALUES (16, 16, 'http://8.217.178.86:8081/music/16.mp3', 'http://8.217.178.86:8081/photo/16.jpg', 'LiQWYD', 'Last Time', 'Riding waves of melodies into the sunset.', 'SunsetSurfer');
INSERT INTO `t_music` VALUES (17, 17, 'http://8.217.178.86:8081/music/17.mp3', 'http://8.217.178.86:8081/photo/17.jpg', 'tubebackr', 'Layer Cake', 'Finding peace in every beat.', 'PeacefulRhythms');
INSERT INTO `t_music` VALUES (18, 18, 'http://8.217.178.86:8081/music/18.mp3', 'http://8.217.178.86:8081/photo/18.jpg', 'GroovyD', 'New Moon', 'This song feels like a warm hug on a cold day.', 'MelodicWarmth');
INSERT INTO `t_music` VALUES (19, 19, 'http://8.217.178.86:8081/music/19.mp3', 'http://8.217.178.86:8081/photo/19.jpg', 'KV', 'Unreal', 'Music is my guide through life\'s ups and downs.', 'SonicNavigator');
INSERT INTO `t_music` VALUES (20, 20, 'http://8.217.178.86:8081/music/20.mp3', 'http://8.217.178.86:8081/photo/20.jpg', 'Dylan Emmet', 'What\'s The Point!', 'A song that lights up the darkest days.', 'BeaconBeat');
INSERT INTO `t_music` VALUES (21, 21, 'http://8.217.178.86:8081/music/21.mp3', 'http://8.217.178.86:8081/photo/21.jpg', 'Jay Someday', '4 You', 'Melodies mapping the journey of my heart.', 'HeartMap');
INSERT INTO `t_music` VALUES (22, 22, 'http://8.217.178.86:8081/music/22.mp3', 'http://8.217.178.86:8081/photo/22.jpg', 'Markvard', 'Contagious', 'Through music, I find clarity.', 'ClearTunes');
INSERT INTO `t_music` VALUES (23, 23, 'http://8.217.178.86:8081/music/23.mp3', 'http://8.217.178.86:8081/photo/23.jpg', 'KV', 'Gate', 'Living for moments when the song speaks to me.', 'MomentSeeker');
INSERT INTO `t_music` VALUES (24, 24, 'http://8.217.178.86:8081/music/24.mp3', 'http://8.217.178.86:8081/photo/24.jpg', 'Broke in Summer', 'How Have You Been', 'Drowning in the melody, emerging with hope.', 'HopefulListener');
INSERT INTO `t_music` VALUES (25, 25, 'http://8.217.178.86:8081/music/25.mp3', 'http://8.217.178.86:8081/photo/25.jpg', 'Alex-Productions', 'Love', 'Songs like this are rare treasures.', 'MusicalGem');
INSERT INTO `t_music` VALUES (26, 26, 'http://8.217.178.86:8081/music/26.mp3', 'http://8.217.178.86:8081/photo/26.jpg', 'LiQWYD', 'Mwah', 'Feeling energized by every beat.', 'RhythmicEnergy');
INSERT INTO `t_music` VALUES (27, 27, 'http://8.217.178.86:8081/music/27.mp3', 'http://8.217.178.86:8081/photo/27.jpg', 'Tomh.', 'Night Heat', 'This song, my escape to a better place.', 'EscapeArtist');
INSERT INTO `t_music` VALUES (28, 28, 'http://8.217.178.86:8081/music/28.mp3', 'http://8.217.178.86:8081/photo/28.jpg', 'jiglr', 'Night Rider', 'The rhythm moves me in ways words can\'t.', 'BeyondWords');
INSERT INTO `t_music` VALUES (29, 29, 'http://8.217.178.86:8081/music/29.mp3', 'http://8.217.178.86:8081/photo/29.jpg', 'LiQWYD', 'Perfect', 'Music has the power to make everything alright.', 'SonicSoothe');
INSERT INTO `t_music` VALUES (30, 30, 'http://8.217.178.86:8081/music/30.mp3', 'http://8.217.178.86:8081/photo/30.jpg', 'Atch ft. Michael Shynes', 'Right Here', 'Letting this melody take the lead in my life\'s dance.', 'LifeDancer');
INSERT INTO `t_music` VALUES (31, 31, 'http://8.217.178.86:8081/music/31.mp3', 'http://8.217.178.86:8081/photo/31.jpg', 'LiQWYD', 'Still Love You', 'This song reminds me of the beauty in the world.', 'BeautySeeker');
INSERT INTO `t_music` VALUES (32, 32, 'http://8.217.178.86:8081/music/32.mp3', 'http://8.217.178.86:8081/photo/32.jpg', 'Tomh.', 'Summer Love', 'Finding my soul\'s echo in this melody.', 'SoulEcho');
INSERT INTO `t_music` VALUES (33, 33, 'http://8.217.178.86:8081/music/33.mp3', 'http://8.217.178.86:8081/photo/33.jpg', 'LiQWYD', 'Talk to me', 'This song is a reminder to keep dreaming.', 'DreamGuardian');
INSERT INTO `t_music` VALUES (34, 34, 'http://8.217.178.86:8081/music/34.mp3', 'http://8.217.178.86:8081/photo/34.jpg', 'Tomh.', 'Te Quiero', 'Music bridges the gap between souls.', 'SoulConnector');
INSERT INTO `t_music` VALUES (35, 35, 'http://8.217.178.86:8081/music/35.mp3', 'http://8.217.178.86:8081/photo/35.jpg', 'LiQWYD', 'Venture', 'With every note, a new piece of me awakens.', 'AwakeningMelody');
INSERT INTO `t_music` VALUES (36, 36, 'http://8.217.178.86:8081/music/36.mp3', 'http://8.217.178.86:8081/photo/36.jpg', 'LiQWYD', 'Wanna Be', 'Let your heart dance to the rhythm of this song.', 'HeartDancer');
INSERT INTO `t_music` VALUES (37, 37, 'http://8.217.178.86:8081/music/37.mp3', 'http://8.217.178.86:8081/photo/37.jpg', 'Sapajou & Yorgo H', 'Wasting Time', 'Turn up the volume, turn down the darkness.', 'LightSounds');
INSERT INTO `t_music` VALUES (38, 38, 'http://8.217.178.86:8081/music/38.mp3', 'http://8.217.178.86:8081/photo/38.jpg', 'Rexlambo', 'i was in love once', 'The soundtrack to my innermost thoughts.', 'InnerSoundtrack');
INSERT INTO `t_music` VALUES (39, 39, 'http://8.217.178.86:8081/music/39.mp3', 'http://8.217.178.86:8081/photo/39.jpg', 'Rexlambo ft. Mieri', 'let me go', 'Let the music carry your worries away.', 'WorryLifter');
INSERT INTO `t_music` VALUES (40, 40, 'http://8.217.178.86:8081/music/40.mp3', 'http://8.217.178.86:8081/photo/40.jpg', 'Rexlambo', 'soar', 'Every play of this song feels like the first time.', 'EternalNewness');
INSERT INTO `t_music` VALUES (41, 41, 'http://8.217.178.86:8081/music/41.mp3', 'http://8.217.178.86:8081/photo/41.jpg', 'Tom Hafner', '6th Floor', 'This song turns my day around, every time.', 'DayChanger');
INSERT INTO `t_music` VALUES (42, 42, 'http://8.217.178.86:8081/music/42.mp3', 'http://8.217.178.86:8081/photo/42.jpg', 'tubebackr', 'A Vibe', 'Diving deep into the sea of sound.', 'SoundDiver');
INSERT INTO `t_music` VALUES (43, 43, 'http://8.217.178.86:8081/music/43.mp3', 'http://8.217.178.86:8081/photo/43.jpg', 'Sakura Girl', 'After All', 'Finding the light in the melody’s embrace.', 'MelodicLight');
INSERT INTO `t_music` VALUES (44, 44, 'http://8.217.178.86:8081/music/44.mp3', 'http://8.217.178.86:8081/photo/44.jpg', 'MBB', 'Bali', 'Music, my constant companion through life’s journey.', 'JourneyCompanion');
INSERT INTO `t_music` VALUES (45, 45, 'http://8.217.178.86:8081/music/45.mp3', 'http://8.217.178.86:8081/photo/45.jpg', 'Ferco', 'Bárcsak', 'This song is a burst of energy and hope.', 'EnergeticHope');
INSERT INTO `t_music` VALUES (46, 46, 'http://8.217.178.86:8081/music/46.mp3', 'http://8.217.178.86:8081/photo/46.jpg', 'Qlowdy', 'Cozy Town', 'Floating on the wings of melody.', 'MelodyWings');
INSERT INTO `t_music` VALUES (47, 47, 'http://8.217.178.86:8081/music/47.mp3', 'http://8.217.178.86:8081/photo/47.jpg', 'HiLau', 'Disc Drive', 'This song is like a key, unlocking my true feelings.', 'EmotionalKey');
INSERT INTO `t_music` VALUES (48, 48, 'http://8.217.178.86:8081/music/48.mp3', 'http://8.217.178.86:8081/photo/48.jpg', 'Roa', 'Dreamer', 'The beat resonates with my heart’s desires.', 'HeartbeatEcho');
INSERT INTO `t_music` VALUES (49, 49, 'http://8.217.178.86:8081/music/49.mp3', 'http://8.217.178.86:8081/photo/49.jpg', 'Declan DP', 'Interstellar', 'Letting the music fill the spaces between my thoughts.', 'ThoughtFiller');
INSERT INTO `t_music` VALUES (50, 50, 'http://8.217.178.86:8081/music/50.mp3', 'http://8.217.178.86:8081/photo/50.jpg', 'tubebackr', 'Kimaya', 'Immersed in the melody, I find my true self.', 'SelfSeeker');
INSERT INTO `t_music` VALUES (51, 51, 'http://8.217.178.86:8081/music/51.mp3', 'http://8.217.178.86:8081/photo/51.jpg', 'Declan DP', 'Look At The Clouds', 'Every note tells a story worth listening to.', 'Storyteller');
INSERT INTO `t_music` VALUES (52, 52, 'http://8.217.178.86:8081/music/52.mp3', 'http://8.217.178.86:8081/photo/52.jpg', 'Mehul Choudhary', 'Memory', 'This song is a canvas for my emotions.', 'EmotionPainter');
INSERT INTO `t_music` VALUES (53, 53, 'http://8.217.178.86:8081/music/53.mp3', 'http://8.217.178.86:8081/photo/53.jpg', 'Roa', 'Natural', 'The rhythm of life captured in a melody.', 'LifeRhythm');
INSERT INTO `t_music` VALUES (54, 54, 'http://8.217.178.86:8081/music/54.mp3', 'http://8.217.178.86:8081/photo/54.jpg', 'Spiring', 'Ocean Melody', 'Finding my stride in the beat of this song.', 'StrideFinder');
INSERT INTO `t_music` VALUES (55, 55, 'http://8.217.178.86:8081/music/55.mp3', 'http://8.217.178.86:8081/photo/55.jpg', 'Ferco', 'One Day Or Day One', 'This music is a beacon in my darkest night.', 'NightlightMelody');
INSERT INTO `t_music` VALUES (56, 56, 'http://8.217.178.86:8081/music/56.mp3', 'http://8.217.178.86:8081/photo/56.jpg', 'Ambyion & GalaxyTones', 'Planetarium', 'With each beat, a step closer to peace.', 'PeaceWalker');
INSERT INTO `t_music` VALUES (57, 57, 'http://8.217.178.86:8081/music/57.mp3', 'http://8.217.178.86:8081/photo/57.jpg', 'Roa', 'Snowflakes', 'This melody is my sanctuary.', 'MelodicSanctuary');
INSERT INTO `t_music` VALUES (58, 58, 'http://8.217.178.86:8081/music/58.mp3', 'http://8.217.178.86:8081/photo/58.jpg', 'Jay Someday', 'Wake Up Call', 'Dancing through life with this song as my guide.', 'LifeDance');
INSERT INTO `t_music` VALUES (59, 59, 'http://8.217.178.86:8081/music/59.mp3', 'http://8.217.178.86:8081/photo/59.jpg', 'Sakura Girl', 'Wake Up', 'Music brings color to my black and white days.', 'ColorfulTunes');
INSERT INTO `t_music` VALUES (60, 60, 'http://8.217.178.86:8081/music/60.mp3', 'http://8.217.178.86:8081/photo/60.jpg', 'Spiring', 'Way To You (Instrumental)', 'This song rejuvenates my spirit.', 'SpiritReviver');
INSERT INTO `t_music` VALUES (61, 61, 'http://8.217.178.86:8081/music/61.mp3', 'http://8.217.178.86:8081/photo/61.jpg', 'Atch', 'All The Same (Sped Up)', 'Every rhythm speaks to the adventurer in me.', 'RhythmicAdventurer');
INSERT INTO `t_music` VALUES (62, 62, 'http://8.217.178.86:8081/music/62.mp3', 'http://8.217.178.86:8081/photo/62.jpg', 'Roa', 'Care', 'Music is the architect of my emotions.', 'EmotionArchitect');
INSERT INTO `t_music` VALUES (63, 63, 'http://8.217.178.86:8081/music/63.mp3', 'http://8.217.178.86:8081/photo/63.jpg', 'Scandinavianz', 'Coconuts', 'Finding hope in the harmony.', 'HarmonicHope');
INSERT INTO `t_music` VALUES (64, 64, 'http://8.217.178.86:8081/music/64.mp3', 'http://8.217.178.86:8081/photo/64.jpg', 'Roa', 'Day Off', 'This melody is the light guiding me home.', 'GuidingMelody');
INSERT INTO `t_music` VALUES (65, 65, 'http://8.217.178.86:8081/music/65.mp3', 'http://8.217.178.86:8081/photo/65.jpg', 'Alex-Productions', 'Happy Carnival', 'The beat breathes life back into my soul.', 'SoulBreather');
INSERT INTO `t_music` VALUES (66, 66, 'http://8.217.178.86:8081/music/66.mp3', 'http://8.217.178.86:8081/photo/66.jpg', 'Roa', 'Haru', 'Swept away by the ocean of sound.', 'SoundSurfer');
INSERT INTO `t_music` VALUES (67, 67, 'http://8.217.178.86:8081/music/67.mp3', 'http://8.217.178.86:8081/photo/67.jpg', 'Aftertune', 'Kitty Time', 'Music is my therapy, my escape.', 'MusicTherapist');
INSERT INTO `t_music` VALUES (68, 68, 'http://8.217.178.86:8081/music/68.mp3', 'http://8.217.178.86:8081/photo/68.jpg', 'Roa', 'Last Summer', 'A song like this turns moments into memories.', 'MemoryMaker');
INSERT INTO `t_music` VALUES (69, 69, 'http://8.217.178.86:8081/music/69.mp3', 'http://8.217.178.86:8081/photo/69.jpg', 'Scandinavianz', 'Naxos', 'This melody holds the key to my heart.', 'HeartKey');
INSERT INTO `t_music` VALUES (70, 70, 'http://8.217.178.86:8081/music/70.mp3', 'http://8.217.178.86:8081/photo/70.jpg', 'Alex-Productions', 'New One', 'In every beat, a reason to smile.', 'SmileRhythm');
INSERT INTO `t_music` VALUES (71, 71, 'http://8.217.178.86:8081/music/71.mp3', 'http://8.217.178.86:8081/photo/71.jpg', 'Scandinavianz', 'Paris Nights', 'Harmony that heals every wound', 'HarmonyHealer');
INSERT INTO `t_music` VALUES (72, 72, 'http://8.217.178.86:8081/music/72.mp3', 'http://8.217.178.86:8081/photo/72.jpg', 'Peyruis', 'Party', 'A single song, a thousand emotions', 'EmotionExplorer');
INSERT INTO `t_music` VALUES (73, 73, 'http://8.217.178.86:8081/music/73.mp3', 'http://8.217.178.86:8081/photo/73.jpg', 'Tokiwave', 'Peach House', 'The soundtrack to my best moments', 'BestMomentBand');
INSERT INTO `t_music` VALUES (74, 74, 'http://8.217.178.86:8081/music/74.mp3', 'http://8.217.178.86:8081/photo/74.jpg', 'KV', 'Pool', 'Music is the pulse of life', 'LifePulse');
INSERT INTO `t_music` VALUES (75, 75, 'http://8.217.178.86:8081/music/75.mp3', 'http://8.217.178.86:8081/photo/75.jpg', 'Scandinavianz', 'Sauna', 'Every lyric speaks to my soul', 'LyricSoulmate');
INSERT INTO `t_music` VALUES (76, 76, 'http://8.217.178.86:8081/music/76.mp3', 'http://8.217.178.86:8081/photo/76.jpg', 'Mehul Choudhary', 'Sunrise', 'Dance to the rhythm of hope', 'HopeDancer');
INSERT INTO `t_music` VALUES (77, 77, 'http://8.217.178.86:8081/music/77.mp3', 'http://8.217.178.86:8081/photo/77.jpg', 'Scandinavianz', 'Sunscreen', 'A melody that carries me away from it all', 'MelodyWings');
INSERT INTO `t_music` VALUES (78, 78, 'http://8.217.178.86:8081/music/78.mp3', 'http://8.217.178.86:8081/photo/78.jpg', 'LiQWYD', 'Told Me', 'Songs like these keep my dreams alive', 'DreamKeeper');
INSERT INTO `t_music` VALUES (79, 79, 'http://8.217.178.86:8081/music/79.mp3', 'http://8.217.178.86:8081/photo/79.jpg', 'LiQWYD', 'Up And Down', 'Turning my thoughts into melodies', 'ThoughtfulMelodies');
INSERT INTO `t_music` VALUES (80, 80, 'http://8.217.178.86:8081/music/80.mp3', 'http://8.217.178.86:8081/photo/80.jpg', 'Tokyo Music Walker', 'Where Do We Go', 'Music is my sanctuary in the chaos', 'ChaosSanctuary');
INSERT INTO `t_music` VALUES (81, 81, 'http://8.217.178.86:8081/music/81.mp3', 'http://8.217.178.86:8081/photo/81.jpg', 'Spiring', 'Alive', 'Each note a step towards happiness', 'HappyNotes');
INSERT INTO `t_music` VALUES (82, 82, 'http://8.217.178.86:8081/music/82.mp3', 'http://8.217.178.86:8081/photo/82.jpg', 'KV', 'Build', 'A beat that beats my fears', 'FearlessBeat');
INSERT INTO `t_music` VALUES (83, 83, 'http://8.217.178.86:8081/music/83.mp3', 'http://8.217.178.86:8081/photo/83.jpg', 'Tokyo Music Walker', 'Closing Time', 'Music, the friend that never lets me down', 'FaithfulMelody');
INSERT INTO `t_music` VALUES (84, 84, 'http://8.217.178.86:8081/music/84.mp3', 'http://8.217.178.86:8081/photo/84.jpg', 'Tokyo Music Walker', 'Coast to Coast', 'Finding joy in every beat', 'JoyfulBeats');
INSERT INTO `t_music` VALUES (85, 85, 'http://8.217.178.86:8081/music/85.mp3', 'http://8.217.178.86:8081/photo/85.jpg', 'Pyrosion', 'Dream State', 'A song that speaks when I cannot', 'VoicelessSpeaker');
INSERT INTO `t_music` VALUES (86, 86, 'http://8.217.178.86:8081/music/86.mp3', 'http://8.217.178.86:8081/photo/86.jpg', 'Spiring', 'Dreamy', 'The rhythm that moves mountains', 'MountainMover');
INSERT INTO `t_music` VALUES (87, 87, 'http://8.217.178.86:8081/music/87.mp3', 'http://8.217.178.86:8081/photo/87.jpg', 'Hotham', 'Drive Town', 'Melodies that mend the broken parts of me', 'MelodyMender');
INSERT INTO `t_music` VALUES (88, 88, 'http://8.217.178.86:8081/music/88.mp3', 'http://8.217.178.86:8081/photo/88.jpg', 'Mehul Choudhary', 'Ethereal', 'This tune turns my blues into brights', 'BluesBeater');
INSERT INTO `t_music` VALUES (89, 89, 'http://8.217.178.86:8081/music/89.mp3', 'http://8.217.178.86:8081/photo/89.jpg', 'LiQWYD', 'Hang On', 'Music that stitches the stars into my night sky', 'StarrySounds');
INSERT INTO `t_music` VALUES (90, 90, 'http://8.217.178.86:8081/music/90.mp3', 'http://8.217.178.86:8081/photo/90.jpg', 'Di Young', 'Minecraft', 'A harmony that sets my spirit free', 'SpiritFreedom');
INSERT INTO `t_music` VALUES (91, 91, 'http://8.217.178.86:8081/music/91.mp3', 'http://8.217.178.86:8081/photo/91.jpg', 'Filo Starquez', 'Panoramic', 'Every note is a brushstroke painting my emotions', 'EmotionalArtist');
INSERT INTO `t_music` VALUES (92, 92, 'http://8.217.178.86:8081/music/92.mp3', 'http://8.217.178.86:8081/photo/92.jpg', 'Ghostrifter Official', 'Serenity', 'Music, the ultimate journey', 'MusicalVoyager');
INSERT INTO `t_music` VALUES (93, 93, 'http://8.217.178.86:8081/music/93.mp3', 'http://8.217.178.86:8081/photo/93.jpg', 'tubebackr & HiLau', 'Subliminal', 'Beats that build bridges to my aspirations', 'BridgeBuilder');
INSERT INTO `t_music` VALUES (94, 94, 'http://8.217.178.86:8081/music/94.mp3', 'http://8.217.178.86:8081/photo/94.jpg', 'Filo Starquez', 'Summer Rain', 'Living in the rhythm, thriving in the beat', 'RhythmLifer');
INSERT INTO `t_music` VALUES (95, 95, 'http://8.217.178.86:8081/music/95.mp3', 'http://8.217.178.86:8081/photo/95.jpg', 'Filo Starquez', 'The Right Path', 'This song wraps around me like a blanket', 'SonicBlanket');
INSERT INTO `t_music` VALUES (96, 96, 'http://8.217.178.86:8081/music/96.mp3', 'http://8.217.178.86:8081/photo/96.jpg', 'Pyrosion', 'Thinking About You', 'Notes that navigate my soul\'s deepest waters', 'DeepWaterNavigator');
INSERT INTO `t_music` VALUES (97, 97, 'http://8.217.178.86:8081/music/97.mp3', 'http://8.217.178.86:8081/photo/97.jpg', 'GalaxyTones', 'Touched by the Sky', 'Turning up the music, lighting up my soul', 'SoulLighter');
INSERT INTO `t_music` VALUES (98, 98, 'http://8.217.178.86:8081/music/98.mp3', 'http://8.217.178.86:8081/photo/98.jpg', 'Hotham', 'Tranquil Times', 'Each beat, a whisper of the universe', 'CosmicWhisper');
INSERT INTO `t_music` VALUES (99, 99, 'http://8.217.178.86:8081/music/99.mp3', 'http://8.217.178.86:8081/photo/99.jpg', 'Filo Starquez', 'Wilderness', 'Songs that shape my path forward', 'PathShaper');
INSERT INTO `t_music` VALUES (100, 100, 'http://8.217.178.86:8081/music/100.mp3', 'http://8.217.178.86:8081/photo/100.jpg', 'Aftertune', 'another feeling', 'Let the rhythm rule your heart', 'RhythmRuler');
INSERT INTO `t_music` VALUES (101, 101, 'http://8.217.178.86:8081/music/101.mp3', 'http://8.217.178.86:8081/photo/101.jpg', 'Devyzed', 'Altered State', 'Music: my secret ingredient for a happy life', 'HappyTune');
INSERT INTO `t_music` VALUES (102, 102, 'http://8.217.178.86:8081/music/102.mp3', 'http://8.217.178.86:8081/photo/102.jpg', 'LiQWYD', 'Ascent', 'This melody speaks the language of my heart', 'HeartLinguist');
INSERT INTO `t_music` VALUES (103, 103, 'http://8.217.178.86:8081/music/103.mp3', 'http://8.217.178.86:8081/photo/103.jpg', 'Luke Hall', 'Awake', 'Songs that soar with my highest hopes', 'HighHopes');
INSERT INTO `t_music` VALUES (104, 104, 'http://8.217.178.86:8081/music/104.mp3', 'http://8.217.178.86:8081/photo/104.jpg', 'LiQWYD', 'Awaken', 'Melodies make memories eternal', 'MemoryMelody');
INSERT INTO `t_music` VALUES (105, 105, 'http://8.217.178.86:8081/music/105.mp3', 'http://8.217.178.86:8081/photo/105.jpg', 'Onycs', 'Bloom', 'Every chord a comfort', 'ChordComforter');
INSERT INTO `t_music` VALUES (106, 106, 'http://8.217.178.86:8081/music/106.mp3', 'http://8.217.178.86:8081/photo/106.jpg', 'Scandinavianz', 'Cape Verde', 'This tune teaches me to dream bigger', 'DreamTutor');
INSERT INTO `t_music` VALUES (107, 107, 'http://8.217.178.86:8081/music/107.mp3', 'http://8.217.178.86:8081/photo/107.jpg', 'Alex-Productions', 'Chinese New Year', 'Harmonies that hug the soul', 'SoulHugger');
INSERT INTO `t_music` VALUES (108, 108, 'http://8.217.178.86:8081/music/108.mp3', 'http://8.217.178.86:8081/photo/108.jpg', 'LiQWYD', 'City Dreams', 'Beats that bounce back brighter', 'BrightBeats');
INSERT INTO `t_music` VALUES (109, 109, 'http://8.217.178.86:8081/music/109.mp3', 'http://8.217.178.86:8081/photo/109.jpg', 'KV', 'Dimension', 'Music: the thread that weaves my days together', 'DayWeaver');
INSERT INTO `t_music` VALUES (110, 110, 'http://8.217.178.86:8081/music/110.mp3', 'http://8.217.178.86:8081/photo/110.jpg', 'Dylan Emmet', 'Don\'t Love Myself', 'Notes that knit my heart\'s desires', 'HeartKnitter');
INSERT INTO `t_music` VALUES (111, 111, 'http://8.217.178.86:8081/music/111.mp3', 'http://8.217.178.86:8081/photo/111.jpg', 'Devyzed', 'Drifting Away', 'Tunes that transport me to tranquility', 'TranquilTuner');
INSERT INTO `t_music` VALUES (112, 112, 'http://8.217.178.86:8081/music/112.mp3', 'http://8.217.178.86:8081/photo/112.jpg', 'Mehul Choudhary', 'Elevate', 'A song that sings to the rhythm of the rain', 'RainRhythm');
INSERT INTO `t_music` VALUES (113, 113, 'http://8.217.178.86:8081/music/113.mp3', 'http://8.217.178.86:8081/photo/113.jpg', 'Artificial.Music', 'Empathic Prosperity', 'Music melds the moments into magic', 'MagicMelodies');
INSERT INTO `t_music` VALUES (114, 114, 'http://8.217.178.86:8081/music/114.mp3', 'http://8.217.178.86:8081/photo/114.jpg', 'Baribal & Pold', 'Get Here', 'Melody that mirrors my mind', 'MindMirror');
INSERT INTO `t_music` VALUES (115, 115, 'http://8.217.178.86:8081/music/115.mp3', 'http://8.217.178.86:8081/photo/115.jpg', 'Tobjan', 'In Your Eyes', 'This beat brings me back to bliss', 'BlissfulBeats');
INSERT INTO `t_music` VALUES (116, 116, 'http://8.217.178.86:8081/music/116.mp3', 'http://8.217.178.86:8081/photo/116.jpg', 'LiQWYD', 'Moving On', 'Finding the melody in the mess', 'MelodyFinder');
INSERT INTO `t_music` VALUES (117, 117, 'http://8.217.178.86:8081/music/117.mp3', 'http://8.217.178.86:8081/photo/117.jpg', 'Mehul Choudhary', 'Starlight', 'Chords that carve out calm', 'CalmCarver');
INSERT INTO `t_music` VALUES (118, 118, 'http://8.217.178.86:8081/music/118.mp3', 'http://8.217.178.86:8081/photo/118.jpg', 'KV', 'Synapse', 'Let the music echo your essence', 'EssenceEcho');
INSERT INTO `t_music` VALUES (119, 119, 'http://8.217.178.86:8081/music/119.mp3', 'http://8.217.178.86:8081/photo/119.jpg', 'LiQWYD', 'To The Moon', 'The song that sews seams of serenity', 'SerenityStitcher');
INSERT INTO `t_music` VALUES (120, 120, 'http://8.217.178.86:8081/music/120.mp3', 'http://8.217.178.86:8081/photo/120.jpg', 'Onycs', 'Voyager', 'Beats that breathe beauty into my days', 'BeautyBreather');
INSERT INTO `t_music` VALUES (121, 121, 'http://8.217.178.86:8081/music/121.mp3', 'http://8.217.178.86:8081/photo/121.jpg', 'Alex-Productions', 'Beautiful Wonder', 'Melodies that move my world', 'WorldMover');
INSERT INTO `t_music` VALUES (122, 122, 'http://8.217.178.86:8081/music/122.mp3', 'http://8.217.178.86:8081/photo/122.jpg', 'Lucjo', 'Cairo', 'Harmonies that heal the heart', 'HeartHealer');
INSERT INTO `t_music` VALUES (123, 123, 'http://8.217.178.86:8081/music/123.mp3', 'http://8.217.178.86:8081/photo/123.jpg', 'Jay Someday', 'Care', 'The rhythm of the rain, the melody of the moon', 'MoonMelody');
INSERT INTO `t_music` VALUES (124, 124, 'http://8.217.178.86:8081/music/124.mp3', 'http://8.217.178.86:8081/photo/124.jpg', 'Mehul Choudhary', 'Cyberreality', 'A symphony for the soul’s story', 'SoulSymphony');
INSERT INTO `t_music` VALUES (125, 125, 'http://8.217.178.86:8081/music/125.mp3', 'http://8.217.178.86:8081/photo/125.jpg', 'Ferco', 'Fish Cannot Climb Trees', 'This song stitches stars into my sky', 'StarStitcher');
INSERT INTO `t_music` VALUES (126, 126, 'http://8.217.178.86:8081/music/126.mp3', 'http://8.217.178.86:8081/photo/126.jpg', 'Alex-Productions', 'Freedom', 'Rhythms that resonate with the real me', 'RealRhythm');
INSERT INTO `t_music` VALUES (127, 127, 'http://8.217.178.86:8081/music/127.mp3', 'http://8.217.178.86:8081/photo/127.jpg', 'jiglr', 'Lapse', 'Finding my pace in the playlist', 'PlaylistPacer');
INSERT INTO `t_music` VALUES (128, 128, 'http://8.217.178.86:8081/music/128.mp3', 'http://8.217.178.86:8081/photo/128.jpg', 'Alex-Productions', 'Majestic', 'Melodies that mold my morning mood', 'MorningMelody');
INSERT INTO `t_music` VALUES (129, 129, 'http://8.217.178.86:8081/music/129.mp3', 'http://8.217.178.86:8081/photo/129.jpg', 'Tokyo Music Walker', 'Nature\'s Beauty', 'Turn the track up, tune the world out', 'TuneTuner');
INSERT INTO `t_music` VALUES (130, 130, 'http://8.217.178.86:8081/music/130.mp3', 'http://8.217.178.86:8081/photo/130.jpg', 'Lucjo', 'Odyssey', 'This song simplifies the complex emotions', 'SimpleSound');
INSERT INTO `t_music` VALUES (131, 131, 'http://8.217.178.86:8081/music/101.mp3', 'http://8.217.178.86:8081/photo/101.jpg', 'Sakura Girl', 'Paris', 'Music magnifies the magic moments', 'MagicMagnifier');
INSERT INTO `t_music` VALUES (132, 132, 'http://8.217.178.86:8081/music/132.mp3', 'http://8.217.178.86:8081/photo/132.jpg', 'Artificial.Music & Mehul ShaRma', 'Rough Nights', 'Harmonies that hold history', 'HistoryHarmonist');
INSERT INTO `t_music` VALUES (133, 133, 'http://8.217.178.86:8081/music/133.mp3', 'http://8.217.178.86:8081/photo/133.jpg', 'ASHUTOSH', 'Royal', 'Every tune a thread in the tapestry of life', 'LifeWeaver');
INSERT INTO `t_music` VALUES (134, 134, 'http://8.217.178.86:8081/music/134.mp3', 'http://8.217.178.86:8081/photo/134.jpg', 'Artificial.Music', 'Skyline Mirage', 'Each song, a portal to peace', 'PeacePortal');
INSERT INTO `t_music` VALUES (135, 135, 'http://8.217.178.86:8081/music/135.mp3', 'http://8.217.178.86:8081/photo/135.jpg', 'Tomh.', 'Snowflakes', 'The rhythm that rewrites my reality', 'RealityRewriter');
INSERT INTO `t_music` VALUES (136, 136, 'http://8.217.178.86:8081/music/136.mp3', 'http://8.217.178.86:8081/photo/136.jpg', 'Tomh.', 'Steam', 'Notes that nourish my soul', 'SoulNourisher');
INSERT INTO `t_music` VALUES (137, 137, 'http://8.217.178.86:8081/music/137.mp3', 'http://8.217.178.86:8081/photo/137.jpg', 'TVARI', 'Venice Beach', 'Harmony that whispers wisdom', 'WisdomWhisperer');
INSERT INTO `t_music` VALUES (138, 138, 'http://8.217.178.86:8081/music/138.mp3', 'http://8.217.178.86:8081/photo/138.jpg', 'jiglr', 'Whisper', 'This melody melts away my worries', 'WorryMeltdown');
INSERT INTO `t_music` VALUES (139, 139, 'http://8.217.178.86:8081/music/139.mp3', 'http://8.217.178.86:8081/photo/139.jpg', 'Tomh.', 'Wistfulness', 'Finding solace in the symphony', 'SymphonySeeker');
INSERT INTO `t_music` VALUES (140, 140, 'http://8.217.178.86:8081/music/140.mp3', 'http://8.217.178.86:8081/photo/140.jpg', 'Nettson', 'emotion deepness', 'Music as my muse, inspiring every step', 'MusicMuse');

-- ----------------------------
-- Table structure for t_recommend
-- ----------------------------
DROP TABLE IF EXISTS `t_recommend`;
CREATE TABLE `t_recommend`  (
  `recommend_id` bigint NOT NULL AUTO_INCREMENT,
  `emotion_recommend_score` double(5, 2) NULL DEFAULT NULL COMMENT '心情推荐度',
  `gender_recommend_score` double(5, 2) NULL DEFAULT NULL COMMENT '性别推荐度',
  `gender` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `emotion` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '心情',
  PRIMARY KEY (`recommend_id`) USING BTREE,
  UNIQUE INDEX `unique_combination`(`recommend_id` ASC, `gender` ASC, `emotion` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_recommend
-- ----------------------------
INSERT INTO `t_recommend` VALUES (1, 0.95, 0.02, 'female', 'angry');
INSERT INTO `t_recommend` VALUES (2, 0.23, 0.37, 'female', 'angry');
INSERT INTO `t_recommend` VALUES (3, 0.64, 0.76, 'male', 'angry');
INSERT INTO `t_recommend` VALUES (4, 0.11, 0.92, 'female', 'angry');
INSERT INTO `t_recommend` VALUES (5, 0.58, 0.12, 'male', 'angry');
INSERT INTO `t_recommend` VALUES (6, 0.78, 0.42, 'male', 'angry');
INSERT INTO `t_recommend` VALUES (7, 0.53, 0.36, 'female', 'angry');
INSERT INTO `t_recommend` VALUES (8, 0.02, 0.44, 'female', 'angry');
INSERT INTO `t_recommend` VALUES (9, 0.30, 0.14, 'male', 'angry');
INSERT INTO `t_recommend` VALUES (10, 0.62, 0.66, 'female', 'angry');
INSERT INTO `t_recommend` VALUES (11, 0.19, 0.65, 'male', 'angry');
INSERT INTO `t_recommend` VALUES (12, 0.42, 0.08, 'female', 'angry');
INSERT INTO `t_recommend` VALUES (13, 0.39, 0.60, 'male', 'angry');
INSERT INTO `t_recommend` VALUES (14, 0.24, 0.78, 'female', 'angry');
INSERT INTO `t_recommend` VALUES (15, 0.62, 0.53, 'male', 'angry');
INSERT INTO `t_recommend` VALUES (16, 0.38, 0.53, 'female', 'angry');
INSERT INTO `t_recommend` VALUES (17, 0.82, 0.67, 'male', 'angry');
INSERT INTO `t_recommend` VALUES (18, 0.46, 0.61, 'male', 'angry');
INSERT INTO `t_recommend` VALUES (19, 0.57, 0.81, 'female', 'angry');
INSERT INTO `t_recommend` VALUES (20, 0.18, 0.93, 'female', 'angry');
INSERT INTO `t_recommend` VALUES (21, 0.72, 0.31, 'female', 'disgust');
INSERT INTO `t_recommend` VALUES (22, 0.96, 0.68, 'female', 'disgust');
INSERT INTO `t_recommend` VALUES (23, 0.44, 0.72, 'female', 'disgust');
INSERT INTO `t_recommend` VALUES (24, 0.19, 0.16, 'female', 'disgust');
INSERT INTO `t_recommend` VALUES (25, 0.59, 0.33, 'male', 'disgust');
INSERT INTO `t_recommend` VALUES (26, 0.36, 0.19, 'male', 'disgust');
INSERT INTO `t_recommend` VALUES (27, 0.83, 0.51, 'female', 'disgust');
INSERT INTO `t_recommend` VALUES (28, 0.75, 0.60, 'male', 'disgust');
INSERT INTO `t_recommend` VALUES (29, 0.95, 0.47, 'male', 'disgust');
INSERT INTO `t_recommend` VALUES (30, 0.25, 0.64, 'female', 'disgust');
INSERT INTO `t_recommend` VALUES (31, 0.37, 0.46, 'female', 'disgust');
INSERT INTO `t_recommend` VALUES (32, 0.68, 0.76, 'male', 'disgust');
INSERT INTO `t_recommend` VALUES (33, 0.58, 0.59, 'female', 'disgust');
INSERT INTO `t_recommend` VALUES (34, 0.21, 0.48, 'male', 'disgust');
INSERT INTO `t_recommend` VALUES (35, 0.29, 0.23, 'female', 'disgust');
INSERT INTO `t_recommend` VALUES (36, 0.67, 0.51, 'male', 'disgust');
INSERT INTO `t_recommend` VALUES (37, 0.30, 0.80, 'female', 'disgust');
INSERT INTO `t_recommend` VALUES (38, 0.07, 0.06, 'female', 'disgust');
INSERT INTO `t_recommend` VALUES (39, 0.38, 0.55, 'male', 'disgust');
INSERT INTO `t_recommend` VALUES (40, 0.47, 0.47, 'male', 'disgust');
INSERT INTO `t_recommend` VALUES (41, 0.26, 0.49, 'male', 'fear');
INSERT INTO `t_recommend` VALUES (42, 0.93, 0.62, 'female', 'fear');
INSERT INTO `t_recommend` VALUES (43, 0.63, 0.25, 'female', 'fear');
INSERT INTO `t_recommend` VALUES (44, 0.13, 0.53, 'female', 'fear');
INSERT INTO `t_recommend` VALUES (45, 0.61, 0.32, 'male', 'fear');
INSERT INTO `t_recommend` VALUES (46, 0.03, 0.74, 'male', 'fear');
INSERT INTO `t_recommend` VALUES (47, 0.85, 0.39, 'female', 'fear');
INSERT INTO `t_recommend` VALUES (48, 0.91, 0.31, 'male', 'fear');
INSERT INTO `t_recommend` VALUES (49, 0.14, 0.27, 'male', 'fear');
INSERT INTO `t_recommend` VALUES (50, 0.88, 0.56, 'female', 'fear');
INSERT INTO `t_recommend` VALUES (51, 0.20, 0.49, 'male', 'fear');
INSERT INTO `t_recommend` VALUES (52, 0.62, 0.65, 'female', 'fear');
INSERT INTO `t_recommend` VALUES (53, 0.06, 0.11, 'female', 'fear');
INSERT INTO `t_recommend` VALUES (54, 0.40, 0.95, 'male', 'fear');
INSERT INTO `t_recommend` VALUES (55, 0.89, 0.81, 'female', 'fear');
INSERT INTO `t_recommend` VALUES (56, 0.51, 0.37, 'female', 'fear');
INSERT INTO `t_recommend` VALUES (57, 0.59, 0.91, 'male', 'fear');
INSERT INTO `t_recommend` VALUES (58, 0.18, 0.56, 'female', 'fear');
INSERT INTO `t_recommend` VALUES (59, 0.60, 0.24, 'female', 'fear');
INSERT INTO `t_recommend` VALUES (60, 0.25, 0.06, 'male', 'fear');
INSERT INTO `t_recommend` VALUES (61, 0.62, 0.42, 'female', 'happy');
INSERT INTO `t_recommend` VALUES (62, 0.90, 0.81, 'female', 'happy');
INSERT INTO `t_recommend` VALUES (63, 0.23, 0.17, 'female', 'happy');
INSERT INTO `t_recommend` VALUES (64, 0.29, 0.99, 'female', 'happy');
INSERT INTO `t_recommend` VALUES (65, 0.28, 0.27, 'male', 'happy');
INSERT INTO `t_recommend` VALUES (66, 0.77, 0.29, 'female', 'happy');
INSERT INTO `t_recommend` VALUES (67, 0.77, 0.47, 'female', 'happy');
INSERT INTO `t_recommend` VALUES (68, 0.74, 0.60, 'male', 'happy');
INSERT INTO `t_recommend` VALUES (69, 0.07, 0.03, 'male', 'happy');
INSERT INTO `t_recommend` VALUES (70, 0.69, 0.59, 'male', 'happy');
INSERT INTO `t_recommend` VALUES (71, 0.59, 0.32, 'male', 'happy');
INSERT INTO `t_recommend` VALUES (72, 0.25, 0.73, 'male', 'happy');
INSERT INTO `t_recommend` VALUES (73, 0.38, 0.13, 'male', 'happy');
INSERT INTO `t_recommend` VALUES (74, 0.30, 0.88, 'male', 'happy');
INSERT INTO `t_recommend` VALUES (75, 0.87, 0.85, 'male', 'happy');
INSERT INTO `t_recommend` VALUES (76, 0.60, 0.13, 'male', 'happy');
INSERT INTO `t_recommend` VALUES (77, 0.86, 0.74, 'female', 'happy');
INSERT INTO `t_recommend` VALUES (78, 0.46, 0.89, 'female', 'happy');
INSERT INTO `t_recommend` VALUES (79, 0.64, 0.03, 'female', 'happy');
INSERT INTO `t_recommend` VALUES (80, 0.06, 0.61, 'male', 'happy');
INSERT INTO `t_recommend` VALUES (81, 0.52, 0.99, 'female', 'neutral');
INSERT INTO `t_recommend` VALUES (82, 0.00, 0.83, 'female', 'neutral');
INSERT INTO `t_recommend` VALUES (83, 0.16, 0.43, 'male', 'neutral');
INSERT INTO `t_recommend` VALUES (84, 0.02, 0.11, 'male', 'neutral');
INSERT INTO `t_recommend` VALUES (85, 0.17, 0.33, 'female', 'neutral');
INSERT INTO `t_recommend` VALUES (86, 0.83, 0.65, 'male', 'neutral');
INSERT INTO `t_recommend` VALUES (87, 0.90, 0.17, 'female', 'neutral');
INSERT INTO `t_recommend` VALUES (88, 0.30, 0.03, 'female', 'neutral');
INSERT INTO `t_recommend` VALUES (89, 0.14, 0.94, 'female', 'neutral');
INSERT INTO `t_recommend` VALUES (90, 0.65, 0.38, 'male', 'neutral');
INSERT INTO `t_recommend` VALUES (91, 0.48, 0.64, 'male', 'neutral');
INSERT INTO `t_recommend` VALUES (92, 0.80, 0.77, 'female', 'neutral');
INSERT INTO `t_recommend` VALUES (93, 0.05, 0.82, 'male', 'neutral');
INSERT INTO `t_recommend` VALUES (94, 0.25, 0.42, 'female', 'neutral');
INSERT INTO `t_recommend` VALUES (95, 0.51, 0.47, 'male', 'neutral');
INSERT INTO `t_recommend` VALUES (96, 0.77, 0.32, 'female', 'neutral');
INSERT INTO `t_recommend` VALUES (97, 0.60, 0.07, 'male', 'neutral');
INSERT INTO `t_recommend` VALUES (98, 0.57, 0.16, 'female', 'neutral');
INSERT INTO `t_recommend` VALUES (99, 0.07, 0.03, 'male', 'neutral');
INSERT INTO `t_recommend` VALUES (100, 0.65, 0.39, 'female', 'neutral');
INSERT INTO `t_recommend` VALUES (101, 0.92, 0.53, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (102, 0.88, 0.71, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (103, 0.44, 0.45, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (104, 0.25, 0.49, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (105, 0.98, 0.82, 'female', 'sad');
INSERT INTO `t_recommend` VALUES (106, 0.45, 0.71, 'female', 'sad');
INSERT INTO `t_recommend` VALUES (107, 0.84, 0.63, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (108, 0.30, 0.58, 'female', 'sad');
INSERT INTO `t_recommend` VALUES (109, 0.26, 0.31, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (110, 0.80, 0.78, 'female', 'sad');
INSERT INTO `t_recommend` VALUES (111, 0.10, 0.02, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (112, 0.06, 0.81, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (113, 0.92, 0.01, 'female', 'sad');
INSERT INTO `t_recommend` VALUES (114, 0.37, 0.01, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (115, 0.67, 0.54, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (116, 0.71, 0.58, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (117, 0.95, 0.56, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (118, 0.08, 0.54, 'female', 'sad');
INSERT INTO `t_recommend` VALUES (119, 0.71, 0.17, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (120, 0.01, 0.92, 'male', 'sad');
INSERT INTO `t_recommend` VALUES (121, 0.19, 0.20, 'female', 'surprise');
INSERT INTO `t_recommend` VALUES (122, 0.41, 0.86, 'female', 'surprise');
INSERT INTO `t_recommend` VALUES (123, 0.74, 0.51, 'female', 'surprise');
INSERT INTO `t_recommend` VALUES (124, 0.17, 0.82, 'male', 'surprise');
INSERT INTO `t_recommend` VALUES (125, 0.42, 0.37, 'male', 'surprise');
INSERT INTO `t_recommend` VALUES (126, 0.93, 0.81, 'female', 'surprise');
INSERT INTO `t_recommend` VALUES (127, 0.85, 0.49, 'male', 'surprise');
INSERT INTO `t_recommend` VALUES (128, 0.94, 0.07, 'male', 'surprise');
INSERT INTO `t_recommend` VALUES (129, 0.47, 0.75, 'female', 'surprise');
INSERT INTO `t_recommend` VALUES (130, 0.48, 0.35, 'female', 'surprise');
INSERT INTO `t_recommend` VALUES (131, 0.54, 0.76, 'female', 'surprise');
INSERT INTO `t_recommend` VALUES (132, 0.53, 0.15, 'female', 'surprise');
INSERT INTO `t_recommend` VALUES (133, 0.43, 0.61, 'male', 'surprise');
INSERT INTO `t_recommend` VALUES (134, 1.00, 0.70, 'female', 'surprise');
INSERT INTO `t_recommend` VALUES (135, 0.36, 0.34, 'male', 'surprise');
INSERT INTO `t_recommend` VALUES (136, 0.97, 0.06, 'female', 'surprise');
INSERT INTO `t_recommend` VALUES (137, 0.86, 0.05, 'male', 'surprise');
INSERT INTO `t_recommend` VALUES (138, 0.35, 0.65, 'female', 'surprise');
INSERT INTO `t_recommend` VALUES (139, 0.04, 0.60, 'male', 'surprise');
INSERT INTO `t_recommend` VALUES (140, 0.69, 0.76, 'male', 'surprise');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `work` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_login` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `UK_USERNAME`(`username` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'test1', 'admin_work', 'admin_country', 18, 'http://8.217.178.86:8081/avatar/9.png', '123@qq.com', '202cb962ac59075b964b07152d234b70', '2024-07-17 07:28:19', '2024-07-18 02:16:09');
INSERT INTO `t_user` VALUES (2, 'test2', NULL, NULL, NULL, NULL, '123@qq.com', '202cb962ac59075b964b07152d234b70', '2024-07-13 09:27:08', '2024-07-13 09:27:08');
INSERT INTO `t_user` VALUES (3, 'test3', '1', '1', 1, NULL, '123@whu.com', '202cb962ac59075b964b07152d234b70', '2024-07-13 08:35:43', '2024-07-13 08:35:43');
INSERT INTO `t_user` VALUES (4, '刘骥飞', NULL, NULL, NULL, 'http://8.217.178.86:8081/avatar/2.png', '123@qq.com', '202cb962ac59075b964b07152d234b70', '2024-07-15 03:13:35', '2024-07-15 03:13:35');
INSERT INTO `t_user` VALUES (5, '何正邦', '工作测试5', '国家测试', 11, 'avatar测试', '123@qq.com', '202cb962ac59075b964b07152d234b70', '2024-07-15 03:13:24', '2024-07-15 03:13:24');
INSERT INTO `t_user` VALUES (6, '陈毅飞', NULL, NULL, NULL, 'http://8.217.178.86:8081/avatar/98.png', '2992852190@qq.com', '202cb962ac59075b964b07152d234b70', '2024-07-15 03:13:44', '2024-07-15 03:13:44');
INSERT INTO `t_user` VALUES (7, '肖一帆', '街道工作者', '武汉', 19, NULL, '2663076859@qq.com', '202cb962ac59075b964b07152d234b70', '2024-07-15 03:17:01', '2024-07-15 03:17:01');
INSERT INTO `t_user` VALUES (8, 'test12211', '工作测试', '国家测试', 12, 'avatar测试', '123@qq.com', '202cb962ac59075b964b07152d234b70', '2024-07-17 08:59:08', '2024-07-17 08:59:08');

-- ----------------------------
-- Table structure for usermessages
-- ----------------------------
DROP TABLE IF EXISTS `usermessages`;
CREATE TABLE `usermessages`  (
  `mes_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `admin_id` int NOT NULL,
  `mes_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `mes_time` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`mes_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `admin_id`(`admin_id` ASC) USING BTREE,
  CONSTRAINT `usermessages_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `usermessages_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `admins` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of usermessages
-- ----------------------------
INSERT INTO `usermessages` VALUES (1, 1, 1, '公告：您是我们的VIP用户', 1721026741);
INSERT INTO `usermessages` VALUES (2, 1, 1, '通知：您的预约成功', 1721026741);
INSERT INTO `usermessages` VALUES (3, 2, 1, '提示：您的密码过于简单，请注意修改保护账号安全', 1721026741);
INSERT INTO `usermessages` VALUES (4, 2, 3, '警告：检测您给原神充值过，现在取消您的权限', 1721026741);

-- ----------------------------
-- Table structure for usertestresults
-- ----------------------------
DROP TABLE IF EXISTS `usertestresults`;
CREATE TABLE `usertestresults`  (
  `res_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `res_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `res_time` datetime NULL DEFAULT NULL,
  `belonging_id` int NOT NULL,
  PRIMARY KEY (`res_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `usertestresults_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of usertestresults
-- ----------------------------
INSERT INTO `usertestresults` VALUES (1, 3, '您的测试结果为重度抑郁，请您不要放弃，心晴一直陪伴在您左右。', '2024-07-12 15:26:33', 1);
INSERT INTO `usertestresults` VALUES (2, 2, '您的测试结果为中度抑郁，', '2024-07-12 15:26:48', 1);
INSERT INTO `usertestresults` VALUES (3, 2, '您的测试结果为重度焦虑，希望您在工作学习之余，也比忘了好好爱自己。', '2024-07-12 15:26:57', 4);
INSERT INTO `usertestresults` VALUES (4, 1, '您的测试结果为健康，恭喜您，心晴祝您接下来的每一天都是晴天。', '2024-07-12 15:27:05', 1);

SET FOREIGN_KEY_CHECKS = 1;
