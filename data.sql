use lcy;
DROP TABLE IF EXISTS `b_user`;
CREATE TABLE `b_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) not null,
  `intro` varchar(200),
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=GBK;

INSERT INTO `b_user` VALUES(NULL,'admin','123456',NULL);
INSERT INTO `b_user` VALUES(NULL,'lcy','lcy','HAHA');
INSERT INTO `b_user` VALUES(NULL,'guaguastandup','123456',NULL);


DROP TABLE IF EXISTS `b_content`;
CREATE TABLE `b_content` (
  `articleId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `articleTitle` varchar(150) NOT NULL,
  `articleText` text  character set gb2312 NOT NULL,
  `articleInfo` varchar(200),
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=gb2312;

drop table if exists `b_comment`;
CREATE TABLE `b_comment`(
`commentId` INT NOT NULL AUTO_INCREMENT,
`reviewerId` INT NOT NULL ,
`articleId` INT NOT NULL,
`commentTime` VARCHAR(100)  NOT NULL,
`commentText` VARCHAR(200) NOT NULL,
PRIMARY KEY(`commentId`) USING BTREE
);

drop TABLE if exists `b_tag`;
CREATE TABLE `b_tag` (
  `tagId` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(100) NOT NULL COMMENT '作者id',
  PRIMARY KEY (`tagId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=GBK;
INSERT `b_tag` VALUES(null,'tag1');
INSERT `b_tag` VALUES(null,'tag2');
INSERT `b_tag` VALUES(null,'tag3');

DROP TABLE IF EXISTS `b_connect`;
CREATE TABLE `b_connect` (
  `connectId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `articleId` int(11) NOT NULL,
  `tagName` varchar(150) NOT NULL,
  PRIMARY KEY (`connectId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

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

drop table if exists b_info;
CREATE TABLE `b_info`(
`infoId` INT NOT NULL AUTO_INCREMENT,
`postUserId` INT NOT NULL ,
`receiveUserId` INT NOT NULL,
`type` INT  NOT NULL,
`articleId` INT ,
`time` VARCHAR(50) NOT NULL,
PRIMARY KEY(`infoId`) USING BTREE
);
