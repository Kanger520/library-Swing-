package edu.libsys.bean;

import java.util.Date;

public class TeaferBean {
	private String ISBN;// 读者编号
	private String name;// 读者姓名
	private String sex;// 读者性别
	private Long age;// 读者年龄
	private String identityCard;// 证件号码
	private Date date;// 会员有效日期
	private Long maxNum;// 最大借书量
	private String tel;// 电话号码
	private Double keepMoney;// 押金
	private Long zj;// 证件类型
	private String zy;// 职业
	private Date bztime;// 办证日期

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Long maxNum) {
		this.maxNum = maxNum;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Double getKeepMoney() {
		return keepMoney;
	}

	public void setKeepMoney(Double keepMoney) {
		this.keepMoney = keepMoney;
	}

	public Long getZj() {
		return zj;
	}

	public void setZj(Long zj) {
		this.zj = zj;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public Date getBztime() {
		return bztime;
	}

	public void setBztime(Date bztime) {
		this.bztime = bztime;
	}
}
