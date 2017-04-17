import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SequenceGUI extends JPanel implements ActionListener {
	public SequenceGUI() {
		super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		JRadioButtonMenuItem iterative = new JRadioButtonMenuItem("Iterative");
		iterative.setActionCommand("iterative");
		
		JRadioButtonMenuItem recursive = new JRadioButtonMenuItem("Recursive");
		recursive.setActionCommand("recursive");
		
		
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
