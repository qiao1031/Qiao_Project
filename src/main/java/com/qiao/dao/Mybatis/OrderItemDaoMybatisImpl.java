package com.qiao.dao.Mybatis;


import com.qiao.dao.IOrderItemDao;
import com.qiao.entity.UserOrderItem;
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
public class OrderItemDaoMybatisImpl implements IOrderItemDao {
    @Autowired
    private  SqlSession  sqlSession;
    /*将订单明细（订单明细对应一条条的商品信息，所以用集合代表）加入到订单明细账集合中*/
    @Override
    public boolean addOrderItems(List<UserOrderItem> orederItems) {

        IOrderItemDao oid = sqlSession.getMapper(IOrderItemDao.class);
        oid.addOrderItems(orederItems);
        return true;
    }

    @Override
    public int generateOrderItemId() {
        return 0;
    }

    @Override
    public List<UserOrderItem> findOrderItem() {

        IOrderItemDao od =  sqlSession.getMapper(IOrderItemDao.class);
        List<UserOrderItem> orderItems = od.findOrderItem();
        return orderItems;
    }

}
