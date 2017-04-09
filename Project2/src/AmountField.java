/*
 * File Name: AmountField
 * Name: Nick Mills
 * Date: 4/7/17
 * Purpose: Changes the behavior of JFormattedTextField so that instead of beeping, invalid input creates a JOptionPane.
 */

import java.text.*;
import javax.swing.*;

public class AmountField extends JFormattedTextField {
	
	public AmountField(Format format) {
		super(format);
	}
	
	//Changes the invalidEdit behavior of JFormattedTextField to inform the user of the proper input format.
	@Override
	protected void invalidEdit() {
		JFrame frame = new JFrame("Invalid Input");
		JOptionPane.showMessageDialog(frame, "Please enter a positive number preceded by the '$' sign.");
	}
}
