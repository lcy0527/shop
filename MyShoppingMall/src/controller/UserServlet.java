package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import util.Md5Encrypt;

import service.UserService;
import service.impl.UserServiceImpl;
import bean.User;

@WebServlet(value = { "/user/*" })
public class UserServlet extends HttpServlet {

	Logger logger = Logger.getLogger(UserServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getParameter("method");
		switch (method) {
		case "register":
			logger.debug("进入注册方法");
			register(req, resp);
			break;
		case "login":
			logger.debug("进入登录方法");
			login(req, resp);
			break;
		default:
			break;
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("userName");
		String userPwd = req.getParameter("passPwd");
		String cheName = req.getParameter("cheName");
		
		UserService us = new UserServiceImpl();
		User user = us.login(userName);
		if (user != null) {
			String userPwd2 = user.getPassword();
			System.out.println(userPwd);
			System.out.println(userPwd2);
			try {
				
				boolean b = Md5Encrypt.validPassword(userPwd, userPwd2);
				
				if(b || userPwd2.equals(userPwd)){
					logger.debug("登录成功");
					if (cheName != null) {				 
						logger.debug("用户名为:"+user.getUserName());
						Cookie cookie1 = new Cookie("userName", userName);
						Cookie cookie2 = new Cookie("userPwd", userPwd2);
						cookie1.setMaxAge(60 * 60 * 24 * 7);
						cookie2.setMaxAge(60 * 60 * 24 * 7);
						resp.addCookie(cookie1);
						resp.addCookie(cookie2);
					}
					try {
						req.getSession().setAttribute("user", user);
						resp.sendRedirect("sm/home/home2.jsp");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					logger.debug("密码错误");
					try {
						resp.sendRedirect("sm/home/login.jsp");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		} else {
			logger.debug("用户名错误");
			try {
				resp.sendRedirect("sm/home/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void register(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		String userPwd = req.getParameter("password");
		String hexString = "";
		try {
			String pwd = Md5Encrypt.getEncryptedPwd(userPwd);
			byte[] bs = Md5Encrypt.hexStringToByte(pwd);
			hexString = Md5Encrypt.byteToHexString(bs);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		User user = new User(email, hexString);
		user.setUserName(email);
		UserService us = new UserServiceImpl();
		int save = us.add(user);
		if (save > 0) {
			logger.debug("注册成功");
			System.out.println(hexString);
			try {
				resp.sendRedirect("sm/home/home2.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			logger.debug("注册失败");
		}

	}

}
