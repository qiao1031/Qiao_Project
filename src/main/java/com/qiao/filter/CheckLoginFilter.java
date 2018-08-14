package com.qiao.filter;

import com.qiao.entity.Account;
import com.qiao.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet Filter implementation class CheckLoginFilter
 */
/*@WebFilter("/view/*")*/
public class CheckLoginFilter implements Filter {
    /**
     * Default constructor. 
     */
    public CheckLoginFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//从回话域中获取token  但是先要把HttpServlet转成ServletRequest 向下转型
		HttpServletRequest qiao_request=(HttpServletRequest)request;
		HttpSession session=qiao_request.getSession();//获取回话域
		Object o=session.getAttribute("token");//获取了回话就可以获取token
		/*if(o!=null) { String token=(String)o;}  /*返回值类型转换一下*/
		Object accobj=session.getAttribute("acc");
		if(o!=null&&accobj!=null) {
			String token=(String)o; /*返回值类型转换一下*/
			//为了让根据用户去访问数据库中的token，与之比较是否相同，就要去dao层去添加方法查询到token的方法，所以去dao层写代码
			

			Account acc=(Account)accobj;
			String result_token=loginService.findTokenByAccountid(acc.getAccountId());
			/*findTokenByAccountid(acc.getAccountId());里的accoundid是怎么得来的呢？
			 * 就需要在LoginController.java中的回话域 中再放一个账户集合即session.setAttribute("acc", acc);
			 * 再在上面获取了回话后再从回话中获取acc   Object accobj=session.getAttribute("acc"  );返回值类型也需要转一下
			 * Account acc=(Account)accobj;这步就是给acc转型
			 * 这样才获得了acc.getAccountId()*/
			if(result_token!=null) {
				if(token.equals(result_token)) {
					System.out.println("到达token过滤器这了");
					  //说明token是个有效的  就可以通过过滤器，访问其他页面
					chain.doFilter(request, response); 
					return;
				  }
			}
		}
		/*所以跳转到登录页面  否则就要去登录才能跳转其他页面
		  页面跳转的第二种方式     重定向   用response来转。所以在方法内需要先转成HTTPServletResponse 的型
	*/
		HttpServletResponse qiao_response=(HttpServletResponse)response;
		qiao_response.sendRedirect("http://localhost:8080/BusinessProject_Qiao/Login.jsp");
		
		
	}



}
