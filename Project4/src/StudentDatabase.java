import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class StudentDatabase extends JPanel implements ActionListener{
	private JTextField idField = new JTextField(10);
	private JTextField nameField = new JTextField(10);
	private JTextField majorField = new JTextField(10);
	private JComboBox<String> optionDropdown;
	HashMap<Integer, Student> database = new HashMap<Integer, Student>();
	
	
	public StudentDatabase() {
		super.setLayout(new GridLayout(5,2,10,10));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		JLabel idLabel = new JLabel("ID:");
		JLabel nameLabel = new JLabel("Name:");
		JLabel majorLabel = new JLabel("Major:");
		JLabel selectionLabel = new JLabel("Choose Selection:");
		
		idField.setText("");
		nameField.setText("");
		majorField.setText("");
		
		String[] options = {"Insert","Delete","Find","Update"};
		optionDropdown = new JComboBox<String>(options);
		optionDropdown.setSelectedIndex(0);
		
		JButton processButton = new JButton("Process Request");
		processButton.addActionListener(this);
		
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
		JFrame frame = new JFrame("Student Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		StudentDatabase school = new StudentDatabase();
		school.setOpaque(true);
		frame.setContentPane(school);
		
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame popupFrame = new JFrame();
		
		try {
			int key = Integer.parseInt(idField.getText());
			String name = nameField.getText();
			String major = majorField.getText();
			String currentOption = optionDropdown.getSelectedItem().toString();
			
			if((!name.equals("") && !major.equals("")) || !currentOption.equals("Insert")) {
				if (currentOption.equals("Insert")) {
					if (database.containsKey(key)) {
						JOptionPane.showMessageDialog(popupFrame, "Student ID already in use. Please select a different ID.");
					}
					else {
						database.put(key, new Student(name, major));
						JOptionPane.showMessageDialog(popupFrame, "Insertion of student " + key + ": " + database.get(key).toString() + " successful.");
					}
				}
				else if (currentOption.equals("Delete")) {
					if (database.containsKey(key)) {
						JOptionPane.showMessageDialog(popupFrame, "Student " + key + ": " + database.get(key).toString() + " will be removed.");
						database.remove(key);
					}
					else {
						JOptionPane.showMessageDialog(popupFrame, "Database does not contain the specified student ID.");
					}
				}
				else if (currentOption.equals("Find")) {
					if (database.containsKey(key)) {
						JOptionPane.showMessageDialog(popupFrame, "Student " + key + ": " + database.get(key).toString());
					}
					else {
						JOptionPane.showMessageDialog(popupFrame, "Database does not contain the specified student ID.");
					}
				}
				else if (currentOption.equals("Update")) {
					if (database.containsKey(key)) {
						Object[] gradeChoices = {'A','B','C','D','F'};
						char grade = (char)JOptionPane.showInputDialog(null, "Choose grade:", "Grade", 
													JOptionPane.INFORMATION_MESSAGE, null, gradeChoices, gradeChoices[0]);
						
						Object[] creditsChoices = {3,6};
						int credits = (int)JOptionPane.showInputDialog(null, "Choose credits:", "Credits", 
													JOptionPane.INFORMATION_MESSAGE, null, creditsChoices, creditsChoices[0]);
						
						database.get(key).courseCompleted(grade, credits);
						
						JOptionPane.showMessageDialog(popupFrame, "Student " + key + ": " + database.get(key).toString() + ". Update complete.");
					}
					else {
						JOptionPane.showMessageDialog(popupFrame, "Database does not contain the specified student ID.");
					}
				}
			}
			else if(name.equals("") && major.equals("")) {
				JOptionPane.showMessageDialog(popupFrame, "Please put a name and major in the appropriate fields.");
			}
			else if(name.equals("")) {
				JOptionPane.showMessageDialog(popupFrame, "Please put a name in the appropriate field.");
			}
			else if(major.equals("")) {
				JOptionPane.showMessageDialog(popupFrame, "Please put a major in the appropriate field.");
			}
		}
		catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(popupFrame, "Please put a number into the ID field.");
		}
	}
}
