package com.fenix.ecom.returns.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenix.ecom.returns.data.OrderItemValueObject;

@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = -8489871436210653082L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "unit_price")
	private BigDecimal unitPrice;
	
	@Column(name = "unit_tax")
	private BigDecimal unitTax;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "shipping_date")
	private Date shippingDate;
	
	@Column(name = "delivery_date")
	private Date deliveryDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@OneToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "item_status")
	private OrderItemStatus itemStatus;
	
	@Column(name = "item_status_date")
	private Date itemStatusDate;

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getUnitTax() {
		return unitTax;
	}

	public void setUnitTax(BigDecimal unitTax) {
		this.unitTax = unitTax;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderItemStatus getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(OrderItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getItemStatusDate() {
		return itemStatusDate;
	}

	public void setItemStatusDate(Date itemStatusDate) {
		this.itemStatusDate = itemStatusDate;
	}

	public OrderItemValueObject getValueObject() {
		OrderItemValueObject reqObj = new OrderItemValueObject();
		reqObj.setId(this.id);
		reqObj.setProduct(this.product.getValueObject());
		reqObj.setQuantity(this.quantity);
		reqObj.setShippingDate(this.shippingDate);
		reqObj.setUnitPrice(this.unitPrice);
		reqObj.setUnitTax(this.unitTax);
		reqObj.setItemStatus(this.itemStatus);

		if (null != this.deliveryDate) {
			reqObj.setDeliveryDate(this.deliveryDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(this.deliveryDate);
			cal.add(Calendar.DAY_OF_YEAR, 30);
			reqObj.setMaxReturnDate(cal.getTime());
			if (new Date().after(cal.getTime())) {
				reqObj.setReturnEligible(false);
			} else {
				reqObj.setReturnEligible(true);
			}
		}
		if (OrderItemStatus.RETURN_INITIATED.equals(itemStatus)) {
			reqObj.setReturnEligible(false);
		}
		reqObj.setItemStatusDate(this.getItemStatusDate());
		return reqObj;
	}
}
