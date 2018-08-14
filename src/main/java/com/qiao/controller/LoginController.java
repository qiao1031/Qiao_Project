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
 *��Ϊ��Ҫ����servlet������Ҫͨ��������·������URL�����з���
 * �����ַ�ʽ��
 * 1.ͨ��ע�⣺@WebServlet("/xxxxx")
 * 2.��lib��web.xml������
 */
@WebServlet("/Login.qiao")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = -3116360063055859984L;
	@Autowired
	LoginService loginService;
	public void init()throws  ServletException{
		/*WebApplicationContext mWebApplicationContext
				= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		//��������ֱ�ӻ�ȡ������������Ҫע��  ����Ҫת��               bean��id
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
			//response.setHeader("Access-Control-Allow-Origin","*");//����ͬ��
			//����д���ǵ�һ�ε�¼��������ӵ�¼ҳ������uername password�����¼�л�ȡ�˻�����
			 String username=request.getParameter("username");
			 String  password=request.getParameter("password");
				Account acc=loginService.doLogin(username, MD5Utils.GetMD5Code(password));
				if(acc!=null) {//��¼�ɹ�                   key      value
					Cookie user_cookie=new Cookie("username",username);//дһ��cookie�Ĺ��췽��������cookie
					user_cookie.setMaxAge(7*24*3600);//�������ô��ʱ��
					//��cookieд����Ӧͷ���У���Ӧ�Ķ���װ��response�����Ե���response����
					response.addCookie(user_cookie);
					
					Cookie pws_cookie=new Cookie("password", MD5Utils.GetMD5Code(password));//������ͨ��MD5Utils���µķ�������
					user_cookie.setMaxAge(7*24*3600);
					response.addCookie(pws_cookie);
					// ���ٴε�¼ʱ���������ͻ�ʶ�������ͼ��������ͷ����λ�ȡcookie ת��LoginFilter.java д����
					
					//����token,Ϊ������̨��¼ҳ���ĸ���ҳ�棬�����ŵ����ݿ���   �Ȼ�ȡtoken ���㷨��username password �͵���ʱ���MD5Util  �����Ѿ���ȡ��uername��password			
					Long time=System.currentTimeMillis();
					String token=MD5Utils.GetMD5Code(username+password+time); 
					//�������ݿ������token����У�ȥservice�㽨addToken()�����������ȥIDao��DaoImpl��ʵ�֡�
					loginService.addToken(token, acc);
					//�������ŵ��ػ�����  �ȴ����ػ�
					HttpSession session=request.getSession();
					session.setAttribute("token", token);//setAttribute()�����������������
					session.setAttribute("acc", acc);
					//����Ͱ�token�ŵ��˻ػ����ˣ���������Ҫ���ˣ���checkloginfilter.java
					request.getRequestDispatcher("view/frameset.jsp").forward(request, response);
				}else {
					//��¼ʧ��
					request.getRequestDispatcher("Faile.html").forward(request, response);

				}
				
	}


	
		
}
