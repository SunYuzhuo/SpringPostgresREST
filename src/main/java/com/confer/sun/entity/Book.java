/**
 * This is an entity class for book.
 * @author: Yuzhuo Sun
 * @date: Nov 8, 2015
 */
package com.confer.sun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "isbn", nullable = false)
	private String isbn;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "author", nullable = false)
	private String author;
	
	@Column(name = "year_published", nullable = false)
	private String yeaPublished;
	
	public Book() {
	}

	public Book(int id, String isbn, String title, String author, String yeaPublished) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.yeaPublished = yeaPublished;
	}

	public Book(String isbn, String title, String author, String yeaPublished) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.yeaPublished = yeaPublished;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYeaPublished() {
		return yeaPublished;
	}

	public void setYeaPublished(String yeaPublished) {
		this.yeaPublished = yeaPublished;
	}
}
