package com.qiao.service;

import com.qiao.entity.Account;

public interface ILoginService{
	public Account  doLogin(String _username, String _password);
	
	public void addToken(String token, Account acc);

	 public String findTokenByAccountid(int accountid);
} 


