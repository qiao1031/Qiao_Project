package com.qiao.entity;

import java.io.Serializable;
import java.util.List;

//分页模型方法
public class PageModel <T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7881282621490826616L;
	//当前页面的数据集合
	private List<T> data;
	//总页数
	private int totalPage;
	//当前页面
	private int currentPage;
	
	
	public PageModel() {
		super();
	}
	public PageModel(List<T> data, int totalPage, int currentPage) {
		super();
		this.data = data;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getTotalPage() {
		return this.totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
