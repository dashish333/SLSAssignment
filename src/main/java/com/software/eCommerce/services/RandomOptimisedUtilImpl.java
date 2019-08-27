/**
 * 
 */
package com.software.eCommerce.services;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.software.eCommerce.UI.BasicUserInterface;
import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.util.RandomOptimisedUtil;

/**
 * 
 * 
 * @author ashish
 *
 */
public class RandomOptimisedUtilImpl implements RandomOptimisedUtil {
	private static TreeMap<String, Book> tree_map = new TreeMap<String, Book>(); 
	private static String sortedBy = null;
	private BasicUserInterface bui = new BasicUserInterface();
	
	public void speedSearch(String searchString, String searchBy, String orderBy,
			Map<String, List<Book> > allProdcuts) {
		
		if(tree_map.size() == 0) {
			createTreeMap(searchBy, orderBy, allProdcuts);
		}
		System.out.println("Searching.....");
		
		Book book = tree_map.get(searchString);
		bui.printItem(Category.BOOK,book.getTitle(), book.getAuthor(), 
				book.getPrice());
		System.out.println("Tree Map Size = "+tree_map.size());
		
		}
	
	private void createTreeMap(String searchBy, 
			String orderBy, Map<String, List<Book>> allProdcuts) {
			List<Book>allBooks =  allProdcuts.get(Category.BOOK.name());
		for (Book book : allBooks) {
			if(searchBy.equals("title"))
				{tree_map.put(book.getTitle(), book);}
			if(searchBy.equals("author")) {
				tree_map.put(book.getAuthor(), book);}
			if(searchBy.equals("Year")) {
				tree_map.put(String.valueOf(book.getYear()), book);
			}	
	}
	
	
	}
	
	public TreeMap<String, Book> getTreeMap(){
		return tree_map;	
	}
	
	public void searchForItem(int sortByKey,Map<String, List<Book>> allProducts) {
		if (sortByKey == 1) {
		System.out.print("Enter the title of book:");
			String title =  bui.getStringInput();
			System.out.println(title);
			speedSearch(title, SearchBookOn.title.name(), OrderBy.ASC.name(), allProducts);
			
		}
		if (sortByKey == 2) {
			System.out.print("Enter author name:");
			String author = bui.getStringInput();
			speedSearch(author, SearchBookOn.author.name(), OrderBy.ASC.name(), allProducts);
			
		}
		if (sortByKey == 3) {
			System.out.print("year");
			System.out.print("Enter year");
			String year = bui.getStringInput();
			speedSearch(year, SearchBookOn.year.name(), OrderBy.ASC.name(), allProducts);
			
		}
	}
}
