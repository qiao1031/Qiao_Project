package com.qiao.service;

import com.qiao.entity.Cart;
import com.qiao.entity.PageModel;

import java.util.List;

public interface ICartService {

		/**
		 *
		 **/
		boolean  addCart(Cart cart);
		/**
		 *
		 * */
		boolean  deleteCart(int id);
		/**
		 *
		 * */
		boolean  updataeCart(Cart cart);
		/**
		 *
		 * */
		List<Cart> findAllCart();
		
		
		/**
		 *
		 * */
		public PageModel<Cart> findCartByPage(int pageNo, int pageSize);
		
		
		/**
		 *
		 * */
		int  getCartNum();
		/**
		*
		 * */
		boolean updateCart(int id, int num);
		/**
		 *
		 * */
		void  clearCart() ;
		/**
		 * */
		Cart  findCartById(int id);
}
