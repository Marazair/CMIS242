/*
 * File Name: Account.java
 * Name: Nick Mills
 * Date: 4/7/17
 * Purpose: Creates account management methods for use with the BankGUI.
 */
public class Account {
	private static final double SERVICE_CHARGE = 1.50;
	private static final int FREE_withdrawalS = 4;
	private static final boolean FREE_TRANSFERS = true;
	
	private static int withdrawals = 0;
	private double accountTotal = 0;
	
	public Account(){
		accountTotal = 0;
	}
	
	public Account(double accountTotal) {
		this.accountTotal = accountTotal;
	}
	
	public void withdraw(double amount) throws InsufficientFunds {
		withdraw(false, amount);
	}
	
	//Attempts to withdraw the given amount from the current accountTotal.
	//Increments withdrawals and potentially applies a service charge if the transaction is not free.
	public void withdraw(boolean freeTransaction, double amount) throws InsufficientFunds{
		if ((withdrawals >= FREE_withdrawalS) && (freeTransaction == false)){
			amount += SERVICE_CHARGE;
		}
		
		if (accountTotal - amount >= 0) {
			accountTotal -= amount;
			if (freeTransaction == false) {
				withdrawals++;
			}
		}
		else {
			InsufficientFunds i = new InsufficientFunds();
			throw i;
		}
	}
	
	//Adds the given amount to the current accountTotal.
	public void deposit(double amount) {
		accountTotal += amount;
	}
	
	//Transfers money from the current account's accountTotal to the otherAccount's accountTotal.
	public void transfer(double amount, Account otherAccount) throws InsufficientFunds {
		withdraw(FREE_TRANSFERS, amount);
		otherAccount.deposit(amount);
	}
	
	//Returns current accountTotal.
	public double balance() {
		return accountTotal;
	}
	
	//Checks to see if the user is getting close to incurring a service charge or is currently incurring a service charge.
	//Returns a string informing them of this status.
	public static String serviceChargeApplied() {
		String ret = "";
		
		if (withdrawals == FREE_withdrawalS) {
			ret = " A $1.50 service charge will be applied on your next withdrawal";
		}
		
		else if (withdrawals > FREE_withdrawalS) {
			ret = " $1.50 service charge is being applied.";
		}
		
		return ret;
	}
}
