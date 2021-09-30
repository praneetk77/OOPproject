package OOPproject;

public class Card {
	int pin;
	
	Card(int pin){
		this.pin = pin;
	}
	
	public boolean checkPin(int enteredPin) {
		return (enteredPin == this.pin);
	}
}
