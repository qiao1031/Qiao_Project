package com.qiao.dao.Mybatis;

import com.qiao.dao.CategoryDao;
import com.qiao.entity.Category;
import com.qiao.entity.PageModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class CateGoryDaoMybatisImpl implements CategoryDao {
    @Autowired
    private  SqlSession  sqlSession;

    private boolean isFou(SqlSession session,int result){
        if (result == 1) {
            System.out.println("成功success");
            session.close();
            return true;
        } else {
            System.out.println("失败Fail");
            session.close();
            return false;
        }
    }
    @Override
    public boolean addCategory(Category category) {

         sqlSession.insert("com.qiao.entity.Category.addCategory", category);
        return true;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public boolean updateCategory(Category category) {

        int result=sqlSession.update("com.qiao.entity.Category.updateCategory", category);
        if (result == 1) {
            System.out.println("success");
            return true;
        } else {

            return false;
        }
    }


    @Override
    public boolean deleteCategory(int id) {

        int result = sqlSession.update("com.qiao.entity.Category.deleteCategory", id);
        if (result == 1) {

            System.out.println("success");
            return true;
        } else {

            return false;
        }
    }

    @Override
    public Category findCategoryById(int id) {

        Category category = sqlSession.selectOne("com.qiao.entity.Category.findCategoryById", id);
        return category;
    }

    @Override
    public PageModel<Category> findCategoryByPage(int pageNo, int pageSize) {

        //先查询总的记录数，然后计算总页数
        int totalCount = sqlSession.selectOne("com.qiao.entity.Category.findTotalCount");
        int totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        //查询页面数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Category> categoryee = sqlSession.selectList("com.qiao.entity.Category.findCategoryByPage", map);
        //session.close();就不用写了

        PageModel<Category> pagemodel = new PageModel<Category>();
        pagemodel.setData(categoryee);
        pagemodel.setCurrentPage(pageNo);
        pagemodel.setTotalPage(totalPage);
        return pagemodel;
    }

}
