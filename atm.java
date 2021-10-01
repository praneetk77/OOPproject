import java.util.*;
class ATM{
	User u;
	
}
class Acc{
int accnumber;
int balance;
boolean checkacc(int acc) {
	if(acc==this.accnumber) {
		return true;
	}else {
		return false;
	}
}
void showBalance() {
	System.out.println(this.balance);
}
}
class Card{
	int pin;
	Card(int pin){
		this.pin=pin;
	}
	boolean checkpin(int pin) {
		if(pin==this.pin) {
			return true;
		}else {
			return false;
		}
	}
	void show() {
		System.out.println(this.pin);
	}
}
class User{
	String name;
	int a;
	int p;
	 User(String name,int a,int p){
		this.name=name;
		this.a=a;
		this.p=p;
	}
	void show() {
		System.out.println(this.name+" "+this.a+" "+this.p+" ");
	}
}
public class atm {

	public static void main(String[] args) {
		Scanner obj=new Scanner(System.in);
		System.out.println("Enter the details of a new user:");
		String a=obj.nextLine();
		int b=obj.nextInt();
		int c=obj.nextInt();
		User u=new User(a,b,c);
		Card c1=new Card(c);
		System.out.println("Enter the details of an existing user: ");
		
//		u.show();
	}

}
