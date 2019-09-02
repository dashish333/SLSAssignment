package com.software.eCommerce.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDataGenerator {
	
		private static final String[] listOfString = {"about"," above"," across","act",
				"actor"," active"," activity"," add"," afraid"," after"," again"," age","ago",
				"agree"," air"," all"," alone"," along"," already"," always"," am"," amount",
				"an","and","angry","another","answer","any","anyone","anything","anytime","appear","apple"
				,"are","area","arm","army","around","arrive","art","as","ask",
				"at"," attack"," aunt"," autumn"," away",
				"baby","base","back","bad","bag","ball","bank","basket","bath","be","bean","bear",
				"beautiful","beer","bed","bedroom","behave","before","begin","behind","bell",
				"below","besides","best","better","between","big","bird","birth","birthday","bit","bite","black",
				"bleed","block","blood","blow","blue","board","boat","body","boil","bone","book","border","born","borrow",
				"both","bottle","bottom","bowl","box","boy","branch","brave","bread","break","breakfast",
				"breathe","bridge","bright","bring","brother","brown","brush","build","burn","business"
				,"bus","busy","but","buy","by",
				"dance","danger","dangerous","dark","daughter","day","dead","decide","decrease","deep","deer","depend",
				"desk","destroy","develop","die","different","difficult","dinner","direction","dirty",
				"discover","dish","do","dog","door","double","down","draw","dream","dress","drink","drive",
				"drop","dry","duck","dust","duty",
				"face","fact","fail","fall","false","family","famous","far","farm","father","fast","fat","fault","fear","feed",
				"feel","female","fever","few","fight","fill","film","find","fine","finger","finish","fire","first","fit",
				"five","fix","flag","flat","float","floor","flour","flower","fly","fold","food","fool","foot","football",
				"for","force","foreign","forest","forget","forgive","fork","form","fox","four","free","freedom","freeze",
				"fresh","friend","friendly","from","front","fruit","full","fun","funny","furniture","further",
				"future", " queen","question","quick","quiet","quite"};
		
		private static final String[] authors= {"Fyodor Dostoevsky","J.R.R. Tolkien","LeoTolstoy","Ernest Hemingway","Jane Austen","George Orwell","John Steinbeck",
				"Mark Twain","James Joyce","C.S. Lewis","Alexandre Dumas","Edgar Allan Poe","F. Scott Fitzgerald",
				"Oscar Wilde","Kurt Vonnegut","Franz Kafka","J.K. Rowling","William Faulkner","Stephen King",
				"Gabriel Garcia Marquez","J.D. Salinger","Vladimir Nabokov","Homer","Victor Hugo",
				"Charlotte Bronte","Agatha Christie","Ayn Rand"};
	
	    public void createCsvFileWithRandomValues() throws IOException {
	    	File file = new File("/Users/ashishdwivedi/eclipse-workspace/SLSAssignment/SLSAssignment/book1.txt");
	        file.createNewFile();
	        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

	        long startTime = System.currentTimeMillis();

	        for (int j = 0; j < 10; j++) {

	            // For assigning random Strings to first five fields (Title, author, ISBN, Publisher, Language) of Book
	            StringBuilder stringBuilder = new StringBuilder("");

	            stringBuilder.append(pickRandomString());
	            stringBuilder.append(",");
	            stringBuilder.append(pickRandomAuthor());
	            stringBuilder.append(",");
	            for (int i = 2; i < 5; i++) {
	            	
	            	if(i != 2) {stringBuilder.append(generateRandomString());}
	            	else{
	            		stringBuilder.append("isbn-"+generateRandomString());
	            		}
	                
	                
	                stringBuilder.append(",");
	            }
	            stringBuilder.append(getRandomValueForBindingType().name());
	            stringBuilder.append(",");
	            stringBuilder.append(getRandomValueForPublishedYear());
	            stringBuilder.append(",");
	            stringBuilder.append(getRandomValueForPrice());
	            stringBuilder.append("\n");

	            bufferedWriter.write(stringBuilder.toString());
	            bufferedWriter.flush();
	        }

	        bufferedWriter.close();

	        long closeTime = System.currentTimeMillis();

	    }
	    
	    public String pickRandomString() {
	    	
	    	StringBuilder sb = new StringBuilder(10);
	        for (int i = 0; i < 4; i++) {
	            int index = (int)(listOfString.length*Math.random());
	            sb.append(listOfString[index]);
	            if(i<3) {sb.append(" ");}
	        }

	        return sb.toString();
	    }
	    
	    public String pickRandomAuthor() {
	    	
	    	int index = (int)(authors.length*Math.random());
            return authors[index];
	    	
	    }
	    private String generateRandomString() {
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
	        StringBuilder sb = new StringBuilder(10);

	        for (int i = 0; i < 4; i++) {
	            int index = (int) (AlphaNumericString.length() * Math.random());
	            sb.append(AlphaNumericString.charAt(index));
	        }

	        return sb.toString();
	    }

	    private int getRandomValueForPublishedYear() {

	        int minYear = 1800;
	        int maxYear = 2019;

	        int year = minYear + (int) (Math.random() * ((maxYear - minYear) + 1));

	        return year;
	    }

	    public int getRandomValueForPrice() {
	    	
	        int minPrice = 100;
	        int maxPrice = 2000;

	        double price = minPrice + (float) (Math.random() * ((maxPrice - minPrice) + 1));

	        return (int) price;
	    }

	    public BindingType getRandomValueForBindingType() {
	        Random random = new Random();
	        return BindingType.values()[new Random().nextInt(BindingType.values().length)];

	    }

	}
