package edu.libsys.dao;

import edu.libsys.bean.BorrowBean;

public interface BorrowDao {
	Long insert(BorrowBean bean);

	Long delete(Long id);

	Long update(BorrowBean bean);

	java.util.List<BorrowBean> list();

	BorrowBean load(Long id);// 获取一行，传入主键，不存在返回null

	Long count();// 统计行数

	BorrowBean loadByName(String name);// 获取一行，传入名称，不存在返回null

	Long countByName(String name);// 统计行数，传入名称

	java.util.List<BorrowBean> listByName(String name);

	java.util.List<BorrowBean> listBySearch(String bookName,String operatorName,String readerName);
	
	Long returnBook(Long id);

}