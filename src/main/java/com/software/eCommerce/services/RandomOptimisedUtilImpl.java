/**
 * 
 */
package com.software.eCommerce.services;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.util.RandomOptimisedUtil;

/**
 * @author ashish
 *
 */
public class RandomOptimisedUtilImpl implements RandomOptimisedUtil {
	private static TreeMap<String, Book> tree_map = new TreeMap<String, Book>(); 
	
	public void speedSearch(Category categoryName, SearchBookOn searchBy, OrderBy orderBy,
			Map<String, List<Book>> allProdcuts) {
		
		List<Book>allBooks =  allProdcuts.get(Category.BOOK.name());
		
		for (Book book : allBooks) {
			
		}
		
	}
	
	public TreeMap<String, Book> getTreeMap(){
		return tree_map;
		
	}
	
	

}
