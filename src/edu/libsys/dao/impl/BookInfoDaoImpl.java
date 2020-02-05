package edu.libsys.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liuvei.common.SysFun;

import edu.libsys.bean.BookInfoBean;
import edu.libsys.dao.BookInfoDao;
import edu.libsys.util.DbPub;

public class BookInfoDaoImpl implements BookInfoDao {

	@Override
	public Long insert(BookInfoBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into tb_BookInfo(ISBN,typeId,bookName,writer,translator,publisher,date,price)");
		sb.append(" values(?, ?, ?, ?, ?, ?, ?, ?)");
		paramsList.add(bean.getISBN());
		paramsList.add(bean.getTypeId());
		paramsList.add(bean.getBookName());
		paramsList.add(bean.getWriter());
		paramsList.add(bean.getTranslator());
		paramsList.add(bean.getPublisher());
		paramsList.add(bean.getDate());
		paramsList.add(bean.getPrice());
		
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
		sb.append(" delete from tb_BookInfo ");
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
	public Long update(BookInfoBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update tb_BookInfo set ");
		sb.append(" typeId = ? ");
		sb.append(" ,bookName = ? ");
		sb.append(" ,writer = ? ");
		sb.append(" ,translator = ? ");
		sb.append(" ,publisher = ? ");
		sb.append(" ,date = ? ");
		sb.append(" ,price = ? ");
		sb.append(" where ISBN = ? ");
		paramsList.add(bean.getTypeId());
		paramsList.add(bean.getBookName());
		paramsList.add(bean.getWriter());
		paramsList.add(bean.getTranslator());
		paramsList.add(bean.getPublisher());
		paramsList.add(bean.getDate());
		paramsList.add(bean.getPrice());
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
	public List<BookInfoBean> list() {
		// TODO Auto-generated method stub
		// 定义一个泛型接受表的数据
		List<BookInfoBean> list = new ArrayList<BookInfoBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select A.*,B.typeName As typeName ");
		sb.append(" From tb_bookInfo A ");
		sb.append(" 	Left Join tb_bookType B On A.typeId=B.id ");
		sb.append("  where 1=1");
		sb.append("  Order by A.ISBN ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			BookInfoBean bean = null;
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

	private BookInfoBean toBean(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		BookInfoBean bean;
		bean = new BookInfoBean();
		bean.setISBN(rs.getString("ISBN"));
		bean.setTypeId(rs.getLong("typeId"));
		bean.setTypeName(rs.getString("typeName"));
		bean.setBookName(rs.getString("bookName"));
		bean.setWriter(rs.getString("writer"));
		bean.setTranslator(rs.getString("translator"));
		bean.setPublisher(rs.getString("publisher"));
		bean.setDate(rs.getTimestamp("date"));
		bean.setPrice(rs.getDouble("price"));
		return bean;
	}

	@Override
	public BookInfoBean load(String ISBN) {
		// TODO Auto-generated method stub
		BookInfoBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select * From tb_BookInfo");
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
				bean = toBeanEX(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DbPub.close(conn);
		return bean;

	}

	private BookInfoBean toBeanEX(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		BookInfoBean bean;
		bean = new BookInfoBean();
		bean.setISBN(rs.getString("ISBN"));
		bean.setTypeId(rs.getLong("typeId"));
		bean.setBookName(rs.getString("bookName"));
		bean.setWriter(rs.getString("writer"));
		bean.setTranslator(rs.getString("translator"));
		bean.setPublisher(rs.getString("publisher"));
		bean.setDate(rs.getTimestamp("date"));
		bean.setPrice(rs.getDouble("price"));
		return bean;

	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select count(1) From tb_bookInfo");
		sb.append(" Where 1=1 ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public BookInfoBean loadByName(String name) {
		// TODO Auto-generated method stub
		BookInfoBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select * From tb_bookInfo");
		sb.append(" Where 1=1 ");
		sb.append("  And bookName=?");
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {

			if (rs.next()) {
				bean = toBeanEX(rs);
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
		sb.append("Select count(1) From tb_bookInfo");
		sb.append(" Where 1=1 ");
		sb.append("  And BookName=?");
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
	public List<BookInfoBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<BookInfoBean> list = new ArrayList<BookInfoBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select A.*,S.typeName As typeName ");
		sb.append(" from tb_bookInfo A ");
		sb.append(" Left join tb_bookType S on A.typeId=S.id ");
		sb.append(" Where 1=1 ");

		if (!SysFun.isNullOrEmpty(name)) {
			sb.append("  and A.bookName like ?");
			name = "%" + name + "%";
			paramsList.add(name);
		}

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			BookInfoBean bean = null;
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