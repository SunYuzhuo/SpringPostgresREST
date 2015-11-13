package com.confer.sun.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.confer.sun.entity.Category;

public class CategoryRepositoryImp extends SimpleJpaRepository<Category,Integer> implements CategoryRepository{
	private EntityManager em;
	public CategoryRepositoryImp(Class domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
	}
	
	@Override
	public Category findCategoryByName(String name) {
		Iterable<Category> ci = findAll();
		for(Category c: ci){
			if(c.getName().equals(name))	return c;
		}
		return null;
	}
	
}
