/**
 * 
 */
package com.software.eCommerce.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.software.eCommerce.datamodel.Books;
import com.software.eCommerce.services.Product;
import com.software.eCommerce.services.RepositoryMaintenance;

/**
 * @author ashish
 *
 */

public class Repository implements RepositoryMaintenance {
	private Map<String, List<Books> > allProducts;

	public void addProduct(Product product, String productCategory) {
		if(allProducts.containsKey(productCategory)) {
			List<Books> inventoryUpdateOfThisCategory = allProducts.get(productCategory);
			inventoryUpdateOfThisCategory.add((Books) product);
			allProducts.replace(productCategory, inventoryUpdateOfThisCategory);
		}
		else
		{
			List<Books> inventoryUpdateOfThisCategory = new ArrayList<Books>();
			inventoryUpdateOfThisCategory.add((Books) product);
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

	public Map<String, List<Books>> getAllProducts() {
		return allProducts;
	}

}
