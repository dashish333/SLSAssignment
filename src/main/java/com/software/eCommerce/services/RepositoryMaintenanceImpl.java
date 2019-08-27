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
import com.software.eCommerce.services.Product;
import com.software.eCommerce.services.RepositoryMaintenance;

/**
 * @author ashish
 *
 */

public class RepositoryMaintenanceImpl implements RepositoryMaintenance {
	private Map<String, List<Book> > allProducts = new HashMap<String, List<Book>>();
	
	public void addProduct(Product product, String productCategory) {
		System.out.println(((Book) product).getAuthor());
		System.out.println(productCategory);
		if(allProducts.containsKey(productCategory)) {
			List<Book> inventoryUpdateOfThisCategory = allProducts.get(productCategory);
			inventoryUpdateOfThisCategory.add((Book) product);
			allProducts.replace(productCategory, inventoryUpdateOfThisCategory);
		}
		else
		{
			List<Book> inventoryUpdateOfThisCategory = new ArrayList<Book>();
			inventoryUpdateOfThisCategory.add((Book) product);
			allProducts.put(productCategory, inventoryUpdateOfThisCategory);
		}

	}
	public void searchProduct(Category category,String itemName) {
		System.out.println("Searching....."+itemName);
		if(allProducts.containsKey(category.name()))
		{
			int matches = 0;
			List<Book> items = allProducts.get(category.name());
			BasicUserInterface bui = new BasicUserInterface();
			for (Product item : items) {
				if((((Book)item).getTitle()).equals(itemName))
				{
					bui.printItem(((Book)item).getProductCategory(),itemName, ((Book)item).getAuthor(), 
							((Book)item).getPrice());
					matches++;
				}
			}
			System.out.printf("Total Items Matching Your Search = {%d}",matches);
		}
		else
		{
			System.out.println("No match found. Try Again!");
		}
	
	}

	public Map<String, List<Book>> getAllProducts() {
		return allProducts;
	}
	public void printAllProducts() {
		List<Book>itemsInCategory = allProducts.get(Category.BOOK.name());
		BasicUserInterface bui = new BasicUserInterface();
		System.out.println("Displaying All Items.....");
		for (Book item : itemsInCategory) {
			bui.printItem(item.getProductCategory(),item.getTitle(),item.getAuthor(),item.getPrice());
		}
	}
	public List<Book> getProduct(Category category, String itemName) {
			List<Book> items = allProducts.get(category.name());
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
