DROP TABLE IF EXISTS `b_user`;
CREATE TABLE `b_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` int(11) NOT NULL,
  `password` varchar(200) not null,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=GBK;

DROP TABLE IF EXISTS `b_content`;
CREATE TABLE `b_content` (
  `articleId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '作者id',
  `articleTitle` varchar(200) not null comment'文章名',
  `articleText` text comment'文章内容',
  `tag` varchar(200) not null comment 'tag',
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=GBK;

DROP TABLE IF EXISTS `b_comment`;
CREATE TABLE `b_comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `reviewerId` int(11) NOT NULL COMMENT '评论者id',
  `articleId` int(11) not null comment'文章id',
  `commentTime` datetime comment'评论时间',
  `commentText` text not null comment '评论内容',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=GBK;

drop TABLE if exists `b_tag`;
CREATE TABLE `b_tag` (
  `tagId` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(100) NOT NULL COMMENT '作者id',
  PRIMARY KEY (`tagId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=GBK;
INSERT `b_tag` VALUES(null,'tag1');
INSERT `b_tag` VALUES(null,'tag2');
INSERT `b_tag` VALUES(null,'tag3');

drop TABLE if exists `b_follow`;
CREATE TABLE `b_follow` (
  `followlistId` int(11) NOT NULL AUTO_INCREMENT,
  `followingId` int(11) NOT NULL COMMENT '被关注者',
  `followerId` int(11) NOT NULL COMMENT '关注者',
  PRIMARY KEY (`followlistId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=GBK;
INSERT `b_follow` VALUES(null,1,2);
INSERT `b_follow` VALUES(null,2,1);
INSERT `b_follow` VALUES(null,1,3);

drop TABLE if exists `b_info`;
CREATE TABLE `b_info` (
  `infoId` int(11) NOT NULL AUTO_INCREMENT,
  `postUserId` int(11) NOT NULL COMMENT '发信人',
  `receiveUserId` int(11) NOT NULL COMMENT '收信人',
  `type` int(11) NOT NULL COMMENT '通知类型',
  `articleId` int(11)  COMMENT '文章Id',
  `isRead` decimal(1,0) NOT NULL COMMENT '已读符号',
  PRIMARY KEY (`infoId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=GBK;


use lcy;
drop TABLE if exists `b_userinfo`;
CREATE TABLE `b_userinfo` (
  `id` int(11) NOT NULL,
  `followerNum` int(11),
  `followingNum` int(11),
  `articleNum` int(11),
  `intro` VARCHAR(256),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=GBK;

insert into `b_userinfo` VALUES(1,0,1,2,"天天开心");
insert into `b_userinfo` VALUES(2,3,4,3,"好好学习");