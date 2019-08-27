/**
 * 
 */
package com.software.eCommerce.services;

import java.util.List;
import java.util.Map;

import com.software.eCommerce.datamodel.Book;

/**
 * @author ashish
 *
 */
public interface RepositoryMaintenance {
	void addProduct(Product product, String productCategory);
	void searchProduct(Category category,String itemName);
	Map<String, List<Book>> getAllProducts();
	List<Book> getProduct(Category category, String itemName);
	void printAllProducts();
	
}
