package edu.libsys.bean;

import java.util.Date;

public class BorrowBean {
	private Long id;// 借阅编号
	private String BookISBN;// 书籍编号
	private String BookName;// 图书名称
	private Long operatorId;// 操作员编号
	private String operatorName;// 操作员名称
	private String readerISBN;// 读者编号
	private String readerName;// 读者名称
	private Long isback;// 是否归还
	private Date borrowDate;// 借书日期
	private Date backDate;// 应还日期

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookISBN() {
		return BookISBN;
	}

	public void setBookISBN(String bookISBN) {
		BookISBN = bookISBN;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getReaderISBN() {
		return readerISBN;
	}

	public void setReaderISBN(String readerISBN) {
		this.readerISBN = readerISBN;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public Long getIsback() {
		return isback;
	}

	public void setIsback(Long isback) {
		this.isback = isback;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getBackDate() {
		return backDate;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

}
