package com.software;

import java.util.Scanner;

import com.software.eCommerce.services.Product;
import com.software.eCommerce.services.RepositoryMaintenance;
import com.software.eCommerce.services.RepositoryMaintenanceImpl;

/**
 * Hello world!
 *
 */

public class eCommerceApp 
{
	private static final int ITEMS_PER_CATEGORY=20;
	private static Product product = null;
	private static RepositoryMaintenance repository = null;
	
	
    public static void main( String[] args )
    {
    	repository = new RepositoryMaintenanceImpl();
        
        /*switch(choice){
        case 1: // make function call to search for book
        		break;
        case 2: // purchase order
        		break;
        case 3: // print all the details of books available
        		break;
         default: System.out.println("Invalid choice");	
        }*/
    }
    private static void addItemForCategory() {
    	// for loop for adding items
    	for (int i = 0;i < ITEMS_PER_CATEGORY;i++)
    	{
    		
    	}
    }
}
