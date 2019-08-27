package com.software.eCommerce.UI;

import java.util.Scanner;

import com.software.eCommerce.services.Category;

public class BasicUserInterface implements UserInterface {
	
	private static final Scanner input = new Scanner(System.in);
	
	public void userInstructions() {
	System.out.println("!!Welcome!!");
    System.out.println("Choose correspoding number for your choice");
    System.out.println("1: Search for Book");
    System.out.println("2: List all the books available");
    System.out.println("3: Buy a Book");
    System.out.println("4. View Cart ");

	}

	public int getIntInput() {
		System.out.println("\n Enter Choice ");
		int userInput =  input.nextInt();
		input.nextLine();
		return userInput;
	}

	public void displayWelcomeInformation() {
		System.out.println("Welcome: This is your Cart \n");
		System.out.println("Type Of Product \t|\t Item \t|\t ProductDetail  \t| \t price \n");
		
	}
	public void printItem(Category category, String itemName,String additionalDetail ,int price) {
		System.out.printf("ProductCategory-{%s} | ItemName-{%s} | Authored by-{%s} | price($) - {%d}\n",category.BOOK.name(),itemName,additionalDetail,price);
	}
	
	public int selectItems() {
		int choice;
		System.out.println("Choose from below menu - \n");
		userInstructions();
		choice = getIntInput();		
		return choice;
	}
	
	public String getStringInput() {
		String itemName =  input.nextLine();
		return itemName;
	}

	public int optimiseSearch() {
		System.out.println("For Optimise Search- Choose the parameter to searched upon");
		System.out.println("1. BookTitle");
		System.out.println("2. PublishedYear");
		System.out.println("3. Author");
		return getIntInput();
	}	
}