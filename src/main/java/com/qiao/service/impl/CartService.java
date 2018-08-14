package com.qiao.service.impl;

import com.qiao.dao.Mybatis.CartDaoMybatisImpl;
import com.qiao.entity.Cart;
import com.qiao.entity.PageModel;
import com.qiao.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartService implements ICartService {
	@Autowired
	CartDaoMybatisImpl cartDao;

	
	public boolean addCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartDao.addCart(cart);
	}

	public boolean deleteCart(int cartId) {
		// TODO Auto-generated method stub
		return cartDao.deleteCart(cartId);
	}

	public boolean updataeCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartDao.updataeCart(cart);
	}
	public boolean updateCart(int id, int num) {
		// TODO Auto-generated method stub
		return cartDao.updateCart(id, num);
	}
	public List<Cart> findAllCart() {
		// TODO Auto-generated method stub
		return cartDao.findAllCart();
	}
	@Override
	public Cart findCartById(int id) {
		// TODO Auto-generated method stub
		return cartDao.findCartById(id) ;
	}
	public int getCartNum() {
		// TODO Auto-generated method stub
		return cartDao.getCartNum();
	}
	@Override
	public void clearCart() {
		// TODO Auto-generated method stub
		cartDao.clearCart();
	}

	@Override
	public PageModel<Cart> findCartByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return cartDao.findCartByPage(pageNo,pageSize);
	}

}
