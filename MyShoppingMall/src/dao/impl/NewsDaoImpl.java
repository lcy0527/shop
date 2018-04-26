package dao.impl;

import bean.News;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.NewsDao;
import util.JdbcUtil;
import util.ResultSetUtil;

public class NewsDaoImpl extends JdbcUtil implements NewsDao {

	/**
	 * 新增新闻
	 */
	@Override
	public int add(News t) {
		String sql = "insert into easybuy_News(title,content,img,newstype) values(?,?,?,?)";
		Object[] params = { t.getTitle(), t.getContent(),
				t.getImg() ,t.getNewstype()};
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
	 * 删除新闻
	 */
	@Override
	public int delete(Serializable id) {
		String sql = "delete from easybuy_News where id=?";
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
	 * 修改
	 */
	@Override
	public int update(News t) {
		String sql = "update easybuy_News set title=?,content=?,newstype=?,img=? where id=?";
		Object[] params = { t.getTitle(), t.getContent(), t.getNewstype(),t.getImg(),t.getId() };
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
	public List<News> findAll() {
		String sql = "select * from easybuy_News";
		// 创建一个集合来保存所有的用户
		List<News> newss = new ArrayList<>();
		try {
			rs = myExcuteQuery(sql);
			newss = ResultSetUtil.findAll(rs, News.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return newss;
	}

	/**
	 * 查询指定的新闻信息
	 */
	@Override
	public News findById(Serializable newId) {
		String sql = "select * from easybuy_News where id=?";
		News news = null;
		try {
			Object[] param = { newId };
			rs = myExcuteQuery(sql, param);
			news = ResultSetUtil.findById(rs, News.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return news;
	}
}
