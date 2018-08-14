package com.qiao.controller;

import com.qiao.dao.Mybatis.CartDaoMybatisImpl;
import com.qiao.entity.Product;
import com.qiao.entity.UserOrder;
import com.qiao.entity.UserOrderItem;
import com.qiao.service.impl.OrderServiceImpl;
import com.qiao.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/view/OrderServlet")
public class OrderController extends HttpServlet {
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
		super();
		// TODO Auto-generated constructor stub
	}
     @Autowired
	 private OrderServiceImpl os ;
	public void init()throws ServletException{
		/*WebApplicationContext mWebApplicationContext
				= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		//从容器中直接获取参数，都不需要注入  但还要转型               bean的id
		os=(OrderServiceImpl) mWebApplicationContext.getBean("os");*/
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,this.getServletContext());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if(operation.equals("1")) {
			createOrder(request,response);
		}else if(operation.equals("2")) {
			findOrder(request,response);
		}else if(operation.equals("3")) {
            findOrderItemByOrderno(request,response);
		}else if (operation.equals("4")){
            findOrderItem(request,response);
        }
	}
	/*查看订单*/
	private void findOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<UserOrder>  orders = os.findOrder();
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("ShowOrder.jsp").forward(request, response);
	}
	/*通过order_no查看单个订单明细*/
	private void findOrderItemByOrderno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  /*      String strid = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(strid);
            Product product = pService.findProductById(id);
            if (product != null) {//查询成功
                request.setAttribute("product", product);
                request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);
            } else {
                System.out.println("查询id=" + id + "的商品失败！！！！");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }*/


		String order_no=request.getParameter("order_no");
		long number=Long.parseLong(order_no);
		UserOrder items = os.findOrderItemByOrderno(number);
		request.setAttribute("items", items);
		request.getRequestDispatcher("ShowOrderItemById.jsp").forward(request, response);
	}
	/*查看所有的订单明细*/
	private void findOrderItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserOrderItem> items = os.findOrderItem();
		request.setAttribute("items", items);
		request.getRequestDispatcher("ShowOrderItem.jsp").forward(request, response);
	}

	public void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(os.createOrder()) {
            ProductServlet pServlet =new ProductServlet();
            pServlet.findAll(request, response);
        }else {

        }

	}
}
