/*
 * File Name: BankGUI.java
 * Name: Nick Mills
 * Date: 4/7/17
 * Purpose: Create a GUI through which one can perform several actions on both their checking and savings accounts.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.*;
import java.beans.*;

public class BankGUI extends JPanel implements ActionListener, PropertyChangeListener {
	
	private Account account;
	private Account checkingAccount;
	private Account savingsAccount;
	private double amount = 0;
	private JFormattedTextField amountField;
	
	public BankGUI() {
		checkingAccount = new Account(0);
		savingsAccount = new Account(0);
		
		JButton withdraw = new JButton("Withdraw");
		withdraw.setActionCommand("withdraw");
		
		JButton deposit = new JButton("Deposit");
		deposit.setActionCommand("deposit");
		
		JButton transfer = new JButton("Transfer from");
		transfer.setActionCommand("transfer");
		
		JButton balance = new JButton("Balance");
		balance.setActionCommand("balance");
		
		JRadioButtonMenuItem checking = new JRadioButtonMenuItem("Checking");
		checking.setActionCommand("checking");
		
		JRadioButtonMenuItem savings = new JRadioButtonMenuItem("Savings");
		checking.setActionCommand("savings");
		
		ButtonGroup accounts = new ButtonGroup();
		accounts.add(savings);
		accounts.add(checking);
		checking.setSelected(true);
		account = checkingAccount;
		
		amountField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
		amountField.setColumns(10);
		amountField.setValue(new Double(0.00));
		amountField.addPropertyChangeListener("value", this);
		
		withdraw.addActionListener(this);
		deposit.addActionListener(this);
		transfer.addActionListener(this);
		balance.addActionListener(this);
		savings.addActionListener(this);
		checking.addActionListener(this);
		
		withdraw.setToolTipText("Withdraw the typed amount from the selected account. (Must be in increments of $20)");
		deposit.setToolTipText("Deposit the typed amount to the selected account.");
		transfer.setToolTipText("Transfer the typed amount from the selected account to the other account.");
		balance.setToolTipText("Displays current balance of selected account.");
		
		add(withdraw);
		add(deposit);
		add(transfer);
		add(balance);
		add(checking);
		add(savings);
		add(amountField);
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("ATM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BankGUI bank = new BankGUI();
		bank.setOpaque(true);
		frame.setContentPane(bank);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if("withdraw".equals(e.getActionCommand())) {
			if((amount % 20) == 0){
				try {
					account.withdraw(amount);
				}
				catch(InsufficientFunds ex) {
					
				}
			}
		}
		else if("deposit".equals(e.getActionCommand())) {
			account.deposit(amount);
			System.out.println(account.balance());
		}
		else if("transfer".equals(e.getActionCommand())) {
			
		}
		else if("balance".equals(e.getActionCommand())) {
			
		}
		else if("checking".equals(e.getActionCommand())) {
			account = checkingAccount;
		}
		else if("savings".equals(e.getActionCommand())) {
			account = savingsAccount;
		}
	}

	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();
		
		if(source == amountField) {
			amount = ((Number)amountField.getValue()).doubleValue();
		}
	}
}
