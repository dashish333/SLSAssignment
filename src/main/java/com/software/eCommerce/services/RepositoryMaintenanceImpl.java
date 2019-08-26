/**
 * 
 */
package com.software.eCommerce.services;

import java.util.ArrayList;
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
	private Map<String, List<Book> > allProducts;

	public void addProduct(Product product, String productCategory) {
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
		System.out.println("Searching.....");
		if(allProducts.containsKey(category.name()))
		{
			int matches = 0;
			List<Book> items = allProducts.get(category.name());
			BasicUserInterface bui = new BasicUserInterface();
			for (Product item : items) {
				if(((Book)item).getTitle() == itemName)
				{
					bui.printItem(itemName, ((Book)item).getAuthor(), 
							((Book)item).getPrice());
					matches++;
				}
				System.out.printf("Total Items Matching Your Search = {}",matches);
			}
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
			bui.printItem(item.getTitle(),item.getAuthor(),item.getPrice());
		}
	}
	public Product getProduct(Category category, String itemName) {
			List<Book> items = allProducts.get(category.name());
			for (Product item : items) {
				if(((Book)item).getTitle() == itemName)
				{
					return item;
				}
			}
			System.out.println("Cannot Find the Product You Looking For!");
		return null;
	}

}
