package com.qiao.dao.impl;


import com.qiao.dao.LogDao;
import com.qiao.entity.LogBean;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoImpl implements LogDao {

    @Autowired
    SqlSession sqlSession;
    @Override
    public void add(LogBean logBean) {

       LogDao logDao= sqlSession.getMapper(LogDao.class);

      logDao.add(logBean);
    }
}
