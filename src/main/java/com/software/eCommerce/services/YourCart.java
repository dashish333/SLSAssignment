package com.software.eCommerce.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.UI.UserInterface;
import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.util.Cart;
import com.software.eCommerce.util.Category;


public class YourCart implements Cart {

	private LinkedHashMap<Book, Integer> cartContent = new LinkedHashMap<Book, Integer>();
	private List<Book> itemsInCart = null;
	private static final UserInterface userInterface = new BasicUserInterface();
	
	public void addItemToCart(Book book, int qty) {
		if(cartContent != null && cartContent.size() > 0) 
		{
			
				if(cartContent.containsKey(book)) {
					cartContent.replace(book, qty);
				}
				else 
				{
					cartContent.put(book, qty);
				}
		}
		else 
		{
			cartContent = getCart();
			cartContent.put(book, qty);
		}
	}
	public void viewCart() {
		userInterface.displayWelcomeInformation();
		userInterface.showCart(getCart());
		
	}
	
	private LinkedHashMap<Book,Integer> getCart(){
		if(cartContent == null) {cartContent = new LinkedHashMap<Book, Integer>();}
		return cartContent;
		
	}
	public void modifyCart() {
		System.out.println("To Change Quantity of Item in Cart. Press 1");
		System.out.println("To Remove Item from Cart. Press 2");
		System.out.println("To Remove All Items From Cart. Press 3");
		List<Book> books = (List<Book>) cartContent.keySet();
		int choice = userInterface.getIntInput();
		if(choice==3) {cartContent.clear();viewCart();}
		if (choice == 2) 
		{
			System.out.println("Enter itemNumber");
			int itemNumber = userInterface.getIntInput();
			cartContent.remove(books.get(itemNumber));
			viewCart();
		}
		if(choice == 1) {
			System.out.println("Enter itemNumber");
			int itemNumber = userInterface.getIntInput();
			System.out.println("Enter the new Quantity");
			int qty = userInterface.getIntInput();
			cartContent.replace(books.get(itemNumber),qty);
			viewCart();
		}
		
	}
}
