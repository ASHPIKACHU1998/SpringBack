package com.anim.FullStack.SpringBack.Service;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anim.FullStack.SpringBack.Dao.CustomerRepository;
import com.anim.FullStack.SpringBack.Dto.Purchase;
import com.anim.FullStack.SpringBack.Dto.PurchaseResponse;
import com.anim.FullStack.SpringBack.entity.Customer;
import com.anim.FullStack.SpringBack.entity.Order;
import com.anim.FullStack.SpringBack.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		//retrieve the order info from dto
		Order order = purchase.getOrder();
		
		// generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		//populate order with orderItems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		//populate billing address and shipping address
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		//populate customer with order
		
		Customer customer = purchase.getCustomer();
		
		String email = customer.getEmail();
		
		//Get the customer if present by using email
		Customer fromDB = this.customerRepository.findByEmail(email); 
		
		//Check if email is already present or not
		if(fromDB != null) {
			customer = fromDB;
		}
		
		customer.add(order);
		
		System.out.println(customer.toString());
		//save to database
		
		customerRepository.save(customer);
		
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		//generate random UUID
		
		return UUID.randomUUID().toString();
	}

}
