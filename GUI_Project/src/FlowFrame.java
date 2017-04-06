import java.awt.*;
import javax.swing.*;
public class FlowFrame extends NiceFrame{
   public FlowFrame() {
      super("Flow Frame", 300, 200);
      setLayout (new FlowLayout());
      for (int i = 0; i < 20; i++)
         add (new JButton("b" + i));
   }
   public static void main(String[] args) {
      FlowFrame f = new FlowFrame();
      f.display();
   }
}
