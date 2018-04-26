package dao.impl;

import bean.User;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.UserDao;
import util.JdbcUtil;
import util.ResultSetUtil;

public class UserDaoImpl extends JdbcUtil implements UserDao {

	/**
	 * 登录
	 */
	@Override
	public User login(String loginName, String password) {
		String sql = "select * from easybuy_user where loginName=? and password=?";
		// 给参数赋值
		Object[] params = { loginName, password };
		User user = null;
		try {
			rs = myExcuteQuery(sql, params);
			user = ResultSetUtil.findById(rs, User.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return user;
	}
	
	@Override
	public User login(String userName) {
		String sql = "select * from easybuy_user where loginName=?";
		Object[] prem = { userName};
		User user = null;
		try {
			 rs = myExcuteQuery(sql, prem);
			user = ResultSetUtil.findById(rs, User.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 现在网址注册 一般都是 用户名 密码 +邮箱/手机号 之后用户的详细信息 可以进行完善
	 */
	@Override
	public int add(User t) {
		String sql = "insert into easybuy_user(userName,loginName,password,sex,identityCode,email,mobile,type) values(?,?,?,?,?,?,?,?)";
		Object[] params = { t.getUserName(), t.getLoginName(), t.getPassword(),
				t.getSex(), t.getIdentityCode(), t.getEmail(), t.getMobile(),
				t.getType() };
		int rowNum = 0;
		try {
			rowNum = myExcuteUpdate(sql, params);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowNum;
	}

	/**
	 * 因为用户有可能根据id删除对象，也有可能是根据其他的选择！ 我们不确定类型，所以就把参数类型设置为Serializable
	 * 
	 * 遗留问题？ id为空 根据name删除 name为空 根据id删除
	 */
	@Override
	public int delete(Serializable id) {
		String sql = "delete from easybuy_user where id=?";
		Object[] params = { id };
		int rowNum = 0;
		try {
			rowNum = myExcuteUpdate(sql, params);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowNum;
	}

	/**
	 * 修改 我们怎么知道用户到底修改了什么？
	 */
	@Override
	public int update(User t) {
		String sql = "update easybuy_user set loginName=?,password=? where id=?";
		Object[] params = { t.getLoginName(), t.getPassword(), t.getId() };
		int rowNum = 0;
		try {
			rowNum = myExcuteUpdate(sql, params);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowNum;
	}

	@Override
	public List<User> findAll() {
		String sql = "select * from easybuy_user";
		// 创建一个集合来保存所有的用户
		List<User> users = new ArrayList<>();
		try {
			rs = myExcuteQuery(sql);
			users = ResultSetUtil.findAll(rs, User.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return users;
	}

	/**
	 * 查询指定的用户信息
	 */
	@Override
	public User findById(Serializable userID) {
		String sql = "select * from easybuy_user where id=?";
		// 给参数赋值
		Object[] params = { userID };
		User user = null;
		try {
			rs = myExcuteQuery(sql, params);
			user = ResultSetUtil.findById(rs, User.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return user;
	}
	
}
