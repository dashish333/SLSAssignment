package com.software;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.services.FileReaderImpl;
import com.software.eCommerce.services.RandomOptimisedUtilImpl;
import com.software.eCommerce.services.RepositoryMaintenanceImpl;
import com.software.eCommerce.services.TimeTrackingUtilImpl;
import com.software.eCommerce.services.YourCart;
import com.software.eCommerce.util.BindingType;
import com.software.eCommerce.util.Cart;
import com.software.eCommerce.util.Category;
import com.software.eCommerce.util.OrderBy;
import com.software.eCommerce.util.RandomDataGenerator;
import com.software.eCommerce.services.FileLoad;
import com.software.eCommerce.util.RepositoryMaintenance;
import com.software.eCommerce.util.SearchBookOn;

/**
 * Hello world!
 *
 */


public class eCommerceApp 
{
	private static RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
	private static final RepositoryMaintenance repository = new RepositoryMaintenanceImpl();
	private static final Cart yourCart = new YourCart();
	private static final  TimeTrackingUtilImpl timeTracker = new TimeTrackingUtilImpl();
	private static final RandomOptimisedUtilImpl optimisedSearch = new RandomOptimisedUtilImpl();
    public static void main( String[] args ) throws IOException
    {
    	boolean infiniteLoop = true;
    	randomDataGenerator.createCsvFileWithRandomValues();
    	addItemForCategory();
    	BasicUserInterface bui = new BasicUserInterface();
    	int sortByKey;
    	RandomOptimisedUtilImpl optimisedSearch = new RandomOptimisedUtilImpl();
    	
    	while(infiniteLoop) {
    		System.out.println("\n");
    	switch(bui.selectItems()){
    	
        case 1: 
        		System.out.println("Searching for Book - ");
        		String itemName = bui.getStringInput();
        		long begin = timeTracker.currentTime();
        		repository.searchProduct(Category.BOOK, itemName);
        		long end = timeTracker.currentTime();
        		System.out.println("\n Time in Search Taken = "+(end - begin));
        		System.out.println("\n\nFaster Search");
        		begin = timeTracker.currentTime();
        		sortByKey = optimisedSearch.searchForItem(repository.getAllProducts());
        		end = timeTracker.currentTime();
        		System.out.println("Result Returned in (milli sec.) = "+(end - begin));
        		break;
        		
        case 2: System.out.println("Listing All Books");
        		repository.printAllProducts();
        		System.out.println("\n\nSorted Listing");
        		begin = timeTracker.currentTime();
        		
        		// insert a UI: asking for user sorting criteria 
        		sortByKey = bui.optimiseSortSearch();
        		OrderBy orderBy =bui.sortedList(sortByKey);
        		SearchBookOn searchBookOn = bui.getSearchBookOn(sortByKey);
        		optimisedSearch.ListAllProducts(searchBookOn,orderBy,repository.getAllProducts());
        		end = timeTracker.currentTime();
        		System.out.println("Result Returned in (milli sec.) = "+(end - begin));
        		break;
        		
        case 3: System.out.println("Enter book title to buy: "); 
        		String buyItemTitled = bui.getStringInput();
        		List<Book> books = repository.getProduct(Category.BOOK, buyItemTitled); 
        		yourCart.addItemToCart(books);
        		
        case 4: yourCart.viewCart();
        		break;
        default: System.out.println("Invalid choice. Exiting!");
        		infiniteLoop = false;
        }
    }
    }
    private static void addItemForCategory() {
    	String rowRecord;
    	FileLoad fileLoader = new FileLoad();
    	List<String[]> records = fileLoader.loadFile();
    	for (int i=0;i<records.size();i++)
    		
    	{Book book = new Book(records.get(i)[0], records.get(i)[1], records.get(i)[2], records.get(i)[3], 
    			records.get(i)[4], BindingType.valueOf(records.get(i)[5]),
    			Integer.parseInt(records.get(i)[6]), Integer.parseInt(records.get(i)[7]));
    	repository.addProduct(book, Category.BOOK);
    	}
    }
}
