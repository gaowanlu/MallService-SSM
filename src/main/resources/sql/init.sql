DROP DATABASE IF exists MALL;
CREATE DATABASE MALL;
USE MALL;
#用户表
CREATE TABLE user(
    #UUID
    userId VARCHAR(32) unique NOT NULL primary key,
    password VARCHAR(25) NOT NULL,
    name VARCHAR(15) NOT NULL default '',
    sex varchar(2) NOT NULL check(sex in('男','女','其他')),
    headImgId varchar(32) NOT NULL,    #UUID
    money double NOT NULL default 0.0,
    email VARCHAR(25) NOT NULL
);

#图片表
CREATE TABLE img(
	#UUID
	imgId VARCHAR(32) NOT NULL unique primary key,
    imgType VARCHAR(20) NOT NULL CHECK(NOT(imgType="")),
    imgSize long default NULL,
    img mediumblob NOT NULL
);

#商品类型表
CREATE TABLE goodType(
	goodTypeId int unsigned auto_increment primary key,
    name VARCHAR(20) NOT NULL unique check(NOT(name=""))
);

#商品表
CREATE TABLE good(
	goodId VARCHAR(32) NOT NULL unique primary key,#UUID
    price double NOT NULL check (price > 0.0),
	name VARCHAR(32) NOT NULL,
    profile VARCHAR(100) NOT NULL,
    stock int unsigned NOT NULL default 0,
    soldSum int unsigned NOT NULL default 0,
    goodTypeId int unsigned NOT NULL,
    index(goodTypeId),
	foreign key(goodTypeId) references goodType(goodTypeId) ON UPDATE cascade ON delete cascade
);

#商品展览图
CREATE TABLE goodImg(
	#UUID
	goodImgId VARCHAR(32) NOT NULL unique primary key,
    #imgId
    imgId VARCHAR(32) NOT NULL,
    goodId VARCHAR(32) NOT NULL,
    index(goodId),
	foreign key(goodId) references good(goodId) ON UPDATE cascade ON delete cascade
);


#购物车
CREATE TABLE cart(
	cartId VARCHAR(32) NOT NULL unique primary key,#UUID
    goodId VARCHAR(32) NOT NULL,
    userId VARCHAR(32) NOT NULL,
    num int unsigned NOT NULL default 1,
    index(goodId),
    index(userId),
    #级联更新与删除
	foreign key(userId) references user(userId) ON UPDATE cascade ON delete cascade,
	foreign key(goodId) references good(goodId) ON UPDATE cascade ON delete cascade
);

#订单表
CREATE TABLE orders(
	#UUID
    orderId VARCHAR(32) NOT NULL unique primary key,
    userId VARCHAR(32) NOT NULL,
    #发货状态
    status VARCHAR(10) NOT NULL check(status in('待付款','待发货','已发货','已签收')),
    #物流联系电话
    phone CHAR(11) NOT NULL,
    #地址
    address VARCHAR(30) NOT NULL,
    #物流联系姓名
    name VARCHAR(14) NOT NULL,
    #物流单号
    logisticsNumber VARCHAR(30) NOT NULL,
    #物流服务商
    logisticsName VARCHAR(20) NOT NULL,
    index(userId),
	foreign key(userId) references user(userId) ON UPDATE cascade ON delete cascade
);

#父评论表
create TABLE fComment(
	fCommentId VARCHAR(32) NOT NULL unique primary key,
    goodId VARCHAR(32) NOT NULL,
    content VARCHAR(20) NOT NULL,
    time datetime NOT NULL,
    index(goodId),
	foreign key(goodId) references good(goodId) ON UPDATE cascade ON delete cascade
);
#子评论表
create TABLE sComment(
	sCommentId VARCHAR(32) NOT NULL unique primary key,
    fCommentId VARCHAR(32) NOT NULL,
    content VARCHAR(20) NOT NULL,
    time datetime NOT NULL,
    index(fCommentId),
	foreign key(fCommentId) references fComment(fCommentId) ON UPDATE cascade ON delete cascade
);
#管理员表
create TABLE admin(
	adminId int unsigned auto_increment not null unique primary key,
    userId VARCHAR(32) NOT NULL,
    index(userId),
	foreign key(userId) references user(userId) ON UPDATE cascade ON delete cascade
);
#用户暂存收货地址表
create TABLE address(
	addressId VARCHAR(32) NOT NULL primary key,
    userId VARCHAR(32) NOT NULL,
    phone CHAR(11) NOT NULL,
    address VARCHAR(30) NOT NULL,
    name VARCHAR(20) NOT NULL,
	index(userId),
	foreign key(userId) references user(userId) ON UPDATE cascade ON delete cascade
);

