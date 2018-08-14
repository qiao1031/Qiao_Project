package com.qiao.dao.Mybatis;

import com.qiao.dao.ILoginDao;
import com.qiao.entity.Account;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class LoginDaoMybatisImpl  implements ILoginDao {
    @Autowired
    private  SqlSession  sqlSession;
    @Override
    public Account doLogin(String _username, String _password) {
      /*1，读取配置文件
         2，生成SqlSessionFactory为SqlSession的工厂，用于建立与数据库的会话。
         3，建立SqlSession用于执行sql语句
         4，调用MyBatis提供的api
         5，查询MAP配置
         6，返回结果
         7，关闭SqlSession*//*
        String resource = "mybatis-config.xml";
        Reader reader = null;
        SqlSession session;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       // 2，生成SqlSessionFactory为SqlSession的工厂，用于建立与数据库的会话。
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
       // 3，建立SqlSession用于执行sql语句
        session = sqlMapper.openSession();
       // 4，调用MyBatis提供的api      5，查询MAP配置
        Map<String,String> map=new HashMap<String, String>();
        map.put("username",_username);
        map.put("password",_password);
        Account account = session.selectOne("com.qiao.entity.Account.doLogin", map);
        System.out.println(account);
        //7，关闭SqlSession
        session.close();
       // 6，返回结果
        return account;*/

       Map<String,String> map=new HashMap<String, String>();
        map.put("username",_username);
        map.put("password",_password);
        Account account = sqlSession.selectOne("com.qiao.entity.Account.doLogin", map);
        System.out.println(account);
        return account;

        /*Map<String,String> map=new HashMap<String, String>();
        map.put("username",_username);
        map.put("password",_password);
        ILoginDao account = sqlSession.getMapper(ILoginDao.class);
        System.out.println(account);
        return (Account) account;*/
    }

    @Override
    public void addToken(String token, Account acc) {

        Map<String,Object> map =new HashMap<String,Object>();
        map.put("token",token);
        map.put("accountid",acc.getAccountId());
        sqlSession.update("com.qiao.entity.Account.addToken",map);
    }

    @Override
    public String findTokenByAccountid(int accountid) {

        String token= sqlSession.selectOne("com.qiao.entity.Account.findTokenByAccountid", accountid);
        return token;
    }

    @Override
    public void updateAccount(String name, double money) {
        ILoginDao loginDao=sqlSession.getMapper(ILoginDao.class);
        loginDao.updateAccount(name,money);
    }
}
