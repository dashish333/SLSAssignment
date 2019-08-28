/**
 * 
 */
package com.software.eCommerce.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
	private static TreeMap<String, List<Book>> tree_map = null;
	private static String sortedBy = null;
	private BasicUserInterface bui = new BasicUserInterface();
	
	
	public void speedSearch(String searchString, String searchBy, String orderBy,
			Map<String, List<Book> > allProducts) {
			tree_map = setTree_map(orderBy);
			createTreeMap(searchBy, orderBy, allProducts);
		System.out.println("Searching.....");
		List<Book> books = tree_map.get(searchString);
		System.out.println("Following items matched your search");
		for(Book book : books) {
		bui.printItem(Category.BOOK,book.getTitle(), book.getAuthor(), 
				book.getPrice());
		}
		System.out.println("Tree Map Size = "+tree_map.size());
		}

	public TreeMap<String, List<Book>> getTreeMap(){
		return tree_map;	
	}
	
	public void searchForItem(Map<String, List<Book>> allProducts) {
		int sortByKey = bui.optimiseSortSearch();
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
	public void ListAllProducts(String searchBy,String orderBy ,Map<String, List<Book>> allProducts) {
		tree_map = setTree_map(orderBy);
		createTreeMap(searchBy, orderBy, allProducts);

		Set<String> keySet = allProducts.keySet();
		for(String key : keySet) {
            List<Book> books = new ArrayList<Book>(allProducts.get(key));
            for(Book book : books) 
            {
            	bui.printItem(book.getProductCategory(), book.getTitle(), book.getAuthor(), book.getPrice());
            }
        }
	}
	
	private List<Book> updateList(List<Book>allBooks,Book book){
		List<Book> serachByMatchedBooks = new ArrayList<Book>(allBooks);
		serachByMatchedBooks.add(book);
		return serachByMatchedBooks;
		
	}
	
	private TreeMap<String, List<Book>>  setTree_map(String orderBy)
	{
		if(orderBy.equals(OrderBy.DESC.name())) {return new TreeMap<String, List<Book>>(Collections.reverseOrder());}
		return new TreeMap<String, List<Book>>(Collections.reverseOrder());
		
	}
	
	private void createTreeMap(String searchBy, String orderBy, Map<String, List<Book>> allProdcuts) {
		List<Book>allBooks =  allProdcuts.get(Category.BOOK.name());
		if(searchBy.equals("title"))
			{ for (Book book:allBooks) 
				{
					if(tree_map.containsKey(book.getTitle())) {
						List<Book> currentBooks = tree_map.get(book.getTitle());
						tree_map.replace(book.getTitle(), updateList(currentBooks, book));
					}
					else {
						List<Book> newBook = new ArrayList<Book>();
						tree_map.put(book.getTitle(), updateList(newBook, book));
					}
				}
			}
		if(searchBy.equals("author")) {
			for (Book book:allBooks) 
			{
				if(tree_map.containsKey(book.getAuthor())) {
					List<Book> currentBooks = tree_map.get(book.getAuthor());
					tree_map.replace(book.getAuthor(), updateList(currentBooks, book));
				}
				else {
					List<Book> newBook = new ArrayList<Book>();
					tree_map.put(book.getAuthor(), updateList(newBook, book));
				}
			}
			}
		if(searchBy.equals("Year")) {
			for (Book book:allBooks) 
			{
				if(tree_map.containsKey(String.valueOf(book.getYear()))) {
					List<Book> currentBooks = tree_map.get(book.getYear());
					tree_map.replace(String.valueOf(book.getYear()), updateList(currentBooks, book));
				}
				else {
					List<Book> newBook = new ArrayList<Book>();
					tree_map.put(String.valueOf(book.getYear()), updateList(newBook, book));
				}
			}
		}
	}
}
