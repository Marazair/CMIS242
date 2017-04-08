/*
 * File Name: Account.java
 * Name: Nick Mills
 * Date: 4/7/17
 * Purpose: Creates account management methods for use with the BankGUI.
 */
public class Account {
	private static final double SERVICE_CHARGE = 1.50;
	private static final boolean FREE_TRANSFERS = true;
	
	private static int withdrawls = 0;
	private double accountTotal = 0;
	
	public Account(double accountTotal) {
		this.accountTotal = accountTotal;
	}
	
	public void withdraw(double amount) throws InsufficientFunds {
		if (withdrawls >= 4){
			amount += SERVICE_CHARGE;
		}
		
		if (accountTotal - amount >= 0){
			accountTotal -= amount;
			withdrawls++;
		}
		
		else {
			InsufficientFunds i = new InsufficientFunds();
			throw i;
		}
	}
	
	public void deposit(double amount) {
		accountTotal += amount;
	}
	
	public void transfer(double amount, Account otherAccount) throws InsufficientFunds {
		withdraw(amount);
		otherAccount.deposit(amount);
	}
	public double balance() {
		return accountTotal;
	}
}
