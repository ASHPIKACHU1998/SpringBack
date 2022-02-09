package com.anim.FullStack.SpringBack.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.anim.FullStack.SpringBack.entity.ProductCategory;


@RepositoryRestResource(collectionResourceRel = "productCategory", path="product-category")
public interface ProductCategoryDAO extends JpaRepository<ProductCategory, Long>{
	
}
