/**
 * 
 */
package com.software.eCommerce.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.util.Category;
import com.software.eCommerce.util.RepositoryMaintenance;
import com.software.eCommerce.util.SearchBookOn;

/**
 * 
 * @author ashish
 *
 */

public class RepositoryMaintenanceImpl implements RepositoryMaintenance {
	private Map<Category, List<Book>> allBooks = new HashMap<Category, List<Book>>();
	
	public void addProduct(Book book, Category category) {
		if(allBooks.containsKey(category)) {
			List<Book> inventoryUpdateOfThisCategory = allBooks.get(category);
			inventoryUpdateOfThisCategory.add(book);
			allBooks.replace(category, inventoryUpdateOfThisCategory);
		}
		else
		{
			List<Book> inventoryUpdateOfThisCategory = new ArrayList<Book>();
			inventoryUpdateOfThisCategory.add(book);
			allBooks.put(category, inventoryUpdateOfThisCategory);
		}
	}
	public void searchProduct(Category category,String itemName) {
		System.out.println("Searching....."+itemName);
		TimeTrackingUtilImpl timeTracker = new TimeTrackingUtilImpl();
		BasicUserInterface bui = new BasicUserInterface();
		double start = timeTracker.currentTime();
		if(allBooks.containsKey(category))
		{
			int matches = 0;
			List<Book> items = allBooks.get(category);
			
			for (Book item : items) {
				if((item.getTitle()).equals(itemName))
				{
					bui.printItem(item.getProductCategory(),itemName, ((Book)item).getAuthor(), 
							item.getPrice(),item.getYear());
					matches++;
				}
			}
			System.out.printf("Total Items Matching Your Search = {%d}",matches);
			
		}
		else
		{
			System.out.println("No match found. Try Again!");
		}
		double end = timeTracker.currentTime();
		bui.printSearchTime(end-start);
	}

	public Map<Category, List<Book> > getAllProducts() {
		return allBooks;
	}
	public void printAllProducts() {
		List<Book>itemsInCategory = allBooks.get(Category.BOOK);
		BasicUserInterface bui = new BasicUserInterface();
		System.out.println("Displaying All Items.....");
		System.out.println(itemsInCategory.size());
		return;
		//bui.showFewRecords(itemsInCategory);
		
	}
	public List<Book> getProduct(Category category, String itemName) {
			System.out.println(allBooks.size());
			List<Book> items = allBooks.get(category);
			System.out.println("itemsSize"+items.size());
			List<Book>matchedBook = new ArrayList<Book>();
			for (Book item : items) {
				if(item.getTitle().equals(itemName))
				{
					matchedBook.add(item);
				}
			}
			
		if (matchedBook.size()==0) {
			System.out.println("Can't find the product you looking for");
		}
		return matchedBook;
	}
	
	
}
