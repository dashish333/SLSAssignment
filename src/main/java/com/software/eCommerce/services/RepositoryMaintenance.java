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
	boolean searchProduct(String productCategory,String itemName,Map<String,List<Product>> allProducts);
	
}
