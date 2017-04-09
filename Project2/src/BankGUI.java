/*
 * File Name: BankGUI.java
 * Name: Nick Mills
 * Date: 4/7/17
 * Purpose: Create a GUI through which one can perform several actions on both their checking and savings accounts.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.text.*;
import java.beans.*;

public class BankGUI extends JPanel implements ActionListener, PropertyChangeListener {
	
	//These are the values which will be passed to the Account constructors for the savings and checking accounts.
	private static final double CHECKING_DEFAULT_AMOUNT = 500;
	private static final double SAVINGS_DEFAULT_AMOUNT = 500;
	
	private Account account;
	private Account offAccount;
	private Account checkingAccount;
	private Account savingsAccount;
	private double amount = 0;
	private AmountField amountField;
	
	//Create a frame for the JOptionPanes.
	private static JFrame PopupFrame = new JFrame("PopUp");
	
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	
	public BankGUI() {
		
		//Create the main panel, padding the edges.
		super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		//Create panels for storing the different types of buttons and fields and lay them out appropriately.
		JPanel transactionButtons = new JPanel(new GridLayout(2,2,10,10));
		JPanel accountButtons = new JPanel(new FlowLayout());
		JPanel textPanel = new JPanel(new FlowLayout());
		
		//Initialize the savings and checking accounts.
		checkingAccount = new Account(CHECKING_DEFAULT_AMOUNT);
		savingsAccount = new Account(SAVINGS_DEFAULT_AMOUNT);
		
		//Create and label the various buttons and prepare to listen to them.
		JButton withdraw = new JButton("Withdraw");
		withdraw.setActionCommand("withdraw");
		
		JButton deposit = new JButton("Deposit");
		deposit.setActionCommand("deposit");
		
		JButton transfer = new JButton("Transfer to");
		transfer.setActionCommand("transfer");
		
		JButton balance = new JButton("Balance");
		balance.setActionCommand("balance");
		
		JRadioButtonMenuItem checking = new JRadioButtonMenuItem("Checking");
		checking.setActionCommand("checking");
		
		JRadioButtonMenuItem savings = new JRadioButtonMenuItem("Savings");
		savings.setActionCommand("savings");
		
		//Group the radio buttons together and set the default account to be checking.
		ButtonGroup accounts = new ButtonGroup();
		accounts.add(savings);
		accounts.add(checking);
		checking.setSelected(true);
		account = checkingAccount;
		offAccount = savingsAccount;
		
		//Create a modified JFormattedTextField with a currency format, set it to 0 and start listening to it.
		amountField = new AmountField(currency);
		amountField.setColumns(20);
		amountField.setValue(new Double(0.00));
		amountField.addPropertyChangeListener("value", this);
		
		//Start listening to the buttons.
		withdraw.addActionListener(this);
		deposit.addActionListener(this);
		transfer.addActionListener(this);
		balance.addActionListener(this);
		savings.addActionListener(this);
		checking.addActionListener(this);
		
		//Create tooltips for the buttons describing their function.
		withdraw.setToolTipText("Withdraw the typed amount from the selected account. (Must be in increments of $20)");
		deposit.setToolTipText("Deposit the typed amount to the selected account.");
		transfer.setToolTipText("Transfer the typed amount to the selected account from the other account.");
		balance.setToolTipText("Displays current balance of selected account.");
		
		//Add the transactionButtons panel and put the relevant buttons in that panel.
		add(transactionButtons);
		transactionButtons.add(withdraw);
		transactionButtons.add(deposit);
		transactionButtons.add(transfer);
		transactionButtons.add(balance);
		
		//Add the accountButtons panel and put the relevant buttons in that panel.
		add(accountButtons);
		accountButtons.add(checking);
		accountButtons.add(savings);
		
		//Add the textPanel panel and put the text field in that panel.
		add(textPanel);
		textPanel.add(amountField);
	}
	
	public static void main(String args[]) {
		//Create the top-level frame with a BankGUI in it.
		JFrame frame = new JFrame("ATM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BankGUI bank = new BankGUI();
		bank.setOpaque(true);
		frame.setContentPane(bank);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		//When the "Withdraw" button is pressed, check to see if the amount in the text field is divisible by 20 and ensure that it is not zero.
		if ("withdraw".equals(e.getActionCommand())) {
			if (((amount % 20) == 0) && (amount != 0)) {
				
				//Attempt to withdraw the amount from the selected account. If successful, inform the user. Warn and inform user of service charges.
				try {
					account.withdraw(amount);
					JOptionPane.showMessageDialog(PopupFrame, "Withdrawal of " + currency.format(amount) + " successful." + Account.serviceChargeApplied());
				}
				
				//If unsuccessful, inform the user. Inform user of service charges.
				catch (InsufficientFunds ex) {
					JOptionPane.showMessageDialog(PopupFrame, "Insufficient funds in the account." + Account.serviceChargeApplied());
				}
			}
			
			//If amount is not divisible by 20 or is zero, inform user of the proper amounts.
			else {
				JOptionPane.showMessageDialog(PopupFrame, "Withdrawals must be in increments of $20.");
			}
		}
		
		//When the "Deposit" button is pressed, deposit the amount in the text field to the selected account.
		else if ("deposit".equals(e.getActionCommand())) {
			account.deposit(amount);
		}
		
		//When the "Transfer" button is pressed, transfer the amount in the text field from the unselected account to the selected account.
		else if ("transfer".equals(e.getActionCommand())) {
			try {
				offAccount.transfer(amount, account);
			}
			
			//If there isn't enough money in the unselected account, inform the user.
			catch (InsufficientFunds ex) {
				JOptionPane.showMessageDialog(PopupFrame, "Insufficient funds in the account.");
			}
		}
		
		//When the "Balance" button is pressed, inform the user of the amount contained in the selected account.
		else if ("balance".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(PopupFrame, "The current balance is " + currency.format(account.balance()) + ".");
		}
		
		//When the "Checking" button is pressed, switch the active account to checking.
		else if ("checking".equals(e.getActionCommand())) {
			account = checkingAccount;
			offAccount = savingsAccount;
		}
		
		//When the "Savings" button is pressed, switch the active account to savings.
		else if ("savings".equals(e.getActionCommand())) {
			account = savingsAccount;
			offAccount = checkingAccount;
		}
	}

	//When the information in the text field changes, if it is a valid input, set amount to the new value.
	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();
		
		if (source == amountField) {
			amount = ((Number)amountField.getValue()).doubleValue();
		}
	}
}
