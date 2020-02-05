package edu.libsys.service.impl;

import java.util.List;

import edu.libsys.bean.OrderBean;
import edu.libsys.dao.OrderDao;
import edu.libsys.dao.impl.OrderDaoImpl;
import edu.libsys.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao = new OrderDaoImpl();

	@Override
	public Long insert(OrderBean bean) {
		// TODO Auto-generated method stub
		return orderDao.insert(bean);
	}

	@Override
	public Long delete(String ISBN) {
		// TODO Auto-generated method stub
		return orderDao.delete(ISBN);
	}

	@Override
	public Long update(OrderBean bean) {
		// TODO Auto-generated method stub
		return orderDao.update(bean);
	}

	@Override
	public List<OrderBean> list() {
		// TODO Auto-generated method stub
		return orderDao.list();
	}

	@Override
	public OrderBean load(String ISBN) {
		// TODO Auto-generated method stub
		return orderDao.load(ISBN);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return orderDao.count();
	}

	@Override
	public OrderBean loadByName(String ISBN) {
		// TODO Auto-generated method stub
		return orderDao.loadByName(ISBN);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return orderDao.countByName(name);
	}

	@Override
	public List<OrderBean> listByName(String name) {
		// TODO Auto-generated method stub
		return orderDao.listByName(name);
	}

	@Override
	public List<OrderBean> listBySearch(String ISBN, String operator) {
		// TODO Auto-generated method stub
		return orderDao.listBySearch(ISBN, operator);
	}

	@Override
	public Long acceptBook(String ISBN) {
		// TODO Auto-generated method stub
		return orderDao.acceptBook(ISBN);
	}

}