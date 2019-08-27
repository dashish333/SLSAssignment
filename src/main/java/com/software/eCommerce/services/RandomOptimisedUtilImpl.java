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
 * @author ashish
 *
 */
public class RandomOptimisedUtilImpl implements RandomOptimisedUtil {
	private static TreeMap<String, Book> tree_map = new TreeMap<String, Book>(); 
	
	public List<String> speedSearch(String itemName,Category categoryName, SearchBookOn searchBy, OrderBy orderBy,
			Map<String, List<Book>> allProdcuts) {
		
		if(tree_map.size() == 0) {
			createTreeMap(categoryName, searchBy, orderBy, allProdcuts);
		}
		System.out.println("Searching.....");
		BasicUserInterface bui = new BasicUserInterface();
		Book book = tree_map.get(itemName);
		bui.printItem(Category.BOOK,book.getTitle(), book.getAuthor(), 
				book.getPrice());
		return null;
		
		
			
		}
	
	private void createTreeMap(Category categoryName, SearchBookOn searchBy, 
			OrderBy orderBy,Map<String, List<Book>> allProdcuts) {
		
			List<Book>allBooks =  allProdcuts.get(categoryName.name());
		
		
		for (Book book : allBooks) {
			
			if(searchBy.name() == "title")
				{tree_map.put(book.getTitle(), book);}
			if(searchBy.name() == "author") {
				tree_map.put(book.getAuthor(), book);}
			if(searchBy.name() == "year") {
				tree_map.put(String.valueOf(book.getYear()), book);
			}	
	}
	
	
	}
	
	public TreeMap<String, Book> getTreeMap(){
		return tree_map;
		
	}
}
