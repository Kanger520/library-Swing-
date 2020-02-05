package edu.libsys.service.impl;

import java.util.List;

import edu.libsys.bean.BorrowBean;
import edu.libsys.dao.BorrowDao;
import edu.libsys.dao.impl.BorrowDaoImpl;
import edu.libsys.service.BorrowService;


public class BorrowServiceImpl implements BorrowService {
	private BorrowDao borrowDao = new BorrowDaoImpl();

	@Override
	public Long insert(BorrowBean bean) {
		// TODO Auto-generated method stub
		return borrowDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return borrowDao.delete(id);
	}

	@Override
	public Long update(BorrowBean bean) {
		// TODO Auto-generated method stub
		return borrowDao.update(bean);
	}

	@Override
	public List<BorrowBean> list() {
		// TODO Auto-generated method stub
		return borrowDao.list();
	}

	@Override
	public BorrowBean load(Long id) {
		// TODO Auto-generated method stub
		return borrowDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return borrowDao.count();
	}

	@Override
	public BorrowBean loadByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BorrowBean> listByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BorrowBean> listBySearch(String bookName, String operatorName, String readerName) {
		// TODO Auto-generated method stub
		return borrowDao.listBySearch(bookName, operatorName, readerName);
	}

	@Override
	public Long returnBook(Long id) {
		// TODO Auto-generated method stub
		return borrowDao.returnBook(id);
	}

	

}