package dao.impl;

import bean.Product_category;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.Product_categoryDao;
import util.JdbcUtil;
import util.ResultSetUtil;

public class Product_categoryDaoImpl extends JdbcUtil implements
		Product_categoryDao {

	@Override
	public int add(Product_category t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Product_category t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product_category> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product_category findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
}
