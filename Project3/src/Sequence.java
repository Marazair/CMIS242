/*
 * File Name: Sequence.java
 * Name: Nick Mills
 * Date: 4/17/17
 * Purpose: Calculate the numbers in the sequence using both iterative and recursive methods.
 */
public final class Sequence {
	
	private static int efficiency = 0;
	
	
	private Sequence() {
		
	}
	
	public static int computeIterative(int n) {
		efficiency = 0;
		int currentTerm = 0;
		int previousTerm = 0;
		int temp = 0;
		
		for (int x = 0; x <= n; x++) {
			if (x == 0) {
				currentTerm = 0;
			}
			else if (x == 1) {
				currentTerm = 1;
				previousTerm = 0;
			}
			else {
				temp = currentTerm;
				currentTerm = (2 * currentTerm) + previousTerm;
				previousTerm = temp;
			}
			efficiency++;
		}
		
		return currentTerm;
	}
	
	public static int computeRecursive(int n) {
		efficiency = 0;
		return recursive(n);
	}
	
	private static int recursive (int n) {
		if (n == 0) {
			efficiency++;
			return 0;
		}
		else if (n == 1) {
			efficiency++;
			return 1;
		}
		
		return 2 * recursive(n-1) + recursive(n-2);
	}
	
	public static int getEfficiency() {
		return efficiency;
	}
}
