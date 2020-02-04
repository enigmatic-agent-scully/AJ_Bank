package com.aj.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aj.models.Account;
import com.aj.models.User;
import com.aj.service.UserServiceImpl;

public class Menus {
	public static int numInput;
	public static String strInput;
	public static UserServiceImpl userSvc = new UserServiceImpl();
	public static User currentUser = null;
	public static List<User> users = new ArrayList<>();
	static boolean flag, isValid;

	public static User mainMenu(Scanner scan) {
		users = userSvc.getAllUsers();
		return welcomeMenu(scan);
	}

	public static User welcomeMenu(Scanner scan) {
		System.out.println("Welcome to JDBC Bank.\n");
		System.out.println("Please select an option from the menu below:\n");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Quit");
		numInput = scan.nextInt();

		switch (numInput) {
		case 1: {
			currentUser = loginMenu(scan);
			
			break;
		}
		case 2: {
			currentUser = registerMenu(scan);
			break;
		}
		default: {
			System.out.println("Please enter the number next to the menu item you want to choose.");
		}

		}
		return currentUser;
	}

	public static User loginMenu(Scanner scan) {
		String user, pw;
		do {
			
			flag = true;
			System.out.println("Please enter your username:");
			Scanner scan1 = new Scanner(System.in);
			user = scan1.nextLine();
			isValid = UserServiceImpl.userExists(user);
			if (isValid) {
				System.out.println("Please enter your password:");
				pw = scan1.nextLine();
				userSvc.logIn(user, pw);
				flag = false;

			} else {
				System.out.println("We're sorry, we don't recognize that username. " + "Please try again.");
			}

		} while (flag);
		System.out.println("Welcome, " + UserServiceImpl.getCurrentUser().getFname() + "!");
		return UserServiceImpl.getCurrentUser();
	}

	public static User registerMenu(Scanner scan) {
		// Create new empty user object
		User newUser = new User();
		boolean isUsernameValid = false;
		boolean isPwValid = false;
		String user = null;
		while (!isUsernameValid) {
			user = scan.nextLine();
			System.out.println("Please enter a username that is 8 or more characters long.");
			user = scan.nextLine();
			isUsernameValid = userSvc.validateUsername(user);
		}
		newUser.setUname(user);

		System.out.println(
				"Please enter a password that is greater than 8 characters long and contains both upper and lowercase characters.");
		String pw = scan.nextLine();
		System.out.println(isPwValid = userSvc.validatePassword(pw));
		while (!isPwValid) {
			System.out.println(
					"Please enter a password that is greater than 8 characters long, contains at least one number, and contains both upper and lowercase characters.");
			pw = scan.nextLine();
			isPwValid = userSvc.validatePassword(pw);
		}
		newUser.setPw(pw);
		System.out.println("Please enter your first name:");
		String fname = scan.nextLine();
		newUser.setFname(fname);
		System.out.println("Please enter your last name:");
		String lname = scan.nextLine();
		newUser.setLname(lname);
		newUser.setRole("cust");
		return userSvc.createUser(newUser);

	}

	public static void viewAllAcc() {

	}
	
	

	public static Account openAccount(Scanner scan) {
		Account newAcc = new Account();

		return newAcc;
	}

	public static void delayTime(int count) {

		for (int i = 0; i < count; i++) {

			try {
				Thread.sleep(500);
				System.out.print(" .");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println();
	}
}
