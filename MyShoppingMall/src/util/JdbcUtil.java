package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * 所有JDBC操作数据库的工具类
 * 01.数据库四要素的获取
 * 02.开启连接
 * 03.关闭连接
 * 04.公共的增删改
 * 05.公共的查询
 */
public class JdbcUtil {

	protected static Connection connection = null; // 便于我们释放资源
	protected static PreparedStatement statement = null;
	protected static ResultSet rs = null; // 查询数据返回的结果集

	/**
	 *  获取连接
	 */
	@SuppressWarnings("static-access")
	public static boolean getConnection() throws ClassNotFoundException,
			SQLException {
		try {
			// 通过反射机制获取数据库驱动包
			Class.forName(ConfigManager.getInstance().getValue("jdbc.driver"));
			// 通过DriverManager获取Conncetion连接对象
			connection = DriverManager.getConnection(ConfigManager
					.getInstance().getValue("jdbc.url"), ConfigManager
					.getInstance().getValue("jdbc.userName"), ConfigManager
					.getInstance().getValue("jdbc.password"));
		} catch (Exception e) {
			System.out.println("getConnection error");
			return false;
		}
		return true;
	}

	/**
	 * 释放资源
	 */
	public static void closeConnection() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 公共的增删改
	 * 
	 *  select * from student
	 *  select * from student where id=?
	 *  select * from student where id=? or name=?
	 *  
	 *  我们可以确定的是有sql语句！因为只有sql语句才能操作数据库！
	 *  但是我么无法确定的是：
	 *     01.参数的个数
	 *     02.参数的类型
	 * 
	 *  我们应该是考虑有参数 还是 没有参数？  有参数的！
	 *  考虑有1个参数还是有N个参数？     有N个参数的！
	 *  
	 *  用户需要传递一个执行的sql语句
	 *           再传递一个参数列表！
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	public static int myExcuteUpdate(String sql, Object... param)
			throws ClassNotFoundException, SQLException {
		int rowNum = 0;
		if (getConnection()) { // 证明有连接
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) { // 给sql语句中的? 一个一个赋值
				statement.setObject(i + 1, param[i]);
			}
			rowNum = statement.executeUpdate();
		}
		closeConnection(); // 释放资源
		return rowNum;
	}

	/**
	 * 公共的查询
	 */
	public static ResultSet myExcuteQuery(String sql, Object... param)
			throws ClassNotFoundException, SQLException {
		if (getConnection()) { // 证明有连接
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) { // 给sql语句中的? 一个一个赋值
				statement.setObject(i + 1, param[i]);
			}
			rs = statement.executeQuery();
		}
		/**
		 * 在这里不能事先关闭资源，因为子类中有rs的具体使用  还需要用到连接！
		 * 子类使用完毕，自行了断！
		 */
		return rs;
	}
}
