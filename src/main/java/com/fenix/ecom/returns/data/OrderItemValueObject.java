package com.fenix.ecom.returns.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fenix.ecom.returns.entity.OrderItemStatus;

public class OrderItemValueObject implements Serializable {

	private static final long serialVersionUID = 8725750498559489350L;
	
	private Integer id;
	private BigDecimal unitPrice;
	private BigDecimal unitTax;
	private Integer quantity;
	private Date shippingDate;
	private Date deliveryDate;
	private ProductValueObject product;
	private OrderItemStatus itemStatus;
	private Date itemStatusDate;
	private Date maxReturnDate;
	private boolean returnEligible;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getUnitTax() {
		return unitTax;
	}
	public void setUnitTax(BigDecimal unitTax) {
		this.unitTax = unitTax;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Date getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}
	public ProductValueObject getProduct() {
		return product;
	}
	public void setProduct(ProductValueObject product) {
		this.product = product;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Date getMaxReturnDate() {
		return maxReturnDate;
	}
	public void setMaxReturnDate(Date maxReturnDate) {
		this.maxReturnDate = maxReturnDate;
	}
	public boolean isReturnEligible() {
		return returnEligible;
	}
	public void setReturnEligible(boolean returnEligible) {
		this.returnEligible = returnEligible;
	}
	public OrderItemStatus getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(OrderItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}
	public Date getItemStatusDate() {
		return itemStatusDate;
	}
	public void setItemStatusDate(Date itemStatusDate) {
		this.itemStatusDate = itemStatusDate;
	}
	public String getItemStatusDisplayString() {
		if(this.itemStatus != null) {
			return (this.itemStatus.name().replace('_', ' ').toLowerCase());
		}
		return "";
	}
}
