package com.fenix.ecom.returns.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fenix.ecom.returns.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	Order findByOrderIdAndEmailOrPhone(String orderId, String email, String phone);
	
	Order findByOrderId(String orderId);
}
