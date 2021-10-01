package OOPproject;

public class User {
	
	Account account;
	Card card;
	
	User(Card card, Account account){
		this.account = account;
		this.card = card;
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
