package com.caoO.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 本类是一个工具类，用于封装一些JDBC常用操作的代码块
 * @author caoO
 *
 */
public class JDBCUtils {
	/**
	 * 配置文件存储对象
	 */
	private static Properties properties = null;
	
	static {
		properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties"));
			Class.forName(properties.getProperty("driverClassName"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 本方法用于创建一个连接对象
	 * @return 连接对象
	 * @throws SQLException 获取数据库连接失败
	 */
	public static Connection creatConnection() throws SQLException {
		return DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("username"), properties.getProperty("password"));
	}
	
	/**
	 * 本方法用于按顺序关闭指定语句执行对象，连接打开的资源
	 * @param c 所要关闭的连接对象
	 * @param s 所要关闭的语句执行对象
	 */
	public static void closeResources(Connection c, Statement s) {
		closeStatement(s);
		closeConnection(c);
	}
	
	/**
	 * 本方法用于按顺序关闭指定结果集，语句执行对象，连接打开的资源
	 * @param c 所要关闭的连接对象
	 * @param s 所要关闭的语句执行对象
	 * @param r 所要关闭的结果集对象
	 */
	public static void closeResources(Connection c, Statement s, ResultSet r) {
		closeResultSet(r);
		closeStatement(s);
		closeConnection(c);
	}
	
	/**
	 * 本方法用于关闭连接对象打开的资源
	 * @param c 所要关闭的连接对象
	 */
	public static void closeConnection(Connection c) {
		if(c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		c = null;
	}
	
	/**
	 * 本方法用于关闭语句执行对象打开的资源
	 * @param s 所要关闭的语句执行对象
	 */
	public static void closeStatement(Statement s) {
		if(s != null) {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		s = null;
	}
	
	/**
	 * 本方法用于关闭结果集对象打开的资源
	 * @param r 所要关闭的结果集对象
	 */
	public static void closeResultSet(ResultSet r) {
		if(r != null) {
			try {
				r.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		r = null;
	}
}
