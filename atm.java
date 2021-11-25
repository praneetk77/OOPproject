package OOPproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ATM {
	Scanner scanner = new Scanner(System.in);
	List<User> users;
	int machineBalance = 10000;
	final String databaseExtension = "C:\\Users\\prane\\eclipse-workspace\\Java\\src\\OOPproject\\database.txt";
	final String tempExtension = "C:\\Users\\prane\\eclipse-workspace\\Java\\src\\OOPproject\\temp.txt";
	
	ATM(){
		users = new ArrayList<>();
		populateList();
	}
	
	private void populateList() {
		Scanner in = null;
		users.clear();
		try {
			in = new Scanner(new FileInputStream(databaseExtension));
			while(in.hasNext() && in.hasNextLine()) {
				int id = in.nextInt();
				int pin = in.nextInt();
				int amount = in.nextInt();
				if(in.hasNextLine())in.nextLine();
				User user = new User(id, pin, amount);
				users.add(user);
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void displayWelcomeMessage() {
		System.out.println("\nHello. Welcome to OOP Bank.");
	}
	
	public int displayLoginChoiceMessage() {
		System.out.println("\nEnter :-\n1 --> For NEW user.\n2 --> For EXISTING user.");
		int n = scanner.nextInt();
		return n;
	}
	
	public int displayNewUserMessage() {
		int p = 0;
		while(true) {
			System.out.println("\nEnter your chosen pin (number of 4 digits) :-");
			String pin = scanner.next();
			if(pin.length()!=4) {
				this.displayPinFormatErrorMessage();
			}else {
				try {
					p = Integer.parseInt(pin);
					break;
				}catch(Exception e) {
					this.displayPinFormatErrorMessage();
					continue;
				}
			}
		}
		int id = addNewUser(p);
		return id;
	}
	
	
	public int displayExistingUserMessage() {
		int p = 0;
		int id = 0;
		while(true) {
			System.out.println("\nEnter your id :-");
			try {
				id = Integer.parseInt(scanner.next());
			}catch(Exception e) {
				System.out.println("\nPlease enter a valid ID.");
			}
			System.out.println("\nEnter your pin :-");
			String pin = scanner.next();
			if(pin.length()!=4) {
				this.displayPinFormatErrorMessage();
			}else {
				try {
					p = Integer.parseInt(pin);
				}catch(Exception e) {
					this.displayPinFormatErrorMessage();
					continue;
				}
			}
			if(getUser(id).checkPin(p)) break;
			else this.displayPinErrorMessage();
		}
		return id;
	}
	
	private int addNewUser(int pin) {
		int id = users.size()+1;
		int balance = 0;
		PrintWriter out = null;
		users.add(new User(id, pin, balance));
		try {
			out = new PrintWriter(new FileOutputStream(databaseExtension, true));
			out.println(id + " " + pin + " " + balance);
			out.close();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	public int displayChoiceMessage(int id) {
		System.out.println("\nWelcome user number " + id + ".\nEnter :-\n1 --> To show balance.\n2 --> To deposit money.\n3 --> To withdraw money.\n4 --> To exit.\n");
		return scanner.nextInt();
	}
	
	public void displayBalance(int id) {
		getUser(id).account.showBalance();
	}
	
	public void deposit(int id, int amount) {
		getUser(id).deposit(amount);
		updateDatabase(id,getUser(id).account.balance);
	}
	
	public void withdraw(int id, int amount) {
		if(machineBalance>=amount) {
			getUser(id).withdraw(amount);
			updateDatabase(id,getUser(id).account.balance);
		}else {
			machineBalance += 10000;
			System.out.println("Machine balance insufficient. Please try again");
		}
		
	}
	
	public void displayExitMessage() {
		System.out.println("\nThank you. See you soon.");
	}
	
	private User getUser(int id) {
		return users.get(id-1);
	}
	
	public int enterAmount() {
		System.out.println("\nPlease enter your amount :-\n");
		return scanner.nextInt();
	}
	
	private void displayPinErrorMessage() {
		System.out.println("\nEntered pin is incorrect.");		
	}
	
	private void displayPinFormatErrorMessage() {
		System.out.println("\nPlease enter a number of 4 digits as your number.");
	}	
	
	private void updateDatabase(int id, int amount) {
		
		PrintWriter out = null;
		Scanner in = null;
		try {
			File file = new File(databaseExtension);
			File temp = new File(tempExtension);
			in = new Scanner(file);
			out = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
			String x = "", y = "", z = "";
			while(in.hasNextLine()) {
				String line = in.nextLine();
				x = line.split(" ")[0];
				y = line.split(" ")[1];
				z = line.split(" ")[2];
				if(Integer.parseInt(x)==id) {
					out.println(x + " " + y + " " + amount);
				}else {
					out.println(x + " " + y + " " + z);
				}
			}
			in.close();
			out.flush();
			out.close();
			copyFile();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void copyFile() {
		try {
			FileReader fin = new FileReader(tempExtension);  
			FileWriter fout = new FileWriter(databaseExtension);  
			int c;  
			while ((c = fin.read()) != -1) {  
			 fout.write(c);  
			}  
			fin.close();  
			fout.close();  
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}	

}