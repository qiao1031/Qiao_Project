package com.qiao.data;

import com.qiao.entity.*;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
	//账号集合
	public static List<Account> account=new ArrayList<Account>();
	//定义商品集合
	public static List<Product> products=new ArrayList<Product>();
	//购物车集合
	public static List<Cart> carts=new ArrayList<Cart>();
	//我的订单集合
	public  static  List<UserOrder> orders=new ArrayList<UserOrder>();
	//订单明细集合
	public  static  List<UserOrderItem> orderItems=new ArrayList<UserOrderItem>();
	//分页集合
    public static   List<PageModel>   pageModel=new ArrayList<PageModel>();
    static {
    	 Account acc=new Account(1,"admin","admin","qiao.com","man");
    	 account.add(acc);
     }

}
