package com.software;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
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
import com.software.eCommerce.util.FileLoad;

/**
 * Hello world!
 *
 */

public class eCommerceApp 
{
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
    	FileLoad fileLoader = new FileLoad();
    	List<String[]> records = fileLoader.loadFile();
    	for (int i=0;i<records.size();i++)
    	{Product product = new Book(records.get(i)[0], records.get(i)[1], records.get(i)[2], records.get(i)[3], 
    			records.get(i)[4], records.get(i)[5], Integer.parseInt(records.get(i)[6]), 
    			Integer.parseInt(records.get(i)[7]));
    	repository.addProduct(product, Category.BOOK.name());
    	}
    	
    }
}
