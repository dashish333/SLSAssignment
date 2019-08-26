package com.software.eCommerce.UI;

import java.util.Scanner;

public class BasicUserInterface implements UserInterface {
	
	private static final Scanner input = new Scanner(System.in);
	
	public void userInstructions() {
	System.out.println("!!Welcome!!");
    System.out.println("Choose correspoding number for your choice");
    System.out.println("1: Search for Book");
    System.out.println("2: List all the books available");
    System.out.println("3: Buy a Book");

	}

	public int getUserInput() {
		
		return input.nextInt();
	}
	
	
	
}