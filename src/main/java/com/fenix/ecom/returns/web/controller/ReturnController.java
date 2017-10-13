package com.fenix.ecom.returns.web.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenix.ecom.returns.common.Constants;
import com.fenix.ecom.returns.data.OrderValueObject;
import com.fenix.ecom.returns.data.ReturnFeeRequest;
import com.fenix.ecom.returns.entity.Category;
import com.fenix.ecom.returns.entity.Order;
import com.fenix.ecom.returns.entity.OrderItem;
import com.fenix.ecom.returns.entity.OrderItemStatus;
import com.fenix.ecom.returns.entity.Product;
import com.fenix.ecom.returns.repositories.OrderItemRepository;
import com.fenix.ecom.returns.repositories.ProductRepository;
import com.fenix.ecom.returns.service.OrderService;
import com.fenix.ecom.returns.service.ReturnFeeService;

@RestController
public class ReturnController {

	@Autowired
	ReturnFeeService returnFeeService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@RequestMapping(value = "/orderdetails", method = RequestMethod.POST, produces = { "application/json" })
	public OrderValueObject order(@Valid @RequestBody OrderValueObject orderValueObject) throws Exception {
		Order order = orderService.findByOrderIdAndEmailOrPhone(orderValueObject.getOrderId(), orderValueObject.getEmail(), orderValueObject.getPhone());
		if(order == null) {
			throw new Exception("Error: Invalid Order, please check Order Number and Email/Phone.");
		}
		return order.getValueObject();
	}
	
	@RequestMapping(value = "/cancelreturn", method = RequestMethod.GET, produces = { "application/json" })
	public OrderValueObject cancelReturn(@RequestParam("orderId") String orderId) {
		Order order = orderService.findByOrderId(orderId);
		return order.getValueObject();
	}

	@RequestMapping(value = "/refund_step1", method = RequestMethod.GET, produces = { "application/json" })
	public Map<String, Object> refundStep1(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String orderId = request.getParameter("orderId");
		String productId = request.getParameter("productId");
		String returnReasonId = request.getParameter("returnReasonId");
		String orderItemId = request.getParameter("orderItemId");
		if (null != orderItemId) {
			OrderItem item = orderItemRepository.findOne(Integer.valueOf(orderItemId));
			modelMap.put("orderItem", item.getValueObject());
		}
		Product prod = productRepository.findOne(Integer.parseInt(productId));
		modelMap.put("productDetails", prod.getValueObject());
		modelMap.put("orderId", orderId);
		modelMap.put("returnReasonId", returnReasonId);
		return modelMap;
	}

	@RequestMapping(value = "/refund_step2", method = RequestMethod.GET, produces = { "application/json" })
	public Map<String, Object> refundStep2(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String orderId = request.getParameter("orderId");
		String orderItemId = request.getParameter("orderItemId");
		String productId = request.getParameter("productId");
		String returnReasonId = request.getParameter("returnReasonId");
		String returnMode = request.getParameter("returnMode");
		Product prod = productRepository.findOne(Integer.parseInt(productId));

		modelMap.put("productDetails", prod.getValueObject());
		modelMap.put("orderId", orderId);
		modelMap.put("orderItemId", orderItemId);
		modelMap.put("returnReasonId", returnReasonId);
		modelMap.put("returnMode", returnMode);
		modelMap.put("returnReasonString", Constants.RETURN_REASONS.get(Integer.parseInt(returnReasonId)));
		return modelMap;
	}

	@RequestMapping(value = "/refund_step3", method = RequestMethod.GET, produces = { "application/json" })
	public Map<String, Object> refundStep3(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String orderId = request.getParameter("orderId");
		String productId = request.getParameter("productId");
		String returnReasonId = request.getParameter("returnReasonId");
		String orderItemId = request.getParameter("orderItemId");
		String returnMode = request.getParameter("returnMode");
		Order order = orderService.findByOrderId(orderId);
		Product prod = productRepository.findOne(Integer.parseInt(productId));
		if("2".equals(returnMode)){
			List<Category> categories = prod.getSuperCategory();
			if(!CollectionUtils.isEmpty(categories)){
				ReturnFeeRequest returnFeeRequest = new ReturnFeeRequest();
				returnFeeRequest.setCategoryCode(categories.get(0).getCode());
				BigDecimal fee = returnFeeService.getReturnFee(returnFeeRequest);
				modelMap.put("returnFee", fee);
				System.out.println(fee);
			}
		}
		modelMap.put("productDetails", prod.getValueObject());
		modelMap.put("orderId", order.getOrderId());
		modelMap.put("orderEmail", order.getEmail());
		modelMap.put("orderItemId", orderItemId);
		modelMap.put("returnReasonId", returnReasonId);
		modelMap.put("returnReasonString", Constants.RETURN_REASONS.get(Integer.parseInt(returnReasonId)));
		modelMap.put("returnMode", returnMode);
		return modelMap;
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET, produces = { "application/json" })
	public Map<String, Object> confirm(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String orderId = request.getParameter("orderId");
		String orderItemId = request.getParameter("orderItemId");
		if (null != orderItemId) {
			OrderItem item = orderItemRepository.findOne(Integer.valueOf(orderItemId));
			item.setItemStatus(OrderItemStatus.RETURN_INITIATED);
			item.setItemStatusDate(new Date());
			orderItemRepository.save(item);
			modelMap.put("confirmationNumber", "RTRN"+orderId);
		}
		return modelMap;
	}
}
