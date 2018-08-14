package com.qiao.dao.Mybatis;

import com.qiao.dao.ProductDao;
import com.qiao.entity.PageModel;
import com.qiao.entity.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class ProductMybatisImpl implements ProductDao {
    @Autowired
    private  SqlSession  sqlSession;
    @Override
    public boolean addProduct(Product product) {
       /* ProductDao productDao=sqlSession.getMapper(ProductDao.class);
        productDao.updateProduct(product);
        return false;*/
        int result=sqlSession.insert("com.qiao.entity.Product.addProduct",product);
        if (result==1){
            return  true;
        }else{
            return  false;
        }






        /*String resource = "mybatis-config.xml";
        Reader reader = null;
        SqlSession session;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();}
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

        session = sqlMapper.openSession(true);
        ProductDao productDao=session.getMapper(ProductDao.class);
        productDao.updateProduct(product);
        session.commit();
        session.close();
        return  false;
      *//* int result=session.insert("com.qiao.entity.Product.addproduct",product);*//*
       */
    }

    @Override
    public List<Product> findAll() {
       /* String resource = "mybatis-config.xml";
        Reader reader = null;
        SqlSession session;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        session = sqlMapper.openSession();
        List<Product> productee= session.selectList("com.qiao.entity.Product.findProductAll");
        session.close();*/
        List<Product> productee= sqlSession.selectList("com.qiao.entity.Product.findProductAll");
        //List<Product> productee= sqlSession.getMapper(ProductDao.class);
        return productee;
    }

    @Override
    public boolean updateProduct(Product product) {

        int result=sqlSession.update("com.qiao.entity.Product.updateProduct",product);
        if (result==1){
            return  true;
        }else{
            return  false;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
       // ProductDao result=sqlSession.getMapper(ProductDao.class);
        int result=sqlSession.delete("com.qiao.entity.Product.deleteProduct",id);
         if (result==1){
            return  true;
        }else{
            return  false;
        }
    }

    @Override
    public Product findProductById(int id) {
        /*ProductDao product=sqlSession.getMapper(ProductDao.class);
        return (Product) product;*/
        /*注意这点：：<mapper namespace="com.qiao.dao.ICartDao">
                      <mapper namespace="com.qiao.entity.Cart.addCart">
          对应的返回值类型也不同
                       Dao接口类型
                       实体类类型*/

        Product productee=sqlSession.selectOne("com.qiao.entity.Product.findProductById",id);
        return productee;
    }

    @Override
    public PageModel<Product> findProductByPage(int pageNo, int pageSize) {

        //先查询总的记录数，然后计算总页数
        int totalCount = sqlSession.selectOne("com.qiao.entity.Product.findTotalCount");
        int totalPage=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);
        //查询页面数据
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Product>productea=sqlSession.selectList("com.qiao.entity.Product.findProductByPage",map);

        PageModel<Product> pagemodel =new PageModel<Product>();
        pagemodel.setData(productea);
        pagemodel.setCurrentPage(pageNo);
        pagemodel.setTotalPage(totalPage);
        return pagemodel;
    }
    private void updateShow(HttpServletRequest request, HttpServletResponse respose) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = findProductById(id);

        request.setAttribute("product", product);
        request.getRequestDispatcher(".jsp").forward(request, respose);

    }
}
