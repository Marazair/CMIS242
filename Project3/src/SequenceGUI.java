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
	private JTextField nField = new JTextField(10);
	private JFormattedTextField resultField = new JFormattedTextField(10);
	private JFormattedTextField efficiencyField = new JFormattedTextField(10);
	
	private String currentMethod;
	private int n;
	
	public SequenceGUI() {
		super.setLayout(new GridLayout(5,2,10,10));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		JPanel radioPanel = new JPanel(new GridLayout(2,1,0,0));
		
		JRadioButtonMenuItem iterative = new JRadioButtonMenuItem("Iterative");
		iterative.setActionCommand("iterative");
		iterative.setSelected(true);
		currentMethod = "iterative";
		
		JRadioButtonMenuItem recursive = new JRadioButtonMenuItem("Recursive");
		recursive.setActionCommand("recursive");
		
		ButtonGroup methods = new ButtonGroup();
		methods.add(iterative);
		methods.add(recursive);
		
		JButton compute = new JButton("Compute");
		compute.setActionCommand("compute");
		
		JLabel nLabel = new JLabel("Enter n:");
		JLabel resultLabel = new JLabel("Result:");
		JLabel efficiency = new JLabel("Efficiency:");
		
		iterative.addActionListener(this);
		recursive.addActionListener(this);
		compute.addActionListener(this);
		
		nField.addActionListener(this);
		resultField.addActionListener(this);
		resultField.setText("");
		resultField.setEditable(false);
		efficiencyField.addActionListener(this);
		efficiencyField.setText("");
		efficiencyField.setEditable(false);
		
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SequenceGUI sequence = new SequenceGUI();
		sequence.setOpaque(true);
		frame.setContentPane(sequence);
		
		frame.pack();
		frame.setVisible(true);
		System.out.println(Sequence.computeIterative(5));
		System.out.println(Sequence.computeRecursive(5));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("iterative".equals(e.getActionCommand())) {
			currentMethod = "iterative";
		}
		if ("recursive".equals(e.getActionCommand())) {
			currentMethod = "recursive";
		}
		if ("compute".equals(e.getActionCommand())) {
			
			try {
				n = Integer.parseInt(nField.getText());
				
				if (currentMethod == "iterative") {
					resultField.setText(Integer.toString(Sequence.computeIterative(n)));
				}
				else if (currentMethod == "recursive") {
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
	
	public class SequenceFileEventHandler extends WindowAdapter{
		@Override
		public void windowClosed(WindowEvent e) {
			try {
				PrintWriter writer = new PrintWriter("info.txt", "UTF-8");
				for(int x = 0; x <= 10; x++){
					writer.print(x + ", ");
					Sequence.computeIterative(x);
					writer.print(Sequence.getEfficiency() + ", ");
					Sequence.computeRecursive(x);
					writer.print(Sequence.getEfficiency());
				}
				writer.close();
			}
			catch (IOException ex) {
				JFrame popupFrame = new JFrame("Popup");
				JOptionPane.showMessageDialog(popupFrame, "Problem with writing to file.");
			}
		}
	}

}
