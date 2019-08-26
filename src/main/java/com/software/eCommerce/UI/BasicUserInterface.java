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
    System.out.println("4. View Cart ");

	}

	public int getIntInput() {
		System.out.println("\n Enter Choice ");
		return input.nextInt();
	}

	public void displayWelcomeInformation() {
		System.out.println("Welcome: This is your Cart \n");
		System.out.println("Item \t AdditionalInfo  \t\t\t : \t\t\t price \n");
		
	}
	public void printItem(String itemName,String additionalDetail ,int price) {
		System.out.printf("{} \t Authored by-{} \t\t\t :\t\t\t cost = {}\n",itemName,additionalDetail,price);
	}
	
	public int selectItems() {
		int choice;
		System.out.println("Choose from below menu - \n");
		userInstructions();
		choice = getIntInput();
		return choice;
	}
	
	public String getStringInput() {
		return input.nextLine();
	}	
}