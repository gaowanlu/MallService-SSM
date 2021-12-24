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
    headImgId varchar(32) NOT NULL default 'default',    #UUID
    money double NOT NULL default 0.0,
    email VARCHAR(25) NOT NULL
);

#图片表
CREATE TABLE img(
	#UUID
	imgId VARCHAR(32) NOT NULL unique primary key,
    imgType VARCHAR(20) NOT NULL CHECK(NOT(imgType="")),
    imgSize int unsigned default NULL,
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
    onSale int unsigned NOT NULL check(onSale in (0,1)),
--    detail TEXT NOT NULL default '',
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
    status VARCHAR(10) NOT NULL check(status in('待付款','待发货','已发货','已签收','退款中','已退款')),
    #物流联系电话
    phone CHAR(11) NOT NULL,
    #地址
    address VARCHAR(30) NOT NULL,
    #物流联系姓名
    name VARCHAR(14) NOT NULL,
    #订单创建时间 
    time DATETIME NOT NULL,
    #物流单号
    logisticsNumber VARCHAR(30) NOT NULL,
    #物流服务商
    logisticsName VARCHAR(20) NOT NULL,
    #消费金额 
    priceCount double NOT NULL,
    index(userId),
	foreign key(userId) references user(userId) ON UPDATE cascade ON delete cascade
);

