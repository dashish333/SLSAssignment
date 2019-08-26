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
	public boolean searchProduct(String itemName,String productCategory, Map<String,List<Product>> allProducts) {
		if(allProducts.containsKey(productCategory))
		{
			List<Product> items = allProducts.get(productCategory);
			for(Product item : items)
			{
				// go through each item and check for the  if item exist
			}
			
			
		}
		return false;
	}

	public Map<String, List<Book>> getAllProducts() {
		
		return allProducts;
	}

}
