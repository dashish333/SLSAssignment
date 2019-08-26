package com.software;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.services.Cart;
import com.software.eCommerce.services.Category;
import com.software.eCommerce.services.FileReaderImpl;
import com.software.eCommerce.services.Product;
import com.software.eCommerce.services.RepositoryMaintenance;
import com.software.eCommerce.services.RepositoryMaintenanceImpl;
import com.software.eCommerce.services.YourCart;

/**
 * Hello world!
 *
 */

public class eCommerceApp 
{
	private static final int ITEMS_PER_CATEGORY=20;
	private static Product product = null;
	private static final RepositoryMaintenance repository = new RepositoryMaintenanceImpl();
	private static final Cart yourCart = new YourCart();
    public static void main( String[] args )
    {
    	addItemForCategory();
    	BasicUserInterface bui = new BasicUserInterface();
    	switch(bui.selectItems()){
        case 1: String itemName = bui.getStringInput();
        		repository.searchProduct(Category.BOOK, itemName);
        		break;
        case 2: repository.getAllProducts();
        		break;
        case 3: System.out.println("Enter book title to buy: "); 
        		String buyItemTitled = bui.getStringInput();
        		Product product = repository.getProduct(Category.BOOK, buyItemTitled);
        		yourCart.addItemToCart(product);
        		break;
        case 4: yourCart.viewCart();	
        default: System.out.println("Invalid choice");	
        }
    }
    private static void addItemForCategory() {
    	String rowRecord;
    	BufferedReader reader = new FileReaderImpl().fileReader();
    	try {
    		while ((rowRecord = reader.readLine()) != null)   
    		{
    				String[] tokens = rowRecord.split(",");
    				Product product = new Book(tokens[0], tokens[1], tokens[2], tokens[3], 
    						tokens[4], tokens[5], Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]));
    				repository.addProduct(product, Category.BOOK.name());
    				
    		}
    		reader.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }
}
