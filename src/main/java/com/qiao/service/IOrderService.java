package com.qiao.service;

import com.qiao.entity.UserOrder;
import com.qiao.entity.UserOrderItem;

import java.util.List;

public interface IOrderService {
	/*��������*/
    boolean  createOrder();
    /*���ɶ���ʵ����*/
    UserOrder createUserOrder();
    /*���ɶ������order_no*/
    long  generateOrderNo();
   /**�鿴����*/
    List<UserOrder> findOrder();
   /*�鿴������ϸ*/
    List<UserOrderItem> findOrderItem();
    /*���ݶ�����Ų�ѯ������ϸ*/
    UserOrder findOrderItemByOrderno(long order_no);
}
