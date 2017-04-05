import java.awt.*;
import javax.swing.*;

public class TriangArrayPanel extends JPanel { 
   private JTextArea text = new JTextArea();  
   public TriangArrayPanel () { 
      setBackground(Color.gray); 
      text.setEditable(false);   
      text.setBackground(Color.gray); 
      text.setForeground(Color.cyan); 
      text.setFont(new Font("Times New Roman", Font.BOLD, 20));
      for (String s : TriangArray.getTriangArray(6))
         text.append(s + "\n");  
      add(text);    
   }		
}
