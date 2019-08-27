package com.software.eCommerce.services;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.software.eCommerce.util.FileReader;
public class FileReaderImpl implements FileReader {
	
	public BufferedReader fileReader(String filepath) 
	{
	String[] tokens=null;
	FileInputStream fileInputStream = null;
	try {
		fileInputStream = new FileInputStream(filepath);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	DataInputStream datainputStream = new DataInputStream(fileInputStream);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(datainputStream));  
    return bufferedReader;
    
 }
	
}