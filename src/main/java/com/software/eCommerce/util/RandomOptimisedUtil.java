package com.software.eCommerce.util;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.util.Category;
import com.software.eCommerce.util.OrderBy;
import com.software.eCommerce.util.SearchBookOn;

public interface RandomOptimisedUtil {
	
	void speedSearch(TreeMap<String, List<Book>> bookTreeMap,String searchString);

			
}