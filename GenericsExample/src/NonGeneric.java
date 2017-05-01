import java.util.ArrayList;
import java.util.List;

public class NonGeneric {
	
	private static final int COUNT_TO = 5;
	public static void main(String[] args) {
		List intList = new ArrayList();
		
		for (int x = 1; x <= COUNT_TO; x++) {
			intList.add(x);
			intList.add(new Object());
		}
		
		for (int x = 0; x < intList.size(); x++) {
			System.out.print(intList.get(x) + " ");
		}
		
	}
}
