package edu.libsys.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.libsys.bean.OperatorBean;
import edu.libsys.dao.OperatorDao;
import edu.libsys.util.DbPub;

public class OperatorDaoImpl implements OperatorDao {
	/**
	 * 插入数据s
	 */
	@Override
	public Long insert(OperatorBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into tb_operator(name,sex,age,identityCard,workdate,tel,admin,password)");
		sb.append(" values(?, ?, ?, ?, ?, ?, ?, ?)");
		paramsList.add(bean.getName());
		paramsList.add(bean.getSex());
		paramsList.add(bean.getAge());
		paramsList.add(bean.getIdentityCard());
		paramsList.add(bean.getWorkdate());
		paramsList.add(bean.getTel());
		paramsList.add(bean.getAdmin());
		paramsList.add(bean.getPassword());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		// 创建三大对象
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		if (num > 0) {

			sql = " select LAST_INSERT_ID()";
			Long result = DbPub.queryScalarLong(conn, sql);

			if (result > 0) {
				bean.setId(result);
				num = result;
			}
		}
		DbPub.close(conn);

		return num;
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub

		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" delete from tb_operator ");
		sb.append(" where id=?");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public Long update(OperatorBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update tb_operator set ");
		sb.append(" name = ? ");
		sb.append(" ,sex = ? ");
		sb.append(" ,age = ? ");
		sb.append(" ,identityCard = ? ");
		sb.append(" ,workdate = ? ");
		sb.append(" ,tel = ? ");
		sb.append(" ,admin = ? ");
		sb.append(" ,password = ? ");
		sb.append(" where id = ? ");
		paramsList.add(bean.getName());
		paramsList.add(bean.getSex());
		paramsList.add(bean.getAge());
		paramsList.add(bean.getIdentityCard());
		paramsList.add(bean.getWorkdate());
		paramsList.add(bean.getTel());
		paramsList.add(bean.getAdmin());
		paramsList.add(bean.getPassword());
		paramsList.add(bean.getId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;

	}

	@Override
	public List<OperatorBean> list() {
		// TODO Auto-generated method stub
		// 定义一个泛型接受表的数据
		List<OperatorBean> list = new ArrayList<OperatorBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from tb_operator ");
		sb.append(" where 1=1 ");
		sb.append(" order by id  ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			OperatorBean bean = null;
			while (rs.next()) {
				bean = toBean(rs);

				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return list;
	}

	@Override
	public List<OperatorBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<OperatorBean> list = new ArrayList<OperatorBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from tb_operator ");
		sb.append(" where 1=1 ");
		sb.append(" 	and name like ?  ");
		sb.append(" 	order by id  ");
		name = "%" + name + "%";
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			OperatorBean bean = null;
			while (rs.next()) {
				bean = toBean(rs);

				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return list;
	}

	@Override
	public OperatorBean load(Long id) {
		// TODO Auto-generated method stub
		OperatorBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from tb_operator ");
		sb.append(" where 1=1 ");
		sb.append(" 	and id = ?  ");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			if (rs.next()) {
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return bean;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select count(1) from tb_operators ");
		sb.append(" where 1=1 ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public OperatorBean loadByName(String name) {
		// TODO Auto-generated method stub
		OperatorBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from tb_operator ");
		sb.append(" where 1=1 ");
		sb.append(" 	and name = ?  ");
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			if (rs.next()) {
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return bean;
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select count(1) from tb_operator ");
		sb.append(" where 1=1 ");
		sb.append(" 	and name like ?  ");
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	private OperatorBean toBean(ResultSet rs) throws SQLException {
		OperatorBean bean;
		bean = new OperatorBean();
		bean.setId(rs.getLong("id"));
		bean.setName(rs.getString("name"));
		bean.setSex(rs.getString("sex"));
		bean.setAge(rs.getLong("age"));
		bean.setIdentityCard(rs.getString("identityCard"));
		bean.setWorkdate(rs.getString("workdate"));
		bean.setTel(rs.getString("tel"));
		bean.setAdmin(rs.getLong("admin"));
		bean.setPassword(rs.getString("password"));
		return bean;
	}

}