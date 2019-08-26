/**
 * 
 */
package com.software.eCommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public boolean searchProduct(Category category,String itemName, Map<String,List<Product>> allProducts) {
		if(allProducts.containsKey(category.name()))
		{
			
			List<Product> items = allProducts.get(category.name());
			//for(Book item : items)
			{
				
			}
		}
		return false;
	}

	public Map<String, List<Book>> getAllProducts() {
		
		return allProducts;
	}

}
