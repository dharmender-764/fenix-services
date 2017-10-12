package com.fenix.ecom.returns.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenix.ecom.returns.data.OrderItemValueObject;
import com.fenix.ecom.returns.data.OrderValueObject;

@Entity
@Table(name="orders")
public class Order implements Serializable {
	
	private static final long serialVersionUID = -1228305934483115270L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique=true, name = "order_id")
	private String orderId;
	
	@Column(unique=true, name = "email")
	private String email;
	
	@Column(unique=true, name = "customer_name")
	private String customerName;
	
	@Column(unique=true, name = "phone")
	private String phone;
	
	@Column(unique=true, name = "order_date")
	private Date orderDate;
	
	@Column(unique=true, name = "total_amount")
	private BigDecimal totalAmount;
	
	@Column(unique=true, name = "total_tax")
	private BigDecimal totalTax;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "billing_address_id")
	private Address billingAddress;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "shipping_address_id")
	private Address shippingAddress;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade=CascadeType.ALL)
	private List<OrderItem> items;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public OrderValueObject getValueObject() {
		OrderValueObject vo = new OrderValueObject();
		vo.setEmail(this.email);
		vo.setOrderId(this.orderId);
		vo.setPhone(this.phone);
		vo.setCustomerName(this.customerName);
		vo.setOrderDate(vo.getOrderDate());
		vo.setTotalAmount(this.totalAmount);
		vo.setTotalTax(this.totalTax);
		List<OrderItemValueObject> itemVOList = new ArrayList<>();
		for (OrderItem orderItem : items) {
			itemVOList.add(orderItem.getValueObject());
		}
		vo.setOrderItems(itemVOList);
		
		return vo;
	}
}
