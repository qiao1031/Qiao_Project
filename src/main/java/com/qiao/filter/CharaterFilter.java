 package com.qiao.filter;

 import org.springframework.web.context.support.SpringBeanAutowiringSupport;

 import javax.servlet.*;
 import javax.servlet.annotation.WebFilter;
 import java.io.IOException;

 /**
  * Servlet Filter implementation class CharaterFilter
  *  ���������ǿ����·�����й��˵�  ��Ϊ@WebFilter("/CharaterFilter")ʱ�����˵���/CharaterFilter
  *  ��Ϊ/*  ���˵������е�����
  *  ����Ҫ�������е��ַ�������·��Ϊ/ *
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
      * ���٣���������������ʱ������
      */
     public void destroy() {
         // TODO Auto-generated method stub
     }

     /**
      * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
      * ִ�����еĹ��˲���
      */
 //                                                                              ��������
     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         //��chain��仰ǰд�Ĵ��룬��ָ�����˵�������Ĵ���
         request.setCharacterEncoding("UTF-8");

         chain.doFilter(request, response);
         //��chain��仰��Ĵ��룬��ָ���˵�����Ӧ�Ĵ���
     }

     /**
      * @see Filter#init(FilterConfig)
      * ��ʼ�������� ����servletһ��������������ʼ��
      */
     public void init(FilterConfig fConfig) throws ServletException {
         // TODO Auto-generated method stub
     }

 }
