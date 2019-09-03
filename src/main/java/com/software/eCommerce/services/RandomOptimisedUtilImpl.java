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
import com.software.eCommerce.util.Category;
import com.software.eCommerce.util.OrderBy;
import com.software.eCommerce.util.RandomOptimisedUtil;
import com.software.eCommerce.util.SearchBookOn;

/**
 * 
 * 
 * @author ashish
 *
 */
public class RandomOptimisedUtilImpl implements RandomOptimisedUtil {
	
	private static TreeMap<String, List<Book>> bookTreeMap = null;
	private static String sortedBy = null;
	private BasicUserInterface bui = new BasicUserInterface();
		
	public void speedSearch(String searchString, SearchBookOn searchBy,
			Map<Category, List<Book> > allProducts) {
			if(bookTreeMap == null || bookTreeMap.size()==0 ) {
			bookTreeMap = setBookTreeMap(OrderBy.ASC);
			}
			createBookTreeMap(searchBy, allProducts);
		System.out.println("Searching.....");
		List<Book> books = bookTreeMap.get(searchString);
		System.out.println("Following items matched your search");
		bui.showFewRecords(books,Category.BOOK.name());
		}

	public TreeMap<String, List<Book>> getTreeMap(){
		return bookTreeMap;	
	}
	
	public int searchForItem(Map<Category, List<Book>> allProducts) {
		int sortByKey = bui.optimiseSortSearch();
		if (sortByKey == 1) {
		System.out.print("Enter the title of book:");
			String title =  bui.getStringInput();
			System.out.println(title);
			speedSearch(title, SearchBookOn.title, allProducts);
		}
		if (sortByKey == 3) {
			System.out.print("Enter author name:");
			String author = bui.getStringInput();
			speedSearch(author, SearchBookOn.author, allProducts);
			
		}
		if (sortByKey == 2) {
			System.out.print("Enter year");
			String year = bui.getStringInput();
			speedSearch(year, SearchBookOn.year, allProducts);
			
		}
		return sortByKey;
	}
	public void ListAllProducts(SearchBookOn searchBy,OrderBy orderBy ,Map<Category, List<Book> > allProducts) {	
		bookTreeMap = setBookTreeMap(orderBy);
		createBookTreeMap(searchBy, allProducts);
		//System.out.println("book tree map size"+bookTreeMap.size());
		Set<String> keySet = bookTreeMap.keySet();
		int numberOfRecordsToSee = 10;
		for(String key : keySet) {
            List<Book> books = new ArrayList<Book>(bookTreeMap.get(key));
            boolean toShowFurtherRecords = bui.showFewRecords(books,key);
           // System.out.println("show further records = "+showFurtherRecords);
            numberOfRecordsToSee--;
			if(numberOfRecordsToSee == 0 || toShowFurtherRecords == false) 
			{
				System.out.printf("\n ##Press 1 to see next records for-{%s} ##",searchBy.name());
				int choice = bui.getIntInput();
				if(choice == 1) {numberOfRecordsToSee=10;}
				else {return;}
			}
        }
	}
	
	private List<Book> updateList(List<Book>allBooks,Book book){
		List<Book> serachByMatchedBooks = new ArrayList<Book>(allBooks);
		serachByMatchedBooks.add(book);
		return serachByMatchedBooks;
		
	}
	
	private TreeMap<String, List<Book>>  setBookTreeMap(OrderBy orderBy)
	{
		if(orderBy.name().equals("DESC")) {//System.out.println("setting desc");
			return new TreeMap<String, List<Book>>(Collections.reverseOrder());}
		return new TreeMap<String, List<Book>>();
		
	}
	private void createBookTreeMap(SearchBookOn searchBy, Map<Category, List<Book>> allProdcuts) {
		List<Book>allBooks =  allProdcuts.get(Category.BOOK);
		bookTreeMap.clear();
		System.out.println("Size-"+allBooks.size());
		if(searchBy.name().equals("title"))
			{ for (Book book:allBooks) 
				{
					if(bookTreeMap.containsKey(book.getTitle())) {
						List<Book> currentBooks = bookTreeMap.get(book.getTitle());
						bookTreeMap.replace(book.getTitle(), updateList(currentBooks, book));
					}
					else {
						List<Book> newBook = new ArrayList<Book>();
						bookTreeMap.put(book.getTitle(), updateList(newBook, book));
					}
				}
			}
		if(searchBy.name().equals("author")) {
			for (Book book:allBooks) 
			{
				if(bookTreeMap.containsKey(book.getAuthor())) {
					List<Book> currentBooks = bookTreeMap.get(book.getAuthor());
					bookTreeMap.replace(book.getAuthor(), updateList(currentBooks, book));
				}
				else {
					List<Book> newBook = new ArrayList<Book>();
					bookTreeMap.put(book.getAuthor(), updateList(newBook, book));
				}
			}
			}
		if(searchBy.name().equals("year")) {
			for (Book book:allBooks) 
			{
				if(bookTreeMap.containsKey(String.valueOf(book.getYear()))) {
					List<Book> currentBooks = bookTreeMap.get(String.valueOf(book.getYear()));
					bookTreeMap.replace(String.valueOf(book.getYear()), updateList(currentBooks, book));
				}
				else {
					List<Book> newBook = new ArrayList<Book>();
					bookTreeMap.put(String.valueOf(book.getYear()), updateList(newBook, book));
				}
			}
		}
		//System.out.println("- treeMap size"+bookTreeMap.size());
	}
}
