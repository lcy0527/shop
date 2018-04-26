package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;

@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("LoginFilter====>doFilter进行处理");
		HttpServletRequest req = (HttpServletRequest) arg0; // 子类的方法 向下转型
		HttpServletResponse resp = (HttpServletResponse) arg1; // 子类的方法 向下转型
		User user = (User) req.getSession().getAttribute("user"); // 看用户是否登录
		String path = req.getRequestURI();
		// System.out.println("getRequestURI()===>" + path);
		// System.out.println("getRequestURL()===>" + req.getRequestURL());
		String path2 = null;
		if (path.contains("register.jsp")) {
			path2 = path;
		}

		if (user != null || path.contains("login") || path.contains("register")
				|| path.contains("/js") || path.contains("/css")
				|| path.contains("/images") || path.contains("/AmazeUI-2.4.2")
				|| path.contains("/lib")) {
			// 放行register
			chain.doFilter(req, resp);
		} else {
			if (path2 != null) {
				resp.sendRedirect("/MyShoppingMall/sm/home/register.jsp");
			}
			resp.sendRedirect("/MyShoppingMall/sm/home/login.jsp");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
