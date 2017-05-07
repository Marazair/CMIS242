/*
 * File Name: StudentDatabase.java
 * Name: Nick Mills
 * Date: 5/3/17
 * Purpose: Create a GUI for accessing and modifying a database of students.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class StudentDatabase extends JPanel implements ActionListener{
	
	//Create the various text fields.
	private JTextField idField = new JTextField(10);
	private JTextField nameField = new JTextField(10);
	private JTextField majorField = new JTextField(10);
	
	//Create the dropdown menu.
	private JComboBox<String> optionDropdown;
	
	//Create the database hashmap.
	HashMap<Integer, Student> database = new HashMap<Integer, Student>();
	
	
	public StudentDatabase() {
		//Set the layout and pad the edge of the window.
		super.setLayout(new GridLayout(5,2,10,10));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		//Create labels for the various interactive elements.
		JLabel idLabel = new JLabel("ID:");
		JLabel nameLabel = new JLabel("Name:");
		JLabel majorLabel = new JLabel("Major:");
		JLabel selectionLabel = new JLabel("Choose Selection:");
		
		//Set the fields to empty strings.
		idField.setText("");
		nameField.setText("");
		majorField.setText("");
		
		//Create an array of the dropdown options and pass it to the JComboBox constructor.
		String[] options = {"Insert","Delete","Find","Update"};
		optionDropdown = new JComboBox<String>(options);
		
		//Default to Insert.
		optionDropdown.setSelectedIndex(0);
		
		//Create a button that triggers the appropriate 
		JButton processButton = new JButton("Process Request");
		processButton.addActionListener(this);
		
		//Add all the elements to the panel.
		add(idLabel);
		add(idField);
		add(nameLabel);
		add(nameField);
		add(majorLabel);
		add(majorField);
		add(selectionLabel);
		add(optionDropdown);
		add(processButton);
	}
	
	public static void main(String args[]) {
		//Initialize the frame.
		JFrame frame = new JFrame("Student Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		StudentDatabase school = new StudentDatabase();
		school.setOpaque(true);
		frame.setContentPane(school);
		
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Create a frame for displaying popups.
		JFrame popupFrame = new JFrame();
		
		//Check to see if idField contains an integer. If so, continue with key set to the value of idField.
		try {
			int key = Integer.parseInt(idField.getText());
			
			//Set variable for the current options.
			String currentOption = optionDropdown.getSelectedItem().toString();
			
			if (currentOption.equals("Insert")) {
				//Set variables for the current name and major.
				String name = nameField.getText();
				String major = majorField.getText();
				
				//Make sure neither field is empty.
				if (!name.equals("") && !major.equals("")) {
					
					//Make sure the database doesn't already have a student assigned to the given ID.
					if (database.containsKey(key)) {
						
						//If it does, inform the user and ask for a new one.
						invalidId(popupFrame, currentOption);
					}
					else {
						//If the ID is free, insert the student into the database and inform the user.
						database.put(key, new Student(name, major));
						JOptionPane.showMessageDialog(popupFrame, "Insertion of Student " + key + ": " + database.get(key).toString() + " successful.");
					}
				
				//If either field is empty, ask for proper input.
				}
				else if (name.equals("") && major.equals("")) {
					JOptionPane.showMessageDialog(popupFrame, "Please put a name and major in the appropriate fields.");
				}
				else if (name.equals("")) {
					JOptionPane.showMessageDialog(popupFrame, "Please put a name in the appropriate field.");
				}
				else if (major.equals("")) {
					JOptionPane.showMessageDialog(popupFrame, "Please put a major in the appropriate field.");
				}
			}
			else if (currentOption.equals("Delete")) {
				//Check to make sure the key is present.
				if (database.containsKey(key)) {
					//If it is, inform the user which Student will be removed and remove the student.
					JOptionPane.showMessageDialog(popupFrame, "Student " + key + ": " + database.get(key).toString() + " will be removed.");
					database.remove(key);
				}
				else {
					//If it isn't, inform the user that the ID is not present.
					invalidId(popupFrame, currentOption);
				}
			}
			else if (currentOption.equals("Find")) {
				//Check to make sure the key is present.
				if (database.containsKey(key)) {
					//If it is, give the user the student's information.
					JOptionPane.showMessageDialog(popupFrame, "Student " + key + ": " + database.get(key).toString());
				}
				else {
					//If it isn't, inform the user that the ID is not present.
					invalidId(popupFrame, currentOption);
				}
			}
			else if (currentOption.equals("Update")) {
				//Check to make sure the key is present.
				if (database.containsKey(key)) {
					//Create an array containing the different grade choices.
					Object[] gradeChoices = {'A','B','C','D','F'};
					
					//Display a popup that contains a dropdown to select the grade input.
					char grade = (char)JOptionPane.showInputDialog(null, "Choose grade:", "Grade", 
												JOptionPane.INFORMATION_MESSAGE, null, gradeChoices, gradeChoices[0]);
					
					//Create an array for different possible credit values.
					Object[] creditsChoices = {3,6};
					
					//Display a popup that contains a dropdown to select the credit value input.
					int credits = (int)JOptionPane.showInputDialog(null, "Choose credits:", "Credits", 
												JOptionPane.INFORMATION_MESSAGE, null, creditsChoices, creditsChoices[0]);
					
					//Update the student with the new values.
					database.get(key).courseCompleted(grade, credits);
					
					//Display the student info with the updated GPA and inform the user that the update occurred.
					JOptionPane.showMessageDialog(popupFrame, "Student " + key + ": " + database.get(key).toString() + ". Update complete.");
				}
				//If the key is not present, inform the user.
				else {
					invalidId(popupFrame, currentOption);
				}
			}
		}
		
		//If idField does not contain an integer, ask for one.
		catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(popupFrame, "Please put an integer into the ID field.");
		}
	}
	
	//Checks to see which invalid ID message is appropriate and displays the correct one.
	public void invalidId(JFrame frame, String option) {
		if (option.equals("Insert")) {
			JOptionPane.showMessageDialog(frame, "Student ID already in use. Please select a different ID.");
		}
		else {
			JOptionPane.showMessageDialog(frame, "Database does not contain the specified student ID.");
		}
	}
}
