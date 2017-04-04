//HelloWorldApplication.java
import java.awt.*;
public class HelloWorldApplication extends NiceFrame { 
   HelloWorldApplication() {
      super("Hello World", 270, 180); 
   } 
   public void paint(Graphics g){
      g.drawString("HelloWorld!", 100, 100); 
   }
   static public void main(String[] args)  {
      HelloWorldApplication hwApp = new HelloWorldApplication();
      hwApp.display();    
   }
}