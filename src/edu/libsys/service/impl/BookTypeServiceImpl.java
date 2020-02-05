package edu.libsys.service.impl;

import java.util.List;

import edu.libsys.bean.BookTypeBean;
import edu.libsys.dao.BookTypeDao;
import edu.libsys.dao.impl.BookTypeDaoImpl;
import edu.libsys.service.BookTypeService;

public class BookTypeServiceImpl implements BookTypeService {
	private BookTypeDao bookTypeDao = new BookTypeDaoImpl();

	@Override
	public Long insert(BookTypeBean bean) {
		// TODO Auto-generated method stub
		return bookTypeDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return bookTypeDao.delete(id);
	}

	@Override
	public Long update(BookTypeBean bean) {
		// TODO Auto-generated method stub
		return bookTypeDao.update(bean);
	}

	@Override
	public List<BookTypeBean> list() {
		// TODO Auto-generated method stub
		return bookTypeDao.list();
	}

	@Override
	public BookTypeBean load(Long id) {
		// TODO Auto-generated method stub
		return bookTypeDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return bookTypeDao.count();
	}

	@Override
	public BookTypeBean loadByName(String name) {
		// TODO Auto-generated method stub
		return bookTypeDao.loadByName(name);
	}
                              
	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return bookTypeDao.countByName(name);
	}

	@Override
	public List<BookTypeBean> listByName(String name) {
		// TODO Auto-generated method stub
		return bookTypeDao.listByName(name);
	}

}