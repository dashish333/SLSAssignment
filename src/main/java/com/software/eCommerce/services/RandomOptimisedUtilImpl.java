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
		List<Book> books;
		double start = timeTracker.currentTime();
		createBookTreeMap(searchBy, allProducts);
		double end = timeTracker.currentTime();
		System.out.printf("\nTime To create optimum data structure : %.3f(msec.) based on %s\n",
				(end-start),searchBy.name());
		System.out.println("Searching..... \""+searchString+"\"");
		start = timeTracker.currentTime();
		try{
			books =    new ArrayList<Book>(bookTreeMap.get(searchString));
			}
		catch(NullPointerException ex){
			books = new ArrayList<Book>();
		}
		end = timeTracker.currentTime();
		bui.printSearchTime(end-start);
		System.out.println("Following items matched your search");
		if(books.size()>0) {
			bui.showFewRecords(books);}
		else
		{
			System.out.println("0 items matched your search");
		}
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
		System.out.println("Listing All Product");
		bookTreeMap = setBookTreeMap(orderBy);
		double start = timeTracker.currentTime();
		createBookTreeMap(searchBy, allProducts);
		double end = timeTracker.currentTime();
		System.out.printf("Constructed bookTreeMap = %.2f(msec.) based on %s and sorted in %s order\n",
				(end-start),searchBy.name(),orderBy.name());
		int numberOfRecordsToSee = 10;
		int moveNext = 1;
		int movePrevious = 0;
		List<List<Book>> currentWindow = new ArrayList<List<Book>>();
		List<Book>allBookInPage = new ArrayList<Book>();
		List<List<Book> >values = new ArrayList<List<Book>>(bookTreeMap.values()) ;
		int totalValues = values.size();
		int totalRecordsInCurrentWindow=0;
		
		int lastWindowSize = 0; // set by previous or next
		for (ListIterator<List<Book>>iter = values.listIterator();; ) {
			//System.out.println("CurrentWindowSize"+currentWindow.size());
			totalRecordsInCurrentWindow=0;
			if(moveNext == 1 && movePrevious == 0)
			{
				
				//System.out.println("earlier size = "+size+" inside forward , windowSize = "+lastWindowSize);	
				while(lastWindowSize > 0) { 
						if(!iter.hasNext())
							break;
						iter.next();
						lastWindowSize--;
						totalValues--;
					}
				//System.out.println(size+"-1-"+iter.hasNext());
				//(numberOfRecordsToSee - currentWindow.size())>0 &&
				while(iter.hasNext() && totalRecordsInCurrentWindow < numberOfRecordsToSee) {
					List <Book> books = iter.next();
					int index = Math.min(books.size(),numberOfRecordsToSee-totalRecordsInCurrentWindow);
					totalRecordsInCurrentWindow +=index; 
					currentWindow .add(books.subList(0, index));
					totalValues--;
					lastWindowSize++;
					if(currentWindow.size()==10) {break;}
					
				}
				//System.out.println(size+" = size-1->"+"cWs - "+currentWindow.size());
			}
			else if (moveNext == 0 && movePrevious == 1)
			{
				//System.out.println("earlier size = "+size+" inside backward, window size =  "+lastWindowSize);
					while(lastWindowSize > 0) { 
						if(!iter.hasPrevious())
							break;
						iter.previous();
						lastWindowSize--;
						totalValues++;
					}
					
					//System.out.println(size+"-2-"+iter.hasPrevious());
				//(numberOfRecordsToSee - currentWindow.size())>0 &&
				while( iter.hasPrevious() && totalRecordsInCurrentWindow < numberOfRecordsToSee) {
					List <Book> books = iter.previous();
					int index = Math.min(books.size(),numberOfRecordsToSee-totalRecordsInCurrentWindow);
					totalRecordsInCurrentWindow +=index;
					currentWindow .add(books.subList(0, index));
					totalValues++;
					lastWindowSize++;
					if(currentWindow.size()==10) {break;}
				}
				//Collections.reverse(currentWindow);	
				//System.out.println(size+" = size -2->"+"cWs - "+currentWindow.size());
			}
			//System.out.println(size+" = size -->"+"after cWs - "+currentWindow.size());
			allBookInPage = getCurrentWindow(moveNext, movePrevious, currentWindow);
			int choice = bui.pagination(allBookInPage);
			//System.out.println("choice - "+choice);
			if(choice == 1) {
				//System.out.println("chosen 1");
				if(moveNext == 0) {//System.out.println("Inside choice 1");
					moveNext=1; movePrevious=0;}
				else {lastWindowSize=0;}
			}
			else if (choice == 2) {
				//System.out.println("chosen 2");
				if(movePrevious == 0) {moveNext=0;movePrevious=1;}
				else {lastWindowSize=0;}
			}
			else if(choice != 1 || choice!=2) {return;}
			currentWindow.clear();
			allBookInPage.clear();
		    }
	}
	private List<Book> getCurrentWindow(int moveNext, int movePrevious, List<List<Book>> currentWindow) {
		List<Book> result = new ArrayList<Book>();
		if(movePrevious == 1) {
		for (List<Book> list : currentWindow) {
			 Collections.reverse(list);
			 result.addAll(list);
		}
		Collections.reverse(result);
		return result;
		}
		for (List<Book> list : currentWindow) {
			 result.addAll(list);
		}
		return result;
	}

	private List<Book> updateList(List<Book>allBooks,Book book){
		List<Book> searchByMatchedBooks = new ArrayList<Book>(allBooks);
		searchByMatchedBooks.add(book);
		return searchByMatchedBooks;
		
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
		//System.out.println("Size-"+allBooks.size());
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
	}
	public TreeMap<String, List<Book>> getTreeMap(){
		return bookTreeMap;	
	}
	
}
