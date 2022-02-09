package com.anim.FullStack.SpringBack.Dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestParam;

import com.anim.FullStack.SpringBack.entity.Product;

@RepositoryRestResource
public interface ProductDAO extends JpaRepository<Product, Long> {
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
//	Page<Product> findByCategoryIdOrDateCreated(@RequestParam("id") Long id,
//		@RequestParam("date") @DateTimeFormat(pattern="mmddyyyy") Date date, Pageable pageable);
	@RestResource(path="nameContaining", rel="ContainingsName")
	Page<Product> findBySnameContaining(@RequestParam("name") String name, Pageable pageable);
}