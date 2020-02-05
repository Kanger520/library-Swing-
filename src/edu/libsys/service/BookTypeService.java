package edu.libsys.service;

import edu.libsys.bean.BookTypeBean;

public interface BookTypeService {
	Long insert(BookTypeBean bean);

	Long delete(Long id);

	Long update(BookTypeBean bean);

	java.util.List<BookTypeBean> list();

	BookTypeBean load(Long id);// 获取一行，传入主键，不存在返回null

	Long count();// 统计行数

	BookTypeBean loadByName(String name);// 获取一行，传入名称，不存在返回null

	Long countByName(String name);// 统计行数，传入名称

	java.util.List<BookTypeBean> listByName(String name);


}