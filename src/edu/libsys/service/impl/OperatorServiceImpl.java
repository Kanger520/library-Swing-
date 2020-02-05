package edu.libsys.service.impl;

import java.util.List;

import edu.libsys.bean.OperatorBean;
import edu.libsys.dao.OperatorDao;
import edu.libsys.dao.impl.OperatorDaoImpl;
import edu.libsys.service.OperatorService;

public class OperatorServiceImpl implements OperatorService {
	private OperatorDao operatorDao = new OperatorDaoImpl();

	@Override
	public Long insert(OperatorBean bean) {
		// TODO Auto-generated method stub
		return operatorDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return operatorDao.delete(id);
	}

	@Override
	public Long update(OperatorBean bean) {
		// TODO Auto-generated method stub
		return operatorDao.update(bean);
	}

	@Override
	public List<OperatorBean> list() {
		// TODO Auto-generated method stub
		return operatorDao.list();
	}

	@Override
	public OperatorBean load(Long id) {
		// TODO Auto-generated method stub
		return operatorDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return operatorDao.count();
	}

	@Override
	public OperatorBean loadByName(String name) {
		// TODO Auto-generated method stub
		return operatorDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return operatorDao.countByName(name);
	}

	@Override
	public List<OperatorBean> listByName(String name) {
		// TODO Auto-generated method stub
		return operatorDao.listByName(name);
	}

	

}