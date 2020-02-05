package edu.libsys.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.libsys.bean.BookTypeBean;
import edu.libsys.dao.BookTypeDao;
import edu.libsys.util.DbPub;

public class BookTypeDaoImpl implements BookTypeDao {
	/**
	 * 插入数据s
	 */
	@Override
	public Long insert(BookTypeBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into tb_bookType(typeName,days,Fk)");
		sb.append(" values(?, ?, ?)");
		paramsList.add(bean.getTypeName());
		paramsList.add(bean.getDays());
		paramsList.add(bean.getFk());

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
		sb.append(" delete from tb_bookType ");
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
	public Long update(BookTypeBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update tb_bookType set ");
		sb.append(" typeName = ? ");
		sb.append(" ,days = ? ");
		sb.append(" ,Fk = ? ");
		sb.append(" where id = ? ");
		paramsList.add(bean.getTypeName());
		paramsList.add(bean.getDays());
		paramsList.add(bean.getFk());
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
	public List<BookTypeBean> list() {
		// TODO Auto-generated method stub
		// 定义一个泛型接受表的数据
		List<BookTypeBean> list = new ArrayList<BookTypeBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from tb_bookType ");
		sb.append(" where 1=1 ");
		sb.append(" order by id  ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			BookTypeBean bean = null;
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
	public List<BookTypeBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<BookTypeBean> list = new ArrayList<BookTypeBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from tb_bookType ");
		sb.append(" where 1=1 ");
		sb.append(" 	and typeName like ?  ");
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
			BookTypeBean bean = null;
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
	public BookTypeBean load(Long id) {
		// TODO Auto-generated method stub
		BookTypeBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from tb_bookType ");
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
		sb.append(" select count(1) from tb_bookType ");
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
	public BookTypeBean loadByName(String name) {
		// TODO Auto-generated method stub
		BookTypeBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from tb_bookType ");
		sb.append(" where 1=1 ");
		sb.append(" 	and typeName = ?  ");
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
		sb.append(" select count(1) from tb_bookType ");
		sb.append(" where 1=1 ");
		sb.append(" 	and typeName like ?  ");
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	private BookTypeBean toBean(ResultSet rs) throws SQLException {
		BookTypeBean bean;
		bean = new BookTypeBean();
		bean.setId(rs.getLong("id"));
		bean.setTypeName(rs.getString("typeName"));
		bean.setDays(rs.getLong("days"));
		bean.setFk(rs.getDouble("Fk"));
		return bean;
	}

}