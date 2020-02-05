package edu.libsys.bean;

import java.util.Date;

public class OrderBean {
	private String ISBN;// 书籍编号
	private Date date;// 订购日期
	private Long number;// 订购数量
	private String operator;// 操作员
	private Long cheakAndAccept;// 是否验收
	private Double zk;// 书籍折扣

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Long getCheakAndAccept() {
		return cheakAndAccept;
	}

	public void setCheakAndAccept(Long cheakAndAccept) {
		this.cheakAndAccept = cheakAndAccept;
	}

	public Double getZk() {
		return zk;
	}

	public void setZk(Double zk) {
		this.zk = zk;
	}
}
