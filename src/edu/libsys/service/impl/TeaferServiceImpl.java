package edu.libsys.service.impl;

import java.util.List;

import edu.libsys.bean.TeaferBean;
import edu.libsys.dao.TeaferDao;
import edu.libsys.dao.impl.TeaferDaoImpl;
import edu.libsys.service.TeaferService;


public class TeaferServiceImpl implements TeaferService {
	private TeaferDao teaferDao = new TeaferDaoImpl();

	@Override
	public Long insert(TeaferBean bean) {
		// TODO Auto-generated method stub
		return teaferDao.insert(bean);
	}

	@Override
	public Long delete(String ISBN) {
		// TODO Auto-generated method stub
		return teaferDao.delete(ISBN);
	}

	@Override
	public Long update(TeaferBean bean) {
		// TODO Auto-generated method stub
		return teaferDao.update(bean);
	}

	@Override
	public List<TeaferBean> list() {
		// TODO Auto-generated method stub
		return teaferDao.list();
	}

	@Override
	public TeaferBean load(String ISBN) {
		// TODO Auto-generated method stub
		return teaferDao.load(ISBN);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return teaferDao.count();
	}

	@Override
	public TeaferBean loadByName(String name) {
		// TODO Auto-generated method stub
		return teaferDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return teaferDao.countByName(name);
	}

	@Override
	public List<TeaferBean> listByName(String name) {
		// TODO Auto-generated method stub
		return teaferDao.listByName(name);
	}



}