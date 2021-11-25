package OOPproject;

public class Main {

	public static void main(String[] args) {
				
		ATM atm = new ATM();
		
		while(true) {
			atm.displayWelcomeMessage();
			int loginChoice = atm.displayLoginChoiceMessage();
			int id = 0;
			
			if(loginChoice==1) {
				id = atm.displayNewUserMessage();
			}else {
				id = atm.displayExistingUserMessage();
				
			}
			
			while(true) {
				int choice = atm.displayChoiceMessage(id);
				if(choice==1) {
					atm.displayBalance(id);
				}else if(choice==2) {
					int amount = atm.enterAmount();
					atm.deposit(id, amount);
				}else if(choice==3) {
					int amount = atm.enterAmount();
					atm.withdraw(id, amount);
				}else {
					atm.displayExitMessage();
					break;
				}
			}
			
			break;
		}
	}

}
