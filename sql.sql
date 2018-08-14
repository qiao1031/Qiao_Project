
# 创建数据库
create database if not exists business;

# 删除数据库
drop  database business;

# 账户表   
 drop table  account;
 create table account( 
  accountid    int   primary key   auto_increment,
  username     varchar(20)  not null  comment ' 用户名',
  password     varchar(20)  not null comment '密码',
  ip           varchar(20)  null,
  sex          varchar(5) default 'man',
  unique index  _accountid_index(accountid ) 
 ); 
 
 
 #添加新列lasttime  timestamp
 alter table account add lasttime timestamp ; 
 # 删除lasttime
 alter table  account drop column lasttime;
 
 
#查询 select 字段 from  表名;
 select accountid,username,password,ip,sex from  account;
 select *  from  account;
 #插入数据 insert into 表名(字段名1,字段名2...) values(字段值1，字段值。。。)
 insert into account(username,password,ip) 
 values('admin','admin','localhost'),
 ('admin1','admin1','localhost'),
 ('admin2','admin2','localhost');
 
 #修改数据 update  表名  set  字段名=值,字段名=值 where  字段名=值
 update account set password='admin' where accountid=1 ;
 # 删除某条数据 delete from  表名 where  字段名=值
 delete from  account where accountid=2;
 
 # 商品表
	create table  product(
	  id     int     primary key   auto_increment,
	  name   varchar(40) not null comment '商品名称',
	  pdesc  text    null comment '商品描述',
	  price  decimal(10,2) comment '商品价格',
	  rule   varchar(50) null comment '商品规格',
	  image  varchar(100) null comment '商品图片'
	);
	
	create unique index _id_index on product(id);
	
	create unique index _id_name_index on product(id,name);
	
# 购物车
	create table  cart(
	  id            int     primary key   auto_increment,
	  productid     int     comment '值来源于product表中的id',
	  productnum    int  comment '加入购物车的商品数量'
	);
#订单表


create table  userorder(
	  id          int     primary key   auto_increment,
	  order_no    bigint  not null,
	  user_id     int     comment '用户id,来源于用户表',
	  shipping_id int  comment '地址id,来源于地址表',
	   payment  decimal(10,2) comment '实际付款金额',
	   payment_type int  comment '支付方式',
	   postage   int   comment '运费',
	   status      int   comment '订单状态',
	    payment_time timestamp comment '支付时间',
	    send_time timestamp comment '发货时间',
	     end_time timestamp comment '订单完成时间',
	    close_time timestamp comment '订单关闭时间',
	    create_time timestamp comment '订单创建时间',
	    update_time timestamp comment '最后修改时间'
	);

# 订单明细表
create table  userorderitem(
	  id          int     primary key   auto_increment,
	  order_no    bigint  not null,
	  user_id     int     comment '用户id,来源于用户表',
	  productid int  comment '商品id,来源于商品表',
	  product_name   varchar(40) not null comment '商品名称',
	  product_image  text    null comment '商品 图片',
	  current_unit_price  decimal(10,2) comment '付款时该商品的价格',
	  total_price  decimal(10,2) comment '总金额',
	  quantity int  comment '购买数量',
	  postage   int   comment '运费',
	  status      int   comment '订单状态',
	  create_time timestamp comment '订单创建时间',
	  update_time timestamp comment '最后修改时间'
	);

	
	
	#登录
	select * from  account where username=  and  password= 

	# 日志记录表
create table  logrecord(
	  id          int     primary key   auto_increment,
	  user_id     int     comment '用户id,来源于用户表',
    operation   varchar(150) not null comment '记录用户操作哪个方法',
	  create_time timestamp comment '日志记录时间'
	);


	update  account set money=2000 where username='zhangsan';
		update  account set money=2000 where username='lisi';
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



	
