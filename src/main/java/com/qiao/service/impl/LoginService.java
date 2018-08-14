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
			System.out.println("��������û�и��˻���Ϣ");
			return null;
		}
	}

		public void addToken (String token, Account acc){
			// TODO Auto-generated method stub
			//��õ���token��Ҫ���浽���ݿ⣬��ô��Ҫ��dao�����һ�����԰�token�浽���ݿ�ķ���������ת��ILogindao
			//��dao�㽨�÷������������
			logindao.addToken(token, acc);
			//��ȥ���Ʋ����
		}


		public String findTokenByAccountid ( int accountid){
			// TODO Auto-generated method stub
			return logindao.findTokenByAccountid(accountid);
		}

	}
