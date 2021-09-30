package OOPproject;

import java.util.Scanner;


public class ATM {
	static Scanner sc = new Scanner(System.in);
	User user;
	
	ATM(User user){
		this.user = user;
	}
	
	public int enterPin() {
		System.out.println("Please enter your pin :-");
		System.out.println();
		return sc.nextInt();
	}
	
	public void selectTransaction(int choice) {
		if(choice==1) {
			this.user.showBalance();
		}else if(choice==2) {
			int amount = enterAmount();
			this.user.deposit(amount);
			System.out.println("success");
		}else {
			int amount = enterAmount();
			this.user.withdraw(amount);
			System.out.println("success");
		}
	}
	
	public int enterAmount() {
		System.out.println("Please enter your amount :-");
		System.out.println();
		return sc.nextInt();
	}
	
	public void displayWelcomeMessage() {
		System.out.println("Hello. Welcome to OOP Bank.");
	}
	
	public void displayChoiceMessage() {
		System.out.println("Enter 1 to show balance, 2 to deposit and 3 to withdraw.");
	}
	
	public void displayPinErrorMessage() {
		System.out.println("You have entered the wrong pin. Please try again later ");
	}
	
	public void displayErrorMessage() {
		System.out.println("Error. Please try again later.");
	}
	
	public void displayExitMessage() {
		System.out.println("Thank you. See you soon.");
	}
	
	public static void main(String[] args) {
		Card card1 = new Card(1234);
		Account account1 = new Account(5000);
		User user1 = new User(card1, account1);
		
		ATM atm = new ATM(user1);
		atm.displayWelcomeMessage();
		int enteredPin = atm.enterPin();
		if(atm.user.card.checkPin(enteredPin)) {
			atm.displayChoiceMessage();
			int choice = sc.nextInt();
			atm.selectTransaction(choice);
		}else {
			atm.displayPinErrorMessage();
			atm.displayExitMessage();
		}
		
	}

}
