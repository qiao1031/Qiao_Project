package com.qiao.controller;

import com.qiao.entity.Cart;
import com.qiao.entity.Product;
import com.qiao.service.impl.CartService;
import com.qiao.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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


@WebServlet("/view/cart")
public class CartController extends HttpServlet{
	private static final long serialVersionUID = 5748915335940444742L;
	@Autowired
	ProductService productService;
	@Autowired
     CartService cartService;//就不能new了但是还得依赖service层，所以创建对象的权限交给了容器config-xml
        //所以需要把容器注入进来，因此还得为容器提供set方法或者构造方法，
	    // 但下面那用 mWebApplicationContext.getBean（）就不必注入了*/
public void init()throws ServletException{
	/*//把获取容器等操作放到init()中初始化，就可以对容器创建一次就可以了，也可以写在doget方法体内
	//获取的ioc容器
	WebApplicationContext mWebApplicationContext
			= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	//从容器中直接获取参数，都不需要注入  但还要转型               bean的id
	cartService=(CartService) mWebApplicationContext.getBean("cartService");
	//在用到的地方直接调用就行了*/
	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,this.getServletContext());
}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		     // TODO Auto-generated method stub
			//response.setHeader("Access-Control-Allow-Origin","*");//设置不同域
			response.setContentType("text/html;charset=UTF-8");//处理乱码
		    String operation=request.getParameter("operation");
		    if(operation!=null&&!operation.equals("")) {
		       if(operation.equals("1")) {
		    	  addCart(request,response);
		       }else if(operation.equals("2")) {
		    	   findAllCart(request,response);
		       }else if(operation.equals("3")) {
		    	  updateCart(request,response);
		       }else if(operation.equals("4")) {
		    	   deleteCart(request,response);
		       }else if(operation.equals("5")) {
		    	   findByCartId(request,response);
		       }
		    }else {
		    	System.out.println("CartServlet层输入操作有误");
		    }
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
	/*添加购物车*/
	public boolean  addCart(Cart cart) {
		return cartService.addCart(cart);
	}
	/**
	 * @throws IOException
	 * @throws ServletException
	 **/
	public void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart=new Cart();
		int  _id=0;//传的id
		int num=0;
		try {
			_id=Integer.parseInt(request.getParameter("id"));//将字符转转成int
			num=Integer.parseInt(request.getParameter("num"));//将字符转转成int
			/*//获取的ioc容器
			WebApplicationContext mWebApplicationContext
					= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			//从容器中直接获取参数，都不需要注入  但还要转型               bean的id
			pService=(ProductService) mWebApplicationContext.getBean("pService");*/
			Product product=productService.findProductById(_id);
			cart.setProduct(product);
			cart.setProductNum(num);
			boolean result=addCart(cart);
			if(result) {//查找成功
				System.out.println("加入购物车成功");
				findAllCart(request,response);
			}else {//查找失败
				System.out.println("加入购物车失败");
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
		/**
		 * 删除
		 * */
		public boolean  deleteCart(int cartId) {
			return cartService.deleteCart(cartId);
		}
		private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String strid=request.getParameter("id");
			int id=0;
			try {
	    		id=Integer.parseInt(strid);//将字符转转成int
	    		boolean result=cartService.deleteCart(id);
	    		if(result) {//删除成功
	    			findAllCart(request,response);
	    		}else {//删除失败
	    			System.out.println("删除id="+id+"的商品失败！！！！");
	    		}
	    	}catch(NumberFormatException e) {
	    		e.printStackTrace();
	    	}
	    	return;
		}

/*修改购物车*/
	public boolean  updateCart(Cart cart) {
		return cartService.updataeCart(cart);
	}
/**
 * 获取购物车中商品数量
 * @param  id 要修改的商品id
 * @param  num 修改后的数量
 * */
/**修改购物车数量*/
	public boolean updateCart(int id,int num) {
		return cartService.updateCart(id, num);
	}
	private void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cart cart=new Cart();
		int num=0;
		int id=0;
		boolean result=false;
		try {
			num=Integer.parseInt(request.getParameter("num"));//将字符串转为int
			id=Integer.parseInt(request.getParameter("id"));
			cart.setProductNum(num);
			cart.setId(id);
			result=updateCart(id,num);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		if(result) {
			System.out.println("购物车修改成功");
			findAllCart(request,response);
		}else {
			System.out.println("购物车修改失败");
		}
	}
	/**
		 * 查询购物车
		 * @throws IOException 
		 * @throws ServletException 
		 * */
	private void findAllCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String pageNo=request.getParameter("pageNo");
    	String pageSize=request.getParameter("pageSize");
		int _pageNo=1;int _pageSize=5;
		    //若没传pageNo和pageSize，再给转int类型，就会报异常了。所以要给这俩设置个默认值
    	    try {if(pageNo!=null&&pageSize!=null) {_pageNo=Integer.parseInt(pageNo); _pageSize=Integer.parseInt(pageSize); }
    	   }catch(NumberFormatException e) { e.printStackTrace(); }
		CartService cartservice =new CartService();
	    PageModel<Cart> pageModel= cartservice.findCartByPage(_pageNo,_pageSize);
        request.setAttribute("pageModel",pageModel);
    	request.getRequestDispatcher("ShowCartByPage.html").forward(request, response);*/
    	/*CartService cartservice=new CartService();*/
	    List<Cart> carts=cartService.findAllCart();
	    request.setAttribute("carts",carts);
    	request.getRequestDispatcher("ShowCart.jsp").forward(request, response);
		}
		/* *通过id查找展示单个购物车*/
		private Cart findByCartId(int id) {
			// TODO Auto-generated method stub
			return cartService.findCartById(id);
		}
		private void findByCartId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String strid=request.getParameter("id");
			int id=0;
			try {
	    		id=Integer.parseInt(strid);
	    		Cart cart=findByCartId(id);
	    		if(cart!=null) {
	    			 request.setAttribute("cart",cart);
	    		    	request.getRequestDispatcher("UpdateCart.jsp").forward(request, response);
	    		}else {
	    			System.out.println("查询id="+id+"的购物车失败！！！！");
	    		}
	    	}catch(NumberFormatException e) {
	    		e.printStackTrace();
	    	}
		}
		/**
		 * 获取购物车中商品数量
		 * *//*
		public int  getCartNum() {
			return cartService.getCartNum();
		};
		*/
}
