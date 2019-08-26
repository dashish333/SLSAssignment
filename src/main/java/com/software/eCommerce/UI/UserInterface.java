/**
 * 
 */
package com.software.eCommerce.UI;

/**
 * @author ashish
 *
 */
public interface UserInterface {
	
	void userInstructions();
	int getIntInput();
	String getStringInput();
	void displayWelcomeInformation();
	void printItem(String itemName,String additionalDetail ,int price);
	int selectItems();
}
