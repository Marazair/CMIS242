
public class ArrayAnalyzer {
	private static final int ARRAY_SIZE = 10;
	private static final int MEANINGLESS_DATA = 0;
	
	public static void main(String[] args) {
		int array[] = new int[ARRAY_SIZE];
		CreateArray(array);
	}
	
	public static void CreateArray(int[] array){
		for(int x = 0; x < array.length; x++){
			array[x] = MEANINGLESS_DATA;
		}
		
		BreakArray(array);
	}
	
	public static void BreakArray(int[] array){
		System.out.println(array[array.length]);
	}
}
