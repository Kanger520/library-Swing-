package edu.libsys.bean;

public class BookTypeBean {
	private Long id;// 图书类别编号
	private String typeName;// 图书类别名称
	private Long days;// 可借天数
	private Double Fk;// 迟还一天的罚款

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getDays() {
		return days;
	}

	public void setDays(Long days) {
		this.days = days;
	}

	public Double getFk() {
		return Fk;
	}

	public void setFk(Double fk) {
		Fk = fk;
	}

}
