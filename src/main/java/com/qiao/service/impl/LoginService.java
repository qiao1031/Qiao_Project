package com.qiao.service.impl;

import com.qiao.dao.ILoginDao;
import com.qiao.entity.Account;
import com.qiao.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LoginService implements ILoginService {
	@Autowired
	ILoginDao logindao ;
	public  Account  doLogin(String  username,String password) {

		Account account = logindao.doLogin(username, password);
		if (account != null) {
			return account;
		} else {
			System.out.println("输入有误，没有该账户信息");
			return null;
		}
	}

		public void addToken (String token, Account acc){
			// TODO Auto-generated method stub
			//获得到了token需要保存到数据库，那么需要在dao层里加一个可以把token存到数据库的方法，所以转到ILogindao
			//在dao层建好方法，回这调用
			logindao.addToken(token, acc);
			//再去控制层调用
		}


		public String findTokenByAccountid ( int accountid){
			// TODO Auto-generated method stub
			return logindao.findTokenByAccountid(accountid);
		}

	}
