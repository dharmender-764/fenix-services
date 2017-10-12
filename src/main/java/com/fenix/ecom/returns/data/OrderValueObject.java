package com.fenix.ecom.returns.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderValueObject implements Serializable {
	
	private static final long serialVersionUID = -3179747502192261696L;
	
	@NotEmpty(message = "Order Number is Required.")
	private String orderId;
	private String email;
	private String phone;
	private String customerName;
	private Date orderDate;
	private BigDecimal totalAmount;
	private BigDecimal totalTax;
	private List<OrderItemValueObject> orderItems;
	
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
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
	

	@AssertTrue(message = "Either the Phone number or Email used for billing is required.")
	public boolean isEmailOrPhoneEntered() {
		return !StringUtils.isEmpty(email) || !StringUtils.isEmpty(phone);
	}
	
	@AssertTrue(message = "Please enter 10 digit Phone Number.")
	public boolean isPhoneValid() {
		if(StringUtils.isEmpty(phone)) {
			return true;
		}
		return NumberUtils.isDigits(phone) && phone.length() == 10;
	}
	public List<OrderItemValueObject> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemValueObject> orderItems) {
		this.orderItems = orderItems;
	}
}
