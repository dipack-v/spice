package com.spice.dao.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String paymentType;
	private String lendingTermType;
	

}
