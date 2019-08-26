/**
 * 
 */
package com.software.eCommerce.services;

/**
 * @author ashish
 *
 */
public interface RepositoryMaintenance {
	void addProduct(Product product, String productCategory);
	void searchProduct(String productName);

}
