package com.qiao.controller;

import com.qiao.entity.Account;
import com.qiao.service.impl.LoginService;
import com.qiao.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/*
 *因为需要访问servlet，所以要通过访问其路径（即URL）进行访问
 * 分两种方式；
 * 1.通过注解：@WebServlet("/xxxxx")
 * 2.在lib下web.xml内配置
 */
@WebServlet("/Login.qiao")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = -3116360063055859984L;
	@Autowired
	LoginService loginService;
	public void init()throws  ServletException{
		/*WebApplicationContext mWebApplicationContext
				= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		//从容器中直接获取参数，都不需要注入  但还要转型               bean的id
		loginService=(LoginService) mWebApplicationContext.getBean("loginService");*/
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,this.getServletContext());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//response.setHeader("Access-Control-Allow-Origin","*");//处理不同域
			//下面写的是第一次登录浏览器，从登录页面输入uername password请求登录中获取账户密码
			 String username=request.getParameter("username");
			 String  password=request.getParameter("password");
				Account acc=loginService.doLogin(username, MD5Utils.GetMD5Code(password));
				if(acc!=null) {//登录成功                   key      value
					Cookie user_cookie=new Cookie("username",username);//写一个cookie的构造方法来创建cookie
					user_cookie.setMaxAge(7*24*3600);//给其设置存货时间
					//将cookie写到响应头当中，响应的都封装在response，所以调用response方法
					response.addCookie(user_cookie);
					
					Cookie pws_cookie=new Cookie("password", MD5Utils.GetMD5Code(password));//给密码通过MD5Utils包下的方法加密
					user_cookie.setMaxAge(7*24*3600);
					response.addCookie(pws_cookie);
					// 若再次登录时，过滤器就会识别操作意图，从请求头上如何获取cookie 转到LoginFilter.java 写代码
					
					//生成token,为保护后台登录页面后的各个页面，而后存放到数据库中   先获取token 的算法由username password 和当期时间后MD5Util  上面已经获取了uername和password			
					Long time=System.currentTimeMillis();
					String token=MD5Utils.GetMD5Code(username+password+time); 
					//先在数据库中添加token这个列，去service层建addToken()这个方法，再去IDao和DaoImpl层实现。
					loginService.addToken(token, acc);
					//并把其存放到回话域中  先创建回话
					HttpSession session=request.getSession();
					session.setAttribute("token", token);//setAttribute()是设置作用域的作用
					session.setAttribute("acc", acc);
					//到这就把token放到了回话域了，接下来就要过滤，建checkloginfilter.java
					request.getRequestDispatcher("view/frameset.jsp").forward(request, response);
				}else {
					//登录失败
					request.getRequestDispatcher("Faile.html").forward(request, response);

				}
				
	}


	
		
}
