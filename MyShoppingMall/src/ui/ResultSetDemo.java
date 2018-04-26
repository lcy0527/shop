package ui;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import bean.User;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * 相当于我们后续的jsp动态页面
 */
public class ResultSetDemo {
	private static Logger logger = Logger.getLogger(ResultSetDemo.class
			.getName());
	private static Scanner input = new Scanner(System.in);
	private static List<User> findAll;

	public static void main(String[] args) {
		showUser();
	}

	/**
	 * 一级菜单
	 */
	public static void showMenu() {
		System.out.println("欢迎进入用户和新闻管理系统");
		System.out.println("1:用户管理");
		System.out.println("2:新闻管理");
		System.out.println("请您选择：");
		int choose = input.nextInt();
		switch (choose) {
		case 1: // 用户管理
			showUser();
			break;
		}
	}

	/**
	 * 用户管理菜单
	 */
	private static void showUser() {
		// 实例化UserService层的对象
		UserService us = new UserServiceImpl();
		System.out.println("====================================");
		System.out.println("欢迎进入用户管理系统");
		System.out.println("1:登录");
		System.out.println("2:注册");
		System.out.println("3:删除");
		System.out.println("4:修改");
		System.out.println("5:查询所有");
		System.out.println("6:查询指定用户信息");
		System.out.println("7:返回上级目录");
		System.out.println("请您选择：");
		int choose = input.nextInt();
		switch (choose) {
		case 1: // 登录
			userLogin(us);
			break;
		case 2: // 注册
			userRegister(us);
			break;
		case 3: // 删除
			userDelete(us);
			break;
		case 4: // 修改
			userUpdate(us);
			break;
		case 5: // 查询所有
			userFindAll(us);
			break;
		case 6: // 查询指定用户信息
			userFindById(us);
			break;
		case 7: // 返回上级目录
			showMenu();
			break;
		}

	}

	private static void userFindById(UserService us) {
		System.out.println("请输入查询的用户编号");
		int id = input.nextInt();
		User user = us.findById(id);
		System.out.println(user.toString());
		
	}

	private static void userFindAll(UserService us) {
		
		List<User> users = us.findAll();
		for (User user : users) {
			System.out.println(user.toString());
		}
		
		
		
	}

	private static void userUpdate(UserService service) {
		System.out.println("请输入要修改的用户编号");
		int id = input.nextInt();
		System.out.println("请输入新登录名：");
		String loginName = input.next();
		System.out.println("请输入新密码：");
		String password = input.next();
		User t = new User();
		t.setId(id);
		t.setLoginName(loginName);
		t.setPassword(password);
		service.update(t);
	}

	private static void userRegister(UserService service) {
		System.out.println("请输入登录名：");
		String loginName = input.next();
		System.out.println("请输入密码：");
		String password = input.next();
		User user = new User();
		user.setLoginName(loginName);
		user.setUserName(loginName);
		user.setPassword(password);
		service.add(user);
	}

	public static void userDelete(UserService service) {
		System.out.println("请您输入需要删除的ID：");
		int id = input.nextInt();
		service.delete(id);
	}

	// 用户登录
	public static void userLogin(UserService service) {
		System.out.println("请您输入用户名：");
		String loginName = input.next();
		System.out.println("请您输入密码：");
		String password = input.next();
		User user = service.login(loginName, password);
		System.out.println("您的个人信息如下：");
		System.out.println(user.getId());
		System.out.println(user.getLoginName());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println(user.getMobile());
		System.out.println(user.getIdentityCode());
	}

}
