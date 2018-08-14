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
/*创建订单*/
	@Override
	public boolean createOrder() {
		// TODO Auto-generated method stub
		//step1:获取购物车中的购物信息  List<Cart>
	    List<Cart> carts= cartDao.findAllCart();
	    System.out.println("==="+carts);
	       if(carts==null||carts.size()<=0) {
		    return false;
	       }
	    //step2:生成订单实体类 UserOrder
	    UserOrder   userOrder=createUserOrder();
	    //step3:将购物信息集合转成订单明细集合List<UserOrderItem>
	    List<UserOrderItem> userOrderItem=new ArrayList<UserOrderItem>();
	    for(int i=0;i<carts.size();i++) {
			   Cart cart=carts.get(i);
		       UserOrderItem orderItem= Utils.convertToOrderItem(orderItemDao.generateOrderItemId(), userOrder.getOrder_no(), cart);
		       //step4:检验库存
		       if(orderItem.getQuantity()<=cart.getProduct().getStock()) {
 				   //库存充足
		    	   userOrderItem.add(orderItem);
 			   }else {//库存不足
 				  return false;  
 			   }
		}
	    //step5:计算订单价格
	    userOrder.setPayment(getOrderPrice(userOrderItem));
	    //step6:下单
	       orderDao.createOrder(userOrder);
		   orderItemDao.addOrderItems(userOrderItem);
	    //step7:扣库存
		   for(int  i=0;i<carts.size();i++) {
 			   Cart cart=carts.get(i);
 			   Product product=cart.getProduct();
 			   int leftStock=product.getStock()-cart.getProductNum();
 			   /*修改商品库存*/
 			   product.setStock(leftStock);
 			   productee.updateProduct(product);
 		   }
	    //step8:清空购物车
		   cartDao.clearCart();
		   return true;
   }
	    /**
		 * 生成订单实体类
		 * */
		public  UserOrder createUserOrder() {
			//new一个订单对象
			UserOrder order=new UserOrder();
			//设置订单id
			order.setId(orderDao.generateOrderId());
			/* * 设置订单编号
			 *  1s=1000ms  1970 1.1 - 当前
			 */
			order.setOrder_no(generateOrderNo());
			//创建订单时间
			order.setCreate_time(System.currentTimeMillis());
			return order;
		}
/*生成订单编号*/
		@Override
		public long generateOrderNo() {
			// TODO Auto-generated method stub
			return System.currentTimeMillis();
		}


	public int generateOrderId() {
		return 0;
	}
/*查看订单*/
	@Override
	public List<UserOrder> findOrder() {
		return orderDao.findOrder();
	}
/*查看订单明细*/
	@Override
	public List<UserOrderItem> findOrderItem() {
		return orderItemDao.findOrderItem();
	}

	/**
	 * 计算订单总价格
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
