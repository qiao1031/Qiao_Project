package com.qiao.entity;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * java实体类 javaBean
	 * 定义JavaBean
	 * (1)属性私有
	 * (2)提供有参/无参的构造方法
	 * （3）提供get/set方法
	 * （4）实现序列化接口
	 * */
		/**
		 * 
		 */
		private static final long serialVersionUID = -6185202905228190806L;
		private  int  id; //商品id
		private  String  name;//商品名称
		private  String  pdesc;//商品描述
		private  double  price;//商品价格
		private  String  rule;//商品规格
		private  String  image;
		private  int  stock=100;//库存



	     public Product() {
			super();
		}
		
		
		public Product(int id, String name, String pdesc, double price, String rule, String image, int stock) {
			super();
			this.id = id;
			this.name = name;
			this.pdesc = pdesc;
			this.price = price;
			this.rule = rule;
			this.image = image;
			this.stock = stock;
		}


		public Product( String name, String pdesc, double price, String rule,String image,int stock) {
			super();
			this.name = name;
			this.pdesc = pdesc;
			this.price = price;
			this.rule = rule;
			this.image=image;
			this.stock=stock;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPdesc() {
			return pdesc;
		}
		public void setPdesc(String pdesc) {
			this.pdesc = pdesc;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getRule() {
			return rule;
		}
		public void setRule(String rule) {
			this.rule = rule;
		}
		
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		@Override
		public String toString() {
			return "Product [id=" + id + ", name=" + name + ", desc=" + pdesc + ", price=" + price + ", rule=" + rule
					+ ", image=" + image + ", stock=" + stock + "]";
		}
		
		
}

