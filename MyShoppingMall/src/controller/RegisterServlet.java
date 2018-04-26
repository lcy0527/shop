package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service.UserService;
import service.impl.UserServiceImpl;
import util.Md5Encrypt;
import bean.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	Logger logger = Logger.getLogger(UserServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
