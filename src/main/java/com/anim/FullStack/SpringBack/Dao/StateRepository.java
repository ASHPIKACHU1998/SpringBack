package com.anim.FullStack.SpringBack.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.anim.FullStack.SpringBack.entity.State;


@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer> {
	@RestResource(rel="country", path="country")
	List<State> findByCountryCode(@RequestParam("code") String code);
}
