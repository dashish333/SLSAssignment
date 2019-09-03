package com.software.eCommerce.services;

import java.util.ArrayList;
import java.util.List;

import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.UI.UserInterface;
import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.util.Cart;
import com.software.eCommerce.util.Category;


public class YourCart implements Cart {

	private List<Book> itemsInCart = null;
	private static final UserInterface userInterface = new BasicUserInterface();
	
	public void addItemToCart(List<Book> books) {
		if(itemsInCart !=null && itemsInCart.size() > 0) 
		{
			for(Book book : books) {
				itemsInCart.add(book);
			}
			
		}
		else {
			itemsInCart = new ArrayList<Book>(books);
		}
		
	}

	public void viewCart() {
		
		userInterface.displayWelcomeInformation();
		List<Book> itemsInCart = getCart();
		userInterface.showFewRecords(itemsInCart,Category.BOOK.name());
	}
	
	private List<Book> getCart(){
		if(itemsInCart == null) {itemsInCart = new ArrayList<Book>();}
		return itemsInCart;
		
	}
}
