package com.fenix.ecom.returns.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenix.ecom.returns.entity.Order;
import com.fenix.ecom.returns.entity.OrderItem;
import com.fenix.ecom.returns.repositories.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public Order findByOrderId(String orderId) {
		Order order = orderRepository.findByOrderId(orderId);
		
		List<OrderItem> items = order.getItems();
		System.out.println(items);
		
		return order;
	}

	public Order findByOrderIdAndEmailOrPhone(String orderId, String email, String phone) {
		return orderRepository.findByOrderIdAndEmailOrPhone(orderId, email, phone);
	}
}
