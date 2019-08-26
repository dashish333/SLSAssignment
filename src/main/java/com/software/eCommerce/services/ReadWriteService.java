package com.software.eCommerce.services;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.software.eCommerce.util.ReadWrite;

public class ReadWriteService implements ReadWrite {
	
	private static final String FILE_PATH = "/home/ashish/eclipse-workspaces/assignment/books.txt";
	
	public void fileReader(String filepath) 
	{
	FileInputStream fileInputStream=null;
	try {
		fileInputStream = new FileInputStream("/home/ashish/eclipse-workspaces/assignment/books.txt");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	DataInputStream datainputStream = new DataInputStream(fileInputStream);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(datainputStream));
    String strLine;
    try {
		while ((strLine = bufferedReader.readLine()) != null)   
		{
				String[] tokens = strLine.split(" ");
				// map the product
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
}