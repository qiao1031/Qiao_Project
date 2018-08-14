create database if not exists business;
#删除数据库
drop database business;
create table category(
id int primary key auto_increment,
parent_id int(20) comment'父类id',
name  varchar(20)   not null  comment'类名',
status  int(20) comment'状态',
sort_order  int(20)  comment'排序编号',
create_time  varchar(50) comment'创建时间',
update_time varchar(50)  comment'更新时间'
);
#创建账户表
drop table account;
create table account(
  accountid int primary key auto_increment,
  username varchar(20) not null comment'用户名',
  password varchar(20) not null comment'密码',
  ip       varchar(20) null,
  sex      varchar(5) default 'man' ,
  unique index biemingq (accountid)
  );

 #添加新列lasttime timestamp
   //account是表名  lastime是要添加的列  timestamp是列对应的数据类型
 alter table account add lasttime timestamp;
 #删除lasttime
 alter table account drop column lasttime;
 alter table account drop column stock;
 #查询数据信息   select 字段 from 表名
  select accountid,username,passwod,ip,sex,from account;
   select * from account;
  #插入数据  insert info
  insert into account(username,password,ip)
  values ('admin,admin,localhost');
         ('admin1,admin1,localhost');
         ('admin2,admin2,localhost');
 insert into shangpin(name,pdesc,price,rule,image,stock)
 values ('phone7,"苹果手机","4800","150g",null,"1000"');
  #修改数据 update 表名 set 属性=新赋的值
  update account set password='123456';
  #修改单个数据 update 表名 set 字段名=值，字段名=值 where 字段名=值
  update account set password='admin'where accountid=1;
  #删除数据 update from 表名 where 字段名=值
  update from account where accountid=2;
  #创建商品表
create table shangpin(
     id   int   primary key auto_increment,
     name  varchar(20) not null comment'商品名称',
     pdesc text null comment'商品描述',
     price decimal(10 ,2)comment'商品价格',
     rule varchar(50) null comment'商品规格',
     image varchar(100) null comment'商品图片',
     stock int
);
create unique index biemingw on shangpin(id);
#展示表
show tables;
#创建购物车表
create table cart(
       id   int  primary key auto_increment,
       productid int comment'值来源于product表中的id',
       productnum int comment'加入购物车的商品数量'
);
#创建订单表
drop table userorder;
create table userorder(
       id int(11) not null  primary key auto_increment,
       order_no bigint(20) default null comment'订单号，唯一索引',
       user_id int(11) default null comment'用户id，来源于用户表',
       shipping_id int(11)  default null comment'地址id，来源于地址表',
       payment decimal (10,2) default null  comment'实际付款金额',
       payment_type int(4) default null comment'支付方式',
       postage int(10)  default null comment'运费',
       status  int(10)  default null comment'订单状态',
       payment_time datetime comment'支付时间',
       send_time datetime default null  comment'发货时间',
       end_time datetime  default null  comment'订单完成时间',
       close_time datetime default null   comment'订单关闭时间',
       create_time datetime default null  comment'订单创建时间',
       update_time datetime  default null   comment'最后修改时间',
       unique  key unique_order_no(order_no)
);
#创建订单明细表
drop table userorderitem;
create table userorderitem(
      id int(11)    not null primary key auto_increment,
      order_no bigint(20) default null  comment'订单号，唯一索引',
      user_id int(11) default null comment'用户id，来源于用户表',
      productid int(11)   default null  comment'商品id，来源于商品表',
      product_name varchar(100) default null comment'商品名称',
      product_image varchar(500)  default null comment'商品图片',
      current_unit_price decimal(10,2)  default null comment'付款时该商品的金额',
      total_price decimal(20,2)  default null comment'总金额',
      payment decimal (10,2) default null  comment'实际付款金额',
      quantity int(10) default null comment'购买数量',
      postage int(10)  default null comment'运费',
      status  int(10)  default null comment'订单状态',
      create_time datetime default null  comment'订单创建时间',
      update_time datetime  default null   comment'最后修改时间',
      key unique_order_no(order_no),
       key index_userid_orderno(user_id,order_no)
);

组合索引 key index_userid_orderno(order_id,order_no)