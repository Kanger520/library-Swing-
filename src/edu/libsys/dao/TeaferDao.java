package edu.libsys.dao;

import java.util.List;

import edu.libsys.bean.TeaferBean;

public interface TeaferDao {
	Long insert(TeaferBean bean);

	Long delete(String ISBN);

	Long update(TeaferBean bean);

	List<TeaferBean> list();

	TeaferBean load(String ISBN);// 获取一行，传入主键，不存在返回null

	Long count();// 统计行数

	TeaferBean loadByName(String name);// 获取一行，传入名称，不存在返回null

	Long countByName(String name);// 统计行数，传入名称
	
	java.util.List<TeaferBean> listByName(String name);

}