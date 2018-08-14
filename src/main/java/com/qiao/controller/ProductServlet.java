package com.qiao.controller;

import com.qiao.entity.PageModel;
import com.qiao.entity.Product;
import com.qiao.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view/product")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 2727984475369842085L;
    @Autowired
    ProductService pService;

    public void init() throws ServletException {
	/*WebApplicationContext mWebApplicationContext
			= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	//从容器中直接获取参数，都不需要注入  但还要转型               bean的id
	pService=(ProductService) mWebApplicationContext.getBean("pService");*/
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, this.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        if (operation != null && !operation.equals("")) {
            if (operation.equals("1")) {
                addProduct(request, response);
            } else if (operation.equals("2")) {
                findAll(request, response);
            } else if (operation.equals("3")) {
                updateProduct(request, response);
            } else if (operation.equals("4")) {
                deleteProduct(request, response);
            } else if (operation.equals("5")) {
                findProductById(request, response);
            } else if (operation.equals("6")) {
                addCount(request, response);
            }

        }
    }
        /**添加商品*/
		/*public boolean addProduct (Product product){
			return pService.addProduct(product);
		}*/
        public void addProduct (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Product product = new Product();
            String name = request.getParameter("pname");
            String pdesc = request.getParameter("pdesc");
            String image = request.getParameter("pimage");
            String rule = request.getParameter("rule");
            int stock = 0;
            double price = 0.0;
            boolean result = false;
            try {//可能出现的错误代码
                price = Double.parseDouble(request.getParameter("price"));//转字符串
                stock = Integer.parseInt(request.getParameter("stock"));
                product.setName(name);
                product.setPdesc(pdesc);
                product.setPrice(price);
                product.setImage(image);
                product.setRule(rule);
                product.setStock(stock);
                result = pService.addProduct(product);//把新new的product添加到商品集合中去
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (result) {
                System.out.println("商品添加成功");
                findAll(request, response);
            } else {
                System.out.println("商品添加失败");
            }
        }
        /**删除商品
         * @throws IOException
         * @throws ServletException */
        public void deleteProduct (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            String strid = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.parseInt(strid);
                boolean result = pService.deleteProduct(id);
                if (result) {
                    findAll(request, response);
                } else {
                    System.out.println("删除id=" + id + "的商品失败！！！！");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return;
        }
        /**修改商品*/
		/*public boolean updateProduct (Product product){
			return pService.updateProduct(product);
		}*/
        private void updateProduct (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            // TODO Auto-generated method stub
            String name = request.getParameter("pname");
            String pdesc = request.getParameter("pdesc");
            String image = request.getParameter("pimage");
            String rule = request.getParameter("rule");
            int stock = 0;
            int id = 0;
            double price = 0.0;
            boolean result = false;
            try {
                price = Double.parseDouble(request.getParameter("price"));
                //System.out.println("stock="+request.getParameter("stock"));
                stock = Integer.parseInt(request.getParameter("stock"));
                id = Integer.parseInt(request.getParameter("id"));
                Product product = pService.findProductById(id);
                product.setId(id);
                product.setName(name);
                product.setPdesc(pdesc);
                product.setPrice(price);
                product.setImage(image);
                product.setRule(rule);
                product.setStock(stock);
                result = pService.updateProduct(product);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (result) {
                System.out.println("商品修改成功");
                findAll(request, response);
            } else {
                System.out.println("商品修改失败");
            }
        }
        /*展示单件商品*/
		/*private Product findProductById ( int id){
			return pService.findProductById(id);
		}*/
        private void findProductById (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            // TODO Auto-generated method stub
            String strid = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.parseInt(strid);
                Product product = pService.findProductById(id);
                if (product != null) {//查询成功
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);
                } else {
                    System.out.println("查询id=" + id + "的商品失败！！！！");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        /**查询商品
         * @throws IOException
         * @throws ServletException */
        @SuppressWarnings("unused")
        public void findAll (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {

            String pageNo = request.getParameter("pageNo");
            /*String pageSize=request.getParameter("pageSize");*/
            int _pageNo = 1;
            int _pageSize = 2;
            try {
                if (pageNo != null/*&&pageSize!=null*/) {
                    _pageNo = Integer.parseInt(pageNo);

                }
                //若没传pageNo和pageSize，再给转int类型，就会报异常了。所以要给这俩设置个默认值
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            PageModel<Product> pageModel = pService.findProductByPage(_pageNo, _pageSize);
            System.out.println("---pagemode==" + pageModel);
            request.setAttribute("pageModel", pageModel);
            request.getRequestDispatcher("ShowProductByPage.jsp").forward(request, response);
        }


        private void addCount (HttpServletRequest request, HttpServletResponse response)throws
                ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = pService.findProductById(id);

            request.setAttribute("product", product);
            request.getRequestDispatcher("ShowProductById.jsp").forward(request, response);

        }
    }



