package com.qiao.data;

import com.qiao.entity.*;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
	//�˺ż���
	public static List<Account> account=new ArrayList<Account>();
	//������Ʒ����
	public static List<Product> products=new ArrayList<Product>();
	//���ﳵ����
	public static List<Cart> carts=new ArrayList<Cart>();
	//�ҵĶ�������
	public  static  List<UserOrder> orders=new ArrayList<UserOrder>();
	//������ϸ����
	public  static  List<UserOrderItem> orderItems=new ArrayList<UserOrderItem>();
	//��ҳ����
    public static   List<PageModel>   pageModel=new ArrayList<PageModel>();
    static {
    	 Account acc=new Account(1,"admin","admin","qiao.com","man");
    	 account.add(acc);
     }

}
