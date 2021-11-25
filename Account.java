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
		System.out.println("\nTransaction succesfull.\nRs." + amount + " has been credited to your account.\n");
	}
	
	public void decrease(int amount) {
		if(this.isDeductionAllowed(amount)) {
			if(amount%100!=0) {
				System.out.println("Please enter an amount in the denomination of 100.");
			}else {
				this.balance -= amount;
				System.out.println("\nTransaction succesfull.\nRs." + amount + " has been deducted from your account.\n");
			}
		}else {
			System.out.println("Your balance is not enough to process this transaction.\n");
		}
	}
	
	private boolean isDeductionAllowed(int amount) {
		return(amount<=this.balance); 
	}

}
