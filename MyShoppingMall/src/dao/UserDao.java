package dao;

import bean.User;

public interface UserDao extends BaseDao<User> {
	User login(String loginName, String password);
	User login(String loginName);
}
