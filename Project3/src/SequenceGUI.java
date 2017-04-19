import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class SequenceGUI extends JPanel implements ActionListener {
	private JTextField nField = new JTextField(10);
	private JTextField resultField = new JTextField(10);
	private JTextField efficiencyField = new JTextField(10);
	
	public SequenceGUI() {
		super.setLayout(new GridLayout(2,5,10,10));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		JPanel emptyPanel = new JPanel();
		JRadioButtonMenuItem iterative = new JRadioButtonMenuItem("Iterative");
		iterative.setActionCommand("iterative");
		
		JRadioButtonMenuItem recursive = new JRadioButtonMenuItem("Recursive");
		recursive.setActionCommand("recursive");
		
		JButton compute = new JButton("Compute");
		compute.setActionCommand("compute");
		
		JLabel n = new JLabel("Enter n:");
		JLabel result = new JLabel("Result:");
		JLabel efficiency = new JLabel("Efficiency:");
		
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sequence");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SequenceGUI sequence = new SequenceGUI();
		sequence.setOpaque(true);
		frame.setContentPane(sequence);
		
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public class FileEventHandler extends WindowAdapter{
		
	}

}
