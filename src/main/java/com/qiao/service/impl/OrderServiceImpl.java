package com.qiao.service.impl;

import com.qiao.dao.IOrder;
import com.qiao.dao.Mybatis.CartDaoMybatisImpl;
import com.qiao.dao.Mybatis.OrderDaoMybatisImpl;
import com.qiao.dao.Mybatis.OrderItemDaoMybatisImpl;
import com.qiao.dao.Mybatis.ProductMybatisImpl;
import com.qiao.entity.Cart;
import com.qiao.entity.Product;
import com.qiao.entity.UserOrder;
import com.qiao.entity.UserOrderItem;
import com.qiao.service.IOrderService;
import com.qiao.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
	CartDaoMybatisImpl cartDao;
	@Autowired
	OrderDaoMybatisImpl orderDao;
	@Autowired
	OrderItemDaoMybatisImpl orderItemDao;
	@Autowired
	ProductMybatisImpl productee;
/*��������*/
	@Override
	public boolean createOrder() {
		// TODO Auto-generated method stub
		//step1:��ȡ���ﳵ�еĹ�����Ϣ  List<Cart>
	    List<Cart> carts= cartDao.findAllCart();
	    System.out.println("==="+carts);
	       if(carts==null||carts.size()<=0) {
		    return false;
	       }
	    //step2:���ɶ���ʵ���� UserOrder
	    UserOrder   userOrder=createUserOrder();
	    //step3:��������Ϣ����ת�ɶ�����ϸ����List<UserOrderItem>
	    List<UserOrderItem> userOrderItem=new ArrayList<UserOrderItem>();
	    for(int i=0;i<carts.size();i++) {
			   Cart cart=carts.get(i);
		       UserOrderItem orderItem= Utils.convertToOrderItem(orderItemDao.generateOrderItemId(), userOrder.getOrder_no(), cart);
		       //step4:������
		       if(orderItem.getQuantity()<=cart.getProduct().getStock()) {
 				   //������
		    	   userOrderItem.add(orderItem);
 			   }else {//��治��
 				  return false;  
 			   }
		}
	    //step5:���㶩���۸�
	    userOrder.setPayment(getOrderPrice(userOrderItem));
	    //step6:�µ�
	       orderDao.createOrder(userOrder);
		   orderItemDao.addOrderItems(userOrderItem);
	    //step7:�ۿ��
		   for(int  i=0;i<carts.size();i++) {
 			   Cart cart=carts.get(i);
 			   Product product=cart.getProduct();
 			   int leftStock=product.getStock()-cart.getProductNum();
 			   /*�޸���Ʒ���*/
 			   product.setStock(leftStock);
 			   productee.updateProduct(product);
 		   }
	    //step8:��չ��ﳵ
		   cartDao.clearCart();
		   return true;
   }
	    /**
		 * ���ɶ���ʵ����
		 * */
		public  UserOrder createUserOrder() {
			//newһ����������
			UserOrder order=new UserOrder();
			//���ö���id
			order.setId(orderDao.generateOrderId());
			/* * ���ö������
			 *  1s=1000ms  1970 1.1 - ��ǰ
			 */
			order.setOrder_no(generateOrderNo());
			//��������ʱ��
			order.setCreate_time(System.currentTimeMillis());
			return order;
		}
/*���ɶ������*/
		@Override
		public long generateOrderNo() {
			// TODO Auto-generated method stub
			return System.currentTimeMillis();
		}


	public int generateOrderId() {
		return 0;
	}
/*�鿴����*/
	@Override
	public List<UserOrder> findOrder() {
		return orderDao.findOrder();
	}
/*�鿴������ϸ*/
	@Override
	public List<UserOrderItem> findOrderItem() {
		return orderItemDao.findOrderItem();
	}

	/**
	 * ���㶩���ܼ۸�
	 * */
	public  double  getOrderPrice(List<UserOrderItem> items) {
			double  totalPrice=0.0;
			for(int i=0;i<items.size();i++) {
				totalPrice+=items.get(i).getTotal_price();
			}
			return  totalPrice;
		}
	public UserOrder findOrderItemByOrderno(long order_no) {
		return orderDao.findOrderItemByOrderno(order_no);
	}

	
}
