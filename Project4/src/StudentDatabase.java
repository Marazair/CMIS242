import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class StudentDatabase extends JPanel implements ActionListener{
	private JTextField idField = new JTextField(10);
	private JTextField nameField = new JTextField(10);
	private JTextField majorField = new JTextField(10);
	private JComboBox<String> optionDropdown;
	
	public StudentDatabase() {
		super.setLayout(new GridLayout(5,2,10,10));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		JLabel idLabel = new JLabel("ID:");
		JLabel nameLabel = new JLabel("Name:");
		JLabel majorLabel = new JLabel("Major:");
		JLabel selectionLabel = new JLabel("Choose Selection:");
		
		String[] options = {"Insert","Delete","Find","Update"};
		optionDropdown = new JComboBox<String>(options);
		
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
		
		
	}
}
