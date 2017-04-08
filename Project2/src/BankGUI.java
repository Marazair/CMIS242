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
	private static final double CHECKING_DEFAULT_AMOUNT = 0;
	private static final double SAVINGS_DEFAULT_AMOUNT = 0;
	
	private Account account;
	private Account offAccount;
	private Account checkingAccount;
	private Account savingsAccount;
	private double amount = 0;
	private AmountField amountField;
	private static JFrame PopupFrame = new JFrame("PopUp");
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	
	public BankGUI() {
		super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		JPanel transactionButtons = new JPanel(new GridLayout(2,2,10,10));
		JPanel accountButtons = new JPanel(new FlowLayout());
		JPanel textPanel = new JPanel(new FlowLayout());
		
		checkingAccount = new Account(CHECKING_DEFAULT_AMOUNT);
		savingsAccount = new Account(SAVINGS_DEFAULT_AMOUNT);
		
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
		
		ButtonGroup accounts = new ButtonGroup();
		accounts.add(savings);
		accounts.add(checking);
		checking.setSelected(true);
		account = checkingAccount;
		offAccount = savingsAccount;
		
		amountField = new AmountField(currency);
		amountField.setColumns(20);
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
		transfer.setToolTipText("Transfer the typed amount to the selected account from the other account.");
		balance.setToolTipText("Displays current balance of selected account.");
		
		add(transactionButtons);
		transactionButtons.add(withdraw);
		transactionButtons.add(deposit);
		transactionButtons.add(transfer);
		transactionButtons.add(balance);
		
		add(accountButtons);
		accountButtons.add(checking);
		accountButtons.add(savings);
		
		add(textPanel);
		textPanel.add(amountField);
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
			if(((amount % 20) == 0) && (amount != 0)) {
				try {
					account.withdraw(amount);
					JOptionPane.showMessageDialog(PopupFrame, "Withdrawl of " + currency.format(amount) + " successful." + Account.serviceChargeApplied());
				}
				catch(InsufficientFunds ex) {
					JOptionPane.showMessageDialog(PopupFrame, "Insufficient funds in the account." + Account.serviceChargeApplied());
				}
			}
			else {
				JOptionPane.showMessageDialog(PopupFrame, "Withdrawls must be in increments of $20.");
			}
		}
		else if("deposit".equals(e.getActionCommand())) {
			account.deposit(amount);
		}
		else if("transfer".equals(e.getActionCommand())) {
			try {
				offAccount.transfer(amount, account);
			}
			catch(InsufficientFunds ex) {
				JOptionPane.showMessageDialog(PopupFrame, "Insufficient funds in the account.");
			}
		}
		else if("balance".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(PopupFrame, "The current balance is " + currency.format(account.balance()) + ".");
		}
		else if("checking".equals(e.getActionCommand())) {
			account = checkingAccount;
			offAccount = savingsAccount;
		}
		else if("savings".equals(e.getActionCommand())) {
			account = savingsAccount;
			offAccount = checkingAccount;
		}
	}

	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();
		
		if(source == amountField) {
			amount = ((Number)amountField.getValue()).doubleValue();
		}
	}
}
