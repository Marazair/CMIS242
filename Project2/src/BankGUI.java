/*
 * File Name: BankGUI.java
 * Name: Nick Mills
 * Date: 4/7/17
 * Purpose: Create a GUI through which 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BankGUI extends JPanel implements ActionListener{
	
	public BankGUI(){
		JButton withdraw = new JButton("Withdraw");
		withdraw.setActionCommand("withdraw");
		
		JButton deposit = new JButton("Deposit");
		deposit.setActionCommand("deposit");
		
		JButton transfer = new JButton("Transfer from");
		transfer.setActionCommand("transfer");
		
		JButton balance = new JButton("Balance");
		balance.setActionCommand("balance");
		
		withdraw.addActionListener(this);
		deposit.addActionListener(this);
		transfer.addActionListener(this);
		balance.addActionListener(this);
		
		withdraw.setToolTipText("Withdraw the typed amount from the selected account. (Must be in increments of $20)");
		deposit.setToolTipText("Deposit the typed amount to the selected account.");
		transfer.setToolTipText("Transfer the typed amount from the selected account to the other account.");
		balance.setToolTipText("Displays current balance of selected account.");
		
		add(withdraw);
		add(deposit);
		add(transfer);
		add(balance);
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
			 
		
	}
}
