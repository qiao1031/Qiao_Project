
# �������ݿ�
create database if not exists business;

# ɾ�����ݿ�
drop  database business;

# �˻���   
 drop table  account;
 create table account( 
  accountid    int   primary key   auto_increment,
  username     varchar(20)  not null  comment ' �û���',
  password     varchar(20)  not null comment '����',
  ip           varchar(20)  null,
  sex          varchar(5) default 'man',
  unique index  _accountid_index(accountid ) 
 ); 
 
 
 #�������lasttime  timestamp
 alter table account add lasttime timestamp ; 
 # ɾ��lasttime
 alter table  account drop column lasttime;
 
 
#��ѯ select �ֶ� from  ����;
 select accountid,username,password,ip,sex from  account;
 select *  from  account;
 #�������� insert into ����(�ֶ���1,�ֶ���2...) values(�ֶ�ֵ1���ֶ�ֵ������)
 insert into account(username,password,ip) 
 values('admin','admin','localhost'),
 ('admin1','admin1','localhost'),
 ('admin2','admin2','localhost');
 
 #�޸����� update  ����  set  �ֶ���=ֵ,�ֶ���=ֵ where  �ֶ���=ֵ
 update account set password='admin' where accountid=1 ;
 # ɾ��ĳ������ delete from  ���� where  �ֶ���=ֵ
 delete from  account where accountid=2;
 
 # ��Ʒ��
	create table  product(
	  id     int     primary key   auto_increment,
	  name   varchar(40) not null comment '��Ʒ����',
	  pdesc  text    null comment '��Ʒ����',
	  price  decimal(10,2) comment '��Ʒ�۸�',
	  rule   varchar(50) null comment '��Ʒ���',
	  image  varchar(100) null comment '��ƷͼƬ'
	);
	
	create unique index _id_index on product(id);
	
	create unique index _id_name_index on product(id,name);
	
# ���ﳵ
	create table  cart(
	  id            int     primary key   auto_increment,
	  productid     int     comment 'ֵ��Դ��product���е�id',
	  productnum    int  comment '���빺�ﳵ����Ʒ����'
	);
#������


create table  userorder(
	  id          int     primary key   auto_increment,
	  order_no    bigint  not null,
	  user_id     int     comment '�û�id,��Դ���û���',
	  shipping_id int  comment '��ַid,��Դ�ڵ�ַ��',
	   payment  decimal(10,2) comment 'ʵ�ʸ�����',
	   payment_type int  comment '֧����ʽ',
	   postage   int   comment '�˷�',
	   status      int   comment '����״̬',
	    payment_time timestamp comment '֧��ʱ��',
	    send_time timestamp comment '����ʱ��',
	     end_time timestamp comment '�������ʱ��',
	    close_time timestamp comment '�����ر�ʱ��',
	    create_time timestamp comment '��������ʱ��',
	    update_time timestamp comment '����޸�ʱ��'
	);

# ������ϸ��
create table  userorderitem(
	  id          int     primary key   auto_increment,
	  order_no    bigint  not null,
	  user_id     int     comment '�û�id,��Դ���û���',
	  productid int  comment '��Ʒid,��Դ����Ʒ��',
	  product_name   varchar(40) not null comment '��Ʒ����',
	  product_image  text    null comment '��Ʒ ͼƬ',
	  current_unit_price  decimal(10,2) comment '����ʱ����Ʒ�ļ۸�',
	  total_price  decimal(10,2) comment '�ܽ��',
	  quantity int  comment '��������',
	  postage   int   comment '�˷�',
	  status      int   comment '����״̬',
	  create_time timestamp comment '��������ʱ��',
	  update_time timestamp comment '����޸�ʱ��'
	);

	
	
	#��¼
	select * from  account where username=  and  password= 

	# ��־��¼��
create table  logrecord(
	  id          int     primary key   auto_increment,
	  user_id     int     comment '�û�id,��Դ���û���',
    operation   varchar(150) not null comment '��¼�û������ĸ�����',
	  create_time timestamp comment '��־��¼ʱ��'
	);


	update  account set money=2000 where username='zhangsan';
		update  account set money=2000 where username='lisi';
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



	
