package com.software;

import java.util.Scanner;

import com.software.eCommerce.dao.Repository;
import com.software.eCommerce.services.Product;

/**
 * Hello world!
 *
 */

public class eCommerceApp 
{
	private static final int ITEMS_PER_CATEGORY=20;
	private static Product product = null;
	private static Repository repository = null;
	private static final Scanner input = new Scanner(System.in);
	
    public static void main( String[] args )
    {
    	repository = new Repository();
    	
        System.out.println("!!Welcome!!");
        System.out.println("Choose correspoding number for your choice");
        System.out.println("1. Search for Book");
        System.out.println("2. Buy a Book");
        System.out.println("3: List all the books available");
        int choice = input.nextInt();
        switch(choice){
        case 1: // make function call to search for book
        		break;
        case 2: // purchase order
        		break;
        case 3: // print all the details of books available
        }
    }
    private static void addItemForCategory() {
    	// for loop for adding items
    	for (int i = 0;i < ITEMS_PER_CATEGORY;i++)
    	{
    		
    	}
    }
}
