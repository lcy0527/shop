package dao.impl;

import bean.Order;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.OrderDao;
import util.JdbcUtil;
import util.ResultSetUtil;

public class OrderDaoImpl extends JdbcUtil implements OrderDao {

	@Override
	public int add(Order t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Order t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
}
