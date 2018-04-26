package controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import bean.News;

import service.NewsService;
import service.impl.NewsServiceImpl;

@WebServlet(value = { "/news/*" })
public class NewsServlet extends HttpServlet {

	private NewsService service = new NewsServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置post乱码
		String method = req.getParameter("method");
		System.out.println(method);
		switch (method) {
		case "add":
			addNews(req, resp); // 新增的方法
			break;
		case "del":
			delNews(req, resp); // 删除的方法
			break;
		case "update":
			updateNews(req, resp); // 修改方法
			break;
		case "findAll":
			findAllNews(req, resp); // 修改方法
			break;
		case "findById":
			findByIdNews(req, resp); // 修改方法
			break;
		default:
			break;
		}

	}

	//
	private void findByIdNews(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		System.out.println(id);
		News news = service.findById(id);
		// 保存在request作用域
		req.setAttribute("news", news);
		try {
			req.getRequestDispatcher("back/new_edit.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	private void findAllNews(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("进入了 findAll");
		List<News> newsList = service.findAll();
		// 把集合保存在request作用域中
		req.setAttribute("newsList", newsList);
		try {
			req.getRequestDispatcher("back/New.jsp").forward(req, resp); // 转发到主页面
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增的方法 包含文件上传
	 * 
	 * 
	 * 01.引入需要的jar包 02.在form表中中更改enctype
	 * 03.ServletFileUpload.isMultipartContent(request) 来判断我们的请求是不是文件上传请求
	 * 04.获取请求中所有的表单元素 List<FileItem>
	 * list=ServletFileUpload.parseRequest(request) 每一个表单元素就对应一个FileItem
	 * 05.FileItem.isFormField() true===>普通的表单元素 getFiledName()===>获取name属性值
	 * getString(String s)===》获取value的值 s===>编码格式 flase==>文件上传元素
	 * getName===>获取上传文件的名称 getContentType()===》获取上传文件的类型 mime-type
	 * 
	 * 
	 */
	private void addNews(HttpServletRequest req, HttpServletResponse resp) {
		// form表单数据解析
		News news = dataAnalysis(req);
		System.out.println("into addNews");
		int num = service.add(news);
		showResult(resp, num);

	}

	private void showResult(HttpServletResponse resp, int num) {
		try {
			if (num > 0) {
				resp.sendRedirect("back/Ok.jsp");
			} else {
				resp.sendRedirect("back/False.jsp");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 数据解析方法
	private News dataAnalysis(HttpServletRequest req) {
		// 创建News对象
		News news = new News();

		System.out.println("临时文件存放的位置：====》"
				+ System.getProperty("java.io.tmpdir"));
		// 创建factory对象 可以设置缓冲区大小 以及存放位置
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 判断是否是文件上传类型
		boolean flag = upload.isMultipartContent(req);
		if (flag) { // form表单是文件上传类型
			try {
				List<FileItem> items = upload.parseRequest(req);// 获取所有的表单元素
				Iterator<FileItem> its = items.iterator();
				while (its.hasNext()) {
					FileItem item = its.next();
					// 判断表单元素是什么类型
					if (item.isFormField()) { // 证明是普通的元素
						String fieldName = item.getFieldName();
						System.out.println(item.getString("UTF-8"));
						switch (fieldName) {
						case "title":
							news.setTitle(item.getString("UTF-8"));
							break;
						case "cid":
							news.setNewstype(item.getString("UTF-8"));
							break;
						case "content":
							news.setContent(item.getString("UTF-8"));
							break;
						}
					} else {// 证明是文件的元素
						String uploadPath = req.getSession()
								.getServletContext().getRealPath("upload/");
						System.out.println(uploadPath);
						// 创建upload文件夹
						File file = new File(uploadPath);
						if (!file.exists()) {
							file.mkdirs();
						}
						String fileName = item.getName();// 获取上传文件的名称
						// 若浏览器显示了全路径，去掉前缀
						if (fileName.contains(":")) {
							int a = fileName.lastIndexOf("\\");
							fileName = fileName.substring(a + 1);
							System.out.println(fileName);
						}

						fileName = new String(fileName.getBytes());// 解决中文乱码
						System.out.println(fileName);
						if (!"".equals(fileName) && null != fileName) {
							String temp = System.currentTimeMillis() + "@";
							File saveFile = new File(uploadPath, temp
									+ fileName);
							item.write(saveFile);
							news.setImg(uploadPath + "\\" + temp + fileName);
							System.out.println(news.getImg());
						}
					}

				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return news;
	}

	private void updateNews(HttpServletRequest req, HttpServletResponse resp) {
		// form表单数据解析
		News news = dataAnalysis(req);
		int id = Integer.valueOf(req.getParameter("id"));
		System.out.println(id);
		news.setId(id);
		int num = service.update(news);
		System.out.println("into updateNews");
		showResult(resp, num);

	}

	private void delNews(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		int delete = service.delete(Integer.valueOf(id));
		if(delete>0){
			findAllNews(req, resp);
		}else{
			try {
				resp.sendRedirect("back/False.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
	}

}
