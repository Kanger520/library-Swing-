package edu.libsys.service.impl;

import java.util.List;

import edu.libsys.bean.BookInfoBean;
import edu.libsys.dao.BookInfoDao;
import edu.libsys.dao.impl.BookInfoDaoImpl;
import edu.libsys.service.BookInfoService;

public class BookInfoServiceImpl implements BookInfoService {
	private BookInfoDao bookInfoDao = new BookInfoDaoImpl();

	@Override
	public Long insert(BookInfoBean bean) {
		// TODO Auto-generated method stub
		return bookInfoDao.insert(bean);
	}

	@Override
	public Long delete(String ISBN) {
		// TODO Auto-generated method stub
		return bookInfoDao.delete(ISBN);
	}

	@Override
	public Long update(BookInfoBean bean) {
		// TODO Auto-generated method stub
		return bookInfoDao.update(bean);
	}

	@Override
	public List<BookInfoBean> list() {
		// TODO Auto-generated method stub
		return bookInfoDao.list();
	}

	@Override
	public BookInfoBean load(String ISBN) {
		// TODO Auto-generated method stub
		return bookInfoDao.load(ISBN);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return bookInfoDao.count();
	}

	@Override
	public BookInfoBean loadByName(String name) {
		// TODO Auto-generated method stub
		return bookInfoDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return bookInfoDao.countByName(name);
	}

	@Override
	public List<BookInfoBean> listByName(String name) {
		// TODO Auto-generated method stub
		return bookInfoDao.listByName(name);
	}

}