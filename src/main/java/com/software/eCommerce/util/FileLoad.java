package com.software.eCommerce.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.services.Category;
import com.software.eCommerce.services.FileReaderImpl;
import com.software.eCommerce.services.Product;

public class FileLoad extends FileReaderImpl {
	private static final String BOOK_FILE_PATH = "books.txt"; 
	
	
	public List<String[]> loadFile() {
		String rowRecord;
		List<String[]> records=null;
		BufferedReader reader = fileReader(BOOK_FILE_PATH);
		try {
    		while ((rowRecord = reader.readLine()) != null)   
    		{
    				String[] tokens = rowRecord.split(",");

    				records.add(tokens);
    				
    		}
    		reader.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		
		return records;
	}

}
