package edu.libsys.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liuvei.common.SysFun;

import edu.libsys.bean.OrderBean;
import edu.libsys.dao.OrderDao;
import edu.libsys.util.DbPub;

public class OrderDaoImpl implements OrderDao {

	@Override
	public Long insert(OrderBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into tb_order(ISBN,date,number,operator,cheakAndAccept,zk)");
		sb.append(" values(?, ?, ?, ?, ?, ?)");
		paramsList.add(bean.getISBN());
		paramsList.add(bean.getDate());
		paramsList.add(bean.getNumber());
		paramsList.add(bean.getOperator());
		paramsList.add(bean.getCheakAndAccept());
		paramsList.add(bean.getZk());
		
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
		sb.append(" delete from tb_order ");
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
	public Long update(OrderBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update tb_order set ");
		sb.append(" date = ? ");
		sb.append(" ,number = ? ");
		sb.append(" ,operator = ? ");
		sb.append(" ,cheakAndAccept = ? ");
		sb.append(" ,zk = ? ");
		sb.append(" where ISBN = ? ");
		paramsList.add(bean.getDate());
		paramsList.add(bean.getNumber());
		paramsList.add(bean.getOperator());
		paramsList.add(bean.getCheakAndAccept());
		paramsList.add(bean.getZk());
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
	public List<OrderBean> list() {
		// TODO Auto-generated method stub
		// 定义一个泛型接受表的数据
		List<OrderBean> list = new ArrayList<OrderBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from tb_order ");
		sb.append(" where 1=1 ");
		sb.append(" order by ISBN  ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			OrderBean bean = null;
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

	private OrderBean toBean(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		OrderBean bean;
		bean = new OrderBean();
		bean.setISBN(rs.getString("ISBN"));
		bean.setDate(rs.getTimestamp("date"));
		bean.setNumber(rs.getLong("number"));
		bean.setOperator(rs.getString("operator"));
		bean.setCheakAndAccept(rs.getLong("cheakAndAccept"));
		bean.setZk(rs.getDouble("zk"));
	
		return bean;
	}

	@Override
	public OrderBean load(String ISBN) {
		// TODO Auto-generated method stub
		OrderBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select * From tb_order");
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
		sb.append("Select count(1) From tb_order");
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
	public OrderBean loadByName(String name) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public List<OrderBean> listByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderBean> listBySearch(String ISBN, String operator) {
		// TODO Auto-generated method stub
		List<OrderBean> list = new ArrayList<OrderBean>();

        StringBuffer sb = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();
        sb.append(" select * from tb_order ");
        sb.append(" where 1=1 ");
        if (!SysFun.isNullOrEmpty(ISBN)) {
            sb.append("  and ISBN like ?");
            ISBN = "%" + ISBN + "%";
            paramsList.add(ISBN);
        }
        if (!SysFun.isNullOrEmpty(operator)) {
            sb.append("  and operator like ?");
            operator = "%" + operator + "%";
            paramsList.add(operator);
        }
        sb.append("  order by ISBN");

        String sql = sb.toString();
        Object[] params = paramsList.toArray();
        Connection conn = null;
        ResultSet rs = null;

        conn = DbPub.getConn();
        rs = DbPub.query(conn, sql, params);

        try {
            OrderBean bean = null;
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

	@Override
	public Long acceptBook(String ISBN) {
		// TODO Auto-generated method stub
		 Long num=0L;
	        StringBuffer sb=new StringBuffer();
	        sb.append(" update tb_order set ");
	        sb.append(" cheakAndAccept=? ");
	        sb.append("  where ISBN=?");
	        
	        String sql=sb.toString();
	        List<Object> paramsList=new ArrayList<Object>();
	        paramsList.add(1);
	        paramsList.add(ISBN);
	        
	        Object [] params=paramsList.toArray();
	        Connection conn=null;
	        conn=DbPub.getConn();
	        num=DbPub.update(conn, sql, params);
	        DbPub.close(conn);
	        return num;
	}

	
}