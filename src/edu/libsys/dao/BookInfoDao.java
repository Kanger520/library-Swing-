package edu.libsys.dao;

import java.util.List;

import edu.libsys.bean.BookInfoBean;

public interface BookInfoDao {
	Long insert(BookInfoBean bean);

	Long delete(String ISBN);

	Long update(BookInfoBean bean);

	List<BookInfoBean> list();

	BookInfoBean load(String ISBN);// 获取一行，传入主键，不存在返回null

	Long count();// 统计行数

	BookInfoBean loadByName(String name);// 获取一行，传入名称，不存在返回null

	Long countByName(String name);// 统计行数，传入名称

	java.util.List<BookInfoBean> listByName(String name);

}