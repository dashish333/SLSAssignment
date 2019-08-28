/**
 * 
 */
package com.software.eCommerce.UI;

import com.software.eCommerce.services.Category;

/**
 * @author ashish
 *
 */
public interface UserInterface {
	
	void userInstructions();
	int getIntInput();
	String getStringInput();
	void displayWelcomeInformation();
	void printItem(Category category, String itemName,String additionalDetail ,int price);
	int selectItems();
	int optimiseSortSearch();
}
