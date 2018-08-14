package com.qiao.service.impl;

import com.qiao.dao.ProductDao;
import com.qiao.entity.PageModel;
import com.qiao.entity.Product;
import com.qiao.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Service
public class ProductService implements IProductService {
	@Autowired
	ProductDao productDao;
	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.addProduct(product);
	}
	@Override
	public boolean deleteProduct(int id) {
		// TODO Auto-generated method stub
		return productDao.deleteProduct(id);
	}
	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.updateProduct(product);
	}
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}
	@Override
	public Product findProductById(int id) {
		// TODO Auto-generated method stub
		return productDao.findProductById(id);
	}
	@Override
	public PageModel<Product> findProductByPage(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return productDao.findProductByPage(pageNo,pageSize);
	}

}
