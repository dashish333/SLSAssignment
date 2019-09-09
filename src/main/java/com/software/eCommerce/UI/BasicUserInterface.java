package com.software.eCommerce.UI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.software.eCommerce.datamodel.Book;
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
		System.out.println("-------------- Welcome: This is your Cart -------------");
		
	}
	public void printItem(Category category, String itemName,String additionalDetail ,int price,int year) {
		System.out.format("%s %40s%30s%20d%20d", category.name() ,itemName, additionalDetail, price, year);
		System.out.println();
	}
	public void printHeader() 
	{
		System.out.format("%s %40s%30s%20s%20s\n", "Product Category" ,"Title", "Author Name", "Price", "Year of Publishing");
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
		System.out.println("2. Year");
		System.out.println("3. Author");
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

	public void showFewRecords(List<Book> keyBooksMapping) {
		//System.out.println("Product Category \t\t %s \t\t %s \t\t %s \t\t %s \n");
		if(keyBooksMapping.size() > 10)
		{	int numberOfRecordsToShow = 10;
		System.out.println("Showing top 10 matched items");
			for (Book book: keyBooksMapping) 
			{	
				printItem(book.getProductCategory(), book.getTitle(), book.getAuthor(), book.getPrice(),book.getYear());
				numberOfRecordsToShow--;
				
			}
		}
		for(Book book : keyBooksMapping) 
        {
        	printItem(book.getProductCategory(), book.getTitle(), book.getAuthor(), book.getPrice(),book.getYear());
        }
	}
	
	public int pagination(List<Book> keyBooksMapping) {
		if(keyBooksMapping.size() != 0) 
		{
			printHeader();
			for(Book book : keyBooksMapping) 
	        {
	        	printItem(book.getProductCategory(), book.getTitle(), book.getAuthor(), book.getPrice(),book.getYear());
	        }
		}
		else {System.out.println("Records Found");}
		System.out.println("See Next Records? Press 1.");
		System.out.println("See Previous Records? Press 2.");
		int choice = getIntInput();
		return choice;
	}
		
	public void printSearchTime(double searchTime) {
		System.out.printf("\nAll matching Results Returned in %.4f (msec.)\n",searchTime);
	}

	public void showCart(HashMap<Book, Integer> yourCart) {
		if(yourCart.isEmpty()) {System.out.println("Cart Empty....");return;}
		int itemNumber = 1;
		for (Map.Entry<Book, Integer> entry : yourCart.entrySet()) {
			System.out.printf("\nItemNumber - %d\n",itemNumber);
			printCart(entry.getKey(), entry.getValue());
			itemNumber++;
		}
	}
	private void printCart(Book book, int qty) {
		System.out.printf("--Product Type - %s\n",book.getProductCategory());
		System.out.printf("  Title - %s\n",book.getTitle());
		System.out.printf("  Author - %s\n",book.getAuthor());
		System.out.printf("  Publisher - %s\n",book.getPublisher());
		System.out.printf("  Price per Unit - %s\n",book.getPrice());
		System.out.printf("  Quantity - %d\n",qty);
	}

	public Book showMultipleChoices(List<Book> books) {
		int choice  = 0;
		if(books.size() > 1)
		{
			System.out.println("Multiple Choice Matching your search... select the one you want to buy");
			for (Book book : books) 
			{
				System.out.println((choice+1)+"."+book.getTitle()+" authored by -"+book.getAuthor()+ 
						" price -"+book.getPrice()+" isbn- "+book.getIsbn());
			}
		}
		return books.get(choice);
	}
}