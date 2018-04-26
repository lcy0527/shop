package dao.impl;

import bean.Product;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.ProductDao;
import util.JdbcUtil;
import util.ResultSetUtil;

public class ProductDaoImpl extends JdbcUtil implements ProductDao {

	@Override
	public int add(Product t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Product t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
}
