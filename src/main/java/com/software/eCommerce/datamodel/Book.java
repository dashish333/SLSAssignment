/**
 * 
 */
package com.software.eCommerce.datamodel;

import com.software.eCommerce.services.Category;
import com.software.eCommerce.services.Product;

/**
 * @author ashish
 *
 */
public class Book implements Product {
	
	private final  String title;
	private final  String author;
	private final  String isbn;
	private final  String publisher;
	private final  String language;
	private final  String bindingType;
	private final int year;
	private final int price;
	public Book(String title, String author, String isbn, String publisher, String language, String bindingType,
			int year, int price) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publisher = publisher;
		this.language = language;
		this.bindingType = bindingType;
		this.year = year;
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getIsbn() {
		return isbn;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getLanguage() {
		return language;
	}
	public String getBindingType() {
		return bindingType;
	}
	public int getYear() {
		return year;
	}
	public int getPrice() {
		return price;
	}

	public Category getProductCategory() {
		
		return Category.BOOK;
	}
	
	
	
	
	
}
