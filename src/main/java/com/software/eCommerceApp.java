package com.software;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.services.Cart;
import com.software.eCommerce.services.Category;
import com.software.eCommerce.services.FileReaderImpl;
import com.software.eCommerce.services.Product;
import com.software.eCommerce.services.RandomOptimisedUtilImpl;
import com.software.eCommerce.services.RepositoryMaintenance;
import com.software.eCommerce.services.RepositoryMaintenanceImpl;
import com.software.eCommerce.services.SearchBookOn;
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
    	
        case 1: System.out.println("Searching for Book - ");
        		String itemName = bui.getStringInput();
        		repository.searchProduct(Category.BOOK, itemName);
        		
        		System.out.println("\n\nFaster Search");
        		int sortByKey = bui.optimiseSearch();
        		
        		
        		break;
        		
        case 2: System.out.println("Listing All Books");
        		repository.printAllProducts();
        		break;
        		
        case 3: System.out.println("Enter book title to buy: "); 
        		String buyItemTitled = bui.getStringInput();
        		List<Book> books = repository.getProduct(Category.BOOK, buyItemTitled); 
        		yourCart.addItemToCart(books);
        		
        		
        		// RandomOptimisedUtilImpl optimisedSearch = new RandomOptimisedUtilImpl();
        		// Book book = books.get(0);
        		
        		//optimisedSearch.speedSearch(book.get, Category.BOOK, SearchBookOn., orderBy, allProdcuts)
        case 4: yourCart.viewCart();
        		break;
        default: System.out.println("Invalid choice");	
        }
    }
    private static void addItemForCategory() {
    	String rowRecord;
    	FileLoad fileLoader = new FileLoad();
    	List<String[]> records = fileLoader.loadFile();
    	for (int i=0;i<records.size();i++)
    		
    	{System.out.printf("%s %s\n",records.get(i)[6],records.get(i)[7]);
    		Product product = new Book(records.get(i)[0], records.get(i)[1], records.get(i)[2], records.get(i)[3], 
    			records.get(i)[4], records.get(i)[5],
    			Integer.parseInt(records.get(i)[6]), Integer.parseInt(records.get(i)[7]));
    	repository.addProduct(product, Category.BOOK.name());
    	}
    	
    }
}
