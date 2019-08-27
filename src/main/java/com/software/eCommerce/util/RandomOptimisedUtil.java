package com.software.eCommerce.util;

import java.util.List;
import java.util.Map;

import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.services.Category;
import com.software.eCommerce.services.OrderBy;
import com.software.eCommerce.services.SearchBookOn;

public interface RandomOptimisedUtil {
	
	void speedSearch(String searchString, String searchBy, String orderBy,Map <String, List<Book>> allProdcuts);

			
}