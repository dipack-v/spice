package com.spice.dao.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private Long id;
	private String code;
	private String desc;
	public Item() {
		super();
	}
	
	public Item(Long id, String code, String desc) {
		super();
		this.id = id;
		this.code = code;
		this.desc = desc;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	
}
