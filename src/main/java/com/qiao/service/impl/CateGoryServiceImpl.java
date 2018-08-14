package com.qiao.service.impl;

import com.qiao.dao.Mybatis.CateGoryDaoMybatisImpl;
import com.qiao.entity.Category;
import com.qiao.entity.PageModel;
import com.qiao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateGoryServiceImpl implements CategoryService {
    @Autowired
	CateGoryDaoMybatisImpl cgd ;

	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		return cgd.addCategory(category);
	}
	@Override
	public boolean deleteCategory(int id) {
		// TODO Auto-generated method stub
		return cgd.deleteCategory(id);
	}
	@Override
	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		return cgd.updateCategory(category);
	}
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return cgd.findAll();
	}
	@Override
	public Category findCategoryById(int id) {
		// TODO Auto-generated method stub
		return cgd.findCategoryById(id);
	}
	@Override
	public PageModel<Category> findCategoryByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return cgd.findCategoryByPage(pageNo, pageSize);
	}

}
