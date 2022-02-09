package com.anim.FullStack.SpringBack.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anim.FullStack.SpringBack.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public Customer findByEmail(String theEmail);
}
