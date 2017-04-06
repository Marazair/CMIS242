/*
 * File Name: BankGUI.java
 * Name: Nick Mills
 * Date: 4/7/17
 * Purpose: Create a GUI through which 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.*;

public class BankGUI extends JPanel implements ActionListener{
	
	public BankGUI(){
		Account checkingAccount = new Account(0);
		Account savingsAccount = new Account(0);
		
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
		
		JFormattedTextField amountField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
		
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
			
		}
		else if("deposit".equals(e.getActionCommand())) {
			
		}
		else if("transfer".equals(e.getActionCommand())){
			
		}
		else if("balance".equals(e.getActionCommand())){
			
		}
		
	}
}
