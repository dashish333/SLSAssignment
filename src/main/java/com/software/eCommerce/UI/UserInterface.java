/**
 * 
 */
package com.software.eCommerce.UI;

import java.util.HashMap;
import java.util.List;

import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.util.Category;

/**
 * @author ashish
 *
 */
public interface UserInterface {
	
	void userInstructions();
	int getIntInput();
	String getStringInput();
	void displayWelcomeInformation();
	void printItem(Category category, String itemName,String additionalDetail ,int price, int year);
	int selectItems();
	int optimiseSortSearch();
	void showFewRecords(List<Book> keyBookMapping);
	void showCart(HashMap<Book, Integer> yourCart);
}
