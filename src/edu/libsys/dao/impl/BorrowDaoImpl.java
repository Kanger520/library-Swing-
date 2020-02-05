package edu.libsys.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liuvei.common.SysFun;

import edu.libsys.bean.BorrowBean;
import edu.libsys.dao.BorrowDao;
import edu.libsys.util.DbPub;

public class BorrowDaoImpl implements BorrowDao {

	@Override
	public Long insert(BorrowBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append("Insert Into tb_borrow(bookISBN,operatorId,readerISBN,isback,borrowDate,backDate)");
		sb.append("value(?,?,?,?,?,?)");

		paramsList.add(bean.getBookISBN());
		paramsList.add(bean.getOperatorId());
		paramsList.add(bean.getReaderISBN());
		paramsList.add(bean.getIsback());
		paramsList.add(bean.getBorrowDate());
		paramsList.add(bean.getBackDate());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);

		return num;
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Delete From tb_borrow ");
		sb.append(" Where id=? ");
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
	public Long update(BorrowBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" UPdate tb_borrow Set ");
		sb.append(" bookISBN=?");
		sb.append(" ,operatorId=?");
		sb.append(" ,readerISBN=?");
		sb.append(" ,isback=?");
		sb.append(" ,borrowDate=?");
		sb.append(" ,backDate=?");
		sb.append(" Where id = ?");
		paramsList.add(bean.getBookISBN());
		paramsList.add(bean.getOperatorId());
		paramsList.add(bean.getReaderISBN());
		paramsList.add(bean.getIsback());
		paramsList.add(bean.getBorrowDate());
		paramsList.add(bean.getBackDate());
		paramsList.add(bean.getId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		if (num > 0) {

			sql = "SELECT LAST_INSERT_id()";
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
	public List<BorrowBean> list() {
		// TODO Auto-generated method stub
		List<BorrowBean> list = new ArrayList<BorrowBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select A.*, B.bookName As bookName, C.name AS operatorName, D.name AS readerName ");
		sb.append(" from tb_borrow A ");
		sb.append(" 	Left Join tb_bookInfo B On A.bookISBN=B.ISBN ");
		sb.append(" 	Left Join tb_operator C On A.operatorId=C.id ");
		sb.append(" 	Left Join tb_teafer D On A.readerISBN=D.ISBN ");
		sb.append(" Where 1=1 ");
		sb.append("  Order by A.id ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			BorrowBean bean = null;
			while (rs.next()) {
				bean = toBeanEx(rs);
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);

		return list;
	}

	private BorrowBean toBeanEx(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		BorrowBean bean;
		bean = new BorrowBean();
		bean.setId(rs.getLong("id"));
		bean.setBookISBN(rs.getString("bookISBN"));
		bean.setBookName(rs.getString("bookName"));
		bean.setOperatorId(rs.getLong("operatorId"));
		bean.setOperatorName(rs.getString("operatorName"));
		bean.setReaderISBN(rs.getString("readerISBN"));
		bean.setReaderName(rs.getString("readerName"));
		bean.setIsback(rs.getLong("isBack"));
		bean.setBorrowDate(rs.getTimestamp("borrowDate"));
		bean.setBackDate(rs.getTimestamp("backDate"));
		return bean;
	}

	@Override
	public BorrowBean load(Long id) {
		// TODO Auto-generated method stub
		BorrowBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select * From tb_borrow");
		sb.append(" Where 1=1 ");
		sb.append("  And id=?");
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
			e.printStackTrace();
		}

		DbPub.close(conn);
		return bean;
	}

	private BorrowBean toBean(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		BorrowBean bean;
		bean = new BorrowBean();
		bean.setId(rs.getLong("id"));
		bean.setBookISBN(rs.getString("bookISBN"));
		bean.setOperatorId(rs.getLong("operatorId"));
		bean.setReaderISBN(rs.getString("readerISBN"));
		bean.setIsback(rs.getLong("isBack"));
		bean.setBorrowDate(rs.getTimestamp("borrowDate"));
		bean.setBackDate(rs.getTimestamp("backDate"));
		return bean;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select count(1) From tb_borrow");
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
	public BorrowBean loadByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BorrowBean> listByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BorrowBean> listBySearch(String bookName, String operatorName, String readerName) {
		// TODO Auto-generated method stub
		List<BorrowBean> list = new ArrayList<BorrowBean>();

        StringBuffer sb = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();
        sb.append(" Select A.*, B.bookName As bookName, C.name AS operatorName, D.name AS readerName ");
		sb.append(" from tb_borrow A ");
		sb.append(" 	Left Join tb_bookInfo B On A.bookISBN=B.ISBN ");
		sb.append(" 	Left Join tb_operator C On A.operatorId=C.id ");
		sb.append(" 	Left Join tb_teafer D On A.readerISBN=D.ISBN ");
		sb.append(" Where 1=1 ");
        if (!SysFun.isNullOrEmpty(bookName)) {
            sb.append("  and B.bookName like ?");
            bookName = "%" + bookName + "%";
            paramsList.add(bookName);
        }
        if (!SysFun.isNullOrEmpty(operatorName)) {
            sb.append("  and C.name like ?");
            operatorName = "%" + operatorName + "%";
            paramsList.add(operatorName);
        }
        if (!SysFun.isNullOrEmpty(readerName)) {
            sb.append("  and D.name like ?");
            readerName = "%" + readerName + "%";
            paramsList.add(readerName);
        }
        sb.append("      order by A.id");

        String sql = sb.toString();
        Object[] params = paramsList.toArray();
        Connection conn = null;
        ResultSet rs = null;

        conn = DbPub.getConn();
        rs = DbPub.query(conn, sql, params);

        try {
            BorrowBean bean = null;
            while (rs.next()) {
                bean = toBeanEx(rs);
                list.add(bean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbPub.close(conn);

        return list;
    }

	@Override
	public Long returnBook(Long id) {
		// TODO Auto-generated method stub
		 Long num=0L;
	        StringBuffer sb=new StringBuffer();
	        sb.append(" update tb_borrow set ");
	        sb.append(" isBack=? ");
	        sb.append("  where id=?");
	        
	        String sql=sb.toString();
	        List<Object> paramsList=new ArrayList<Object>();
	        paramsList.add(1);
	        paramsList.add(id);
	        
	        Object [] params=paramsList.toArray();
	        Connection conn=null;
	        conn=DbPub.getConn();
	        num=DbPub.update(conn, sql, params);
	        DbPub.close(conn);
	        return num;
	}

}