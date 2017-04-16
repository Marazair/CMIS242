import java.util.*;

public class Pizza {
	private String crust;
	private List<String> toppings = new ArrayList<String>();
	
	public Pizza(String crust, List<String> toppings) {
		this.crust = crust;
		this.toppings = toppings;
	}
	
	public String toString() {
		String str = "Your pizza on " + crust + " crust with ";
		for (int x = 0; x < toppings.size(); x++) {
			if (x != 0) {
				str += ", ";
			}
			
			if (x == toppings.size() - 1) {
				str += "and ";
			}
			
			str += toppings.get(x);
		}
		
		str += " has been ordered.";
		
		return str;
	}
	
}
