package edu.libsys.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.libsys.bean.TeaferBean;
import edu.libsys.dao.TeaferDao;
import edu.libsys.util.DbPub;

public class TeaferDaoImpl implements TeaferDao {

	@Override
	public Long insert(TeaferBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into tb_teafer(ISBN,name,sex,age,identityCard,date,maxNum,tel,keepMoney,zj,zy,bztime)");
		sb.append(" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		paramsList.add(bean.getISBN());
		paramsList.add(bean.getName());
		paramsList.add(bean.getSex());
		paramsList.add(bean.getAge());
		paramsList.add(bean.getIdentityCard());
		paramsList.add(bean.getDate());
		paramsList.add(bean.getMaxNum());
		paramsList.add(bean.getTel());
		paramsList.add(bean.getKeepMoney());
		paramsList.add(bean.getZj());
		paramsList.add(bean.getZy());
		paramsList.add(bean.getBztime());

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
				bean.setISBN(result.toString());
				num = result;
			}
		}
		DbPub.close(conn);

		return num;
	}

	@Override
	public Long delete(String ISBN) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" delete from tb_teafer ");
		sb.append(" where ISBN=?");
		paramsList.add(ISBN);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public Long update(TeaferBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update tb_teafer set ");
		sb.append(" name = ? ");
		sb.append(" ,sex = ? ");
		sb.append(" ,age = ? ");
		sb.append(" ,identityCard = ? ");
		sb.append(" ,date = ? ");
		sb.append(" ,maxNum = ? ");
		sb.append(" ,tel = ? ");
		sb.append(" ,keepMoney = ? ");
		sb.append(" ,zj = ? ");
		sb.append(" ,zy = ? ");
		sb.append(" ,bztime = ? ");
		sb.append(" where ISBN = ? ");
		paramsList.add(bean.getName());
		paramsList.add(bean.getSex());
		paramsList.add(bean.getAge());
		paramsList.add(bean.getIdentityCard());
		paramsList.add(bean.getDate());
		paramsList.add(bean.getMaxNum());
		paramsList.add(bean.getTel());
		paramsList.add(bean.getKeepMoney());
		paramsList.add(bean.getZj());
		paramsList.add(bean.getZy());
		paramsList.add(bean.getBztime());
		paramsList.add(bean.getISBN());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<TeaferBean> list() {
		// TODO Auto-generated method stub
		// 定义一个泛型接受表的数据
		List<TeaferBean> list = new ArrayList<TeaferBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from tb_teafer ");
		sb.append(" where 1=1 ");
		sb.append(" order by ISBN  ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			TeaferBean bean = null;
			while (rs.next()) {
				bean = toBean(rs);
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

	private TeaferBean toBean(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TeaferBean bean;
		bean = new TeaferBean();
		bean.setISBN(rs.getString("ISBN"));
		bean.setName(rs.getString("name"));
		bean.setSex(rs.getString("sex"));
		bean.setAge(rs.getLong("age"));
		bean.setIdentityCard(rs.getString("identityCard"));
		bean.setDate(rs.getTimestamp("date"));
		bean.setMaxNum(rs.getLong("maxNum"));
		bean.setTel(rs.getString("tel"));
		bean.setKeepMoney(rs.getDouble("keepMoney"));
		bean.setZj(rs.getLong("zj"));
		bean.setZy(rs.getString("zy"));
		bean.setBztime(rs.getTimestamp("bztime"));

		return bean;
	}

	@Override
	public TeaferBean load(String ISBN) {
		// TODO Auto-generated method stub
		TeaferBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select * From tb_teafer");
		sb.append(" Where 1=1 ");
		sb.append("  And ISBN=?");
		paramsList.add(ISBN);

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
		sb.append("Select count(1) From tb_teafer");
		sb.append(" Wherre 1=1 ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public TeaferBean loadByName(String name) {
		// TODO Auto-generated method stub
		TeaferBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select * From tb_teafer");
		sb.append(" Where 1=1 ");
		sb.append("  And name=?");
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
		sb.append("Select count(1) From tb_teafer");
		sb.append(" Where 1=1 ");
		sb.append("  And name=?");
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<TeaferBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<TeaferBean> list = new ArrayList<TeaferBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from tb_teafer ");
		sb.append(" where 1=1 ");
		sb.append(" 	and name like ?  ");
		sb.append(" 	order by ISBN  ");
		name = "%" + name + "%";
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			TeaferBean bean = null;
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

}