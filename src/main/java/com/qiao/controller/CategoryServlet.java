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
		//把获取容器等操作放到init()中初始化，就可以对容器创建一次就可以了，也可以写在doget方法体内
		/*//获取的ioc容器
		WebApplicationContext mWebApplicationContext
				= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		//从容器中直接获取参数，都不需要注入  但还要转型               bean的id
		categoryservice=(CateGoryServiceImpl) mWebApplicationContext.getBean("categoryservice");
		//在用到的地方直接调用就行了*/
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
		//response.setHeader("Access-Control-Allow-Origin","*");//处理不同域
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
	/** 添加类别 * */
	boolean addCategory(Category category){
		return categoryservice.addCategory(category);
	}
	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收addcategory.jsp的信息
		int parent_id = Integer.parseInt(request.getParameter("parent_id"));
		String name = request.getParameter("name");
		int status = Integer.parseInt(request.getParameter("status"));
		int sort_order = Integer.parseInt(request.getParameter("sort_order"));
		/*String create_time = request.getParameter("create_time");
		String update_time = request.getParameter("update_time");*/
		Category category = new Category(parent_id,status,name,sort_order/*,create_time,update_time*/);
		boolean result = addCategory(category);
		if(result) {
			System.out.println("添加类别成功");
			findAllCategory(request,response);
		}else {
			System.out.println("添加类别失败");
		}
	}
	/**删除类别*/
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
			System.out.println("删除商品成功");
			findAllCategory(request,response);
		}else {
			System.out.println("删除商品失败");
		}
	}

		/**修改类别*/
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
			System.out.println("修改类别成功");
			findAllCategory(request,response);
		}else {
			System.out.println("修改类别失败");
		}
	}



		//根据ID查看某个类别
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
				System.out.println("查询id="+id+"的类别失败！！！！");
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		/**不用分页来展示
		 * Category category =findCategoryById(id);
		request.setAttribute("category", category);
		request.getRequestDispatcher("updateCategory.jsp").forward(request, response);
		*/
	}
	/*查看类别*/
	public void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String pageNo=request.getParameter("pageNo");
    	/*String pageSize=request.getParameter("pageSize");*/
    	int _pageNo=1;
    	int _pageSize=2;
		//若没传pageNo和pageSize，再给转int类型，就会报异常了。所以要给这俩设置个默认值
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
		/**不用分页查看类别
		 List<Category> categorys = categoryservice.findAll();
		 request.setAttribute("categorys", categorys);
		 request.getRequestDispatcher("findCategory.jsp").forward(request, respose);
		 */
    }

}
