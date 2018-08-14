package com.qiao.utils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
public class dbutils {
	//导入配置文件<1>在src下建了个properties的文件，调用它所以new
    static Properties ps=new Properties();
	//静态代码块
	static {
		try {
			//导入配置文件<2>
			ps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("qiao.properties"));
			//Class.forName("com.mysql.jdbc.Driver");换成：
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
	//关闭连接
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
