package com.fenix.ecom.returns.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "category")
public class Category implements Serializable {
	private static final long serialVersionUID = 6735888829200059052L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique = true)
	private String code;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name = "category_sub_category",
        joinColumns = @JoinColumn(
                name = "category_id",
                referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "sub_category_id",
                referencedColumnName = "id"
        ))
	private List<Category> subCategory;
	
	@ManyToMany
	@JoinTable(name = "category_super_category",
    joinColumns = @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    ),
    inverseJoinColumns = @JoinColumn(
            name = "super_category_id",
            referencedColumnName = "id"
    ))
	private List<Category> superCategory;
	
	@ManyToMany
	@JoinTable(name = "category_products",
    joinColumns = @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    ),
    inverseJoinColumns = @JoinColumn(
            name = "products_id",
            referencedColumnName = "id"
    ))
	private List<Product> products;

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

	public List<Category> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<Category> subCategory) {
		this.subCategory = subCategory;
	}

	public List<Category> getSuperCateggory() {
		return superCategory;
	}

	public void setSuperCategory(List<Category> superCategory) {
		this.superCategory = superCategory;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
