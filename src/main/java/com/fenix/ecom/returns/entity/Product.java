package com.fenix.ecom.returns.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fenix.ecom.returns.data.ProductValueObject;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = -3991954881429532092L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique = true)
	private String code;
	
	private String name;
	
	private BigDecimal price;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@ManyToMany
	@JoinTable(name = "product_super_category",
    joinColumns = @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    ),
    inverseJoinColumns = @JoinColumn(
            name = "super_category_id",
            referencedColumnName = "id"
    ))
	private List<Category> superCategory;

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

	public List<Category> getSuperCategory() {
		return superCategory;
	}

	public void setSuperCateggory(List<Category> superCategory) {
		this.superCategory = superCategory;
	}

	public ProductValueObject getValueObject() {
		ProductValueObject pr = new ProductValueObject();
		pr.setCode(this.code);
		pr.setId(this.id);
		pr.setImageUrl(this.imageUrl);
		pr.setName(this.name);
		pr.setPrice(this.price);
		return pr;
	}
}
