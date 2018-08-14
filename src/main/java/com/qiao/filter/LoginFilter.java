package com.qiao.filter;

import com.qiao.entity.Account;
import com.qiao.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet Filter implementation class LoginFilter
 */
/*@WebFilter("/Login.jsp")*/
public class LoginFilter implements Filter {
    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Autowired
	LoginService loginService;
	@SuppressWarnings("unused")
	//从cookie中获取username password从而达到自动登录的效果      
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//因为在LoginContrnller中cookies在HTTPRequest下，而ServletRequest属于其子类，所以要想调用获取cookie的方法  request.getCookies();，就要向下转型
		 HttpServletRequest qiao_request=(HttpServletRequest)request;
		 HttpServletResponse qiao_response=(HttpServletResponse)response;
		  String password=null;
		  String username=null;
		  //getCookies()是cookis的获取方法，qiao_request等同于request，所以获取cookis返回的是cookie数组
		  Cookie[] cookis=qiao_request.getCookies();
		  /*接下来就是遍历cookie【】数组来看看用户在网页输入的用户密码在cookie数组中存不存在
		   *当浏览器清空了cookies，则访问，再到回应，浏览器没有数据携带可以能自动登录了，则此时下面的for增强中Cookie就是空指针了
		   *所以要给设一个语句*/
		 if(cookis!=null) {
			 //增强for循环 是在 当不需要下标的情况下使用
			 for(Cookie c:cookis) {//遍历的是cookis，每一项是Cookie类型  设变量为c:  
				 if(c.getName().equals("username")) {					 
				     username=c.getValue();//获取用户名
				/*当想要把已经存在的cookies清空的方法，通过设置存活时间,还要写到响应头里
				   c.setMaxAge(0);
				   response.addCookie(c);
				  下面的password也是这样设置*/
					System.out.println("username="+username);
				 }
				if(c.getName().equals("password")) {
					password=c.getValue();//获取密码值
					System.out.println("password="+password);
				}
			 }
		 }
		 //二者都不为空且不为空字符串则证明有该账户密码，登录成功了
		if(username!=null&&password!=null&&!username.equals("")&&!password.equals("")) {

			Account acc=loginService.doLogin(username, password);
			if(acc!=null) {
				//登录成功
			     request.getRequestDispatcher("view/frameset.jsp").forward(request, response);
			      return;//成功后程序就应该结束了，所以用return，后面的都不执行了
			}else {//登录失败
				//request.getRequestDispatcher("Faile.html").forward(request, response);
                    chain.doFilter(request,response);
			}
		}else {
			    //request.getRequestDispatcher("Faile.html").forward(request, response);
                chain.doFilter(request,response);
		}
	}


}
