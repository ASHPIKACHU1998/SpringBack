package com.anim.FullStack.SpringBack.Dto; //DTO stands for Data Transfer object

import java.util.Set;

import com.anim.FullStack.SpringBack.entity.Address;
import com.anim.FullStack.SpringBack.entity.Customer;
import com.anim.FullStack.SpringBack.entity.Order;
import com.anim.FullStack.SpringBack.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {
	
	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private Order order;
	private Set<OrderItem> orderItems;
}
