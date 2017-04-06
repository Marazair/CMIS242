import java.awt.*;
public class TriangArrayApplication extends NiceFrame { 
   public TriangArrayApplication() {
      super("Triangular Array", 200, 200);
      add(new TriangArrayPanel()); 
   }
   static public void main(String[] args)   {
      TriangArrayApplication taApp = new TriangArrayApplication();
      taApp.display();
   }
}