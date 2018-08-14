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
	//��cookie�л�ȡusername password�Ӷ��ﵽ�Զ���¼��Ч��      
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//��Ϊ��LoginContrnller��cookies��HTTPRequest�£���ServletRequest���������࣬����Ҫ����û�ȡcookie�ķ���  request.getCookies();����Ҫ����ת��
		 HttpServletRequest qiao_request=(HttpServletRequest)request;
		 HttpServletResponse qiao_response=(HttpServletResponse)response;
		  String password=null;
		  String username=null;
		  //getCookies()��cookis�Ļ�ȡ������qiao_request��ͬ��request�����Ի�ȡcookis���ص���cookie����
		  Cookie[] cookis=qiao_request.getCookies();
		  /*���������Ǳ���cookie���������������û�����ҳ������û�������cookie�����д治����
		   *������������cookies������ʣ��ٵ���Ӧ�������û������Я���������Զ���¼�ˣ����ʱ�����for��ǿ��Cookie���ǿ�ָ����
		   *����Ҫ����һ�����*/
		 if(cookis!=null) {
			 //��ǿforѭ�� ���� ������Ҫ�±�������ʹ��
			 for(Cookie c:cookis) {//��������cookis��ÿһ����Cookie����  �����Ϊc:  
				 if(c.getName().equals("username")) {					 
				     username=c.getValue();//��ȡ�û���
				/*����Ҫ���Ѿ����ڵ�cookies��յķ�����ͨ�����ô��ʱ��,��Ҫд����Ӧͷ��
				   c.setMaxAge(0);
				   response.addCookie(c);
				  �����passwordҲ����������*/
					System.out.println("username="+username);
				 }
				if(c.getName().equals("password")) {
					password=c.getValue();//��ȡ����ֵ
					System.out.println("password="+password);
				}
			 }
		 }
		 //���߶���Ϊ���Ҳ�Ϊ���ַ�����֤���и��˻����룬��¼�ɹ���
		if(username!=null&&password!=null&&!username.equals("")&&!password.equals("")) {

			Account acc=loginService.doLogin(username, password);
			if(acc!=null) {
				//��¼�ɹ�
			     request.getRequestDispatcher("view/frameset.jsp").forward(request, response);
			      return;//�ɹ�������Ӧ�ý����ˣ�������return������Ķ���ִ����
			}else {//��¼ʧ��
				//request.getRequestDispatcher("Faile.html").forward(request, response);
                    chain.doFilter(request,response);
			}
		}else {
			    //request.getRequestDispatcher("Faile.html").forward(request, response);
                chain.doFilter(request,response);
		}
	}


}
