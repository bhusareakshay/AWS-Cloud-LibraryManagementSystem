package com.neu.library.json;

import com.neu.library.model.Book;

public class BookJson {
	
	private String id;
	private String title;
	private String author;
	private String isbn;
	private int quantity;
	
	public BookJson(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.isbn = book.getIsbn();
		this.quantity = book.getQuantity();
		this.author = book.getAuthor();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	

}
