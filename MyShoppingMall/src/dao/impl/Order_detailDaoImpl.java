package dao.impl;

import bean.Order_detail;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.Order_detailDao;
import util.JdbcUtil;
import util.ResultSetUtil;

public class Order_detailDaoImpl extends JdbcUtil implements Order_detailDao {

	@Override
	public int add(Order_detail t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Order_detail t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order_detail> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order_detail findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
}
