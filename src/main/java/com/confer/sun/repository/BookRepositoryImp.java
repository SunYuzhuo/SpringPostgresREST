package com.confer.sun.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.confer.sun.entity.Book;

public class BookRepositoryImp extends SimpleJpaRepository<Book,Integer> implements BookRepository{
	private EntityManager em;
	public BookRepositoryImp(Class domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
	}

	@Override
	public Book findBooksByIsbn(String isbn){
		Iterable<Book> allBooks = findAll();
		for(Book b: allBooks){
			if(b.getIsbn().equals(isbn)){
				return b;
			}
		}
		return null;
	}

	@Override
	public List<Book> findBooksByAuthor(String author) {
		Iterable<Book> allBooks = findAll();
		List<Book> result = new ArrayList<>();
		for(Book b: allBooks){
			if(b.getAuthor().equals(author))
				result.add(b);
		}
		System.out.println(result.size());
		return result;
	}

	@Override
	public List<Book> findBooksByTitle(String title) {
		Iterable<Book> allBooks = findAll();
		System.out.println(title);
		List<Book> result = new ArrayList<>();
		for(Book b: allBooks){
			if(b.getTitle().contains(title))
				result.add(b);
		}
		return result;
	}
	
	
	
}
