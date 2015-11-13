package com.confer.sun.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.confer.sun.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer>{
	Category findCategoryByName(String name);
}
