package com.qiao.controller;

import com.qiao.entity.Category;
import com.qiao.entity.PageModel;
import com.qiao.service.impl.CateGoryServiceImpl;
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

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/view/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 9027606393000655873L;
	@Autowired
	CateGoryServiceImpl categoryservice ;
	public void init()throws ServletException{
		//�ѻ�ȡ�����Ȳ����ŵ�init()�г�ʼ�����Ϳ��Զ���������һ�ξͿ����ˣ�Ҳ����д��doget��������
		/*//��ȡ��ioc����
		WebApplicationContext mWebApplicationContext
				= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		//��������ֱ�ӻ�ȡ������������Ҫע��  ����Ҫת��               bean��id
		categoryservice=(CateGoryServiceImpl) mWebApplicationContext.getBean("categoryservice");
		//���õ��ĵط�ֱ�ӵ��þ�����*/
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,this.getServletContext());
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setHeader("Access-Control-Allow-Origin","*");//����ͬ��
    	String operation = request.getParameter("operation");
		if(operation!=null&&!operation.equals("")) {
			if(operation.equals("1")) {
				addCategory(request,response);
			}else if(operation.equals("2")) {
				findAllCategory(request,response);
			}else if(operation.equals("3")) {
				updateCategory(request,response);
			}else if(operation.equals("4")) {
				deleteCategory(request,response);
			}else if(operation.equals("5")) {
				findCategoryById(request,response);
			}
		}
	}
	/** ������ * */
	boolean addCategory(Category category){
		return categoryservice.addCategory(category);
	}
	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����addcategory.jsp����Ϣ
		int parent_id = Integer.parseInt(request.getParameter("parent_id"));
		String name = request.getParameter("name");
		int status = Integer.parseInt(request.getParameter("status"));
		int sort_order = Integer.parseInt(request.getParameter("sort_order"));
		/*String create_time = request.getParameter("create_time");
		String update_time = request.getParameter("update_time");*/
		Category category = new Category(parent_id,status,name,sort_order/*,create_time,update_time*/);
		boolean result = addCategory(category);
		if(result) {
			System.out.println("������ɹ�");
			findAllCategory(request,response);
		}else {
			System.out.println("������ʧ��");
		}
	}
	/**ɾ�����*/
	private boolean deleteCategory(int id) {
		return categoryservice.deleteCategory(id);
	}
	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		double price = 0.0;
		boolean result = false;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			result = deleteCategory(id);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		if(result) {
			System.out.println("ɾ����Ʒ�ɹ�");
			findAllCategory(request,response);
		}else {
			System.out.println("ɾ����Ʒʧ��");
		}
	}

		/**�޸����*/
	boolean updateCategory(Category category) {
		return categoryservice.updateCategory(category);
	}
	private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		int parent_id = Integer.parseInt(request.getParameter("parent_id"));
		int status = Integer.parseInt(request.getParameter("status"));
		int sort_order = Integer.parseInt(request.getParameter("sort_order"));
		String name = request.getParameter("name");
		//String create_time = request.getParameter("create_time");
		//String update_time = request.getParameter("update_time");
		Category category = findCategoryById(id);
		category=new Category(id,parent_id,status,name,sort_order);
		boolean result = updateCategory(category);
		if(result) {
			System.out.println("�޸����ɹ�");
			findAllCategory(request,response);
		}else {
			System.out.println("�޸����ʧ��");
		}
	}



		//����ID�鿴ĳ�����
	public Category findCategoryById(int id) {
			return categoryservice.findCategoryById(id);
	}
	private void findCategoryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strid = request.getParameter("id");
		int id=0;
		try {
			id=Integer.parseInt(strid);
			Category category=findCategoryById(id);
			if(category!=null) {
				request.setAttribute("category", category);
				request.getRequestDispatcher("UpdateCategory.jsp").forward(request, response);
			}else {
				System.out.println("��ѯid="+id+"�����ʧ�ܣ�������");
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		/**���÷�ҳ��չʾ
		 * Category category =findCategoryById(id);
		request.setAttribute("category", category);
		request.getRequestDispatcher("updateCategory.jsp").forward(request, response);
		*/
	}
	/*�鿴���*/
	public void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String pageNo=request.getParameter("pageNo");
    	/*String pageSize=request.getParameter("pageSize");*/
    	int _pageNo=1;
    	int _pageSize=2;
		//��û��pageNo��pageSize���ٸ�תint���ͣ��ͻᱨ�쳣�ˡ�����Ҫ���������ø�Ĭ��ֵ
    	try {
    		if(pageNo!=null/*&&pageSize!=null*/) {
			   _pageNo=Integer.parseInt(pageNo);
			  /* _pageSize=Integer.parseInt(pageSize);*/
			}
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    	}
    	PageModel<Category> pageModel= categoryservice.findCategoryByPage(_pageNo,_pageSize);
        request.setAttribute("pageModel",pageModel);
    	request.getRequestDispatcher("ShowCategoryByPage.jsp").forward(request,response);
		/**���÷�ҳ�鿴���
		 List<Category> categorys = categoryservice.findAll();
		 request.setAttribute("categorys", categorys);
		 request.getRequestDispatcher("findCategory.jsp").forward(request, respose);
		 */
    }

}
