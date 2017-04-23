/*
 * File Name: SequenceGUI.java
 * Name: Nick Mills
 * Date: 4/17/17
 * Purpose: Provide a GUI for interacting with the Sequence class.
 */

import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class SequenceGUI extends JPanel implements ActionListener {
	//Create and initialize the interactable objects in the GUI.
	private JTextField nField = new JTextField(10);
	private JFormattedTextField resultField = new JFormattedTextField(10);
	private JFormattedTextField efficiencyField = new JFormattedTextField(10);
	private JRadioButtonMenuItem recursive = new JRadioButtonMenuItem("Recursive");
	private JRadioButtonMenuItem iterative = new JRadioButtonMenuItem("Iterative");
	
	private int n;
	
	public SequenceGUI() {
		super.setLayout(new GridLayout(5,2,10,10));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		JPanel radioPanel = new JPanel(new GridLayout(2,1,0,0));
		iterative.setSelected(true);
		
		ButtonGroup methods = new ButtonGroup();
		methods.add(iterative);
		methods.add(recursive);
		
		JButton compute = new JButton("Compute");
		compute.setActionCommand("compute");
		
		JLabel nLabel = new JLabel("Enter n:");
		JLabel resultLabel = new JLabel("Result:");
		JLabel efficiency = new JLabel("Efficiency:");
		
		resultField.setText("");
		resultField.setEditable(false);
		efficiencyField.setText("");
		efficiencyField.setEditable(false);
		
		compute.addActionListener(this);
		
		add(new JLabel(""));
		
		radioPanel.add(iterative);
		radioPanel.add(recursive);
		add(radioPanel);
		
		add(nLabel);
		add(nField);
		add(new JLabel(""));
		add(compute);
		add(resultLabel);
		add(resultField);
		add(efficiency);
		add(efficiencyField);
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sequence");
		
		SequenceGUI sequence = new SequenceGUI();
		SequenceFileEventHandler fileWriter = new SequenceFileEventHandler();
		sequence.setOpaque(true);
		frame.setContentPane(sequence);
		frame.addWindowListener(fileWriter);
		
		frame.pack();
		frame.setVisible(true);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("compute".equals(e.getActionCommand())) {
			
			try {
				n = Integer.parseInt(nField.getText());
				
				if (iterative.isSelected()) {
					resultField.setText(Integer.toString(Sequence.computeIterative(n)));
				}
				else if (recursive.isSelected()) {
					resultField.setText(Integer.toString(Sequence.computeRecursive(n)));
				}
				
				efficiencyField.setText(Integer.toString(Sequence.getEfficiency()));
			}
			catch (NumberFormatException ex) {
				JFrame popupFrame = new JFrame("Popup");
				JOptionPane.showMessageDialog(popupFrame, "Please put an integer into the field for n.");
			}
		}
	}
	
	public static class SequenceFileEventHandler extends WindowAdapter{
		public SequenceFileEventHandler() {
			super();
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			try {
				PrintWriter writer = new PrintWriter("info.txt", "UTF-8");
				for(int x = 0; x <= 10; x++){
					writer.print(x + ", ");
					Sequence.computeIterative(x);
					writer.print(Sequence.getEfficiency() + ", ");
					Sequence.computeRecursive(x);
					writer.print(Sequence.getEfficiency());
					writer.println();
				}
				writer.close();
				JFrame popupFrame = new JFrame("Popup");
				JOptionPane.showMessageDialog(popupFrame, "File has been written.");
			}
			catch (IOException ex) {
				JFrame popupFrame = new JFrame("Popup");
				JOptionPane.showMessageDialog(popupFrame, "Problem with writing to file.");
			}
		}
	}
}
