/**
 * 
 */
package com.software.eCommerce.util;

import java.util.List;
import java.util.Map;

import com.software.eCommerce.datamodel.Book;

/**
 * @author ashish
 *
 */
public interface RepositoryMaintenance {
	void addProduct(Book book, Category productCategory);
	void searchProduct(Category category,String itemName);
	Map<Category, List<Book>> getAllProducts();
	List<Book> getProduct(Category category, String itemName);
	void printAllProducts();
	
}
