/**
 * 
 */
package com.software.eCommerce.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
	private TimeTrackingUtilImpl timeTracker = new TimeTrackingUtilImpl();	
	
	public void speedSearch(String searchString, SearchBookOn searchBy,
			Map<Category, List<Book> > allProducts) {
			if(bookTreeMap == null || bookTreeMap.size()==0 ) {
			bookTreeMap = setBookTreeMap(OrderBy.ASC);
			}
			createBookTreeMap(searchBy, allProducts);
		System.out.println("Searching.....");
		double start = timeTracker.currentTime();
		List<Book> books =    new ArrayList<Book>(bookTreeMap.get(searchString));
		double end = timeTracker.currentTime();
		bui.printSearchTime(end-start);
		System.out.println("Following items matched your search");
		if(books.size()>0) {
			bui.showFewRecords(books);}
		else
		{
			System.out.println("0 items matched your search");
		}
 }
	public TreeMap<String, List<Book>> getTreeMap(){
		return bookTreeMap;	
	}
	
	
	public int searchForItem(Map<Category, List<Book>> allProducts) {
		int sortByKey = bui.optimiseSortSearch();
		if (sortByKey == 1) {
		System.out.print("Enter the title of book:");
			String title =  bui.getStringInput();
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
	public void ListAllProducts(SearchBookOn searchBy,OrderBy orderBy ,Map<Category, List<Book> > allProducts) throws InterruptedException {	
		bookTreeMap = setBookTreeMap(orderBy);
		createBookTreeMap(searchBy, allProducts);
		int numberOfRecordsToSee = 10;
		String seeNext = "1";
		List<List<Book>>values = new ArrayList<List<Book>>(bookTreeMap.values()) ;
		
		int currentPos=1; // 1 forward, 0 backward
		int lastWindowSize = 0;
		for (ListIterator<List<Book>>iter = values.listIterator();; ) {
			List<Book> currentWindow = new ArrayList<Book>();
			if(seeNext.equals("1") && iter.hasNext()) {
				System.out.println("\n Next Record \n");
				if(currentPos == 0)
				{for(int i=0;i<lastWindowSize;i++) {if(iter.hasNext()) {iter.next();}}}
				currentPos = 1;
				while(iter.hasNext() && numberOfRecordsToSee > 0) 
				{
					List <Book> books = iter.next();
					int newDataSize = Math.min(books.size(),10-currentWindow.size());
					currentWindow .addAll(books.subList(0, newDataSize));
					numberOfRecordsToSee -= newDataSize;
			
				}
			}
			
			if(seeNext.equals("2") && iter.hasPrevious())
			{System.out.println("\n Previous Record \n");
				if(iter.hasPrevious()) 
			{		System.out.println("inside");
					if(currentPos == 1)
					{for(int i=0;i<lastWindowSize;i++) {if(iter.hasPrevious()) {iter.previous();}}}
					currentPos = 0;
					System.out.println(numberOfRecordsToSee+"ddkdlsd"+iter.hasPrevious());
					while(iter.hasPrevious() && numberOfRecordsToSee > 0) 
					{List <Book> books = iter.previous();
					int newDataSize = Math.min(books.size(),10-currentWindow.size());
					currentWindow .addAll(books.subList(0, newDataSize));
					numberOfRecordsToSee -= newDataSize;
					System.out.println("hisds-"+currentWindow.size());
					}
					if(currentWindow.size()>0) {
						Collections.reverse(currentWindow);
						}
				}
				System.out.println("hiwhew-"+currentWindow.size());
			}
			if(currentWindow.size() > 0) {bui.showFewRecords(currentWindow);}
			else 
			{
				if(seeNext.equals("1"))
				{System.out.println("No Next Records to Show");}
				else if(seeNext.equals("2")) {System.out.println("No Previous Records to Show");}
				numberOfRecordsToSee = 0;
			}			
			if(numberOfRecordsToSee <= 0)
			{
				System.out.println("Would like to navigate more? Press 1");
				int choice = bui.getIntInput();
				if(choice == 1 ) {
				numberOfRecordsToSee=10;
				System.out.println("Press 1 for next records ");
				System.out.println("Press 2 for previous records");
				seeNext = bui.getStringInput();
				}
				else {System.out.println("Invalid Choice!");
				return;
				}
			}
			lastWindowSize = currentWindow.size();
			System.out.println("windiwse"+lastWindowSize);
			currentWindow.clear();
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
