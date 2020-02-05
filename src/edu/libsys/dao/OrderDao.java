package edu.libsys.dao;

import java.util.List;

import edu.libsys.bean.OrderBean;

public interface OrderDao {
	Long insert(OrderBean bean);

	Long delete(String ISBN);

	Long update(OrderBean bean);

	List<OrderBean> list();

	OrderBean load(String ISBN);// 获取一行，传入主键，不存在返回null

	Long count();// 统计行数

	OrderBean loadByName(String ISBN);// 获取一行，传入名称，不存在返回null

	Long countByName(String name);// 统计行数，传入名称

	java.util.List<OrderBean> listByName(String name);

	java.util.List<OrderBean> listBySearch(String ISBN, String operator);

	Long acceptBook(String ISBN);
}