package com.qiao.dao;

import com.qiao.entity.UserOrderItem;

import java.util.List;

public interface IOrderItemDao {
    /*��������ϸ��������ϸ��Ӧһ��������Ʒ��Ϣ�������ü��ϴ������뵽������ϸ�˼�����*/
	 boolean  addOrderItems(List<UserOrderItem> orederItems);
	/*��ȡ������ϸ���*/
	 int  generateOrderItemId();
    /*�쿴������ϸ*/
	 public List<UserOrderItem> findOrderItem();
}
