package com.qiao.service;

import com.qiao.dao.ProductDao;
import com.qiao.entity.PageModel;
import com.qiao.entity.Product;

import java.util.List;


public interface IProductService {

    public  boolean addProduct(Product product);	

    public  List<Product> findAll();

    public  boolean  updateProduct(Product product);

    public  boolean deleteProduct(int id);

    public Product findProductById(int id);

    public PageModel<Product> findProductByPage(Integer pageNo, Integer pageSize);
    	
    
}
