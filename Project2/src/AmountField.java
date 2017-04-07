import java.text.*;
import javax.swing.*;

public class AmountField extends JFormattedTextField{
	
	public AmountField(Format format){
		super(format);
	}
	
	@Override
	protected void invalidEdit(){
		JFrame frame = new JFrame("Invalid Input");
		JOptionPane.showMessageDialog(frame, "Please enter a positive number preceded by the '$' sign.");
	}
}
