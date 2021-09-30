package OOPproject;

public class Account {
	
	int balance;
	
	Account(int balance){
		this.balance = balance;
	}
	
	public void increase(int amount) {
		this.balance += amount;
	}
	
	public void decrease(int amount) {
		this.balance -= amount;
	}

}
