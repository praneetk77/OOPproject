package OOPproject;

public class User {
	int id;
	Account account;
	Card card;
	
	User(Card card, Account account){
		this.account = account;
		this.card = card;
	}
	
	User(int id, int pin, int balance){
		this.id = id;
		this.account = new Account(balance);
		this.card = new Card(pin);
	}
	
	public void showBalance() {
		this.account.showBalance();
	}
	
	public void deposit(int x) {
		this.account.increase(x);
	}
	
	public void withdraw(int x) {
		this.account.decrease(x);
	}

}
