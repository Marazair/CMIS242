import java.io.*;
import java.util.Scanner;

public class FIleBreaker {
	private static final String NONEXISTANT_FILENAME = "dhfldasjflkdsajl";
	
	public static void main(String args[]) {
		ReadFile();
	}
	
	public static void ReadFile() {
		try {
			Scanner reader = CreateFileReader();
			while(reader.hasNext()){
				System.out.print(reader.next());
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Invalid File Name.");
		}
	}
	
	public static Scanner CreateFileReader() throws FileNotFoundException {
		Scanner s = new Scanner(new File(NONEXISTANT_FILENAME));
		return s;
	}
	
}
