package com.qiao.dao;

import com.qiao.entity.UserOrderItem;

import java.util.List;

public interface IOrderItemDao {
    /*将订单明细（订单明细对应一条条的商品信息，所以用集合代表）加入到订单明细账集合中*/
	 boolean  addOrderItems(List<UserOrderItem> orederItems);
	/*获取订单明细编号*/
	 int  generateOrderItemId();
    /*察看订单明细*/
	 public List<UserOrderItem> findOrderItem();
}
