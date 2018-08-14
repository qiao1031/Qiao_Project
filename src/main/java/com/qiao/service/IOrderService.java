package com.qiao.service;

import com.qiao.entity.UserOrder;
import com.qiao.entity.UserOrderItem;

import java.util.List;

public interface IOrderService {
	/*创建订单*/
    boolean  createOrder();
    /*生成订单实体类*/
    UserOrder createUserOrder();
    /*生成订单编号order_no*/
    long  generateOrderNo();
   /**查看订单*/
    List<UserOrder> findOrder();
   /*查看订单明细*/
    List<UserOrderItem> findOrderItem();
    /*根据订单编号查询订单明细*/
    UserOrder findOrderItemByOrderno(long order_no);
}
