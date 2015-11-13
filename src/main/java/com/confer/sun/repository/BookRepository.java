package com.confer.sun.repository;

import com.confer.sun.entity.Book;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer>{
	Book findBooksByIsbn(String isbn);
	List<Book> findBooksByAuthor(String author);
	List<Book> findBooksByTitle(String title);
}
