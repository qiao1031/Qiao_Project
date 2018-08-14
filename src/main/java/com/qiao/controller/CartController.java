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
     CartService cartService;//�Ͳ���new�˵��ǻ�������service�㣬���Դ��������Ȩ�޽���������config-xml
        //������Ҫ������ע���������˻���Ϊ�����ṩset�������߹��췽����
	    // ���������� mWebApplicationContext.getBean�����Ͳ���ע����*/
public void init()throws ServletException{
	/*//�ѻ�ȡ�����Ȳ����ŵ�init()�г�ʼ�����Ϳ��Զ���������һ�ξͿ����ˣ�Ҳ����д��doget��������
	//��ȡ��ioc����
	WebApplicationContext mWebApplicationContext
			= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	//��������ֱ�ӻ�ȡ������������Ҫע��  ����Ҫת��               bean��id
	cartService=(CartService) mWebApplicationContext.getBean("cartService");
	//���õ��ĵط�ֱ�ӵ��þ�����*/
	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,this.getServletContext());
}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		     // TODO Auto-generated method stub
			//response.setHeader("Access-Control-Allow-Origin","*");//���ò�ͬ��
			response.setContentType("text/html;charset=UTF-8");//��������
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
		    	System.out.println("CartServlet�������������");
		    }
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
	/*��ӹ��ﳵ*/
	public boolean  addCart(Cart cart) {
		return cartService.addCart(cart);
	}
	/**
	 * @throws IOException
	 * @throws ServletException
	 **/
	public void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart=new Cart();
		int  _id=0;//����id
		int num=0;
		try {
			_id=Integer.parseInt(request.getParameter("id"));//���ַ�תת��int
			num=Integer.parseInt(request.getParameter("num"));//���ַ�תת��int
			/*//��ȡ��ioc����
			WebApplicationContext mWebApplicationContext
					= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			//��������ֱ�ӻ�ȡ������������Ҫע��  ����Ҫת��               bean��id
			pService=(ProductService) mWebApplicationContext.getBean("pService");*/
			Product product=productService.findProductById(_id);
			cart.setProduct(product);
			cart.setProductNum(num);
			boolean result=addCart(cart);
			if(result) {//���ҳɹ�
				System.out.println("���빺�ﳵ�ɹ�");
				findAllCart(request,response);
			}else {//����ʧ��
				System.out.println("���빺�ﳵʧ��");
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
		/**
		 * ɾ��
		 * */
		public boolean  deleteCart(int cartId) {
			return cartService.deleteCart(cartId);
		}
		private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String strid=request.getParameter("id");
			int id=0;
			try {
	    		id=Integer.parseInt(strid);//���ַ�תת��int
	    		boolean result=cartService.deleteCart(id);
	    		if(result) {//ɾ���ɹ�
	    			findAllCart(request,response);
	    		}else {//ɾ��ʧ��
	    			System.out.println("ɾ��id="+id+"����Ʒʧ�ܣ�������");
	    		}
	    	}catch(NumberFormatException e) {
	    		e.printStackTrace();
	    	}
	    	return;
		}

/*�޸Ĺ��ﳵ*/
	public boolean  updateCart(Cart cart) {
		return cartService.updataeCart(cart);
	}
/**
 * ��ȡ���ﳵ����Ʒ����
 * @param  id Ҫ�޸ĵ���Ʒid
 * @param  num �޸ĺ������
 * */
/**�޸Ĺ��ﳵ����*/
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
			num=Integer.parseInt(request.getParameter("num"));//���ַ���תΪint
			id=Integer.parseInt(request.getParameter("id"));
			cart.setProductNum(num);
			cart.setId(id);
			result=updateCart(id,num);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		if(result) {
			System.out.println("���ﳵ�޸ĳɹ�");
			findAllCart(request,response);
		}else {
			System.out.println("���ﳵ�޸�ʧ��");
		}
	}
	/**
		 * ��ѯ���ﳵ
		 * @throws IOException 
		 * @throws ServletException 
		 * */
	private void findAllCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String pageNo=request.getParameter("pageNo");
    	String pageSize=request.getParameter("pageSize");
		int _pageNo=1;int _pageSize=5;
		    //��û��pageNo��pageSize���ٸ�תint���ͣ��ͻᱨ�쳣�ˡ�����Ҫ���������ø�Ĭ��ֵ
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
		/* *ͨ��id����չʾ�������ﳵ*/
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
	    			System.out.println("��ѯid="+id+"�Ĺ��ﳵʧ�ܣ�������");
	    		}
	    	}catch(NumberFormatException e) {
	    		e.printStackTrace();
	    	}
		}
		/**
		 * ��ȡ���ﳵ����Ʒ����
		 * *//*
		public int  getCartNum() {
			return cartService.getCartNum();
		};
		*/
}
