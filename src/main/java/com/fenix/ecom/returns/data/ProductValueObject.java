package com.fenix.ecom.returns.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductValueObject implements Serializable {

	private static final long serialVersionUID = 8079058612653621766L;

	private Integer id;
	private String code;
	private String name;
	private BigDecimal price;
	private String imageUrl;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
