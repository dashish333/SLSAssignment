package com.software.eCommerce.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.software.eCommerce.datamodel.Book;
import com.software.eCommerce.services.Category;
import com.software.eCommerce.services.FileReaderImpl;
import com.software.eCommerce.services.Product;

public class FileLoad extends FileReaderImpl {
	private static final String BOOK_FILE_PATH = "/Users/ashishdwivedi/eclipse-workspace/SLSAssignment/SLSAssignment/book.txt"; 
	private static final String Strand_Book_File_Path = "/home/ashish/git_repo/SLSAssignment/book.txt";
	
	public List<String[]> loadFile() {
		String rowRecord;
		List<String[]> tokens=new ArrayList<String[]>();
		BufferedReader reader = fileReader(Strand_Book_File_Path);
		try {
    		while ((rowRecord = reader.readLine()) != null)   
    		{
    				String[] token = rowRecord.split(",");
    				tokens.add(token);
    				
    		}
    		reader.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		
		return tokens;
	}

}
