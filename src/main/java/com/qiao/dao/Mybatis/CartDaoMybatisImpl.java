package com.qiao.dao.Mybatis;

import com.qiao.dao.ICartDao;
import com.qiao.entity.Cart;
import com.qiao.entity.PageModel;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class CartDaoMybatisImpl implements ICartDao {
    @Autowired
    private  SqlSession  sqlSession;
    @Override
    public boolean addCart(Cart cart) {

        //ICartDao iCartDao = sqlSession.getMapper(ICartDao.class);
        //iCartDao.addCart(cart);
       int result=sqlSession.insert("com.qiao.entity.Cart.addCart", cart);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
        /*session.insert("com.qiao.entity.Cart.addCart", cart);
         ICartDao iCartDao = sqlSession.getMapper(ICartDao.class);
         这两种方式是根据在cartMapper.xml文件中
         <mapper namespace="com.qiao.dao.ICartDao">
         <mapper namespace="com.qiao.entity.Cart.addCart">
         来决定*/
         /*sqlSession.commit();
        sqlSession.close();查询就不需要这些了，因为这都交给c3p0去做了
        但添加啥的需要
        */

    @Override
    public boolean deleteCart(int id) {
        /*ICartDao iCartDao = sqlSession.getMapper(ICartDao.class);
        iCartDao.deleteCart(id);*/
        int result  =sqlSession.delete("com.qiao.entity.Cart.deleteCart", id);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }


    public boolean updataeCart(Cart cart) {

      /*  ICartDao iCartDao = sqlSession.getMapper(ICartDao.class);
        iCartDao.updataeCart(cart);*/
        int result  =sqlSession.update("com.qiao.entity.Cart.updataeCart", cart);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Cart> findAllCart() {
        /*ICartDao iCartDao=sqlSession.getMapper(ICartDao.class);
        List<Cart> carts = iCartDao.findAllCart();*/
        List<Cart> carts = sqlSession.selectList("com.qiao.entity.Cart.findAllCart");
        return carts;
    }

    @Override
    public PageModel<Cart> findCartByPage(int pageNo, int pageSize) {

        //先查询总的记录数，然后计算总页数
        int totalCount = sqlSession.selectOne("com.qiao.entity.Cart.findTotalCount");
        int totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        //查询页面数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Cart> cartee = sqlSession.selectList("com.qiao.entity.Cart.findCartByPage", map);

        PageModel<Cart> pagemodel = new PageModel<Cart>();
        pagemodel.setData(cartee);
        pagemodel.setCurrentPage(pageNo);
        pagemodel.setTotalPage(totalPage);
        return pagemodel;
    }

    @Override
    public Cart findCartById(int id) {

        /*ICartDao carte = sqlSession.getMapper(ICartDao.class);
        carte.findCartById(id);*/
        Cart cart=sqlSession.selectOne("com.qiao.entity.Cart.findCartById", id);
        return cart ;
    }

    @Override
    public int getCartNum() {
        return 0;
    }

    @Override
    public boolean updateCart(int id, int num) {


        Map<String, Integer> map = new HashMap<>();
        map.put("num", num);
        map.put("id", id);
        int result = sqlSession.update("com.qiao.entity.Cart.updataeCart", map);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clearCart() {
        /*ICartDao iCartDao=sqlSession.getMapper(ICartDao.class);
        iCartDao.clearCart();*/
      sqlSession.delete("com.qiao.entity.Cart.clearCart");



        /*String resource = "mybatis-config.xml";
        Reader reader = null;
        SqlSession session;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        session = sqlMapper.openSession(true);
                ICartDao iCartDao=session.getMapper(ICartDao.class);
        或者为:session.delete("com.qiao.entity.Cart.clearCart")
         iCartDao.clearCart();
        session.commit();
        session.close();*/

    }
}





