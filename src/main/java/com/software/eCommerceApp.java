package com.software;

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
	
	
    public static void main( String[] args )
    {
    	repository = new Repository();
    	
        
    }
    private static void addItemForCategory() {
    	// for loop for adding items
    	for (int i = 0;i < ITEMS_PER_CATEGORY;i++)
    	{
    		
    	}
    }
}
