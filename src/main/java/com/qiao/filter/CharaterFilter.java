 package com.qiao.filter;

 import org.springframework.web.context.support.SpringBeanAutowiringSupport;

 import javax.servlet.*;
 import javax.servlet.annotation.WebFilter;
 import java.io.IOException;

 /**
  * Servlet Filter implementation class CharaterFilter
  *  过滤器，是看你的路径进行过滤的  当为@WebFilter("/CharaterFilter")时，过滤的是/CharaterFilter
  *  当为/*  过滤的是所有的连接
  *  当想要过滤所有的字符串，则路径为/ *
  */
 @WebFilter("/*")
 public class CharaterFilter implements Filter {

     /**
      * Default constructor.
      */
     public CharaterFilter() {
         // TODO Auto-generated constructor stub
         SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
     }

     /**
      * @see Filter#destroy()
      * 销毁，当过滤器被销毁时被调用
      */
     public void destroy() {
         // TODO Auto-generated method stub
     }

     /**
      * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
      * 执行所有的过滤操作
      */
 //                                                                              过滤器链
     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         //在chain这句话前写的代码，是指，过滤的是请求的代码
         request.setCharacterEncoding("UTF-8");

         chain.doFilter(request, response);
         //在chain这句话后的代码，是指过滤的是响应的代码
     }

     /**
      * @see Filter#init(FilterConfig)
      * 初始化过滤器 。和servlet一样都是由容器初始化
      */
     public void init(FilterConfig fConfig) throws ServletException {
         // TODO Auto-generated method stub
     }

 }
