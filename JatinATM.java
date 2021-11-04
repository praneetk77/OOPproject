package OOPproject;

import java.util.*;
class jatin_ATM{
	private User user;
	public void jatin_ATM(User user){
	this.user=user;
	}
}
class JatinCard{
	int pin;
	JatinCard(int pin){
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
class JatinUser{
	String name;
	int a;
	int p;
	 JatinUser(String name,int a,int p){
		this.name=name;
		this.a=a;
		this.p=p;
	}
	void show() {
		System.out.println(this.name+" "+this.a+" "+this.p+" ");
	}
}
public class JatinATM {

	public static void main(String[] args) {
		Scanner obj=new Scanner(System.in);
		System.out.println("Enter the details of a new user:");
		String a=obj.nextLine();
		int b=obj.nextInt();
		int c=obj.nextInt();
		JatinUser u=new JatinUser(a,b,c);
		Card c1=new Card(c);
		System.out.println("Enter the details of an existing user: ");
		
//		u.show();
	}

}
