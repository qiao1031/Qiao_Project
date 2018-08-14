
package com.qiao.controller;

import com.google.gson.Gson;
import com.qiao.entity.Cart;
import com.qiao.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class CartServletToFront
 */
@WebServlet("/view/CartServletToFront")
public class CartServletToFront extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	CartService cs;

	public void init()throws ServletException{

		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,this.getServletContext());
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServletToFront() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "3628800");*/
		String operation = request.getParameter("operation");
		if(operation.equals("1")) {
		//	addCart(request,response);
		}else if(operation.equals("2")) {
			findAllCart(request,response);
		}else if(operation.equals("3")) {
			deleteCart(request,response);
		}else if(operation.equals("4")) {
			updataeCart(request,response);
		}else if(operation.equals("5")) {
			findCartById(request,response);
		}
		
	}

	public boolean updataeCart(Cart cart) {

		return cs.updataeCart(cart);
	}
	private void updataeCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		int num = 0;
		int id =0;
		try {
			
			num = Integer.parseInt(request.getParameter("num"));
			id = Integer.parseInt(request.getParameter("id"));
			Cart cart = findCartById(id);
			cart.setProductNum(num);
			boolean result = updataeCart(cart);
			System.out.println(id);
			System.out.println(num);
			System.out.println("=======update=======");
			if(result) {
				System.out.println("修改成功");

				findAllCart(request, response);

			}else {
				System.out.println("修改失败");
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void findAllCart(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		System.out.println("========find=========");

		List<Cart> carts = cs.findAllCart();
         /*很重要*/
		String callback = request.getParameter("callback");

		PrintWriter pw = response.getWriter();
		Gson gson = new Gson();
		String result = gson.toJson(carts);
		System.out.println(result);
		pw.println(callback+"("+result+")");
		
	}

	
	/*删除购物车*/
	public boolean deleteCart(int id) {

		return cs.deleteCart(id);
	}
	private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		boolean result = false;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			result = deleteCart(id);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		if(result){
			System.out.println("删除购物车成功");
		}else {
			System.out.println("失败");
		}

		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/*
	 * 通过id寻找购物车
	 * */
	public Cart findCartById(int id) {

		return cs.findCartById(id);

	}
	private void findCartById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			Cart cart = findCartById(id);
			if(cart!=null) {
				String qiao = request.getParameter("qiao");
				PrintWriter pw = response.getWriter();
				Gson gson = new Gson();
				String result = gson.toJson(cart);
				System.out.println(result);
				pw.println(qiao+"("+result+")");
				//response.sendRedirect("http://127.0.0.1:8020/shop/updataeCart.html");
				
			}else {
				System.out.println("Fail");
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		
	}
}
