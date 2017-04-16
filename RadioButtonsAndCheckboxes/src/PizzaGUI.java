import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

public class PizzaGUI extends JPanel implements ActionListener{
	private String crust;
	private String size;
	private List<String> toppings = new ArrayList<String>();
	
	public PizzaGUI(){
		super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		JRadioButtonMenuItem small = new JRadioButtonMenuItem("Small");
		small.setActionCommand("small");
		
		JRadioButtonMenuItem medium = new JRadioButtonMenuItem("Medium");
		medium.setActionCommand("medium");
		
		JRadioButtonMenuItem large = new JRadioButtonMenuItem("Large");
		large.setActionCommand("large");
		
		ButtonGroup sizes = new ButtonGroup();
		sizes.add(small);
		sizes.add(medium);
		sizes.add(large);
		
		medium.setSelected(true);
		size = "medium";
		
		JRadioButtonMenuItem white = new JRadioButtonMenuItem("White");
		white.setActionCommand("white");
		
		JRadioButtonMenuItem wheat = new JRadioButtonMenuItem("Wheat");
		wheat.setActionCommand("wheat");
		
		ButtonGroup crusts = new ButtonGroup();
		crusts.add(white);
		crusts.add(wheat);
		
		JCheckBox extraCheese = new JCheckBox("Extra Cheese");
		
		JCheckBox pepperoni = new JCheckBox("Pepperoni");
		
		JCheckBox sausage = new JCheckBox("Sausage");
		
		JCheckBox bacon = new JCheckBox("Bacon");
		
		JCheckBox olives = new JCheckBox("Olives");
		
		JCheckBox onions = new JCheckBox("Onions");
		
		JCheckBox peppers = new JCheckBox("Peppers");
		
		JButton submit = new JButton("Submit");
		submit.setActionCommand("submit");
		
		add(small);
		add(medium);
		add(large);
		
		add(white);
		add(wheat);
		
		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Pizza Order");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PizzaGUI pizza = new PizzaGUI();
		pizza.setOpaque(true);
		frame.setContentPane(pizza);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
