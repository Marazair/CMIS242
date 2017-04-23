/*
 * File Name: Sequence.java
 * Name: Nick Mills
 * Date: 4/17/17
 * Purpose: Calculate the numbers in the sequence using both iterative and recursive methods.
 */

//Declare class as final so that nothing can extend this class.
public final class Sequence {
	
	//Create static variable for keeping track of the most recent function call's efficiency.
	private static int efficiency = 0;
	
	//Declare a private constructor so that nothing can make an instance of this class.
	private Sequence() {
		
	}
	
	//Method for computing the nth term using iteration.
	public static int computeIterative(int n) {
		//Reset the efficiency counter.
		efficiency = 0;
		
		
		int currentTerm = 0;
		int previousTerm = 0;
		int temp = 0;
		
		//Calculate the nth term.
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
	
	//Method that resets the efficiency counter then calls the recursive calculation method.
	public static int computeRecursive(int n) {
		efficiency = 0;
		return recursive(n);
	}
	
	//Method that calculates the nth term using recursion. 
	//Set to private to ensure efficiency is reset before the calculations are made.
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
	
	//Returns the efficiency of the last call to either the recursive or iterative methods.
	public static int getEfficiency() {
		return efficiency;
	}
}
