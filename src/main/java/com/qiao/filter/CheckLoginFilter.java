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
		//�ӻػ����л�ȡtoken  ������Ҫ��HttpServletת��ServletRequest ����ת��
		HttpServletRequest qiao_request=(HttpServletRequest)request;
		HttpSession session=qiao_request.getSession();//��ȡ�ػ���
		Object o=session.getAttribute("token");//��ȡ�˻ػ��Ϳ��Ի�ȡtoken
		/*if(o!=null) { String token=(String)o;}  /*����ֵ����ת��һ��*/
		Object accobj=session.getAttribute("acc");
		if(o!=null&&accobj!=null) {
			String token=(String)o; /*����ֵ����ת��һ��*/
			//Ϊ���ø����û�ȥ�������ݿ��е�token����֮�Ƚ��Ƿ���ͬ����Ҫȥdao��ȥ��ӷ�����ѯ��token�ķ���������ȥdao��д����
			

			Account acc=(Account)accobj;
			String result_token=loginService.findTokenByAccountid(acc.getAccountId());
			/*findTokenByAccountid(acc.getAccountId());���accoundid����ô�������أ�
			 * ����Ҫ��LoginController.java�еĻػ��� ���ٷ�һ���˻����ϼ�session.setAttribute("acc", acc);
			 * ���������ȡ�˻ػ����ٴӻػ��л�ȡacc   Object accobj=session.getAttribute("acc"  );����ֵ����Ҳ��Ҫתһ��
			 * Account acc=(Account)accobj;�ⲽ���Ǹ�accת��
			 * �����Ż����acc.getAccountId()*/
			if(result_token!=null) {
				if(token.equals(result_token)) {
					System.out.println("����token����������");
					  //˵��token�Ǹ���Ч��  �Ϳ���ͨ������������������ҳ��
					chain.doFilter(request, response); 
					return;
				  }
			}
		}
		/*������ת����¼ҳ��  �����Ҫȥ��¼������ת����ҳ��
		  ҳ����ת�ĵڶ��ַ�ʽ     �ض���   ��response��ת�������ڷ�������Ҫ��ת��HTTPServletResponse ����
	*/
		HttpServletResponse qiao_response=(HttpServletResponse)response;
		qiao_response.sendRedirect("http://localhost:8080/BusinessProject_Qiao/Login.jsp");
		
		
	}



}
