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
	Scanner sc = new Scanner(System.in);
	List<User> users;
	
	ATM(){
		users = new ArrayList<>();
		populateList();
	}
	
	private void populateList() {
		Scanner in = null;
		users.clear();
		try {
			in = new Scanner(new FileInputStream("C:\\Users\\prane\\eclipse-workspace\\Java\\src\\OOPproject\\database.txt"));
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
	
	private User getUser(int id) {
		return users.get(id-1);
	}
	
	public int enterPin() {
		System.out.println("\nPlease enter your pin :-\n");
		return sc.nextInt();
	}
	
	public boolean selectTransaction(int choice, int id) {
		User user = getUser(id);
		if(choice==1) {
			user.showBalance();
		}else if(choice==2) {
			int amount = enterAmount();
			user.deposit(amount);
		}else if(choice==3) {
			int amount = enterAmount();
			user.withdraw(amount);
		}else if(choice==4) {
			displayExitMessage();
			return false;
		}
		users.set(id-1, user);
		return true;
	}
	
	public int enterAmount() {
		System.out.println("\nPlease enter your amount :-\n");
		return sc.nextInt();
	}
	
	public void displayWelcomeMessage() {
		System.out.println("\nHello. Welcome to OOP Bank.");
	}
	
	public int displayLoginChoiceMessage() {
		System.out.println("\nEnter :-\n1 --> For NEW user.\n2 --> For EXISTING user.");
		int n = sc.nextInt();
		return n;
	}
	
	public int displayNewUserMessage() {
		int p = 0;
		while(true) {
			System.out.println("\nEnter your chosen pin (number of 4 digits) :-");
			String pin = sc.next();
			if(pin.length()!=4) {
				System.out.println("\nPlease enter a number of 4 digits as your number.");
			}else {
				try {
					p = Integer.parseInt(pin);
					break;
				}catch(Exception e) {
					System.out.println("\nPlease enter a number of 4 digits as your number.");
					continue;
				}
			}
		}
		int id = addNewUser(p);
		return id;
	}
	
	private int addNewUser(int pin) {
		int id = users.size()+1;
		int balance = 0;
		PrintWriter out = null;
		users.add(new User(id, pin, balance));
		try {
			out = new PrintWriter(new FileOutputStream("C:\\Users\\prane\\eclipse-workspace\\Java\\src\\OOPproject\\database.txt", true));
			out.println(id + " " + pin + " " + balance);
			out.close();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	
	public int displayExistingUserMessage() {
		int p = 0;
		int id = 0;
		while(true) {
			System.out.println("\nEnter your id :-");
			try {
				id = Integer.parseInt(sc.next());
			}catch(Exception e) {
				System.out.println("\nPlease enter a valid ID.");
			}
			System.out.println("\nEnter your pin :-");
			String pin = sc.next();
			if(pin.length()!=4) {
				System.out.println("\nPlease enter a number of 4 digits as your number.");
			}else {
				try {
					p = Integer.parseInt(pin);
				}catch(Exception e) {
					System.out.println("\nPlease enter a number of 4 digits as your number.");
					continue;
				}
			}
			if(users.get(id-1).checkPin(p)) break;
			else this.displayPinErrorMessage();
		}
		return id;
	}
	
	public boolean checkId(int id, int pin) {
		return users.get(id-1).checkPin(pin);
	}
	
	public void deposit(int id, int amount) {
		users.get(id-1).account.increase(amount);
		updateDatabase(id,users.get(id-1).account.balance);
	}
	
	public void withdraw(int id, int amount) {
		users.get(id-1).account.decrease(amount);
		updateDatabase(id,users.get(id-1).account.balance);
	}
	
	private void updateDatabase(int id, int amount) {
		
		PrintWriter out = null;
		Scanner in = null;
		try {
			File file = new File("C:\\Users\\prane\\eclipse-workspace\\Java\\src\\OOPproject\\database.txt");
			File temp = new File("C:\\Users\\prane\\eclipse-workspace\\Java\\src\\OOPproject\\temp.txt");
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
//				String line = in.nextLine();
//			    String[] user = line.split(" ");
//			    if(Integer.parseInt(user[0])==id) {
//			    	line = user[0] +" " +  user[1] +" "+ amount;
//			    }
//			    out.println(line);
			}
			in.close();
			out.flush();
			out.close();
			copyFile();
//			file.delete();
//			System.out.println(temp.renameTo(file));
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void copyFile() {
		try {
			FileReader fin = new FileReader("C:\\Users\\prane\\eclipse-workspace\\Java\\src\\OOPproject\\temp.txt");  
			FileWriter fout = new FileWriter("C:\\Users\\prane\\eclipse-workspace\\Java\\src\\OOPproject\\database.txt");  
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
	
	public int displayChoiceMessage() {
		System.out.println("\nEnter :-\n1 --> To show balance.\n2 --> To deposit money.\n3 --> To withdraw money.\n4 --> To exit.\n");
		return sc.nextInt();
	}
	
	public void displayPinErrorMessage() {
		System.out.println("\nEntered pin is incorrect.");
	}
	
	public void displayBalance(int id) {
		users.get(id-1).account.showBalance();
	}
	
	public void displayErrorMessage() {
		System.out.println("\nError. Please try again later.");
	}
	
	public void displayExitMessage() {
		System.out.println("\nThank you. See you soon.");
	}
	
	public static void main(String[] args) {
//		Card card1 = new Card(1234);
//		Account account1 = new Account(5000);
//		User user1 = new User(card1, account1);
//		
//		ATM atm = new ATM(user1);
//		atm.displayWelcomeMessage();
//		
//		int enteredPin = atm.enterPin();
//		if(atm.user.card.checkPin(enteredPin)) {
//			
//			while(true) {
//				atm.displayChoiceMessage();
//				int choice = sc.nextInt();
//				if(!atm.selectTransaction(choice)) break;
//			}
//			
//			
//			
//		}else {
//			atm.displayPinErrorMessage();
//		}
		
	}

}