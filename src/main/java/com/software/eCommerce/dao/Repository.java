/**
 * 
 */
package com.software.eCommerce.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.software.eCommerce.services.Product;
import com.software.eCommerce.services.RepositoryMaintenance;

/**
 * @author ashish
 *
 */

public class Repository implements RepositoryMaintenance {
	private Map<String, List<Product> > allProducts;

	public void addProduct(Product product, String productCategory) {
		if(allProducts.containsKey(productCategory)) {
			List<Product> inventoryUpdateOfThisCategory = allProducts.get(productCategory);
			inventoryUpdateOfThisCategory.add(product);
			allProducts.replace(productCategory, inventoryUpdateOfThisCategory);
		}
		else
		{
			List<Product> inventoryUpdateOfThisCategory = new ArrayList<Product>();
			inventoryUpdateOfThisCategory.add(product);
			allProducts.put(productCategory, inventoryUpdateOfThisCategory);
		}

	}

	public void searchProduct(String productName) {
		

	}

}
