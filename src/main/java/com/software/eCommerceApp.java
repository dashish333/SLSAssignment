package com.software;

import java.io.IOException;
import java.util.List;

import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.services.FileLoad;
import com.software.eCommerce.services.RandomOptimisedUtilImpl;
import com.software.eCommerce.services.RepositoryMaintenanceImpl;
import com.software.eCommerce.services.TimeTrackingUtilImpl;
import com.software.eCommerce.services.YourCart;
import com.software.eCommerce.util.Cart;
import com.software.eCommerce.util.Category;
import com.software.eCommerce.util.OrderBy;
import com.software.eCommerce.util.RandomDataGenerator;
import com.software.eCommerce.util.RepositoryMaintenance;
import com.software.eCommerce.util.SearchBookOn;
import com.software.eCommerce.util.BindingType;

/**
 * 
 * Hello world!
 *
 */

public class eCommerceApp 
{
	//private static RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
	private static final RepositoryMaintenance repository = new RepositoryMaintenanceImpl();
	private static final Cart yourCart = new YourCart();
    public static void main( String[] args) throws IOException, InterruptedException
    {
    	boolean infiniteLoop = true;
    	//randomDataGenerator.createCsvFileWithRandomValues();
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
        		repository.searchProduct(Category.BOOK, itemName);
        		System.out.println("\n\nFaster Search");
        		sortByKey = optimisedSearch.searchForItem(repository.getAllProducts());
        		break;
        		
        case 2: //System.out.println("Listing All Books");
        		//repository.printAllProducts();
        		System.out.println("\n\n Optimised and Sorted Listing");
        		sortByKey = bui.optimiseSortSearch();
        		System.out.printf("%d",sortByKey);
        		if(sortByKey!=1 && sortByKey!=2 &&sortByKey!=3) {System.out.println("Invalid Input!!");break;}
        		OrderBy orderBy =bui.sortedList(sortByKey);
        		SearchBookOn searchBookOn = bui.getSearchBookOn(sortByKey);
        		optimisedSearch.ListAllProducts(searchBookOn,orderBy,repository.getAllProducts());
        		break;
 
        case 3: System.out.println("Enter book title to buy: "); 
        		String buyItemTitled = bui.getStringInput();
        		List<Book> books = repository.getProduct(Category.BOOK, buyItemTitled); 
        		if(books != null) {}
        		else{
        			Book selectedBook = bui.showMultipleChoices(books);
        			System.out.println("Enter Quantity to buy--");
            		int qty = bui.getIntInput();
            		yourCart.addItemToCart(selectedBook, qty);
        		}
        		
        		
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
