package com.qiao.utils;

import com.qiao.entity.Cart;
import com.qiao.entity.UserOrderItem;

import java.util.Scanner;

public class Utils {

	
	/**
	  *键盘输入字符串
	*/
	public static String  input(String msg){
		@SuppressWarnings("resource")
		Scanner mScanner=new Scanner(System.in);
		System.out.print(msg);
		return mScanner.nextLine();
	}


	/**
	 *键盘输入数字
	 */
	public  static  int  inputInt(String  msg){
		@SuppressWarnings("resource")
		Scanner mScanner=new Scanner(System.in);
		System.out.print(msg);
		int operation=mScanner.nextInt();
		return operation;
	}

	/**
	 *键盘输入小数
	 */
	public  static  double  inputDouble(String  msg){
		
		@SuppressWarnings("resource")
		Scanner mScanner=new Scanner(System.in);
		System.out.print(msg);
		double operation=mScanner.nextDouble();
		return operation;
	}
	/*nextLine()将空格看作是字符串 的一部分，返回时作为字符串一并返回
	 * next()将空格看作是两个字符串的间隔，返回时不带着
	 * nextInt()返回值是int类型的，但将空格作为数据的间隔*/



	public static UserOrderItem convertToOrderItem(int id, long order_no, Cart cart) {
		UserOrderItem userOrderItem=new UserOrderItem();
		userOrderItem.setId(id);
		userOrderItem.setOrder_no(order_no);
	/*	System.out.println("cart="+cart);
		System.out.println("cart product="+cart.getProduct());*/
		userOrderItem.setProduct_id(cart.getProduct().getId());
		userOrderItem.setProduct_name(cart.getProduct().getName());
		userOrderItem.setProduct_image(cart.getProduct().getImage());
		userOrderItem.setCurrent_unit_price(cart.getProduct().getPrice());
		userOrderItem.setQuantity(cart.getProductNum());
		userOrderItem.setTotal_price(cart.getProduct().getPrice()*cart.getProductNum());
		userOrderItem.setCreate_time(System.currentTimeMillis());
		return userOrderItem;
		
	}
}
