package com.qiao.dao;

import com.qiao.entity.PageModel;
import com.qiao.entity.Product;

import java.util.List;


public interface ProductDao {
	/**
	  *
	  * */
	boolean  addProduct(Product product);
	/**
	 *
	 * */
	List<Product> findAll();
	/**
	 *
	 * */
	boolean  updateProduct(Product product);
	/**
	 *
	 * */
	boolean  deleteProduct(int id);
	
	/***/
	Product findProductById(int id);
    /**  */
	public PageModel<Product> findProductByPage(int pageNo, int pageSize);
}
