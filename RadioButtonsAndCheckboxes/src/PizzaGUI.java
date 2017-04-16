import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class PizzaGUI extends JPanel implements ActionListener{
	public PizzaGUI(){
		super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		
	}

	public static void main(String[] args) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}
