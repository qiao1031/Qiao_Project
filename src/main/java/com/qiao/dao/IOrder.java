package com.qiao.dao;

import com.qiao.entity.UserOrder;

import java.util.List;

public interface IOrder {
   /*创建订单*/
   boolean  createOrder(UserOrder userOrder);
   /*获取订单编号*/
   public long generateOrderNo() ;
   /*查看订单*/
   List<UserOrder> findOrder();
   /*生成订单id*/
    int generateOrderId();
   /*根据订单编号查询订单*/
   UserOrder findOrderItemByOrderno(long order_no);

}
