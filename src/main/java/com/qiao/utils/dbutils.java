package com.qiao.utils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
public class dbutils {
	//���������ļ�<1>��src�½��˸�properties���ļ�������������new
    static Properties ps=new Properties();
	//��̬�����
	static {
		try {
			//���������ļ�<2>
			ps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("qiao.properties"));
			//Class.forName("com.mysql.jdbc.Driver");���ɣ�
			Class.forName(ps.getProperty("driver"));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public static Connection getConnection() throws SQLException {
		String url=ps.getProperty("url");
		String user=ps.getProperty("username");
		String password=ps.getProperty("password");
		return DriverManager.getConnection(url,user,password);
	}
	//�ر�����
	public static void close(Connection conn,Statement st) throws SQLException {
		if(conn!=null){
		conn.close();
		}
		if(st!=null){
		st.close();
		}
	}
    public static void close(Connection conn,Statement st,ResultSet rs) throws SQLException {
    	if(conn!=null){
		conn.close();
    	}
		if(st!=null){
		st.close();
		}
		if(rs!=null){
		rs.close();
		}
	}
}
