package com.qiao.dao;

import com.qiao.entity.Account;


public interface ILoginDao {
 public Account   doLogin(String _username, String _password);

 public void addToken(String token, Account acc);

 public String findTokenByAccountid(int accountid);

    void updateAccount(String name, double money);
}
