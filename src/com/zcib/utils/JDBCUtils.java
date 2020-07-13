package com.zcib.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JDBCUtils {
	private static String drivername;
	private static String url;
	private static String name;
	private static String pwd;
	//专用于存储事务所用的Connection对象
	private static ThreadLocal<Connection> tl= new ThreadLocal<Connection>();
	
	//静态模块，在类被加载时执行，并且仅被执行一次
	static{
		//从配置文件里面读取参数值
		InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("dbConfig.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			drivername = prop.getProperty("drivername");
			url = prop.getProperty("url");
			name = prop.getProperty("name");
			pwd = prop.getProperty("password");
			// 1.加载驱动
			Class.forName(drivername);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e.getMessage());
		}
	}
	
	//获取连接
	public static Connection getConnection() throws SQLException{
		/*
		 * 1.如果有事务，且事务中已经有连接了，返回当前事务的连接，也就是tl中的conn
		 * 2.如果没有事务，或者有事务，事务中没有连接，创建连接，并返回
		 */
		Connection conn = tl.get();//取出存储在事务中的连接
		if(conn!=null){//说明了事务中有连接
			return conn;
		}
		// 2.建立数据库的连接
		conn = DriverManager.getConnection(url, name, pwd);
		return conn;
	}
	//关闭连接
	public static void release(ResultSet rs,Statement stmt,Connection conn){
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			} finally {
				try {
					Connection transConn = tl.get();//取出存储在事务中的连接
					//和要关闭的连接比较，如果是同一个连接的话，说明了要关闭的是事务的连接
					if(transConn == conn){
					//如果是事务的连接，不能关
						return;
					}
					//如果不是事务的连接，关闭它
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		}
	}
	//开启事务
	public static void beginTransaction() throws SQLException{
		//1.获取数据库的连接，存储在tl中
		//1.1查看事务中是否有连接
		Connection conn = tl.get();
		if(conn!=null){
			throw new RuntimeException("已经开启过事务，不能重复开启");
		}
		conn = getConnection();
		//1.2把连接存储在tl中
		tl.set(conn);
		conn.setAutoCommit(false);//2.开启事务
	}
	
	//提交事务
	public static void CommitTransaction() throws SQLException{
		Connection conn = tl.get();//取出连接
		if(conn == null){
			throw new RuntimeException("没有事务可以提交");
		}
		conn.commit();//提交事务
		conn.close();//关闭连接
		conn = null;
		tl.remove();//把tl中的连接给移除
	}
	
	//回滚事务
		public static void roolbackTransaction() throws SQLException{
			Connection conn = tl.get();//取出连接
			if(conn == null){
				throw new RuntimeException("没有事务可以提交");
			}
			conn.rollback();//回滚事务
			conn.close();//关闭连接
			conn = null;
			tl.remove();//把tl中的连接给移除
		}
	// 增删改
	public static void update(String sql, Object... params) {
		/*
		 * 1.加载驱动 2.建立数据库的连接 3.创建Statement对象 4.调用Statement中的方法向数据库发送sql语句，数据库执行完毕
		 * 5.关闭数据库的连接
		 */
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			//2. 获取连接
			conn = getConnection();
			// 3.创建Statement对象
			pre = conn.prepareStatement(sql);
			// 3.2设置参数
			setParams(pre, params);
			// 4.访问数据库，进行增删改
			pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			release(null, pre, conn);
		}
	}
	// 3.2设置参数
	private static void setParams(PreparedStatement pre, Object... params)
			throws SQLException {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pre.setObject(i + 1, params[i]);
			}
		}
	}

	// 查找
	public static List<Map<String,Object>> select(String sql, Object... params) {
		/*
		 * 1.加载驱动 2.建立数据库的连接 3.创建Statement对象 4.调用Statement中的方法向数据库发送sql语句，数据库执行完毕
		 * 5.关闭数据库的连接
		 */
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		List<Map<String,Object>> list = null;
		try {

			// 2.获取数据库的连接
			conn = getConnection();
			// 3.创建Statement对象
			pre = conn.prepareStatement(sql);
			setParams(pre, params);
			//4.访问数据库，获取返回值
			rs = pre.executeQuery();
			list = RsToList(rs);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			// 5.关闭连接
			release(rs, pre, conn);
		}

		return list;
	}
	
	// 查找
		public static <T> T selectToBean(Class<T> clazz, String sql, Object... params) {
			/*
			 * 1.加载驱动 2.建立数据库的连接 3.创建Statement对象 4.调用Statement中的方法向数据库发送sql语句，数据库执行完毕
			 * 5.关闭数据库的连接
			 */
			Connection conn = null;
			PreparedStatement pre = null;
			ResultSet rs = null;
			T t = null;
			try {

				// 2.获取数据库的连接
				conn = getConnection();
				// 3.创建Statement对象
				pre = conn.prepareStatement(sql);
				setParams(pre, params);
				//4.访问数据库，获取返回值
				rs = pre.executeQuery();
				t = BeanUtils.BeanHandler(rs, clazz);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}finally {
				// 5.关闭连接
				release(rs, pre, conn);
			}

			return t;
		}
	
	
	// 增，可以返回自增的单个主键
	public static Object insert(String sql, Object... params) {
			/*
			 * 1.加载驱动 2.建立数据库的连接 3.创建Statement对象 4.调用Statement中的方法向数据库发送sql语句，数据库执行完毕
			 * 5.关闭数据库的连接
			 */
			Connection conn = null;
			PreparedStatement pre = null;
			ResultSet rs = null;
			Object key = null;
			try {
				//2. 获取连接
				conn = getConnection();
				// 3.创建Statement对象
				pre = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				// 3.2设置参数
				setParams(pre, params);
				// 4.访问数据库，进行增
				pre.executeUpdate();
				//4.2获取自增的主键
				rs = pre.getGeneratedKeys();
				if(rs.next()){
					key = rs.getObject(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//关闭连接
				release(null, pre, conn);
			}
			return key;
		}
	
	//把rs转换成list
	private static  List<Map<String,Object>> RsToList(ResultSet rs) throws SQLException {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		ResultSetMetaData rsmd = rs.getMetaData();//获取了表结构
		while(rs.next()) {
			Map<String,Object> map = new HashMap<String,Object>();
			//rsmd.getColumnCount(),字段的个数
			//rsmd.getColumnName(),第i个字段名称
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				map.put(rsmd.getColumnLabel(i), rs.getObject(i));
			}
			list.add(map);
		}
		return list;
	}
	
	//批处理，增操作
	public static <T> List<T> insertbatch(String sql,Object [][]params){
		/*
		 * 1.获取连接
		 * 2.创建PreparedStatement对象
		 * 3.设置参数（设置多组参数）
		 * 4.执行批处理操作
		 * 5.关闭连接
		 */
		
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		List<T> list = null;
		try {
			//1.获取连接
			conn = getConnection();
			//2.创建PreparedStatement对象
			pre = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//3.设置参数（设置多组参数）
			for(int i=0;i<params.length;i++){
				setParams(pre, params[i]);
				//将参数加入批处理
				pre.addBatch();
			}
			//4.执行批处理操作
			pre.executeBatch();
			//4.2 保存返回的主键
			rs = pre.getGeneratedKeys();
			list = new ArrayList<T>();
			while(rs.next()){
				list.add((T)rs.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//5.关闭连接
			release(rs, pre, conn);
		}
		return list;
	}
	
	//用于返回单值的操作
		public static <T> T selectScalar(String sql,Object ...params){
			Connection conn = null;
			PreparedStatement pre = null;
			ResultSet rs = null;
			T result = null;
			try {
				conn = getConnection();
				pre = conn.prepareStatement(sql);
				setParams(pre, params);
				rs = pre.executeQuery();
				if(rs.next()){
					result = (T)rs.getObject(1);
				}
				return result;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}finally{
				release(rs,pre,conn);
			}
			
		} 
	//批处理，增删改操作
		public static void updatebatch(String sql,Object [][]params){
			/*
			 * 1.获取连接
			 * 2.创建PreparedStatement对象
			 * 3.设置参数（设置多组参数）
			 * 4.执行批处理操作
			 * 5.关闭连接
			 */
			
			Connection conn = null;
			PreparedStatement pre = null;
			try {
				//1.获取连接
				conn = getConnection();
				//2.创建PreparedStatement对象
				pre = conn.prepareStatement(sql);
				//3.设置参数（设置多组参数）
				for(int i=0;i<params.length;i++){
					setParams(pre, params[i]);
					//将参数加入批处理
					pre.addBatch();
				}
				//4.执行批处理操作
				pre.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				//5.关闭连接
				release(null, pre, conn);
			}
		}
}
