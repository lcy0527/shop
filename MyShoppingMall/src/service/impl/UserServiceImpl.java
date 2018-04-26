package service.impl;

import java.io.Serializable;
import java.util.List;

import net.spy.memcached.MemcachedClient;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import bean.User;
import service.UserService;
import util.MemcachedUtil;

public class UserServiceImpl implements UserService {
	MemcachedClient client = MemcachedUtil.getInstance().client;
	// 实例化dao层对象 但是这种方式不可取 是耦合的！
	UserDao dao = new UserDaoImpl();

	/**
	 * 登录
	 */
	@Override
	public User login(String loginName, String password) {
		User user =(User) client.get(loginName);
		if(user!=null){
			if(user.getPassword().equals(password)){
				System.out.println("缓存用户登录成功！");
				return user;
			}else{
				System.out.println("缓存用户登录失败！");
				return null;
			}
		}else{
			user=dao.login(loginName, password);
			if (user == null) {
				System.out.println("mysql用户登录失败！");
				return null;
			} else {
				System.out.println("mysql用户登录成功！");
				client.set(loginName, 60*60, user);
				return user;
			}
		}
	}
	public User login(String loginName) {
		User user =(User) client.get(loginName);
		if(user==null){
			user=dao.login(loginName);
			System.out.println("mysql用户");
			client.set(loginName, 60*60, user);
		}else{
			System.out.println("缓存用户");
		}
		return user;
	}

	@Override
	public int add(User t) {
		return dao.add(t);
	}

	@Override
	public int delete(Serializable id) {
		return dao.delete(id);
	}

	@Override
	public int update(User t) {
		return dao.update(t);
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public User findById(Serializable id) {
		return dao.findById(id);
	}

}
