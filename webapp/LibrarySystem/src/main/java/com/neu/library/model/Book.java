package com.neu.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Book {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = "varchar(100)")
	private String id;

	private String title;
	private String author;
	private String isbn;
	private int quantity;
	public Book() {
		
	}
	
	

	public Book(String title, String author, String isbn, int quantity) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.quantity = quantity;
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
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	



}
