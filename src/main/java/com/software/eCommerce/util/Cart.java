/**
 * 
 */
package com.software.eCommerce.util;

import java.util.List;

import com.software.eCommerce.datamodel.Book;

/**
 * @author ashish
 *
 */
public interface Cart {
	
	void addItemToCart(List<Book> books);
	void viewCart();
	
	

}
