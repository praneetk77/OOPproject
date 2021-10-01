package OOPproject;

import java.util.Scanner;


public class ATM {
	static Scanner sc = new Scanner(System.in);
	User user;
	
	ATM(User user){
		this.user = user;
	}
	
	public int enterPin() {
		System.out.println("\nPlease enter your pin :-\n");
		return sc.nextInt();
	}
	
	public boolean selectTransaction(int choice) {
		if(choice==1) {
			this.user.showBalance();
		}else if(choice==2) {
			int amount = enterAmount();
			this.user.deposit(amount);
		}else if(choice==3) {
			int amount = enterAmount();
			this.user.withdraw(amount);
		}else if(choice==4) {
			this.displayExitMessage();
			return false;
		}
		return true;
	}
	
	public int enterAmount() {
		System.out.println("\nPlease enter your amount :-\n");
		return sc.nextInt();
	}
	
	public void displayWelcomeMessage() {
		
		System.out.println("\nHello. Welcome to OOP Bank.");
	}
	
	public void displayChoiceMessage() {
		System.out.println("\nEnter :-\n1 --> To show balance.\n2 --> To deposit money.\n3 --> To withdraw money.\n4 --> To exit.\n");
	}
	
	public void displayPinErrorMessage() {
		System.out.println("\nYou have entered the wrong pin. Please try again later ");
	}
	
	public void displayErrorMessage() {
		System.out.println("\nError. Please try again later.");
	}
	
	public void displayExitMessage() {
		System.out.println("\nThank you. See you soon.");
	}
	
	public static void main(String[] args) {
		Card card1 = new Card(1234);
		Account account1 = new Account(5000);
		User user1 = new User(card1, account1);
		
		ATM atm = new ATM(user1);
		atm.displayWelcomeMessage();
		
		int enteredPin = atm.enterPin();
		if(atm.user.card.checkPin(enteredPin)) {
			
			while(true) {
				atm.displayChoiceMessage();
				int choice = sc.nextInt();
				if(!atm.selectTransaction(choice)) break;
			}
			
			
			
		}else {
			atm.displayPinErrorMessage();
		}
		
	}

}
