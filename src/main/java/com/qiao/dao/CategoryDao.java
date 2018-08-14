package com.qiao.dao;

import com.qiao.entity.Category;
import com.qiao.entity.PageModel;

import java.util.List;


public interface CategoryDao {

		//增加类别
		boolean addCategory(Category category);
		//查看类别
		List<Category> findAll();
		//修改类别
		boolean updateCategory(Category category);
		//通过id删除类别
		boolean deleteCategory(int id);
		//类别通过id查找类别
		Category findCategoryById(int id);
		//分页查询类别
		PageModel<Category> findCategoryByPage(int pageNo, int pageSize);
}
