package OOPproject;

public class Account {
	
	int balance;
	
	Account(int balance){
		this.balance = balance;
	}
	
	public void showBalance() {
		System.out.println("Your current balance is Rs." + this.balance + ".\n");
	}
	
	public void increase(int amount) {
		this.balance += amount;
		System.out.println("\nRs." + amount + " has been credited to your account. Your current balance is Rs." + this.balance + ".\n");
	}
	
	public void decrease(int amount) {
		if(this.isDeductionAllowed(amount)) {
			this.balance -= amount;
			System.out.println("\nRs." + amount + " has been deducted from your account. Your current balance is Rs." + this.balance + ".\n");
		}else {
			System.out.println("Your balance is not enough to process this transaction. Please deposit Rs." + (amount-this.balance) + " first or reduce the required amount.\n");
		}
	}
	
	private boolean isDeductionAllowed(int amount) {
		return(amount<this.balance); 
	}

}
