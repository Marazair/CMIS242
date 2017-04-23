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
	
	//Create a variable for holding the value of n.
	private int n;
	
	public SequenceGUI() {
		//Create the panel and make padding around the edges.
		super.setLayout(new GridLayout(5,2,10,10));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		//Create a panel for organizing the radio buttons and set iterative to be the default calculation method.
		JPanel radioPanel = new JPanel(new GridLayout(2,1,0,0));
		iterative.setSelected(true);
		
		//Group the radio buttons so that they work properly.
		ButtonGroup methods = new ButtonGroup();
		methods.add(iterative);
		methods.add(recursive);
		
		//Create the Compute button.
		JButton compute = new JButton("Compute");
		compute.setActionCommand("compute");
		compute.addActionListener(this);
		
		//Create the labels.
		JLabel nLabel = new JLabel("Enter n:");
		JLabel resultLabel = new JLabel("Result:");
		JLabel efficiency = new JLabel("Efficiency:");
		
		//Make sure the result and efficiency fields are blank to begin with, then make them uneditable.
		resultField.setText("");
		resultField.setEditable(false);
		efficiencyField.setText("");
		efficiencyField.setEditable(false);
		
		//Add all the components to the panel, including blank components to fill up grid slots.
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
		//Create the frame, content panel and window close event handler.
		JFrame frame = new JFrame("Sequence");
		SequenceGUI sequence = new SequenceGUI();
		SequenceFileEventHandler fileWriter = new SequenceFileEventHandler();
		
		//Attach the content pane and window close event handler to the frame.
		sequence.setOpaque(true);
		frame.setContentPane(sequence);
		frame.addWindowListener(fileWriter);
		
		//Display the frame.
		frame.pack();
		frame.setVisible(true);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Give logic for when the compute button is pressed.
		if ("compute".equals(e.getActionCommand())) {
			
			try {
				//Changed the contents of n into an integer.
				n = Integer.parseInt(nField.getText());
				
				//If iterative is selected, pass n to the method which computes the result in the iterative way, 
				//then set that result to the result field.
				if (iterative.isSelected()) {
					resultField.setText(Integer.toString(Sequence.computeIterative(n)));
				}
				//If recursive is selected, pass n to the method which computes the result in the recursive way,
				//then set that result to the result field.
				else if (recursive.isSelected()) {
					resultField.setText(Integer.toString(Sequence.computeRecursive(n)));
				}
				
				//Set the efficiency field to the efficiency of the most recently run calculation method.
				efficiencyField.setText(Integer.toString(Sequence.getEfficiency()));
			}
			//If the text field contains something that cannot be translated into an integer, inform the user.
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
		//This method is called when the window the handler is attached to is closing.
		public void windowClosing(WindowEvent e) {
			
			//Attempt to write a file to info.txt that contains the values of n, iterative efficiency and recursive efficiency
			//for values of n up to and including 10.
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
				
				//Inform the user that the file has been written.
				JFrame popupFrame = new JFrame("Popup");
				JOptionPane.showMessageDialog(popupFrame, "File has been written.");
			}
			
			//If there is a problem writing to the file, inform the user.
			catch (IOException ex) {
				JFrame popupFrame = new JFrame("Popup");
				JOptionPane.showMessageDialog(popupFrame, "Problem with writing to file.");
			}
		}
	}
}
