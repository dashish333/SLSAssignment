package com.software.eCommerce.UI;

import java.util.Scanner;

import com.software.eCommerce.util.Category;
import com.software.eCommerce.util.OrderBy;
import com.software.eCommerce.util.SearchBookOn;

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
		System.out.printf("ProductCategory-{%s} | ItemName-{%s} | Authored by-{%s} | price($) - {%d}\n",
				category.name(),itemName,additionalDetail,price);
	}
	
	public int selectItems() {
		int choice;
		userInstructions();
		choice = getIntInput();		
		return choice;
	}
	
	public String getStringInput() {
		String itemName =  input.nextLine();
		return itemName;
	}

	public int optimiseSortSearch() {
		System.out.println("For Optimise Search/Sort- Choose the parameter to searched/sorted upon");
		System.out.println("1. BookTitle");
		System.out.println("2. Author");
		System.out.println("3. Year");
		return getIntInput();
	}
	
	public OrderBy sortedList(int sortByKey) {
		String sortedBy = null;
		if(sortByKey == 1) {sortedBy = SearchBookOn.title.name();}
		if(sortByKey == 2) {sortedBy = SearchBookOn.year.name();}
		if(sortByKey == 3) {sortedBy = SearchBookOn.author.name();}
		System.out.println(" To Sort Ascending/Descending on field "+ "<" +sortedBy + ">" +"- choose corresponding Number");
		System.out.println("1. ASC");
		System.out.println("2. DSC");
		int choice = getIntInput();
		if (choice == 1) {return OrderBy.ASC;}
		return OrderBy.DESC;
	}
	
	public SearchBookOn getSearchBookOn(int sortByKey) 
	{
		if (sortByKey == 1) {return SearchBookOn.title;}
		if (sortByKey == 2) {return SearchBookOn.year;}
		return SearchBookOn.author;
	}

	public void searchSortMenu() {
		// TODO Auto-generated method stub
		
	}
}