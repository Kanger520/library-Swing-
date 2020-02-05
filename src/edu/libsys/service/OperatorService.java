package edu.libsys.service;

import edu.libsys.bean.OperatorBean;

public interface OperatorService {
	Long insert(OperatorBean bean);

	Long delete(Long id);

	Long update(OperatorBean bean);

	java.util.List<OperatorBean> list();

	OperatorBean load(Long id);// 获取一行，传入主键，不存在返回null

	Long count();// 统计行数

	OperatorBean loadByName(String name);// 获取一行，传入名称，不存在返回null

	Long countByName(String name);// 统计行数，传入名称

	java.util.List<OperatorBean> listByName(String name);


}