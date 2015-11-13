/**
 * Home controller class which handles all the REST mapping
 * @author Yuzhuo Sun
 * @date: Nov 8, 2015
 */

package com.confer.sun.controller;

import com.confer.sun.entity.Book;
import com.confer.sun.entity.Category;
import com.confer.sun.repository.BookRepository;
import com.confer.sun.repository.CategoryRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

	@Inject
	BookRepository bookRepository;

	@Inject
	CategoryRepository categoryRepository;

	/**
	 * 
	 * Mapped to /hello GET: Test REST connection method
	 * 
	 * @return Hello there!
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHello() {
		return "Hello there !";
	}
	
	/**
	 * 
	 * Mapped to /book/{isbn}/{title}/{author}/{year}
	 * Create a new book in database
	 * 
	 * @param isbn
	 * @param title
	 * @param author
	 * @param year
	 * @return saved book
	 */
	@RequestMapping(value = "/book/{isbn}/{title}/{author}/{year}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book createBook(@PathVariable String isbn, @PathVariable String title, @PathVariable String author,
			@PathVariable String year) {
		if(bookRepository.findBooksByIsbn(isbn) != null) return null;
		return bookRepository.save(new Book(isbn, title, author, year));
	}

	/**
	 * 
	 * Mapped to /book
	 * Retrieve all book instance from database
	 * 
	 * @return all books in database
	 */
	@RequestMapping(value = "/book", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> findAllBook() {
		final List<Book> resultList = new ArrayList<>();
		final Iterable<Book> all = bookRepository.findAll();
		all.forEach(new Consumer<Book>() {
			@Override
			public void accept(Book book) {
				resultList.add(book);
			}
		});
		return resultList;
	}

	/**
	 * 
	 * Mapped to /search/book/isbn/{isbn}
	 * Find book by a given isbn
	 * 
	 * @param isbn
	 * @return book match given isbn
	 */
	@RequestMapping(value = "/search/book/isbn/{isbn}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book findBookByIsbn(@PathVariable String isbn) {
		final Book book = bookRepository.findBooksByIsbn(isbn);
		return book;
	}
	
	/**
	 * 
	 * Mapped to /search/book/author/{author}
	 * Find all books by given author name
	 * 
	 * @param author
	 * @return List<Book> books match given author
	 */
	@RequestMapping(value = "/search/book/author/{author}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> findBookByAuthor(@PathVariable String author) {
		return bookRepository.findBooksByAuthor(author);
	}
	
	/**
	 * 
	 * Mapped to /search/book/title/{title}
	 * Find all books by given title
	 * 
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "/search/book/title/{title}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> findBookByTitle(@PathVariable String title) {
		
		return bookRepository.findBooksByTitle(title);
	}

	/**
	 * 
	 * Mapped to /category/{name}/{description}
	 * Create a category by name and description parameters
	 * 
	 * @param name
	 * @param description
	 * @return save category instance
	 */
	@RequestMapping(value = "/category/{name}/{description}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Category createCategory(@PathVariable String name, @PathVariable String description) {

		return categoryRepository.save(new Category(name, description));
	}

	/**
	 * 
	 * Mapped to /category/{name}/{description}/{isbn}
	 * Create a category by name, description and related book isbn parameters
	 * @param name
	 * @param description
	 * @param isbn
	 * @return saved category instance
	 */
	@RequestMapping(value = "/category/{name}/{description}/{isbn}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Category createCategoryWithBook(@PathVariable String name, @PathVariable String description,
			@PathVariable String isbn) {

		Book b = bookRepository.findBooksByIsbn(isbn);
		
		if(categoryRepository.findCategoryByName(name) != null){
			Category tmp = categoryRepository.findCategoryByName(name);
			if(tmp.getBooks() != null){
				List<Book> bl = tmp.getBooks();
				if(!bl.contains(b)) bl.add(b);
				else return null;
				tmp.setBooks(bl);
			}else{
				List<Book> bl = new ArrayList<>();
				bl.add(b);
				tmp.setBooks(bl);
			}
			return categoryRepository.save(tmp);
		}
		
		Category c = new Category(name, description);

		List<Book> books = new ArrayList<>();
		books.add(b);
		c.setBooks(books);
		
		return categoryRepository.save(c);
	}
	
}
