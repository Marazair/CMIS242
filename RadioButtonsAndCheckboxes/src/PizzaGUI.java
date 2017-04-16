import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

public class PizzaGUI extends JPanel implements ActionListener, ItemListener{
	private String crust;
	private String size;
	private List<String> toppings = new ArrayList<String>();
	
	private JCheckBox cheese = new JCheckBox("Cheese");
	private JCheckBox extraCheese = new JCheckBox("Extra Cheese");
	private JCheckBox pepperoni = new JCheckBox("Pepperoni");
	private JCheckBox sausage = new JCheckBox("Sausage");
	private JCheckBox bacon = new JCheckBox("Bacon");
	private JCheckBox olives = new JCheckBox("Olives");
	private JCheckBox onions = new JCheckBox("Onions");
	private JCheckBox peppers = new JCheckBox("Peppers"); 
	
	public PizzaGUI(){
		super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		JPanel sizePanel = new JPanel(new GridLayout(1,3,5,5));
		JPanel crustPanel = new JPanel(new GridLayout(1,2,5,5));
		JPanel toppingsPanel = new JPanel(new GridLayout(3,3,5,5));
		
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
		
		white.setSelected(true);
		crust = "white";
		
		cheese.setSelected(true);
		toppings.add("cheese");
		
		JButton submit = new JButton("Submit");
		submit.setActionCommand("submit");
		
		small.addActionListener(this);
		medium.addActionListener(this);
		large.addActionListener(this);
		white.addActionListener(this);
		wheat.addActionListener(this);
		submit.addActionListener(this);
		
		cheese.addItemListener(this);
		pepperoni.addItemListener(this);
		sausage.addItemListener(this);
		bacon.addItemListener(this);
		onions.addItemListener(this);
		olives.addItemListener(this);
		peppers.addItemListener(this);
		extraCheese.addItemListener(this);
		
		sizePanel.add(small);
		sizePanel.add(medium);
		sizePanel.add(large);
		add(sizePanel);
		
		crustPanel.add(white);
		crustPanel.add(wheat);
		add(crustPanel);
		
		toppingsPanel.add(cheese);
		toppingsPanel.add(pepperoni);
		toppingsPanel.add(sausage);
		toppingsPanel.add(bacon);
		toppingsPanel.add(onions);
		toppingsPanel.add(olives);
		toppingsPanel.add(peppers);
		toppingsPanel.add(extraCheese);
		add(toppingsPanel);
		
		add(submit);
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
		String str = e.getActionCommand();
		
		if (str.equals("white") || str.equals("wheat")) {
			crust = str;
		}
		
		if (str.equals("small") || str.equals("medium") || str.equals("large")) {
			size = str;
		}
		
		if (str.equals("submit")) {
			Pizza pizza = new Pizza(size, crust, toppings);
			JFrame PopupFrame = new JFrame ("Pop-up");
			JOptionPane.showMessageDialog(PopupFrame, pizza.toString());
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		String topping = "";
		
		if (source == cheese) {
			topping = "cheese";
		}
		
		if (source == pepperoni) {
			topping = "pepperoni";
		}
		
		if (source == sausage) {
			topping = "sausage";
		}
		
		if (source == bacon) {
			topping = "bacon";
		}
		
		if (source == onions) {
			topping = "onions";
		}
		
		if (source == olives) {
			topping = "olives";
		}
		
		if (source == peppers) {
			topping = "peppers";
		}
		
		if (source == extraCheese) {
			topping = "extra cheese";
		}
		
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			toppings.remove(topping);
		}
		if (e.getStateChange() == ItemEvent.SELECTED) {
			toppings.add(topping);
		}
	}
}
