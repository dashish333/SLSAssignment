package com.software.eCommerce.services;

import java.util.ArrayList;
import java.util.List;

import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.UI.UserInterface;
import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.util.Cart;


public class YourCart implements Cart {

	private List<Book> itemsInCart = null;
	private static final UserInterface userInterface = new BasicUserInterface();
	
	public void addItemToCart(List<Book> books) {
		itemsInCart = new ArrayList<Book>(books);
	}

	public void viewCart() {
		
		userInterface.displayWelcomeInformation();
		List<Book> itemsInCart = getCart();
		userInterface.showFewRecords(itemsInCart);
	}
	
	private List<Book> getCart(){
		if(itemsInCart == null) {itemsInCart = new ArrayList<Book>();}
		return itemsInCart;
		
	}
}
