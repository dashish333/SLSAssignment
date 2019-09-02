package com.software.eCommerce.services;

import java.util.ArrayList;
import java.util.List;

import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.UI.UserInterface;
import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.util.Cart;


public class YourCart implements Cart {

	private  List<Book> itemsInCart = null;
	private static final UserInterface userInterface = new BasicUserInterface();
	
	public void addItemToCart(List<Book> books) {
		itemsInCart = new ArrayList<Book>(books);
	}

	public void viewCart() {
		
		userInterface.displayWelcomeInformation();
		
		List<Book> itemsInCart = getCart();
		for (Book item : itemsInCart) {
			userInterface.printItem(item.getProductCategory(),item.getTitle(),item.getAuthor(), item.getPrice());
		}
	}
	
	private List<Book> getCart(){
		return itemsInCart;
		
	}
}
