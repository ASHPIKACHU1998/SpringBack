package com.anim.FullStack.SpringBack.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.anim.FullStack.SpringBack.entity.Country;
import com.anim.FullStack.SpringBack.entity.Order;
import com.anim.FullStack.SpringBack.entity.Product;
import com.anim.FullStack.SpringBack.entity.ProductCategory;
import com.anim.FullStack.SpringBack.entity.State;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{

	private EntityManager entityManager;
	
	@Value("${allowed.origins}")
	private String[] theAllowedOrigins;
	
	@Autowired
	public MyDataRestConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		// TODO Auto-generated method stub
		HttpMethod[] unsupportedActions = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT};
		
		//It will disable http methods PUT, POST, DELETE for Product repository
		disableHttpMethods(Product.class, config, unsupportedActions);
		
		//It will disable http methods PUT, POST, DELETE for ProductCategory repository
		disableHttpMethods(ProductCategory.class, config, unsupportedActions);
		
	
		//It will disable http methods PUT, POST, DELETE for Country repository
		disableHttpMethods(Country.class, config, unsupportedActions);
		
				
		//It will disable http methods PUT, POST, DELETE for State repository
		disableHttpMethods(State.class, config, unsupportedActions);
		
		//It will disable http methods PUT, POST, DELETE for Order repository
		disableHttpMethods(Order.class, config, unsupportedActions);
		
		exposeIds(config);
		
		//cors configuration for Spring Data REST
		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
		
	}


	private ExposureConfigurer disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
		return config.getExposureConfiguration()
			  .forDomainType(theClass)
			  .withItemExposure((metadata,httpMethods) -> httpMethods.disable(unsupportedActions))
			  .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions));
	}


	private void exposeIds(RepositoryRestConfiguration config) {
		
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		List<Class> entityClasses = new ArrayList<>();
		
		for(EntityType tempEntityType: entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
		
	}

}
