package service;

import bean.User;

public interface UserService extends BaseService<User> {
	User login(String loginName, String password);
	User login(String loginName);
}
