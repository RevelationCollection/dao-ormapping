package com.jbp.oracle.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:MLDN";
	private static final String DBUSER = "scott";
	private static final String PASSWORD = "tiger";
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	private DatabaseConnection(){}
	public static Connection rebuildConnection(){
		try {
			Class.forName(DBDRIVER);
			return DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Connection getConnection(){
		Connection conn = threadLocal.get();
		if(conn==null){
			conn = rebuildConnection();
			threadLocal.set(conn);
		}
		return conn;
	}
	public static void close(){
		Connection conn = threadLocal.get();
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			threadLocal.remove();
		}
	}
}
