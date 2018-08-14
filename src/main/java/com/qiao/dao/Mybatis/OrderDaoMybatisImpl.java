package com.qiao.dao.Mybatis;

import com.qiao.dao.IOrder;
import com.qiao.entity.UserOrder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Repository
public class OrderDaoMybatisImpl implements IOrder {
    @Autowired
    private  SqlSession  sqlSession;
    @Override
    public boolean createOrder(UserOrder userOrder) {

        IOrder od =  sqlSession.getMapper(IOrder.class);
        od.createOrder(userOrder);
        return true;
    }
/*获取订单编号*/
    @Override
    public long generateOrderNo() {
        // TODO Auto-generated method stub
        return System.currentTimeMillis(); 
    }
/*查看订单*/
    public List<UserOrder> findOrder() {

        IOrder od =  sqlSession.getMapper(IOrder.class);
        List<UserOrder> orders = od.findOrder();
        return orders;
    }

    @Override
    public int generateOrderId() {
        return 0;
    }

/**
 * 通过订单编号查询订单
 * 在映射文件里是通过args0和args1来获取参数的
 * args0   第一个参数
 * args1   第二个参数
 *   .
 *   。
 *    。
 * */
    public UserOrder findOrderItemByOrderno(long order_no) {
        IOrder od =  sqlSession.getMapper(IOrder.class);
        UserOrder orders = od.findOrderItemByOrderno(order_no);
        return orders;
    }
}
