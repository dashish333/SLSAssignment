package com.software;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.services.FileLoad;
import com.software.eCommerce.services.RandomOptimisedUtilImpl;
import com.software.eCommerce.services.RepositoryMaintenanceImpl;
import com.software.eCommerce.services.YourCart;
import com.software.eCommerce.util.Cart;
import com.software.eCommerce.util.Category;
import com.software.eCommerce.util.OrderBy;
import com.software.eCommerce.util.RandomOptimisedUtil;
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
	private static final RandomOptimisedUtilImpl optimisedSearch = new RandomOptimisedUtilImpl();
	private static final Cart yourCart = new YourCart();
    private static final BasicUserInterface bui = new BasicUserInterface();
	private static TreeMap<String, List<Book>> bookTitleTreeMap;
	private static TreeMap<String, List<Book>> bookAuthorTreeMap;
	private static TreeMap<String,List<Book>> bookYearTreeMap;
	
	
	public static void main( String[] args) throws IOException, InterruptedException
    {
		List<? extends Object> treeMapSearchString;
    	boolean infiniteLoop = true;
    	addItemForCategory();
    	buildTreeMap();
    	
    	int sortByKey;
    	//RandomOptimisedUtilImpl optimisedSearch = new RandomOptimisedUtilImpl();
    	
    	while(infiniteLoop) {
    		System.out.println("\n");
    	switch(bui.selectItems()){
        case 1: 
        		System.out.println("Searching for Book - ");
        		String itemName = bui.getStringInput();
        		repository.searchProduct(Category.BOOK, itemName);
        		System.out.println("\n\nFaster Search");
        		
        		sortByKey = bui.optimiseSortSearch();
        		treeMapSearchString = sendAppropriateTreeMapForSearch(sortByKey, false);
        		optimisedSearch.searchForItem((TreeMap<String, List<Book>>)treeMapSearchString.get(0), (String)treeMapSearchString.get(1));
        		break;
        		
        case 2: bui.printGenericUIContent("\n\n Optimised and Sorted Listing");
        		sortByKey = bui.optimiseSortSearch();
        		//System.out.printf("%d",sortByKey);
        		if(sortByKey!=1 && sortByKey!=2 &&sortByKey!=3) {bui.printGenericUIContent("Invalid Input!!");break;}
        		treeMapSearchString = sendAppropriateTreeMapForSearch(sortByKey, true);
        		OrderBy orderBy =bui.sortedList(sortByKey);
        		optimisedSearch.ListAllProducts((TreeMap<String, List<Book>>)treeMapSearchString.get(0),orderBy);
        		break;
 
        case 3: System.out.println("Enter book title to buy: "); 
        		String buyItemTitled = bui.getStringInput();
        		List<Book> books = repository.getProduct(Category.BOOK, buyItemTitled); 
        		if(books == null) {System.out.println("No Record Found");}
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
    	FileLoad fileLoader = new FileLoad();
    	List<String[]> records = fileLoader.loadFile();
    	for (int i=0;i<records.size();i++)
    		
    	{Book book = new Book(records.get(i)[0], records.get(i)[1], records.get(i)[2], records.get(i)[3], 
    			records.get(i)[4], BindingType.valueOf(records.get(i)[5]),
    			Integer.parseInt(records.get(i)[6]), Integer.parseInt(records.get(i)[7]));
    	repository.addProduct(book, Category.BOOK);
    	}
    }
    
    private static void buildTreeMap() {
    	List<Book> allBooks = repository.getAllProducts().get(Category.BOOK);
    	bookTitleTreeMap = new TreeMap<String, List<Book>>();
    	optimisedSearch.createSpecificTreeMap(bookTitleTreeMap,SearchBookOn.title, allBooks);
    	
    	bookAuthorTreeMap = new TreeMap<String, List<Book>>();
    	optimisedSearch.createSpecificTreeMap(bookAuthorTreeMap,SearchBookOn.author, allBooks);
    	
    	bookYearTreeMap =new TreeMap<String, List<Book>>();
    	optimisedSearch.createSpecificTreeMap(bookYearTreeMap,SearchBookOn.author, allBooks);


    }
    
    private static List<? extends Object> sendAppropriateTreeMapForSearch(int sortByKey, boolean isListingAll) {
    	TreeMap<String, List<Book>> currentTreeMap = null;
    	String titleStatment = "Enter the title of book:\n";
    	String authorStatment = "Enter author name:\n";
    	String yearStatment = "Enter year:\n";
    	String searchString = null;
    	if (sortByKey == 1) {
    		if(isListingAll == false) {bui.printGenericUIContent(titleStatment);searchString = bui.getStringInput();}
    		currentTreeMap = bookTitleTreeMap;    			
    		}
    	else if (sortByKey == 3) {
    		if(isListingAll == false) {bui.printGenericUIContent(authorStatment);searchString = bui.getStringInput();}
    			currentTreeMap = bookAuthorTreeMap;
    			
    		}
    	else if (sortByKey == 2) {
    		if(isListingAll == false) {bui.printGenericUIContent(yearStatment);searchString = bui.getStringInput();}
    			currentTreeMap = bookYearTreeMap;
    			
    		}
    	return Arrays.asList(currentTreeMap, searchString);
    }
}
