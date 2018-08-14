package com.qiao.service;

import com.qiao.entity.Category;
import com.qiao.entity.PageModel;

import java.util.List;


public interface CategoryService {

			//
			boolean addCategory(Category category);
			//
			List<Category> findAll();
			//
			boolean updateCategory(Category category);
			//
			boolean deleteCategory(int id);
			//
			Category findCategoryById(int id);
			//
			PageModel<Category> findCategoryByPage(int pageNo, int pageSize);
}
