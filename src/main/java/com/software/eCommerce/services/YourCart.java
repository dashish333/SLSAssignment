package com.software.eCommerce.services;

import java.util.List;

import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.UI.UserInterface;
import com.software.eCommerce.datamodel.Book;

public class YourCart implements Cart {

	private List<Book> itemsInCart;
	private static UserInterface userInterface= null;
	
	public void addItemToCart(Product product) {
		itemsInCart.add((Book) product);
	}

	public void viewCart() {
		userInterface = new BasicUserInterface();
		userInterface.displayWelcomeInformation();
		
		List<Book> itemsInCart = getCart();
		for (Book item : itemsInCart) {
			
			userInterface.printItem(item.getTitle(),item.getAuthor(), item.getPrice());
		}
	}
	
	private List<Book> getCart(){
		return itemsInCart;
		
	}
	

	

	

}