#父评论表
create TABLE fComment(
	fCommentId VARCHAR(32) NOT NULL unique primary key,
    goodId VARCHAR(32) NOT NULL,
    content VARCHAR(20) NOT NULL,
    time datetime NOT NULL,
    userId VARCHAR(32) NOT NULL,
    rate int unsigned NOT NULL check(rate in (0,1,2,3,4,5)),
    index(goodId),
	foreign key(goodId) references good(goodId) ON UPDATE cascade ON delete cascade,
    index(userId),
	foreign key(userId) references user(userId) ON UPDATE cascade ON delete cascade
);
#子评论表
create TABLE sComment(
	sCommentId VARCHAR(32) NOT NULL unique primary key,
    fCommentId VARCHAR(32) NOT NULL,
    content VARCHAR(20) NOT NULL,
    time datetime NOT NULL,
	userId VARCHAR(32) NOT NULL,
    index(fCommentId),
	foreign key(fCommentId) references fComment(fCommentId) ON UPDATE cascade ON delete cascade,
	index(userId),
	foreign key(userId) references user(userId) ON UPDATE cascade ON delete cascade
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


#订单所购买的物品项
CREATE TABLE orderGood(
    orderGoodId int unsigned auto_increment NOT NULL primary key,
    orderId VARCHAR(32) NOT NULL,
    goodId VARCHAR(32) NOT NULL,
    num int NOT NULL check(num>0),
    index(orderId),
	foreign key(orderId) references orders(orderId) ON UPDATE cascade ON delete cascade
);

# 触发器 
# 当新增订单商品时，被买的订单的物品的库存减去相对应的数量,与销量增加
DELIMITER $$
CREATE TRIGGER orderGood_after_insert
    AFTER
    INSERT
    ON orderGood
    FOR EACH ROW
BEGIN
    UPDATE good
    SET stock=good.stock-NEW.num,soldSum=good.soldSum+NEW.num
    WHERE goodId=NEW.goodId;
END$$
DELIMITER ;

# 当新增订单时，将响应用户余额减少为订单总价
DELIMITER $$
CREATE TRIGGER orders_after_insert
    AFTER
    INSERT
    ON orders
    FOR EACH ROW
BEGIN
    UPDATE user
    SET money=money-NEW.priceCount
    WHERE user.userId=NEW.userId;
END$$
DELIMITER ;



# 用户
INSERT INTO user VALUES('cd0bf38c57fe44d39d4b6a135fb8fc7e','123456','未知','男','default',10000.0,'moezrf@gmail.com');
INSERT INTO user VALUES('1','123456789','gaowanlu','男','default',10000.0,'2209120827@qq.com');

# 添加为管理员 
INSERT  INTO admin(userId) VALUES('cd0bf38c57fe44d39d4b6a135fb8fc7e');
INSERT  INTO admin(userId) VALUES('1');

#添加商品类型与商品
INSERT INTO goodType values(1,'手机');

INSERT INTO good values('1',634,'p50','好手机',100,300,1,1);
INSERT INTO cart VALUES('dsvdfvsdf','1','1',1);

INSERT INTO goodType values(2,'电脑');
INSERT INTO good values('2',645,'BX505','好电脑',100,300,2,1);
INSERT INTO cart VALUES('dsvdcdscs','2','1',2);

INSERT INTO img VALUES("dsds","csc",234,"");
INSERT INTO img VALUES("dsdsvfd","csc",234,"");
INSERT INTO img VALUES("dsdngms","csc",234,"");
INSERT INTO goodImg VALUES("csvfd1","dsds","1");
INSERT INTO goodImg VALUES("csvfd2","dsds","2");
INSERT INTO goodImg VALUES("csvfd3","dsdsvfd","1");
INSERT INTO goodImg VALUES("csvfd4","dsdsvfd","2");
INSERT INTO goodImg VALUES("csvfd5","dsdngms","1");
INSERT INTO goodImg VALUES("csvfd6","dsdngms","2");

#插入评论
#父评论
INSERT INTO fComment VALUES("2wdwef",'1','dfvdbdbfg',NOW(),'1',5);
INSERT INTO fComment VALUES("2wgef",'1','dfvnghg',NOW(),'1',5);
INSERT INTO fComment VALUES("2wdfef",'1','dfvadfg',NOW(),'1',5);
INSERT INTO fComment VALUES("2wsdf",'1','dfnfbfg',NOW(),'1',5);
INSERT INTO fComment VALUES("2wgfef",'1','dfghbfg',NOW(),'1',5);
INSERT INTO fComment VALUES("2whgef",'1','dfasddbfg',NOW(),'1',5);

#子评论
INSERT INTO sComment VALUES('vsdfbs1','2wdwef','vdsbfgnf',NOW(),'1');
INSERT INTO sComment VALUES('vsdfbs2','2wdwef','vdsbfgnf',NOW(),'1');
INSERT INTO sComment VALUES('vsdfbs3','2wdwef','vdsbfgnf',NOW(),'1');
INSERT INTO sComment VALUES('vsdfbs4','2wdwef','vdsbfgnf',NOW(),'1');

INSERT INTO sComment VALUES('vsdwfbs','2wgef','vdsbfgnf',NOW(),'1');
INSERT INTO sComment VALUES('vsdffbs','2wgef','vdsbfgnf',NOW(),'1');
INSERT INTO sComment VALUES('vsdafbs','2wgef','vdsbfgnf',NOW(),'1');
INSERT INTO sComment VALUES('vsdfgbs','2wgef','vdsbfgnf',NOW(),'1');

#插入收货地址 
INSERT INTO address VALUES('qwertyuioplkjhgfdsazxcv45678','1','13346637702','广西 桂林市 电子科技大学(花江校区)','高万禄');
INSERT INTO address VALUES('qwertyuioplkjhgfdsazxcv4568','1','13346637702','广西 桂林市 电子科技大学(花江校区)','高万禄');

#添加订单 
INSERT INTO orders VALUES('dscavf1','1','待发货','13346637702','桂林市','gaowanlu',now(),'','',123);
#添加订单物品
INSERT INTO orderGood(orderId,goodId,num) values('dscavf1','1',2);
INSERT INTO orderGood(orderId,goodId,num) values('dscavf1','2',2);
INSERT INTO orders VALUES('dscavf2','1','待发货','13346637702','桂林市','gaowanlu',now(),'','',123);
INSERT INTO orders VALUES('dscavf3','1','待发货','13346637702','桂林市','gaowanlu',now(),'','',123);
INSERT INTO orderGood(orderId,goodId,num) values('dscavf3','1',2);
INSERT INTO orders VALUES('dscavf4','1','待发货','13346637702','桂林市','gaowanlu',now(),'','',123); 

USE MALL;
ALTER TABLE good ADD detail TEXT NOT NULL;
ALTER TABLE good ADD detailImgId VARCHAR(32) NOT NULL default 'defaultDetail';
