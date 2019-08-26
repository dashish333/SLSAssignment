/**
 * 
 */
package com.software.eCommerce.services;

import java.util.List;
import java.util.Map;

/**
 * @author ashish
 *
 */
public interface RepositoryMaintenance {
	void addProduct(Product product, String productCategory);
	boolean searchProduct(Category category,String itemName,Map<String,List<Product>> allProducts);
	
}
